package com.voll.api.domain.cliente;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DadosCadastrarCliente(

    @NotBlank
    String nome,

    @Email
    String email

) {

    public DadosCadastrarCliente(Cliente cliente){
        this(
            cliente.getNome(),
            cliente.getEmail()
        );
    }
    
}
