package com.shopme.admin.user;

import com.shopme.shopmecommon.entity.Role;
import com.shopme.shopmecommon.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@Rollback(false)
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    void shouldCreateUserWithOneRole() {
        User user = new User("rachana@gmail.com", "fakepassword", "Rachana", "Devi");

        Role adminRole = testEntityManager.find(Role.class, 1);
        user.addRole(adminRole);

        userRepository.save(user);

        assertThat(userRepository.count()).isGreaterThan(0);
    }

    @Test
    void shouldCreateUserWithTwoRole() {
        User user = new User("charlotte@gmail.com", "fakepassword", "Charlotte", "Smith");

        Role assistantRole = new Role(3);
        Role editorRole = new Role(5);

        user.addRole(assistantRole);
        user.addRole(editorRole);

        userRepository.save(user);

        assertThat(userRepository.count()).isGreaterThan(0);
    }
}