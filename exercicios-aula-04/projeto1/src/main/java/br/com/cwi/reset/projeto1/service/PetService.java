package br.com.cwi.reset.projeto1.service;

import br.com.cwi.reset.projeto1.domain.Pet;
import br.com.cwi.reset.projeto1.exception.PetNaoExistenteException;
import br.com.cwi.reset.projeto1.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService {

    @Autowired
    private PetRepository petRepository;

    public Pet salvarPet(Pet pet) throws Exception{
        Pet petJaCadastrado = petRepository.findByNome(pet.getNome());

        if(petJaCadastrado != null){
            throw new Exception(String.format("Já existe um pet cadastrado com o nome %s", pet.getNome()));
        }

        petRepository.save(pet);
        return pet;
    }

    public List<Pet> listarPets(){
        return petRepository.findAll();
    }

    public Pet buscarPorNome(String nomePet) throws PetNaoExistenteException {

        Pet pet =  petRepository.findByNome(nomePet);

        if(pet == null){
            throw new PetNaoExistenteException("O pet de nome " + nomePet + " não existe");
        }

        return pet;
    }

    public void deletar(String nomePet) throws PetNaoExistenteException{
        Pet pet = petRepository.findByNome(nomePet);

        if(pet == null){
            throw new PetNaoExistenteException("O pet de nome " + nomePet + " não existe");
        }

        petRepository.delete(pet);
    }

    public Pet atualizar(Pet pet) throws PetNaoExistenteException{
        Pet petJaCadastrado = petRepository.findByNome(pet.getNome());

        if(petJaCadastrado == null){
            throw new PetNaoExistenteException("O pet de nome " + pet.getNome() + " não existe");
        }

        return petRepository.save(pet);
    }
}
