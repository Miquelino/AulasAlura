package br.com.casaDoCodigo.livroOO.integracaoMinisterio;

import java.util.List;

import br.com.casaDoCodigo.livroOO.entidades.Neurocirurgia;
import br.com.casaDoCodigo.livroOO.entidades.Procedimento;
import br.com.casaDoCodigo.livroOO.negocio.FaringoplastiaNegocio;
import br.com.casaDoCodigo.livroOO.negocio.NeurocirurgiaNegocio;

public class DemonstrativoMinisterio implements IIntegracaoMinisterioSaude {

	@Override
	public void gerarDados(List<Procedimento> procedimentos) {

		NeurocirurgiaNegocio neurocirurgiaNegocio = new NeurocirurgiaNegocio();  
		FaringoplastiaNegocio faringoplastiaNegocio = new FaringoplastiaNegocio();  
		double totalProcedimentos = 0.0;
		for (Procedimento procedimento : procedimentos) {
			
			if (procedimento instanceof Neurocirurgia) {
				totalProcedimentos += neurocirurgiaNegocio.calcularTotal(procedimento);
			} else {
				totalProcedimentos += faringoplastiaNegocio.calcularTotal(procedimento);
			}
		}
		
		System.out.println("Total faturado pelo hospital com procedimentos: R$ " + totalProcedimentos);
	}

}
