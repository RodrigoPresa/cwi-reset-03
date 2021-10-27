package br.com.cwi.reset.rodrigopresa.repository;

import br.com.cwi.reset.rodrigopresa.model.Estudio;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EstudioRepository extends CrudRepository<Estudio, Integer> {
    List<Estudio> findAll();
}
