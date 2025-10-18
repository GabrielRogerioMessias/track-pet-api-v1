package com.unifio.tcc.track_pet.adapters.in.controllers;


import com.unifio.tcc.track_pet.adapters.in.dtos.UsuarioAtualizarDTO;
import com.unifio.tcc.track_pet.adapters.in.dtos.UsuarioRespostaDTO;
import com.unifio.tcc.track_pet.adapters.in.mappers.UsuarioDTOMapper;
import com.unifio.tcc.track_pet.domain.usecases.usuario.AtualizarDadosUsuarioUseCase;
import com.unifio.tcc.track_pet.domain.usecases.usuario.BuscarDadosUsuarioAutenticadoUseCase;
import com.unifio.tcc.track_pet.domain.usuario.Usuario;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Tag(name = "Usuário", description = "Controlador para operações relacionadas ao usuário.")
@RestController
@RequestMapping(path = "/usuario", produces = APPLICATION_JSON_VALUE)
public class UsuarioController {
    private final UsuarioDTOMapper usuarioDtoMapper;
    private final AtualizarDadosUsuarioUseCase atualizarDadosUsuarioUseCase;
    private final BuscarDadosUsuarioAutenticadoUseCase buscarDadosUsuarioAutenticadoUseCase;

    public UsuarioController(UsuarioDTOMapper usuarioDtoMapper,
                             AtualizarDadosUsuarioUseCase atualizarDadosUsuarioUseCase,
                             BuscarDadosUsuarioAutenticadoUseCase buscarDadosUsuarioAutenticadoUseCase) {
        this.usuarioDtoMapper = usuarioDtoMapper;
        this.atualizarDadosUsuarioUseCase = atualizarDadosUsuarioUseCase;
        this.buscarDadosUsuarioAutenticadoUseCase = buscarDadosUsuarioAutenticadoUseCase;
    }

    @Operation(
            summary = "Permite a atualização dos dados do usuário autenticado.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Dados do usuário atualizados com sucesso",
                            content = @Content(schema = @Schema(implementation = UsuarioRespostaDTO.class))),
                    @ApiResponse(responseCode = "403", description = "Usuário não autenticado ou token inválido")
            }
    )
    @PutMapping
    public ResponseEntity<UsuarioRespostaDTO> usuarioAutenticado(
            @RequestBody UsuarioAtualizarDTO novosDados) {
        Usuario atualizado = atualizarDadosUsuarioUseCase
                .atualizarDadosUsuario(usuarioDtoMapper.atualizarDtoToEntity(novosDados));
        return ResponseEntity.ok().body(usuarioDtoMapper.entityToDto(atualizado));
    }

    @Operation(
            summary = "Permite pegar os dados do usuário autenticado.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Retorna os dados do usuário autenticado no sistema.",
                            content = @Content(schema = @Schema(implementation = UsuarioRespostaDTO.class))),
                    @ApiResponse(responseCode = "403", description = "Usuário não autenticado ou token inválido")
            }
    )
    @GetMapping
    public ResponseEntity<UsuarioRespostaDTO> usuarioAutenticado() {
        Usuario dadosUsuarioAutenticado = buscarDadosUsuarioAutenticadoUseCase.buscarDadosUsuarioLogado();
        return ResponseEntity.ok().body(usuarioDtoMapper.entityToDto(dadosUsuarioAutenticado));
    }
}
