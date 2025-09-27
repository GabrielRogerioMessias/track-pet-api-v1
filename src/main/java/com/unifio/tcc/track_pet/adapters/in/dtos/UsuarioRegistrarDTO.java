package com.unifio.tcc.track_pet.adapters.in.dtos;

import lombok.Data;

@Data
public class UsuarioRegistrarDTO {
    private String nome;
    private String sobrenome;
    private String email;
    private String senha;
    private String cidade;
    private String bairro;
    private String numero;
    private String telefone;

}
