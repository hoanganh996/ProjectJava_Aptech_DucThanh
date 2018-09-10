/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bkap.model;

import bkap.entity.PaymentType;
import bkap.util.ConnectDB;
import com.sun.xml.internal.ws.org.objectweb.asm.Type;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author panth
 */
public class PaymentTypeModel {
    public List<PaymentType> getAllPaymentType(){
        Connection conn = null;
        CallableStatement calla = null;
        List<PaymentType> list = new ArrayList<PaymentType>();
        ResultSet rs;     
        try {
            conn = ConnectDB.openConnect();
            calla = conn.prepareCall("{call getAllPaymentType()}");
            rs = calla.executeQuery();
            while(rs.next()){
                PaymentType paymentType = new PaymentType();
                paymentType.setPaymentTypeId(rs.getInt("PaymentTypeId"));
                paymentType.setPaymentTypeName(rs.getString("PaymentTypeName"));
                paymentType.setDescription(rs.getString("Descriptions"));
                paymentType.setStatus(rs.getBoolean("Status"));
                list.add(paymentType);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PaymentTypeModel.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectDB.closeConnection(conn, calla);
        }
        return list;
    }
    
    public boolean insertPaymentType(PaymentType paymentType){
        Connection conn = null;
        CallableStatement calla = null;
        boolean result = false;
        
        try {
            conn = ConnectDB.openConnect();
            calla = conn.prepareCall("{call insertPaymentType(?,?)}");
            calla.setString(1, paymentType.getPaymentTypeName());
            calla.setString(2, paymentType.getDescription());
            calla.executeUpdate();
            result = true;
        } catch (SQLException ex) {
            Logger.getLogger(PaymentTypeModel.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectDB.closeConnection(conn, calla);
        }
        return result;
    }
    
    public boolean updatePaymentType(PaymentType paymentType){
        boolean result = false;
        Connection conn = null;
        CallableStatement calla = null;
        
        try {
            conn = ConnectDB.openConnect();
            calla = conn.prepareCall("{call updatePaymentType(?,?,?,?)}");
            calla.setInt(1, paymentType.getPaymentTypeId());
            calla.setString(2,paymentType.getPaymentTypeName());
            calla.setString(3, paymentType.getDescription());
            calla.setBoolean(4, paymentType.isStatus());
            calla.executeUpdate();
            result = true;
        } catch (SQLException ex) {
            Logger.getLogger(PaymentTypeModel.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectDB.closeConnection(conn, calla);
        }
        return result;
    }
    
    public boolean deletePaymentType(int id){
        Connection conn = null;
        CallableStatement calla = null;
        boolean result  = false;
        
        try {
             conn = ConnectDB.openConnect();
            calla = conn.prepareCall("{call deletePaymentType(?)}");
            calla.setInt(1, id);
            calla.executeUpdate();
            result = true;
        } catch (SQLException ex) {
            Logger.getLogger(PaymentTypeModel.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectDB.closeConnection(conn, calla);
        }
        return result;
    }
    
    public boolean checkPaymentTypeName(String name){
        Connection conn = null;
        CallableStatement calla = null;
        boolean result = false;
        
        try {
            conn = ConnectDB.openConnect();
            calla = conn.prepareCall("{call checkPaymentTypeName(?,?)}");
            calla.setString(1, name);
            calla.registerOutParameter(2, Types.BOOLEAN);
            calla.executeUpdate();
            result = calla.getBoolean(2);
        } catch (SQLException ex) {
            Logger.getLogger(PaymentTypeModel.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectDB.closeConnection(conn, calla);
        }
        return result;
    }
    
    public boolean checkExistsPaymentTypeName(PaymentType paymentType){
        Connection conn = null;
        CallableStatement calla = null;
        boolean result = false;
        
        try {
            conn = ConnectDB.openConnect();
            calla = conn.prepareCall("{call checkExistsPaymentTypeName(?,?,?)}");
            calla.setInt(1, paymentType.getPaymentTypeId());
            calla.setString(2, paymentType.getPaymentTypeName());
            calla.registerOutParameter(3, Types.BOOLEAN);
            calla.executeUpdate();
            result = calla.getBoolean(3);
        } catch (SQLException ex) {
            Logger.getLogger(PaymentTypeModel.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectDB.closeConnection(conn, calla);
        }
        return result;
    }
    
    public PaymentType getPaymentTypeById(int id){
        Connection conn = null;
        CallableStatement calla = null;
        PaymentType paymentType = new PaymentType();
        ResultSet rs;
        
        try {
            conn = ConnectDB.openConnect();
            calla = conn.prepareCall("{call getPaymentTypeById()}");
            rs = calla.executeQuery();
            while(rs.next()){
                paymentType.setPaymentTypeId(rs.getInt("PaymentTypeId"));
                paymentType.setPaymentTypeName(rs.getString("PaymentTypeName"));
                paymentType.setDescription(rs.getString("Descriptions"));
                paymentType.setStatus(rs.getBoolean("Status"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PaymentTypeModel.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectDB.closeConnection(conn, calla);
        }
        return paymentType;
    }
}
