package com.unifio.tcc.track_pet.adapters.in.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "Objeto de transferência utilizado para atualizar dados de um usuário existente.")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioAtualizarDTO {
    @Schema(description = "Nome do usuário", example = "Exemplo")
    private String nome;
    @Schema(description = "Sobrenome do usuário", example = "Exemplo Exemplo")
    private String sobrenome;
    @Schema(description = "Cidade do usuário", example = "New York")
    private String cidade;
    @Schema(description = "Bairro do usuário", example = "Queens")
    private String bairro;
    @Schema(description = "Número da casa do usuário", example = "177")
    private String numero;
    @Schema(description = "Telefone do usuário", example = "5543999999999")
    private String telefone;
}
