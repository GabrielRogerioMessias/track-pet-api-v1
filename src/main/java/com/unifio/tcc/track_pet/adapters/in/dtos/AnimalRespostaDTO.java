package com.unifio.tcc.track_pet.adapters.in.dtos;

import com.unifio.tcc.track_pet.adapters.out.persistence.entities.Sexo;
import com.unifio.tcc.track_pet.adapters.out.persistence.entities.Situacao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnimalRespostaDTO {
    private UUID id;
    private String nome;
    private LocalDate dataNascimento;
    private Double peso;
    private Situacao situacao;
    private String fotoUrl;
    private String raca;
    private Sexo sexo;
    private String cor;
    private boolean ativo;
}
