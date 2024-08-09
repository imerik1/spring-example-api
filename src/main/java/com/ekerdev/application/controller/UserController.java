package com.ekerdev.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ekerdev.application.dto.UserDTO;
import com.ekerdev.application.dto.UserDataDTO;
import com.ekerdev.application.exception.UserNotFoundException;
import com.ekerdev.application.service.UserService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<UserDTO> getAll() {
        return userService.getAllUsers();
    }

    @GetMapping("{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public UserDTO getById(@PathVariable("id") Long id) throws UserNotFoundException {
        return userService.getById(id);
    }

    @GetMapping("email/{email}")
    @ResponseStatus(value = HttpStatus.OK)
    public UserDTO getByEmail(@Valid @Email @PathVariable("email") String email) throws UserNotFoundException {
        return userService.getByEmail(email);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public void create(@Valid @RequestBody UserDataDTO userDTO) {
        userService.create(userDTO);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        userService.delete(id);
    }

    @PutMapping("{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@PathVariable("id") Long id, @Valid @RequestBody UserDataDTO userDTO) {
        userService.update(id, userDTO);
    }
}
