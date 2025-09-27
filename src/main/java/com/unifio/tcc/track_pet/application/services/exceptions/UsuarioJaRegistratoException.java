package com.unifio.tcc.track_pet.application.services.exceptions;

public class UsuarioJaRegistratoException extends RuntimeException {
    public UsuarioJaRegistratoException(String menssage) {
        super(menssage + " já registrado");
    }
}
