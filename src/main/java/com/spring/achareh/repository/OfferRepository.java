package com.spring.achareh.repository;

import com.spring.achareh.model.Offer;
import com.spring.achareh.service.dto.OfferDto;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OfferRepository extends JpaRepository<Offer, Integer> {

    @Query("select new com.spring.achareh.service.dto.OfferDto(o.id , " +
            "concat(e.firstName,' ',e.lastName) as expertFullName , " +
            "o.durationOfWork , " +
            "a.workDate , " +
            "o.startWorkTime , " +
            "o.suggestionPrice as expertSuggestionPrice , " +
            "e.averageScore as expertScore ) " +
            "from com.spring.achareh.model.Offer o " +
            "join o.order a " +
            "join o.expert e " +
            "where a.id = ?1")
    List<OfferDto> findAllOfferByOrderId(Integer orderId, Sort sort);
}
