package com.lanchefacil.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.lanchefacil.entity.IngredienteDomain;
import com.lanchefacil.entity.LancheDomain;

public class MemDatabase {
	
	public static List<IngredienteDomain> ingredientes = new ArrayList<IngredienteDomain>();
	public static List<LancheDomain> lanches = new ArrayList<LancheDomain>();
	
	public static int ID_ALFACE = 1;
	public static int ID_BACON = 2;
	public static int ID_CARNE = 3;
	public static int ID_QUEIJO = 4;
	public static int ID_OVO = 5;
	public static int ID_XBACON = 1;
	public static int ID_XBURGER = 2;
	public static int ID_XEGG = 3;
	public static int ID_XEGGBACON = 4;
	public static int ID_LIGHT = 1;
	public static int ID_MUITACARNE = 2;
	public static int ID_MUITOQUEIJO = 3;
	
	
	public static void initializeDb(){
		
		IngredienteDomain alface = new IngredienteDomain(ID_ALFACE,"Alface", 0.4);
		IngredienteDomain bacon = new IngredienteDomain(ID_BACON,"Bacon", 2.0);
		IngredienteDomain carne = new IngredienteDomain(ID_CARNE, "Hambúrguer de carne", 3.0);
		IngredienteDomain queijo = new IngredienteDomain(ID_QUEIJO,"Queijo", 0.8);
		IngredienteDomain ovo = new IngredienteDomain(ID_OVO,"Ovo", 0.8);
		
		ingredientes.addAll(Arrays.asList(alface, bacon, carne, ovo, queijo));
		
		LancheDomain xBacon = new LancheDomain(ID_XBACON,"X-Bacon", Arrays.asList(bacon, carne, queijo));
		LancheDomain xBurger = new LancheDomain(ID_XBURGER,"X-Burger", Arrays.asList(carne, queijo));
		LancheDomain xEgg = new LancheDomain(ID_XEGG,"X-Egg", Arrays.asList(ovo, carne, queijo));
		LancheDomain xEggBacon = new LancheDomain(ID_XEGGBACON,"X-Egg Bacon", Arrays.asList(ovo, bacon, carne, queijo));
		lanches.addAll(Arrays.asList(xBacon, xBurger, xEgg, xEggBacon));
		
	}


}
