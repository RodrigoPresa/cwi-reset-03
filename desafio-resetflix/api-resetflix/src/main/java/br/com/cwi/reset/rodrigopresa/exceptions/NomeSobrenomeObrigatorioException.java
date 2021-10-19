package br.com.cwi.reset.rodrigopresa.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NomeSobrenomeObrigatorioException extends Exception{
    public NomeSobrenomeObrigatorioException(String campo){
        super(String.format("Deve ser informado no mínimo nome e sobrenome para o %s.", campo));
    }
}
