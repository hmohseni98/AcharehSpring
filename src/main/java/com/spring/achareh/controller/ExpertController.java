package com.spring.achareh.controller;

import com.spring.achareh.model.Expert;
import com.spring.achareh.service.ExpertService;
import com.spring.achareh.service.dto.expert.ExpertRegisterDTO;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("api/expert")
public class ExpertController {
    private final ExpertService expertService;
    private final ModelMapper modelMapper;

    public ExpertController(ExpertService expertService, ModelMapper modelMapper) {
        this.expertService = expertService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/register")
    public ResponseEntity<Expert> save(@Valid @ModelAttribute ExpertRegisterDTO expertDto) throws IOException {
        Expert expert = modelMapper.map(expertDto, Expert.class);
        expert.setImage(expertDto.getImage().getBytes());
        expertService.save(expert);
        if (expert.getId() != null) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/selectSpeciality")
    public ResponseEntity<Expert> addExpertToSpeciality(@RequestParam Integer expertId, Integer specialityId) {
        expertService.addExpertToSpeciality(expertId, specialityId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/removeSpeciality")
    public ResponseEntity<Expert> removeExpertFromSpeciality(@RequestParam Integer expertId, Integer specialityId) {
        expertService.removeExpertFromSpeciality(expertId, specialityId);
        return ResponseEntity.ok().build();
    }
}