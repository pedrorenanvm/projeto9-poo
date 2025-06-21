package br.edu.ufersa.projeto9poo.models;

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

    @OneToMany(fetch = FetchType.LAZY)
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

    public void cadastrar() {
        System.out.println("Cadastrando Pedido");
    }

    public void editar() {
        System.out.println("Editando Pedido");
    }

    public void deletar() {
        System.out.println("Deletando Pedido");
    }

    public void buscar() {
        System.out.println("Buscando Pedido");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id < 0) {
            return;
        }
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        if (data == null) {
            return;
        }
        this.data = data;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        if (cliente == null) {
            return;
        }
        this.cliente = cliente;
    }

    public ProdutoPedido[] getItens() {
        return itens;
    }

    public void setItens(ProdutoPedido[] itens) {
        if (itens == null || itens.length == 0) {
            return;
        }
        for (ProdutoPedido item : itens) {
            if (item == null) {
                return;
            }
        }
        this.itens = itens;
    }

    public TipoPagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(TipoPagamento pagamento) {
        if (pagamento == null) {
            return;
        }
        this.pagamento = pagamento;
    }

    public TipoEstado getEstado() {
        return estado;
    }

    public void setEstado(TipoEstado estado) {
        if (estado == null) {
            return;
        }
        this.estado = estado;
    }
}
