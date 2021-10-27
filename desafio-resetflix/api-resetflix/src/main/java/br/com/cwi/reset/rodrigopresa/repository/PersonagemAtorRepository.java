package br.com.cwi.reset.rodrigopresa.repository;

import br.com.cwi.reset.rodrigopresa.model.PersonagemAtor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PersonagemAtorRepository extends CrudRepository<PersonagemAtor, Integer> {
    List<PersonagemAtor> findAll();
}
