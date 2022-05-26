package com.spring.achareh.repository;

import com.spring.achareh.model.Offer;
import com.spring.achareh.service.dto.offer.OfferDTO;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OfferRepository extends JpaRepository<Offer, Integer> {

    @Query("select new com.spring.achareh.service.dto.offer.OfferDTO(o.id , " +
            "concat(e.firstName,' ',e.lastName) as expertFullName , " +
            "s.name as serviceName ," +
            "o.durationOfWork , " +
            "a.description as orderDescription , " +
            "a.workDate , " +
            "o.suggestionPrice as expertSuggestionPrice ) " +
            "from com.spring.achareh.model.Offer o " +
            "join o.order a " +
            "join o.expert e " +
            "join a.speciality s " +
            "where a.id = ?1")
    List<OfferDTO> findAllOfferByOrderId(Integer orderId, Sort sort);

    @Query("select new com.spring.achareh.service.dto.offer.OfferDTO(o.id , " +
            "concat(e.firstName,' ',e.lastName) as expertFullName , " +
            "s.name as serviceName ," +
            "o.durationOfWork , " +
            "a.description as orderDescription , " +
            "a.workDate , " +
            "o.suggestionPrice as expertSuggestionPrice ) " +
            "from com.spring.achareh.model.Offer o " +
            "join o.order a " +
            "join o.expert e " +
            "join a.speciality s " +
            "where e.id = ?1")
    List<OfferDTO> findAllOfferByExpertId(Integer expertId);
}
