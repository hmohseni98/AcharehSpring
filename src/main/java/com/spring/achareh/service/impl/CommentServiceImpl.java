package com.spring.achareh.service.impl;

import com.spring.achareh.model.Comment;
import com.spring.achareh.model.Customer;
import com.spring.achareh.model.Expert;
import com.spring.achareh.model.Speciality;
import com.spring.achareh.repository.CommentRepository;
import com.spring.achareh.service.CommentService;
import com.spring.achareh.service.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CommentServiceImpl extends BaseServiceImpl<Comment, Integer, CommentRepository>
        implements CommentService {
    public CommentServiceImpl(CommentRepository repository) {
        super(repository);
    }

    @Override
    public List<Comment> findAllByCustomer(Customer customer) {
        return null;
    }

    @Override
    public List<Comment> findAllByExpert(Expert expert) {
        return null;
    }

    @Override
    public List<Comment> findAllByService(Speciality speciality) {
        return null;
    }
}
