/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Admin
 */

import model.User;
import org.junit.Test;
import static org.junit.Assert.*;
import controller.LoginController;

public class LoginTest {

    // =====================================
    // TEST 1 - VALID LOGIN
    // =====================================

    @Test
    public void testValidLogin() throws Exception {

        LoginController controller =
                new LoginController();

        User user =
                controller.login("admin", "1234");

        assertNotNull(
                "Valid login should return a user",
                user
        );
    }


    // =====================================
    // TEST 2 - INVALID LOGIN
    // =====================================

    @Test
    public void testInvalidLogin() throws Exception {

        LoginController controller =
                new LoginController();

        User user =
                controller.login(
                        "wronguser",
                        "wrongpassword"
                );

        assertNull(
                "Invalid login should return null",
                user
        );
    }


    // =====================================
    // TEST 3 - EMPTY LOGIN
    // =====================================

    @Test
    public void testEmptyLogin() throws Exception {

        LoginController controller =
                new LoginController();

        User user =
                controller.login("", "");

        assertNull(
                "Empty username and password should return null",
                user
        );
    }
}