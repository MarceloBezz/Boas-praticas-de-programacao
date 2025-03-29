package br.com.alura.adopet.api.controller;

import br.com.alura.adopet.api.dto.CadastroAbrigo;
import br.com.alura.adopet.api.dto.CadastroPet;
import br.com.alura.adopet.api.dto.DetalhesAbrigo;
import br.com.alura.adopet.api.service.AbrigoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/abrigos")
public class AbrigoController {

    @Autowired
    AbrigoService abrigoService;    

    @GetMapping
    public ResponseEntity<List<DetalhesAbrigo>> listar() {
        var abrigos = abrigoService.listar();
        return ResponseEntity.ok(abrigos);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<String> cadastrar(@RequestBody @Valid CadastroAbrigo abrigoDto) {
        try {
            abrigoService.cadastrar(abrigoDto);
            return ResponseEntity.ok("Cadastro concluído com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @GetMapping("/{idOuNome}/pets")
    public ResponseEntity listarPets(@PathVariable String idOuNome) {
        try {
            var pets = abrigoService.listarPets(idOuNome);
            return ResponseEntity.ok(pets);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/{idOuNome}/pets")
    @Transactional
    public ResponseEntity<String> cadastrarPet(@PathVariable String idOuNome, @RequestBody @Valid CadastroPet petDto) {
        try {
            abrigoService.cadastrarPet(idOuNome, petDto);
            return ResponseEntity.ok("Pet cadastrado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
