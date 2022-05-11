package com.spring.achareh.service.impl;

import com.spring.achareh.customException.OldPasswordIsIncorrect;
import com.spring.achareh.model.User;
import com.spring.achareh.repository.UserRepository;
import com.spring.achareh.service.UserService;
import com.spring.achareh.service.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class UserServiceImpl<S extends User> extends BaseServiceImpl<S, Integer, UserRepository<S>>
        implements UserService<S> {

    public UserServiceImpl(UserRepository<S> repository) {
        super(repository);
    }

    @Override
    public S login(String email, String password) {
        return repository.findByEmailAndPassword(email,password);
    }

    @Override
    public S findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public void changePassword(Integer userId, String oldPassword, String newPassword) {
        S s = repository.findById(userId).get();

        if (!s.getPassword().equals(oldPassword)) {
            throw new OldPasswordIsIncorrect();
        }
        s.setPassword(newPassword);
        repository.save(s);
    }
}
