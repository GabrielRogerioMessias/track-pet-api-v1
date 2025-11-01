package com.unifio.tcc.track_pet.adapters.in.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.unifio.tcc.track_pet.adapters.exceptions.StandardError;
import com.unifio.tcc.track_pet.adapters.in.dtos.AnimalAtualizarDTO;
import com.unifio.tcc.track_pet.adapters.in.dtos.AnimalEncontroDTO;
import com.unifio.tcc.track_pet.adapters.in.dtos.AnimalRegistrarDTO;
import com.unifio.tcc.track_pet.adapters.in.dtos.AnimalRespostaDTO;
import com.unifio.tcc.track_pet.adapters.in.mappers.AnimalDTOMapper;
import com.unifio.tcc.track_pet.application.services.animal.BuscarAnimalPorIdService;
import com.unifio.tcc.track_pet.domain.animal.Animal;
import com.unifio.tcc.track_pet.domain.sk.AnimalId;
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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Tag(name = "Animal", description = "Controlador para operações relacionadas a animais")
@RestController
@RequestMapping(value = "animal", produces = APPLICATION_JSON_VALUE)
public class AnimalController {
    private BuscarAnimalPorIdUseCase buscarAnimalPorIdUseCase;
    private ListarAnimalUseCase listarAnimalUseCase;
    private DesativarAnimalUseCase desativarAnimalUseCase;
    private AtualizarDadosAnimalUseCase atualizarDadosAnimalUseCase;
    private MarcarAnimalComoPerdidoUseCase marcarAnimalComoPerdidoUseCase;
    private AnimalDTOMapper animalDTOMapper;
    private RegistrarAnimalComFotoUseCase registrarAnimalComFotoUseCase;
    private BuscarAnimalPorIDNaoAutenticadoUseCase buscarAnimalPorIDNaoAutenticadoUseCase;

    public AnimalController(
            AnimalDTOMapper animalDTOMapper,
            ListarAnimalUseCase listarAnimalUseCase,
            BuscarAnimalPorIdService buscarAnimalPorIdUseCase,
            DesativarAnimalUseCase desativarAnimalUseCase,
            AtualizarDadosAnimalUseCase atualizarDadosAnimalUseCase,
            MarcarAnimalComoPerdidoUseCase marcarAnimalComoPerdidoUseCase,
            RegistrarAnimalComFotoUseCase registrarAnimalComFotoUseCase,
            BuscarAnimalPorIDNaoAutenticadoUseCase buscarAnimalPorIDNaoAutenticadoUseCase) {
        this.animalDTOMapper = animalDTOMapper;
        this.listarAnimalUseCase = listarAnimalUseCase;
        this.buscarAnimalPorIdUseCase = buscarAnimalPorIdUseCase;
        this.desativarAnimalUseCase = desativarAnimalUseCase;
        this.atualizarDadosAnimalUseCase = atualizarDadosAnimalUseCase;
        this.marcarAnimalComoPerdidoUseCase = marcarAnimalComoPerdidoUseCase;
        this.registrarAnimalComFotoUseCase = registrarAnimalComFotoUseCase;
        this.buscarAnimalPorIDNaoAutenticadoUseCase = buscarAnimalPorIDNaoAutenticadoUseCase;
    }

    @Operation(
            summary = "Registrar um novo animal com foto (opcional).",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Animal criado com sucesso.",
                            content = @Content(schema = @Schema(implementation = AnimalRespostaDTO.class))),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida (JSON malformado ou campos ausentes).",
                            content = @Content()),
                    @ApiResponse(responseCode = "403", description = "Usuário não autenticado.", content = @Content()),
            }
    )
    @PostMapping(value = "/foto", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AnimalRespostaDTO> registrarAnimalComFoto(
            @Parameter(
                    description = "JSON contendo os dados do animal.",
                    example = "{ \"nome\": \"Tigo\", \"peso\": 15.5, \"raca\": \"vira-lata\", \"sexo\": \"F\", \"cor\": \"preto\" }"
            )
            @RequestPart("dados") String dadosJson,
            @RequestPart(value = "foto", required = false) MultipartFile foto)
            throws JsonProcessingException {

        // Converta o JSON
        ObjectMapper objectMapper = new ObjectMapper();
        AnimalRegistrarDTO dto = objectMapper.readValue(dadosJson, AnimalRegistrarDTO.class);

        Animal animal = animalDTOMapper.registrarDtoToEntity(dto);
        Animal salvo = registrarAnimalComFotoUseCase.registrarAnimalComFoto(animal, foto);
        return ResponseEntity.status(HttpStatus.CREATED).body(animalDTOMapper.domainToDto(salvo));
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
            @Valid @RequestBody AnimalAtualizarDTO novosDadosDTO,
            @Parameter(description = "Id do animal a ser atualizado.", example = "468ffe98-eff4-4947-b078-fbd3beb713a5")
            @PathVariable UUID idAnimal) {
        Animal novosDados = animalDTOMapper.atualizarDtoToEntity(novosDadosDTO);
        return ResponseEntity.ok().body(animalDTOMapper.domainToDto(atualizarDadosAnimalUseCase.atualizarDadosAnimal(novosDados, idAnimal)));
    }

    @Operation(
            summary = "Marcar um animal como perdido, através do ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Animal marcado como perdido com sucesso.",
                            content = @Content(schema = @Schema(implementation = AnimalRespostaDTO.class))),
                    @ApiResponse(responseCode = "404", description = "Animal não encontrado",
                            content = @Content(schema = @Schema(implementation = StandardError.class)))
            }
    )
    @PatchMapping(value = "/perdido/{idAnimal}")
    public ResponseEntity<AnimalRespostaDTO> marcarComoPerdido(
            @Parameter(description = "ID do animal a ser marcado como perdido.")
            @PathVariable UUID idAnimal) {
        Animal animalPerdido = marcarAnimalComoPerdidoUseCase.marcarComoPerdido(idAnimal);
        return ResponseEntity.ok().body(animalDTOMapper.domainToDto(animalPerdido));
    }

    @Operation(
            summary = "Buscar informações do animal para o módulo de encontro.",
            description = "Retorna os dados detalhados de um animal específico, utilizados para exibição em funcionalidades de 'encontro' ou perfis públicos.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Animal encontrado com o ID passado, e dados retornados com sucesso.",
                            content = @Content(schema = @Schema(implementation = AnimalEncontroDTO.class)))
            }
    )
    @GetMapping(value = "/informacoes-publicas/{idAnimal}")
    public ResponseEntity<AnimalEncontroDTO> buscarDadosParaEncontro(
            @Parameter(description = "ID do animal, que deve estar presente no QR code")
            @PathVariable UUID idAnimal) {
        Animal animal = buscarAnimalPorIDNaoAutenticadoUseCase.buscarAnimalPorIDSemTokenUseCase(AnimalId.of(idAnimal));
        return ResponseEntity.ok().body(animalDTOMapper.entityToDtoEncontro(animal));
    }
}
