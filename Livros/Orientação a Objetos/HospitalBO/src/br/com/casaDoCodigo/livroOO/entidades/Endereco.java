package br.com.casaDoCodigo.livroOO.entidades;

public class Endereco {

	private String logradouro;
	
	private int numero;
	
	private String bairro;
	
	private String cep;

	public Endereco() {
		
	}
	
	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	@Override
	public int hashCode() {
		final int prime = 29;
		int result = 1;
		result = prime * result
				+ ((logradouro == null) ? 0 : logradouro.hashCode());
		result = prime * result + numero;
		return result;
	}

	@Override
	public boolean equals(Object obj) {

		if (obj instanceof Endereco) {
			
			Endereco endereco = (Endereco) obj;
			return this.logradouro.equalsIgnoreCase(endereco.getLogradouro())
					&& this.numero == endereco.getNumero();
		}
		return false;
	}
	
	@Override
	public String toString() {
		return logradouro + ",n " + numero + "," + bairro + "," + cep;
	}
}
