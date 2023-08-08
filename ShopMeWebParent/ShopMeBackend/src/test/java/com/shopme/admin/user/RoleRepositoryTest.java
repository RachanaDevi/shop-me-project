package com.shopme.admin.user;

import com.shopme.shopmecommon.entity.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@Rollback(false)  // to insert into the database
class RoleRepositoryTest {


    @Autowired
    private RoleRepository roleRepository;

    @Test
    void shouldCreateRole() {
        Role role = new Role("Admin", "Manage everything");

        roleRepository.save(role);

        assertThat(roleRepository.count()).isGreaterThan(0);
    }
}