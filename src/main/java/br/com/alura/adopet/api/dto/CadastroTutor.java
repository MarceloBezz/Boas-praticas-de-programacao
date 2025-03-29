package br.com.alura.adopet.api.dto;

import br.com.alura.adopet.api.model.Tutor;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record CadastroTutor(
                @NotBlank String nome,
                @NotBlank @Pattern(regexp = "\\(?\\d{2}\\)?\\s?\\d{4,5}-?\\d{4}", message = "Telefone inválido") String telefone,
                @NotBlank @Email String email) {

        public CadastroTutor(Tutor tutor) {
                this(tutor.getNome(), tutor.getTelefone(), tutor.getEmail());
        }
}
