package com.jdbc.main;

import com.jdbc.util.Utility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main2 {
    public static void main(String[] args) {
        //Resources
        Connection connect = null;
        PreparedStatement stmt = null;
        ResultSet result = null;

        connect = Utility.getDbConnection();
        System.out.println("Connection is fine!");
        try {
        if(connect!=null) {
            String sql = "UPDATE student_info set sname = ? where sid=?";
            stmt = connect.prepareStatement(sql);
            }
        if(stmt!=null){
            stmt.setString(1, "Ram");
            stmt.setInt(2, 1);
            stmt.addBatch();

            stmt.setString(1, "Ram123");
            stmt.setInt(2, 2);
            stmt.addBatch();

            stmt.executeBatch();
            }
        }
        catch (SQLException e) {
                throw new RuntimeException(e);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        finally{
            Utility.closeResources(result, stmt, connect);
        }
    }
}
