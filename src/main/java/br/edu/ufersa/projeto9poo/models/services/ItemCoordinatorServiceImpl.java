package br.edu.ufersa.projeto9poo.models.services;

import br.edu.ufersa.projeto9poo.models.entities.Item;
import br.edu.ufersa.projeto9poo.models.entities.Produto;
import br.edu.ufersa.projeto9poo.models.entities.Adicional;
import br.edu.ufersa.projeto9poo.models.factories.ItemFactory;
import br.edu.ufersa.projeto9poo.models.repositories.AdicionalRepositoryImpl;
import br.edu.ufersa.projeto9poo.models.repositories.ProdutoRepositoryImpl;

import java.util.ArrayList;
import java.util.List;

public class ItemCoordinatorServiceImpl implements ItemCoodinatorService{
    private final ItemFactory itemFactory;
    private final ProdutoService produtoService;
    private final AdicionalService adicionalService;

    public ItemCoordinatorServiceImpl() {
        this(
                new ItemFactory(),
                new ProdutoServiceImpl(),
                new AdicionalServiceImpl()
        );
    }

    public ItemCoordinatorServiceImpl(ItemFactory itemFactory, ProdutoService produtoService, AdicionalService adicionalService) {
        this.itemFactory = itemFactory;
        this.produtoService = produtoService;
        this.adicionalService = adicionalService;
    }

    @Override
    public void cadastrar(String tipo, String nome, long preco, boolean estoque) {
        Item item = itemFactory.criarItem(tipo,nome,preco,estoque);
        if (item instanceof Produto){
            produtoService.cadastrar((Produto) item);
        } else if (item instanceof Adicional){
            adicionalService.cadastrar((Adicional) item );
        }
    }

    public void editar(Item item) {
        if (item instanceof Produto) {
            produtoService.editar((Produto) item);
        } else if (item instanceof Adicional) {
            adicionalService.editar((Adicional) item);
        }
    }

    public void deletar(Item item) {
        if (item instanceof Produto) {
            produtoService.deletar((Produto) item);
        } else if (item instanceof Adicional) {
            adicionalService.deletar((Adicional) item);
        }
    }

    @Override
    public List<Item> buscarTodos(String item) {
        List<Item> todosItens = new ArrayList<>();
        todosItens.addAll(produtoService.buscarTodos(item));
        todosItens.addAll(adicionalService.buscarTodos(item));
        return todosItens;
    }

    public List<Item> buscarTodos() {
        List<Item> todosItens = new ArrayList<>();
        todosItens.addAll(produtoService.buscarTodos());
        todosItens.addAll(adicionalService.buscarTodos());
        return todosItens;
    }

}
