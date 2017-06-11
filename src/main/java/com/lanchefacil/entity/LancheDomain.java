package com.lanchefacil.entity;

import java.util.List;

import com.lanchefacil.business.PromocaoBusiness;

public class LancheDomain {
	
	private Integer id;
	private String nome;
	private List<IngredienteDomain> ingredientes;
	
	public LancheDomain() {
		// TODO Auto-generated constructor stub
	}
	
	public LancheDomain(Integer id, String nome, List<IngredienteDomain> ingredientes) {
		super();
		this.nome = nome;
		this.ingredientes = ingredientes;
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
	public List<IngredienteDomain> getIngredientes() {
		return ingredientes;
	}
	public void setIngredientes(List<IngredienteDomain> ingredientes) {
		this.ingredientes = ingredientes;
	}
	
	public Double getValorTotalDouble(){
		Double tot = 0.0;
		for (IngredienteDomain ingrediente : ingredientes) {
			tot += ingrediente.getValor();
		}
		return tot;
	}
	
	public String getValorTotal(){
		Double tot = 0.0;
		for (IngredienteDomain ingrediente : ingredientes) {
			tot += ingrediente.getValor();
		}
		return PromocaoBusiness.df.format(tot);
	}
	
	

}
