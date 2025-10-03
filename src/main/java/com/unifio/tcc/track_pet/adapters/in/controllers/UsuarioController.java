package com.unifio.tcc.track_pet.adapters.in.controllers;


import com.unifio.tcc.track_pet.adapters.in.mappers.UsuarioDTOMapper;
import com.unifio.tcc.track_pet.application.services.usuario.CriarUsuarioService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "/usuarios", produces = APPLICATION_JSON_VALUE)
public class UsuarioController {
    private final CriarUsuarioService criarUsuarioService;
    private final UsuarioDTOMapper usuarioDtoMapper;

    public UsuarioController(CriarUsuarioService criarUsuarioService, UsuarioDTOMapper usuarioDtoMapper) {
        this.criarUsuarioService = criarUsuarioService;
        this.usuarioDtoMapper = usuarioDtoMapper;
    }

}
