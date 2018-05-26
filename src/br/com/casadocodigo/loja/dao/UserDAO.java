package br.com.casadocodigo.loja.dao;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import br.com.casadocodigo.loja.models.User;

@Repository
public class UserDAO implements UserDetailsService{

	@PersistenceContext
	private EntityManager em;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return Optional.of(em.createQuery("SELECT u FROM User u WHERE u.login=:login",User.class)
				.setParameter("login", username)
				.getSingleResult()).orElseThrow(()->new UsernameNotFoundException("Usuário não encontrado"));
	}
	
	
	
	
}
