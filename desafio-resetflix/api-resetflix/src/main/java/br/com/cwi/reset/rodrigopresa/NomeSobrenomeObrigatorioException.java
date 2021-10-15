package br.com.cwi.reset.rodrigopresa;

public class NomeSobrenomeObrigatorioException extends Exception{
    public NomeSobrenomeObrigatorioException(String campo){
        super(String.format("Deve ser informado no mínimo nome e sobrenome para o %s.", campo));
    }
}
