package com.spring.achareh.service;

import com.spring.achareh.model.Comment;
import com.spring.achareh.model.Customer;
import com.spring.achareh.model.Expert;
import com.spring.achareh.model.Speciality;
import com.spring.achareh.service.base.BaseService;

import java.util.List;

public interface CommentService extends BaseService<Comment,Integer> {
    List<Comment> findAllByCustomer(Customer customer);

    List<Comment> findAllByExpert(Expert expert);

    List<Comment> findAllByService(Speciality speciality);
}
