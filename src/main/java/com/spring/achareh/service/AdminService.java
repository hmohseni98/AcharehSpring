package com.spring.achareh.service;

import com.spring.achareh.model.Admin;
import com.spring.achareh.service.base.BaseService;

public interface AdminService extends BaseService<Admin, Integer> {
    Admin login(String email, String password);

    Admin findByEmail(String email);

    void changePassword(Integer id, String oldPassword, String newPassword);
}
