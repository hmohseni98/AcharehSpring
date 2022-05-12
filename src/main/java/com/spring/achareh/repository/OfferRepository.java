package com.spring.achareh.repository;

import com.spring.achareh.model.Offer;
import com.spring.achareh.model.Order;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OfferRepository extends JpaRepository<Offer, Integer>, PagingAndSortingRepository<Offer,Integer> {
    List<Offer> findAll(Specification<Offer> specification);

    
    List<Offer> findAllOfferByOrder(Order order, Sort sort);

}
