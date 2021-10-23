package br.com.cwi.reset.rodrigopresa.model;

import java.util.List;
import java.util.Objects;

public class Filme {

    private Integer id;
    private String nome;
    private Integer anoLancamento;
    private String capaFilme;
    private List<Genero> generos;
    private Estudio estudio;
    private Diretor diretor;
    private List<PersonagemAtor> personagens;
    private String resumo;

    public Filme(Integer id, String nome, Integer anoLancamento, String capaFilme, List<Genero> generos, Estudio estudio, Diretor diretor, List<PersonagemAtor> personagens, String resumo) {
        this.id = id;
        this.nome = nome;
        this.anoLancamento = anoLancamento;
        this.capaFilme = capaFilme;
        this.generos = generos;
        this.estudio = estudio;
        this.diretor = diretor;
        this.personagens = personagens;
        this.resumo = resumo;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Integer getAnoLancamento() {
        return anoLancamento;
    }

    public String getCapaFilme() {
        return capaFilme;
    }

    public List<Genero> getGeneros() {
        return generos;
    }

    public Estudio getEstudio() {
        return estudio;
    }

    public Diretor getDiretor() {
        return diretor;
    }

    public List<PersonagemAtor> getPersonagens() {
        return personagens;
    }

    public String getResumo() {
        return resumo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Filme filme = (Filme) o;
        return Objects.equals(id, filme.id) && Objects.equals(nome, filme.nome) && Objects.equals(anoLancamento, filme.anoLancamento) && Objects.equals(capaFilme, filme.capaFilme) && Objects.equals(generos, filme.generos) && Objects.equals(estudio, filme.estudio) && Objects.equals(diretor, filme.diretor) && Objects.equals(personagens, filme.personagens) && Objects.equals(resumo, filme.resumo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, anoLancamento, capaFilme, generos, estudio, diretor, personagens, resumo);
    }
}
