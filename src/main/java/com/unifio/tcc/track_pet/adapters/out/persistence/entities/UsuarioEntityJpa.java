package com.unifio.tcc.track_pet.adapters.out.persistence.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "usuario")
public class UsuarioEntityJpa {
    @Id
    private UUID id;
    private String nome;
    private String sobrenome;
    private String email;
    private String senha;
    private String cidade;
    private String bairro;
    private String numero;
    private String telefone;

    @OneToMany(mappedBy = "usuario")
    List<AnimalEntityJpa> animals = new ArrayList<>();
}
