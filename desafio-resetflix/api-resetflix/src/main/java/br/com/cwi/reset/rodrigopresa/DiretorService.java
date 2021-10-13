package br.com.cwi.reset.rodrigopresa;

import java.util.List;

public class DiretorService {
    private FakeDatabase fakeDatabase;
    private AtorEmAtividade atorEmAtividade;

    public DiretorService(FakeDatabase fakeDatabase) {
        this.fakeDatabase = fakeDatabase;
    }

    public void criarDiretor(DiretorRequest diretorRequest){
        List<Diretor> diretores = fakeDatabase.recuperaDiretores();
        Diretor diretor = new Diretor(diretores.size() + 1, diretorRequest.getNome(), diretorRequest.getDataNascimento(),
                diretorRequest.getAnoInicioAtividade());
        this.fakeDatabase.persisteDiretor(diretor);
    }

    public Diretor consultarDiretor(Integer id){
        List<Diretor> diretores = fakeDatabase.recuperaDiretores();
        for(Diretor diretor : diretores){
            if(diretor.getId() == id){
                return diretor;
            }
        }
        return null;
    }

    public List<Diretor> consultarDiretores(){
        List<Diretor> diretores = fakeDatabase.recuperaDiretores();

        return diretores;
    }

    public void printarDiretor(Diretor diretor){
        System.out.println("----------------------------------------------");
        System.out.println("nome: " + diretor.getNome());
        System.out.println("Data de Nascimento: " + diretor.getDataNascimento());
        System.out.println("Inicio da Atividade: " + diretor.getAnoInicioAtividade());
    }
}
