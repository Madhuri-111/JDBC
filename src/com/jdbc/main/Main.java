package com.jdbc.main;
import com.jdbc.util.Utility;

import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //Resources
        Connection connect = null;
        PreparedStatement stmt = null;
        ResultSet result = null;
        Scanner sc= null;

        try {
            //get the conection
            connect = Utility.getDbConnection();
            System.out.println("Connection is fine!");

            if (connect != null) {
                String sql="INSERT INTO student_info (sid, sname, sage, saddr) VALUES (?,?,?,?)";
                stmt = connect.prepareStatement(sql);
            }

            if (stmt != null) {
                sc = new Scanner(System.in);
                System.out.println("Please enter following details to be stored in DB:");
                System.out.println("Enter your id");
                Integer id = sc.nextInt();

                System.out.println("Enter your name");
                String name = sc.next();

                System.out.println("Enter your age");
                Integer age = sc.nextInt();

                System.out.println("Enter your addr");
                String addr = sc.next();

                stmt.setInt(1, id);
                stmt.setString(2, name);
                stmt.setInt(3, age);
                stmt.setString(4, addr);

                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected == 1) {
                    System.out.println("Operation success.");
                } else {
                    System.out.println("operation failed.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Utility.closeResources(result, stmt, connect);
            sc.close();
        }
    }
}