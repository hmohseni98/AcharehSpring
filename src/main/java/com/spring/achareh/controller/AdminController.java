package com.spring.achareh.controller;

import com.spring.achareh.model.Admin;
import com.spring.achareh.service.AdminService;
import com.spring.achareh.service.dto.admin.AdminRegisterDTO;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/admin")
public class AdminController {

    private final AdminService adminService;
    private final ModelMapper modelMapper;

    public AdminController(AdminService adminService, ModelMapper modelMapper) {
        this.adminService = adminService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    public void register(AdminRegisterDTO adminDTO) {
        Admin admin = modelMapper.map(adminDTO, Admin.class);
        adminService.save(admin);
    }
}
