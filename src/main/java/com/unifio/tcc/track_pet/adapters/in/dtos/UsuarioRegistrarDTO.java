package com.unifio.tcc.track_pet.adapters.in.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "Objeto de transferencia para registro de novos usuários.")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioRegistrarDTO {
    @Schema(description = "Nome do usuário", example = "Exemplo")
    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @Schema(description = "Sobrenome do usuário", example = "Exemplo Exemplo")
    @NotBlank(message = "Sobrenome é obrigatório")
    private String sobrenome;

    @Schema(description = "Endereço de e-mail válido e único", example = "exemplo.unico@email.com")
    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Formato de e-mail inválido")
    private String email;

    @Schema(description = "Senha do usuário com no mínimo 6 e no máximo 20 caracteres", example = "ExemploSenha123")
    @NotBlank(message = "A senha é obrigatória")
    @Size(max = 20, min = 6, message = "A senha deve conter no mínimo 4 caracteres e máximo de 20")
    private String senha;

    @Schema(description = "Cidade do usuário", example = "New York")
    @NotBlank(message = "Cidade é obrigatória")
    private String cidade;

    @Schema(description = "Bairro do usuário", example = "Queens")
    @NotBlank(message = "Bairro é obrigatório")
    private String bairro;

    @Schema(description = "Número da casa do usuário", example = "177")
    @NotBlank(message = "Número é obrigatório")
    private String numero;

    @Schema(description = "Telefone do usuário", example = "5543999999999")
    @NotBlank(message = "Telefone é obrigatório")
    private String telefone;

}
