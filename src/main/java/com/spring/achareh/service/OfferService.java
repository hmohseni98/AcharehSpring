package com.spring.achareh.service;

import com.spring.achareh.model.Offer;
import com.spring.achareh.service.base.BaseService;
import com.spring.achareh.service.dto.OfferDto;

import java.time.LocalTime;
import java.util.List;

public interface OfferService extends BaseService<Offer, Integer> {
    void offerRegister(Integer expertId, Integer orderId, Integer suggestionPrice, Integer durationOfWork, LocalTime startWorkTime);

    List<OfferDto> findAllOfferByOrderId(Integer orderId, boolean sortByPrice, boolean sortByScore);

    void selectOfferByCustomer(Integer offerId,Integer orderId);


}
