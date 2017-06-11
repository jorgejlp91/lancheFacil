package com.lanchefacil.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lanchefacil.dao.IngredienteDao;
import com.lanchefacil.entity.DefaultMsg;
import com.lanchefacil.entity.ListaIngredientes;

@Controller
@RequestMapping("/ingredientes")
public class IngredienteController {
	
	private IngredienteDao dao = new IngredienteDao();
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(path = "findAll", method = RequestMethod.GET, produces = "application/json")	
	@ResponseBody
	public ResponseEntity findAll() {		
		try {
			ListaIngredientes ingredientes = dao.list();			
			return new ResponseEntity<ListaIngredientes>(ingredientes, HttpStatus.OK);			
		} catch (Exception e) {
			return new ResponseEntity<DefaultMsg>(DefaultMsg.error(e.getMessage()), HttpStatus.BAD_REQUEST);
		}		
	}
	
}
