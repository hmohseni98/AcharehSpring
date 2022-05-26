package com.spring.achareh.service;

import com.spring.achareh.model.Category;
import com.spring.achareh.service.base.BaseService;


public interface CategoryService extends BaseService<Category,Integer> {

    Category findByName(String name);

}
