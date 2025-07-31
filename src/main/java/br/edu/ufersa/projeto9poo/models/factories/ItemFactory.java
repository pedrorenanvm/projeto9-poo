package br.edu.ufersa.projeto9poo.models.factories;
import br.edu.ufersa.projeto9poo.models.entities.*;

public class ItemFactory {

    public static Item criarItem(String tipo,String nome, long preco, boolean estoque){
       if ("produto".equalsIgnoreCase(tipo)){
           return new Produto(nome,preco,estoque);
       } else if ("adicional".equalsIgnoreCase(tipo)){
           return new Adicional(nome,preco,estoque);
       }
       throw new IllegalArgumentException("Tipo invalido:" + tipo);
    }
}
