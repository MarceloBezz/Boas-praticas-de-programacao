package br.com.alura.adopet.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alura.adopet.api.dto.CadastroAbrigo;
import br.com.alura.adopet.api.dto.CadastroPet;
import br.com.alura.adopet.api.dto.DetalhesAbrigo;
import br.com.alura.adopet.api.dto.DetalhesPet;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.repository.AbrigoRepository;

@Service
public class AbrigoService {

    @Autowired
    private AbrigoRepository repository;

    public List<DetalhesAbrigo> listar() {
        return repository
                .findAll()
                .stream()
                .map(a -> new DetalhesAbrigo(a))
                .toList();
    }

    public void cadastrar(CadastroAbrigo abrigoDto) {
        boolean dadosJaCadastrado = repository.existsByNomeOrTelefoneOrEmail(abrigoDto.nome(), abrigoDto.telefone(),
                abrigoDto.email());

        if (dadosJaCadastrado) {
            throw new ValidacaoException("Dados já cadastrados para outro abrigo!");
        }

        repository.save(new Abrigo(abrigoDto));
    }

    public List<DetalhesPet> listarPets(String idOuNome) {
        try {
            Abrigo abrigo = carregarAbrigo(idOuNome);
            List<DetalhesPet> pets = abrigo
                    .getPets()
                    .stream()
                    .map(p -> new DetalhesPet(p))
                    .toList();
            return pets;
        } catch (Exception e) {
            throw new ValidacaoException(e.getMessage());
        }
    }

    public void cadastrarPet(String idOuNome, CadastroPet petDto) {
        try {
            Abrigo abrigo = carregarAbrigo(idOuNome);
            abrigo.adotarPet(petDto);
        } catch (Exception e) {
            throw new ValidacaoException(e.getMessage());
        }
    }

    private Abrigo carregarAbrigo(String idOuNome) {
        Optional<Abrigo> abrigo;
        try {
            Long id = Long.parseLong(idOuNome);
            abrigo = repository.findById(id);
        } catch (NumberFormatException nfe) {
            abrigo = repository.findByNome(idOuNome);
        }

        return abrigo.orElseThrow(() -> new ValidacaoException("Abrigo não encontrado!"));
    }
}
