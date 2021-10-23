package br.com.cwi.reset.projeto1.controller;

import br.com.cwi.reset.projeto1.domain.Ator;
import br.com.cwi.reset.projeto1.service.AtorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ator")
public class AtorController {

    @Autowired
    private AtorService atorService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Ator cadastrarAtor(@RequestBody Ator ator){
        return atorService.salvarAtor(ator);
    }

    @GetMapping
    public List<Ator> buscarAtores(){
        return atorService.buscarAtores();
    }

    @GetMapping("/{nomeAtor}")
    public Ator getByNomeAtor(@PathVariable String nomeAtor){
        return atorService.buscarAtorPorNome(nomeAtor);
    }

//    @GetMapping("/{numeroOscars}")
//    public Ator getByNumeroOscars(@PathVariable Integer numeroOscars){
//        return atorService.buscarAtorPorNumeroOscars(numeroOscars);
//    }

    @DeleteMapping("/{id}")
    public void deletarAtor(@PathVariable Integer id){
        atorService.deletarAtor(id);
    }

}
