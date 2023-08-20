package com.shopme.admin.user.repository;

import com.shopme.admin.user.repository.UserRepository;
import com.shopme.shopmecommon.entity.Role;
import com.shopme.shopmecommon.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

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
        User user = new User("carrieSmith@gmail.com", "fakepassword", "Carrie", "Smith");

        Role adminRole = testEntityManager.find(Role.class, 1);
        user.addRole(adminRole);

        userRepository.save(user);

        assertThat(userRepository.count()).isGreaterThan(0);
    }

    @Test
    void shouldCreateUserWithTwoRole() {
        User user = new User("suzuki@gmail.com", "fakepassword", "Suzuki", "Mitsuha");

        Role editorRole = new Role(5);
        Role assistantRole = new Role(3); // you cannot add any role id here because there is a FK constraint
        // it will throw DataIntegrityViolationException

        user.addRole(assistantRole);
        user.addRole(editorRole);

        userRepository.save(user);

        assertThat(userRepository.count()).isGreaterThan(0);
    }

    @Test
    void shouldReturnAllUsers() {
        Iterable<User> listUsers = userRepository.findAll();

        assertThat(listUsers.spliterator().estimateSize()).isGreaterThanOrEqualTo(2);
    }

    @Test
    void shouldGetUserById() {
        Optional<User> user = userRepository.findById(1);

        assertThat(user.get()).isNotNull();
    }

    @Test
    void shouldUpdateUserDetails() {
        String newEmail = "carrieSmith12@gmail.com";
        boolean newEnabledStatus = true;

        User user = userRepository.findById(1).get();
        user.updateEmailId(newEmail);
        user.updateEnabled(newEnabledStatus);

        User updatedUser = testEntityManager.find(User.class, 1);

        Assertions.assertAll(
                () -> assertThat(updatedUser.getEmail()).isEqualTo(newEmail),
                () -> assertThat(updatedUser.isEnabled()).isTrue()
        );
    }

    @Test
    void shouldRemoveRolesFromAUser() {
        User user = userRepository.findById(3).get();

        Role editorRole = new Role(5); // but for this you will have to override equals and hashcode with only id

        user.removeRole(editorRole); // this results in removal

        assertThat(user.roles().size()).isLessThan(2);
    }

    @Test
    void shouldDeleteUserByUserId() {
        Integer userId = 3;

        userRepository.deleteById(userId);

        assertThat(userRepository.findById(3)).isEmpty();
    }
}