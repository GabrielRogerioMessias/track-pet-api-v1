package com.unifio.tcc.track_pet.adapters.out.persistence.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Arrays;

@Schema(description = "Situação do Animal.")
public enum Situacao {
    VIVO("V", "VIVO"),
    PERDIDO("P", "PERDIDO");

    private String code;
    private String descricao;

    Situacao(String code, String descricao) {
        this.code = code;
        this.descricao = descricao;
    }

    @JsonCreator
    public static Situacao getSituacaoByCode(String code) {
        return Arrays.stream(Situacao.values())
                .filter(context -> context.code.equals(code))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid Sexo"));
    }

    @JsonValue
    public String getDescricao() {
        return descricao;
    }
}
