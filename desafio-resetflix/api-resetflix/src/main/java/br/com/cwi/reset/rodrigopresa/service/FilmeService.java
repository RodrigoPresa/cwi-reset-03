package br.com.cwi.reset.rodrigopresa.service;

import br.com.cwi.reset.rodrigopresa.FakeDatabase;
import br.com.cwi.reset.rodrigopresa.exceptions.*;
import br.com.cwi.reset.rodrigopresa.model.Ator;
import br.com.cwi.reset.rodrigopresa.model.Filme;
import br.com.cwi.reset.rodrigopresa.model.PersonagemAtor;
import br.com.cwi.reset.rodrigopresa.request.FilmeRequest;
import br.com.cwi.reset.rodrigopresa.request.PersonagemRequest;

import java.util.ArrayList;
import java.util.List;

public class FilmeService {
    private FakeDatabase fakeDatabase;
    private AtorService atorService;
    private DiretorService diretorService;
    private EstudioService estudioService;

    public FilmeService(FakeDatabase fakeDatabase) {
        this.fakeDatabase = fakeDatabase;
        this.atorService = new AtorService(FakeDatabase.getInstance());
        this.diretorService = new DiretorService(FakeDatabase.getInstance());
        this.estudioService = new EstudioService(FakeDatabase.getInstance());
    }

    public void criarFilme(FilmeRequest filmeRequest) throws Exception{
        if(filmeRequest.getNome() == null){
            throw new NomeNaoInformadoException();
        }
        if(filmeRequest.getAnoLancamento() == null){
            throw new CampoNaoInformadoException("anoLancamento");
        }
        if(filmeRequest.getCapaFilme() == null){
            throw new CampoNaoInformadoException("capaFilme");
        }
        if(filmeRequest.getGeneros() == null){
            throw new GeneroNaoInformadoException();
        }
        if(filmeRequest.getIdDiretor() == null){
            throw new IdNaoInformadoException();
        }
        if(filmeRequest.getIdEstudio() == null){
            throw new IdNaoInformadoException();
        }
        if(filmeRequest.getResumo() == null){
            throw new CampoNaoInformadoException("resumo");
        }
        if(filmeRequest.getPersonagens() == null){
            throw new CampoNaoInformadoException("personagens");
        }

        List<Filme> filmes = fakeDatabase.recuperaFilmes();

        Filme filme = new Filme(filmes.size() + 1, filmeRequest.getNome(), filmeRequest.getAnoLancamento(), filmeRequest.getCapaFilme(),
                filmeRequest.getGeneros(), filmeRequest.getIdDiretor(), filmeRequest.getIdEstudio(), filmeRequest.getResumo(),
                filmeRequest.getPersonagens());

        this.fakeDatabase.persisteFilme(filme);
    }

    public List<Filme> consultarFilmes(String nomeFilme, String nomeDiretor, String nomePersonagem, String nomeAtor) {
        return null;
    }
}
