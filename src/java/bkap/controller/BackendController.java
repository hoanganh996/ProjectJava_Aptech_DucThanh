/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bkap.controller;

import bkap.entity.Catalog;
import bkap.entity.ImageLink;
import bkap.entity.Product;
import bkap.entity.Supplier;
import bkap.model.CatalogModel;
import bkap.model.FeedbackModel;
import bkap.model.FunctionModel;
import bkap.model.GroupModel;
import bkap.model.OrderModel;
import bkap.model.PackageModel;
import bkap.model.PaymentTypeModel;
import bkap.model.ProductModel;
import bkap.model.SupplierModel;
import bkap.model.UserModel;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

public class BackendController {

    private ProductModel productModel;
    private FunctionModel functionModel;
    private UserModel userModel;
    private GroupModel groupModel;
    private SupplierModel supplierModel;
    private PaymentTypeModel paymentTypeModel;
    private PackageModel packageModel;
    private CatalogModel catalogModel;
    private OrderModel orderModel;
    private FeedbackModel feedbackModel;

    public BackendController() {
        productModel = new ProductModel();
        functionModel = new FunctionModel();
        userModel = new UserModel();
        groupModel = new GroupModel();
        supplierModel = new SupplierModel();
        paymentTypeModel = new PaymentTypeModel();
        packageModel = new PackageModel();
        catalogModel = new CatalogModel();
        orderModel = new OrderModel();
        feedbackModel = new FeedbackModel();
    }

    /**
     * Lấy toàn bộ danh sách sản phẩm return trang Product.jsp
     */
    
    @RequestMapping(value="getAllProductBackend")
    public ModelAndView getAllProduct() {
        ModelAndView model = new ModelAndView("Admin/Product");
        List<Product> list = productModel.getAllProduct();
        model.getModelMap().put("listProduct", list);
        return model;
    }

    @RequestMapping(value = "initInsertProduct")
    public ModelAndView initInsertProduct() {
        ModelAndView model = new ModelAndView("Admin/InsertProduct");
        Product product = new Product();
        List<Supplier> listSupplier = supplierModel.getAllSupplier();
        List<Catalog> listCatalog = catalogModel.getAllCatalog();
        model.getModelMap().put("newProduct", product);
        model.getModelMap().put("listCatalog", listCatalog);
        model.getModelMap().put("listSupplier", listSupplier);
        return model;
    }

    /**
     * Thêm mới sản phẩm product: đối tượng sản phẩm cần thêm request: đối tượng
     * request
     *
     * @param mm: đối tượng model map
     * @return
     */
    @RequestMapping(value = "insertProduct")
    public String insertProduct(@ModelAttribute("/*newProduct") Product product, ModelMap mm, HttpServletRequest request) {
        List<String> image = new ArrayList<String>();

        String path = request.getRealPath("/jsp/Fontend/img");
        path = path.substring(0, path.indexOf("\\build"));
        path = path + "\\web\\jsp\\FontEnd\\img";
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload uploader = new ServletFileUpload(factory);
        try {
            List<FileItem> lst = uploader.parseRequest(request);
            for (FileItem fileItem : lst) {
                if (fileItem.isFormField() == false) {
                    //path
                    if ("img".equals(fileItem.getName())) {
                        product.setImages(fileItem.getName());
                    } else if ("imageLink".equals(fileItem.getFieldName())) {
                        image.add(fileItem.getName());
                    }
                    //upload to folder
                    fileItem.write(new File(path + "/" + fileItem.getName()));
                } else {
                    String name = fileItem.getFieldName();
                    String value = fileItem.getString();
                    if (name.equals("productName")) {
                        product.setProductName(value);
                    } else if (name.equals("productContent")) {
                        product.setProductContent(value);
                    } else if (name.equals("productContentDetail")) {
                        product.setProductContentDetail(value);
                    } else if (name.equals("priceInput")) {
                        product.setPriceInput(value);
                    } else if (name.equals("priceOutput")) {
                        product.setPriceOutput(value);
                    } else if (name.equals("supplierId")) {
                        product.setSupplierId(Integer.parseInt(value));
                    } else if (name.equals("catalogId")) {
                        product.setCatalogId(Integer.parseInt(value));
                    } else if (name.equals("discount")) {
                        product.setDiscount(Integer.parseInt(value));
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        boolean check = productModel.insertProduct(product);
        if (check) {
            int id = productModel.getProductIdNew();
            for (String image1 : image) {
                ImageLink imageLink = new ImageLink();
                imageLink.setProductId(id);
                imageLink.setImageLinkName(image1);
                productModel.insertImageLink(imageLink);
            }
            return "redirect:getAllProductBackend.htm";
        } else {
            mm.put("message", "Thêm mới không thành công");
            return "FontEnd/ÍnertProduct";
        }
    }
}
