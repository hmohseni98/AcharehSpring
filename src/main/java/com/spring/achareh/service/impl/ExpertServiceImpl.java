package com.spring.achareh.service.impl;

import com.spring.achareh.model.Expert;
import com.spring.achareh.model.enumration.AccountStatus;
import com.spring.achareh.repository.ExpertRepository;
import com.spring.achareh.service.ExpertService;
import com.spring.achareh.service.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpertServiceImpl extends BaseServiceImpl<Expert, Integer, ExpertRepository>
        implements ExpertService {
    public ExpertServiceImpl(ExpertRepository repository) {
        super(repository);
    }

    @Override
    public List<Expert> findAllByStatus(AccountStatus status) {
        return null;
    }

    @Override
    public List<Expert> findAllByCategory(String categoryName) {
        return null;
    }
}
