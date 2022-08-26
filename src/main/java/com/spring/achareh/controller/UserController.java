package com.spring.achareh.controller;

import com.spring.achareh.exceptionHandler.customException.UsernameOrPasswordInCorrectException;
import com.spring.achareh.model.User;
import com.spring.achareh.model.enumration.Role;
import com.spring.achareh.service.UserService;
import com.spring.achareh.service.dto.user.UserDTO;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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
import java.util.function.Function;

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
    @ResponseStatus(HttpStatus.OK)
    public void login(String email, String password, HttpServletResponse response) {

        User user = userService.login(email, password);
        if (user == null)
            throw new UsernameOrPasswordInCorrectException();
        String randomValue = UUID.randomUUID().toString();
        userMap.put(randomValue, user);
        Cookie cookie = new Cookie("sec_data", randomValue);
        cookie.setPath("/");
        cookie.setMaxAge(-1);
        response.addCookie(cookie);
    }

    @PostMapping("/changePassword")
    public ResponseEntity<User> changePassword(Integer userId, String oldPassword, String newPassword) {
        userService.changePassword(userId, oldPassword, newPassword);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/gridSearch")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Page<UserDTO> gridSearch(@RequestParam(required = false) Integer id, @RequestParam String firstName, @RequestParam String lastName,
                                    @RequestParam String email, @RequestParam String userType, @RequestParam Integer pageNo,
                                    @RequestParam Integer pageSize, @RequestParam String sortingField,
                                    @RequestParam String sortingDirection) {
        Role role;
        if (userType.equals("متخصص")) {
            role = Role.Expert;
        } else if (userType.equals("مشتری")) {
            role = Role.Customer;
        } else if (userType.equals("مدیر")) {
            role = Role.Admin;
        } else
            role = null;

        Page<User> userPage = userService.gridSearch(id, email, firstName, lastName, role, pageNo, pageSize,
                sortingField, sortingDirection);
        Page<UserDTO> userDTOPage = userPage.map(new Function<User, UserDTO>() {
            @Override
            public UserDTO apply(User user) {
                return new ModelMapper().map(user,UserDTO.class);
            }
        });
        return userDTOPage;
    }
}
