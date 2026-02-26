package br.com.casaDoCodigo.livroOO.integracaoMinisterio;

import java.util.List;

import br.com.casaDoCodigo.livroOO.entidades.Procedimento;

public class DemonstrativoMinisterio implements IIntegracaoMinisterioSaude {

	@Override
	public void gerarDados(List<Procedimento> procedimentos) {

		double totalProcedimentos = 0.0;
		for (Procedimento procedimento : procedimentos) {
			totalProcedimentos += procedimento.calcularTotal();
		}
		
		System.out.println("Total faturado pelo hospital com procedimentos: R$ " + totalProcedimentos);
	}

}
