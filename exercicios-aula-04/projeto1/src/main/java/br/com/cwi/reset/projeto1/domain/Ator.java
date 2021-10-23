package br.com.cwi.reset.projeto1.domain;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "ator")
public class Ator extends Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer numeroOscars;

    public Ator(String nome, LocalDate nascimento, Integer numeroOscars, Genero genero) {
        super(nome, nascimento, genero);
        this.numeroOscars = numeroOscars;
    }


    public Ator() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumeroOscars() {
        return numeroOscars;
    }

    public void setNumeroOscars(Integer numeroOscars) {
        this.numeroOscars = numeroOscars;
    }


}
