package com.spring.achareh.controller;

import com.spring.achareh.exceptionHandler.customException.AccessDeniedException;
import com.spring.achareh.model.Expert;
import com.spring.achareh.model.User;
import com.spring.achareh.service.ExpertService;
import com.spring.achareh.service.dto.expert.ExpertRegisterDTO;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;

@RestController
@CrossOrigin
@RequestMapping("api/expert")
public class ExpertController {
    private final ExpertService expertService;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    public ExpertController(ExpertService expertService, ModelMapper modelMapper, BCryptPasswordEncoder passwordEncoder) {
        this.expertService = expertService;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    public void save(@Valid @ModelAttribute ExpertRegisterDTO expertDTO) throws IOException {
        Expert expert = modelMapper.map(expertDTO, Expert.class);
        expert.setImage(expertDTO.getImage().getBytes());
        expert.setPassword(passwordEncoder.encode(expert.getPassword()));
        expertService.save(expert);
    }

    @PostMapping("/select-speciality")
    @ResponseStatus(HttpStatus.OK)
    public void addExpertToSpeciality(Integer specialityId, HttpServletRequest request) {
        User user = null;
        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals("sec_data")) {
                user = UserController.userMap.get(cookie.getValue());
                break;
            }
        }
        if (user == null)
            throw new AccessDeniedException();
        expertService.addExpertToSpeciality(user.getId(), specialityId);
    }

    @PostMapping("/remove-speciality")
    public ResponseEntity<Expert> removeExpertFromSpeciality(Integer expertId, Integer specialityId) {
        expertService.removeExpertFromSpeciality(expertId, specialityId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/account-balance")
    @ResponseStatus(HttpStatus.OK)
    public int accountBalance(HttpServletRequest request) {
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
        Expert expert = expertService.findById(user.getId()).get();
        return expert.getBalance();
    }
}