package com.spring.achareh.service.impl;

import com.spring.achareh.model.Expert;
import com.spring.achareh.model.Offer;
import com.spring.achareh.repository.OfferRepository;
import com.spring.achareh.service.OfferService;
import com.spring.achareh.service.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;


@Service
public class OfferServiceImpl extends BaseServiceImpl<Offer, Integer, OfferRepository>
        implements OfferService {
    public OfferServiceImpl(OfferRepository repository) {
        super(repository);
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
