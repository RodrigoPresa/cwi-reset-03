package br.com.cwi.reset.rodrigopresa.service;

import br.com.cwi.reset.rodrigopresa.exceptions.*;
import br.com.cwi.reset.rodrigopresa.model.Filme;
import br.com.cwi.reset.rodrigopresa.model.Genero;
import br.com.cwi.reset.rodrigopresa.repository.FilmeRepository;
import br.com.cwi.reset.rodrigopresa.request.FilmeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FilmeService {

    @Autowired
    private FilmeRepository filmeRepository;
    @Autowired
    private AtorService atorService;
    @Autowired
    private DiretorService diretorService;
    @Autowired
    private EstudioService estudioService;
    @Autowired
    private PersonagemAtorService personagemAtorService;

    public void criarFilme(FilmeRequest filmeRequest) throws Exception{

        final List<Filme> filmesCadastrados = filmeRepository.findAll();

        final Filme filme = new Filme(
                filmeRequest.getNome(),
                filmeRequest.getAnoLancamento(),
                filmeRequest.getCapaFilme(),
                filmeRequest.getGeneros(),
                estudioService.consultarEstudio(filmeRequest.getIdEstudio()),
                diretorService.consultarDiretor(filmeRequest.getIdDiretor()),
                personagemAtorService.cadastrarPersonagensFilme(filmeRequest.getPersonagens()),
                filmeRequest.getResumo()
        );

        if (filme.getGeneros().isEmpty()) {
            throw new Exception("Deve ser informado pelo menos um gênero para o cadastro do filme.");
        }

        final Set<Genero> generoSet = new HashSet<>();

        for (Genero genero : filme.getGeneros()) {
            if (generoSet.contains(genero)) {
                throw new Exception("Não é permitido informar o mesmo gênero mais de uma vez para o mesmo filme.");
            } else {
                generoSet.add(genero);
            }
        }

        filmeRepository.save(filme);
    }

    public List<Filme> consultarFilmes(String nomeFilme, String nomeDiretor, String nomePersonagem, String nomeAtor) throws Exception{
        final List<Filme> filmes = filmeRepository.findAll();

        if(filmes.isEmpty()){
            throw new ListaVaziaException("filme", "filmes");
        }

        final List<Filme> filtrarPorNomePersonagem = filtrarPorNomePersonagem(filmes, nomePersonagem);
        final List<Filme> filtrarPorNomeAtor = filtrarPorNomeAtor(filtrarPorNomePersonagem, nomeAtor);
        final List<Filme> filtrarPorNomeDiretor = filtrarPorNomeDiretor(filtrarPorNomeAtor, nomeDiretor);
        final List<Filme> filtroFinal = filtrarPorNomeFilme(filtrarPorNomeDiretor, nomeFilme);

        if(filtroFinal.isEmpty()){
            throw new Exception(String.format("Filme não encontrado com os filtros nomeFilme=%s, nomeDiretor=%s, nomePersonagem=%s, nomeAtor=%s, favor informar outros filtros.",
                    nomeFilme, nomeDiretor, nomePersonagem, nomeAtor));
        }

        return filtroFinal;
    }

    public List<Filme> consultarFilmes(){
        return filmeRepository.findAll();
    }

    public void deletarFilme(Integer id) throws ConsultaIdInvalidoException {

        List<Filme> filmes = filmeRepository.findAll();

        for(Filme filmeDeletado : filmes){
            if(filmeDeletado.getId().equals(id)){
                filmeRepository.delete(filmeDeletado);
            }else {
                throw new ConsultaIdInvalidoException("filme", id);
            }
        }
    }

    private List<Filme> filtrarPorNomeAtor(List<Filme> filmes, String nomeAtor) {
        if(nomeAtor == null){
            return filmes;
        }

        List<Filme> filmesFiltrados = new ArrayList<>();

        for(Filme filme : filmes){
            if(filme.getNome().toLowerCase(Locale.ROOT).contains(nomeAtor.toLowerCase(Locale.ROOT))){
                filmesFiltrados.add(filme);
                break;
            }
        }

        return filmesFiltrados;
    }

    private List<Filme> filtrarPorNomePersonagem(List<Filme> filmes, String nomePersonagem) {
        if(nomePersonagem == null){
            return filmes;
        }

        List<Filme> filmesFiltrados = new ArrayList<>();

        for(Filme filme : filmes){
            if(filme.getNome().toLowerCase(Locale.ROOT).contains(nomePersonagem.toLowerCase(Locale.ROOT))){
                filmesFiltrados.add(filme);
                break;
            }
        }

        return filmesFiltrados;
    }

    private List<Filme> filtrarPorNomeDiretor(List<Filme> filmes, String nomeDiretor) {
        if(nomeDiretor == null){
            return filmes;
        }

        List<Filme> filmesFiltrados = new ArrayList<>();

        for(Filme filme : filmes){
            if(filme.getNome().toLowerCase(Locale.ROOT).contains(nomeDiretor.toLowerCase(Locale.ROOT))){
                filmesFiltrados.add(filme);
                break;
            }
        }

        return filmesFiltrados;
    }

    private List<Filme> filtrarPorNomeFilme(List<Filme> filmes, String nomeFilme) {
        if(nomeFilme == null){
            return filmes;
        }

        List<Filme> filmesFiltrados = new ArrayList<>();

        for(Filme filme : filmes){
            if(filme.getNome().toLowerCase(Locale.ROOT).contains(nomeFilme.toLowerCase(Locale.ROOT))){
                filmesFiltrados.add(filme);
                break;
            }
        }

        return filmesFiltrados;
    }
}
