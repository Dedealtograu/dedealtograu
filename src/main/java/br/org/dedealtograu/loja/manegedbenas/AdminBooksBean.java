package br.org.dedealtograu.loja.manegedbenas;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.facelets.FaceletContext;
import javax.inject.Inject;
import javax.transaction.Transactional;

import br.org.dedealtograu.loja.daos.AuthorDAO;
import br.org.dedealtograu.loja.daos.BookDAO;
import br.org.dedealtograu.loja.infra.MessagesHelper;
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
	
	@Inject
	private MessagesHelper messagesHelper;
	
	@Transactional
	public String save() {
		populateBookAuthor();
		bookDAO.save(product);
		
		messagesHelper.addFlash(new FacesMessage("Livro gravado com sucesso!"));
		
		return "/livros/lista?faces-redirect=true";
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
