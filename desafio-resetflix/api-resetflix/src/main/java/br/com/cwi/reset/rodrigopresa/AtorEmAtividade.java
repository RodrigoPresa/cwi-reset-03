package br.com.cwi.reset.rodrigopresa;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AtorEmAtividade extends Ator {

    private FakeDatabase fakeDatabase;

    public AtorEmAtividade(Integer id, String nome, LocalDate dataNascimento, StatusCarreira statusCarreira, Integer anoInicioAtividade) {
        super(id, nome, dataNascimento, statusCarreira, anoInicioAtividade);
    }

    public List<AtorEmAtividade> recuperaAtorEmAtividade(){
        List<Ator> atores = fakeDatabase.recuperaAtores();
        List<AtorEmAtividade> atorEmAtividade = new ArrayList<>();

        for(Ator ator : atores){
            if(ator.getStatusCarreira().equals(StatusCarreira.EM_ATIVIDADE) ){
                atorEmAtividade.add((AtorEmAtividade) ator);
                return atorEmAtividade;
            }
        }
        return null;
    }

}
