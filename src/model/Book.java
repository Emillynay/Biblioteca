package model;

public class Book {
	
	private Long id;
	private String name;
	private String author;
	private  int cdd;
	
	private int numberOfCopies;
	private int availableCopies;
	
	public Book(String name, String author, int cdd, int numberOfCopies) {
		super();
		this.name = name;
		this.author = author;
		this.cdd = cdd;
		this.numberOfCopies = numberOfCopies;
		this.availableCopies = numberOfCopies;
	}
	
	public Book(Long id, String name, String author, int cdd, int numberOfCopies, int availableCopies) {
		super();
		this.id = id;
		this.name = name;
		this.author = author;
		this.cdd = cdd;
		this.numberOfCopies = numberOfCopies;
		this.availableCopies = availableCopies;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getCdd() {
		return cdd;
	}

	public void setCdd(int cdd) {
		this.cdd = cdd;
	}

	public int getNumberOfCopies() {
		return numberOfCopies;
	}

	public Long getIdBook() {
		return id;
	}

	public void setIdBook(Long idBook) {
		this.id = idBook;
	}

	public void setNumberOfCopies(int numberOfCopies) {
		this.numberOfCopies = numberOfCopies;
	}

	public int getAvailableCopies() {
		return availableCopies;
	}
	
	public boolean loan() {
		if(availableCopies > 0) {
			availableCopies--;
			return true;
		} return false;
	}
	
	public void returnBook() {
		if(availableCopies < numberOfCopies) {
		availableCopies++;
	}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setAvailableCopies(int availableCopies) {
	    this.availableCopies = availableCopies;
	}
}
