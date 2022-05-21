package com.spring.achareh.service;

import com.spring.achareh.model.Offer;
import com.spring.achareh.service.base.BaseService;
import com.spring.achareh.service.dto.offer.OfferDTO;

import java.util.List;

public interface OfferService extends BaseService<Offer, Integer> {
    void offerRegister(Offer offer);

    List<OfferDTO> findAllOfferByOrderId(Integer orderId, boolean sortByPrice, boolean sortByScore);

    void selectOfferByCustomer(Integer offerId, Integer orderId);
}
