package repository;

import java.util.List;

import model.Loan;

public interface LoanRepository {
	
	void save(Loan loan);
	
	List<Loan> findAll();
	
	Loan findById(Long id);
	
	void delete(Loan loan);

	void update(Loan loan);
}
