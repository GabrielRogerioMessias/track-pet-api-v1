package com.unifio.tcc.track_pet.adapters.in.dtos;

import com.unifio.tcc.track_pet.adapters.out.persistence.entities.Sexo;
import com.unifio.tcc.track_pet.adapters.out.persistence.entities.Situacao;
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
public class AnimalRegistrarDTO {
    @NotBlank(message = "Nome é obrigatório")
    private String nome;
    private LocalDate dataNascimento;
    @Min(0)
    private Double peso;
    @Enumerated(EnumType.STRING)
    private Situacao situacao;
    private String raca;
    @Enumerated(EnumType.STRING)
    private Sexo sexo;
    @NotBlank(message = "Cor é obrigatória")
    private String cor;

}
