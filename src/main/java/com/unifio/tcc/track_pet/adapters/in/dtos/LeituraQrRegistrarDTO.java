package com.unifio.tcc.track_pet.adapters.in.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Schema(description = "Objeto de transferência de dados utilizado para o registro de uma nova leitura do QR do animal.")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeituraQrRegistrarDTO {
    @Schema(description = "Refere-se a latitude do usuário que scaneou o QR code")
    private String latitude;
    @Schema(description = "Refere-se a longitude do usuário que scaneou o QR code")
    private String longitude;
    @Schema(description = "Comporta uma mensagem do usuário que encontrou o PET")
    private String mensagem;
}
