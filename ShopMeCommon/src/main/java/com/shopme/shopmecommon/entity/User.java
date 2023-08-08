package com.shopme.shopmecommon.entity;

import jakarta.persistence.*;

import java.util.HashSet;
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

    public void updateEmailId(String email) {
        this.email = email;
    }

    public void updateEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean enabled() {
        return enabled;
    }

    public String emailId() {
        return email;
    }

    public void removeRole(Role role) {
        this.roles.remove(role);
    }

    public Set<Role> roles() {
        return roles;
    }
}
