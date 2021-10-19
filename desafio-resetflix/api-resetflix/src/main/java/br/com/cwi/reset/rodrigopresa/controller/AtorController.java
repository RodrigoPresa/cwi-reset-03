package br.com.cwi.reset.rodrigopresa.controller;

import br.com.cwi.reset.rodrigopresa.FakeDatabase;
import br.com.cwi.reset.rodrigopresa.service.AtorService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/atores")
public class AtorController {

    private AtorService atorService;

    public AtorController() {
        this.atorService = new AtorService(FakeDatabase.getInstance());
    }

    //demais métodos
}
