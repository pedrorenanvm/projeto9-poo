package br.edu.ufersa.projeto9poo.models.entities;

import jakarta.persistence.*;

@Entity
public abstract class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @Column(nullable = false, unique = true,length = 50)
    public String nome;

    @Column(nullable = false)
    public long preco;

    @Column(nullable = false)
    public boolean estoque;

    public Item(Integer id, String nome, long preco,boolean estoque) {
        this.estoque = estoque;
        this.preco = preco;
        this.nome = nome;
        this.id = id;
    }

    public void setId(Integer id) {
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