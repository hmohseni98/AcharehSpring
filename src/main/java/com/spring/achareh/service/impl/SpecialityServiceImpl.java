package com.spring.achareh.service.impl;

import com.spring.achareh.exceptionHandler.customException.CategoryDoesNotExistException;
import com.spring.achareh.exceptionHandler.customException.ServiceNameAlreadyExistException;
import com.spring.achareh.model.Category;
import com.spring.achareh.model.Speciality;
import com.spring.achareh.repository.SpecialityRepository;
import com.spring.achareh.service.CategoryService;
import com.spring.achareh.service.SpecialityService;
import com.spring.achareh.service.base.BaseServiceImpl;
import com.spring.achareh.service.dto.speciality.SpecialityDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
public class SpecialityServiceImpl extends BaseServiceImpl<Speciality, Integer, SpecialityRepository>
        implements SpecialityService {
    private final CategoryService categoryService;

    public SpecialityServiceImpl(SpecialityRepository repository, CategoryService categoryService) {
        super(repository);
        this.categoryService = categoryService;
    }

    @Transactional
    @Override
    public void save(Speciality speciality) {
        Category category = categoryService.findByName(speciality.getCategory().getName());
        if (category == null)
            throw new CategoryDoesNotExistException();
        speciality.setCategory(category);
        super.save(speciality);
        if (speciality.getId() == null)
            throw new ServiceNameAlreadyExistException();
    }


    @Override
    public List<Speciality> findAllByCategory(Category category) {
        return repository.findAllByCategory(category);
    }

    @Override
    public Set<Speciality> findSpecialityByExpertId(Integer expertId) {
        return repository.findSpecialityByExpertId(expertId);
    }

    @Override
    public List<SpecialityDTO> selectAll() {
        return repository.selectAll();
    }
}
