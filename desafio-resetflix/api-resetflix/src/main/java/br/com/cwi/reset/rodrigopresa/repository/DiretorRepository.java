package br.com.cwi.reset.rodrigopresa.repository;

import br.com.cwi.reset.rodrigopresa.model.Diretor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DiretorRepository extends CrudRepository<Diretor, Integer> {
    List<Diretor> findAll();
}
