package br.com.cwi.reset.rodrigopresa.exceptions;

import br.com.cwi.reset.rodrigopresa.exceptions.CampoNaoInformadoException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IdAtorNaoInformadoException extends CampoNaoInformadoException {
    public IdAtorNaoInformadoException() {
        super("idAtor");
    }
}
