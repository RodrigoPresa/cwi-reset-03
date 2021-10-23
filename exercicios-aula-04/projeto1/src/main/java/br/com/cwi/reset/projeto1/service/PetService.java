package br.com.cwi.reset.projeto1.service;

import br.com.cwi.reset.projeto1.domain.Pet;
import br.com.cwi.reset.projeto1.repository.PetRepository;

import java.util.List;

public class PetService {
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

    public Pet buscarPorNome(String nomePet){
        return petRepository.findByNome(nomePet);
    }

    public void deletar(String nomePet) throws Exception{
        Pet pet = buscarPorNome(nomePet);

        if (pet == null) {
            throw new Exception("O pet de nome " + nomePet + " não existe");
        }

        petRepository.delete(pet);
    }

    public Pet atualizar(Pet pet) throws Exception{
        if (pet == null) {
            throw new Exception("O pet de nome " + pet.getNome() + " não existe");
        }

        return petRepository.update(pet);
    }
}
