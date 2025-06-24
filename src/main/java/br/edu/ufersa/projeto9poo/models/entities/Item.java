package br.edu.ufersa.projeto9poo.models.entities;

import jakarta.persistence.*;

@MappedSuperclass
public abstract class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(nullable = false, unique = true,length = 50)
    public String nome;

    @Column(nullable = false)
    public long preco;

    @Column(nullable = false)
    public boolean estoque;

    public Item() {}

    public Item(String nome, long preco,boolean estoque) {
        this.id = id;
        this.estoque = estoque;
        this.preco = preco;
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public long getPreco() {
        return preco;
    }

    public boolean isEstoque() {
        return estoque;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPreco(long preco) {
        this.preco = preco;
    }

    public void setEstoque(boolean estoque) {
        this.estoque = estoque;
    }
}