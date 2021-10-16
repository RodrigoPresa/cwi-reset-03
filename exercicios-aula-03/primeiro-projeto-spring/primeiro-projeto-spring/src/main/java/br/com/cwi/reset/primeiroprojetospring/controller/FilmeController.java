package br.com.cwi.reset.primeiroprojetospring.controller;

import br.com.cwi.reset.primeiroprojetospring.domain.AvaliacaoForaDoPadraoException;
import br.com.cwi.reset.primeiroprojetospring.domain.Diretor;
import br.com.cwi.reset.primeiroprojetospring.domain.Filme;
import br.com.cwi.reset.primeiroprojetospring.domain.Genero;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/filme")
public class FilmeController {

    @GetMapping
    public Filme getFilme() throws AvaliacaoForaDoPadraoException {
        Diretor diretor = new Diretor("diretor1", LocalDate.of(1968,06,06), 2, Genero.MASCULINO);
        return new Filme("Yesterday", "Filme sobre os Beatles", 120, "2019", 5.0, diretor);
    }

}
