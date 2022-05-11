package com.spring.achareh.service.impl;

import com.spring.achareh.model.Admin;
import com.spring.achareh.repository.AdminRepository;
import com.spring.achareh.service.AdminService;
import com.spring.achareh.service.base.BaseServiceImpl;
import org.springframework.stereotype.Service;


@Service
public class AdminServiceImpl extends BaseServiceImpl<Admin, Integer, AdminRepository>
        implements AdminService {

    public AdminServiceImpl(AdminRepository repository) {
        super(repository);
    }

}
