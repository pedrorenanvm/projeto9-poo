package br.edu.ufersa.projeto9poo.models.repositories;

import br.edu.ufersa.projeto9poo.models.entities.Adicional;

import java.util.List;
import java.util.Optional;


public interface AdicionalRepository {
    void cadastrar(Adicional adicional);

    void editar(Adicional adicional);

    void deletar(Adicional adicional);

    Optional<Adicional> buscarPorNome(Adicional adicional);

    Optional<Adicional> buscarPorId(long id);

    Optional<Adicional> buscarPorPreco(Adicional adicional);

    List<Adicional> buscarTodos();
}
