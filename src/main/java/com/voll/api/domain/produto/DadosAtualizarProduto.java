package com.voll.api.domain.produto;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizarProduto(
    @NotNull
    Long id,
    String nome,
    String descricao,
    Integer quantidade,
    Float valor) {
    
}
