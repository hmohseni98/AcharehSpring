package com.spring.achareh.service;

import com.spring.achareh.model.User;
import com.spring.achareh.model.enumration.Role;
import com.spring.achareh.service.base.BaseService;

import java.util.List;

public interface UserService extends BaseService<User,Integer> {

    User login(String email, String password);

    void changePassword(Integer userId, String oldPassword, String newPassword);

    List<User> gridSearch(Integer id, String email, String firstName, String lastName , Role role);

    Boolean existsUserByEmail(String email);

}
