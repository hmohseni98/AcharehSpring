package com.spring.achareh.service;

import com.spring.achareh.model.Expert;
import com.spring.achareh.model.Offer;
import com.spring.achareh.service.base.BaseService;

import java.sql.Date;
import java.util.List;

public interface OfferService extends BaseService<Offer,Integer> {
    List<Offer> findAllOfferByExpert(Expert expert);

    List<Offer> findAllOfferByDate(Date date);
}
