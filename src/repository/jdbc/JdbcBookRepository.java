package repository.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.DB;
import model.Book;
import repository.BookRepository;

public class JdbcBookRepository implements BookRepository {

	@Override
	public void save(Book book) {
		String sql = "INSERT INTO books " 
				+ "(name, author, cdd, number_of_copies, available_copies) "
				+ "VALUES (?, ?, ?, ?, ?)";

		try (Connection conn = DB.getConection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, book.getName());
			stmt.setString(2, book.getAuthor());
			stmt.setInt(3, book.getCdd());
			stmt.setInt(4, book.getNumberOfCopies());
			stmt.setInt(5, book.getAvailableCopies());

			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Book> findAll() {
		List<Book> books = new ArrayList<>();

		String sql = "SELECT * FROM books";

		try(Connection conn = DB.getConection();
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {

			while(rs.next()) {

				Long id = rs.getLong("id");
				String name = rs.getNString("name");
				String author = rs.getNString("author");
				int cdd = rs.getInt("cdd");
				int numberOfCopies = rs.getInt("number_of_copies");
				int availableCopies = rs.getInt("available_copies");

				Book book = new Book(id, name, author, cdd, numberOfCopies, availableCopies);
				books.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return books;
	}

	@Override
	public Book findById(Long id) {
		String sql = "SELECT * FROM books WHERE id = ?";

		try(Connection conn = DB.getConection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setLong(1, id);

			try (ResultSet rs = stmt.executeQuery()) {
				if(rs.next()) {
					Long bookId = rs.getLong("id");
					String name = rs.getNString("name");
					String author = rs.getString("author");
					int cdd = rs.getInt("cdd");
					int numberOfCopies = rs.getInt("number_of_copies");
					int availableCopies = rs.getInt("available_copies");

					return new Book(bookId, name, author, cdd, numberOfCopies, availableCopies);
				} 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void delete(Book book) {
		String sql = "DELETE FROM books WHERE id = ?";

		try(Connection conn = DB.getConection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setLong(1, book.getId()); 

			int rows = stmt.executeUpdate();

			System.out.println("Linhas deletadas: " + rows);

		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}

	@Override
	public void update(Book book) {

		String sql = "UPDATE books SET "
				+ "name = ?, "
				+ "author = ?, "
				+ "cdd = ?, "
				+ "number_of_copies = ?, "
				+ "available_copies = ? "
				+ "WHERE id = ?";

		try (Connection conn = DB.getConection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, book.getName());
			stmt.setString(2, book.getAuthor());
			stmt.setInt(3, book.getCdd());
			stmt.setInt(4, book.getNumberOfCopies());
			stmt.setInt(5, book.getAvailableCopies());
			stmt.setLong(6, book.getId());

			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
