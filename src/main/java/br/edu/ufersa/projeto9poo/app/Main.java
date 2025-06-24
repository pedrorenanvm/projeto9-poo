package br.edu.ufersa.projeto9poo.app;

import br.edu.ufersa.projeto9poo.models.entities.*;
import br.edu.ufersa.projeto9poo.models.repositories.*;
import br.edu.ufersa.projeto9poo.models.services.*;
import br.edu.ufersa.projeto9poo.models.utils.JPAUtil;

import java.time.LocalDate;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        // Cria serviços
        ClienteService cs = new ClienteServiceImpl();
        ProdutoPedidoService ps = new ProdutoPedidoServiceImpl();
        // Pode criar também PedidoService

        // 1) Cria cliente
        Cliente cliente = new Cliente("Ana", "8577777777", "Rua A, 123");
        cs.cadastrar(cliente);

        // 2) Cria pedido
        Pedido pedido = new Pedido();
        pedido.setData(LocalDate.now());
        pedido.setCliente(cliente);
        pedido.setPagamento(TipoPagamento.PIX);
        pedido.setEstado(TipoEstado.EM_ANDAMENTO);

        // Persistência do pedido
        PedidoRepository pr = new PedidoRepositoryImpl();
        pr.cadastrar(pedido);

        // 3) Itens
        Produto produto = new Produto("Açai 300ml", 1200L, true);
        Adicional ad1 = new Adicional("Granola", 200L, true);
        Adicional ad2 = new Adicional("Leite em pó", 300L, true);

        ProdutoPedido item = new ProdutoPedido(produto, Arrays.asList(ad1, ad2), 2, 1700L);
        item.setPedido(pedido);
        ps.cadastrar(item);

        // Atualiza pedido
        pedido.setItens(Arrays.asList(item));
        pr.editar(pedido);

        // 4) Saída
        System.out.println("Total do pedido: " + pedido.precoTotal());

        // Encerra JPA
        JPAUtil.shutdown();
    }
}
