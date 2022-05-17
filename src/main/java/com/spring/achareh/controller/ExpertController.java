package com.spring.achareh.controller;

import com.spring.achareh.customException.AccessDenied;
import com.spring.achareh.model.Expert;
import com.spring.achareh.model.Speciality;
import com.spring.achareh.service.ExpertService;
import com.spring.achareh.service.SpecialityService;
import com.spring.achareh.service.dto.expert.ExpertRegisterDTO;
import lombok.SneakyThrows;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("api/expert")
public class ExpertController {
    private final ExpertService expertService;
    private final SpecialityService specialityService;
    private final ModelMapper modelMapper;

    public ExpertController(ExpertService expertService, SpecialityService specialityService, ModelMapper modelMapper) {
        this.expertService = expertService;
        this.specialityService = specialityService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/register")
    public ResponseEntity<Expert> save(@ModelAttribute ExpertRegisterDTO expertDto ,
                                       @RequestParam Set<Integer> specialitiesId) throws IOException {
        Expert expert = modelMapper.map(expertDto, Expert.class);
        expert.setImage(expertDto.getImage().getBytes());
        expertService.save(expert);
        specialitiesId.forEach(id -> expertService.addExpertToSpeciality(expert.getId(), id));
        if (expert.getId() != null) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
