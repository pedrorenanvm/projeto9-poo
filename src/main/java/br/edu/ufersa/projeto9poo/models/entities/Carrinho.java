package br.edu.ufersa.projeto9poo.models.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_carrinho")
public class Carrinho {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate data;

    @ManyToOne(optional = false)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @OneToMany(mappedBy = "carrinho", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<ItemCarrinho> itens = new ArrayList<>();

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoPagamento pagamento;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoEstado estado;

    public Carrinho() {
    }

    public Carrinho(LocalDate data, Cliente cliente, TipoPagamento pagamento, TipoEstado estado) {
        setData(data);
        setCliente(cliente);
        setPagamento(pagamento);
        setEstado(estado);
    }
    public void adicionarItem(ItemCarrinho item) {
        if (item == null) {
            throw new IllegalArgumentException("O item não pode ser nulo.");
        }
        if (!this.itens.contains(item)) {

            this.itens.add(item);
            item.setCarrinho(this);
        }
    }

    public void removerItem(ItemCarrinho item) {
        if (item == null) {
            throw new IllegalArgumentException("O item não pode ser nulo.");
        }
        if (this.itens.remove(item)) {
            item.setCarrinho(null);
        }
    }

    public long precoTotal() {
        long soma = 0;
        if (itens != null) {
            for (ItemCarrinho item : itens) {
                soma += item.precoTotal();
            }
        }
        return soma;
    }


    public void gerarNota() {
        System.out.println("Gerando nota");
    }

    public void relatorio() {
        System.out.println("Relatorio");
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public List<ItemCarrinho> getItensCarrinho() {
        return itens;
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

    public List<ItemCarrinho> getItens() {
        return itens;
    }

    public void setItens(List<ItemCarrinho> novosItens) {
        if (novosItens == null) {
            throw new IllegalArgumentException("A lista de itens não pode ser nula.");
        }
        this.itens.clear();
        for (ItemCarrinho item : novosItens) {
            this.adicionarItem(item);
        }
    }

    public void setItensCarrinho(List<ItemCarrinho> itens) {
        setItens(itens);
    }

}