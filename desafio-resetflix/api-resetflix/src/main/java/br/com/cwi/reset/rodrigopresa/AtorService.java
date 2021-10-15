package br.com.cwi.reset.rodrigopresa;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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

//    public void criarAtor(AtorRequest atorRequest) throws Exception{
//        if(atorRequest.getNome() == null){
//            throw new Exception("Campo obrigatório não informado. Favor informar o campo nome");
//        }
//        if(atorRequest.getDataNascimento() == null){
//            throw new Exception("Campo obrigatório não informado. Favor informar o campo dataNascimento");
//        }
//        if(atorRequest.getStatusCarreira() == null){
//            throw new Exception("Campo obrigatório não informado. Favor informar o campo dataNascimento");
//        }
//        if(atorRequest.getAnoInicioAtividade() == null){
//            throw new Exception("Campo obrigatório não informado. Favor informar o campo dataNascimento");
//        }
//        fakeDatabase.persisteAtor(ator);
//    }

    public List<AtorEmAtividade> listarAtoresEmAtividade(String filtroNome) throws Exception{
        List<Ator> atoresCadastrados = fakeDatabase.recuperaAtores();
        if(atoresCadastrados.isEmpty()){
            throw new Exception("Nenhum ator cadastrado, favor cadastar atores.");
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
            throw new Exception(String.format("Ator não encontrato com o filtro %s, favor informar outro filtro.", filtroNome));
        }

        return atorEmAtividade;
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

    public void printarAtorEmAtividade(AtorEmAtividade ator){
        System.out.println("----------------------------------------------");
        System.out.println(String.format("Ator em atividade filtrado: %s", ator.getNome()));
        System.out.println("nome: " + ator.getNome());
        System.out.println("Data de Nascimento: " + ator.getDataNascimento());
    }
}