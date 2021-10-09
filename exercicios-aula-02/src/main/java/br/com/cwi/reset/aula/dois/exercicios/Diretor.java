package br.com.cwi.reset.aula.dois.exercicios;

public class Diretor extends Pessoa{

    private int qtdeFilmesDirigidos;

    public Diretor(String nome, String idade, int qtdeFilmesDirigidos, Genero genero) {
        super(nome,idade,genero);
        this.qtdeFilmesDirigidos = qtdeFilmesDirigidos;
    }
}
