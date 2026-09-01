package repository.memory;

import java.util.ArrayList;
import java.util.List;

import model.Loan;
import repository.LoanRepository;

public class InMemoryLoanRepository implements LoanRepository {

	private List<Loan> loans = new ArrayList<>();
	private Long nextId = 1L;

	@Override
	public void save(Loan loan) {
		loan.setId(nextId);
		nextId++;

		loans.add(loan);
	}

	@Override
	public List<Loan> findAll() {
		return List.copyOf(loans);
	}

	@Override
	public Loan findById(Long id) {
		for(Loan loan: loans) {
			if(id.equals(loan.getId())) {
				return loan;
			}
		} return null;
	}

	@Override
	public void delete(Loan loan) {
		loans.remove(loan);
	}

	@Override
	public void update(Loan loan) {
	}
}
