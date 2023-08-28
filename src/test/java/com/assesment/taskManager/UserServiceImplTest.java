package com.assesment.taskManager;

import com.assesment.taskManager.model.User;
import com.assesment.taskManager.repository.UserRepository;
import com.assesment.taskManager.service.UserServiceImpl;
import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;



public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testLoginSuccess() {
        // Given
        String email = "test@example.com";
        String password = "password";

        User user = new User();

        List<User> result = new ArrayList<>();
        result.add(user);

       when(userRepository.login(email, password)).thenReturn(result);

        // When
        HashMap<String, String> map = userService.login(email, password);

        // Then
        assertThat(map).containsEntry("status", "success");
        assertThat(map).containsEntry("userid", String.valueOf(user.getId()));
        assertThat(map).containsEntry("name", user.getName());
        assertThat(map).containsKey("token");
    }

    @Test
    public void testLoginFailure() {
        // Given
        String email = "test@example.com";
        String password = "password";

        when(userRepository.login(email, password)).thenReturn(new ArrayList<>());

        // When
        HashMap<String, String> map = userService.login(email, password);

        // Then
        assertThat(map).containsEntry("status", "failed");
    }

    @Test
    public void testLogout() {
        // Given
        int id = 1;
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("password");
        user.setName("Test User");
        user.setId(id);
        when(userRepository.findById(id)).thenReturn(java.util.Optional.of(user));

        // When
        String result = userService.logout(user);

        // Then
        assertThat(result).isEqualTo("{\"status\":\"success\"}");
        Mockito.verify(userRepository).save(ArgumentMatchers.any(User.class));
    }

    @Test
    public void testGenerateToken() {
        // Given
        String subject = "password";
        String secretKey = "secret";
        long ttlMillis = 3600000L;

        // When
        String token = userService.generateToken(subject, secretKey, ttlMillis);

        // Then
        assertThat(token).isNotNull();
        DecodedJWT decodedJWT = JWT.decode(token);
        assertThat(decodedJWT.getSubject()).isEqualTo(subject);
    }

}
