package com.spring.achareh.service.impl;

import com.spring.achareh.model.Category;
import com.spring.achareh.repository.CategoryRepository;
import com.spring.achareh.service.CategoryService;
import com.spring.achareh.service.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends BaseServiceImpl<Category, Integer, CategoryRepository>
        implements CategoryService {

    public CategoryServiceImpl(CategoryRepository repository) {
        super(repository);
    }
}
