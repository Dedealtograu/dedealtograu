package br.org.dedealtograu.loja.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.org.dedealtograu.loja.models.Author;

public class AuthorDAO {
	@PersistenceContext
	private EntityManager manager;

	public List<Author> list() {
		return manager.createQuery(
				"select a from Author a order by a.name asc", Author.class)
				.getResultList();
	}
	
	public void save(Author product) {
		manager.persist(product);
	}
}
