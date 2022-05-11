package com.spring.achareh.service.impl;

import com.spring.achareh.model.Admin;
import com.spring.achareh.repository.AdminRepository;
import com.spring.achareh.service.AdminService;
import com.spring.achareh.service.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class AdminServiceImpl extends BaseServiceImpl<Admin, Integer, AdminRepository>
        implements AdminService {

    public AdminServiceImpl(AdminRepository repository) {
        super(repository);
    }

    @Transactional
    @Override
    public Admin login(String email, String password) {
        return repository.findByEmailAndPassword(email, password);
    }

    @Transactional
    @Override
    public Admin findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Transactional
    @Override
    public void changePassword(Integer id, String oldPassword, String newPassword) {
        Admin admin = repository.findById(id).get();

        if (!admin.getPassword().equals(oldPassword)) {
            //throw new
        }
        admin.setPassword(newPassword);
        repository.save(admin);
    }
}
