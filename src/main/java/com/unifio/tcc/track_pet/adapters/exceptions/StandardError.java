package com.unifio.tcc.track_pet.adapters.exceptions;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Schema(description = "Modelo padrão de resposta para exceções e erros da API")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StandardError {
    @Schema(description = "Momento em que o erro ocorreu (em formato UTC ISO 8601)", example = "2025-05-01T14:33:22.123Z")
    private Instant timestamp;
    @Schema(description = "Código HTTP correspondente ao erro", example = "404")
    private Integer status;
    @Schema(description = "Tipo genérico de erro ocorrido", example = "Entidade não encontrado")
    private String error;
    @Schema(description = "Mensagem detalhada da exceção para auxiliar no debug", example = "Entidade com ID 10 não encontrada, Campos vazios ou nulos")
    private String message;
    @Schema(description = "Caminho (URL) da requisição que gerou o erro", example = "/api/exemplo/1")
    private String path;
}
