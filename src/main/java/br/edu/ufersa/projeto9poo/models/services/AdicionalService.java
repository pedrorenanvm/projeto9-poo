package br.edu.ufersa.projeto9poo.models.services;

import br.edu.ufersa.projeto9poo.models.entities.Adicional;

import java.util.List;

public interface AdicionalService {
    void cadastrar(Adicional adicional);

    void editar(Adicional adicional);

    void deletar(Adicional adicional);

    List<Adicional> buscarTodos(String adicional);

    List<Adicional> buscarTodos();
}

