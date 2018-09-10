/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bkap.util;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectDB {

    private static final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=PhoneStoreDB";
    private static final String USER = "sa";
    private static final String PASS = "123";

    /**
     * hàm kết nối CSDL
     *
     * @return chuỗi kết nối
     */
    public static Connection openConnect() {
        Connection conn = null;
        try {
            try {
                Class.forName(DRIVER);
                conn = DriverManager.getConnection(URL, USER, PASS);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }

    /**
     * Hàm đòng kết nối
     *
     * @param conn chuỗi kết nối
     * @param call callableStatement gọi procduce trong sql
     */
    public static void closeConnection(Connection conn, CallableStatement call) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (call != null) {
                try {
                    call.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
