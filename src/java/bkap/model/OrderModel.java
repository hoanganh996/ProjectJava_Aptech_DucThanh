/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bkap.model;

import bkap.entity.Order;
import bkap.entity.OrderDetail;
import bkap.util.Common;
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

public class OrderModel {

    SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

    public List<Order> getAllOrder() {
        Connection conn = null;
        CallableStatement calla = null;
        List<Order> list = new ArrayList<Order>();
        try {
            conn = ConnectDB.openConnect();
            calla = conn.prepareCall("{call getAllOrder()}");
            ResultSet rs = calla.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                order.setOrderId(rs.getInt("OrderId"));
                order.setOrderName(rs.getString("OrderName"));
                order.setOrderNumber(rs.getString("OrderNumber"));
                order.setPhone(rs.getString("Phone"));
                order.setEmail(rs.getString("Email"));
                order.setAddress(rs.getString("Address"));
                order.setPaymentMethod(rs.getString("PaymentMethod"));
                order.setTotalAmount(Common.formatNumber(rs.getFloat("TotalAmount")));
                if (rs.getDate("PaymentDate") != null) {
                    order.setPaymentDate(format.format(rs.getDate("PaymentDate")));
                }
                if (rs.getDate("CreatedDate") != null) {
                    order.setCreated(format.format(rs.getDate("Created")));
                }
                order.setStatus(rs.getInt("OrderStatus"));
                list.add(order);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderModel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectDB.closeConnection(conn, calla);
        }
        return list;
    }

    public List<Order> getOrderByUserId(int id) {
        Connection conn = null;
        CallableStatement calla = null;
        List<Order> list = new ArrayList<Order>();
        try {
            conn = ConnectDB.openConnect();
            calla = conn.prepareCall("{call getOrderByUserId(?)}");
            calla.setInt(1, id);
            ResultSet rs = calla.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                order.setOrderId(rs.getInt("OrderId"));
                order.setOrderName(rs.getString("OrderName"));
                order.setOrderNumber(rs.getString("OrderNumber"));
                order.setPaymentMethod(rs.getString("PaymentMethod"));
                order.setTotalAmount(Common.formatNumber(rs.getFloat("TotalAmount")));
                if (rs.getDate("PaymentDate") != null) {
                    order.setPaymentDate(format.format(rs.getDate("PaymentDate")));
                }
                if (rs.getDate("CreatedDate") != null) {
                    order.setCreated(format.format(rs.getDate("Created")));
                }

                order.setStatus(rs.getInt("Status"));
                list.add(order);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderModel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectDB.closeConnection(conn, calla);
        }
        return list;
    }

    public List<Order> getOrderNew() {
        Connection conn = null;
        CallableStatement calla = null;
        List<Order> list = new ArrayList<Order>();
        try {
            conn = ConnectDB.openConnect();
            calla = conn.prepareCall("{call getOrderNew()}");
            ResultSet rs = calla.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                order.setOrderId(rs.getInt("OrderId"));
                order.setOrderName(rs.getString("OrderName"));
                order.setOrderNumber(rs.getString("OrderNumber"));
                order.setPhone(rs.getString("Phone"));
                order.setEmail(rs.getString("Email"));
                order.setAddress(rs.getString("Address"));
                order.setTotalAmount(Common.formatNumber(rs.getFloat("TotalAmount")));
                order.setPaymentMethod(rs.getString("PaymentMethod"));
                if (rs.getDate("PaymentDate") != null) {
                    order.setPaymentDate(format.format(rs.getDate("PaymentDate")));
                }
                if (rs.getDate("CreatedDate") != null) {
                    order.setCreated(format.format(rs.getDate("Created")));
                }
                order.setStatus(rs.getInt("Status"));
                list.add(order);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderModel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectDB.closeConnection(conn, calla);
        }
        return list;
    }

