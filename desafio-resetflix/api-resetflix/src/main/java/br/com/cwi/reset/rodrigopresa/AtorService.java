package br.com.cwi.reset.rodrigopresa;

import java.util.ArrayList;
import java.util.List;

public class AtorService {

    private FakeDatabase fakeDatabase;
    private AtorEmAtividade atorEmAtividade;

    public AtorService(FakeDatabase fakeDatabase) {
        this.fakeDatabase = fakeDatabase;
    }

    public void criarAtor(AtorRequest atorRequest){
        List<Ator> atores = fakeDatabase.recuperaAtores();
        Ator ator = new Ator(atores.size() + 1, atorRequest.getNome(), atorRequest.getDataNascimento(),
                atorRequest.getStatusCarreira(), atorRequest.getAnoInicioAtividade());
        this.fakeDatabase.persisteAtor(ator);
    }

    public List<AtorEmAtividade> listarAtoresEmAtividade(String filtroNome){
        return atorEmAtividade.recuperaAtorEmAtividade();
    }

    public Ator consultarAtor(Integer id){
        List<Ator> atores = fakeDatabase.recuperaAtores();
        for(Ator ator : atores){
            if(ator.getId() == id){
                return ator;
            }
        }
        return null;
    }

    public List<Ator> consultarAtores(){
        List<Ator> atores = fakeDatabase.recuperaAtores();

        return atores;
    }

    public void printarAtor(Ator ator){
        System.out.println("----------------------------------------------");
        System.out.println("nome: " + ator.getNome());
        System.out.println("Data de Nascimento: " + ator.getDataNascimento());
        System.out.println("Status: " + ator.getStatusCarreira());
        System.out.println("Inicio da Atividade: " + ator.getAnoInicioAtividade());
    }
}