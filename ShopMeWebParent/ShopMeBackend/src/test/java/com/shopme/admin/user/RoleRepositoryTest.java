package com.shopme.admin.user;

import com.shopme.shopmecommon.entity.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@Rollback(false)  // to insert into the database
class RoleRepositoryTest {


    @Autowired
    private RoleRepository roleRepository;

    @Test
    void shouldCreateAllRoles() {
        Role adminRole = new Role("Admin", "Manage everything");
        Role salespersonRole = new Role("Salesperson", "Manage product price");
        Role editorRole = new Role("Editor",
                "Manage categories, brands, prodcuts, articles and menus");
        Role shipperRole = new Role("Shipper", "View products, orders and update order status");
        Role assistantRole = new Role("Assistant", "Manage questions and reviews");

        roleRepository.saveAll(List.of(adminRole, salespersonRole, editorRole, shipperRole, assistantRole));

        assertThat(roleRepository.count()).isGreaterThan(0);
    }
}