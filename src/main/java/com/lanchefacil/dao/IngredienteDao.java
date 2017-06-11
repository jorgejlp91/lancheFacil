package com.lanchefacil.dao;

import org.springframework.stereotype.Repository;

import com.lanchefacil.entity.ListaIngredientes;
import com.lanchefacil.util.MemDatabase;

@Repository
public class IngredienteDao {
	
	public ListaIngredientes list(){
		ListaIngredientes lista = new ListaIngredientes();
		lista.setIngredientes(MemDatabase.ingredientes);
		return lista;
	}

}
