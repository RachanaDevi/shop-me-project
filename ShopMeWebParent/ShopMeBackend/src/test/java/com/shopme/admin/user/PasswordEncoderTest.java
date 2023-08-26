package com.shopme.admin.user;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;

public class PasswordEncoderTest {

    @Test
    void shouldEncodePassword() {
        String rawPassword = "password";

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(rawPassword);

        assertThat(passwordEncoder.matches(rawPassword, encodedPassword)).isTrue();
    }
}
