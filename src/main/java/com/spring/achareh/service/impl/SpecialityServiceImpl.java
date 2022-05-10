package com.spring.achareh.service.impl;

import com.spring.achareh.model.Category;
import com.spring.achareh.model.Speciality;
import com.spring.achareh.repository.SpecialityRepository;
import com.spring.achareh.service.SpecialityService;
import com.spring.achareh.service.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecialityServiceImpl extends BaseServiceImpl<Speciality, Integer, SpecialityRepository>
        implements SpecialityService {
    public SpecialityServiceImpl(SpecialityRepository repository) {
        super(repository);
    }

    @Override
    public List<Speciality> findAllByCategory(Category category) {
        return null;
    }
}
