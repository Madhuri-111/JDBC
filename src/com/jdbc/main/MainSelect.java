package com.jdbc.main;

import com.jdbc.util.Utility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class MainSelect {
    public static void main(String[] args) {
        //Resources
        Connection connect = null;
        PreparedStatement stmt = null;
        ResultSet result = null;
        Scanner sc= null;

        connect = Utility.getDbConnection();
        System.out.println("Connection is fine!");

        String sql = "select sid,sname from student_info where sid=?";
        try {
            if(connect!=null) {
                stmt = connect.prepareStatement(sql);
            }

            if(stmt!=null){
                System.out.println("Please enter id");
                sc = new Scanner(System.in);
                int id = sc.nextInt();
                stmt.setInt(1, id);
                result =stmt.executeQuery();
                System.out.println("ID\tNAME");
                while(result.next()){
                    System.out.println(result.getInt(1)+"\t" + result.getString(2));
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {
            Utility.closeResources(result, stmt, connect);
            sc.close();
        }
    }
}
