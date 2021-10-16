package br.com.cwi.reset.primeiroprojetospring.controller;

import br.com.cwi.reset.primeiroprojetospring.domain.AvaliacaoForaDoPadraoException;
import br.com.cwi.reset.primeiroprojetospring.domain.Diretor;
import br.com.cwi.reset.primeiroprojetospring.domain.Filme;
import br.com.cwi.reset.primeiroprojetospring.domain.Genero;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/filme")
public class FilmeController {

    private static List<Filme> filmes = new ArrayList<>();

    private Filme buscarFilmePeloNome(String nome) {
        for (Filme filme : filmes) {
            if (filme.getNome().equals(nome)) {
                return filme;
            }
        }
        return null;
    }

    @GetMapping
    public List<Filme> getListaFilmes() {
        return filmes;
    }

    @GetMapping("/{nome}")
    public ResponseEntity<Filme> getFilmeByNome(@PathVariable String nome) {
        Filme filme = buscarFilmePeloNome(nome);

        if (filme == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(filme);
    }

    @PostMapping
    public ResponseEntity<Filme> postFilme(@RequestBody Filme filme){
        Filme filmeExistente = buscarFilmePeloNome(filme.getNome());

        if(filmeExistente != null){
            ResponseEntity.badRequest().build();
        }
        filmes.add(filme);
        return ResponseEntity.ok(filme);
    }

    @DeleteMapping("/{nome}")
    public void deletarFilme(@PathVariable String nome) {
        Filme filme = buscarFilmePeloNome(nome);
        if (filme != null) {
            filmes.remove(filme);
        }
    }

    @PutMapping
    public Filme atualizarFilme(@RequestBody Filme filme) {
        Filme filmeExistente = buscarFilmePeloNome(filme.getNome());

        if(filmeExistente != null){
            filmes.remove(filmeExistente);
            filmes.add(filme);
            return filme;
        }

        return null;
    }

}
