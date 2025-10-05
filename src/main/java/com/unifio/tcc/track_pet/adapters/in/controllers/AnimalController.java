package com.unifio.tcc.track_pet.adapters.in.controllers;

import com.unifio.tcc.track_pet.adapters.exceptions.StandardError;
import com.unifio.tcc.track_pet.adapters.in.dtos.AnimalRegistrarDTO;
import com.unifio.tcc.track_pet.adapters.in.dtos.AnimalRespostaDTO;
import com.unifio.tcc.track_pet.adapters.in.mappers.AnimalDTOMapper;
import com.unifio.tcc.track_pet.application.services.animal.BuscarAnimalPorIdService;
import com.unifio.tcc.track_pet.application.services.animal.RegistrarAnimalService;
import com.unifio.tcc.track_pet.domain.animal.Animal;
import com.unifio.tcc.track_pet.domain.usecases.animal.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
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
    private BuscarAnimalPorIdUseCase buscarAnimalPorIdUseCase;
    private ListarAnimalUseCase listarAnimalUseCase;
    private RegistrarAnimalUseCase registrarAnimalUseCase;
    private DesativarAnimalUseCase desativarAnimalUseCase;
    private AtualizarDadosAnimalUseCase atualizarDadosAnimalUseCase;
    private AnimalDTOMapper animalDTOMapper;

    public AnimalController(RegistrarAnimalService registrarAnimalUseCase,
                            AnimalDTOMapper animalDTOMapper,
                            ListarAnimalUseCase listarAnimalUseCase,
                            BuscarAnimalPorIdService buscarAnimalPorIdUseCase,
                            DesativarAnimalUseCase desativarAnimalUseCase,
                            AtualizarDadosAnimalUseCase atualizarDadosAnimalUseCase) {
        this.registrarAnimalUseCase = registrarAnimalUseCase;
        this.animalDTOMapper = animalDTOMapper;
        this.listarAnimalUseCase = listarAnimalUseCase;
        this.buscarAnimalPorIdUseCase = buscarAnimalPorIdUseCase;
        this.desativarAnimalUseCase = desativarAnimalUseCase;
        this.atualizarDadosAnimalUseCase = atualizarDadosAnimalUseCase;

    }

    @Operation(
            summary = "Registrar um novo animal/pet.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Animal registrado com sucesso.",
                            content = @Content(schema = @Schema(implementation = AnimalRespostaDTO.class))),
                    @ApiResponse(responseCode = "400", description = "Dados de registro inválidos.",
                            content = @Content(schema = @Schema(implementation = StandardError.class)))
            }
    )
    @PostMapping
    public ResponseEntity<AnimalRespostaDTO> registrarAnimal(
            @Parameter(description = "Objeto de transferencia com os dados atualizados do animal.")
            @Valid @RequestBody AnimalRegistrarDTO dto) {
        Animal animal = registrarAnimalUseCase.registrarAnimal(animalDTOMapper.registrarDtoToEntity(dto));
        return ResponseEntity.status(HttpStatus.CREATED).body(animalDTOMapper.domainToDto(animal));
    }

    @Operation(
            summary = "Listar todos os animais do usuário autenticado.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista dos animais ATIVOS do usuário autenticado.",
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
            summary = "Buscar um animal por ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Animal é encontrado e retornado com sucesso.",
                            content = @Content(schema = @Schema(implementation = AnimalRespostaDTO.class))),
                    @ApiResponse(responseCode = "404", description = "Animal não encontrado.",
                            content = @Content(schema = @Schema(implementation = StandardError.class)))
            }
    )
    @GetMapping(value = "/{idAnimal}")
    public ResponseEntity<AnimalRespostaDTO> buscarAnimalPorId(
            @Parameter(description = "ID do animal", example = "468ffe98-eff4-4947-b078-fbd3beb713a5")
            @PathVariable UUID idAnimal) {
        return ResponseEntity.ok().body(animalDTOMapper.domainToDto(buscarAnimalPorIdUseCase.buscarAnimalPorId(idAnimal)));
    }

    @Operation(
            summary = "Desativar/excluir um animal.",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Animal desativado com sucesso."),
                    @ApiResponse(responseCode = "404", description = "Animal não encontrado.",
                            content = @Content(schema = @Schema(implementation = StandardError.class)))
            }
    )
    @PatchMapping(value = "/{idAnimal}")
    public ResponseEntity<Void> desativarAnimal(
            @Parameter(description = "ID do animal a ser excluído/desativado.", example = "468ffe98-eff4-4947-b078-fbd3beb713a5")
            @PathVariable UUID idAnimal) {
        desativarAnimalUseCase.desativarAnimal(idAnimal);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Atualizar dados de um animal existente.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Dados do animal atualizado com sucesso.",
                            content = @Content(schema = @Schema(implementation = AnimalRespostaDTO.class))),
                    @ApiResponse(responseCode = "400", description = "Dados para atualiza inválidos.",
                            content = @Content(schema = @Schema(implementation = StandardError.class)))
            }

    )
    @PutMapping(value = "/{idAnimal}")
    public ResponseEntity<AnimalRespostaDTO> atualizarAnimal(
            @Parameter(description = "Objeto de transferencia com os dados atualizados do animal.")
            @Valid @RequestBody AnimalRegistrarDTO novosDadosDTO,
            @Parameter(description = "Id do animal a ser atualizado.", example = "468ffe98-eff4-4947-b078-fbd3beb713a5")
            @PathVariable UUID idAnimal) {
        Animal novosDados = animalDTOMapper.registrarDtoToEntity(novosDadosDTO);
        return ResponseEntity.ok().body(animalDTOMapper.domainToDto(atualizarDadosAnimalUseCase.atualizarDadosAnimal(novosDados, idAnimal)));
    }
}
