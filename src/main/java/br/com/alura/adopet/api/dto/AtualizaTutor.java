package br.com.alura.adopet.api.dto;

import jakarta.validation.constraints.NotBlank;

public record AtualizaTutor(String nome, String telefone, String email, @NotBlank Long id) {
    
}
