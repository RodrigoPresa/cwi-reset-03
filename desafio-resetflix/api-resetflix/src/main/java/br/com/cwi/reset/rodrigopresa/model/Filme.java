package br.com.cwi.reset.rodrigopresa.model;

import br.com.cwi.reset.rodrigopresa.request.PersonagemRequest;

import java.util.List;

public class Filme {
    private Integer id;
    private String nome;
    private Integer anoLancamento;
    private String capaFilme;
    private List<Genero> generos;
    private Diretor idDiretor;
    private Estudio idEstudio;
    private String resumo;
    private List<PersonagemRequest> personagens;

    public Filme(Integer id, String nome, Integer anoLancamento, String capaFilme, List<Genero> generos, Diretor idDiretor, Estudio idEstudio, String resumo, List<PersonagemRequest> personagens) {
        this.id = id;
        this.nome = nome;
        this.anoLancamento = anoLancamento;
        this.capaFilme = capaFilme;
        this.generos = generos;
        this.idDiretor = idDiretor;
        this.idEstudio = idEstudio;
        this.resumo = resumo;
        this.personagens = personagens;
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

    public Diretor getIdDiretor() {
        return idDiretor;
    }

    public Estudio getIdEstudio() {
        return idEstudio;
    }

    public String getResumo() {
        return resumo;
    }

    public List<PersonagemRequest> getPersonagens() {
        return personagens;
    }
}
