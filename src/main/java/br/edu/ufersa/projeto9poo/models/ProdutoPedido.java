public class ProdutoPedido{
    private Produto produto;
    private Adicional adicional[];
    private int quantidade;
    private long precoUnidade;

    public ProdutoPedido(Produto produto, Adicional adicional[],int quantidade, long precoUnidade){
        this.produto = produto;
        this.adicional = adicional;
        setQuantidade(quantidade);
        setPrecoUnidade(precoUnidade);
    }

    public long precoTotal(){
        return this.quantidade*this.precoUnidade;
    }

    public void setQuantidade(int q){
        if (q>0){
            quantidade = q;
        } else{
            System.out.println("Diga uma quantidade maior que 0");
        }
    }
    public void setPrecoUnidade(long pU){
        if (pU>0){
            precoUnidade = pU;
        } else{
            System.out.println("Diga uma quantidade maior que 0");
        }
    }
    public int getQuantidade(){
        return this.quantidade;
    }
    public long getprecoUnidade(){
        return this.precoUnidade;
    }
    public void setProduto(Produto produto){
        this.produto = produto;
    }
    public Produto getProduto(){
        return produto;
    }
    public void setAdicional(Adicional[] adicional){
        this.adicional = adicional;
    }
    public Adicional[] getAdicional(){
        return this.adicional;
    }
}