package com.unifio.tcc.track_pet.adapters.out.persistence.entities;

import com.unifio.tcc.track_pet.domain.animal.Sexo;
import com.unifio.tcc.track_pet.domain.animal.Situacao;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
@Table(name = "animal")
public class AnimalEntityJpa {
    @Id
    private UUID id;
    private String nome;
    private LocalDate dataNascimento;
    private Double peso;
    private Situacao situacao;
    private String fotoUrl;
    private String raca;
    private Sexo sexo;
    private String cor;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private UsuarioEntityJpa usuario;

}
