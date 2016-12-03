package br.org.dedealtograu.loja.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.org.dedealtograu.loja.models.Book;

public class BookDAO {
	@PersistenceContext
	private EntityManager manager;
	
	public void save(Book product) {
		manager.persist(product);
	}
	
	public List<Book> lista() {
		return manager.createQuery("select distinct(b) from Book b join fetch b.authors",
				Book.class).getResultList();
	}
}
