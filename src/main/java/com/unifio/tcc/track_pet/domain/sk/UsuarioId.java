package com.unifio.tcc.track_pet.domain.sk;

import java.util.Objects;
import java.util.UUID;

public final class UsuarioId {
    private final UUID value;

    private UsuarioId(UUID value) {
        if (value == null){
            throw new IllegalArgumentException("Usuario id não pode ser nulo.");
        }
        this.value = value;}
    public static UsuarioId genereId() {
        return new UsuarioId(UUID.randomUUID());
    }
    public static UsuarioId of(UUID value) {
        return new UsuarioId(value);
    }
    public static UsuarioId fromString(String s){
        return new UsuarioId(UUID.fromString(s));
    }
    public UUID getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioId usuarioId = (UsuarioId) o;
        return Objects.equals(value, usuarioId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
