package com.unifio.tcc.track_pet.infra.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Token {
    private Boolean authenticated;
    private String email;
    private Date created;
    private Date expiration;
    private String token;
}