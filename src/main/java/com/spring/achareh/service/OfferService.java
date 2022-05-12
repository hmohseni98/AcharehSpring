package com.spring.achareh.service;

import com.spring.achareh.model.Offer;
import com.spring.achareh.service.base.BaseService;

import java.time.LocalTime;
import java.util.List;

public interface OfferService extends BaseService<Offer, Integer> {
    void OfferRegister(Integer expertId, Integer orderId, Integer suggestionPrice, Integer durationOfWork, LocalTime startWorkTime);

    //List<Offer> findAll(Specification<Offer> offer);

    List<Offer> findAllOfferByOrder(Integer orderId, boolean sortByPrice, boolean sortByScore);

    void selectOfferByCustomer(Integer offerId,Integer orderId);


}
