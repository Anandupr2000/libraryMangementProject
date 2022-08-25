/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;
import java.sql.*;

/**
 *
 * @author sreen
 */
public class ConnectionProvider {

    /**
     *
     * @return
     */
    public static Connection getCon()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            //Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/lib","lms","123456");  
            Connection con=DriverManager.getConnection("jdbc:mysql://freedb.tech:3306/freedbtech_hgfyhvfghjbcghbfgbgfykjnmbhnnhjnbhgyghbvhghbhghn","freedbtech_gbvghbvghbghbgh","123456");  
//            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/lib?zeroDateTimeBehavior=convertToNull","lms","123456");
            System.out.println("Connection Sucess");
            return con;
        }
        catch (ClassNotFoundException | SQLException e)
                {
                   System.out.println("Connection failed");
                   System.out.println(e);
                   return null;
                }
    }
    public static void main(String[] args)
    {
        /*Connection a =*/ ConnectionProvider.getCon();
        //Connection con=ConnectionProvider.getCon();
    }
}
