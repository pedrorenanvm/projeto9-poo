package br.edu.ufersa.projeto9poo.models.services;

import br.edu.ufersa.projeto9poo.models.entities.Adicional;
import br.edu.ufersa.projeto9poo.models.repositories.AdicionalRepository;
import br.edu.ufersa.projeto9poo.models.repositories.AdicionalRepositorylmpl;

import java.util.List;

public class AdicionalServicelmpl implements AdicionalServicer{

    private final AdicionalRepository repository = new AdicionalRepositorylmpl();

    public void cadastrar(Adicional adicional){
        if (repository.buscar(adicional.nome) != null) {
            throw new IllegalArgumentException("Produto já existente.");
        }
        repository.cadastrar(adicional);
    }
    public void editar(Adicional adicional){
        if (repository.buscar(adicional.nome) == null) {
            throw new IllegalArgumentException("Produto inexistente");
        }
        Adicional a = repository.buscar(adicional.getId());
        a.setNome(adicional.getNome());
        a.setId(adicional.getId());
        a.setPreco(adicional.getPreco());
        a.setEstoque(adicional.getEstoque());

        repository.editar(a);
    }

    public void deletar(int id){
         Adicional a = repository.buscar(id);
        if (a==null){
            throw new IllegalArgumentException("O id não existe");
        } else {
            repository.deletar(a);
        }
    }

    @Override
    public Adicional buscar(int id) {
        return repository.buscar(id);
    }
    public List<Adicional> buscarTodos(){
        return repository.buscarTodos();
    }
}
