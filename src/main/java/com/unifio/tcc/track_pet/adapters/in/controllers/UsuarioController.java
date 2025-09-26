package com.unifio.tcc.track_pet.adapters.in.controllers;

import com.unifio.tcc.track_pet.adapters.in.dtos.UsuarioRegistrarDto;
import com.unifio.tcc.track_pet.adapters.in.mappers.UsuarioDtoMapper;
import com.unifio.tcc.track_pet.application.services.CriarUsuarioService;
import com.unifio.tcc.track_pet.domain.usecases.usuario.CriarUsuarioUseCase;
import com.unifio.tcc.track_pet.domain.usuario.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "/usuarios", produces = APPLICATION_JSON_VALUE)
public class UsuarioController {
    private final CriarUsuarioService criarUsuarioService;
    private final UsuarioDtoMapper usuarioDtoMapper;

    public UsuarioController(CriarUsuarioService criarUsuarioService, UsuarioDtoMapper usuarioDtoMapper) {
        this.criarUsuarioService = criarUsuarioService;
        this.usuarioDtoMapper = usuarioDtoMapper;
    }

    @PostMapping
    public ResponseEntity<Void> registrar(@RequestBody UsuarioRegistrarDto request) {
        criarUsuarioService.criar(
                new CriarUsuarioUseCase.criarUsuarioCommand(
                        request.getNome(),
                        request.getSobrenome(),
                        request.getEmail(),
                        request.getSenha(),
                        request.getCidade(),
                        request.getBairro(),
                        request.getNumero(),
                        request.getTelefone()
                )
        );
        return ResponseEntity.ok().build();
    }

}
