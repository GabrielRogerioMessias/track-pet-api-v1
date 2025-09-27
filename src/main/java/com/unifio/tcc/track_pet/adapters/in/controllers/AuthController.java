package com.unifio.tcc.track_pet.adapters.in.controllers;

import com.unifio.tcc.track_pet.adapters.in.dtos.LoginDadosDTO;
import com.unifio.tcc.track_pet.adapters.in.dtos.UsuarioRegistrarDTO;
import com.unifio.tcc.track_pet.application.services.auth.AuthService;
import com.unifio.tcc.track_pet.domain.usuario.Usuario;
import com.unifio.tcc.track_pet.infra.security.Token;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<Token> login(@RequestBody LoginDadosDTO loginDadosDTO) {
        Token token = authService.login(loginDadosDTO);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/registrar")
    public ResponseEntity<Void> registrar(@RequestBody UsuarioRegistrarDTO request) {
        Usuario usuario = Usuario.builder()
                .nome(request.getNome())
                .sobrenome(request.getSobrenome())
                .email(request.getEmail())
                .senha(request.getSenha()) // ainda em texto claro (o UseCase vai encodar)
                .cidade(request.getCidade())
                .bairro(request.getBairro())
                .numero(request.getNumero())
                .telefone(request.getTelefone())
                .build();
        authService.registrarUsuario(usuario);
        return ResponseEntity.ok().build();
    }

}
