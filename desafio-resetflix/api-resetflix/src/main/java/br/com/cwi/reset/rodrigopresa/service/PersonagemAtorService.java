package br.com.cwi.reset.rodrigopresa.service;

import br.com.cwi.reset.rodrigopresa.FakeDatabase;
import br.com.cwi.reset.rodrigopresa.exceptions.*;
import br.com.cwi.reset.rodrigopresa.model.Ator;
import br.com.cwi.reset.rodrigopresa.model.PersonagemAtor;
import br.com.cwi.reset.rodrigopresa.request.PersonagemRequest;
import br.com.cwi.reset.rodrigopresa.validator.PersonagemRequestCamposObrigatoriosValidator;

import java.util.*;

public class PersonagemAtorService {
    private FakeDatabase fakeDatabase;
    private AtorService atorService;

    public PersonagemAtorService(FakeDatabase fakeDatabase) {
        this.fakeDatabase = fakeDatabase;
        this.atorService = new AtorService(fakeDatabase);
    }

    public PersonagemAtor cadastrarPersonagemAtor(PersonagemRequest personagemRequest) throws Exception {
        if(personagemRequest.getIdAtor() == null){
            throw new IdNaoInformadoException();
        }
        if(personagemRequest.getNomePersonagem() == null){
            throw new NomeNaoInformadoException();
        }
        if(personagemRequest.getDescricaoPersonagem() == null){
            throw new DescricaoNaoInformadaException();
        }
        if(personagemRequest.getTipoAtuacao() == null){
            throw new CampoNaoInformadoException("tipoAtuacao");
        }

        final Ator ator = atorService.consultarAtor(personagemRequest.getIdAtor());

        final PersonagemAtor personagemAtor = new PersonagemAtor(ator, personagemRequest.getNomePersonagem(), personagemRequest.getDescricaoPersonagem(), personagemRequest.getTipoAtuacao());

        this.fakeDatabase.persistePersonagem(personagemAtor);

        return personagemAtor;
    }

    public List<PersonagemAtor> consultarPersonagemAtor(String nome) throws Exception {
        return fakeDatabase.recuperaPersonagens();
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
