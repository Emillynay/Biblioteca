package repository.memory;

import java.util.ArrayList;
import java.util.List;

import model.Book;
import repository.BookRepository;

public class InMemoryBookRepository implements BookRepository {
	
	private List<Book> books = new ArrayList<>();
	private Long nextId = 1L;
	
	@Override
	public void save(Book book) {
		book.setId(nextId);
		nextId++;
		books.add(book);
	}
	
	@Override
	public List<Book> findAll() {
		return List.copyOf(books);
	}
	
	@Override
	public Book findById(Long id) {
		for(Book book: books) {
			if(id.equals(book.getId())) {
				return book;
			}
		} return null;
	}
	
	@Override
	public void delete(Book book) {
		books.remove(book);
	}

	@Override
	public void update(Book book) {
		// TODO Auto-generated method stub
		
	}

}
