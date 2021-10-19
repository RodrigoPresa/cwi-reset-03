package br.com.cwi.reset.rodrigopresa;

import br.com.cwi.reset.rodrigopresa.model.Ator;
import br.com.cwi.reset.rodrigopresa.model.Diretor;
import br.com.cwi.reset.rodrigopresa.model.StatusCarreira;
import br.com.cwi.reset.rodrigopresa.request.AtorRequest;
import br.com.cwi.reset.rodrigopresa.request.DiretorRequest;
import br.com.cwi.reset.rodrigopresa.response.AtorEmAtividade;
import br.com.cwi.reset.rodrigopresa.service.AtorService;
import br.com.cwi.reset.rodrigopresa.service.DiretorService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.time.Month;

@SpringBootApplication
public class Aplicacao {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Aplicacao.class, args);
    }
}