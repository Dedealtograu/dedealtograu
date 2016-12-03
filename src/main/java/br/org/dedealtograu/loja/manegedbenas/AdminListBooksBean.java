package br.org.dedealtograu.loja.manegedbenas;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.org.dedealtograu.loja.daos.BookDAO;
import br.org.dedealtograu.loja.models.Book;

@Model
public class AdminListBooksBean {
	@Inject
	private BookDAO bookDAO;
	
	private List<Book> books = new ArrayList<Book>();
	
	public List<Book> getBooks() {
		return books;
	}
	
	@PostConstruct
	private void carregarObjetos() {
		this.books = bookDAO.lista();
	}
	
	
}
