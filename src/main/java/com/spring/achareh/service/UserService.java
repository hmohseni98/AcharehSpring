package com.spring.achareh.service;

import com.spring.achareh.model.User;
import com.spring.achareh.model.enumration.Role;
import com.spring.achareh.service.base.BaseService;
import com.spring.achareh.service.dto.user.UserDTO;
import org.springframework.data.domain.Page;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public interface UserService extends BaseService<User,Integer> {

    User login(String email, String password);

    void changePassword(Integer userId, String oldPassword, String newPassword);

    Page<User> gridSearch(Integer id, String email, String firstName, String lastName , Role role,
                          Integer pageNo, Integer pageSize, String sortingField, String sortingDirection);

    Boolean existsUserByEmail(String email);

    void generateOneTimePassword(User user) throws MessagingException, UnsupportedEncodingException;

    void sendOTPEmail(User user, String OTP) throws MessagingException, UnsupportedEncodingException;

    void clearOTP(User user);
}
