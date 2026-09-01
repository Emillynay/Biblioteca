package repository.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.DB;
import model.User;
import repository.UserRepository;

public class JdbcUserRepository implements UserRepository {

	@Override
	public void save(User user) {

		String sql = "INSERT INTO users"
				+ "(name, number, email, anddress)"
				+ "VALUES(?, ?, ?, ?)";

		try (Connection conn = DB.getConection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, user.getName());
			stmt.setString(2, user.getNumber());
			stmt.setString(3, user.getEmail());
			stmt.setString(4, user.getAnddress());

			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<User> findAll() {

		List<User> users = new ArrayList<>();

		String sql = "SELECT * FROM users";

		try(Connection conn = DB.getConection();
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {

			while(rs.next()) {
				Long id = rs.getLong("id");
				String name = rs.getString("name");
				String number = rs.getString("number");
				String email = rs.getString("email");
				String anddress = rs.getString("anddress");

				User user = new User(id, name, number, email, anddress);
				users.add(user);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		} return users;
	} 

	@Override
	public User findById(Long id) {

		String sql = "SELECT * FROM users WHERE id = ?";

		try(Connection conn = DB.getConection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setLong(1, id);

			try (ResultSet rs = stmt.executeQuery()) {
				if(rs.next()) {
					Long userId = rs.getLong("id");
					String name = rs.getString("name");
					String number = rs.getString("number");
					String email = rs.getString("email");
					String anddress = rs.getString("anddress");

					return new User(userId, name, number, email, anddress);
				} 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void delete(User user) {
		String sql = "DELETE FROM users WHERE id = ?";

		try(Connection conn = DB.getConection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setLong(1, user.getId()); 
			int rows = stmt.executeUpdate();

			System.out.println("Linhas deletadas: " + rows);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(User user) {

		String sql = "UPDATE users SET "
				+ "name = ?, "
				+ "number = ?, "
				+ "email = ?, "
				+ "anddress = ? "
				+ "WHERE id = ? ";

		try (Connection conn = DB.getConection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, user.getName());
			stmt.setString(2, user.getNumber());
			stmt.setString(3, user.getEmail());
			stmt.setString(4, user.getAnddress());
			stmt.setLong(5, user.getId());

			int rows = stmt.executeUpdate();

			System.out.println("Linhas atualizadas: " + rows);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
