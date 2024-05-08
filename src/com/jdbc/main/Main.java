package com.jdbc.main;
import com.jdbc.util.Utility;

import java.sql.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //Resources
        Connection connect = null;
        Statement stmt = null;
        ResultSet result = null;
        try {
            //get the conection
            connect = Utility.getDbConnection();

            if (connect != null)
                stmt = connect.createStatement();

            if (stmt != null)
                result = stmt.executeQuery("select * from student_info");

            if (result != null) {
                System.out.println("Id\tName\tAge\tAddress");
                while (result.next()) {
                    System.out.println(result.getInt("sid") + "\t"
                            + result.getString("sname") + "\t"
                            + result.getInt("sage") + "\t"
                            + result.getString("saddr"));
                }
            }

            int rowsEffected = stmt.executeUpdate("Insert into student_info (sid, sname, sage, saddr) " +
                    "values (3, 'Ram', 23, 'Bangalore') ");
            if (rowsEffected == 1)
                System.out.println("Data inserted successfully");
            else
                System.out.println("Failed to insert data");

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Utility.closeResources(result, stmt, connect);
        }
    }
}