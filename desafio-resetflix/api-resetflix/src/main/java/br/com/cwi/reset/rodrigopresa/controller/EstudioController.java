package br.com.cwi.reset.rodrigopresa.controller;

import br.com.cwi.reset.rodrigopresa.model.Estudio;
import br.com.cwi.reset.rodrigopresa.request.EstudioRequest;
import br.com.cwi.reset.rodrigopresa.service.EstudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/estudios")
public class EstudioController {

    @Autowired
    private EstudioService estudioService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void criarEstudio(@RequestBody @Valid EstudioRequest estudioRequest) throws Exception {
        this.estudioService.criarEstudio(estudioRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Estudio> consultarEstudios(@RequestParam(required = false) String filtroNome) throws Exception {
        return this.estudioService.consultarEstudios(filtroNome);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Estudio consultarEstudio(@PathVariable Integer id) throws Exception {
        return this.estudioService.consultarEstudio(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void atualizarEstudio(@PathVariable Integer id, @RequestBody @Valid EstudioRequest estudioRequest) throws Exception {
        estudioService.atualizarEstudio(id, estudioRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deletarEstudio(@PathVariable Integer id) throws Exception {
        estudioService.deletarEstudio(id);
    }
}
