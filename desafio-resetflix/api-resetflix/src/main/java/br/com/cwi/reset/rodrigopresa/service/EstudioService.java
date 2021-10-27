package br.com.cwi.reset.rodrigopresa.service;

import br.com.cwi.reset.rodrigopresa.FakeDatabase;
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
    private FakeDatabase fakeDatabase;

    public EstudioService(FakeDatabase fakeDatabase) {
        this.fakeDatabase = fakeDatabase;
    }

    public void criarEstudio(EstudioRequest estudioRequest) throws Exception{

        if(estudioRequest.getNome() == null){
            throw new NomeNaoInformadoException();
        }
        if(estudioRequest.getDescricao() == null){
            throw new DescricaoNaoInformadaException();
        }
        if(estudioRequest.getStatusAtividade() == null){
            throw new StatusAtividadeNaoInformadoException();
        }

        LocalDate dataAtual = LocalDate.now();
        if (dataAtual.isBefore(estudioRequest.getDataCriacao())){
            throw new DataCriacaoMaiorQueDataAtualException("estúdios");
        }

        List<Estudio> estudios = fakeDatabase.recuperaEstudios();

        for(Estudio estudioCadastrado : estudios){
            if (estudioCadastrado.getNome().equals(estudioRequest.getNome())){
                throw new CadastroDuplicadoException("estúdio", estudioCadastrado.getNome());
            }
        }

        Estudio estudio = new Estudio(estudioRequest.getNome(), estudioRequest.getDescricao(),
                estudioRequest.getDataCriacao(), estudioRequest.getStatusAtividade());

        this.fakeDatabase.persisteEstudio(estudio);
    }

    public List<Estudio> consultarEstudios(String filtroNome) throws Exception{
        List<Estudio> estudiosCadastrados = fakeDatabase.recuperaEstudios();

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

        throw new ListaVaziaException("estúdio", "estúdios");
    }

    public Estudio consultarEstudio(Integer id) throws Exception{
        if (id == null) {
            throw new IdNaoInformadoException();
        }

        List<Estudio> estudios = fakeDatabase.recuperaEstudios();
        for(Estudio estudio : estudios){
            if(estudio.getId() == id){
                return estudio;
            }
        }
        throw new ConsultaIdInvalidoException("estúdio", id);
    }
}
