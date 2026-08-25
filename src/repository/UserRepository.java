package repository;

import java.util.List;

import model.User;

public interface UserRepository {
	
	void save(User user);
	
	List<User> findAll();
	
	User findById(Long id);
	
	void delete(User user);
}
