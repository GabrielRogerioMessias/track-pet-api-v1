package com.unifio.tcc.track_pet.adapters.out.files;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;

@Service
public class FileStorageService {
    private final Path uploadDir = Paths.get("uploads");

    public String salvar(MultipartFile file, String nomeBase) {
        try {
            if (!Files.exists(uploadDir)) {
                Files.createDirectories(uploadDir);
            }

            String extensao = getExtensao(file.getOriginalFilename());
            String nomeArquivo = nomeBase + extensao;
            Path destino = uploadDir.resolve(nomeArquivo);
            Files.copy(file.getInputStream(), destino, StandardCopyOption.REPLACE_EXISTING);

            return "/uploads/" + nomeArquivo;
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar arquivo: " + e.getMessage());
        }
    }

    private String getExtensao(String nomeArquivo) {
        return nomeArquivo != null && nomeArquivo.contains(".")
                ? nomeArquivo.substring(nomeArquivo.lastIndexOf("."))
                : "";
    }
}
