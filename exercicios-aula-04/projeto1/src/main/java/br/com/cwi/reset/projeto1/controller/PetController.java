package br.com.cwi.reset.projeto1.controller;

import br.com.cwi.reset.projeto1.domain.Pet;
import br.com.cwi.reset.projeto1.service.PetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pet")
public class PetController {

    private PetService petService = new PetService();

    @GetMapping
    public List<Pet> getPet() {
        return petService.listarPets();
    }

    @GetMapping("/{nome}")
    public Pet getByNome(@PathVariable String nomePet) {
        return petService.buscarPorNome(nomePet);
    }

    @PostMapping
    public ResponseEntity<Pet> cadastrarPet(@RequestBody Pet pet) {
        try {
            Pet petSalvo = petService.salvarPet(pet);
            return ResponseEntity.ok(petSalvo);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping
    public ResponseEntity<Pet> atualizarPet(@RequestBody Pet pet) {
        try {
            return ResponseEntity.ok(petService.atualizar(pet));
        }catch (Exception ex){
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{nome}")
    public ResponseEntity<Pet> deletarPet(@PathVariable String nomePet) {
        try {
            petService.deletar(nomePet);
            return ResponseEntity.ok().build();
        }catch (Exception ex){
            return ResponseEntity.badRequest().build();
        }
    }

}
