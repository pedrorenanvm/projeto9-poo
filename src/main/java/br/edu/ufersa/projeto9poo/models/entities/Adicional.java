package br.edu.ufersa.projeto9poo.models.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Adicional")
public class Adicional extends Item{
    public Adicional(Integer id, String nome, long preco,boolean estoque){
        super(id, nome, preco, estoque);
    }
    public void cadastrar(Integer id,String nome,long preco){
        System.out.println("Cadastrando adicional:");
        System.out.println("Nome" + nome);
        System.out.println("ID:" + id);
        System.out.println("preco:" + preco);
    }
    public void editar(Integer id,String nome,long preco){
        System.out.println("Adicional Selecionado:");
        System.out.println("Nome" + this.nome);
        System.out.println("ID:" + this.id);
        System.out.println("preco:" + this.preco);

        System.out.println("Adicional Atualizado:");
        System.out.println("Nome" + nome);
        System.out.println("ID:" + id);
        System.out.println("preco:" + preco);

        setNome(nome);
        setId(id);
        setPreco(preco);
    }

    public void deletar(String nome){
        System.out.println("Deletando Adicional " + nome);
    }

    public void buscar(String nome){
        System.out.println("Buscando Adicional " + nome);
    }
    public void setPreco(long preco){
        if (preco>0){
            super.setPreco(preco);
        } else{
            System.out.println("Preço negativo,tente novamente");
        }
    }
    public long getPreco(){
        return preco;
    }
    public String getNome(){
        return nome;
    }
    public int getId(){
        return id;
    }
    public void setId(int id){
        super.setId(id);
    }
    public void setNome(String nome){
        super.setNome(nome);
    }

}