package br.edu.ufersa.projeto9poo.models.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "pedido")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private LocalDate data;

    @ManyToOne(optional = false)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private ProdutoPedido[] itens;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoPagamento pagamento;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoEstado estado;

    public Pedido() {
    }

    public Pedido(int id, LocalDate data, Cliente cliente, TipoPagamento pagamento, TipoEstado estado) {
        setId(id);
        setData(data);
        setCliente(cliente);
        setPagamento(pagamento);
        setEstado(estado);
    }

    public long precoTotal() {
        long soma = 0;
        for (ProdutoPedido item : itens) {
            soma += item.precoTotal();
        }
        return soma;
    }

    public void gerarNota() {
        System.out.println("Gerando nota");
    }

    public void relatorio() {
        System.out.println("Relatorio");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id < 0) {
            throw new IllegalArgumentException("O id não pode ser negativo");
        }
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        if (data == null) {
            throw new IllegalArgumentException("A data não pode ser nulo");
        }
        this.data = data;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("O cliente não pode ser nulo");
        }
        this.cliente = cliente;
    }

    public ProdutoPedido[] getItens() {
        return itens;
    }

    public void setItens(ProdutoPedido[] itens) {
        if (itens == null || itens.length == 0) {
            throw new IllegalArgumentException("O itens não pode está vazio");
        }
        for (ProdutoPedido item : itens) {
            if (item == null) {
                throw new IllegalArgumentException("O item não pode ser nulo");
            }
        }
        this.itens = itens;
    }

    public TipoPagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(TipoPagamento pagamento) {
        if (pagamento == null) {
            throw new IllegalArgumentException("O pagamento não pode ser nulo");
        }
        this.pagamento = pagamento;
    }

    public TipoEstado getEstado() {
        return estado;
    }

    public void setEstado(TipoEstado estado) {
        if (estado == null) {
            throw new IllegalArgumentException("O estado não pode ser nulo");
        }
        this.estado = estado;
    }
}
