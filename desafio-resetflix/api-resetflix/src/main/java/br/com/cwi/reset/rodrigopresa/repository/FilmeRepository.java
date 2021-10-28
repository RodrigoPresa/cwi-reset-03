package br.com.cwi.reset.rodrigopresa.repository;

import br.com.cwi.reset.rodrigopresa.model.Filme;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FilmeRepository extends CrudRepository<Filme, Integer> {
    List<Filme> findAll();
}
