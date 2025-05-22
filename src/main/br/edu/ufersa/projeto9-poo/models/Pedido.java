import java.time.LocalDate;

public class Pedido {
    private int id;
    private LocalDate data;
    private Cliente cliente;
    // TODO: adicionar o `private ProdutoPedido[] itens` depois que o ProdutoPedido for implementado
    // SE POSSIVEL: Trocar para enum no futuro
    private String pagamento;
    // SE POSSIVEL: Trocar para enum no futuro
    private String estado;

    public Pedido() {
    }

    public Pedido(int id, LocalDate data, Cliente cliente, String pagamento, String estado) {
        setId(id);
        setData(data);
        setCliente(cliente);
        setPagamento(pagamento);
        setEstado(estado);
    }

    public long precoTotal() {
        // TODO: implementar essa função depois que o ProdutoPedido for implementado
        return 0;
    }

    public void gerarNota() {
        System.out.println("Gerando nota");
    }

    public void relatorio() {
        System.out.println("Relatorio");
    }

    public void cadastrar() {
        System.out.println("Cadastrando Pedido");
    }

    public void editar() {
        System.out.println("Editando Pedido");
    }

    public void deletar() {
        System.out.println("Deletando Pedido");
    }

    public void buscar() {
        System.out.println("Buscando Pedido");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id < 0) {
            return;
        }
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        if (data == null) {
            return;
        }
        this.data = data;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        if (cliente == null) {
            return;
        }
        this.cliente = cliente;
    }

    public String getPagamento() {
        return pagamento;
    }

    public void setPagamento(String pagamento) {
        if (pagamento == null || pagamento.isEmpty()) {
            return;
        }
        this.pagamento = pagamento;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        if (estado == null || estado.isEmpty()) {
            return;
        }
        this.estado = estado;
    }
}
