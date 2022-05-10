package com.spring.achareh.service;


import com.spring.achareh.model.Category;
import com.spring.achareh.model.Speciality;
import com.spring.achareh.service.base.BaseService;
import java.util.List;

public interface SpecialityService extends BaseService<Speciality, Integer> {
    List<Speciality> findAllByCategory(Category category);
}
