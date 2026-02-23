package med.voll.api.domain.consultas.validacoes;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consultas.DadosAgendamentoConsulta;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidadorFuncionamentoClinica implements ValidadorAgendamentoConsulta{

    public void validar(DadosAgendamentoConsulta dados){
        var dataCosulta = dados.data();
        var domingo = dataCosulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var antesDaAberturaClinica = dataCosulta.getHour() < 7;
        var depoisDoEncerramentoClinica = dataCosulta.getHour() > 18;

        if (domingo || antesDaAberturaClinica || depoisDoEncerramentoClinica){
            throw new ValidacaoException("Cosulta fora do horário de funcionamento da clinica");
        }
    }
}
