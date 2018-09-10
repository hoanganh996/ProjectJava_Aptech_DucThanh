/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bkap.model;

import bkap.entity.Function;
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

public class FunctionModel {

    public List<Function> getAllFunction() {
        Connection conn = null;
        CallableStatement calla = null;
        List<Function> list = new ArrayList<Function>();
        ResultSet rs;
        try {
            conn = ConnectDB.openConnect();
            calla = conn.prepareCall("{call getAllFunction()}");
            rs = calla.executeQuery();
            while (rs.next()) {
                Function function = new Function();
                function.setFunctionId(rs.getInt("FunctionId"));
                function.setFunctionName(rs.getString("FunctionName"));
                function.setStatus(rs.getBoolean("Status"));
                list.add(function);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FunctionModel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectDB.closeConnection(conn, calla);
        }
        return list;
    }

    public Function getFunctionById(int id) {
        Connection conn = null;
        CallableStatement calla = null;
        ResultSet rs;
        Function function = new Function();

        try {
            conn = ConnectDB.openConnect();
            calla = conn.prepareCall("{call getFunctionById(?)}");
            calla.setInt(1, id);
            rs = calla.executeQuery();
            while (rs.next()) {
                function.setFunctionId(rs.getInt("FunctionId"));
                function.setFunctionName(rs.getString("FunctionName"));
                function.setStatus(rs.getBoolean("Status"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(FunctionModel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectDB.closeConnection(conn, calla);
        }
        return function;
    }

    public boolean insertFunction(Function function) {
        Connection conn = null;
        CallableStatement calla = null;
        boolean result = false;

        try {
            conn = ConnectDB.openConnect();
            calla = conn.prepareCall("{call insertFunction(?)}");
            calla.setString(1, function.getFunctionName());
            calla.executeUpdate();
            result = true;
        } catch (SQLException ex) {
            Logger.getLogger(FunctionModel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectDB.closeConnection(conn, calla);
        }
        return result;
    }

    public boolean updateFunction(Function function) {
        Connection conn = null;
        CallableStatement calla = null;
        boolean result = false;

        try {
            conn = ConnectDB.openConnect();
            calla = conn.prepareCall("{call updateFunction(?,?,?)}");
            calla.setInt(1, function.getFunctionId());
            calla.setString(2,function.getFunctionName());
            calla.setBoolean(3,function.isStatus());
            calla.executeUpdate();
            result = true;
        } catch (SQLException ex) {
            Logger.getLogger(FunctionModel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectDB.closeConnection(conn, calla);
        }
        return result;
    }

    public boolean deleteFunction(int id) {
        Connection conn = null;
        CallableStatement calla = null;
        boolean result = false;

        try {
            conn = ConnectDB.openConnect();
            calla = conn.prepareCall("{call deleteFunction(?)}");
            calla.setInt(1, id);
            calla.executeUpdate();
            result = true;
        } catch (SQLException ex) {
            Logger.getLogger(FunctionModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public boolean checkFunctionName(String name) {
        Connection conn = null;
        CallableStatement calla = null;
        boolean result = false;

        try {
            conn = ConnectDB.openConnect();
            calla = conn.prepareCall("{call checkFunctionName(?,?)}");
            calla.setString(1, name);
            calla.registerOutParameter(2, Types.BOOLEAN);
            calla.executeUpdate();
            result = calla.getBoolean(2);
        } catch (SQLException ex) {
            Logger.getLogger(FunctionModel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectDB.closeConnection(conn, calla);
        }
        return result;
    }

    public boolean checkExistFunctionName(Function function) {
        Connection conn = null;
        CallableStatement calla = null;
        boolean result = false;

        try {
            conn = ConnectDB.openConnect();
            calla = conn.prepareCall("{call checkExistFunctionName(?,?,?)}"); 
            calla.setInt(1, function.getFunctionId());
            calla.setString(2, function.getFunctionName());
            calla.registerOutParameter(3, Types.BOOLEAN);
            calla.executeUpdate();
            result = calla.getBoolean(3);
        } catch (SQLException ex) {
            Logger.getLogger(FunctionModel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectDB.closeConnection(conn, calla);
        }
        return result;
    }
}
