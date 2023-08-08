package com.shopme.shopmecommon.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "email", length = 128, nullable = false, unique = true)
    private String email;

    @Column(name = "password", length = 64, nullable = false)
    private String password;

    @Column(name = "firstName", length = 45, nullable = false)
    private String firstName;

    @Column(name = "lastName", length = 45, nullable = false)
    private String lastName;

    private String photos;

    private boolean enabled;

    // this relationship is unidirectional from user to role
    // it is not from role to user
    @ManyToMany
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    public User() {
    }

    public User(String email,
                String password,
                String firstName,
                String lastName) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void addRole(Role role) {
        this.roles.add(role);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return enabled == user.enabled &&
                Objects.equals(id, user.id) &&
                Objects.equals(email, user.email) &&
                Objects.equals(password, user.password) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(photos, user.photos) &&
                Objects.equals(roles, user.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, password, firstName, lastName, photos, enabled, roles);
    }
}
