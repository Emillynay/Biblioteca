package repository;

import java.util.List;

import model.Book;

public interface BookRepository {
	
	
	void save(Book book);
	
	List<Book> findAll();
	
	Book findById(Long id);
	
	void delete(Book book);
}
