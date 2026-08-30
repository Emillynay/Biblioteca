package model;

public class User {
	
	private Long id;
	private String name;
	private String number;
	private String email;
	private String anddress;
	
	public User(String name, String number, String email, String anddress) {
		super();
		this.name = name;
		this.number = number;
		this.email = email;
		this.anddress = anddress;
	}
	
	public User(Long id, String name, String number, String email, String anddress) {
		super();
		this.id = id;
		this.name = name;
		this.number = number;
		this.email = email;
		this.anddress = anddress;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAnddress() {
		return anddress;
	}

	public void setAnddress(String anddress) {
		this.anddress = anddress;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
