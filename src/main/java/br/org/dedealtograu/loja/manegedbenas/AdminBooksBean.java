package br.org.dedealtograu.loja.manegedbenas;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;

import br.org.dedealtograu.loja.daos.AuthorDAO;
import br.org.dedealtograu.loja.daos.BookDAO;
import br.org.dedealtograu.loja.models.Author;
import br.org.dedealtograu.loja.models.Book;

@Model
public class AdminBooksBean {
	
	private Book product = new Book();
	private List<Author> authors = new ArrayList<Author>();
	
	public List<Author> getAuthors() {
		return authors;
	}

	private List<Integer> selectedAuthorsIds = new ArrayList<>();
	
	public List<Integer> getSelectedAuthorsIds() {
		return selectedAuthorsIds;
	}

	public void setSelectedAuthorsIds(List<Integer> selectedAuthorsIds) {
		this.selectedAuthorsIds = selectedAuthorsIds;
	}

	@Inject
	private BookDAO bookDAO;
	
	@Inject 
	private AuthorDAO authorDAO;
	
	@Transactional
	public void save() {
		populateBookAuthor();
		bookDAO.save(product);
		limparObjetos();
	}
	
	private void limparObjetos() {
		this.product = new Book();
		this.selectedAuthorsIds.clear();
	}
	
	private void populateBookAuthor(){
		selectedAuthorsIds.stream().map( (id) -> {
			return new Author(id);
			}).forEach(p -> product.add(p));
	}

	public Book getProduct() {
		return product;
	}
	
	@PostConstruct
	private void loadObjects(){
		this.authors = authorDAO.list();
	}
}
