package com.ekerdev.application.dto;


import com.ekerdev.application.entity.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record UserDataDTO(
    @NotNull @Email String email,
    @NotNull String name
) {
    public User toEntity() {
        return new User(email, name);
    }
}
