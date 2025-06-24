package br.edu.ufersa.projeto9poo.models.entities;

import jakarta.persistence.*;
@Entity
@Table(name="Produto")
public class Produto extends Item {

    public Produto(Integer id, String nome, long preco, boolean estoque) {
        super(id, nome, preco, estoque);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        super.setId(id);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        super.setNome(nome);
    }

    public Long getPreco() {
        return preco;
    }

    public void setPreco(Long preco) {
        if (preco < 0) {
            System.out.println("O preço do seu produto deve ser maior que zero");
        } else {
            super.setPreco(preco);
        }
    }
    public boolean getEstoque() {return estoque;}

    public void setEstoque(boolean estoque) {super.setEstoque(estoque);}

}
