package br.com.cwi.reset.primeiroprojetospring.domain;

import java.time.LocalDate;

public class Diretor extends Pessoa{

    private int qtdeFilmesDirigidos;

    public Diretor(String nome, LocalDate dataNascimento, int qtdeFilmesDirigidos, Genero genero) {
        super(nome,dataNascimento,genero);
        this.qtdeFilmesDirigidos = qtdeFilmesDirigidos;
    }

    public int getQtdeFilmesDirigidos() {
        return qtdeFilmesDirigidos;
    }
}
