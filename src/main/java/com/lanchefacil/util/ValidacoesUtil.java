package com.lanchefacil.util;

import com.lanchefacil.entity.IngredienteDomain;
import com.lanchefacil.entity.LancheDomain;

public class ValidacoesUtil {
	
	public static void validate(LancheDomain lanche) throws Exception{
		if (lanche.getIngredientes() != null && !lanche.getIngredientes().isEmpty()){
			for (IngredienteDomain ing : lanche.getIngredientes()) {
				if (ing.getId() == null
					|| ing.getQuantidade() == null
					|| ing.getValor() == null){
					throw new Exception("Request inválida");
				}
			}
		}
	}

}
