package br.com.cwi.reset.rodrigopresa.controller;

import br.com.cwi.reset.rodrigopresa.exceptions.ConsultaIdInvalidoException;
import br.com.cwi.reset.rodrigopresa.exceptions.IdNaoInformadoException;
import br.com.cwi.reset.rodrigopresa.model.Filme;
import br.com.cwi.reset.rodrigopresa.request.FilmeRequest;
import br.com.cwi.reset.rodrigopresa.service.FilmeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/filmes")
public class FilmeController {

    @Autowired
    private FilmeService filmeService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void criarFilme(@RequestBody FilmeRequest filmeRequest) throws Exception {
        this.filmeService.criarFilme(filmeRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Filme> consultarFilmes(@RequestParam(required = false) String nomeFilme,
                                       @RequestParam(required = false) String nomeDiretor,
                                       @RequestParam(required = false) String nomePersonagem,
                                       @RequestParam(required = false) String nomeAtor) throws Exception{
        return this.filmeService.consultarFilmes(nomeFilme, nomeDiretor, nomePersonagem, nomeAtor);
    }

//    @GetMapping
//    @ResponseStatus(HttpStatus.OK)
//    public List<Filme> consultarFilmes() throws Exception{
//        return this.filmeService.consultarFilmes();
//    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deletarFilme(@PathVariable Integer id) throws IdNaoInformadoException, ConsultaIdInvalidoException {
        filmeService.deletarFilme(id);
    }
}
