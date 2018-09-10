/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bkap.model;



import bkap.entity.Catalog;
import bkap.util.ConnectDB;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CatalogModel {
    /**
     * ResultSet trong JDBC là lớp dùng để lấy kết quả trả về sau khi thực hiện
     * các câu lệnh truy vấn SQL bằng Statement, PreparedStatement hoặc CallableStatement.
     * Lây danh sách danh mục của sản phẩm
     * @return 
     */
     public List<Catalog> getAllCatalog(){
        Connection conn = null;
        CallableStatement calla = null;
        List<Catalog> list = new ArrayList<Catalog>();
        try {
            conn = ConnectDB.openConnect();
            calla = conn.prepareCall("{call getAllCatalog()}");
            ResultSet rs = calla.executeQuery();
            while(rs.next()){
                Catalog catalog = new Catalog();
                catalog.setCatalogId(rs.getInt("CatalogId"));
                catalog.setCatalogName(rs.getString("CatalogName"));
                catalog.setDescriptions(rs.getString("Descriptions"));
                catalog.setParentId(rs.getInt("ParentId"));
                catalog.setDisplayNumber(rs.getInt("DisplayNumber"));
                catalog.setImages(rs.getString("Images"));
                catalog.setStatus(rs.getBoolean("Status"));
                list.add(catalog);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CatalogModel.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
           ConnectDB.closeConnection(conn, calla);
        }
        return list;
    }
     /**
      * Lấy danh sách danh mục cha
      * @return 
      */
     public List<Catalog> getCatalogRoot(){
          Connection conn = null;
        CallableStatement calla = null;
        List<Catalog> list = new ArrayList<Catalog>();
        try {
            conn = ConnectDB.openConnect();
            calla = conn.prepareCall("{call getCatalogRoot()}"); // getCatalogRoot: procedure trong CSDL
            ResultSet rs = calla.executeQuery();
            while(rs.next()){
                Catalog catalog = new Catalog();
                catalog.setCatalogId(rs.getInt("CatalogId"));
                catalog.setCatalogName(rs.getString("CatalogName"));
                catalog.setDescriptions(rs.getString("Descriptions"));
                catalog.setParentId(rs.getInt("ParentId"));
                   catalog.setDisplayNumber(rs.getInt("DisplayNumber"));
                catalog.setImages(rs.getString("Images"));
                catalog.setStatus(rs.getBoolean("Status"));
                list.add(catalog);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CatalogModel.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            ConnectDB.closeConnection(conn, calla);
        }
        return list;
     }
     /**
      * THêm mới danh mục sản phẩm
      * @param catalog
      * @return 
      */
    public boolean insertCatalog(Catalog catalog){
        Connection conn = null;
        CallableStatement calla = null;
        boolean check = true;      
        try {
            conn = ConnectDB.openConnect();
            calla = conn.prepareCall("{ call insertCatalog(?,?,?,?,?)}"); // call đến procedure vừa tạo
            // đẩy dữ liệu vào các đầu vào
            calla.setString(1, catalog.getCatalogName());
            calla.setString(2, catalog.getDescriptions());
            calla.setInt(3, catalog.getParentId());
            calla.setString(4, catalog.getImages());
            calla.setInt(5, catalog.getDisplayNumber());
            // thực hiện procedure
            calla.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            check = false;
        } finally{
            ConnectDB.closeConnection(conn, calla);
        }
        return check;
    }
    /**
     * Lấy thông tin danh mục sản phẩm theo mã danh mục
     * @param id
     * @return 
     */
    public Catalog getCatalogById(int id){
        Catalog catalog = new Catalog();
        Connection conn = null;
        CallableStatement calla = null;      
        ResultSet rs;
        try {
            conn = ConnectDB.openConnect();
            calla = conn.prepareCall(" { call getCatalogById(?)}");
            calla.setInt(1, id);
            rs = calla.executeQuery();
            while(rs.next()){
                catalog.setCatalogId(id);
                catalog.setCatalogName(rs.getString("CatalogName"));
                catalog.setDescriptions(rs.getString("Descriptions"));
                catalog.setParentId(rs.getInt("ParentId"));
                catalog.setImages(rs.getString("Images"));
                catalog.setDisplayNumber(rs.getInt("DisplayNumber"));
                catalog.setStatus(rs.getBoolean("Status"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CatalogModel.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
         ConnectDB.closeConnection(conn, calla);
        }
        
        return catalog;
    }
    /**
     * Sửa thông tin danh mục sản phẩm
     * @param catalog
     * @return 
     */
    public boolean updateCatalog(Catalog catalog){
        Connection conn = null;
        CallableStatement calla = null;
        boolean result = false;
        
        try {
            conn = ConnectDB.openConnect();
            calla = conn.prepareCall("{call updateCatalog(?,?,?,?,?,?,?)}");
            calla.setInt(1, catalog.getCatalogId());
            calla.setString(2, catalog.getCatalogName());
            calla.setString(3, catalog.getDescriptions());
            calla.setInt(4, catalog.getParentId());
            calla.setString(5, catalog.getImages());
            calla.setInt(6, catalog.getDisplayNumber());
            calla.setBoolean(7, catalog.isStatus());
            
            calla.executeUpdate();
            result = true;
        } catch (SQLException ex) {
            Logger.getLogger(CatalogModel.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
           ConnectDB.closeConnection(conn, calla);
        }
        return result;
    }
    /**
     * Xóa danh mục sản phẩm theo mã danh mục sản phẩm
     * @param id
     * @return 
     */
    public boolean deleteCatalog(int id){
        Connection conn = null;
        CallableStatement calla = null;
        boolean result = false;
        try {
              conn = ConnectDB.openConnect();
              calla = conn.prepareCall("{ call deleteCatalog(?)}");
              calla.setInt(1, id);
              calla.executeUpdate();
              result = true;
        } catch (SQLException ex) {
            Logger.getLogger(CatalogModel.class.getName()).log(Level.SEVERE, null, ex);
        }
       return result;
    }
    
}
