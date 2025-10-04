package com.unifio.tcc.track_pet.adapters.in.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "DTO para realizar o login e receber o Token")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDadosDTO {
    @Schema(description = "Email para efetuar o login", example = "exemplo@exemplo.com")
    @Email(message = "Formato de e-mail inválido")
    @NotBlank(message = "O e-mail é obrigatório")
    private String email;

    @Schema(description = "Senha para efetuar o login", example = "exemplo")
    @NotBlank(message = "A senha é obrigatória")
    private String senha;
}
