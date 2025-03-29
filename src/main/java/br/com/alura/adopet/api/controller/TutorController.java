package br.com.alura.adopet.api.controller;

import br.com.alura.adopet.api.dto.AtualizaTutor;
import br.com.alura.adopet.api.dto.CadastroTutor;
import br.com.alura.adopet.api.service.TutorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tutores")
public class TutorController {

    @Autowired
    private TutorService tutorService;

    @PostMapping
    @Transactional
    public ResponseEntity<String> cadastrar(@RequestBody @Valid CadastroTutor tutorDto) {
        try {
            tutorService.cadastrar(tutorDto);
            return ResponseEntity.ok("Tutor cadastrado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping
    @Transactional
    public ResponseEntity<String> atualizar(@RequestBody @Valid AtualizaTutor tutor) {
        try {
            tutorService.atualizar(tutor);
            return ResponseEntity.ok("Tutor atualizado!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
