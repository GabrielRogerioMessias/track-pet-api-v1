package com.unifio.tcc.track_pet.domain.animal;

import com.unifio.tcc.track_pet.domain.sk.AnimalId;
import com.unifio.tcc.track_pet.domain.sk.UsuarioId;

import java.time.LocalDate;
import java.util.Objects;

public class Animal {
    private AnimalId id;
    private UsuarioId usuarioId;
    private String nome;
    private LocalDate dataNascimento;
    private Double peso;
    private Situacao situacao;
    private String fotoUrl;
    private String raca;
    private Sexo sexo;
    private String cor;


    protected Animal(AnimalBuilder builder) {
        this.id = Objects.requireNonNull(builder.id, "Id de animal não pode ser nulo.");
        this.usuarioId = Objects.requireNonNull(builder.usuarioId, "usuarioId não pode ser nulo.");
        this.nome = Objects.requireNonNull(builder.nome, "Nome não pode ser nulo.");
        this.dataNascimento = builder.dataNascimento;
        this.peso = builder.peso;
        this.situacao = builder.situacao == null ? Situacao.MORTO : builder.situacao;
        this.fotoUrl = builder.fotoUrl;
        this.raca = builder.raca;
        this.sexo = builder.sexo;
        this.cor = builder.cor;
    }

    public static AnimalBuilder builder() {
        return new AnimalBuilder();
    }

    public AnimalId getId() {
        return id;
    }

    public UsuarioId getUsuarioId() {
        return usuarioId;
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public Double getPeso() {
        return peso;
    }

    public Situacao getSituacao() {
        return situacao;
    }

    public String getFotoUrl() {
        return fotoUrl;
    }

    public String getRaca() {
        return raca;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public String getCor() {
        return cor;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return Objects.equals(id, animal.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
