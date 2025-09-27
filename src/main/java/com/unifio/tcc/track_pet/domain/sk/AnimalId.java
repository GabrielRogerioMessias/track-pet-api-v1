package com.unifio.tcc.track_pet.domain.sk;

import java.util.Objects;
import java.util.UUID;

public final class AnimalId {
    private final UUID value;

    private AnimalId(UUID value) {
        if (value == null) {
            throw new IllegalArgumentException("Id do animal não pode ser nulo.");
        }
        this.value = value;
    }

    public static AnimalId genereId() {
        return new AnimalId(UUID.randomUUID());
    }

    public static AnimalId of(String value) {
        return new AnimalId(UUID.fromString(value));
    }

    public UUID getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        AnimalId animalId = (AnimalId) o;
        return Objects.equals(value, animalId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
