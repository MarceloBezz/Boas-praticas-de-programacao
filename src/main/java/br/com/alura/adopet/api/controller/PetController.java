package br.com.alura.adopet.api.controller;

import br.com.alura.adopet.api.service.PetService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pets")
public class PetController {

    @Autowired
    PetService petService;

    @GetMapping
    public ResponseEntity listarTodosDisponiveis() {
        var pets = petService.listarTodosDisponiveis();
        return ResponseEntity.ok(pets);
    }

}
