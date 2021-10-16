package br.com.cwi.reset.primeiroprojetospring.domain;

import java.time.LocalDate;
import java.time.Period;

public abstract class Pessoa {
    private String nome;
    private LocalDate dataNascimento;
    private Genero genero;

    public Pessoa(String nome, LocalDate dataNascimento, Genero genero) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.genero = genero;
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public Genero getGenero() {
        return genero;
    }

    private Integer calcularIdade() {
        return Period.between(LocalDate.now(), dataNascimento).getYears();
    }

    public void sobre(){
        System.out.println("Nome: " + nome);
        System.out.println("Data de nascimento: " + dataNascimento);
        System.out.println("Gênero: " + genero.getDescricao());
        System.out.println("--------------------------");
    }

}
