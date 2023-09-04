package com.voll.api.produto;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastrarProduto(

    @NotBlank
    String nome,

    @NotBlank
    String descricao,

    @NotBlank
    Integer quantidade,
    
    @NotBlank
    Float valor) {
    
}
