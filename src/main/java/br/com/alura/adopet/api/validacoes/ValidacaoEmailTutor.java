package br.com.alura.adopet.api.validacoes;

import org.springframework.stereotype.Component;

import br.com.alura.adopet.api.dto.AtualizaTutor;
import br.com.alura.adopet.api.exception.ValidacaoException;

@Component
public class ValidacaoEmailTutor implements ValidacaoAtualizarTutor {

    @Override
    public void validar(AtualizaTutor tutorDto) {
        if (tutorDto.email() != null && tutorDto.email() != "") {
            String regex = "^[a-zA-z0-9._%+-]+@[a-zA-Z0-9.-]\\.[a-zA-Z]{2,}$";
            if (!tutorDto.email().matches(regex)) {
                throw new ValidacaoException("Formato de email inválido");
            }
        }
    }

}
