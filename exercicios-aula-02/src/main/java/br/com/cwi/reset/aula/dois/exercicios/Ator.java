package br.com.cwi.reset.aula.dois.exercicios;

public class Ator {
    private String nome;
    private String idade;
    private int oscarsVencidos;
    private Genero genero;

    public Ator(String nome, String idade, int oscarsVencidos, Genero genero) {
        this.nome = nome;
        this.idade = idade;
        this.oscarsVencidos = oscarsVencidos;
        this.genero = genero;
    }

    public void sobre(){
        System.out.println("Nome do Ator: " + nome);
        System.out.println("Idade: " + idade);
        System.out.println("Gênero: " + genero.getDescricao());
        System.out.println("--------------------------");
    }

}
