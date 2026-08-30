package aplication;

import java.time.LocalDate;

import model.Book;
import model.Loan;
import model.User;
import repository.jdbc.JdbcLoanRepository;

public class TestLoanRepository {
	
	 public static void main(String[] args) {

	        JdbcLoanRepository repository = new JdbcLoanRepository();

	       /* 
	        Book book = new Book(
	                2L,
	                null,
	                null,
	                0,
	                0,
	                0
	        );

	        User user = new User(
	                3L,
	                null,
	                null,
	                null,
	                null
	        );

	        Loan loan = new Loan(
	                null,
	                book,
	                user,
	                LocalDate.now(),
	                LocalDate.now().plusDays(7)
	        );

	        repository.save(loan);

	        System.out.println("Empréstimo salvo!"); */
	        
	        Loan loan = repository.findById(2L); // use o ID real

	        if (loan != null) {

	            System.out.println("Antes da devolução:");
	            System.out.println("Data de devolução: " + loan.getReturnDate());
	            System.out.println("Está ativo? " + loan.isActive());

	            loan.returnBook();

	            System.out.println("\nDepois da devolução:");
	            System.out.println("Data de devolução: " + loan.getReturnDate());
	            System.out.println("Está ativo? " + loan.isActive());

	            repository.update(loan);
	        }
	    
	 /*
	        System.out.println("\n--- EMPRÉSTIMOS ---");

	        for (Loan loan : repository.findAll()) {

	            System.out.println(
	                "ID: " + loan.getId()
	                + " | Livro ID: " + loan.getBook().getId()
	                + " | Usuário ID: " + loan.getUser().getId()
	                + " | Empréstimo: " + loan.getLoanDate()
	                + " | Devolução prevista: " + loan.getExpectedReturnDate()
	                + " | Ativo: " + loan.isActive()
	            );
	        }
	        
	        Loan foundLoan = repository.findById(1L);

	        if (foundLoan != null) {

	            System.out.println("\n--- EMPRÉSTIMO ENCONTRADO ---");

	            System.out.println(
	                "ID: " + foundLoan.getId()
	                + " | Livro ID: " + foundLoan.getBook().getId()
	                + " | Usuário ID: " + foundLoan.getUser().getId()
	                + " | Data: " + foundLoan.getLoanDate()
	                + " | Devolução prevista: " + foundLoan.getExpectedReturnDate()
	                + " | Ativo: " + foundLoan.isActive()
	            );

	        } else {

	            System.out.println("Empréstimo não encontrado.");
	        }
	        
	        Loan loan = repository.findById(1L);

	        if (loan != null) {

	            loan.setExpectedReturnDate(
	                loan.getExpectedReturnDate().plusDays(3)
	            );

	            repository.update(loan);

	            System.out.println("Empréstimo atualizado!");
	        }
	        
	        
	        Loan loan = repository.findById(1L);

	        if (loan != null) {

	            repository.delete(loan);

	            System.out.println("Empréstimo deletado!");

	        } else {
	            System.out.println("Empréstimo não encontrado.");
	        } */

	 }}
