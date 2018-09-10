/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bkap.model;

import bkap.entity.Supplier;
import bkap.util.ConnectDB;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jdk.internal.org.objectweb.asm.Type;

/**
 *
 * @author panth
 */
public class SupplierModel {

    public List<Supplier> getAllSupplier() {
        Connection conn = null;
        CallableStatement calla = null;
        List<Supplier> list = new ArrayList<Supplier>();
        ResultSet rs;

        try {
            conn = ConnectDB.openConnect();
            calla = conn.prepareCall("{call getAllSupplier}");
            rs = calla.executeQuery();
            while (rs.next()) {
                Supplier supplier = new Supplier();
                supplier.setSupplierId(rs.getInt("SupplierId"));
                supplier.setSupplierName(rs.getString("SupplierName"));
                supplier.setSupplierPhone(rs.getString("SupplierPhone"));
                supplier.setSupplierMail(rs.getString("SupplierMail"));
                supplier.setSupplierAddress(rs.getString("SupplierAddress"));
                supplier.setSupplierStatus(rs.getInt("SupplierStatus"));
                list.add(supplier);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SupplierModel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectDB.closeConnection(conn, calla);
        }
        return list;
    }

    public boolean insertSupplier(Supplier supplier) {
        Connection conn = null;
        CallableStatement calla = null;
        boolean result = false;

        try {
            conn = ConnectDB.openConnect();
            calla = conn.prepareCall("{call insertSupplier(?,?,?,?)}");
            calla.setString(1, supplier.getSupplierName());
            calla.setString(2, supplier.getSupplierPhone());
            calla.setString(3, supplier.getSupplierMail());
            calla.setString(4, supplier.getSupplierAddress());
            calla.executeUpdate();
            result = true;
        } catch (SQLException ex) {
            Logger.getLogger(SupplierModel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectDB.closeConnection(conn, calla);
        }
        return result;
    }

    public boolean checkSupplierName(String name) {
        Connection conn = null;
        CallableStatement calla = null;
        boolean result = false;

        try {
            conn = ConnectDB.openConnect();
            calla = conn.prepareCall("{call checkSupplierName(?,?)}");
            calla.setString(1, name);
            calla.registerOutParameter(2, Type.BOOLEAN);
            calla.executeUpdate();
            result = calla.getBoolean(2);
        } catch (SQLException ex) {
            Logger.getLogger(SupplierModel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectDB.closeConnection(conn, calla);
        }
        return result;
    }

    public boolean checkExistsSupplierName(Supplier supplier) {
        Connection conn = null;
        CallableStatement calla = null;
        boolean result = false;

        try {
            conn = ConnectDB.openConnect();
            calla = conn.prepareCall("{call checkExistSupplierName(?,?,?)}");
            calla.setInt(1, supplier.getSupplierId());
            calla.setString(2, supplier.getSupplierName());
            calla.registerOutParameter(3, Type.BOOLEAN);
            calla.executeUpdate();
            result = calla.getBoolean(3);
        } catch (SQLException ex) {
            Logger.getLogger(SupplierModel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectDB.closeConnection(conn, calla);
        }
        return result;
    }

    public Supplier getSupplierById(int id) {
        Connection conn = null;
        CallableStatement calla = null;
        ResultSet rs;
        Supplier supplier = new Supplier();

        try {
            conn = ConnectDB.openConnect();
            calla = conn.prepareCall("{call getSupplierById(?)}");
            calla.setInt(1, id);
            rs = calla.executeQuery();
            while (rs.next()) {
                supplier.setSupplierId(rs.getInt("ProviderId"));
                supplier.setSupplierName(rs.getString("ProviderName"));
                supplier.setSupplierAddress(rs.getString("Address"));
                supplier.setSupplierPhone(rs.getString("Phone"));
                supplier.setSupplierMail(rs.getString("Email"));
                supplier.setStatus(rs.getBoolean("Status"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SupplierModel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectDB.closeConnection(conn, calla);
        }
        return supplier;
    }

    public boolean updateSupplier(Supplier supplier) {
        Connection conn = null;
        CallableStatement calla = null;
        boolean result = false;

        try {
            conn = ConnectDB.openConnect();
            calla = conn.prepareCall("{call updateSupplier(?,?,?,?,?,?)}");
            calla.setInt(1, supplier.getSupplierId());
            calla.setString(2, supplier.getSupplierName());
            calla.setString(3, supplier.getSupplierAddress());
            calla.setString(4, supplier.getSupplierMail());
            calla.setString(5, supplier.getSupplierPhone());
            calla.setBoolean(6, supplier.isStatus());
            calla.executeUpdate();
            result = true;
        } catch (SQLException ex) {
            Logger.getLogger(SupplierModel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectDB.closeConnection(conn, calla);
        }
        return result;
    }

    public boolean deleteSupplier(int id) {
        Connection conn = null;
        CallableStatement calla = null;
        boolean result = false;

        try {
            conn = ConnectDB.openConnect();
            calla = conn.prepareCall("{call deleteSupplier(?)}");
            calla.setInt(1, id);
            calla.executeUpdate();
            result = true;
        } catch (SQLException ex) {
            Logger.getLogger(SupplierModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

}
