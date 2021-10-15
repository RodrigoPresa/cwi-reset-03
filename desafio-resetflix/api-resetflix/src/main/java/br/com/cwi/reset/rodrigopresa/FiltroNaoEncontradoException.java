package br.com.cwi.reset.rodrigopresa;

public class FiltroNaoEncontradoException extends Exception{
    public FiltroNaoEncontradoException(String tipo, String parametro){
        super(String.format("%s não encontrado com o filtro %s, favor informar outro filtro.", tipo, parametro));
    }
}
