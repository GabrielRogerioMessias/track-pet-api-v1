package com.unifio.tcc.track_pet.adapters.in.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UsuarioRegistrarDTO {
    @NotBlank(message = "Nome é obrigatório")
    private String nome;
    @NotBlank(message = "Sobrenome é obrigatório")
    private String sobrenome;
    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Formato de e-mail inválido")
    private String email;
    @NotBlank(message = "A senha é obrigatória")
    @Size(max = 20, min = 6, message = "A senha deve conter no mínimo 4 caracteres e máximo de 20")
    private String senha;
    @NotBlank(message = "Cidade é obrigatória")
    private String cidade;
    @NotBlank(message = "Bairro é obrigatório")
    private String bairro;
    @NotBlank(message = "Númeor é obrigatório")
    private String numero;
    @NotBlank(message = "Telefone é obrigatório")
    private String telefone;

}
