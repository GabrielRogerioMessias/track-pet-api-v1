package com.unifio.tcc.track_pet.adapters.out.persistence.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

import java.util.Arrays;

public enum Sexo {
    MACHO("M", "MACHO"), FEMEA("F", "FEMEA");
    private final String code;
    @Getter
    private final String descricao;

    Sexo(String code, String descricao) {
        this.code = code;
        this.descricao = descricao;
    }

    @JsonCreator
    public static Sexo fromCode(String code) {
        return Arrays.stream(Sexo.values())
                .filter(context -> context.code.equals(code))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid Sexo"));
    }

    @JsonValue
    public String getCode() {
        return code;
    }
}
