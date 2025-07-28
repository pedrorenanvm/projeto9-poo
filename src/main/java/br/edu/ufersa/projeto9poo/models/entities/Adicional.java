package br.edu.ufersa.projeto9poo.models.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "adicionais")
public class Adicional extends Item{

    public Adicional() {}

    public Adicional(String nome, long preco, boolean estoque){
        super(nome, preco, estoque);
    }
    public void setPreco(long preco){
        if (preco>0){
            super.setPreco(preco);
        } else{
            System.out.println("Preço negativo,tente novamente");
        }
    }
    public long getPreco(){
        return super.getPreco();
    }
    public String getNome(){
        return super.getNome();
    }
    public Long getId(){
        return super.getId();
    }
    public void setId(Long id){
        super.setId(id);
    }
    public void setNome(String nome){
        super.setNome(nome);
    }
    public boolean getEstoque() {return super.isEstoque();}

    public void setEstoque(boolean estoque) {super.setEstoque(estoque);}

}