package com.spring.achareh.service;

import com.spring.achareh.model.Expert;
import com.spring.achareh.model.Offer;
import com.spring.achareh.service.base.BaseService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface OfferService extends BaseService<Offer, Integer> {
    void OfferRegister(Integer expertId, Integer orderId, Integer suggestionPrice, Integer durationOfWork, LocalTime startWorkTime);

    List<Offer> findAllOfferByExpert(Expert expert);

    List<Offer> findAllOfferByDate(LocalDate date);
}
