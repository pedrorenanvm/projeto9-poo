package br.edu.ufersa.projeto9poo.models.entities;

import jakarta.persistence.*;
@Entity
@Table(name="Produto")
public class Produto extends Item{

    public Produto(Integer id, String nome, long preco){
        super(id);
        super(nome);
        super(preco);
    }

    public void cadastrar(String nome, Long preco){
        System.out.println("Cadastrando produto: " + nome);
    }

    public void editar(Integer id,String nome, Long preco){
        System.out.println("Atualizando produto: " + nome);
    }

    public void deletar(Integer id, String nome){
        System.out.println("Removendo produto: " + nome);
    }

    public void buscar(String nome){
        System.out.println("Buscando produto: " + nome);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        super(id);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) super(nome);
    }

    public Long getPreco() {
        return preco;
    }

    public void setPreco(Long preco) {
        if (preco < 0) {
            System.out.println("O preço do seu produto deve ser maior que zero");
        }else {
            super(preco);
        }
    }
}