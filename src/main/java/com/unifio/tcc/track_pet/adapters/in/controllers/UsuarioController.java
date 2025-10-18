package com.unifio.tcc.track_pet.adapters.in.controllers;


import com.unifio.tcc.track_pet.adapters.in.dtos.UsuarioAtualizarDTO;
import com.unifio.tcc.track_pet.adapters.in.dtos.UsuarioRespostaDTO;
import com.unifio.tcc.track_pet.adapters.in.mappers.UsuarioDTOMapper;
import com.unifio.tcc.track_pet.domain.usecases.usuario.AtualizarDadosUsuarioUseCase;
import com.unifio.tcc.track_pet.domain.usuario.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "/usuario", produces = APPLICATION_JSON_VALUE)
public class UsuarioController {
    private final UsuarioDTOMapper usuarioDtoMapper;
    private final AtualizarDadosUsuarioUseCase atualizarDadosUsuarioUseCase;

    public UsuarioController(UsuarioDTOMapper usuarioDtoMapper,
                             AtualizarDadosUsuarioUseCase atualizarDadosUsuarioUseCase) {
        this.usuarioDtoMapper = usuarioDtoMapper;
        this.atualizarDadosUsuarioUseCase = atualizarDadosUsuarioUseCase;
    }

    @PutMapping
    public ResponseEntity<UsuarioRespostaDTO> usuarioAutenticado(
            @RequestBody UsuarioAtualizarDTO novosDados) {
        Usuario atualizado = atualizarDadosUsuarioUseCase
                .atualizarDadosUsuario(usuarioDtoMapper.atualizarDtoToEntity(novosDados));
        return ResponseEntity.ok().body(usuarioDtoMapper.entityToDto(atualizado));
    }
}
