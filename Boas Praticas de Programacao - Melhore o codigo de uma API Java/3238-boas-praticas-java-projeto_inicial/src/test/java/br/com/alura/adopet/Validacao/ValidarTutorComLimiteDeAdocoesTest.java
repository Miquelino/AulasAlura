package br.com.alura.adopet;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDTO;
import br.com.alura.adopet.api.exception.ValidationException;
import br.com.alura.adopet.api.model.StatusAdocao;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import br.com.alura.adopet.api.validacoes.ValidacaoTutoComLimitesAdocoes;
import br.com.alura.adopet.api.validacoes.ValidacaoTutorComAdocaoEmAndameto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)

public class ValidarTutorComLimiteDeAdocoesTest {

    @InjectMocks
    private ValidacaoTutoComLimitesAdocoes validador;

    @Mock
    private AdocaoRepository adocaoRepository;

    @Mock
    private SolicitacaoAdocaoDTO dto;

    @Test
    void naoDeveriaPermitirSolicitacaoDeAdocaoDePetComPedidoEmAndamento() {
        // Arrange
        given(adocaoRepository.existsByPetIdAndStatus
                (dto.idPet(),
                        StatusAdocao.AGUARDANDO_AVALIACAO))
                .willReturn(true);

        // Act + Assert
        assertThrows(ValidationException.class, () -> validador.validar(dto));
    }

    @Test
    void deveriaPermitirSolicitacaoDeAdocaoDePetComPedidoInexistente(){
        // Arrange
        given(adocaoRepository.existsByPetIdAndStatus
                (dto.idPet(),
                        StatusAdocao.AGUARDANDO_AVALIACAO))
                .willReturn(false);
        // Act + Assert
        assertThrows(ValidationException.class, () -> validador.validar(dto));
    }
}
