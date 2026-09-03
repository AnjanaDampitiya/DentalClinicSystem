/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Admin
 */
import controller.UserController;
import model.User;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class UserTest {

    // 1. TEST SAVE USER
    @Test
    public void testSaveUser() throws Exception {

        UserController controller = new UserController();

        User user = new User();

        String username = "testuser_" + System.currentTimeMillis();

        user.setUsername(username);
        user.setPassword("1234");
        user.setRole("Staff");

        boolean result = controller.saveUser(user);

        assertTrue(
                "User should be saved successfully",
                result
        );

        // Cleanup
        List<User> users = controller.getAllUsers();

        for (User u : users) {

            if (u.getUsername().equals(username)) {
                controller.deleteUser(u.getUserId());
                break;
            }
        }
    }


    // 2. TEST UPDATE USER
    @Test
    public void testUpdateUser() throws Exception {

        UserController controller = new UserController();

        // First create a test user
        User user = new User();

        String username = "updateuser_" + System.currentTimeMillis();

        user.setUsername(username);
        user.setPassword("1234");
        user.setRole("Staff");

        boolean saved = controller.saveUser(user);

        assertTrue(
                "Test user should be created",
                saved
        );

        // Find created user ID
        List<User> users = controller.getAllUsers();

        int userId = 0;

        for (User u : users) {

            if (u.getUsername().equals(username)) {
                userId = u.getUserId();
                break;
            }
        }

        assertTrue(
                "Created user ID should be found",
                userId > 0
        );

        // Update user
        User updatedUser = new User();

        updatedUser.setUserId(userId);
        updatedUser.setUsername(username + "_updated");
        updatedUser.setPassword("5678");
        updatedUser.setRole("Admin");

        boolean result =
                controller.updateUser(updatedUser);

        assertTrue(
                "User should be updated successfully",
                result
        );

        // Cleanup
        controller.deleteUser(userId);
    }


    // 3. TEST GET ALL USERS
    @Test
    public void testGetAllUsers() throws Exception {

        UserController controller = new UserController();

        List<User> users =
                controller.getAllUsers();

        assertNotNull(
                "User list should not be null",
                users
        );

        assertTrue(
                "User list should contain users",
                users.size() > 0
        );
    }


    // 4. TEST DELETE USER
    @Test
    public void testDeleteUser() throws Exception {

        UserController controller = new UserController();

        // Create temporary user
        User user = new User();

        String username = "deleteuser_" + System.currentTimeMillis();

        user.setUsername(username);
        user.setPassword("1234");
        user.setRole("Staff");

        boolean saved =
                controller.saveUser(user);

        assertTrue(
                "Test user should be created",
                saved
        );

        // Find user ID
        List<User> users =
                controller.getAllUsers();

        int userId = 0;

        for (User u : users) {

            if (u.getUsername().equals(username)) {
                userId = u.getUserId();
                break;
            }
        }

        assertTrue(
                "Created user ID should be found",
                userId > 0
        );

        // Delete user
        boolean deleted =
                controller.deleteUser(userId);

        assertTrue(
                "User should be deleted successfully",
                deleted
        );
    }
}