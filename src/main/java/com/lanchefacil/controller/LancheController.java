package com.lanchefacil.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lanchefacil.business.PromocaoBusiness;
import com.lanchefacil.dao.LancheDao;
import com.lanchefacil.entity.DefaultMsg;
import com.lanchefacil.entity.LancheDomain;
import com.lanchefacil.entity.ListaLanche;
import com.lanchefacil.entity.ListaPromocao;
import com.lanchefacil.util.ValidacoesUtil;

@Controller
@RequestMapping("/lanches")
public class LancheController {
	
	private LancheDao dao = new LancheDao();
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(path = "listAll", method = RequestMethod.GET, produces = "application/json")	
	@ResponseBody
	public ResponseEntity findAll() {		
		try {
			ListaLanche lanches = dao.list();			
			return new ResponseEntity<ListaLanche>(lanches, HttpStatus.OK);			
		} catch (Exception e) {
			return new ResponseEntity<DefaultMsg>(DefaultMsg.error(e.getMessage()), HttpStatus.BAD_REQUEST);
		}		
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(path = "{idLanche}", method = RequestMethod.GET, produces = "application/json")	
	@ResponseBody
	public ResponseEntity find(@PathVariable Integer idLanche) {		
		try {
			LancheDomain lanche = dao.list(idLanche);			
			return new ResponseEntity<LancheDomain>(lanche, HttpStatus.OK);			
		} catch (Exception e) {
			return new ResponseEntity<DefaultMsg>(DefaultMsg.error(e.getMessage()), HttpStatus.BAD_REQUEST);
		}		
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(path = "promocoes", method = RequestMethod.POST, produces = "application/json")	
	@ResponseBody
	public ResponseEntity promocoes(@RequestBody LancheDomain lanche) {		
		try {
			ValidacoesUtil.validate(lanche);
			ListaPromocao listaPromocao = PromocaoBusiness.calculaDescontoPromocao(lanche);
			return new ResponseEntity<ListaPromocao>(listaPromocao, HttpStatus.OK);			
		} catch (Exception e) {
			return new ResponseEntity<DefaultMsg>(DefaultMsg.error(e.getMessage()), HttpStatus.BAD_REQUEST);
		}		
	}
	
}
