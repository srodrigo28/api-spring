package com.voll.api.domain.medico;

import com.voll.api.domain.endereco.DadosEndereco;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroMedico(
        
        @NotBlank
        String nome,

        @Email
        String email,
        
        @NotBlank
        String telefone,

        @NotBlank
        // @Pattern(regexp = "\\d{4-6}")
        String crm,

        @NotNull
        Especialidade especialidade,

        @Valid
        @NotNull
        DadosEndereco endereco) {
}
