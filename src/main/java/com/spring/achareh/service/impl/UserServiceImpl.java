package com.spring.achareh.service.impl;

import com.spring.achareh.exceptionHandler.customException.OldPasswordIsIncorrectException;
import com.spring.achareh.model.User;
import com.spring.achareh.model.enumration.Role;
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
    public User login(String email, String password) {
        return repository.findByEmailAndPassword(email, password);
    }

    @Override
    public void changePassword(Integer userId, String oldPassword, String newPassword) {
        User user = repository.findById(userId).get();
        if (!user.getPassword().equals(oldPassword))
            throw new OldPasswordIsIncorrectException();
        user.setPassword(newPassword);
        repository.save(user);
    }

    @Override
    public List<User> gridSearch(Integer id, String email, String firstName, String lastName, Role role) {
        Specification<User> specification = userGridSearch.gridSearch(id, email, firstName, lastName, role);
        return repository.findAll(specification);
    }

    @Override
    public Boolean existsUserByEmail(String email) {
        return repository.existsUserByEmail(email);
    }

}
