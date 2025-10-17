package com.unifio.tcc.track_pet.adapters.in.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Schema(description = "Objeto de transferência de dados utilizado para representar dados de leituras de QR code " +
        "retornados pela API ")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeituraQrRespostaDTO {
    @Schema(description = "Refere-se ao ID da leitura", example = "25def909-7794-7c84-b954-154262d7d614")
    private UUID id;
    @Schema(description = "Refere-se ao ID do animal", example = "24def809-7696-4c43-b774-534632d7d615")
    private UUID idAnimal;
    @Schema(description = "Refere-se a latitude do usuário que scaneou o QR code", example = "-23.5505")
    private String latitude;
    @Schema(description = "Refere-se a longitude do usuário que scaneou o QR code", example = "-46.6333")
    private String longitude;
    @Schema(description = "Comporta uma mensagem do usuário que encontrou o PET", example = "Olá, encontrei seu PET aqui Avenida Silveira")
    private String mensagem;
    @Schema(description = "Refere-se a data e hora em que o QR code foi scaneado", example = "2025-10-14T22:30:00")
    private LocalDateTime dataHora;
}
