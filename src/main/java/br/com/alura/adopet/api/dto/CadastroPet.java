package br.com.alura.adopet.api.dto;

import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.model.TipoPet;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CadastroPet(
    @NotNull TipoPet tipo,
    @NotBlank String nome,
    @NotBlank String raca,
    @NotNull Integer idade,
    @NotBlank String cor,
    @NotNull float peso)
    {
        public CadastroPet(Pet pet) {
            this(pet.getTipo(), pet.getNome(), pet.getRaca(), pet.getIdade(), pet.getCor(), pet.getPeso());
        }
    }
    