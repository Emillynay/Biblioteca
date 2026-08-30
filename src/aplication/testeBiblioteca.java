package aplication;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Scanner;

import exception.BookNotFoundException;
import model.Book;
import model.Loan;
import model.User;
import service.BookService;
import service.LoanService;
import service.UserService;

public class testeBiblioteca {
	
	public static void main(String[] agrs) throws ParseException, BookNotFoundException {

		Scanner sc = new Scanner(System.in);
		Locale.setDefault(Locale.US);
		
		/*
		BookService bookService = new BookService();
		
		Book book1 = new Book("Dom Casmurro", "Machado de Assis", 800, 5);
		Book book2 = new Book("O Cortiço", "Aluísio Azevedo", 800, 2);
		Book book3 = new Book("Clean Code", "Robert C. Martin", 005, 4);
		Book book4 = new Book("O alienista", "Machado de Assis", 800, 1);
		
		bookService.addBook(book1);
		bookService.addBook(book2);
		bookService.addBook(book3);
		bookService.addBook(book4);
		
		System.out.println("Todos os livros: ");
		for(Book book: bookService.listBooks()) {
			System.out.println(
		            "ID: " + book.getId()
		            + " | Título: " + book.getName()
		            + " | Autor: " + book.getAuthor()
		            + " | Disponíveis: "
		            + book.getAvailableCopies()
		            + "/" + book.getNumberOfCopies() );
		}
		
		System.out.println();
		System.out.println("Pesquisando por nome: ");
		for(Book book: bookService.findByName("dom")) {
			System.out.println(book.getName() + " - " + book.getAuthor());
		}
		
		System.out.println();
		System.out.println("Pesquisando por autor: ");
		for(Book book: bookService.searchByAuthor("assis")) {
			System.out.println(book.getName() + " - " + book.getAuthor());
		}
		
		System.out.println();
		System.out.println("Atualização: ");
		book3.setName("Clean Code: A Handbook of Agile Software Craftsmanship");
		bookService.updateBook(book3);
		
		for(Book book: bookService.listBooks()) {
			System.out.println("ID: " + book.getId()   + " | Título: " + book.getName());
		}
		
		System.out.println();
		System.out.println("Removendo");
		bookService.removeBook(2L);
		for (Book book : bookService.listBooks()) {
		    System.out.println( "ID: " + book.getId() + " | Título: " + book.getName());
	}
		
	LoanService loanService = new LoanService();
	UserService userService = new UserService();
	
	System.out.println();
	System.out.println("-------------------------------------");
	
	User user1 = new User("Emilly Nayanne", "(98) 98445-1713", "emillynay@gmail.com", "Bairro: Canaã; Rua: Jardim das Flores");
	userService.addUser(user1);
	
	Loan loan1 = loanService.makeLoan(user1, book1);
	
	System.out.println("Empréstimo realizado!"); 
	System.out.println("Livro: " + loan1.getBook().getName());
	System.out.println("Usuário: " + loan1.getUser().getName()); 
	System.out.println("Disponíneis: " + book1.getAvailableCopies());
	System.out.println("Empréstimo ativo? " + loan1.isActive());
	
	loanService.returnLoan(loan1.getId());
	
	System.out.println();
	System.out.println("LIvro devolvido!");
	System.out.println("Disponíveis: " + book1.getAvailableCopies());
	
	System.out.println();
	System.out.println("Empréstimo ativo? " + loan1.isActive()); 
	
	Loan loan2 = loanService.makeLoan(user1, book1);
	Loan loan3 = loanService.makeLoan(user1, book2);
	Loan loan4 = loanService.makeLoan(user1, book3);
	
	System.out.println();
	System.out.println("Emprestimos realizados!");
	 
	System.out.println();
	System.out.println("\n===== TODOS OS EMPRÉSTIMOS =====");
	for (Loan loan : loanService.listLoans()) {
	    System.out.println("ID: " + loan.getId() + " | Usuário: " + loan.getUser().getName()
	            + " | Livro: " + loan.getBook().getName() + " | Ativo: " + loan.isActive());
	}
	
	System.out.println();
	System.out.println("\n===== EMPRÉSTIMOS DA EMILLY =====");
	
	for (Loan loan : loanService.searchByUser(user1)) {
	    System.out.println("ID: " + loan.getId() + " | Livro: " + loan.getBook().getName()
	            + " | Ativo: " + loan.isActive() );
	}
	
	System.out.println();
	System.out.println("\n===== HISTÓRICO DO LIVRO =====");

	for (Loan loan : loanService.searchByBook(book1)) {
		System.out.println("Empréstimo: " + loan.getId() + " | Usuário: " + loan.getUser().getName()
				+ " | Ativo: " + loan.isActive() );
	} */
	}
}
