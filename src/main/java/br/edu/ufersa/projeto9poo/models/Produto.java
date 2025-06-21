package br.edu.ufersa.projeto9poo.models;

public class Produto {

    private Integer id;
    private String nome;
    private Long preco;

    public Produto(Integer id, String nome, Long preco) {
        this.id = id;
        this.nome = nome;
        setPreco(preco);
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
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getPreco() {
        return preco;
    }

    public void setPreco(Long preco) {
        if (preco < 0) {
            System.out.println("O preço do seu produto deve ser maior que zero");
        }else {
            this.preco = preco;
        }
    }
}