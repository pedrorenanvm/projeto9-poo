package br.edu.ufersa.projeto9poo.models.services;

import br.edu.ufersa.projeto9poo.models.entities.Adicional;

import java.util.List;

public interface AdicionalServicer {
    public void cadastrar(Adicional adicional);

    public void editar(Adicional adicional);

    public void deletar(int id);

    public Adicional buscar(int id);

    public List<Adicional> buscarTodos();
}

