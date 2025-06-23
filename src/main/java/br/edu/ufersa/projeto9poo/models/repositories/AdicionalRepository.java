package br.edu.ufersa.projeto9poo.models.repositories;

import br.edu.ufersa.projeto9poo.models.entities.Adicional;

import java.util.List;


public interface AdicionalRepository {
    public void cadastrar(Adicional adicional);

    public void editar(Adicional adicional);

    public void deletar(Adicional adicional);

    public Adicional buscar(int id);

    public Adicional buscar(String nome);

    public List<Adicional> buscar();
}
