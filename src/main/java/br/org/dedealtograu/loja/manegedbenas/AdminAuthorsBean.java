package br.org.dedealtograu.loja.manegedbenas;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;

import br.org.dedealtograu.loja.daos.AuthorDAO;
import br.org.dedealtograu.loja.models.Author;

@Model
public class AdminAuthorsBean {
	
	private Author product = new Author();

	@Inject
	private AuthorDAO authorDAO;
	
	@Transactional
	public void save() {
		authorDAO.save(product);
	}

	public Author getProduct() {
		return product;
	}
	
}
