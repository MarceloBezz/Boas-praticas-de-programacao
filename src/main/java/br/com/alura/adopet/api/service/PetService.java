package br.com.alura.adopet.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alura.adopet.api.dto.DetalhesPet;
import br.com.alura.adopet.api.repository.PetRepository;

@Service
public class PetService {

    @Autowired
    private PetRepository repository;

    public List<DetalhesPet> listarTodosDisponiveis() {
        return repository.findAllByAdotadoFalse()
                .stream()
                .map(p -> new DetalhesPet(p))
                .toList();
    }
}
