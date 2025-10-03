package com.unifio.tcc.track_pet.domain.sk;

import java.util.Objects;

public final class LeituraId {
    private final Long value;

    private LeituraId(Long id) {
        this.value = id;
    }

    public static LeituraId of(Long value) {
        if (value == null || value <= 0) {
            throw new IllegalArgumentException("ID da Leitura deve ser um número positivo");
        }
        return new LeituraId(value);
    }

    public Long getValue() {
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
