package com.unifio.tcc.track_pet.adapters.exceptions;

import com.unifio.tcc.track_pet.application.services.exceptions.EntidadeNaoEncontradaException;
import com.unifio.tcc.track_pet.application.services.exceptions.UsuarioJaRegistratoException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(UsuarioJaRegistratoException.class)
    public ResponseEntity<StandardError> usuarioJaCadastradoException(UsuarioJaRegistratoException ex, HttpServletRequest request) {
        String mensagemDeErro = "Erro de cadastro";
        HttpStatus status = HttpStatus.CONFLICT;
        StandardError standardError = new StandardError(Instant.now(),
                status.value(),
                mensagemDeErro,
                ex.getMessage(),
                request.getRequestURI());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(standardError);
    }

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<StandardError> entidadeNaoEncontradaException(EntidadeNaoEncontradaException ex, HttpServletRequest request) {
        String mensagemDeErro = "Entidade não encontrada!";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError standardError = new StandardError(Instant.now(),
                status.value(),
                mensagemDeErro,
                ex.getMessage(),
                request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(standardError);
    }

}
