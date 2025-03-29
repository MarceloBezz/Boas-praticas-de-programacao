package br.com.alura.adopet.api.validacoes;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.StatusAdocao;
import br.com.alura.adopet.api.repository.AdocaoRepository;

@Component
public class ValidacaoTutorComLimiteDeAdocao implements ValidacaoSolicitacaoAdocao {

    @Autowired
    private AdocaoRepository adocaoRepository;

    public void validar(SolicitacaoAdocaoDto dto) {
        int adocoesAprovadasDoTutor = adocaoRepository.countByTutorIdAndStatus(dto.idTutor(), StatusAdocao.APROVADO);

        if (adocoesAprovadasDoTutor == 5) {
            throw new ValidacaoException("Tutor chegou ao limite máximo de 5 adoções!");
        }

    }
}
