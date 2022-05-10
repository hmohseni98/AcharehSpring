package com.spring.achareh.repository;

import com.spring.achareh.model.Expert;
import com.spring.achareh.model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Integer> {
    List<Offer> findAllOfferByExpert(Expert expert);

    List<Offer> findAllOfferByDate(Date date);
}
