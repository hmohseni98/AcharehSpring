package com.spring.achareh.controller;

import com.spring.achareh.model.Admin;
import com.spring.achareh.service.AdminService;
import com.spring.achareh.service.dto.admin.AdminRegisterDTO;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("api/admin")
public class AdminController {

    private final AdminService adminService;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    public AdminController(AdminService adminService, ModelMapper modelMapper, BCryptPasswordEncoder passwordEncoder) {
        this.adminService = adminService;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    public void register(@RequestBody AdminRegisterDTO adminDTO) {
        Admin admin = modelMapper.map(adminDTO, Admin.class);
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        adminService.save(admin);
    }
}
