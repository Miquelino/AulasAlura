package br.com.casaDoCodigo.livroOO.util;

import br.com.casaDoCodigo.livroOO.entidades.Endereco;

public final class ConversoraEndereco {

	public static Endereco converterEndereco(String endereco) {
		
		Endereco end = new Endereco();
		end.setLogradouro(endereco.split(",")[0]);
		end.setNumero(Integer.parseInt(endereco.split(",")[1].split(" ")[1]));
		end.setBairro(endereco.split(",")[2]);
		end.setCep(endereco.split(",")[3]);
		
		return end;
	}
}
