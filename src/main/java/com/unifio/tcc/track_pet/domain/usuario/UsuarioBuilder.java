package com.unifio.tcc.track_pet.domain.usuario;

import com.unifio.tcc.track_pet.domain.sk.UsuarioId;

public class UsuarioBuilder {
    UsuarioId id = UsuarioId.genereId();
    String nome;
    String sobrenome;
    String email;
    String senha;
    String cidade;
    String bairro;
    String numero;
    String telefone;

    public UsuarioBuilder id(UsuarioId id) {
        this.id = id;
        return this;
    }

    public UsuarioBuilder nome(String nome) {
        this.nome = nome;
        return this;
    }

    public UsuarioBuilder sobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
        return this;
    }

    public UsuarioBuilder email(String email) {
        this.email = email;
        return this;
    }

    public UsuarioBuilder senha(String senha) {
        this.senha = senha;
        return this;
    }

    public UsuarioBuilder cidade(String cidade) {
        this.cidade = cidade;
        return this;
    }

    public UsuarioBuilder bairro(String bairro) {
        this.bairro = bairro;
        return this;
    }

    public UsuarioBuilder numero(String numero) {
        this.numero = numero;
        return this;
    }

    public UsuarioBuilder telefone(String telefone) {
        this.telefone = telefone;
        return this;
    }

    public Usuario build() {
        return new Usuario(this);
    }
}
