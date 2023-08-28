package com.assesment.taskManager;


import com.assesment.taskManager.model.User;
import com.assesment.taskManager.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;



    @Test
    public void testLogin() {
        // Given
        User user = new User("test@example.com", "password");
        userRepository.save(user);

        // When
        List<User> result = userRepository.login("test@example.com", "password");

        // Then
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getEmail()).isEqualTo("test@example.com");
        assertThat(result.get(0).getPassword()).isEqualTo("password");
    }
}