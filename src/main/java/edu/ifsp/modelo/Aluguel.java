package edu.ifsp.modelo;

public class Aluguel {
	
	int id;
	String entrada;
	String saida;
	int idQuarto;
	int idCliente;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEntrada() {
		return entrada;
	}
	public void setEntrada(String entrada) {
		this.entrada = entrada;
	}
	public String getSaida() {
		return saida;
	}
	public void setSaida(String saida) {
		this.saida = saida;
	}
	public long getIdQuarto() {
		return idQuarto;
	}
	public void setIdQuarto(int idQuarto) {
		this.idQuarto = idQuarto;
	}
	public long getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

}
