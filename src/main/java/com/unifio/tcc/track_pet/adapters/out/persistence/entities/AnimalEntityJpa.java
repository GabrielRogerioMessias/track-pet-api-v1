package com.unifio.tcc.track_pet.adapters.out.persistence.entities;


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
    @Enumerated(EnumType.STRING)
    private Situacao situacao;
    private String fotoUrl;
    private String raca;
    @Enumerated(EnumType.STRING)
    private Sexo sexo;
    private String cor;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private UsuarioEntityJpa usuario;

}
