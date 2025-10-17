package com.unifio.tcc.track_pet.adapters.in.controllers;

import com.unifio.tcc.track_pet.adapters.in.dtos.LeituraQrRegistrarDTO;
import com.unifio.tcc.track_pet.adapters.in.dtos.LeituraQrRespostaDTO;
import com.unifio.tcc.track_pet.adapters.in.mappers.LeituraDTOMapper;
import com.unifio.tcc.track_pet.domain.qr.LeituraQr;
import com.unifio.tcc.track_pet.domain.sk.AnimalId;
import com.unifio.tcc.track_pet.domain.usecases.leituraqr.ListarLeiturasQrUseCase;
import com.unifio.tcc.track_pet.domain.usecases.leituraqr.RegistrarLeituraQrUseCase;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "leitura")
public class LeituraQrController {
    private final LeituraDTOMapper leituraDTOMapper;
    private final RegistrarLeituraQrUseCase registrarLeituraQrUseCase;
    private final ListarLeiturasQrUseCase listarLeiturasQrUseCase;

    public LeituraQrController(RegistrarLeituraQrUseCase registrarLeituraQrUseCase,
                               LeituraDTOMapper leituraDTOMapper,
                               ListarLeiturasQrUseCase listarLeiturasQrUseCase) {
        this.leituraDTOMapper = leituraDTOMapper;
        this.registrarLeituraQrUseCase = registrarLeituraQrUseCase;
        this.listarLeiturasQrUseCase = listarLeiturasQrUseCase;

    }

    @PostMapping(value = "/{idAnimal}")
    public ResponseEntity<LeituraQrRespostaDTO> registrarLeituraQr(@PathVariable UUID idAnimal,
                                                                   @RequestBody LeituraQrRegistrarDTO entity) {
        LeituraQr leituraQr = registrarLeituraQrUseCase.registarLeituraQr(leituraDTOMapper.registrarDtoToEntity(entity), idAnimal);
        System.out.println();
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(leituraDTOMapper.entityToDtoResposta(leituraQr));
    }

    @GetMapping(value = "/{idAnimal}")
    public ResponseEntity<List<LeituraQrRespostaDTO>> listarLeiturasAnimal(@PathVariable UUID idAnimal) {
        List<LeituraQr> leituraQrs = listarLeiturasQrUseCase.listar(AnimalId.of(idAnimal));
        return ResponseEntity.ok().body(leituraQrs
                .stream()
                .map(leituraDTOMapper::entityToDtoResposta)
                .toList());
    }
}
