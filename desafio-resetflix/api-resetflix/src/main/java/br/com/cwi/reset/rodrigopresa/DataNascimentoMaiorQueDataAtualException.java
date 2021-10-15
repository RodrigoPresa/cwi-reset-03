package br.com.cwi.reset.rodrigopresa;

public class DataNascimentoMaiorQueDataAtualException extends Exception{
    public DataNascimentoMaiorQueDataAtualException(String campo){
        super(String.format("Não é possível cadastrar %s não nascidos.", campo));
    }
}
