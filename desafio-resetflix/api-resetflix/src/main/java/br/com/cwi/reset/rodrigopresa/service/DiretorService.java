package br.com.cwi.reset.rodrigopresa.service;

import br.com.cwi.reset.rodrigopresa.exceptions.*;
import br.com.cwi.reset.rodrigopresa.model.Diretor;
import br.com.cwi.reset.rodrigopresa.repository.DiretorRepository;
import br.com.cwi.reset.rodrigopresa.request.DiretorRequest;
import br.com.cwi.reset.rodrigopresa.FakeDatabase;
import br.com.cwi.reset.rodrigopresa.response.AtorEmAtividade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DiretorService {

    @Autowired
    private DiretorRepository diretorRepository;
    private FakeDatabase fakeDatabase;
    private AtorEmAtividade atorEmAtividade;

    public DiretorService(FakeDatabase fakeDatabase) {
        this.fakeDatabase = fakeDatabase;
    }

    public void criarDiretor(DiretorRequest diretorRequest) throws Exception{
        if(diretorRequest.getNome() == null){
            throw new NomeNaoInformadoException();
        }
        if(diretorRequest.getDataNascimento() == null){
            throw new DataNascimentoNaoInformadoException();
        }
        if(diretorRequest.getAnoInicioAtividade() == null){
            throw new AnoInicioAtividadeNaoInformadoException();
        }
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

        List<Diretor> diretores = fakeDatabase.recuperaDiretores();

        for(Diretor diretorCadastrado : diretores){
            if (diretorCadastrado.getNome().equals(diretorRequest.getNome())){
                throw new CadastroDuplicadoException("diretor", diretorCadastrado.getNome());
            }
        }

        Diretor diretor = new Diretor(diretorRequest.getNome(), diretorRequest.getDataNascimento(),
                diretorRequest.getAnoInicioAtividade());

        this.fakeDatabase.persisteDiretor(diretor);
    }

    public Diretor consultarDiretor(Integer id) throws Exception{
        if (id == null) {
            throw new IdNaoInformadoException();
        }

        List<Diretor> diretores = fakeDatabase.recuperaDiretores();

        for(Diretor diretor : diretores){
            if(diretor.getId() == id){
                return diretor;
            }
        }

        throw new ConsultaIdInvalidoException("diretor", id);
    }

    public List<Diretor> consultarDiretores() throws Exception{
        List<Diretor> diretores = fakeDatabase.recuperaDiretores();

        if (diretores.isEmpty()) {
            throw new ListaVaziaException("ator", "atores");
        }

        return diretores;
    }

    public void printarDiretor(Diretor diretor){
        System.out.println("----------------------------------------------");
        System.out.println("nome: " + diretor.getNome());
        System.out.println("Data de Nascimento: " + diretor.getDataNascimento());
        System.out.println("Inicio da Atividade: " + diretor.getAnoInicioAtividade());
    }
}
