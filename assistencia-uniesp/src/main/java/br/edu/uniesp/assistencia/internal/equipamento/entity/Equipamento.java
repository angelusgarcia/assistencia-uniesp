package br.edu.uniesp.assistencia.internal.equipamento.entity;

import br.edu.uniesp.assistencia.internal.cliente.entity.Cliente;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "equipamentos")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Equipamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String tipo;

    @Column(nullable = false, length = 100)
    private String marca;

    @Column(nullable = false, length = 100)
    private String modelo;

    @Column(name = "numero_serie", length = 100)
    private String numeroSerie;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    public Equipamento(String tipo, String marca, String modelo, String numeroSerie) {
        this.tipo = tipo;
        this.marca = marca;
        this.modelo = modelo;
        this.numeroSerie = numeroSerie;
    }

    public void associarCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}