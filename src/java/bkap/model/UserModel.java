/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bkap.model;

import bkap.entity.User;
import bkap.util.ConnectDB;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author panth
 */
public class UserModel {

    SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

    public List<User> getAllUser() {
        Connection conn = null;
        CallableStatement calla = null;
        List<User> list = new ArrayList<User>();
        ResultSet rs;

        try {
            conn = ConnectDB.openConnect();
            calla = conn.prepareCall("{call getAllUser()}");
            rs = calla.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setUserId(rs.getInt("UserId"));
                user.setUserName(rs.getString("UserName"));
                user.setPassword(rs.getString("Password"));
                user.setAddress(rs.getString("Address"));
                user.setPhone(rs.getString("Phone"));
                user.setMail(rs.getString("Mail"));
                user.setGroupId(rs.getInt("GroupId"));
                user.setStatus(rs.getBoolean("Status"));
                if (rs.getDate("Created") == null) {
                    user.setCreated(null);
                } else {
                    user.setCreated(format.format(rs.getDate("Created")));
                }
                list.add(user);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public boolean checkLogin(User user) {
        boolean result = false;
        Connection conn = null;
        CallableStatement calla = null;
        ResultSet rs;

        try {
            conn = ConnectDB.openConnect();
            calla = conn.prepareCall("{call checkLogin(?,?)}");
            calla.setString(1, user.getUserName());
            calla.setString(2, user.getPassword());
            rs = calla.executeQuery();
            while (rs.next()) {
                result = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserModel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectDB.closeConnection(conn, calla);
        }
        return result;
    }

    public boolean insertUser(User user) {
        Connection conn = null;
        CallableStatement calla = null;
        boolean result = false;

        try {
            conn = ConnectDB.openConnect();
            calla = conn.prepareCall("{call insertUser(?,?,?,?,?,?)}");
            calla.setString(1, user.getUserName());
            calla.setString(2, user.getPassword());
            calla.setString(3, user.getPhone());
            calla.setString(4, user.getMail());
            calla.setString(5, user.getAddress());
            calla.setInt(6, user.getGroupId());
            calla.executeUpdate();
            result = true;
        } catch (SQLException ex) {
            Logger.getLogger(UserModel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectDB.closeConnection(conn, calla);
        }
        return result;
    }

    public boolean insertAccount(User user) {
        Connection conn = null;
        CallableStatement calla = null;
        boolean result = false;

        try {
            conn = ConnectDB.openConnect();
            calla = conn.prepareCall("{call insertAccount(?,?,?,?,?)}");
            calla.setString(1, user.getUserName());
            calla.setString(2, user.getPassword());
            calla.setString(3, user.getPhone());
            calla.setString(4, user.getMail());
            calla.setString(5, user.getAddress());
            calla.executeUpdate();
            result = true;
        } catch (SQLException ex) {
            Logger.getLogger(UserModel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectDB.closeConnection(conn, calla);
        }
        return result;
    }

    public boolean activeAccount(int id) {
        Connection conn = null;
        CallableStatement calla = null;
        boolean result = false;

        try {
            conn = ConnectDB.openConnect();
            calla = conn.prepareCall("{call activeAccount(?)}");
            calla.setInt(1, id);
            calla.executeUpdate();
            result = true;
        } catch (SQLException ex) {
            Logger.getLogger(UserModel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectDB.closeConnection(conn, calla);
        }
        return result;
    }

    public boolean checkUserName(String name) {
        Connection conn = null;
        CallableStatement calla = null;
        boolean result = false;

        try {
            conn = ConnectDB.openConnect();
            calla = conn.prepareCall("{call checkUserName(?,?)}");
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

    public boolean checkExistUserName(User user) {
        Connection conn = null;
        CallableStatement calla = null;
        boolean result = false;

        try {
            conn = ConnectDB.openConnect();
            calla = conn.prepareCall("{call checkExistUserName(?,?,?)}");
            calla.setInt(1, user.getUserId());
            calla.setString(2, user.getUserName());
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

    public boolean checkUserEmail(String mail) {
        Connection conn = null;
        CallableStatement calla = null;
        boolean result = false;

        try {
            conn = ConnectDB.openConnect();
            calla = conn.prepareCall("{ call checkUserEmail(?,?)}");
            calla.setString(1, mail);
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

    public boolean checkExistsUserEmail(User user) {
        Connection conn = null;
        CallableStatement calla = null;
        boolean result = false;
        try {
            conn = ConnectDB.openConnect();
            calla = conn.prepareCall("{ call checkExistsUserEmail(?,?,?)}");
            calla.setInt(1, user.getUserId());
            calla.setString(2, user.getMail());
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

    public User getUserById(int id) {
        Connection conn = null;
        CallableStatement calla = null;
        ResultSet rs;
        User user = new User();

        try {
            conn = ConnectDB.openConnect();
            calla = conn.prepareCall("{call getUserById(?)}");
            calla.setInt(1, id);
            rs = calla.executeQuery();
            while (rs.next()) {
                user.setUserId(rs.getInt("UserId"));
                user.setUserName(rs.getString("UserName"));
                user.setPassword(rs.getString("Password"));
                user.setAddress(rs.getString("Address"));
                user.setPhone(rs.getString("Phone"));
                user.setMail(rs.getString("Mail"));
                user.setGroupId(rs.getInt("GroupId"));
                if (rs.getDate("Created") == null) {
                    user.setCreated(null);
                } else {
                    user.setCreated(format.format(rs.getDate("Created")));
                }
                user.setStatus(rs.getBoolean("Status"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PackageModel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectDB.closeConnection(conn, calla);
        }
        return user;
    }

    public boolean updateUser(User user) {
        Connection conn = null;
        CallableStatement calla = null;
        boolean result = false;

        try {
            conn = ConnectDB.openConnect();
            calla = conn.prepareCall("{call updateUser(?,?,?,?,?,?,?,?)}");
            calla.setInt(1, user.getUserId());
            calla.setString(2, user.getUserName());
            calla.setString(3, user.getPassword());
            calla.setString(4, user.getPhone());
            calla.setString(5, user.getMail());
            calla.setString(6, user.getAddress());
            calla.setInt(7, user.getGroupId());
            calla.setBoolean(8, user.isStatus());
            calla.executeUpdate();
            result = true;
        } catch (SQLException ex) {
            Logger.getLogger(CatalogModel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectDB.closeConnection(conn, calla);
        }
        return result;
    }

    public boolean updateAccount(User user) {
        Connection conn = null;
        CallableStatement calla = null;
        boolean result = false;

        try {
            conn = ConnectDB.openConnect();
            calla = conn.prepareCall("{call updateAccount(?,?,?,?,?,?)}");
            calla.setInt(1, user.getUserId());
            calla.setString(2, user.getUserName());
            calla.setString(3, user.getPassword());
            calla.setString(4, user.getPhone());
            calla.setString(5, user.getMail());
            calla.setString(6, user.getAddress());

            calla.executeUpdate();
            result = true;
        } catch (SQLException ex) {
            Logger.getLogger(CatalogModel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectDB.closeConnection(conn, calla);
        }
        return result;
    }

    public boolean deleteUser(int id) {
        Connection conn = null;
        CallableStatement calla = null;
        boolean result = false;
        try {
            conn = ConnectDB.openConnect();
            calla = conn.prepareCall("{ call deleteUser(?)}");
            calla.setInt(1, id);
            calla.executeUpdate();
            result = true;
        } catch (SQLException ex) {
            Logger.getLogger(CatalogModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public User getUserByUserName(String name) {
        Connection conn = null;
        CallableStatement calla = null;
        ResultSet rs;
        User user = new User();
        try {
            conn = ConnectDB.openConnect();
            calla = conn.prepareCall("{call  getUserByName(?)}");
            calla.setString(1, name);
            rs = calla.executeQuery();
            while (rs.next()) {
                user.setUserId(rs.getInt("UserId"));
                user.setUserName(rs.getString("UserName"));
                user.setPassword(rs.getString("Password"));
                user.setAddress(rs.getString("Address"));
                user.setPhone(rs.getString("Phone"));
                user.setMail(rs.getString("Mail"));
                user.setGroupId(rs.getInt("GroupId"));
                if (rs.getDate("Created") == null) {
                    user.setCreated(null);
                } else {
                    user.setCreated(format.format(rs.getDate("Created")));
                }
                user.setStatus(rs.getBoolean("Status"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PackageModel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectDB.closeConnection(conn, calla);
        }
        return user;
    }

    public int getTotalUser() {
        Connection conn = null;
        CallableStatement calla = null;
        int result = 0;
        try {
            conn = ConnectDB.openConnect();
            calla = conn.prepareCall("{ call getTotalUser(?)}");
            calla.registerOutParameter(1, Types.INTEGER);
            calla.executeUpdate();
            result = calla.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(UserModel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectDB.closeConnection(conn, calla);
        }
        return result;
    }

    public User getUserByEmail(String email) {
        Connection conn = null;
        CallableStatement calla = null;
        ResultSet rs;
        User user = new User();
        try {
            conn = ConnectDB.openConnect();
            calla = conn.prepareCall("{call  getUserByEmail(?)}");
            calla.setString(1, email);
            rs = calla.executeQuery();
            while (rs.next()) {
                user.setUserId(rs.getInt("UserId"));
                user.setUserName(rs.getString("UserName"));
                user.setPassword(rs.getString("Password"));
                user.setAddress(rs.getString("Address"));
                user.setPhone(rs.getString("Phone"));
                user.setMail(rs.getString("Email"));
                user.setGroupId(rs.getInt("GroupId"));

                if (rs.getDate("Created") == null) {
                    user.setCreated(null);
                } else {
                    user.setCreated(format.format(rs.getDate("Created")));
                }
                user.setStatus(rs.getBoolean("Status"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PackageModel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectDB.closeConnection(conn, calla);
        }
        return user;
    }

    public boolean recoverPassword(User user) {
        Connection conn = null;
        CallableStatement calla = null;
        boolean result = false;
        try {
            conn = ConnectDB.openConnect();
            calla = conn.prepareCall("{ call recoverPassword(?,?)}");
            calla.setInt(1, user.getUserId());
            calla.setString(2, user.getMail());
            calla.executeUpdate();
            result = true;
        } catch (SQLException ex) {
            Logger.getLogger(UserModel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectDB.closeConnection(conn, calla);
        }
        return result;
    }

    public int getUserIdNew() {
        Connection conn = null;
        CallableStatement calla = null;
        int result = 0;

        try {
            conn = ConnectDB.openConnect();
            calla = conn.prepareCall("{call getUserIdNew(?)}");
            calla.registerOutParameter(1, Types.INTEGER);
            calla.executeUpdate();
            result = calla.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(UserModel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectDB.closeConnection(conn, calla);
        }
        return result;
    }
}
