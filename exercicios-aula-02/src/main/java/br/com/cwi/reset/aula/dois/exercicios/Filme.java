package br.com.cwi.reset.aula.dois.exercicios;

public class Filme {
    private String nome;
    private String descricao;
    private double duracao;
    private String anoLancamento;
    private int avaliacao;
    private Diretor diretor;

    public Filme(String nome, String descricao, double duracao, String anoLancamento, int avaliacao, Diretor diretor) {
        this.nome = nome;
        this.descricao = descricao;
        this.duracao = duracao;
        this.anoLancamento = anoLancamento;
        this.avaliacao = avaliacao;
        this.diretor = diretor;
    }

    public void reproduzir(){
        System.out.println("Filme: " + nome);
        System.out.println("Descrição: " + descricao);
        System.out.println("Duração: " + duracao);
        System.out.println("Diretor: " + diretor.getNome());
        System.out.println("--------------------------");
    }
}
