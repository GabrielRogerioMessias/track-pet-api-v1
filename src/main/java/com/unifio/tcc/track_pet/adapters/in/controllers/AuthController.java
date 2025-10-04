package com.unifio.tcc.track_pet.adapters.in.controllers;

import com.unifio.tcc.track_pet.adapters.exceptions.StandardError;
import com.unifio.tcc.track_pet.adapters.in.dtos.LoginDadosDTO;
import com.unifio.tcc.track_pet.adapters.in.dtos.UsuarioRegistrarDTO;
import com.unifio.tcc.track_pet.application.services.auth.AuthService;
import com.unifio.tcc.track_pet.domain.usuario.Usuario;
import com.unifio.tcc.track_pet.infra.security.Token;
import com.unifio.tcc.track_pet.infra.security.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Tag(name = "Autenticação", description = "Controlador para registro de novos usuários, e fazer login.")
public class AuthController {

    private final AuthService authService;
    private final TokenService tokenService;

    public AuthController(AuthService authService,
                          TokenService tokenService) {
        this.authService = authService;
        this.tokenService = tokenService;
    }

    @Operation(
            summary = "Efetuar login",
            description = "Permite fazer login, e receber o token JWT",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Login efetuado com sucesso", content = @Content(schema = @Schema(implementation = Token.class))),
                    @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content(schema = @Schema(implementation = StandardError.class))),
            }
    )
    @PostMapping("/login")
    public ResponseEntity<Token> login(@RequestBody @Valid LoginDadosDTO loginDadosDTO) {
        Token token = null;
        if (authService.login(loginDadosDTO.getEmail(), loginDadosDTO.getSenha())) {
            Usuario usuario = Usuario.builder()
                    .email(loginDadosDTO.getEmail())
                    .senha(loginDadosDTO.getSenha())
                    .build();
            token = tokenService.generateToken(usuario);
        }

        return ResponseEntity.ok(token);
    }

    @Operation(
            summary = "Salva dados do usuário",
            description = "Registra um novo usuário no sistema a partir dos dados informados",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Usuário registrado com sucesso", content = @Content),
                    @ApiResponse(responseCode = "409", description = "Usuário já cadastrado com e-mail informado", content = @Content(schema = @Schema(implementation = StandardError.class))),
                    @ApiResponse(responseCode = "400", description = "Campos obrigatórios nulos ou em inválidos", content = @Content(schema = @Schema(implementation = StandardError.class)))
            }
    )
    @PostMapping("/registrar")
    public ResponseEntity<Void> registrar(@RequestBody @Valid UsuarioRegistrarDTO request) {
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
        authService.criar(usuario);
        return ResponseEntity.ok().build();
    }

}
