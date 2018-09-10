/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bkap.model;

import bkap.entity.Feedback;
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

/**
 *
 * @author panth
 */
public class FeedbackModel {
   public boolean insertFeedback(Feedback feedback){ 
       Connection conn = null;
       CallableStatement calla = null;
       boolean result = false;
       
       
       try {
           conn = ConnectDB.openConnect();
           calla = conn.prepareCall("{call insertFeedback(?,?,?) }");
           calla.setString(1,feedback.getName());
           calla.setString(2,feedback.getEmail());
           calla.setString(3,feedback.getContent());
           calla.executeUpdate();
           result = true;
       } catch (SQLException ex) {
           Logger.getLogger(FeedbackModel.class.getName()).log(Level.SEVERE, null, ex);
       }finally{
           ConnectDB.closeConnection(conn, calla);
       }
       return result;
}
   public List<Feedback> getAllFeedback(){
       Connection conn = null;
       CallableStatement calla = null;
       List<Feedback> list = new ArrayList<Feedback>();
       ResultSet rs;
       
       try {
           conn = ConnectDB.openConnect();
           calla = conn.prepareCall("{call getAllFeedback()}");
           rs = calla.executeQuery();
           while(rs.next()){
               Feedback feedback = new Feedback();
               feedback.setFeedbackId(rs.getInt("FeedbackId"));
               feedback.setName(rs.getString("Name"));
               feedback.setEmail(rs.getString("Email"));
               feedback.setContent(rs.getString("Content"));
               list.add(feedback);
           }
       } catch (SQLException ex) {
           Logger.getLogger(FeedbackModel.class.getName()).log(Level.SEVERE, null, ex);
       }finally{
           ConnectDB.closeConnection(conn, calla);
       }
       return list;
   }
   
   public int getTotalFeedback(){
       Connection conn = null;
       CallableStatement calla = null;
       int result =0;
       
       try {
           conn = ConnectDB.openConnect();
           calla = conn.prepareCall("{call totalFeedback(?)}");
           calla.registerOutParameter(1, Types.INTEGER);
           calla.executeUpdate();
           result = calla.getInt(1);
       } catch (SQLException ex) {
           Logger.getLogger(FeedbackModel.class.getName()).log(Level.SEVERE, null, ex);
       }finally{
           ConnectDB.closeConnection(conn, calla);
       }
       return result;
   }
   
   public boolean updateFeedback(int id){
       Connection conn = null;
       CallableStatement calla = null;
       boolean result = false;  
       try {
           conn = ConnectDB.openConnect();
           calla = conn.prepareCall("{call updateFeedback(?)}");
           calla.setInt(1, id);
           calla.executeUpdate();
           result = true;
       } catch (SQLException ex) {
           Logger.getLogger(FeedbackModel.class.getName()).log(Level.SEVERE, null, ex);
       }finally{
           ConnectDB.closeConnection(conn, calla);
       }
       return result;
   }
}
