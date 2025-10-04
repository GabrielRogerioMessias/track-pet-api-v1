package com.unifio.tcc.track_pet.adapters.in.controllers;

import com.unifio.tcc.track_pet.adapters.exceptions.StandardError;
import com.unifio.tcc.track_pet.adapters.in.dtos.AnimalRegistrarDTO;
import com.unifio.tcc.track_pet.adapters.in.dtos.AnimalRespostaDTO;
import com.unifio.tcc.track_pet.adapters.in.mappers.AnimalDTOMapper;
import com.unifio.tcc.track_pet.application.services.animal.BuscarAnimalPorIdService;
import com.unifio.tcc.track_pet.application.services.animal.RegistrarAnimalService;
import com.unifio.tcc.track_pet.domain.animal.Animal;
import com.unifio.tcc.track_pet.domain.usecases.animal.BuscarAnimalPorIdUseCase;
import com.unifio.tcc.track_pet.domain.usecases.animal.DesativarAnimalUseCase;
import com.unifio.tcc.track_pet.domain.usecases.animal.ListarAnimalUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Tag(name = "Animal", description = "Controlador para operações relacionadas a animais")
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

    @Operation(
            summary = "Registrar um novo animal/pet",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Animal registrado com sucesso",
                            content = @Content(schema = @Schema(implementation = AnimalRespostaDTO.class))),
                    @ApiResponse(responseCode = "400", description = "Dados de registro inválidos",
                            content = @Content(schema = @Schema(implementation = StandardError.class)))
            }
    )
    @PostMapping
    public ResponseEntity<AnimalRespostaDTO> registrarAnimal(@RequestBody AnimalRegistrarDTO dto) {
        Animal animal = registrarAnimalService.registrarAnimal(animalDTOMapper.registrarDtoToEntity(dto));
        return ResponseEntity.status(HttpStatus.CREATED).body(animalDTOMapper.domainToDto(animal));
    }

    @Operation(
            summary = "Listar todos os animais do usuário autenticado",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista dos animais ATIVOS do usuário autenticado",
                            content = @Content(array = @ArraySchema(schema = @Schema(implementation = AnimalRespostaDTO.class))))
            }
    )
    @GetMapping
    public ResponseEntity<List<AnimalRespostaDTO>> listarAnimal() {
        List<Animal> animais = listarAnimalUseCase.listarAnimal();
        return ResponseEntity.ok().body(animais
                .stream()
                .filter(a -> a.getAtivo().equals(Boolean.TRUE))
                .map(animalDTOMapper::domainToDto)
                .toList());
    }

    @Operation(
            summary = "Buscar um animal por ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Animal é encontrado e retornado com sucesso",
                            content = @Content(schema = @Schema(implementation = AnimalRespostaDTO.class))),
                    @ApiResponse(responseCode = "404", description = "Animal não encontrado",
                            content = @Content(schema = @Schema(implementation = StandardError.class)))
            }
    )
    @GetMapping(value = "/{idAnimal}")
    public ResponseEntity<AnimalRespostaDTO> buscarAnimalPorId(
            @Parameter(description = "ID do animal", example = "468ffe98-eff4-4947-b078-fbd3beb713a5")
            @PathVariable UUID idAnimal) {
        return ResponseEntity.ok().body(animalDTOMapper.domainToDto(buscarAnimalPorIdService.buscarAnimalPorId(idAnimal)));
    }

    @Operation(
            summary = "Desativar/excluir um animal",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Animal desativado com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Animal não encontrado",
                            content = @Content(schema = @Schema(implementation = StandardError.class)))
            }
    )
    @PatchMapping(value = "/{idAnimal}")
    public ResponseEntity<Void> desativarAnimal(
            @Parameter(description = "ID do animal a ser excluído/desativado", example = "468ffe98-eff4-4947-b078-fbd3beb713a5")
            @PathVariable UUID idAnimal) {
        desativarAnimalUseCase.desativarAnimal(idAnimal);
        return ResponseEntity.noContent().build();
    }

}
