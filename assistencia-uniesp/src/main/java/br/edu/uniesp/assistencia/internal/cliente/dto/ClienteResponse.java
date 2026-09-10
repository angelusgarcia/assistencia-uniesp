package br.edu.uniesp.assistencia.internal.cliente.dto;

import br.edu.uniesp.assistencia.internal.cliente.entity.Cliente;

public record ClienteResponse(
        Long id,
        String nome,
        String cpf,
        String email,
        Boolean ativo
) {
    public static ClienteResponse de(Cliente cliente) {
        return new ClienteResponse(
                cliente.getId(),
                cliente.getNome(),
                cliente.getCpf(),
                cliente.getEmail(),
                cliente.getAtivo()
        );
    }
}
