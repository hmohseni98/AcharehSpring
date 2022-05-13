package com.spring.achareh.service.impl;

import com.spring.achareh.customException.DoNotHaveAccessToThisOrder;
import com.spring.achareh.customException.ScoreOutOfRange;
import com.spring.achareh.customException.StatusOfThisOrderHasNotBeenPaid;
import com.spring.achareh.model.Comment;
import com.spring.achareh.model.Customer;
import com.spring.achareh.model.Expert;
import com.spring.achareh.model.Speciality;
import com.spring.achareh.model.enumration.OrderStatus;
import com.spring.achareh.repository.CommentRepository;
import com.spring.achareh.service.CommentService;
import com.spring.achareh.service.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class CommentServiceImpl extends BaseServiceImpl<Comment, Integer, CommentRepository>
        implements CommentService {

    @Transactional
    @Override
    public void save(Comment comment) {
        try {
            if (!(comment.getOffer().getOrder().getCustomer().getId().equals(comment.getCustomer().getId()))){
                throw new DoNotHaveAccessToThisOrder();
            }
            if (!(comment.getOffer().getOrder().getStatus().equals(OrderStatus.Paid))) {
                throw new StatusOfThisOrderHasNotBeenPaid();
            }
            if (!(comment.getScore() > 0 && comment.getScore() < 6)){
                throw new ScoreOutOfRange();
            }
            super.save(comment);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        super.save(comment);
    }

    public CommentServiceImpl(CommentRepository repository) {
        super(repository);
    }

    @Override
    public List<Comment> findAllByCustomer(Customer customer) {
        return repository.findAllByCustomer(customer);
    }

    @Override
    public List<Comment> findAllByService(Speciality speciality) {
        return repository.findAllBySpeciality(speciality);
    }
}
