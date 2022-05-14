package com.spring.achareh.service.impl;

import com.spring.achareh.customException.ServiceNameAlreadyExist;
import com.spring.achareh.model.Category;
import com.spring.achareh.model.Speciality;
import com.spring.achareh.repository.SpecialityRepository;
import com.spring.achareh.service.SpecialityService;
import com.spring.achareh.service.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
public class SpecialityServiceImpl extends BaseServiceImpl<Speciality, Integer, SpecialityRepository>
        implements SpecialityService {
    public SpecialityServiceImpl(SpecialityRepository repository) {
        super(repository);
    }

    @Transactional
    @Override
    public void save(Speciality speciality) {
        try {
            super.save(speciality);
            if (speciality.getId() == null)
                throw new ServiceNameAlreadyExist();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Speciality> findAllByCategory(Category category) {
        return repository.findAllByCategory(category);
    }

    @Override
    public Set<Speciality> findSpecialityByExpertId(Integer expertId) {
        return repository.findSpecialityByExpertId(expertId);
    }
}
