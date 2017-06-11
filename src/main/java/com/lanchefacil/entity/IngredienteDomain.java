package com.lanchefacil.entity;

import com.lanchefacil.business.PromocaoBusiness;

public class IngredienteDomain {
	
	private Integer id;
	private String nome;
	private Double valor;
	private Integer quantidade;
	
	public IngredienteDomain() {
		// TODO Auto-generated constructor stub
	}
	
	public IngredienteDomain(Integer id, String nome, Double valor) {
		super();
		this.nome = nome;
		this.valor = valor;
		this.id = id;
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
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	
	public String getValorFormatado(){
		return PromocaoBusiness.df.format(valor);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof IngredienteDomain){
			IngredienteDomain i = (IngredienteDomain) obj;
			return id.equals(i.getId());
		}
		return false;
	}
	
}
