package com.voll.api.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DadosCadastroUser(

        @NotBlank
        String nome,
        
        @Email
        String email,

        @NotBlank
        String senha
        
    ) {

      public DadosCadastroUser(User user) {
        this(
            user.getNome(),
            user.getEmail(),
            user.getSenha()
        );
    }
    
}
