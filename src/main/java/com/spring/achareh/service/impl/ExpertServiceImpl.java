package com.spring.achareh.service.impl;

import com.spring.achareh.customException.AccountNotActive;
import com.spring.achareh.model.Expert;
import com.spring.achareh.model.Speciality;
import com.spring.achareh.model.enumration.AccountStatus;
import com.spring.achareh.model.enumration.Role;
import com.spring.achareh.repository.ExpertRepository;
import com.spring.achareh.service.ExpertService;
import com.spring.achareh.service.SpecialityService;
import com.spring.achareh.service.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;


@Service
public class ExpertServiceImpl extends BaseServiceImpl<Expert, Integer, ExpertRepository>
        implements ExpertService {
    private final SpecialityService specialityService;

    public ExpertServiceImpl(ExpertRepository repository, SpecialityService specialityService) {
        super(repository);
        this.specialityService = specialityService;
    }

    @Transactional
    @Override
    public void save(Expert expert) {
        expert.setRole(Role.Expert);
        expert.setAverageScore(0);
        expert.setBalance(0);
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
    public void addExpertToSpeciality(Integer expertId, Integer specialityId) {
        Expert expert = repository.findById(expertId).get();
        Speciality speciality = specialityService.findById(specialityId).get();
        if (expert.getStatus() == AccountStatus.inActive){
            throw new AccountNotActive();
        }
        Set<Speciality> newSet = specialityService.findSpecialityByExpertId(expert.getId());
        newSet.add(speciality);
        expert.setSpecialities(newSet);
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
