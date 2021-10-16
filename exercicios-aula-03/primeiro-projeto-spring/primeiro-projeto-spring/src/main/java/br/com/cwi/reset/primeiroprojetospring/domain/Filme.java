package br.com.cwi.reset.primeiroprojetospring.domain;

public class Filme {
    private String nome;
    private String descricao;
    private double duracao;
    private String anoLancamento;
    private Double avaliacao;
    private Diretor diretor;

    public Filme(String nome, String descricao, double duracao,
                 String anoLancamento, Double avaliacao, Diretor diretor){
        this.nome = nome;
        this.descricao = descricao;
        this.duracao = duracao;
        this.anoLancamento = anoLancamento;
        this.avaliacao = avaliacao;
        this.diretor = diretor;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getDuracao() {
        return duracao;
    }

    public String getAnoLancamento() {
        return anoLancamento;
    }

    public Double getAvaliacao() {
        return avaliacao;
    }

    public Diretor getDiretor() {
        return diretor;
    }

    public void reproduzir()  throws AvaliacaoForaDoPadraoException {
        System.out.println("Filme: " + nome);
        System.out.println("Descrição: " + descricao);
        System.out.println("Duração: " + duracao);
        System.out.println("Diretor: " + diretor.getNome());
        if(avaliacao > 5 || avaliacao < 1) {
            throw new AvaliacaoForaDoPadraoException();
        }else {
            System.out.println("Avaliação: " + avaliacao);
        }
        System.out.println("--------------------------");
    }
}
