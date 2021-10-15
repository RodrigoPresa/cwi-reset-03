package br.com.cwi.reset.rodrigopresa;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public class Aplicacao {

    public static void main(String[] args) {
        FakeDatabase fakeDatabase = new FakeDatabase();

        AtorService atorService = new AtorService(fakeDatabase);

        String nome = "Will Smith";
        LocalDate dataNascimento = LocalDate.of(1968, Month.SEPTEMBER, 25);
        StatusCarreira statusCarreira = StatusCarreira.EM_ATIVIDADE;
        Integer anoInicioAtividade = 1986;
        AtorRequest atorRequest = new AtorRequest(nome, dataNascimento, statusCarreira, anoInicioAtividade);

        atorService.criarAtor(atorRequest);

        String nome2 = "Will Smith2";
        LocalDate dataNascimento2 = LocalDate.of(1968, Month.SEPTEMBER, 25);
        StatusCarreira statusCarreira2 = StatusCarreira.EM_ATIVIDADE;
        Integer anoInicioAtividade2 = 1986;
        AtorRequest atorRequest2 = new AtorRequest(nome2, dataNascimento2, statusCarreira2, anoInicioAtividade2);

        atorService.criarAtor(atorRequest2);

        atorService.printarAtor(atorService.consultarAtor(2));

        for(Ator ator : atorService.consultarAtores()){
            atorService.printarAtor(ator);
        }

        try {

            for(AtorEmAtividade ator : atorService.listarAtoresEmAtividade("Will")){
                atorService.printarAtorEmAtividade(ator);
            }

        }catch (Exception e){
            System.out.println("Erro");
        }


        DiretorService diretorService = new DiretorService(fakeDatabase);

        String nomeDiretor = "Quentin Tarantino";
        LocalDate dataNascimentoDiretor = LocalDate.of(1963, Month.MARCH, 27);
        Integer anoInicioAtividadeDiretor = 1986;
        DiretorRequest diretorRequest = new DiretorRequest(nomeDiretor, dataNascimentoDiretor, anoInicioAtividadeDiretor);

        diretorService.criarDiretor(diretorRequest);

        String nomeDiretor2 = "Quentin Tarantino2";
        LocalDate dataNascimentoDiretor2 = LocalDate.of(1963, Month.MARCH, 27);
        Integer anoInicioAtividadeDiretor2 = 1986;
        DiretorRequest diretorRequest2 = new DiretorRequest(nomeDiretor2, dataNascimentoDiretor2, anoInicioAtividadeDiretor2);

        diretorService.criarDiretor(diretorRequest2);

        diretorService.printarDiretor(diretorService.consultarDiretor(1));

        for(Diretor diretor : diretorService.consultarDiretores()){
            diretorService.printarDiretor(diretor);
        }
//        System.out.println("Deve conter 1 ator, quantidade encontrada: " + atores.size());
//        System.out.println("Primeiro ator deve ser 'Will Smith', valor encontrado: " + atores.get(1).getNome());

    }
}