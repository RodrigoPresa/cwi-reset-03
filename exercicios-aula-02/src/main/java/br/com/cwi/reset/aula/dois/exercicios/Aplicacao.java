package br.com.cwi.reset.aula.dois.exercicios;

public class Aplicacao {
    public static void main(String[] args) {
        Diretor diretor = new Diretor("João", "35", 5, Genero.MASCULINO);
        Ator ator1 = new Ator("Maria", "44", 1, Genero.FEMININO);
        Filme filme1 = new Filme("Primeiro Filme", "Descrição1", 120,
                "2020", 4, diretor);
        Filme filme2 = new Filme("Segundo Filme", "Descrição2", 140,
                "2021", 6, diretor);

        try {
            filme1.reproduzir();
        } catch(AvaliacaoForaDoPadraoException e){
            System.out.println(e.getMessage());
        }

        diretor.sobre();
        ator1.sobre();
        try {
            filme2.reproduzir();
        } catch(AvaliacaoForaDoPadraoException e){
            System.out.println(e.getMessage());
        }
    }
}
