/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bkap.controller;

import bkap.entity.User;
import bkap.model.UserModel;
import bkap.util.EmailUtil;
import java.io.UnsupportedEncodingException;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/email", method = RequestMethod.GET)
public class SendEmailController {

    @RequestMapping(value = "/sendEmail")
    public ModelAndView doSendEmail(HttpServletRequest request, HttpSession session) throws MessagingException,
            UnsupportedEncodingException {
        ModelAndView model = new ModelAndView("Fontend/PaymentSuccess");
        request.setCharacterEncoding("UTF-8");
        // lấy địa chỉ ng nhận
        UserModel userModel = new UserModel();
        String user = (String) session.getAttribute("account");
        User u = userModel.getUserByUserName(user);

        String recipientAddress = u.getMail();
        // gửi mail
        String url = request.getContextPath() + "/myCart.htm";
        EmailUtil.sendMail(recipientAddress, "Website bán quần áo. Xác nhận thanh toán", "Chúc mừng bạn thanh toán thành công tại shop của chúng tôi. Đay là thông tin đơn hàng của bạn." + url);
        return model;
    }
}
