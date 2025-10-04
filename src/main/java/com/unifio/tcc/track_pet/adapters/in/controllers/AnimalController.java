package com.unifio.tcc.track_pet.adapters.in.controllers;

import com.unifio.tcc.track_pet.adapters.in.dtos.AnimalRegistrarDTO;
import com.unifio.tcc.track_pet.adapters.in.dtos.AnimalRespostaDTO;
import com.unifio.tcc.track_pet.adapters.in.mappers.AnimalDTOMapper;
import com.unifio.tcc.track_pet.application.services.animal.RegistrarAnimalService;
import com.unifio.tcc.track_pet.domain.animal.Animal;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "animal")
public class AnimalController {
    private RegistrarAnimalService registrarAnimalService;
    private AnimalDTOMapper animalDTOMapper;

    public AnimalController(RegistrarAnimalService registrarAnimalService,
                            AnimalDTOMapper animalDTOMapper) {
        this.registrarAnimalService = registrarAnimalService;
        this.animalDTOMapper = animalDTOMapper;
    }

    @PostMapping
    public ResponseEntity<AnimalRespostaDTO> registrarAnimal(@RequestBody AnimalRegistrarDTO dto) {
        Animal animal = registrarAnimalService.registrarAnimal(animalDTOMapper.registrarDtoToEntity(dto));
        return ResponseEntity.status(HttpStatus.CREATED).body(animalDTOMapper.domainToDto(animal));
    }
}
