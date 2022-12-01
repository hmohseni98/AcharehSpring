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
    public void offerRegister(@RequestBody OfferRegisterDTO offerDTO) {
        Offer offer = modelMapper.map(offerDTO, Offer.class);
        Expert expert = new Expert();
        expert.setId(offer.getExpert().getId());
        offer.setStartWorkTime(LocalTime.parse(offerDTO.getStartWorkTime()));
        offer.setExpert(expert);
        offerService.offerRegister(offer);
    }

    @GetMapping("/findAllOfferByCustomer")
    public ResponseEntity<Offer> findOfferByCustomer(@RequestParam Integer offerId, @RequestParam Integer orderId) {
        offerService.selectOfferByCustomer(offerId, orderId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/findAllOfferByOrder")
    @ResponseStatus(HttpStatus.OK)
    public List<OfferDTO> findAllOfferByOrderId(@RequestParam Integer orderId, @RequestParam boolean sortByPrice, @RequestParam boolean sortByScore) {
        return offerService.findAllOfferByOrderId(orderId, sortByPrice, sortByScore);
    }

    @GetMapping("/findAllOfferByExpert")
    @ResponseStatus(HttpStatus.OK)
    public List<OfferDTO> findAllOfferByExpertId(@RequestParam Integer userId) {
        return offerService.findAllOfferByExpertId(userId);
    }

    @PostMapping("/selectOfferByCustomer")
    @ResponseStatus(HttpStatus.OK)
    public void selectOfferByCustomer(@RequestParam Integer offerId, @RequestParam Integer orderId) {
        offerService.selectOfferByCustomer(offerId, orderId);
    }
}
