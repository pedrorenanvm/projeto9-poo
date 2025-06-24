package br.edu.ufersa.projeto9poo.models.entities;

import jakarta.persistence.*;
@Entity
@Table(name="produtos")
public class Produto extends Item {

    public Produto() {}

    public Produto(Long id, String nome, long preco, boolean estoque) {
        super(id,nome, preco, estoque);
    }

    public void setPreco(Long preco) {
        if (preco < 0) {
            System.out.println("O preço do seu produto deve ser maior que zero");
        } else {
            super.setPreco(preco);
        }
    }
}
