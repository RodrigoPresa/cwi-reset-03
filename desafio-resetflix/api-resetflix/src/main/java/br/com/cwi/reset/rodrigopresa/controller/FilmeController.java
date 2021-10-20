package br.com.cwi.reset.rodrigopresa.controller;

import br.com.cwi.reset.rodrigopresa.FakeDatabase;
import br.com.cwi.reset.rodrigopresa.model.Filme;
import br.com.cwi.reset.rodrigopresa.request.FilmeRequest;
import br.com.cwi.reset.rodrigopresa.service.FilmeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/filmes")
public class FilmeController {
    private FilmeService filmeService;

    public FilmeController(){
        this.filmeService = new FilmeService(FakeDatabase.getInstance());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void criarFilme(@RequestBody FilmeRequest filmeRequest) throws Exception {
        this.filmeService.criarFilme(filmeRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Filme> consultarFilmes(@RequestParam String nomeFilme, @RequestParam String nomeDiretor, @RequestParam String nomePersonagem, @RequestParam String nomeAtor){
        return this.filmeService.consultarFilmes(nomeFilme, nomeDiretor, nomePersonagem, nomeAtor);
    }
}
