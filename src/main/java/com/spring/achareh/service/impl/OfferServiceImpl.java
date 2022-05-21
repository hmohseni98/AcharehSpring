package com.spring.achareh.service.impl;

import com.spring.achareh.customException.*;
import com.spring.achareh.model.Expert;
import com.spring.achareh.model.Offer;
import com.spring.achareh.model.Order;
import com.spring.achareh.model.enumration.AccountStatus;
import com.spring.achareh.model.enumration.OrderStatus;
import com.spring.achareh.repository.OfferRepository;
import com.spring.achareh.service.ExpertService;
import com.spring.achareh.service.OfferService;
import com.spring.achareh.service.OrderService;
import com.spring.achareh.service.base.BaseServiceImpl;
import com.spring.achareh.service.dto.offer.OfferDTO;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class OfferServiceImpl extends BaseServiceImpl<Offer, Integer, OfferRepository>
        implements OfferService {
    private final ExpertService expertService;
    private final OrderService orderService;

    public OfferServiceImpl(OfferRepository repository, ExpertService expertService, OrderService orderService) {
        super(repository);
        this.expertService = expertService;
        this.orderService = orderService;
    }

    @Transactional
    @Override
    public void offerRegister(Offer offer) {
        try {
            Expert expert = expertService.findById(offer.getExpert().getId()).get();
            Order order = orderService.findById(offer.getOrder().getId()).get();
            if(!expert.getStatus().equals(AccountStatus.active)){
                throw new AccountNotActive();
            }
            if (!expert.getSpecialities().contains(order.getSpeciality())) {
                throw new DoNotHaveAccessToThisService();
            }
            if (offer.getSuggestionPrice() < order.getSpeciality().getBasePrice()) {
                throw new SuggestionPriceMustBeHigherThanTheBasePrice();
            }
            offer.setExpert(expert);
            offer.setOrder(order);
            repository.save(offer);
            order.setStatus(OrderStatus.waitingExpertSelection);
            orderService.save(order);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Transactional
    @Override
    public void selectOfferByCustomer(Integer offerId, Integer orderId) {
        Offer offer = repository.findById(offerId).get();
        Order order = orderService.findById(orderId).get();
        try {
            if (!order.getStatus().equals(OrderStatus.waitingExpertSelection)) {
                throw new AccessDenied();
            }
            order.setAcceptOffer(offer);
            order.setStatus(OrderStatus.dispatchOfAnExpert);
            orderService.save(order);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    @Transactional
    @Override
    public List<OfferDTO> findAllOfferByOrderId(Integer orderId, boolean sortByPrice, boolean sortByScore) {
        if (sortByPrice && sortByScore) {
            return repository.findAllOfferByOrderId(orderId, Sort.by("expertSuggestionPrice").ascending().and(Sort.by("expertScore").descending()));
        }
        if (sortByScore) {
            return repository.findAllOfferByOrderId(orderId, Sort.by("expertScore").descending());
        }
        if (sortByPrice) {
            return repository.findAllOfferByOrderId(orderId, Sort.by("expertSuggestionPrice").ascending());
        }
        return repository.findAllOfferByOrderId(orderId, null);
    }


}
