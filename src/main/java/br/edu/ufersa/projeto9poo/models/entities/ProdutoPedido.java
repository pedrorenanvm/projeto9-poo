package br.edu.ufersa.projeto9poo.models.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name= "produto_pedido")

public class ProdutoPedido{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional=false)
    @JoinColumn(name ="produto_id")
    private Produto produto;

    @ManyToMany
    @JoinTable(
            name = "produto_pedido_adicional",
            joinColumns = @JoinColumn(name = "produto_pedido_id"),
            inverseJoinColumns = @JoinColumn(name = "adicional_id")
    )
    private List<Adicional> adicionais;

    @ManyToOne(optional=false)
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    @Column(nullable = false)
    private int quantidade;

    @Column(nullable = false)
    private long precoUnidade;

    public ProdutoPedido(){}

    public ProdutoPedido(Produto produto, List<Adicional> adicionais, int quantidade, long precoUnidade){
        this.produto = produto;
        this.adicionais = adicionais;
        setQuantidade(quantidade);
        setPrecoUnidade(precoUnidade);
    }
    // TODO: Verificar se o método deve ser assim mesmo
    public long precoTotal(){
        return this.quantidade*this.precoUnidade;
    }

    public Long getId() {
        return id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public List<Adicional> getAdicionais() {
        return adicionais;
    }

    public void setAdicionais(List<Adicional> adicionais) {
        this.adicionais = adicionais;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        if (quantidade > 0) {
            this.quantidade = quantidade;
        } else {
            throw new IllegalArgumentException("A quantidade deve ser maior que zero.");
        }
    }

    public long getPrecoUnidade() {
        return precoUnidade;
    }

    public void setPrecoUnidade(long precoUnidade) {
        if (precoUnidade > 0) {
            this.precoUnidade = precoUnidade;
        } else {
            throw new IllegalArgumentException("O preço unitário deve ser maior que zero.");
        }
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

}