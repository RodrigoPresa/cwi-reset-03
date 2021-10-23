package br.com.cwi.reset.projeto1.service;

import br.com.cwi.reset.projeto1.domain.Ator;
import br.com.cwi.reset.projeto1.repository.AtorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AtorService {

    @Autowired
    private AtorRepository atorRepository;

    public Ator salvarAtor(Ator ator){

        atorRepository.save(ator);
        return ator;
    }

    public List<Ator> buscarAtores(){
        return atorRepository.findAll();
    }

    public Ator buscarAtorPorNome(String nomeAtor){
        Ator ator = atorRepository.findByNome(nomeAtor);
        return ator;
    }

//    public Ator buscarAtorPorNumeroOscars(Integer oscars){
//        Ator ator = atorRepository.findByNumeroOscars(oscars);
//        return ator;
//    }

    public void deletarAtor(Integer id){
        atorRepository.deleteById(id);
    }
}
