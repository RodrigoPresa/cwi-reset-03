package br.com.cwi.reset.aula.dois.exercicios;

public class Diretor {
    private String nome;
    private String idade;
    private int qtdeFilmesDirigidos;
    private Genero genero;

    public Diretor(String nome, String idade, int qtdeFilmesDirigidos, Genero genero) {
        this.nome = nome;
        this.idade = idade;
        this.qtdeFilmesDirigidos = qtdeFilmesDirigidos;
        this.genero = genero;
    }

    public void sobre(){
        System.out.println("Nome do Diretor: " + nome);
        System.out.println("Idade: " + idade);
        System.out.println("Gênero: " + genero.getDescricao());
        System.out.println("--------------------------");
    }

    public String getNome() {
        return nome;
    }
}
