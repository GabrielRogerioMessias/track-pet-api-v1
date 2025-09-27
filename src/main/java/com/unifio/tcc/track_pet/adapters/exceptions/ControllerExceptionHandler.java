package com.unifio.tcc.track_pet.adapters.exceptions;

import com.unifio.tcc.track_pet.application.services.exceptions.EntidadeNaoEncontradaException;
import com.unifio.tcc.track_pet.application.services.exceptions.UsuarioJaRegistratoException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> handleValidationException(MethodArgumentNotValidException ex, HttpServletRequest request) {
        List<String> errors = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach(err -> {
            errors.add(err.getDefaultMessage());
        });
        String mensagemDeErro = "Campos obrigatórios inválidos";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError standardError = new StandardError(Instant.now(),
                status.value(),
                mensagemDeErro,
                errors.toString(),
                request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(standardError);
    }

}
