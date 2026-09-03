/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    private Connection getConnection() throws Exception {

        return DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/dental_clinic",
            "root",
            System.getenv("DENTAL_DB_PASSWORD")
        );
    }

    // LOGIN
    public User login(String username, String password) throws Exception {

        Connection con = getConnection();

        String sql = "SELECT * FROM users "
                   + "WHERE username = ? AND password = ?";

        PreparedStatement pst = con.prepareStatement(sql);

        pst.setString(1, username);
        pst.setString(2, password);

        ResultSet rs = pst.executeQuery();

        User user = null;

        if (rs.next()) {

            user = new User();

            user.setUserId(rs.getInt("user_id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setRole(rs.getString("role"));
        }

        rs.close();
        pst.close();
        con.close();

        return user;
    }
    public boolean save(User user) throws Exception {

    Connection con = getConnection();

    String sql = "INSERT INTO users (username, password, role) "
               + "VALUES (?, ?, ?)";

    PreparedStatement pst = con.prepareStatement(sql);

    pst.setString(1, user.getUsername());
    pst.setString(2, user.getPassword());
    pst.setString(3, user.getRole());

    int result = pst.executeUpdate();

    pst.close();
    con.close();

    return result > 0;
}
   public List<User> getAllUsers() throws Exception {

    List<User> users = new ArrayList<>();

    Connection con = getConnection();

    String sql = "SELECT * FROM users ORDER BY user_id";

    PreparedStatement pst = con.prepareStatement(sql);

    ResultSet rs = pst.executeQuery();

    while (rs.next()) {

        User user = new User();

        user.setUserId(rs.getInt("user_id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setRole(rs.getString("role"));

        users.add(user);
    }

    rs.close();
    pst.close();
    con.close();

    return users;
} 

    

    // UPDATE USER
    public boolean update(User user) throws Exception {

        Connection con = getConnection();

        String sql = "UPDATE users SET username=?, password=?, role=? "
                   + "WHERE user_id=?";

        PreparedStatement pst = con.prepareStatement(sql);

        pst.setString(1, user.getUsername());
        pst.setString(2, user.getPassword());
        pst.setString(3, user.getRole());
        pst.setInt(4, user.getUserId());

        int result = pst.executeUpdate();

        pst.close();
        con.close();

        return result > 0;
    }

    // DELETE USER
    public boolean delete(int userId) throws Exception {

        Connection con = getConnection();

        String sql = "DELETE FROM users WHERE user_id=?";

        PreparedStatement pst = con.prepareStatement(sql);

        pst.setInt(1, userId);

        int result = pst.executeUpdate();

        pst.close();
        con.close();

        return result > 0;
    }

}