package service;

import java.util.ArrayList;
import java.util.List;

import model.User;
import repository.UserRepository;
import repository.jdbc.JdbcUserRepository;

public class UserService {
	
	private UserRepository repository = new JdbcUserRepository();
	
	public List<User> listUsers() {
	    return repository.findAll();
	}
	
	public User searchByEmail(String email) {
		for(User user: repository.findAll()) {
			if(user.getEmail().equalsIgnoreCase(email)) {
				return user;
			}
		} return null;
	}
	
	public List<User> searchByName(String name) {
		List<User> results = new ArrayList<>();
		
		for(User user: repository.findAll()) {
			if(user.getName().toLowerCase().contains(name.toLowerCase())) {
				results.add(user);
			}
		} return results;
	} 
	
	public void addUser(User user) {
		if(searchByEmail(user.getEmail()) != null) {
			throw new IllegalArgumentException("Já existe um usuário com este e-mail.");
		} 
		repository.save(user);
	}

	public void updateUser(User user) {
		User existingUser = repository.findById(user.getId());
		
		if(existingUser == null) {
			return;
		}
		existingUser.setName(user.getName());
		existingUser.setNumber(user.getNumber());
		existingUser.setEmail(user.getEmail());
		existingUser.setAnddress(user.getAnddress());
		
		repository.update(existingUser);
	}
	
	public void removeUser(Long id) {
		User user = repository.findById(id);
		if (user == null) {
			return;
		}
		repository.delete(user);
	} 
}
