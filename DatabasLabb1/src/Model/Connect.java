/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Ahmed Heidari 2
 */
public class Connect {

    private String IP;
    private String port;

    public Connect(String IP, String port) throws SQLException {
        this.IP = IP;
        this.port = port;
        String database = "library";
        String server = "jdbc:mysql://localhost:3306/" + database + "?UseClientEnc=UTF8";
        Connection con = null;
        Statement statement = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String userName = "john";
            con = DriverManager.getConnection(server, "John", "john");
            System.out.println("Connected!");
            statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM t_customer WHERE Username = '" + userName + "'");
//            while (rs.next()) {
//                int persID = rs.getInt("persID");
//                String firstName = rs.getString("First Name");
//                String lastName = rs.getString("Last Name");
//                Date dob = rs.getDate("dob");
//                String city = rs.getString("City");
//                int zipCode = rs.getInt("Zip Code");
//                String street = rs.getString("Street");
//                String email = rs.getString("E-mail");
//                userName = rs.getString("Username");
//                User user = new User();
//                System.out.println(user);
//            }

        } catch (Exception e) {
            System.out.println("Database error, " + e.toString());
        }
        

    }

}
