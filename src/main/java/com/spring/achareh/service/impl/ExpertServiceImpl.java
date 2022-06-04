package com.spring.achareh.service.impl;

import com.spring.achareh.exceptionHandler.customException.AccountNotActiveException;
import com.spring.achareh.exceptionHandler.customException.EmailAlreadyExistException;
import com.spring.achareh.model.Expert;
import com.spring.achareh.model.Speciality;
import com.spring.achareh.model.User;
import com.spring.achareh.model.enumration.AccountStatus;
import com.spring.achareh.model.enumration.Role;
import com.spring.achareh.repository.ExpertRepository;
import com.spring.achareh.service.ExpertService;
import com.spring.achareh.service.SpecialityService;
import com.spring.achareh.service.UserService;
import com.spring.achareh.service.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;


@Service
public class ExpertServiceImpl extends BaseServiceImpl<Expert, Integer, ExpertRepository>
        implements ExpertService {
    private final SpecialityService specialityService;
    private final UserService userService;

    public ExpertServiceImpl(ExpertRepository repository, SpecialityService specialityService, UserService userService) {
        super(repository);
        this.specialityService = specialityService;
        this.userService = userService;
    }

    @Transactional
    @Override
    public void save(Expert expert) {
        if (userService.existsUserByEmail(expert.getEmail()))
            throw new EmailAlreadyExistException();
        expert.setRole(Role.Expert);
        expert.setAverageScore(0);
        expert.setBalance(0);
        expert.setEnabled(true);
        expert.setExpired(false);
        expert.setCredentialsExpired(false);
        expert.setLocked(false);
        super.save(expert);
    }

    @Transactional
    @Override
    public List<Expert> findAllByStatus(AccountStatus status) {
        return repository.findAllByStatus(status);
    }

    @Transactional
    @Override
    public List<Expert> findAllByCategory(String categoryName) {
        return findAllByCategory(categoryName);
    }

    @Override
    public Expert findExpertBySpecialityId(Integer expertId, Integer specialityId) {
        return null;
    }

    @Override
    public Expert findExpertByEmail(String email) {
        return repository.findExpertByEmail(email);
    }

    @Override
    public void addExpertToSpeciality(Integer expertId, Integer specialityId) {
        Expert expert = repository.findById(expertId).get();
        Speciality speciality = specialityService.findById(specialityId).get();
        if (expert.getStatus() == AccountStatus.inActive)
            throw new AccountNotActiveException();
        Set<Speciality> newSet = specialityService.findSpecialityByExpertId(expert.getId());
        newSet.add(speciality);
        expert.setSpecialities(newSet);
        expert.setStatus(AccountStatus.waiting);
        repository.save(expert);
    }

    @Override
    public void removeExpertFromSpeciality(Integer expertId, Integer specialityId) {
        Expert expert = repository.findById(expertId).get();
        Speciality speciality = specialityService.findById(specialityId).get();
        Set<Speciality> newSet = specialityService.findSpecialityByExpertId(expert.getId());
        newSet.remove(speciality);
        expert.setSpecialities(newSet);
        repository.save(expert);
    }
}
