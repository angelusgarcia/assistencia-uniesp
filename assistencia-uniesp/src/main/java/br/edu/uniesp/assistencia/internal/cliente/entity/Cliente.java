package br.edu.uniesp.assistencia.internal.cliente.entity;

import br.edu.uniesp.assistencia.internal.equipamento.entity.Equipamento;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "cliente")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 150)
    private String nome;
    @Column(nullable = false, unique = true, length = 11)
    private String cpf;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private Boolean ativo = true;

    public Cliente(String nome, String email, String cpf) {
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
    }

    public void alterarNome(String nome) {
        this.nome = nome;
    }

    public void ativar () {
        this.ativo = true;
    }
    public void desativar () {
        this.ativo = false;
    }

    @OneToMany(mappedBy = "cliente")
    private List<Equipamento> equipamentos;
}