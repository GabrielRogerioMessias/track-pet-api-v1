package com.unifio.tcc.track_pet.domain.animal;

import com.unifio.tcc.track_pet.domain.exceptions.AnimalJaDesativadoException;
import com.unifio.tcc.track_pet.domain.sk.AnimalId;
import com.unifio.tcc.track_pet.domain.sk.UsuarioId;
import com.unifio.tcc.track_pet.domain.usuario.Usuario;

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
    private Boolean ativo;


    protected Animal(AnimalBuilder builder) {
        this.id = Objects.requireNonNull(builder.id, "Id de animal não pode ser nulo.");
        this.usuarioId = builder.usuarioId;
        this.nome = Objects.requireNonNull(builder.nome, "Nome não pode ser nulo.");
        this.dataNascimento = builder.dataNascimento;
        this.peso = builder.peso;
        this.situacao = builder.situacao == null ? Situacao.MORTO : builder.situacao;
        this.fotoUrl = builder.fotoUrl;
        this.raca = builder.raca;
        this.sexo = builder.sexo;
        this.cor = builder.cor;
        this.ativo = builder.ativo;
    }

    public static AnimalBuilder builder() {
        return new AnimalBuilder();
    }

    public void atualizarPeso(Double peso) {
        if (this.peso <= 0) {
            throw new IllegalArgumentException("Peso inválido");
        }
        this.peso = peso;
    }

    public void desativarAnimal() {
        if (this.ativo.equals(Boolean.FALSE)) {
            throw new AnimalJaDesativadoException("Animal já desativado com o ID: " + this.id.getValue());
        } else {
            this.ativo = Boolean.FALSE;
        }
    }

    public void ativarAnimal() {
        this.ativo = true;
    }

    public void vincularUsuario(Usuario usuario) {
        this.usuarioId = usuario.getId();
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

    public Boolean getAtivo() {
        return ativo;
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
