package com.voll.api.user;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoUser(
    @NotNull
    Long id, 
    String nome, 
    String email, 
    String senha) {
}
