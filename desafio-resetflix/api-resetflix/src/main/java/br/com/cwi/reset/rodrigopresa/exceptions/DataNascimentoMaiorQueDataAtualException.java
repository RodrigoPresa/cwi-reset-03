package br.com.cwi.reset.rodrigopresa.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DataNascimentoMaiorQueDataAtualException extends Exception{
    public DataNascimentoMaiorQueDataAtualException(String campo){
        super(String.format("Não é possível cadastrar %s não nascidos.", campo));
    }
}
