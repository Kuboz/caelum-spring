package br.com.casadocodigo.loja.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.casadocodigo.model.Product;

@Controller
public class ProductsController {

	@RequestMapping("/products/form")
	public String form(){
		return "products/form";
	}
	
	@RequestMapping("/products")
	public String save(Product product){
		System.out.println("Cadstrando o produto:"+product);
		return "products/ok";
	}
}
