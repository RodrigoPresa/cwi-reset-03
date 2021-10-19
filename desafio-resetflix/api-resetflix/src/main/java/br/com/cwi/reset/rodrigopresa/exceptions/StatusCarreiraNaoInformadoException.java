package br.com.cwi.reset.rodrigopresa.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class StatusCarreiraNaoInformadoException extends CampoNaoInformadoException{
    public StatusCarreiraNaoInformadoException(){
        super("statusCarreira");
    }
}
