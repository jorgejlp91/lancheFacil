package com.lanchefacil.dao;

import org.springframework.stereotype.Repository;

import com.lanchefacil.entity.LancheDomain;
import com.lanchefacil.entity.ListaLanche;
import com.lanchefacil.util.MemDatabase;

@Repository
public class LancheDao {
	
	public ListaLanche list(){
		ListaLanche lista = new ListaLanche();
		lista.setLanches(MemDatabase.lanches);
		return lista;
	}

	public LancheDomain list(Integer idLanche) {
		for (LancheDomain lanche : MemDatabase.lanches) {
			if (lanche.getId().equals(idLanche)){
				return lanche;
			}
		}
		return null;
	}

}
