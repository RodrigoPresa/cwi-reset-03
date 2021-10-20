package br.com.cwi.reset.rodrigopresa.exceptions;

public class DataCriacaoMaiorQueDataAtualException extends Exception{
    public DataCriacaoMaiorQueDataAtualException(String campo){
        super(String.format("Não é possível cadastrar %s no futuro.", campo));
    }
}
