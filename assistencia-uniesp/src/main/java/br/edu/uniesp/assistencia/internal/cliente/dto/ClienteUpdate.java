package br.edu.uniesp.assistencia.internal.cliente.dto;

import jakarta.validation.constraints.NotBlank;

public record ClienteUpdate(
        @NotBlank(message = "O nome é obrigatório")
        String nome
) {}
