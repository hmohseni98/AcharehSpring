package com.spring.achareh.controller;

import com.spring.achareh.model.Offer;
import com.spring.achareh.service.OfferService;
import com.spring.achareh.service.dto.offer.OfferDTO;
import com.spring.achareh.service.dto.offer.OfferRegisterDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/selectOfferByCustomer")
    public ResponseEntity<Offer> selectOfferByCustomer(@RequestParam Integer offerId, Integer orderId) {
        offerService.selectOfferByCustomer(offerId, orderId);
        return ResponseEntity.ok().build();
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping("/findAllOfferByOrderId")
    public List<OfferDTO> findAllOfferByOrderId(Integer orderId, boolean sortByPrice, boolean sortByScore) {
        return offerService.findAllOfferByOrderId(orderId, sortByPrice, sortByScore);
    }

}
