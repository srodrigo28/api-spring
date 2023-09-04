package com.voll.api.cliente;

public record DadosListaCliente(
    Long id,
    String nome,
    String email) {

    public DadosListaCliente(Cliente cliente){
        this(
            cliente.getId(),
            cliente.getNome(),
            cliente.getEmail()
        );
    }
    
}
