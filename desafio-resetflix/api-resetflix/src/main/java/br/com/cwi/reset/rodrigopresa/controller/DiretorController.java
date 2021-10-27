package br.com.cwi.reset.rodrigopresa.controller;

import br.com.cwi.reset.rodrigopresa.FakeDatabase;
import br.com.cwi.reset.rodrigopresa.model.Diretor;
import br.com.cwi.reset.rodrigopresa.request.AtorRequest;
import br.com.cwi.reset.rodrigopresa.request.DiretorRequest;
import br.com.cwi.reset.rodrigopresa.service.DiretorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/diretores")
public class DiretorController {

    @Autowired
    private DiretorService diretorService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void cadastrarDiretor(@RequestBody @Valid DiretorRequest diretorRequest) throws Exception {
        this.diretorService.criarDiretor(diretorRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Diretor> listarDiretores(@RequestParam String filtroNome) throws Exception {
        return this.diretorService.consultarDiretores();
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Diretor> listarDiretores() throws Exception {
        return this.diretorService.consultarDiretores();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Diretor consultarDiretor(@PathVariable Integer id) throws Exception {
        return this.diretorService.consultarDiretor(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void atualizarDiretor(@PathVariable Integer id, @RequestBody @Valid DiretorRequest diretorRequest) throws Exception {
        diretorService.atualizarDiretor(id, diretorRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deletarDiretor(@PathVariable Integer id) throws Exception {
        diretorService.deletarDiretor(id);
    }
}
