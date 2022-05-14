package com.spring.achareh.service;

import com.spring.achareh.model.Expert;
import com.spring.achareh.model.Speciality;
import com.spring.achareh.model.enumration.AccountStatus;
import com.spring.achareh.service.base.BaseService;

import java.util.List;

public interface ExpertService extends BaseService<Expert, Integer> {

    List<Expert> findAllByStatus(AccountStatus status);

    List<Expert> findAllByCategory(String categoryName);

    Expert findExpertBySpecialityId(Integer expertId, Integer specialityId);

    void addExpertToSpeciality(Integer expertId, Integer specialityId);

    void removeExpertFromSpeciality(Integer expertId, Integer specialityId);

}
