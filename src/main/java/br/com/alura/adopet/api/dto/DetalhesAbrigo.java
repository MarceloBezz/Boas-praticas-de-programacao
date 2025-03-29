package br.com.alura.adopet.api.dto;

import java.util.List;

import br.com.alura.adopet.api.model.Abrigo;

public record DetalhesAbrigo(
        String nome,
        String telefone,
        String email,
        List<DetalhesPet> pets) {

    public DetalhesAbrigo(Abrigo abrigo) {
        this(abrigo.getNome(), abrigo.getTelefone(), abrigo.getEmail(),
                abrigo.getPets().stream().map(p -> new DetalhesPet(p)).toList());
    }
}
