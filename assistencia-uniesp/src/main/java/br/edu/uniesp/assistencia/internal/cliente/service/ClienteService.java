package br.edu.uniesp.assistencia.internal.cliente.service;

import br.edu.uniesp.assistencia.internal.cliente.dto.ClienteCreate;
import br.edu.uniesp.assistencia.internal.cliente.dto.ClienteResponse;
import br.edu.uniesp.assistencia.internal.cliente.dto.ClienteUpdate;
import br.edu.uniesp.assistencia.internal.cliente.entity.Cliente;
import br.edu.uniesp.assistencia.internal.cliente.repository.ClienteRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    @Transactional
    public ClienteResponse criar(ClienteCreate request) {
        if (clienteRepository.existsByCpf(request.cpf())) {
            throw new IllegalArgumentException("CPF já cadastrado no sistema.");
        }

        Cliente cliente = new Cliente(request.nome(), request.email(), request.cpf());
        Cliente clienteSalvo = clienteRepository.save(cliente);

        return ClienteResponse.de(clienteSalvo);
    }

    @Transactional(readOnly = true)
    public Page<ClienteResponse> listarTodos(Pageable pageable) {
        return clienteRepository.findAll(pageable)
                .map(ClienteResponse::de);
    }

    @Transactional(readOnly = true)
    public ClienteResponse buscarPorId(Long id) {
        Cliente cliente = buscarEntidadePorId(id);
        return ClienteResponse.de(cliente);
    }

    @Transactional
    public ClienteResponse atualizarNome(Long id, ClienteUpdate request) {
        Cliente cliente = buscarEntidadePorId(id);
        cliente.alterarNome(request.nome());
        return ClienteResponse.de(cliente);
    }

    @Transactional
    public void ativar(Long id) {
        Cliente cliente = buscarEntidadePorId(id);
        cliente.ativar();
    }

    @Transactional
    public void desativar(Long id) {
        Cliente cliente = buscarEntidadePorId(id);
        cliente.desativar();
    }

    private Cliente buscarEntidadePorId(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado com o ID: " + id));
    }
}
