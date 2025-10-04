package com.unifio.tcc.track_pet.domain.exceptions;

public class AnimalJaDesativadoException extends RuntimeException {
    public AnimalJaDesativadoException(String message) {
        super(message);
    }
}
