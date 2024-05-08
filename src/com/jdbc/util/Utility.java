package com.jdbc.util;

import java.sql.*;

public class Utility {
    //as static block gets executed, during class loading itself.
    //for the driver to get loaded and registered the moment this particular class is
    //getting loaded in the JVM.

    static{
        //load and register Driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Driver registered successfully.");
    }

    public static Connection getDbConnection() throws SQLException {
        //establish connection
        String url="jdbc:mysql://localhost:3306/jdbc_student";
        String username ="root";
        String password="root";
        return DriverManager.getConnection(url, username, password);
    }

    public static void closeResources(ResultSet rs, Statement st, Connection c) {
        try {
            if (rs != null)
                rs.close();
            if (st != null)
                st.close();
            if (c != null)
                c.close();
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
}
