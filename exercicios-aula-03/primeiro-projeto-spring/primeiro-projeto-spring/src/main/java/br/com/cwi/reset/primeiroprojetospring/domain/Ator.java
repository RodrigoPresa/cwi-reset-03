package br.com.cwi.reset.primeiroprojetospring.domain;

import java.time.LocalDate;

public class Ator extends Pessoa{
    private int oscarsVencidos;

    public Ator(String nome, LocalDate dataNascimento, int oscarsVencidos, Genero genero) {
        super(nome,dataNascimento,genero);
        this.oscarsVencidos = oscarsVencidos;
    }

}
