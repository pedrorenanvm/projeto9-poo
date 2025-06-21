public class Cliente {
    private Integer id;
    private String nome;
    private String endereco;
    private String telefone;

    public Cliente(Integer id, String nome, String endereco, String telefone) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
    }
    public void cadastrar(String nome, String endereco, String telefone){
        System.out.println("Cadastrando cliente: " + nome);
    }

    public void editar(Integer id, String nome, String endereco, String telefone){
        System.out.println("Atualizando cliente: " + nome);
    }

    public void deletar(Integer id, String nome){
        System.out.println("Removendo cliente: " + nome);
    }

    public void buscar(String nome){
        System.out.println("Buscando cliente: " + nome);
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

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}