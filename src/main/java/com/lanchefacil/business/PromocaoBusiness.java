package com.lanchefacil.business;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.lanchefacil.entity.IngredienteDomain;
import com.lanchefacil.entity.LancheDomain;
import com.lanchefacil.entity.ListaPromocao;
import com.lanchefacil.entity.Promocao;
import com.lanchefacil.util.MemDatabase;

/**
 * Classe Business, utilizar para regras de negócio do cliente.
 * 
 * @author jorgep
 *
 */
@Component
public class PromocaoBusiness {

	public static DecimalFormat df = new DecimalFormat("#,##0.00");

	/**
	 * Este metodo verifica se os ingredientes do lanche estão contemplando
	 * alguma promoção retornando também o valor final do lanche
	 * 
	 * @param lanche
	 *            the LancheDomain
	 * @return lista com todas as promoções contempladas
	 * @throws ParseException
	 */
	public ListaPromocao calculaDescontoPromocao(LancheDomain lanche) throws ParseException {

		List<Promocao> promocoes = new ArrayList<Promocao>();

		calculaPromoLight(lanche, promocoes);
		calculaPromoCarne(lanche, promocoes);
		calculaPromoQueijo(lanche, promocoes);

		Double valorFinal = 0.0;

		for (IngredienteDomain ing : lanche.getIngredientes()) {
			valorFinal += ing.getValor() * ing.getQuantidade();
		}

		for (Promocao promocao : promocoes) {
			valorFinal -= df.parse(promocao.getValorDesconto()).doubleValue();
		}

		ListaPromocao listaPromocao = new ListaPromocao();
		listaPromocao.setValorFinal(df.format(valorFinal));
		listaPromocao.setPromocoes(promocoes);
		return listaPromocao;
	}

	private static void calculaPromoCarne(LancheDomain lanche, List<Promocao> promocoes) {
		Double descCarne = 0.0;
		for (IngredienteDomain ing : lanche.getIngredientes()) {
			if (ing.getId().equals(MemDatabase.ID_CARNE)) {
				Integer res = ing.getQuantidade() / 3;
				if (res >= 1) {
					descCarne = res * ing.getValor();
					promocoes.add(new Promocao(MemDatabase.ID_MUITACARNE, "Muita carne",
							df.format(descCarne)));
				}
			}
		}
	}

	private static void calculaPromoQueijo(LancheDomain lanche, List<Promocao> promocoes) {
		Double descQueijo = 0.0;
		for (IngredienteDomain ing : lanche.getIngredientes()) {
			if (ing.getId().equals(MemDatabase.ID_QUEIJO)) {
				Integer res = ing.getQuantidade() / 3;
				if (res >= 1) {
					descQueijo = res * ing.getValor();
					promocoes.add(new Promocao(MemDatabase.ID_MUITOQUEIJO, "Muito Queijo",
							df.format(descQueijo)));
				}
			}
		}
	}

	private static void calculaPromoLight(LancheDomain lanche, List<Promocao> promocoes) {
		boolean temAlface = false;
		boolean temBacon = false;
		Double valorFinal = 0.0;
		for (IngredienteDomain ing : lanche.getIngredientes()) {
			valorFinal += ing.getValor() * ing.getQuantidade();
			if (ing.getId().equals(MemDatabase.ID_ALFACE)) {
				temAlface = true;
			} else if (ing.getId().equals(MemDatabase.ID_BACON)) {
				temBacon = true;
			}
		}
		if (temAlface && !temBacon) {
			promocoes.add(new Promocao(MemDatabase.ID_LIGHT, "Light", df.format(valorFinal * 0.1)));
		}

	}

}
