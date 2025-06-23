package br.edu.ufersa.projeto9poo.models.entities;

import jakarta.persistence.*

@Entity
public abstract class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDEENTITY)
    private Integer id;

    @Collumn(nullable = false, unique = true,length = 50)
    private String nome;

    @Collumn(nullable = false)
    private long preco;

    @Collumn(nullable = false)
    private boolean estoque;

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