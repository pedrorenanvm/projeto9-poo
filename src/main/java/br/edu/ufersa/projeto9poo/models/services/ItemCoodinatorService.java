package br.edu.ufersa.projeto9poo.models.services;
import br.edu.ufersa.projeto9poo.models.entities.Item;
import java.util.List;


public interface ItemCoodinatorService {
    void cadastrar(String tipo, String nome, long preco, boolean estoque);

    void editar(Item item);

    void deletar(Item item);

    List<Item> buscarTodos(String item);

    List<Item> buscarTodos();
}


