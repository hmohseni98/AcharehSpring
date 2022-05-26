package com.spring.achareh.service;


import com.spring.achareh.model.Category;
import com.spring.achareh.model.Speciality;
import com.spring.achareh.service.base.BaseService;
import com.spring.achareh.service.dto.speciality.SpecialityDTO;

import java.util.List;
import java.util.Set;

public interface SpecialityService extends BaseService<Speciality, Integer> {
    List<Speciality> findAllByCategory(Category category);

    Set<Speciality> findSpecialityByExpertId(Integer expertId);

    List<SpecialityDTO> selectAll();

}
