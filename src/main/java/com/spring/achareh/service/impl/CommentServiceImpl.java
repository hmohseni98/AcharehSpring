package com.spring.achareh.service.impl;

import com.spring.achareh.exceptionHandler.customException.DoNotHaveAccessToThisOrderException;
import com.spring.achareh.exceptionHandler.customException.ScoreOutOfRangeException;
import com.spring.achareh.exceptionHandler.customException.OrderHasNotBeenPaidException;
import com.spring.achareh.model.Comment;
import com.spring.achareh.model.Customer;
import com.spring.achareh.model.Offer;
import com.spring.achareh.model.enumration.OrderStatus;
import com.spring.achareh.repository.CommentRepository;
import com.spring.achareh.service.CommentService;
import com.spring.achareh.service.CustomerService;
import com.spring.achareh.service.OfferService;
import com.spring.achareh.service.base.BaseServiceImpl;
import com.spring.achareh.service.dto.comment.CommentDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class CommentServiceImpl extends BaseServiceImpl<Comment, Integer, CommentRepository>
        implements CommentService {

    private final OfferService offerService;
    private final CustomerService customerService;

    @Transactional
    @Override
    public void save(Comment comment) {
        Offer offer = offerService.findById(comment.getOffer().getId()).get();
        Customer customer =  customerService.findById(comment.getCustomer().getId()).get();
        comment.setOffer(offer);
        comment.setCustomer(customer);
        if (!(comment.getOffer().getOrder().getCustomer().getId().equals(comment.getCustomer().getId())))
            throw new DoNotHaveAccessToThisOrderException();
        if (!(comment.getOffer().getOrder().getStatus().equals(OrderStatus.Paid)))
            throw new OrderHasNotBeenPaidException();
        if (!(comment.getScore() > 0 && comment.getScore() < 6))
            throw new ScoreOutOfRangeException();
        super.save(comment);
    }

    public CommentServiceImpl(CommentRepository repository, OfferService offerService, CustomerService customerService) {
        super(repository);
        this.offerService = offerService;
        this.customerService = customerService;
    }

    @Override
    public List<CommentDTO> findAllByExpertId(Integer expertId) {
        return repository.findAllByExpertId(expertId);
    }
}
