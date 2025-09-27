package com.unifio.tcc.track_pet.adapters.in.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDadosDTO {
    private String email;
    private String senha;
}
