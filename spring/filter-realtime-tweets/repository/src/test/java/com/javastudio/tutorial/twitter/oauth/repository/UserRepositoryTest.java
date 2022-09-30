package com.javastudio.tutorial.twitter.oauth.repository;

import com.javastudio.tutorial.twitter.oauth.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    void saveUser() {
        User user = new User();
        user.setUserId(1L);

        User savedUser = userRepository.save(user);
        userRepository.flush();

        assertNotNull(savedUser.getId());
    }
}