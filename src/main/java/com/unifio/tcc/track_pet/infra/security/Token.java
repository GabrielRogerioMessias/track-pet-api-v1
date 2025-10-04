package com.unifio.tcc.track_pet.infra.security;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Schema(description = "Token de acesso autenticação do usuário")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Token {
    @Schema(description = "Indica se está autenticado ou não", example = "true")
    private Boolean authenticated;
    @Schema(description = "E-mail do usuário autenticado", example = "exemplo@exemplo.com")
    private String email;
    @Schema(description = "Hora em que o token foi criado", example = "2025-10-22T00:36:25.794+00:00")
    private Date created;
    @Schema(description = "Hora de expiração do token", example = "2025-10-22T02:36:25.794+00:00")
    private Date expiration;
    @Schema(description = "Token JWT, que deve ser utilizado nas requisições", example = "")
    private String token;
}