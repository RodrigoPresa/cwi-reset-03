package br.com.cwi.reset.rodrigopresa.service;

import br.com.cwi.reset.rodrigopresa.FakeDatabase;
import br.com.cwi.reset.rodrigopresa.exceptions.*;
import br.com.cwi.reset.rodrigopresa.model.Ator;
import br.com.cwi.reset.rodrigopresa.model.StatusCarreira;
import br.com.cwi.reset.rodrigopresa.request.AtorRequest;
import br.com.cwi.reset.rodrigopresa.response.AtorEmAtividade;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AtorService {

    private FakeDatabase fakeDatabase;
    private AtorEmAtividade atorEmAtividade;

    public AtorService(FakeDatabase fakeDatabase) {
        this.fakeDatabase = fakeDatabase;
    }

    public void criarAtor(AtorRequest atorRequest) throws Exception{

        if(atorRequest.getNome() == null){
            throw new NomeNaoInformadoException();
        }
        if(atorRequest.getDataNascimento() == null){
            throw new DataNascimentoNaoInformadoException();
        }
        if(atorRequest.getStatusCarreira() == null){
            throw new StatusCarreiraNaoInformadoException();
        }
        if(atorRequest.getAnoInicioAtividade() == null){
            throw new AnoInicioAtividadeNaoInformadoException();
        }

        if(atorRequest.getNome().split(" ").length < 2){
            throw new NomeSobrenomeObrigatorioException("ator");
        }

        LocalDate dataAtual = LocalDate.now();
        if (dataAtual.isBefore(atorRequest.getDataNascimento())){
            throw new DataNascimentoMaiorQueDataAtualException("atores");
        }

        Integer anoNascimento = atorRequest.getDataNascimento().getYear();
        if(atorRequest.getAnoInicioAtividade() < anoNascimento){
            throw new AnoInicioAtividadeInvalidoException("ator");
        }

        List<Ator> atores = fakeDatabase.recuperaAtores();

        for(Ator atorCadastrado : atores){
            if (atorCadastrado.getNome().equals(atorRequest.getNome())){
                throw new CadastroDuplicadoException("ator", atorCadastrado.getNome());
            }
        }

        Ator ator = new Ator(atorRequest.getNome(), atorRequest.getDataNascimento(),
                atorRequest.getStatusCarreira(), atorRequest.getAnoInicioAtividade());

        this.fakeDatabase.persisteAtor(ator);
    }

    public List<AtorEmAtividade> listarAtoresEmAtividade(String filtroNome) throws Exception{
        List<Ator> atoresCadastrados = fakeDatabase.recuperaAtores();
        if(atoresCadastrados.isEmpty()){
            throw new ListaVaziaException("ator", "atores");
        }

        List<AtorEmAtividade> atorEmAtividade = new ArrayList<>();

        if(filtroNome != null){
            for(Ator ator : atoresCadastrados){
                boolean contemFiltroNome = ator.getNome().toLowerCase(Locale.ROOT).contains(filtroNome.toLowerCase(Locale.ROOT));
                boolean emAtividade = StatusCarreira.EM_ATIVIDADE.equals(ator.getStatusCarreira());
                if(contemFiltroNome && emAtividade){
                    atorEmAtividade.add(new AtorEmAtividade(ator.getId(), ator.getNome(), ator.getDataNascimento()));
                }
            }
        } else{
            for(Ator ator : atoresCadastrados){
                boolean emAtividade = StatusCarreira.EM_ATIVIDADE.equals(ator.getStatusCarreira());
                if(emAtividade){
                    atorEmAtividade.add(new AtorEmAtividade(ator.getId(), ator.getNome(), ator.getDataNascimento()));
                }
            }
        }

        if(atorEmAtividade.isEmpty()){
            throw new FiltroNaoEncontradoException("Ator", filtroNome);
        }

        return atorEmAtividade;
    }

    public Ator consultarAtor(Integer id) throws Exception{
        if (id == null) {
            throw new IdNaoInformadoException();
        }

        List<Ator> atores = fakeDatabase.recuperaAtores();
        for(Ator ator : atores){
            if(ator.getId() == id){
                return ator;
            }
        }
        throw new ConsultaIdInvalidoException("ator", id);
    }

    public List<Ator> consultarAtores() throws Exception{
        List<Ator> atores = fakeDatabase.recuperaAtores();

        if (atores.isEmpty()) {
            throw new ListaVaziaException("ator", "atores");
        }

        return atores;
    }

    public void printarAtor(Ator ator){
        System.out.println("----------------------------------------------");
        System.out.println("nome: " + ator.getNome());
        System.out.println("Data de Nascimento: " + ator.getDataNascimento());
        System.out.println("Status: " + ator.getStatusCarreira());
        System.out.println("Inicio da Atividade: " + ator.getAnoInicioAtividade());
    }

    public void printarAtorEmAtividade(AtorEmAtividade ator){
        System.out.println("----------------------------------------------");
        System.out.println(String.format("Ator em atividade filtrado: %s", ator.getNome()));
        System.out.println("nome: " + ator.getNome());
        System.out.println("Data de Nascimento: " + ator.getDataNascimento());
    }
}