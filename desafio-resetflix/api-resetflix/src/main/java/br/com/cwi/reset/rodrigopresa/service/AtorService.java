package br.com.cwi.reset.rodrigopresa.service;

import br.com.cwi.reset.rodrigopresa.exceptions.*;
import br.com.cwi.reset.rodrigopresa.model.Ator;
import br.com.cwi.reset.rodrigopresa.model.PersonagemAtor;
import br.com.cwi.reset.rodrigopresa.model.StatusCarreira;
import br.com.cwi.reset.rodrigopresa.repository.AtorRepository;
import br.com.cwi.reset.rodrigopresa.request.AtorRequest;
import br.com.cwi.reset.rodrigopresa.response.AtorEmAtividade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class AtorService {

    @Autowired
    private AtorRepository atorRepository;
    private AtorEmAtividade atorEmAtividade;
    @Autowired
    private PersonagemAtorService personagemAtorService;


    public void criarAtor(AtorRequest atorRequest) throws Exception{

        if(atorRequest.getNome().split(" ").length < 2){
            throw new NomeSobrenomeObrigatorioException("ator");
        }
        LocalDate dataAtual = LocalDate.now();
        if (dataAtual.isBefore(atorRequest.getDataNascimento())){
            throw new DataNascimentoMaiorQueDataAtualException("atores");
        }
        Integer anoNascimento = atorRequest.getDataNascimento().getYear();
        if(atorRequest.getAnoInicioAtividade() < anoNascimento){
            throw new AnoInicioAtividadeInvalidoException("ator");
        }

        List<Ator> atores = atorRepository.findAll();

        for(Ator atorCadastrado : atores){
            if (atorCadastrado.getNome().equals(atorRequest.getNome())){
                throw new CadastroDuplicadoException("ator", atorCadastrado.getNome());
            }
        }

        Ator ator = new Ator(atorRequest.getNome(), atorRequest.getDataNascimento(),
                atorRequest.getStatusCarreira(), atorRequest.getAnoInicioAtividade());

        atorRepository.save(ator);
    }

    public List<AtorEmAtividade> listarAtoresEmAtividade(String filtroNome) throws Exception{
        List<Ator> atoresCadastrados = atorRepository.findAll();
        if(atoresCadastrados.isEmpty()){
            throw new ListaVaziaException("ator", "atores");
        }

        List<AtorEmAtividade> atorEmAtividade = new ArrayList<>();

        if(filtroNome != null){
            for(Ator ator : atoresCadastrados){
                boolean contemFiltroNome = ator.getNome().toLowerCase(Locale.ROOT).contains(filtroNome.toLowerCase(Locale.ROOT));
                boolean emAtividade = StatusCarreira.EM_ATIVIDADE.equals(ator.getStatusCarreira());
                if(contemFiltroNome && emAtividade){
                    atorEmAtividade.add(new AtorEmAtividade(ator.getId(), ator.getNome(), ator.getDataNascimento()));
                }
            }
        } else{
            for(Ator ator : atoresCadastrados){
                boolean emAtividade = StatusCarreira.EM_ATIVIDADE.equals(ator.getStatusCarreira());
                if(emAtividade){
                    atorEmAtividade.add(new AtorEmAtividade(ator.getId(), ator.getNome(), ator.getDataNascimento()));
                }
            }
        }

        if(atorEmAtividade.isEmpty()){
            throw new FiltroNaoEncontradoException("Ator", filtroNome);
        }

        return atorEmAtividade;
    }

    public Ator consultarAtor(Integer id) throws Exception{
        return atorRepository.findById(id).orElseThrow(() -> new ConsultaIdInvalidoException("ator", id));
    }

    public List<Ator> consultarAtores() throws Exception{
        List<Ator> atores = atorRepository.findAll();

        if (atores.isEmpty()) {
            throw new ListaVaziaException("ator", "atores");
        }

        return atores;
    }

    public void atualizarAtor(Integer id, AtorRequest atorRequest) throws Exception {
        Ator atorAtualizado = new Ator(atorRequest.getNome(), atorRequest.getDataNascimento(),
                atorRequest.getStatusCarreira(), atorRequest.getAnoInicioAtividade());

        atorAtualizado.setId(consultarAtor(id).getId());

        List<Ator> atores = atorRepository.findAll();

        for(Ator atorCadastrado : atores){
            if (atorCadastrado.getNome().equals(atorAtualizado.getNome())){
                throw new CadastroDuplicadoException("ator", atorAtualizado.getNome());
            }
        }

        atorRepository.save(atorAtualizado);

    }

    public void deletarAtor(Integer id) throws Exception {

        Ator atorDeletado = consultarAtor(id);
        List<PersonagemAtor> personagens = personagemAtorService.consultarPersonagens();
        for(PersonagemAtor personagem : personagens){
            if (personagem.getAtor().equals(atorDeletado.getNome())){
                throw new Exception("Este ator está vinculado a um ou mais personagens, para remover o ator é necessário remover os seus personagens de atuação.");
            }
        }

        atorRepository.delete(atorDeletado);
    }

}