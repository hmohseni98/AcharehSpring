package com.spring.achareh.service.impl;

import com.spring.achareh.customException.OldPasswordIsIncorrect;
import com.spring.achareh.model.User;
import com.spring.achareh.util.UserGridSearch;
import com.spring.achareh.repository.UserRepository;
import com.spring.achareh.service.UserService;
import com.spring.achareh.service.base.BaseServiceImpl;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
@Service
public class UserServiceImpl extends BaseServiceImpl<User, Integer, UserRepository>
        implements UserService {
    private final UserGridSearch userGridSearch;

    public UserServiceImpl(UserRepository repository, UserGridSearch userGridSearch) {
        super(repository);
        this.userGridSearch = userGridSearch;
    }

    @Override
    public Boolean login(String email, String password) {
        return repository.existsByEmailAndPassword(email, password);
    }

    @Override
    public void changePassword(Integer userId, String oldPassword, String newPassword) {
        User user = repository.findById(userId).get();
        if (!user.getPassword().equals(oldPassword)) {
            throw new OldPasswordIsIncorrect();
        }
        user.setPassword(newPassword);
        repository.save(user);
    }

    @Override
    public List<User> gridSearch(Integer userId, String email, String firstName, String lastName) {
        Specification<User> specification = userGridSearch.gridSearch(userId, email, firstName, lastName);
        return repository.findAll(specification);
    }

}
