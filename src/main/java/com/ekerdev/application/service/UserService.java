package com.ekerdev.application.service;

import java.util.List;

import com.ekerdev.application.dto.UserDTO;
import com.ekerdev.application.dto.UserDataDTO;
import com.ekerdev.application.exception.UserNotFoundException;

public interface UserService {
    public List<UserDTO> getAllUsers();

    public UserDTO getById(Long id) throws UserNotFoundException;

    public UserDTO getByEmail(String email) throws UserNotFoundException;

    public void create(UserDataDTO userDTO);

    public void delete(Long id);

    public void update(Long id, UserDataDTO userDTO);
}
