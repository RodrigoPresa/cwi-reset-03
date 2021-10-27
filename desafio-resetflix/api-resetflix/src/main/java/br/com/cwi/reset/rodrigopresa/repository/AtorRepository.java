package br.com.cwi.reset.rodrigopresa.repository;

import br.com.cwi.reset.rodrigopresa.model.Ator;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AtorRepository extends CrudRepository<Ator, Integer> {
    List<Ator> findAll();
}
