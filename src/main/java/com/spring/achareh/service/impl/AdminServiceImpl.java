package com.spring.achareh.service.impl;

import com.spring.achareh.exceptionHandler.customException.EmailAlreadyExistException;
import com.spring.achareh.model.Admin;
import com.spring.achareh.model.enumration.Role;
import com.spring.achareh.repository.AdminRepository;
import com.spring.achareh.service.AdminService;
import com.spring.achareh.service.UserService;
import com.spring.achareh.service.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class AdminServiceImpl extends BaseServiceImpl<Admin, Integer, AdminRepository>
        implements AdminService {

    private final UserService userService;
    public AdminServiceImpl(AdminRepository repository, UserService userService) {
        super(repository);
        this.userService = userService;
    }

    @Transactional
    @Override
    public void save(Admin admin) {
        if (userService.existsUserByEmail(admin.getEmail()))
            throw new EmailAlreadyExistException();
        admin.setRole(Role.Admin);
        admin.setEnabled(true);
        admin.setExpired(false);
        admin.setCredentialsExpired(false);
        admin.setLocked(false);
        super.save(admin);
    }
}
