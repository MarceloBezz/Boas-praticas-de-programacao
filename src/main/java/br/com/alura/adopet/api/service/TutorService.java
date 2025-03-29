package br.com.alura.adopet.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alura.adopet.api.dto.AtualizaTutor;
import br.com.alura.adopet.api.dto.CadastroTutor;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Tutor;
import br.com.alura.adopet.api.repository.TutorRepository;
import br.com.alura.adopet.api.validacoes.ValidacaoAtualizarTutor;

@Service
public class TutorService {

    @Autowired
    private TutorRepository repository;

    private List<ValidacaoAtualizarTutor> validacoes;

    public void cadastrar(CadastroTutor tutorDto) {
        boolean dadosJaCadastrados = repository.existsByEmailOrTelefone(tutorDto.email(), tutorDto.telefone())

        if (dadosJaCadastrados) {
            throw new ValidacaoException("Dados já cadastrados para outro tutor!");
        }

        repository.save(new Tutor(tutorDto));
    }

    public void atualizar(AtualizaTutor tutorDto) {
        var tutorBd = repository.getReferenceById(tutorDto.id());

        if (tutorBd == null) {
            throw new ValidacaoException("Tutor não cadastrado!");
        }

        validacoes.forEach(v -> v.validar(tutorDto));

        tutorBd.atualizar(tutorDto);
    }
}
