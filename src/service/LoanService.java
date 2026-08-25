package service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import exception.LoanLimitException;
import exception.LoanNotFoundException;
import exception.NoAvailableCopyException;
import model.Book;
import model.Loan;
import model.User;
import repository.LoanRepository;
import repository.memory.InMemoryLoanRepository;

public class LoanService {
	
	private LoanRepository repository = new InMemoryLoanRepository();
	
	public List<Loan> listLoans() {
		return repository.findAll();
	}
	
	public List<Loan> searchByUser(User user) {
		List<Loan> results = new ArrayList<>();
		
		for(Loan loan: repository.findAll()) {
			if(loan.getUser().getId().equals(user.getId())) {
				results.add(loan);
			}
		} return results;
	}
	
	public List<Loan> searchByBook(Book book) {
	    List<Loan> results = new ArrayList<>();

	    for (Loan loan : repository.findAll()) {
	        if (loan.getBook().getId().equals(book.getId())) {
	            results.add(loan);
	        }
	    } return results;
	}
	
	public boolean hasPreviousLoan(User user) {
		for(Loan loan: repository.findAll()) {
			if(loan.getUser().getId().equals(user.getId())) {
				return true;
			}
		} return false;
	}
	
	public int countActiveLoans(User user) {
		int count = 0;
		
		for(Loan loan: repository.findAll()) {
			if(loan.getUser().getId().equals(user.getId()) && 
					loan.isActive()) {
				count++;
			}
		} return count;
	}
	
	public int getLoanLimit(User user) {
		if(!hasPreviousLoan(user)) {
			return 1;
		} return 3;
	}

	public boolean canBorrow(User user) {
		int activeLoans = countActiveLoans(user);
		int limit = getLoanLimit(user);

		return activeLoans < limit;
	}

	public Loan makeLoan(User user, Book book) {
		if (!canBorrow(user)) {
			throw new LoanLimitException("Usuário atingiu o limite de empréstimos.");
		}
		
		if(!book.loan()) {
			throw new NoAvailableCopyException( "Não há exemplares disponíveis." );
		}
		
		Loan loan = new Loan(null, book, user, LocalDate.now(), LocalDate.now().plusDays(20));
		repository.save(loan);
		return loan;
	}
	
	public Loan findById(Long id) {
		Loan loan = repository.findById(id);
		
		if(loan == null) {
			throw new LoanNotFoundException("Empréstimo não encontrado.");
		}
		return loan;
	}
	
	public void returnLoan(Long id) {
		Loan loan = findById(id) ;
		
		if(!loan.isActive()) {
			throw new IllegalStateException("Este empréstimo já foi devolvido.");
		} 
		
		loan.returnBook();
		loan.getBook().returnBook();
	}
	
	

}
