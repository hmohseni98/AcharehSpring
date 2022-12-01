package com.spring.achareh.controller;

import com.spring.achareh.config.JwtTokenUtil;
import com.spring.achareh.model.User;
import com.spring.achareh.model.enumration.Role;
import com.spring.achareh.service.UserService;
import com.spring.achareh.service.dto.jwt.JwtRequest;
import com.spring.achareh.service.dto.jwt.JwtResponse;
import com.spring.achareh.service.dto.user.UserChangePasswordDTO;
import com.spring.achareh.service.dto.user.UserDTO;
import com.spring.achareh.service.dto.user.UserGridSearchDTO;
import com.spring.achareh.service.impl.JwtUserDetailsServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("api/user")
public class UserController {
    private final UserService userService;
    private final ModelMapper modelMapper;

    private AuthenticationManager authenticationManager;

    private JwtTokenUtil jwtTokenUtil;

    private JwtUserDetailsServiceImpl userDetailsService;

    private final BCryptPasswordEncoder passwordEncoder;


    public UserController(UserService userService, ModelMapper modelMapper, AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil, JwtUserDetailsServiceImpl userDetailsService, BCryptPasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }


    @PostMapping("/changePassword")
    @ResponseStatus(HttpStatus.OK)
    public void changePassword(@RequestBody UserChangePasswordDTO changePasswordDTO) {
        userService.changePassword(changePasswordDTO.getId(), changePasswordDTO.getOldPassword(), changePasswordDTO.getNewPassword());
    }

    @GetMapping("/gridSearch")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Page<UserDTO> gridSearch(@RequestBody UserGridSearchDTO gridSearchDTO) {
        Role role;
        if (gridSearchDTO.getUserType().equals("متخصص")) {
            role = Role.Expert;
        } else if (gridSearchDTO.getUserType().equals("مشتری")) {
            role = Role.Customer;
        } else if (gridSearchDTO.getUserType().equals("مدیر")) {
            role = Role.Admin;
        } else
            role = null;

        Page<User> userPage = userService.gridSearch(gridSearchDTO.getId(), gridSearchDTO.getEmail(), gridSearchDTO.getFirstName(),
                gridSearchDTO.getLastName(), role, gridSearchDTO.getPageNo(), gridSearchDTO.getPageSize(),
                gridSearchDTO.getSortingField(), gridSearchDTO.getSortingDirection());
        Page<UserDTO> userDTOPage = userPage.map(user -> new ModelMapper().map(user, UserDTO.class));
        return userDTOPage;
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
