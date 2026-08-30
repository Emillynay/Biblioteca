package aplication;

import java.util.List;

import model.Book;
import repository.jdbc.JdbcBookRepository;

public class TestBookRepository {

	public static void main(String[] args) {

		JdbcBookRepository repository = new JdbcBookRepository();

		/*
		List<Book> books = repository.findAll();

		for(Book book: books) {
			System.out.println(
					book.getId() + " | " +
							book.getName() + " | " +
							book.getAuthor() + " | " +
							book.getCdd() + " | " +
							book.getAvailableCopies() + " | ");
		}
		
		repository.findById(1L);
		*/
		
		/*
		Book book = repository.findById(2L);

	    if (book != null) {
	        System.out.println(
	            book.getId() + " | " +
	            book.getName() + " | " +
	            book.getAuthor() + " | " +
	            book.getAvailableCopies()
	        );
	    } else {
	        System.out.println("Livro não encontrado.");
	    } */
		
		/*
		Book book = repository.findById(1L);

		if(book != null) {
			System.out.println("Antes de deletar");
			System.out.println(book.getId() + " | " + book.getName());

			repository.delete(book);

			System.out.println("Livro deletado!");
		} else {
			System.out.println("Livro não encontrado.");
		}
		
		
		Book book = repository.findById(3L);

		if (book != null) {

		    book.setName("Dom Casmurro - Edição Especial");

		    repository.update(book);

		    System.out.println("Livro atualizado!");
		}
		*/

	} 
}
