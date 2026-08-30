package model;

import java.time.LocalDate;

public class Loan {
	
	private Long id;
	private Book book;
	private User user;
	private LocalDate loanDate;
	private LocalDate expectedReturnDate;
	private LocalDate returnDate;
	
	public Loan(Long id, Book book, User user, LocalDate loanDate, LocalDate expectedReturnDate) {
		super();
		this.id = id;
		this.book = book;
		this.user = user;
		this.loanDate = loanDate;
		this.expectedReturnDate = expectedReturnDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LocalDate getLoanDate() {
		return loanDate;
	}

	public void setLoanDate(LocalDate loanDate) {
		this.loanDate = loanDate;
	}

	public LocalDate getExpectedReturnDate() {
		return expectedReturnDate;
	}

	public void setExpectedReturnDate(LocalDate expectedReturnDate) {
		this.expectedReturnDate = expectedReturnDate;
	}

	public LocalDate getReturnDate() {
		return returnDate;
	}
	
	public void setReturnDate(LocalDate returnDate) {
	    this.returnDate = returnDate;
	}
	
	public boolean isActive() {
		return returnDate == null;
	}
	
	public void returnBook() {
		if(!isActive()) {
			throw new IllegalStateException(
					"Este empréstimo já foi encerrado."
					);
		}
		this.returnDate = LocalDate.now();
	}
}
