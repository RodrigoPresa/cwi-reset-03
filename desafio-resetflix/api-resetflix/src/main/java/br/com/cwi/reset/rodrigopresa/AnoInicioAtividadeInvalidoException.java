package br.com.cwi.reset.rodrigopresa;

public class AnoInicioAtividadeInvalidoException extends Exception{
    public AnoInicioAtividadeInvalidoException(String campo){
        super(String.format("Ano de início de atividade inválido para o %s cadastrado.", campo));
    }
}
