package com.spring.achareh.service.impl;

import com.spring.achareh.customException.*;
import com.spring.achareh.model.Expert;
import com.spring.achareh.model.Offer;
import com.spring.achareh.model.Order;
import com.spring.achareh.model.enumration.OrderStatus;
import com.spring.achareh.repository.OfferRepository;
import com.spring.achareh.service.ExpertService;
import com.spring.achareh.service.OfferService;
import com.spring.achareh.service.OrderService;
import com.spring.achareh.service.base.BaseServiceImpl;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
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
    public void OfferRegister(Integer expertId, Integer orderId, Integer suggestionPrice,
                              Integer durationOfWork, LocalTime startWorkTime) {
        Offer offer = new Offer();
        try {
            Expert expert = expertService.findById(expertId).get();
            Order order = orderService.findById(orderId).get();
            if (!expert.getSpecialities().contains(order.getSpeciality())) {
                throw new DoNotHaveAccessToThisService();
            }
            if (suggestionPrice < order.getSpeciality().getBasePrice()) {
                throw new SuggestionPriceMustBeHigherThanTheBasePrice();
            }
            offer.setExpert(expert);
            offer.setOrder(order);
            offer.setSuggestionPrice(suggestionPrice);
            offer.setDurationOfWork(durationOfWork);
            offer.setStartWorkTime(startWorkTime);
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
            if (!offer.getOrder().getId().equals(orderId)) {
                throw new OfferAndOrderDoesNotMatch();
            }
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
    public List<Offer> findAllOfferByOrder(Integer orderId, boolean sortByPrice, boolean sortByScore) {
        Order order = orderService.findById(orderId).get();
        if (sortByPrice && sortByScore) {
            return repository.findAllOfferByOrder(order, Sort.by("suggestionPrice").ascending().and(Sort.by("score").ascending()));
        }
        if (sortByScore) {
            return repository.findAllOfferByOrder(order, Sort.by("score").ascending());
        }
        if (sortByPrice) {
            return repository.findAllOfferByOrder(order, Sort.by("suggestionPrice").ascending());
        }
        return repository.findAllOfferByOrder(order, null);
    }


}
