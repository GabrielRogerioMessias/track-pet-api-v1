package com.unifio.tcc.track_pet.domain.animal;

import com.unifio.tcc.track_pet.domain.sk.AnimalId;
import com.unifio.tcc.track_pet.domain.sk.UsuarioId;

import java.time.LocalDate;

public class AnimalBuilder {
    AnimalId id = AnimalId.genereId();
    UsuarioId usuarioId;
    String nome;
    LocalDate dataNascimento;
    Double peso;
    Situacao situacao;
    String fotoUrl;
    String raca;
    Sexo sexo;
    String cor;
    Boolean ativo;

    public AnimalBuilder id(AnimalId id) {
        this.id = id;
        return this;
    }

    public AnimalBuilder usuarioId(UsuarioId usuarioId) {
        this.usuarioId = usuarioId;
        return this;
    }

    public AnimalBuilder nome(String nome) {
        this.nome = nome;
        return this;
    }

    public AnimalBuilder nascimento(LocalDate nascimento) {
        this.dataNascimento = nascimento;
        return this;
    }

    public AnimalBuilder peso(Double peso) {
        this.peso = peso;
        return this;
    }

    public AnimalBuilder situacao(Situacao situacao) {
        this.situacao = situacao;
        return this;
    }

    public AnimalBuilder fotoUrl(String fotoUrl) {
        this.fotoUrl = fotoUrl;
        return this;
    }

    public AnimalBuilder raca(String raca) {
        this.raca = raca;
        return this;
    }

    public AnimalBuilder sexo(Sexo sexo) {
        this.sexo = sexo;
        return this;
    }

    public AnimalBuilder cor(String cor) {
        this.cor = cor;
        return this;
    }

    public AnimalBuilder ativo(Boolean ativo) {
        this.ativo = ativo;
        return this;
    }

    public Animal build() {
        return new Animal(this);
    }
}
