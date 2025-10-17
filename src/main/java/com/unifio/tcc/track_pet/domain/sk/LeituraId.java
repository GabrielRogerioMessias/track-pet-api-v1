package com.unifio.tcc.track_pet.domain.sk;

import java.util.Objects;
import java.util.UUID;

public final class LeituraId {
    private final UUID value;

    private LeituraId(UUID value) {
        if (value == null) {
            throw new IllegalArgumentException("Id da leitura não pode ser nulo.");
        }
        this.value = value;
    }

    public static LeituraId genereId() {
        return new LeituraId(UUID.randomUUID());
    }

    public static LeituraId of(UUID value) {
        return new LeituraId(value);
    }

    public UUID getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        LeituraId leituraId = (LeituraId) o;
        return Objects.equals(value, leituraId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
