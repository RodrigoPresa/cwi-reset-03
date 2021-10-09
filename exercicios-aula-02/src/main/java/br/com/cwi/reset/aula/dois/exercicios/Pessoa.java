package br.com.cwi.reset.aula.dois.exercicios;

public class Pessoa {
    private String nome;
    private String idade;

    public String getNome() {
        return nome;
    }

    private Genero genero;

    public Pessoa(String nome, String idade, Genero genero) {
        this.nome = nome;
        this.idade = idade;
        this.genero = genero;
    }

    public void sobre(){
        System.out.println("Nome: " + nome);
        System.out.println("Idade: " + idade);
        System.out.println("Gênero: " + genero.getDescricao());
        System.out.println("--------------------------");
    }
}
