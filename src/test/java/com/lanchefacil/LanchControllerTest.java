package com.lanchefacil;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.lanchefacil.business.PromocaoBusiness;
import com.lanchefacil.dao.IngredienteDao;
import com.lanchefacil.dao.LancheDao;
import com.lanchefacil.entity.IngredienteDomain;
import com.lanchefacil.entity.LancheDomain;
import com.lanchefacil.entity.ListaIngredientes;
import com.lanchefacil.entity.ListaLanche;
import com.lanchefacil.entity.ListaPromocao;
import com.lanchefacil.util.MemDatabase;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class LanchControllerTest {

	private static final String URL_LANCHE = "/lanchefacil/lanches/";

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private LancheDao lancheDao;

	@Autowired
	private IngredienteDao ingDao;

	private ListaLanche listaLache;
	private ListaIngredientes listaIng;
	private IngredienteDomain alface = new IngredienteDomain(MemDatabase.ID_ALFACE, "Alface", 0.4);
	private IngredienteDomain bacon = new IngredienteDomain(MemDatabase.ID_BACON, "Bacon", 2.0);
	private IngredienteDomain carne = new IngredienteDomain(MemDatabase.ID_CARNE,
			"Hambúrguer de carne", 3.0);
	private IngredienteDomain queijo = new IngredienteDomain(MemDatabase.ID_QUEIJO, "Queijo", 0.8);

	/**
	 * Obtém os ingredientes e lanches direto da base de dados
	 */
	@Before
	public void init() {
		listaLache = lancheDao.list();
		listaIng = ingDao.list();
	}

	/**
	 * Testa verificando se o valor de cada lanche está de acordo com o a
	 * somatória do valor dos ingredientes
	 */
	public void valoresLanchesCardapioTest() {
		ResponseEntity<LancheDomain> xBacon = this.restTemplate
				.getForEntity(URL_LANCHE + MemDatabase.ID_XBACON, LancheDomain.class);

		verificaValor(xBacon);

		ResponseEntity<LancheDomain> xBurger = this.restTemplate
				.getForEntity(URL_LANCHE + MemDatabase.ID_XBURGER, LancheDomain.class);

		verificaValor(xBurger);

		ResponseEntity<LancheDomain> xEgg = this.restTemplate
				.getForEntity(URL_LANCHE + MemDatabase.ID_XEGG, LancheDomain.class);

		verificaValor(xEgg);

		ResponseEntity<LancheDomain> xEggBacon = this.restTemplate
				.getForEntity(URL_LANCHE + MemDatabase.ID_XEGGBACON, LancheDomain.class);

		verificaValor(xEggBacon);

	}

	/**
	 * Testa se a promoção light está efetuando o calculo corretamente
	 * 
	 * @throws ParseException
	 */
	public void validaPromocaoLight() throws ParseException {

		alface.setQuantidade(2);
		carne.setQuantidade(2);
		LancheDomain lancheDomain = new LancheDomain(99, "monta", Arrays.asList(alface, carne));

		ResponseEntity<ListaPromocao> entity = this.restTemplate
				.postForEntity("/lanchefacil/lanches/promocoes", lancheDomain, ListaPromocao.class);

		// Somente uma promocao
		assertEquals(1, entity.getBody().getPromocoes().size());
		// Promocao Light id = 1
		assertEquals(MemDatabase.ID_LIGHT,
				entity.getBody().getPromocoes().get(0).getId().intValue());
		// Valor deve ter desconto de 10%
		Double totalLight = 0.0;
		for (IngredienteDomain ingrediente : lancheDomain.getIngredientes()) {
			totalLight += ingrediente.getValor() * ingrediente.getQuantidade();
		}
		assertEquals(Double.valueOf(totalLight * 0.9), Double.valueOf(
				PromocaoBusiness.df.parse(entity.getBody().getValorFinal()).doubleValue()));
	}

	/**
	 * Testa se a promoção queijo está efetuando o calculo corretamente
	 * 
	 * @throws ParseException
	 */
	@Test
	public void validaPromocaoQueijo() throws ParseException {

		queijo.setQuantidade(4);
		bacon.setQuantidade(1);
		LancheDomain lancheDomain = new LancheDomain(99, "monta", Arrays.asList(queijo, bacon));

		ResponseEntity<ListaPromocao> entity = this.restTemplate
				.postForEntity("/lanchefacil/lanches/promocoes", lancheDomain, ListaPromocao.class);

		// Somente uma promocao
		assertEquals(1, entity.getBody().getPromocoes().size());
		assertEquals(MemDatabase.ID_MUITOQUEIJO,
				entity.getBody().getPromocoes().get(0).getId().intValue());
		// Valor deve ter desconto de uma porção de queijo
		Double totalQueijo = 0.0;
		Double valorQueijo = 0.0;
		for (IngredienteDomain ingrediente : lancheDomain.getIngredientes()) {
			totalQueijo += ingrediente.getValor() * ingrediente.getQuantidade();
			if (ingrediente.getId().equals(MemDatabase.ID_QUEIJO)) {
				valorQueijo = ingrediente.getValor();
			}
		}
		assertEquals(Double.valueOf(totalQueijo - valorQueijo), Double.valueOf(
				PromocaoBusiness.df.parse(entity.getBody().getValorFinal()).doubleValue()));
	}

	/**
	 * Testa se a promoção carne está efetuando o calculo corretamente
	 * 
	 * @throws ParseException
	 */
	public void validaPromocaoCarne() throws ParseException {

		carne.setQuantidade(4);
		bacon.setQuantidade(1);
		LancheDomain lancheDomain = new LancheDomain(99, "monta", Arrays.asList(carne, bacon));

		ResponseEntity<ListaPromocao> entity = this.restTemplate
				.postForEntity("/lanchefacil/lanches/promocoes", lancheDomain, ListaPromocao.class);

		// Somente uma promocao
		assertEquals(1, entity.getBody().getPromocoes().size());
		assertEquals(MemDatabase.ID_MUITACARNE,
				entity.getBody().getPromocoes().get(0).getId().intValue());
		// Valor deve ter desconto de uma porção de queijo
		Double totalCarne = 0.0;
		Double valorCarne = 0.0;
		for (IngredienteDomain ingrediente : lancheDomain.getIngredientes()) {
			totalCarne += ingrediente.getValor() * ingrediente.getQuantidade();
			if (ingrediente.getId().equals(MemDatabase.ID_CARNE)) {
				valorCarne = ingrediente.getValor();
			}
		}
		assertEquals(Double.valueOf(totalCarne - valorCarne), Double.valueOf(
				PromocaoBusiness.df.parse(entity.getBody().getValorFinal()).doubleValue()));
	}

	private void verificaValor(ResponseEntity<LancheDomain> lancheResponse) {
		for (LancheDomain lanche : listaLache.getLanches()) {
			if (lanche.getId().equals(lancheResponse.getBody().getId())) {
				Double valorIng = 0.0;
				Double valorLanche = lancheResponse.getBody().getValorTotalDouble();

				for (IngredienteDomain ing : listaIng.getIngredientes()) {
					if (lancheResponse.getBody().getIngredientes().contains(ing)) {
						valorIng += ing.getValor();
					}
				}

				assertEquals(valorIng, valorLanche);

			}

		}
	}

}
