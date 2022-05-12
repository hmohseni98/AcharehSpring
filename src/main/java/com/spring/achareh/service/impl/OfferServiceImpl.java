package com.spring.achareh.service.impl;

import com.spring.achareh.customException.SuggestionPriceMustBeHigherThanTheBasePrice;
import com.spring.achareh.customException.doNotHaveAccessToThisService;
import com.spring.achareh.model.Expert;
import com.spring.achareh.model.Offer;
import com.spring.achareh.model.Order;
import com.spring.achareh.model.enumration.OrderStatus;
import com.spring.achareh.repository.OfferRepository;
import com.spring.achareh.service.ExpertService;
import com.spring.achareh.service.OfferService;
import com.spring.achareh.service.OrderService;
import com.spring.achareh.service.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
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
                throw new doNotHaveAccessToThisService();
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

    @Override
    public List<Offer> findAllOfferByExpert(Expert expert) {
        return repository.findAllOfferByExpert(expert);
    }

    @Override
    public List<Offer> findAllOfferByDate(LocalDate date) {
        return repository.findAllOfferBySubmitDateTime(date);
    }
}
