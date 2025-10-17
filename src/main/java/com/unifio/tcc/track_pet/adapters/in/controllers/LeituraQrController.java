package com.unifio.tcc.track_pet.adapters.in.controllers;

import com.unifio.tcc.track_pet.adapters.exceptions.StandardError;
import com.unifio.tcc.track_pet.adapters.in.dtos.LeituraQrRegistrarDTO;
import com.unifio.tcc.track_pet.adapters.in.dtos.LeituraQrRespostaDTO;
import com.unifio.tcc.track_pet.adapters.in.mappers.LeituraDTOMapper;
import com.unifio.tcc.track_pet.domain.qr.LeituraQr;
import com.unifio.tcc.track_pet.domain.sk.AnimalId;
import com.unifio.tcc.track_pet.domain.sk.LeituraId;
import com.unifio.tcc.track_pet.domain.usecases.leituraqr.BuscarLeituraQrPorIdUseCase;
import com.unifio.tcc.track_pet.domain.usecases.leituraqr.ExcluirLeituraQrUseCase;
import com.unifio.tcc.track_pet.domain.usecases.leituraqr.ListarLeiturasQrUseCase;
import com.unifio.tcc.track_pet.domain.usecases.leituraqr.RegistrarLeituraQrUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "LeiturasQr", description = "Controlador para operações relacionadas a leituras de QR codes.")
@RestController
@RequestMapping(value = "leitura")
public class LeituraQrController {
    private final LeituraDTOMapper leituraDTOMapper;
    private final RegistrarLeituraQrUseCase registrarLeituraQrUseCase;
    private final ListarLeiturasQrUseCase listarLeiturasQrUseCase;
    private final BuscarLeituraQrPorIdUseCase buscarLeituraQrPorIdUseCase;
    private final ExcluirLeituraQrUseCase excluirLeituraQrUseCase;

    public LeituraQrController(RegistrarLeituraQrUseCase registrarLeituraQrUseCase,
                               LeituraDTOMapper leituraDTOMapper,
                               ListarLeiturasQrUseCase listarLeiturasQrUseCase,
                               BuscarLeituraQrPorIdUseCase buscarLeituraQrPorIdUseCase,
                               ExcluirLeituraQrUseCase excluirLeituraQrUseCase) {
        this.leituraDTOMapper = leituraDTOMapper;
        this.registrarLeituraQrUseCase = registrarLeituraQrUseCase;
        this.listarLeiturasQrUseCase = listarLeiturasQrUseCase;
        this.buscarLeituraQrPorIdUseCase = buscarLeituraQrPorIdUseCase;
        this.excluirLeituraQrUseCase = excluirLeituraQrUseCase;
    }

    @Operation(
            summary = "Registrar leitura de QR code e salvar dados de localização e mensagem personalizada.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Os dados da leitura são registrados com sucesso.",
                            content = @Content(schema = @Schema(implementation = LeituraQrRespostaDTO.class))),
            }
    )
    @PostMapping(value = "/{idAnimal}")
    public ResponseEntity<LeituraQrRespostaDTO> registrarLeituraQr(
            @Parameter(description = "ID do animal perdido - contido no QR da coleira")
            @PathVariable UUID idAnimal,
            @Parameter(description = "Dados da leitura, que possibilitam o rastreamento do animal.")
            @RequestBody LeituraQrRegistrarDTO entity) {
        LeituraQr leituraQr = registrarLeituraQrUseCase.registarLeituraQr(leituraDTOMapper.registrarDtoToEntity(entity), idAnimal);
        System.out.println();
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(leituraDTOMapper.entityToDtoResposta(leituraQr));
    }

    @Operation(
            summary = "Retorna todas as leituras contendo a localização e mensagens personalizadas através do ID do animal.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Uma lista com as leituras realizadas para determinado animal é retornada.",
                            content = @Content(array = @ArraySchema(schema = @Schema(implementation = LeituraQrRespostaDTO.class)))),
                    @ApiResponse(responseCode = "404", description = "Animal não encontrado com o ID passado, ou não pertence ao usuário autenticado.",
                            content = @Content(schema = @Schema(implementation = StandardError.class))),
            }
    )
    @GetMapping(value = "animal/{idAnimal}")
    public ResponseEntity<List<LeituraQrRespostaDTO>> listarLeiturasAnimal(
            @Parameter(description = "ID do animal para ver a lista de leituras efetuadas para ele.")
            @PathVariable UUID idAnimal) {
        List<LeituraQr> leituraQrs = listarLeiturasQrUseCase.listar(AnimalId.of(idAnimal));
        return ResponseEntity.ok().body(leituraQrs
                .stream()
                .map(leituraDTOMapper::entityToDtoResposta)
                .toList());
    }

    @Operation(
            summary = "Busca uma leitura pelo ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Leitura com o ID passado é encontrada e retornada com sucesso.",
                            content = @Content(schema = @Schema(implementation = LeituraQrRespostaDTO.class))),
                    @ApiResponse(responseCode = "404", description = "Leitura com o ID passado não existe, ou não pertence ao usuário autenticado.",
                            content = @Content(schema = @Schema(implementation = StandardError.class))),
            }
    )
    @GetMapping(value = "/{idLeitura}")
    public ResponseEntity<LeituraQrRespostaDTO> buscarLeituraQrPorId(
            @Parameter(description = "ID da leitura a ser buscada", example = "82cc8a63-ff18-4fb0-ac87-7018ba1b7221")
            @PathVariable UUID idLeitura) {
        return ResponseEntity.ok().body(leituraDTOMapper.
                entityToDtoResposta(buscarLeituraQrPorIdUseCase.buscarLeituraQrPorId(LeituraId.of(idLeitura))));
    }

    @Operation(
            summary = "Excluir uma leitura pelo ID.",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Leitura com o ID passado é excluída com sucesso."),
                    @ApiResponse(responseCode = "404", description = "Leitura com o ID passado não existe, ou não pertence ao usuário autenticado.",
                            content = @Content(schema = @Schema(implementation = StandardError.class))),
            }
    )
    @DeleteMapping(value = "/{idLeitura}")
    public ResponseEntity<Void> excluirLeitura(
            @Parameter(description = "ID da leitura a ser excluída", example = "82cc8a63-ff18-4fb0-ac87-7018ba1b7221")
            @PathVariable UUID idLeitura) {
        excluirLeituraQrUseCase.excluirLeitura(LeituraId.of(idLeitura));
        return ResponseEntity.noContent().build();
    }
}
