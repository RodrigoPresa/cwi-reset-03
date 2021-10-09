package br.com.cwi.reset.aula.dois.exercicios;

public class Ator extends Pessoa{
    private int oscarsVencidos;

    public Ator(String nome, String idade, int oscarsVencidos, Genero genero) {
        super(nome,idade,genero);
        this.oscarsVencidos = oscarsVencidos;
    }

}
