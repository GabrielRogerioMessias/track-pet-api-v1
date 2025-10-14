package com.unifio.tcc.track_pet.adapters.in.dtos;

import com.unifio.tcc.track_pet.adapters.out.persistence.entities.Sexo;
import com.unifio.tcc.track_pet.adapters.out.persistence.entities.Situacao;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnimalAtualizarDTO {
    @Schema(description = "Nome do animal.", example = "Rex")
    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @Schema(description = "Data de nascimento do animal (formato ISO-8601).", example = "2020-05-14")
    private LocalDate dataNascimento;

    @Schema(description = "Peso do animal em quilogramas. Deve ser um valor positivo.", example = "12.5", minimum = "0")
    @Min(value = 0, message = "O peso deve ser maior que 0")
    private Double peso;

    @Schema(description = "Situação atual do animal(V - VIVO | P - PERDIDO).", example = "V")
    @Enumerated(EnumType.STRING)
    private Situacao situacao;

    @Schema(description = "Raça do animal.", example = "Labrador Retriever")
    private String raca;

    @Schema(description = "Sexo do animal (F - FEMEA | M - MACHO).", example = "M")
    @Enumerated(EnumType.STRING)
    private Sexo sexo;

    @Schema(description = "Cor predominante do animal.", example = "Caramelo", required = true)
    @NotBlank(message = "Cor é obrigatória")
    private String cor;

}
