package com.ekerdev.application.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ekerdev.application.dto.UserDTO;
import com.ekerdev.application.dto.UserDataDTO;
import com.ekerdev.application.entity.User;
import com.ekerdev.application.exception.UserNotFoundException;
import com.ekerdev.application.repository.UserRepository;
import com.ekerdev.application.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();

        return UserDTO.toDTOs(users);
    }

    @Override
    public void create(UserDataDTO userDTO) {
        userRepository.save(userDTO.toEntity());
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void update(Long id, UserDataDTO userDTO) {
        User user = userDTO.toEntity();
        user.setId(id);

        userRepository.save(user);
    }

    @Override
    public UserDTO getById(Long id) throws UserNotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);

        return UserDTO.toDTO(user);
    }

    @Override
    public UserDTO getByEmail(String email) throws UserNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);

        return UserDTO.toDTO(user);
    }
}
