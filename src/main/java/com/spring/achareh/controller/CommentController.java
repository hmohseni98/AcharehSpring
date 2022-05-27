package com.spring.achareh.controller;

import com.spring.achareh.exceptionHandler.customException.AccessDeniedException;
import com.spring.achareh.model.Comment;
import com.spring.achareh.model.Customer;
import com.spring.achareh.model.Offer;
import com.spring.achareh.model.User;
import com.spring.achareh.service.CommentService;
import com.spring.achareh.service.dto.comment.CommentDTO;
import com.spring.achareh.service.dto.comment.CommentRegisterDTO;
import com.spring.achareh.service.dto.customer.CustomerRegisterDTO;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("api/comment")
public class CommentController {
    private final CommentService commentService;
    private ModelMapper modelMapper;

    public CommentController(CommentService commentService, ModelMapper modelMapper) {
        this.commentService = commentService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    public void save(@RequestBody CommentRegisterDTO commentDTO){
        Comment comment = new Comment();
        Customer customer = new Customer();
        Offer offer = new Offer();
        customer.setId(commentDTO.getCustomerId());
        comment.setCustomer(customer);
        offer.setId(commentDTO.getOfferId());
        comment.setOffer(offer);
        comment.setDescription(commentDTO.getDescription());
        comment.setScore(commentDTO.getScore());
        commentService.save(comment);

    }
    @GetMapping("/find-all-by-by-expert-id")
    @ResponseStatus(HttpStatus.OK)
    public List<CommentDTO> findAllByExpertId(HttpServletRequest request) {
        User user = null;
        if (request.getCookies() == null)
            throw new AccessDeniedException();
        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals("sec_data")) {
                user = UserController.userMap.get(cookie.getValue());
                break;
            }
        }
        if (user == null)
            throw new AccessDeniedException();
        return commentService.findAllByExpertId(user.getId());
    }
}
