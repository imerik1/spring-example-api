package com.ekerdev.application.dto;

import java.util.List;

import com.ekerdev.application.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String email;
    private String name;

    public static User toEntity(UserDTO dto) {
        return new User(dto.getId(), dto.getEmail(), dto.getName());
    }

    public static UserDTO toDTO(User user) {
        return new UserDTO(user.getId(), user.getEmail(), user.getName());
    }

    public static List<UserDTO> toDTOs(List<User> users) {
        return users.stream().map(UserDTO::toDTO).toList();
    }
}