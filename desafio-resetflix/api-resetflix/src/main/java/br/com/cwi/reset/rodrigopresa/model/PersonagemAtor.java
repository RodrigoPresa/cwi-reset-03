package br.com.cwi.reset.rodrigopresa.model;

import java.util.Objects;

public class PersonagemAtor {
    private Integer id;
    private Ator ator;
    private String nomePersonagem;
    private String descricaoPersonagem;
    private TipoAtuacao tipoAtuacao;

    public PersonagemAtor(Integer id, Ator ator, String nomePersonagem, String descricaoPersonagem, TipoAtuacao tipoAtuacao) {
        this.id = id;
        this.ator = ator;
        this.nomePersonagem = nomePersonagem;
        this.descricaoPersonagem = descricaoPersonagem;
        this.tipoAtuacao = tipoAtuacao;
    }

    public Integer getId() {
        return id;
    }

    public Ator getAtor() {
        return ator;
    }

    public String getNomePersonagem() {
        return nomePersonagem;
    }

    public String getDescricaoPersonagem() {
        return descricaoPersonagem;
    }

    public TipoAtuacao getTipoAtuacao() {
        return tipoAtuacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonagemAtor that = (PersonagemAtor) o;
        return Objects.equals(id, that.id) && Objects.equals(ator, that.ator) && Objects.equals(nomePersonagem, that.nomePersonagem) && Objects.equals(descricaoPersonagem, that.descricaoPersonagem) && tipoAtuacao == that.tipoAtuacao;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ator, nomePersonagem, descricaoPersonagem, tipoAtuacao);
    }
}
