/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.UserDAO;
import model.User;

public class LoginController {

    private UserDAO userDAO;

    public LoginController() {
        userDAO = new UserDAO();
    }

    public User login(String username, String password) throws Exception {

        return userDAO.login(username, password);
    }
}