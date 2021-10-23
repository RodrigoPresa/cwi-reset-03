package br.com.cwi.reset.rodrigopresa.validator;

import br.com.cwi.reset.rodrigopresa.exceptions.DescricaoPersonagemNaoInformadaException;
import br.com.cwi.reset.rodrigopresa.exceptions.IdAtorNaoInformadoException;
import br.com.cwi.reset.rodrigopresa.exceptions.NomeNaoInformadoException;
import br.com.cwi.reset.rodrigopresa.exceptions.TipoAtuacaoNaoInformadoException;
import br.com.cwi.reset.rodrigopresa.request.PersonagemRequest;

import static java.util.Objects.isNull;

public class PersonagemRequestCamposObrigatoriosValidator {

        public void accept(final PersonagemRequest personagemRequest) throws Exception {
            if (isNull(personagemRequest.getNomePersonagem())) {
                throw new NomeNaoInformadoException();
            }

            if (isNull(personagemRequest.getDescricaoPersonagem())) {
                throw new DescricaoPersonagemNaoInformadaException();
            }

            if (isNull(personagemRequest.getIdAtor())) {
                throw new IdAtorNaoInformadoException();
            }

            if (isNull(personagemRequest.getTipoAtuacao())) {
                throw new TipoAtuacaoNaoInformadoException();
            }
        }
}
