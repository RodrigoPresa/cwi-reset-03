package br.com.cwi.reset.rodrigopresa.service;

import br.com.cwi.reset.rodrigopresa.exceptions.*;
import br.com.cwi.reset.rodrigopresa.model.Diretor;
import br.com.cwi.reset.rodrigopresa.model.Filme;
import br.com.cwi.reset.rodrigopresa.repository.DiretorRepository;
import br.com.cwi.reset.rodrigopresa.request.DiretorRequest;
import br.com.cwi.reset.rodrigopresa.response.AtorEmAtividade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class DiretorService {

    @Autowired
    private DiretorRepository diretorRepository;
    private AtorEmAtividade atorEmAtividade;
    @Autowired
    private FilmeService filmeService;

    public void criarDiretor(DiretorRequest diretorRequest) throws Exception{

        if(diretorRequest.getNome().split(" ").length < 2){
            throw new NomeSobrenomeObrigatorioException("diretor");
        }

        LocalDate dataAtual = LocalDate.now();
        if (dataAtual.isBefore(diretorRequest.getDataNascimento())){
            throw new DataNascimentoMaiorQueDataAtualException("diretores");
        }

        Integer anoNascimento = diretorRequest.getDataNascimento().getYear();
        if(diretorRequest.getAnoInicioAtividade() < anoNascimento){
            throw new AnoInicioAtividadeInvalidoException("diretor");
        }

        List<Diretor> diretores = diretorRepository.findAll();

        for(Diretor diretorCadastrado : diretores){
            if (diretorCadastrado.getNome().equals(diretorRequest.getNome())){
                throw new CadastroDuplicadoException("diretor", diretorCadastrado.getNome());
            }
        }

        Diretor diretor = new Diretor(diretorRequest.getNome(), diretorRequest.getDataNascimento(),
                diretorRequest.getAnoInicioAtividade());

        diretorRepository.save(diretor);
    }

    public Diretor consultarDiretor(Integer id) throws Exception{
        if (id == null) {
            throw new IdNaoInformadoException();
        }

        List<Diretor> diretores = diretorRepository.findAll();

        for(Diretor diretor : diretores){
            if(diretor.getId() == id){
                return diretor;
            }
        }

        return diretorRepository.findById(id).orElseThrow(() -> new ConsultaIdInvalidoException("diretor", id));
    }

    public List<Diretor> consultarDiretores() throws Exception{
        List<Diretor> diretores = diretorRepository.findAll();

        if (diretores.isEmpty()) {
            throw new ListaVaziaException("diretor", "diretores");
        }

        return diretores;
    }

    public List<Diretor> consultarDiretores(String filtroNome) throws Exception{
        List<Diretor> diretores = diretorRepository.findAll();

        if (diretores.isEmpty()) {
            throw new ListaVaziaException("diretor", "diretores");
        }

        List<Diretor> diretorFiltrado = new ArrayList<>();

        if(filtroNome != null){
            for(Diretor diretor : diretores){
                boolean contemFiltroNome = diretor.getNome().toLowerCase(Locale.ROOT).contains(filtroNome.toLowerCase(Locale.ROOT));
                if(contemFiltroNome){
                    diretorFiltrado.add(new Diretor(diretor.getNome(), diretor.getDataNascimento(),
                            diretor.getAnoInicioAtividade()));
                    return diretorFiltrado;
                } else {
                    throw new FiltroNaoEncontradoException("Diretor", filtroNome);
                }
            }
        }

        return diretores;
    }

    public void atualizarDiretor(Integer id, DiretorRequest diretorRequest) throws Exception {
        Diretor diretorAtualizado = new Diretor(diretorRequest.getNome(), diretorRequest.getDataNascimento(),
                diretorRequest.getAnoInicioAtividade());

        diretorAtualizado.setId(consultarDiretor(id).getId());

        List<Diretor> diretores = diretorRepository.findAll();

        for(Diretor diretorCadastrado : diretores){
            if (diretorCadastrado.getNome().equals(diretorAtualizado.getNome())){
                throw new CadastroDuplicadoException("diretor", diretorAtualizado.getNome());
            }
        }

        diretorRepository.save(diretorAtualizado);
    }

    public void deletarDiretor(Integer id) throws Exception {
        if (id == null) {
            throw new IdNaoInformadoException();
        }
        Diretor diretorDeletado = consultarDiretor(id);
        List<Filme> filmes = filmeService.consultarFilmes();
        for(Filme filme : filmes){
            if (filme.getDiretor().equals(diretorDeletado.getNome())){
                throw new Exception("Este diretor está vinculado a um ou mais filmes, para remover o diretor é necessário remover os seus filmes de participação.");
            }
        }

        diretorRepository.delete(diretorDeletado);
    }

}
