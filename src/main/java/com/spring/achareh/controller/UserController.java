package com.spring.achareh.controller;

import com.spring.achareh.model.User;
import com.spring.achareh.model.enumration.Role;
import com.spring.achareh.service.UserService;
import com.spring.achareh.service.dto.user.UserDTO;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@CrossOrigin
@RequestMapping("api/user")
public class UserController {
    private final UserService userService;
    private final ModelMapper modelMapper;

    public static Map<String, User> userMap = new ConcurrentHashMap<>();

    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(String email, String password, HttpServletResponse response) {

        User user = userService.login(email, password);
        if (user != null) {
            String randomValue = UUID.randomUUID().toString();
            userMap.put(randomValue, user);
            Cookie cookie = new Cookie("sec_data", randomValue);
            cookie.setPath("/");
            cookie.setMaxAge(-1);
            response.addCookie(cookie);
            return ResponseEntity.ok().build();
        } else
            return ResponseEntity.notFound().build();
    }

    @PostMapping("/changePassword")
    public ResponseEntity<User> changePassword(Integer userId, String oldPassword, String newPassword) {
        userService.changePassword(userId, oldPassword, newPassword);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/gridSearch")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDTO> gridSearch(Integer id, String firstName, String lastName, String email, String userType) {
        Role role;
        if (userType.equals("متخصص")) {
            role = Role.Expert;
        } else if (userType.equals("مشتری")) {
            role = Role.Customer;
        } else
            role = null;
        List<UserDTO> userDTOList = new ArrayList<>();
        userService.gridSearch(id, email, firstName, lastName, role).
                forEach(user -> userDTOList.add(modelMapper.map(user, UserDTO.class)));
        return userDTOList;
    }
}
