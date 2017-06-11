package com.lanchefacil.entity;

import java.util.List;

public class ListaPromocao {

	private String valorFinal;
	private List<Promocao> promocoes;
	public String getValorFinal() {
		return valorFinal;
	}
	public void setValorFinal(String valorFinal) {
		this.valorFinal = valorFinal;
	}
	public List<Promocao> getPromocoes() {
		return promocoes;
	}
	public void setPromocoes(List<Promocao> promocoes) {
		this.promocoes = promocoes;
	}
	
	
	
}
