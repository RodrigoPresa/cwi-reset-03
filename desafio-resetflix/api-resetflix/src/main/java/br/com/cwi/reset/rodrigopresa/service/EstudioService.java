package br.com.cwi.reset.rodrigopresa.service;

import br.com.cwi.reset.rodrigopresa.exceptions.*;
import br.com.cwi.reset.rodrigopresa.model.Estudio;
import br.com.cwi.reset.rodrigopresa.repository.EstudioRepository;
import br.com.cwi.reset.rodrigopresa.request.EstudioRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class EstudioService {

    @Autowired
    private EstudioRepository estudioRepository;

    public void criarEstudio(EstudioRequest estudioRequest) throws Exception{

        LocalDate dataAtual = LocalDate.now();
        if (dataAtual.isBefore(estudioRequest.getDataCriacao())){
            throw new DataCriacaoMaiorQueDataAtualException("estúdios");
        }

        List<Estudio> estudios = estudioRepository.findAll();

        for(Estudio estudioCadastrado : estudios){
            if (estudioCadastrado.getNome().equals(estudioRequest.getNome())){
                throw new CadastroDuplicadoException("estúdio", estudioCadastrado.getNome());
            }
        }

        Estudio estudio = new Estudio(estudioRequest.getNome(), estudioRequest.getDescricao(),
                estudioRequest.getDataCriacao(), estudioRequest.getStatusAtividade());

        estudioRepository.save(estudio);
    }

    public List<Estudio> consultarEstudios(String filtroNome) throws Exception{
        List<Estudio> estudiosCadastrados = estudioRepository.findAll();

        if(estudiosCadastrados.isEmpty()){
            throw new ListaVaziaException("estúdio", "estúdios");
        }

        List<Estudio> estudioFiltrado = new ArrayList<>();

        if(filtroNome != null){
            for(Estudio estudio : estudiosCadastrados){
                boolean contemFiltroNome = estudio.getNome().toLowerCase(Locale.ROOT).contains(filtroNome.toLowerCase(Locale.ROOT));
                if(contemFiltroNome){
                    estudioFiltrado.add(new Estudio(estudio.getNome(), estudio.getDescricao(), estudio.getDataCriacao(), estudio.getStatusAtividade()));
                    return estudioFiltrado;
                } else {
                    throw new FiltroNaoEncontradoException("Estúdio", filtroNome);
                }
            }
        }

        return estudiosCadastrados;
    }

    public Estudio consultarEstudio(Integer id) throws Exception{
        if (id == null) {
            throw new IdNaoInformadoException();
        }

        List<Estudio> estudios = estudioRepository.findAll();

        for(Estudio estudio : estudios){
            if(estudio.getId() == id){
                return estudio;
            }
        }

        return estudioRepository.findById(id).orElseThrow(() -> new ConsultaIdInvalidoException("estúdio", id));
    }

    public void atualizarEstudio(Integer id, EstudioRequest estudioRequest) throws Exception {
        Estudio estudioAtualizado = new Estudio(estudioRequest.getNome(), estudioRequest.getDescricao(),
                estudioRequest.getDataCriacao(), estudioRequest.getStatusAtividade());

        estudioAtualizado.setId(consultarEstudio(id).getId());

        List<Estudio> estudios = estudioRepository.findAll();

        for(Estudio estudioCadastrado : estudios){
            if (estudioCadastrado.getNome().equals(estudioAtualizado.getNome())){
                throw new CadastroDuplicadoException("estúdio", estudioAtualizado.getNome());
            }
        }

        estudioRepository.save(estudioAtualizado);

    }

    public void deletarEstudio(Integer id) throws Exception {
        if (id == null) {
            throw new IdNaoInformadoException();
        }
        Estudio estudioDeletado = consultarEstudio(id);

        estudioRepository.delete(estudioDeletado);
    }

}
