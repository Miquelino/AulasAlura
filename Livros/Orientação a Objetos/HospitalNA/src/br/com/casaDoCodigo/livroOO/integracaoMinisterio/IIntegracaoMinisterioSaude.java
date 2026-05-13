package br.com.casaDoCodigo.livroOO.integracaoMinisterio;

import java.util.List;

import br.com.casaDoCodigo.livroOO.entidades.Procedimento;

public interface IIntegracaoMinisterioSaude {

	void gerarDados(List<Procedimento> procedimentos);
}
