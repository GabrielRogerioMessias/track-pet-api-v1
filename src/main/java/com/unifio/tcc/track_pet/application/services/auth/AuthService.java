package com.unifio.tcc.track_pet.application.services.auth;

import com.unifio.tcc.track_pet.adapters.in.dtos.LoginDadosDTO;
import com.unifio.tcc.track_pet.application.services.exceptions.EntidadeNaoEncontradaException;
import com.unifio.tcc.track_pet.application.services.exceptions.UsuarioJaRegistratoException;
import com.unifio.tcc.track_pet.domain.repositories.UsuarioDomainRepository;
import com.unifio.tcc.track_pet.domain.sk.UsuarioId;
import com.unifio.tcc.track_pet.domain.usecases.usuario.CriarUsuarioUseCase;
import com.unifio.tcc.track_pet.domain.usecases.usuario.LoginUsuarioUseCase;
import com.unifio.tcc.track_pet.domain.usuario.Usuario;
import com.unifio.tcc.track_pet.infra.security.Token;
import com.unifio.tcc.track_pet.infra.security.TokenService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements CriarUsuarioUseCase, LoginUsuarioUseCase {
    private final UsuarioDomainRepository usuarioRepository;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UsuarioDomainRepository usuarioRepository, TokenService tokenService, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.tokenService = tokenService;
        this.passwordEncoder = passwordEncoder;
    }

    public Token login(LoginDadosDTO login) {
        Usuario usuario = usuarioRepository.buscarUsuarioEmail(login.getEmail()).orElseThrow(() -> new EntidadeNaoEncontradaException("Usuário não encontrado com e-mail: " + login.getEmail()));
        if (passwordEncoder.matches(login.getSenha(), usuario.getSenha())) {
            return this.tokenService.generateToken(usuario);
        }
        return null;
    }

    @Override
    public Boolean login(String email, String login) {
        Usuario usuario = usuarioRepository.buscarUsuarioEmail(email).orElseThrow(() -> new EntidadeNaoEncontradaException("Usuário não encontrado com e-mail: " + email));
        return passwordEncoder.matches(login, usuario.getSenha());
    }

    @Override
    public void criar(Usuario usuario) {
        if (usuarioRepository.buscarUsuarioEmail(usuario.getEmail()).isPresent()) {
            throw new UsuarioJaRegistratoException(usuario.getEmail());
        }
        Usuario usuarioRegistrado = Usuario.builder()
                .id(UsuarioId.genereId())
                .nome(usuario.getNome())
                .sobrenome(usuario.getSobrenome())
                .email(usuario.getEmail())
                .senha(passwordEncoder.encode(usuario.getSenha()))
                .cidade(usuario.getCidade())
                .bairro(usuario.getBairro())
                .numero(usuario.getNumero())
                .telefone(usuario.getTelefone())
                .build();
        usuarioRepository.salvar(usuarioRegistrado);
    }


}
