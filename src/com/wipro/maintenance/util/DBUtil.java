package com.wipro.maintenance.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {

    public static Connection getDBConnection() {
        Connection con = null;

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

            con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521/XEPDB1",
                    "system",
                    "admin");   
            con.setAutoCommit(true); 
        } catch (Exception e) {
            e.printStackTrace();
        }

        return con;
    }

}