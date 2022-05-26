package com.spring.achareh.controller;

import com.spring.achareh.exceptionHandler.customException.AccessDeniedException;
import com.spring.achareh.model.Expert;
import com.spring.achareh.model.Offer;
import com.spring.achareh.model.User;
import com.spring.achareh.service.OfferService;
import com.spring.achareh.service.dto.offer.OfferDTO;
import com.spring.achareh.service.dto.offer.OfferRegisterDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalTime;
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
    @ResponseStatus(HttpStatus.OK)
    public void offerRegister(OfferRegisterDTO offerDTO, HttpServletRequest request) {
        Offer offer = modelMapper.map(offerDTO, Offer.class);
        User user = null;
        if (request.getCookies() == null)
            throw new AccessDeniedException();
        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals("sec_data")) {
                user = UserController.userMap.get(cookie.getValue());
                break;
            }
        }
        if (user == null)
            throw new AccessDeniedException();
        Expert expert = new Expert();
        expert.setId(user.getId());
        offer.setStartWorkTime(LocalTime.parse(offerDTO.getStartWorkTime()));
        offer.setExpert(expert);
        offerService.offerRegister(offer);
    }

    @PostMapping("/select-offer-by-customer")
    public ResponseEntity<Offer> selectOfferByCustomer(Integer offerId, Integer orderId) {
        offerService.selectOfferByCustomer(offerId, orderId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/find-all-offer-by-order-id")
    @ResponseStatus(HttpStatus.OK)
    public List<OfferDTO> findAllOfferByOrderId(Integer orderId, boolean sortByPrice, boolean sortByScore) {
        return offerService.findAllOfferByOrderId(orderId, sortByPrice, sortByScore);
    }

    @GetMapping("/find-all-offer-by-expert-id")
    @ResponseStatus(HttpStatus.OK)
    public List<OfferDTO> findAllOfferByExpertId(HttpServletRequest request) {
        User user = null;
        if (request.getCookies() == null)
            throw new AccessDeniedException();
        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals("sec_data")) {
                user = UserController.userMap.get(cookie.getValue());
                break;
            }
        }
        if (user == null)
            throw new AccessDeniedException();
        return offerService.findAllOfferByExpertId(user.getId());
    }
}
