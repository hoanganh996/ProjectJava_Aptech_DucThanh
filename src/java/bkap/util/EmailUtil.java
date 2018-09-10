/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bkap.util;

import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author panth
 */
public class EmailUtil {
    public static final String host ="smtp.gmail.com";
    private static final String port ="587";
    private static final String userName ="thanhhaha0611@gmail.com";
    private static final String password ="hoahongden123";
    public static void sendMail(String recipent , String subject , String message) 
            throws AddressException, MessagingException{
        // set các thuộc tính
        
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth","true");
        properties.put("mail.starttls.enable", "true");
        properties.put("mail.user",userName);
        properties.put("mail.password", password);
        
        // tao mot session de xac thuc mail gui
        Authenticator auth = new Authenticator(){
            @Override
            public PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(userName, password);
            }
        };
        Session session = Session.getInstance(properties, auth);
        // Tao mot mail moi
        MimeMessage msg = new MimeMessage(session);
        msg.setHeader("Context-Type", "text/plain; charset= UTF-8");
        msg.setFrom(new InternetAddress(userName));
        InternetAddress[] toAddresses = {new InternetAddress(recipent)};
        msg.setRecipients(Message.RecipientType.TO, toAddresses);
        msg.setSubject(subject,"UTF-8");
        msg.setSentDate(new Date());
        msg.setContent(message, "text/html; charset=utf-8");
        
        // gui mail
        Transport.send(msg);
    }
}
