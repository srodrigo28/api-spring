package com.voll.api.domain.user;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoUser(
    @NotNull
    Long id, 
    String nome, 
    String email, 
    String senha) {
}
