package com.spring.achareh.controller;

import com.spring.achareh.model.User;
import com.spring.achareh.service.UserService;
import com.spring.achareh.service.dto.user.UserDTO;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/user")
public class UserController {
    private UserService userService;
    private ModelMapper modelMapper;

    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestParam String email, String password) {
        Boolean result = userService.login(email, password);
        if (result) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/changePassword")
    public ResponseEntity<User> changePassword(Integer userId, String oldPassword, String newPassword) {
        userService.changePassword(userId, oldPassword, newPassword);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/gridSearch")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<UserDTO> gridSearch(Integer userId, String email, String firstName, String lastName) {
        List<UserDTO> userDTOList = new ArrayList<>();
        userService.gridSearch(userId, email, firstName, lastName).
                forEach(user -> userDTOList.add(modelMapper.map(user, UserDTO.class)));
        return userDTOList;
    }
}
