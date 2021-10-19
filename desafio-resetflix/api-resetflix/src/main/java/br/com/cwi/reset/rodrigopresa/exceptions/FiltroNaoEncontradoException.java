package br.com.cwi.reset.rodrigopresa.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FiltroNaoEncontradoException extends Exception{
    public FiltroNaoEncontradoException(String tipo, String parametro){
        super(String.format("%s não encontrado com o filtro %s, favor informar outro filtro.", tipo, parametro));
    }
}
