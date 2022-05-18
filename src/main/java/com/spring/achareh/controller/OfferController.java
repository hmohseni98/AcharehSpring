package com.spring.achareh.controller;

import com.spring.achareh.model.Offer;
import com.spring.achareh.service.OfferService;
import com.spring.achareh.service.dto.offer.OfferRegisterDTO;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/offer")
public class OfferController {

    private final OfferService offerService;

    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    private static final ModelMapper modelMapper = new ModelMapper();

    static {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        PropertyMap<OfferRegisterDTO, Offer> map =
                new PropertyMap<>() {
                    @Override
                    protected void configure() {
                        map(source.getExpertId()).getExpert().setId(null);
                        map(source.getOrderId()).getOrder().setId(null);
                    }
                };
        modelMapper.addMappings(map);
    }

    @PostMapping("/register")
    public ResponseEntity<Offer> offerRegister(OfferRegisterDTO offerDTO) {
        Offer offer = modelMapper.map(offerDTO, Offer.class);
        offerService.offerRegister(offer);
        if (offer.getId() != null) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
