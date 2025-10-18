package com.unifio.tcc.track_pet.adapters.in.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Schema(description = "Objeto de transferência para dados do usuário autenticado.")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioRespostaDTO {
    @Schema(description = "Id do usuário", example = "25def909-7794-7c84-b954-154262d7d614")
    private UUID id;
    @Schema(description = "Nome do usuário", example = "Deitona")
    private String nome;
    @Schema(description = "Sobrenome do usuário", example = "Ralphi")
    private String sobrenome;
    @Schema(description = "Cidade do usuário", example = "New York")
    private String cidade;
    @Schema(description = "Bairro do usuário", example = "Albuquerque")
    private String bairro;
    @Schema(description = "Número da casa do usuário", example = "150")
    private String numero;
    @Schema(description = "Telefone do usuário", example = "43999999999")
    private String telefone;
}
