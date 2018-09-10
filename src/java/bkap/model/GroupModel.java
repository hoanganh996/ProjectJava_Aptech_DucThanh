/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bkap.model;

import bkap.entity.Group;
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
public class GroupModel {

    public List<Group> getAllGroup() {
        Connection conn = null;
        CallableStatement calla = null;
        List<Group> list = new ArrayList<Group>();
        ResultSet rs;

        try {
            conn = ConnectDB.openConnect();
            calla = conn.prepareCall("{call getAllGroup()}");
            rs = calla.executeQuery();
            while (rs.next()) {
                Group group = new Group();
                group.setGroupId(rs.getInt("GroupId"));
                group.setGroupName(rs.getString("GroupName"));
                group.setStatus(rs.getBoolean("Status"));
                list.add(group);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GroupModel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectDB.closeConnection(conn, calla);
        }
        return list;
    }

    public Group getGroupById(int id) {
        Connection conn = null;
        CallableStatement calla = null;
        ResultSet rs;
        Group group = new Group();

        try {
            conn = ConnectDB.openConnect();
            calla = conn.prepareCall("{call getgroupById(?)}");
            calla.setInt(1, id);
            rs = calla.executeQuery();
            while (rs.next()) {
                group.setGroupId(rs.getInt("GroupId"));
                group.setGroupName(rs.getString("groupName"));
                group.setStatus(rs.getBoolean("Status"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(GroupModel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectDB.closeConnection(conn, calla);
        }
        return group;
    }

    public boolean insertGroup(Group group) {
        Connection conn = null;
        CallableStatement calla = null;
        boolean result = false;

        try {
            conn = ConnectDB.openConnect();
            calla = conn.prepareCall("{call insertGroup(?)}");
            calla.setString(1, group.getGroupName());
            calla.executeUpdate();
            result = true;
        } catch (SQLException ex) {
            Logger.getLogger(GroupModel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectDB.closeConnection(conn, calla);
        }
        return result;
    }

    public boolean updateGroup(Group group) {
        Connection conn = null;
        CallableStatement calla = null;
        boolean result = false;

        try {
            conn = ConnectDB.openConnect();
            calla = conn.prepareCall("{call updateGroup(?,?,?)}");
            calla.setInt(1, group.getGroupId());
            calla.setString(2, group.getGroupName());
            calla.setBoolean(3, group.isStatus());
            calla.executeUpdate();
            result = true;
        } catch (SQLException ex) {
            Logger.getLogger(CatalogModel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectDB.closeConnection(conn, calla);
        }
        return result;
    }

    public boolean deleteGroup(int id) {
        Connection conn = null;
        CallableStatement calla = null;
        boolean result = false;
        try {
            conn = ConnectDB.openConnect();
            calla = conn.prepareCall("{ call deleteGroup(?)}");
            calla.setInt(1, id);
            calla.executeUpdate();
            result = true;
        } catch (SQLException ex) {
            Logger.getLogger(CatalogModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public boolean checkGroupName(String name) {
        Connection conn = null;
        CallableStatement calla = null;
        boolean result = false;
        try {
            conn = ConnectDB.openConnect();
            calla = conn.prepareCall("{ call checkGroupName(?,?)}");
            calla.setString(1, name);
            calla.registerOutParameter(2, Types.BOOLEAN);
            calla.executeUpdate();
            result = calla.getBoolean(2);
        } catch (SQLException ex) {
            Logger.getLogger(CatalogModel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectDB.closeConnection(conn, calla);
        }
        return result;
    }

    public boolean checkExistsGroupName(Group group) {
        Connection conn = null;
        CallableStatement calla = null;
        boolean result = false;
        try {
            conn = ConnectDB.openConnect();
            calla = conn.prepareCall("{ call checkExistsGroupName(?,?,?)}");
            calla.setInt(1, group.getGroupId());
            calla.setString(2, group.getGroupName());
            calla.registerOutParameter(3, Types.BOOLEAN);
            calla.executeUpdate();
            result = calla.getBoolean(3);
        } catch (SQLException ex) {
            Logger.getLogger(CatalogModel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectDB.closeConnection(conn, calla);
        }
        return result;
    }
}
