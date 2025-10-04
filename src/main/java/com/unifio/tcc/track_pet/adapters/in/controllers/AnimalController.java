package com.unifio.tcc.track_pet.adapters.in.controllers;

import com.unifio.tcc.track_pet.adapters.in.dtos.AnimalRegistrarDTO;
import com.unifio.tcc.track_pet.adapters.in.dtos.AnimalRespostaDTO;
import com.unifio.tcc.track_pet.adapters.in.mappers.AnimalDTOMapper;
import com.unifio.tcc.track_pet.application.services.animal.RegistrarAnimalService;
import com.unifio.tcc.track_pet.domain.animal.Animal;
import com.unifio.tcc.track_pet.domain.usecases.animal.ListarAnimalUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "animal", produces = APPLICATION_JSON_VALUE)
public class AnimalController {
    private ListarAnimalUseCase listarAnimalUseCase;
    private RegistrarAnimalService registrarAnimalService;
    private AnimalDTOMapper animalDTOMapper;

    public AnimalController(RegistrarAnimalService registrarAnimalService,
                            AnimalDTOMapper animalDTOMapper,
                            ListarAnimalUseCase listarAnimalUseCase) {
        this.registrarAnimalService = registrarAnimalService;
        this.animalDTOMapper = animalDTOMapper;
        this.listarAnimalUseCase = listarAnimalUseCase;
    }

    @PostMapping
    public ResponseEntity<AnimalRespostaDTO> registrarAnimal(@RequestBody AnimalRegistrarDTO dto) {
        Animal animal = registrarAnimalService.registrarAnimal(animalDTOMapper.registrarDtoToEntity(dto));
        return ResponseEntity.status(HttpStatus.CREATED).body(animalDTOMapper.domainToDto(animal));
    }

    @GetMapping
    public ResponseEntity<List<AnimalRespostaDTO>> listarAnimal() {
        List<Animal> animais = listarAnimalUseCase.listarAnimal();
        return ResponseEntity.ok().body(animais.stream()
                .map(animalDTOMapper::domainToDto)
                .toList());
    }
}