    public int getTotalOrder() {
        Connection conn = null;
        CallableStatement calla = null;
        int result = 0;
        try {
            conn = ConnectDB.openConnect();
            calla = conn.prepareCall("{ call getTotalOrder(?)}");
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

    public List<OrderDetail> getOrderDetailById(int id) {
        Connection conn = null;
        CallableStatement calla = null;
        List<OrderDetail> list = new ArrayList<OrderDetail>();
        ResultSet rs;
        try {
            conn = ConnectDB.openConnect();
            calla = conn.prepareCall("{ call getOrderDetailById(?)}");
            calla.setInt(1, id);
            rs = calla.executeQuery();
            while (rs.next()) {
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setOrderId(id);
                orderDetail.setProductId(rs.getInt("ProductId"));
                orderDetail.setProductName(rs.getString("ProductName"));
                orderDetail.setQuantity(rs.getInt("Quantity"));
                orderDetail.setPrice(Common.formatNumber(rs.getFloat("Price")));
                orderDetail.setAmount(Common.formatNumber(rs.getFloat("Amount")));
                orderDetail.setStatus(rs.getBoolean("Status"));
                list.add(orderDetail);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderModel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectDB.closeConnection(conn, calla);
        }
        return list;

    }

    public boolean updateOrder(int id) {
        Connection conn = null;
        CallableStatement calla = null;
        boolean result = false;
        try {
            conn = ConnectDB.openConnect();
            calla = conn.prepareCall("{ call updateOrder(?)}");
            calla.setInt(1, id);
            calla.executeUpdate();
            result = true;
        } catch (SQLException ex) {
            Logger.getLogger(OrderModel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectDB.closeConnection(conn, calla);
        }
        return result;
    }

    public boolean insertOrder(Order order) {
        Connection conn = null;
        CallableStatement calla = null;
        boolean result = false;
        try {
            conn = ConnectDB.openConnect();
            calla = conn.prepareCall("{ call insertOrder(?,?,?,?,?,?,?)}");
            calla.setString(1, order.getOrderName());
            calla.setString(2, order.getOrderNumber());
            calla.setString(3, order.getPhone());
            calla.setString(4, order.getEmail());
            calla.setString(5, order.getAddress());
            calla.setFloat(6, Common.convertToFloat(order.getTotalAmount()));
            calla.setInt(7, order.getUserId());
            calla.executeUpdate();
            result = true;
        } catch (SQLException ex) {
            Logger.getLogger(OrderModel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectDB.closeConnection(conn, calla);
        }
        return result;
    }

    public boolean insertOrderDetail(OrderDetail orderDetail) {
        Connection conn = null;
        CallableStatement calla = null;
        boolean result = false;
        try {
            conn = ConnectDB.openConnect();
            calla = conn.prepareCall("{ call insertOrderDetail(?,?,?,?,?,?)}");
            calla.setInt(1, orderDetail.getOrderId());
            calla.setInt(2, orderDetail.getProductId());
            calla.setFloat(3, Common.convertToFloat(orderDetail.getAmount()));
            calla.setFloat(4, Common.convertToFloat(orderDetail.getPrice()));
            calla.setInt(5, orderDetail.getQuantity());
            calla.executeUpdate();
            result = true;
        } catch (SQLException ex) {
            Logger.getLogger(OrderModel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectDB.closeConnection(conn, calla);
        }
        return result;
    }

    public int getOrderIdNew() {
        Connection conn = null;
        CallableStatement calla = null;
        int result = 0;

        try {
            conn = ConnectDB.openConnect();
            calla = conn.prepareCall("{call getOrderIdNew(?)}");
            calla.registerOutParameter(1, Types.INTEGER);
            calla.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(OrderModel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectDB.closeConnection(conn, calla);
        }
        return result;
    }

    public float getTotalMoney() {
        Connection conn = null;
        CallableStatement calla = null;
        float result = 0;
        try {
            conn = ConnectDB.openConnect();
            calla = conn.prepareCall("{ call getTotalMoney(?)}");
            calla.registerOutParameter(1, Types.FLOAT);
            calla.executeUpdate();
            result = calla.getFloat(1);
        } catch (SQLException ex) {
            Logger.getLogger(OrderModel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectDB.closeConnection(conn, calla);
        }
        return result;

    }

    public boolean updatePaymentMethod(int id, String method) {
        Connection conn = null;
        CallableStatement calla = null;
        boolean result = false;
        try {
            conn = ConnectDB.openConnect();
            calla = conn.prepareCall("{ call updatePaymentMethod(?,?)}");
            calla.setInt(1, id);
            calla.setString(2, method);
            calla.executeUpdate();
            result = true;
        } catch (SQLException ex) {
            Logger.getLogger(OrderModel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectDB.closeConnection(conn, calla);
        }
        return result;
    }

    public int getOrderIdNewByUserId(int id) {
        Connection conn = null;
        CallableStatement calla = null;
        int result = 0;
        try {
            conn = ConnectDB.openConnect();
            calla = conn.prepareCall("{ call getOrderIdNewByUserId(?,?)}");
            calla.setInt(1, id);
            calla.registerOutParameter(2, Types.INTEGER);
            calla.executeUpdate();
            result = calla.getInt(2);
        } catch (SQLException ex) {
            Logger.getLogger(UserModel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectDB.closeConnection(conn, calla);
        }
        return result;

    }

    public boolean confirmOrder(int id) {
        Connection conn = null;
        CallableStatement calla = null;
        boolean result = false;
        try {
            conn = ConnectDB.openConnect();
            calla = conn.prepareCall("{ call confirmOrder(?)}");
            calla.setInt(1, id);
            calla.executeUpdate();
            result = true;
        } catch (SQLException ex) {
            Logger.getLogger(OrderModel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectDB.closeConnection(conn, calla);
        }
        return result;
    }

}
