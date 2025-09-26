package com.unifio.tcc.track_pet.domain.usuario;

import com.unifio.tcc.track_pet.domain.sk.UsuarioId;

import java.util.Objects;

public class Usuario {
    private final UsuarioId id;
    private String nome;
    private String sobrenome;
    private String email;
    private String senha;
    private String cidade;
    private String bairro;
    private String numero;
    private String telefone;

    protected Usuario(UsuarioBuilder b) {
        this.id = Objects.requireNonNull(b.id, "id não pode ser nulo");
        this.nome = b.nome;
        this.sobrenome = b.sobrenome;
        this.email = b.email;
        this.senha = b.senha;
        this.cidade = b.cidade;
        this.bairro = b.bairro;
        this.numero = b.numero;
        this.telefone = b.telefone;
    }

    public static UsuarioBuilder builder() {
        return new UsuarioBuilder();
    }

    public UsuarioId getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public String getCidade() {
        return cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public String getNumero() {
        return numero;
    }

    public String getTelefone() {
        return telefone;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(id, usuario.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
