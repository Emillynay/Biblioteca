package service;

import java.util.ArrayList;
import java.util.List;

import exception.BookNotFoundException;
import model.Book;
import repository.BookRepository;
import repository.memory.InMemoryBookRepository;

public class BookService {

	private BookRepository repository = new InMemoryBookRepository();

	public void addBook(Book book) {
		repository.save(book);
	}

	public List<Book> listBooks() {
		return repository.findAll();
	}
	
	public Book findById(Long id) throws BookNotFoundException {
        Book book = repository.findById(id);

        if (book == null) {
            throw new BookNotFoundException("Livro não encontrado.");
        }
        return book;
    }

	public List<Book> findByName(String name) {
		List<Book> results = new ArrayList<>();
		for (Book book : repository.findAll()) {
	        if (book.getName().toLowerCase().contains(name.toLowerCase())) {
	            results.add(book);
	        }
	    }
	    return results;
	}
	
	public List<Book> searchByAuthor(String author) {
	    List<Book> results = new ArrayList<>();
	    for (Book book : repository.findAll()) {
	        if (book.getAuthor()  .toLowerCase().contains(author.toLowerCase())) {
	            results.add(book);
	        }
	    }
	    return results;
	}
	
	public List<Book> searchByCdd(int cdd) {
	    List<Book> results = new ArrayList<>();
	    for (Book book :repository.findAll()) {
	        if (book.getCdd() == cdd) {
	            results.add(book);
	        }
	    }
	    return results;
	}
	
	public void updateBook(Book book) throws BookNotFoundException {
		
		Book existingBook = findById(book.getId());
		
        existingBook.setName(book.getName());
        existingBook.setAuthor(book.getAuthor());
        existingBook.setCdd(book.getCdd());
        existingBook.setNumberOfCopies(book.getNumberOfCopies());
        
    }

    public void removeBook(Long id) {
    	
    	Book book = repository.findById(id);
    	repository.delete(book);
    }
	
}
