package br.com.casadocodigo.loja.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.casadocodigo.loja.models.Product;

@Repository
public class ProductDAO {

	@PersistenceContext
	private EntityManager manager;
	
	public void save(Product product){
		manager.persist(product);
	}
	
	public List<Product> list(){
		return manager.createQuery("SELECT DISTINCT p FROM Product p JOIN fetch p.prices").getResultList();
	}

	public Product find(Integer id) {
		// TODO Auto-generated method stub
		return manager.createQuery("SELECT p FROM Product p JOIN FETCH p.prices WHERE p.id=:id",Product.class)
				.setParameter("id", id)
				.getSingleResult();
	}
}
