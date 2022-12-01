package com.spring.achareh.controller;

import com.spring.achareh.model.Speciality;
import com.spring.achareh.service.SpecialityService;
import com.spring.achareh.service.dto.speciality.SpecialityDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/speciality")
public class SpecialityController {
    private final SpecialityService specialityService;

    public SpecialityController(SpecialityService specialityService) {
        this.specialityService = specialityService;
    }

    private static final ModelMapper modelMapper = new ModelMapper();

    static {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        PropertyMap<SpecialityDTO, Speciality> map =
                new PropertyMap<>() {
                    @Override
                    protected void configure() {
                        map(source.getCategoryName()).getCategory().setName(null);
                    }
                };
        modelMapper.addMappings(map);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    public void save(@RequestBody SpecialityDTO specialityDTO) {
        Speciality speciality = modelMapper.map(specialityDTO, Speciality.class);
        specialityService.save(speciality);
    }

    @GetMapping("/findAll")
    public List<SpecialityDTO> findAll() {
        return specialityService.selectAll();
    }
}
