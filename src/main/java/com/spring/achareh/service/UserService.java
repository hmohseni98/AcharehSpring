package com.spring.achareh.service;

import com.spring.achareh.model.User;
import com.spring.achareh.service.base.BaseService;

public interface UserService<S extends User> extends BaseService<S,Integer> {

    S login(String email, String password);

    S findByEmail(String email);

    void changePassword(Integer userId, String oldPassword, String newPassword);

}
