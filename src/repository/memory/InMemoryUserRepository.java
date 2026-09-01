package repository.memory;

import java.util.ArrayList;
import java.util.List;

import model.User;
import repository.UserRepository;

public class InMemoryUserRepository implements UserRepository {

	private List<User> users = new ArrayList<>();
	private Long nextId = 1L;

	@Override
	public void save(User user) {
		user.setId(nextId);
		nextId++;

		users.add(user);
	}

	@Override
	public List<User> findAll() {
		return List.copyOf(users);
	}

	@Override
	public User findById(Long id) {
		for(User user: users) {
			if(id.equals(user.getId())) {
				return user;
			}
		} return null;
	}

	@Override
	public void delete(User user) {
		users.remove(user);
	}

	@Override
	public void update(User user) {
	}	
}
