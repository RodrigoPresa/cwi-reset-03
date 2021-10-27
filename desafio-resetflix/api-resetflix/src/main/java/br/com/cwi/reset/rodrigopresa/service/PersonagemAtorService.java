package br.com.cwi.reset.rodrigopresa.service;

import br.com.cwi.reset.rodrigopresa.exceptions.*;
import br.com.cwi.reset.rodrigopresa.model.Ator;
import br.com.cwi.reset.rodrigopresa.model.PersonagemAtor;
import br.com.cwi.reset.rodrigopresa.repository.PersonagemAtorRepository;
import br.com.cwi.reset.rodrigopresa.request.PersonagemRequest;
import br.com.cwi.reset.rodrigopresa.validator.PersonagemRequestCamposObrigatoriosValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PersonagemAtorService {

    @Autowired
    private PersonagemAtorRepository personagemAtorRepository;
    @Autowired
    private AtorService atorService;

    public PersonagemAtor cadastrarPersonagemAtor(PersonagemRequest personagemRequest) throws Exception {

        final Ator ator = atorService.consultarAtor(personagemRequest.getIdAtor());

        final PersonagemAtor personagemAtor = new PersonagemAtor(ator, personagemRequest.getNomePersonagem(), personagemRequest.getDescricaoPersonagem(), personagemRequest.getTipoAtuacao());

        personagemAtorRepository.save(personagemAtor);

        return personagemAtor;
    }

    public List<PersonagemAtor> consultarPersonagemAtor(String nome) throws Exception {
        List<PersonagemAtor> personagens = personagemAtorRepository.findAll();

        if (personagens.isEmpty()) {
            throw new ListaVaziaException("personagem", "personagens");
        }

        List<PersonagemAtor> personagemFiltrado = new ArrayList<>();


        if(nome != null){
            for(PersonagemAtor personagem : personagens){
                boolean contemFiltroNome = personagem.getNomePersonagem().toLowerCase(Locale.ROOT).contains(nome.toLowerCase(Locale.ROOT));
                if(contemFiltroNome){
                    personagemFiltrado.add(new PersonagemAtor(atorService.consultarAtor(personagem.getAtor().getId()), personagem.getNomePersonagem(),
                            personagem.getDescricaoPersonagem(), personagem.getTipoAtuacao()));
                    return personagemFiltrado;
                } else {
                    throw new FiltroNaoEncontradoException("personagem", nome);
                }
            }
        }
        return personagens;
    }

    public List<PersonagemAtor> consultarPersonagens(){
        return personagemAtorRepository.findAll();
    }

    private void validarPersonagensAtoresFilme(final List<PersonagemRequest> personagens) throws Exception {

        final Set<PersonagemRequest> personagemRequestSet = new HashSet<>();

        for (PersonagemRequest personagemRequest : personagens) {
            new PersonagemRequestCamposObrigatoriosValidator().accept(personagemRequest);

            if (personagemRequestSet.contains(personagemRequest)) {
                throw new Exception("Não é permitido informar o mesmo ator/personagem mais de uma vez para o mesmo filme.");
            } else {
                personagemRequestSet.add(personagemRequest);
            }
        }
    }

    public List<PersonagemAtor> cadastrarPersonagensFilme(final List<PersonagemRequest> personagens) throws Exception {
        validarPersonagensAtoresFilme(personagens);

        final List<PersonagemAtor> personagensAtores = new ArrayList<>();

        for (PersonagemRequest request : personagens) {
            personagensAtores.add(cadastrarPersonagemAtor(request));
        }

        return personagensAtores;
    }
}
