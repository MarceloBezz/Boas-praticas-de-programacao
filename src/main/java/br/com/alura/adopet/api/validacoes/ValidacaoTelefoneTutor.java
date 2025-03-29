package br.com.alura.adopet.api.validacoes;

import org.springframework.stereotype.Component;

import br.com.alura.adopet.api.dto.AtualizaTutor;
import br.com.alura.adopet.api.exception.ValidacaoException;

@Component
public class ValidacaoTelefoneTutor implements ValidacaoAtualizarTutor{

    @Override
    public void validar(AtualizaTutor tutorDto) {
        if (tutorDto.telefone() != null && tutorDto.telefone() != "") {
            String regex = "\\(?\\d{2}\\)?\\s?\\d{4,5}-?\\d{4}";
            if (!tutorDto.telefone().matches(regex)) {
                throw new ValidacaoException("Formato de telefone inválido");
            }
        }
    }
}
