package aplication;

import exception.BookNotFoundException;
import model.Book;
import service.BookService;

public class TestBookService {
	
	public static void main(String[] args) {

		
        BookService service = new BookService();

        /*
        Book book = new Book(
                "O Hobbit",
                "J. R. R. Tolkien",
                823,
                5
        );

        service.addBook(book);

        System.out.println("Livro adicionado!");
     
		
		System.out.println("\n--- LIVROS ---");

		for (Book book : service.listBooks()) {
		    System.out.println(
		        book.getId() + " | " +
		        book.getName() + " | " +
		        book.getAuthor() + " | " +
		        book.getAvailableCopies()
		    );
		}
		
		System.out.println("-----------------------------------------");
		
		try {
		    Book book = service.findById(3L);
		    System.out.println(
		        book.getId() + " | " +
		        book.getName() + " | " +
		        book.getAuthor()
		    );

		} catch (BookNotFoundException e) {
		    System.out.println(e.getMessage());
		}
	
		System.out.println("--------------------------------");
		
		try {
		    Book book = service.findById(3L);
		    book.setName("O Hobbit - Edição Atualizada");
		    book.setAuthor("J. R. R. Tolkien");
		    book.setCdd(823);
		    book.setNumberOfCopies(10);

		    service.updateBook(book);

		    System.out.println("Livro atualizado com sucesso!");
		} catch (BookNotFoundException e) {
		    System.out.println(e.getMessage());
		}
		
		System.out.println("---------------------------------------");
		
		try {

		    service.removeBook(3L);

		    System.out.println("Livro removido com sucesso!");

		} catch (BookNotFoundException e) {

		    System.out.println(e.getMessage());
		}
		*/
	}	

}
