package com.unifio.tcc.track_pet.adapters.in.controllers;

import com.unifio.tcc.track_pet.adapters.in.dtos.AnimalRegistrarDTO;
import com.unifio.tcc.track_pet.adapters.in.dtos.AnimalRespostaDTO;
import com.unifio.tcc.track_pet.adapters.in.mappers.AnimalDTOMapper;
import com.unifio.tcc.track_pet.application.services.animal.BuscarAnimalPorIdService;
import com.unifio.tcc.track_pet.application.services.animal.RegistrarAnimalService;
import com.unifio.tcc.track_pet.domain.animal.Animal;
import com.unifio.tcc.track_pet.domain.usecases.animal.BuscarAnimalPorIdUseCase;
import com.unifio.tcc.track_pet.domain.usecases.animal.DesativarAnimalUseCase;
import com.unifio.tcc.track_pet.domain.usecases.animal.ListarAnimalUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "animal", produces = APPLICATION_JSON_VALUE)
public class AnimalController {
    private BuscarAnimalPorIdUseCase buscarAnimalPorIdService;
    private ListarAnimalUseCase listarAnimalUseCase;
    private RegistrarAnimalService registrarAnimalService;
    private DesativarAnimalUseCase desativarAnimalUseCase;
    private AnimalDTOMapper animalDTOMapper;

    public AnimalController(RegistrarAnimalService registrarAnimalService,
                            AnimalDTOMapper animalDTOMapper,
                            ListarAnimalUseCase listarAnimalUseCase,
                            BuscarAnimalPorIdService buscarAnimalPorIdService,
                            DesativarAnimalUseCase desativarAnimalUseCase) {
        this.registrarAnimalService = registrarAnimalService;
        this.animalDTOMapper = animalDTOMapper;
        this.listarAnimalUseCase = listarAnimalUseCase;
        this.buscarAnimalPorIdService = buscarAnimalPorIdService;
        this.desativarAnimalUseCase = desativarAnimalUseCase;
    }

    @PostMapping
    public ResponseEntity<AnimalRespostaDTO> registrarAnimal(@RequestBody AnimalRegistrarDTO dto) {
        Animal animal = registrarAnimalService.registrarAnimal(animalDTOMapper.registrarDtoToEntity(dto));
        return ResponseEntity.status(HttpStatus.CREATED).body(animalDTOMapper.domainToDto(animal));
    }

    @GetMapping
    public ResponseEntity<List<AnimalRespostaDTO>> listarAnimal() {
        List<Animal> animais = listarAnimalUseCase.listarAnimal();
        return ResponseEntity.ok().body(animais
                .stream()
                .filter(a -> a.getAtivo().equals(Boolean.TRUE))
                .map(animalDTOMapper::domainToDto)
                .toList());
    }

    @GetMapping(value = "/{idAnimal}")
    public ResponseEntity<AnimalRespostaDTO> buscarAnimalPorId(@PathVariable UUID idAnimal) {
        return ResponseEntity.ok().body(animalDTOMapper.domainToDto(buscarAnimalPorIdService.buscarAnimalPorId(idAnimal)));
    }

    @PatchMapping(value = "/{idAnimal}")
    public ResponseEntity<Void> desativarAnimal(@PathVariable UUID idAnimal) {
        desativarAnimalUseCase.desativarAnimal(idAnimal);
        return ResponseEntity.noContent().build();
    }

}
