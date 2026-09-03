/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.UserDAO;
import model.User;
import java.sql.SQLException;
import java.util.List;

public class UserController {

    private UserDAO userDAO;

    public UserController() {
        userDAO = new UserDAO();
    }

    // LOGIN
    public User login(String username, String password) throws Exception {
        return userDAO.login(username, password);
    }

    // SAVE
    public boolean saveUser(User user) throws Exception {
        return userDAO.save(user);
    }

    // UPDATE
    public boolean updateUser(User user) throws Exception {
        return userDAO.update(user);
    }

    // DELETE
    public boolean deleteUser(int userId) throws Exception {
        return userDAO.delete(userId);
    }

    // GET ALL USERS
    public List<User> getAllUsers() throws Exception {
        return userDAO.getAllUsers();
    }
}
