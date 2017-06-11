package com.lanchefacil.entity;

public class Promocao {
	
	private Integer id;
	private String nome;
	private String valorDesconto;
	
	public Promocao() {
		// TODO Auto-generated constructor stub
	}
	
	
	public Promocao(Integer id, String nome, String valorDesconto) {
		super();
		this.id = id;
		this.nome = nome;
		this.valorDesconto = valorDesconto;
	}


	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getValorDesconto() {
		return valorDesconto;
	}
	public void setValorDesconto(String valorDesconto) {
		this.valorDesconto = valorDesconto;
	}
	
	

}
