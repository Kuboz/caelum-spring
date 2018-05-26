package br.com.casadocodigo.loja.controllers;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casadocodigo.loja.dao.ProductDAO;
import br.com.casadocodigo.loja.infra.FileSaver;
import br.com.casadocodigo.loja.models.BookType;
import br.com.casadocodigo.loja.models.Product;

@Controller
@RequestMapping("/products")
public class ProductsController {

	@Autowired
	private ProductDAO productDAO;
	
	@Autowired
	private FileSaver fileSaver;
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		//binder.addValidators(new ProductValidator());
	}
	
	@RequestMapping("/form")
	public ModelAndView form(Product product){
		ModelAndView mv = new ModelAndView("products/form");
		mv.addObject("types", BookType.values());
		return mv;
	}
	
	@Transactional
	@RequestMapping(method=RequestMethod.POST)
	@CacheEvict(value="lastProducts",allEntries=true)
	public ModelAndView save(MultipartFile summary,@Valid Product product,
			BindingResult bindingResult, RedirectAttributes att){
		if(bindingResult.hasErrors()){
			return form(product);
		}
		String webPath = fileSaver.write("uploaded-summaries",summary);
		product.setSummaryPath(webPath);
		productDAO.save(product);
		att.addFlashAttribute("success","Item Criado com sucesso!");
		return new ModelAndView("redirect:products");
	}
	
	@Cacheable(value="lastProducts")
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView list(){
		ModelAndView mv = new ModelAndView("products/list");
		mv.addObject("products", productDAO.list());
		return mv;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/{id}")
	public ModelAndView show (@PathVariable("id") Integer id){
		ModelAndView modelAndView = new ModelAndView("products/show");
		modelAndView.addObject("product",productDAO.find(id));
		return modelAndView;
	}
}
