package br.com.cwi.reset.rodrigopresa.controller;

import br.com.cwi.reset.rodrigopresa.FakeDatabase;
import br.com.cwi.reset.rodrigopresa.model.Ator;
import br.com.cwi.reset.rodrigopresa.request.AtorRequest;
import br.com.cwi.reset.rodrigopresa.response.AtorEmAtividade;
import br.com.cwi.reset.rodrigopresa.service.AtorService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/atores")
public class AtorController {

    private AtorService atorService;

    public AtorController() {
        this.atorService = new AtorService(FakeDatabase.getInstance());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void criarAtor(@RequestBody AtorRequest atorRequest) throws Exception {
        this.atorService.criarAtor(atorRequest);
    }

    @GetMapping("/em_atividade")
    @ResponseStatus(HttpStatus.OK)
    public List<AtorEmAtividade> listarAtoresEmAtividade(String filtroNome) throws Exception {
        return this.atorService.listarAtoresEmAtividade(filtroNome);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Ator consultarAtor(@PathVariable Integer id) throws Exception {
        return this.atorService.consultarAtor(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Ator> consultarAtores() throws Exception {
        return this.atorService.consultarAtores();
    }
}
