package com.spring.achareh.controller;

import com.spring.achareh.exceptionHandler.customException.AccessDeniedException;
import com.spring.achareh.model.Expert;
import com.spring.achareh.model.User;
import com.spring.achareh.model.enumration.AccountStatus;
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
    public void register(@ModelAttribute ExpertRegisterDTO expertDTO) throws IOException {
        Expert expert = modelMapper.map(expertDTO, Expert.class);
        expert.setImage(expertDTO.getImage().getBytes());
        expert.setPassword(passwordEncoder.encode(expert.getPassword()));
        expertService.save(expert);
    }

    @PostMapping("/selectSpeciality")
    @ResponseStatus(HttpStatus.OK)
    public void addExpertToSpeciality(@RequestParam Integer expertId, @RequestParam Integer specialityId) {
        expertService.addExpertToSpeciality(expertId, specialityId);
    }

    @PostMapping("/removeSpeciality")
    public ResponseEntity<Expert> removeExpertFromSpeciality(Integer expertId, Integer specialityId) {
        expertService.removeExpertFromSpeciality(expertId, specialityId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/accountBalance")
    @ResponseStatus(HttpStatus.OK)
    public int accountBalance(@RequestParam Integer userId) {
        Expert expert = expertService.findById(userId).get();
        return expert.getBalance();
    }

    @PostMapping("/changeStatus")
    public void changeStatus(@RequestParam Integer userId, @RequestParam String status) {
        AccountStatus accountStatus;
        if (AccountStatus.active.name().toLowerCase().equals(status))
            accountStatus = AccountStatus.active;
        else
            accountStatus = AccountStatus.inActive;
        expertService.changeStatus(userId,accountStatus);
    }
}