package com.unifio.tcc.track_pet.adapters.in.dtos;

import com.unifio.tcc.track_pet.adapters.out.persistence.entities.Sexo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.UUID;

@Schema(description = "Objeto de resposta que representa dados de um animal retornados pela API.")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnimalEncontroDTO {
    @Schema(description = "Identificador único do animal.", example = "550e8400-e29b-41d4-a716-446655440000")
    private UUID id;

    @Schema(description = "Nome do animal.", example = "REX")
    private String nome;

    @Schema(description = "Peso do animal em quilogramas.", example = "14.5")
    private Double peso;

    @Schema(description = "URL da foto do animal.", example = "https://meuservidor.com/imagens/animais/rex.jpg")
    private String fotoUrl;

    @Schema(description = "Sexo do animal (F - FEMEA | M - MACHO)", example = "M")
    private Sexo sexo;

    @Schema(description = "Cor do animal.", example = "MARROM")
    private String cor;

}
