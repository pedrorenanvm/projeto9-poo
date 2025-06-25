package br.edu.ufersa.projeto9poo.app;

import br.edu.ufersa.projeto9poo.models.entities.*;
import br.edu.ufersa.projeto9poo.models.services.*;
import br.edu.ufersa.projeto9poo.models.utils.JPAUtil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main2 {
    public static void main(String[] args) {
        // Cria serviços
        AdicionalServicer adicionalServicer = new AdicionalServicelmpl();
        CarrinhoService carrinhoService = new CarrinhoServiceImpl();
        ClienteService clienteService = new ClienteServiceImpl();
        FuncionarioService funcionarioService = new FuncionarioServiceImpl();
        ProdutoService produtoService = new ProdutoServicelmpl();

        // cadastrar

        Funcionario funcionario = new Funcionario("jose", "senha123");
        funcionarioService.cadastrar(funcionario);

        Cliente cliente = new Cliente("Ana", "1234546456423", "5584912345678");
        clienteService.cadastrar(cliente);

        Adicional adicional = new Adicional("morango", 2000, true);
        adicionalServicer.cadastrar(adicional);

        Produto produto = new Produto("Açai", 5000, true);
        produtoService.cadastrar(produto);

        Carrinho carrinho = new Carrinho(LocalDate.now(), cliente, TipoPagamento.PIX, TipoEstado.CONCLUIDO);
        List<Adicional> adicionais = new ArrayList<>();
        adicionais.add(adicional);
        ItemCarrinho item = new ItemCarrinho(produto, adicionais, 3, 8000);
        item.setCarrinho(carrinho);
        List<ItemCarrinho> itens = new ArrayList<>();
        itens.add(item);
        carrinho.setItensCarrinho(itens);
        carrinhoService.cadastrar(carrinho);

        // editar

        funcionario.setSenha("novasenha");
        funcionarioService.trocarSenha(funcionario);

        cliente.setNome("Anna");
        clienteService.editar(cliente);

        adicional.setPreco(3000);
        adicionalServicer.editar(adicional);

        produto.setPreco(10000);
        produtoService.editar(produto);

        carrinho.setPagamento(TipoPagamento.DINHEIRO);
        item.setQuantidade(10);
        carrinhoService.editar(carrinho);


        // deletar

        carrinhoService.deletar(carrinho);
        produtoService.deletar(produto);
        adicionalServicer.deletar(adicional);
        clienteService.deletar(cliente);
        funcionarioService.deletar(funcionario);

        JPAUtil.shutdown();
    }
}
