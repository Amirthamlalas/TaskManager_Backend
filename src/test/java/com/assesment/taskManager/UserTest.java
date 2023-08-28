package com.assesment.taskManager;
import com.assesment.taskManager.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class UserTest {

    private User user;

    @BeforeEach
    void setUp() {
        user = new User(1, "Test User", "test@example.com", "password", LocalDateTime.now());
    }

    @Test
    void testGetId() {
        Assertions.assertEquals(1, user.getId());
    }

    @Test
    void testSetId() {
        user.setId(2);
        Assertions.assertEquals(2, user.getId());
    }

    @Test
    void testGetName() {
        Assertions.assertEquals("Test User", user.getName());
    }

    @Test
    void testSetName() {
        user.setName("New Test User");
        Assertions.assertEquals("New Test User", user.getName());
    }

    @Test
    void testGetEmail() {
        Assertions.assertEquals("test@example.com", user.getEmail());
    }

    @Test
    void testSetEmail() {
        user.setEmail("newtest@example.com");
        Assertions.assertEquals("newtest@example.com", user.getEmail());
    }

    @Test
    void testGetPassword() {
        Assertions.assertEquals("password", user.getPassword());
    }

    @Test
    void testSetPassword() {
        user.setPassword("newpassword");
        Assertions.assertEquals("newpassword", user.getPassword());
    }

    @Test
    void testGetLogout() {
        Assertions.assertNotNull(user.getLogout_time());
    }

    @Test
    void testSetLogout() {
        LocalDateTime logoutTime = LocalDateTime.now().plusHours(1);
        user.setLogout_time(logoutTime);
        Assertions.assertEquals(logoutTime, user.getLogout_time());
    }
}