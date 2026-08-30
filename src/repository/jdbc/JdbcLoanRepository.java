package repository.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.DB;
import model.Book;
import model.Loan;
import model.User;
import repository.LoanRepository;

public class JdbcLoanRepository implements LoanRepository {

    private JdbcBookRepository bookRepository = new JdbcBookRepository();
    private JdbcUserRepository userRepository = new JdbcUserRepository();

    @Override
    public void save(Loan loan) {

    	String sql = "INSERT INTO loans "
                + "(book_id, user_id, loan_date, expected_return_date, return_date) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DB.getConection();
             PreparedStatement stmt = conn.prepareStatement(
                     sql,
                     java.sql.Statement.RETURN_GENERATED_KEYS)) {

            stmt.setLong(1, loan.getBook().getId());
            stmt.setLong(2, loan.getUser().getId());
            stmt.setDate(3, java.sql.Date.valueOf(loan.getLoanDate()));
            stmt.setDate(4,
                    java.sql.Date.valueOf(loan.getExpectedReturnDate()));

            if (loan.getReturnDate() != null) {
                stmt.setDate(
                        5,
                        java.sql.Date.valueOf(loan.getReturnDate())
                );
            } else {
                stmt.setNull(5, java.sql.Types.DATE);
            }

            stmt.executeUpdate();

            // Recupera o ID gerado pelo MySQL
            try (ResultSet rs = stmt.getGeneratedKeys()) {

                if (rs.next()) {
                    loan.setId(rs.getLong(1));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Loan> findAll() {

        List<Loan> loans = new ArrayList<>();

        String sql = "SELECT * FROM loans";

        try (Connection conn = DB.getConection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                Long id = rs.getLong("id");
                Long bookId = rs.getLong("book_id");
                Long userId = rs.getLong("user_id");

                java.sql.Date loanSqlDate = rs.getDate("loan_date");
                java.sql.Date expectedSqlDate =
                        rs.getDate("expected_return_date");
                java.sql.Date returnSqlDate =
                        rs.getDate("return_date");

                Book book = bookRepository.findById(bookId);
                User user = userRepository.findById(userId);

                Loan loan = new Loan(
                        id,
                        book,
                        user,
                        loanSqlDate.toLocalDate(),
                        expectedSqlDate.toLocalDate()
                );

                if (returnSqlDate != null) {
                    loan.setReturnDate(returnSqlDate.toLocalDate());
                }

                loans.add(loan);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return loans;
    }

    @Override
    public Loan findById(Long id) {

        String sql = "SELECT * FROM loans WHERE id = ?";

        try (Connection conn = DB.getConection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {

                    Long loanId = rs.getLong("id");
                    Long bookId = rs.getLong("book_id");
                    Long userId = rs.getLong("user_id");

                    java.sql.Date loanSqlDate =
                            rs.getDate("loan_date");

                    java.sql.Date expectedSqlDate =
                            rs.getDate("expected_return_date");

                    java.sql.Date returnSqlDate =
                            rs.getDate("return_date");

                    Book book = bookRepository.findById(bookId);
                    User user = userRepository.findById(userId);

                    Loan loan = new Loan(
                            loanId,
                            book,
                            user,
                            loanSqlDate.toLocalDate(),
                            expectedSqlDate.toLocalDate()
                    );

                    if (returnSqlDate != null) {
                        loan.setReturnDate(returnSqlDate.toLocalDate());
                    }

                    return loan;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void delete(Loan loan) {

        String sql = "DELETE FROM loans WHERE id = ?";

        try (Connection conn = DB.getConection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, loan.getId());

            int rows = stmt.executeUpdate();

            System.out.println("Linhas deletadas: " + rows);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Loan loan) {

        String sql = "UPDATE loans SET "
                + "book_id = ?, "
                + "user_id = ?, "
                + "loan_date = ?, "
                + "expected_return_date = ?, "
                + "return_date = ? "
                + "WHERE id = ?";

        try (Connection conn = DB.getConection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, loan.getBook().getId());
            stmt.setLong(2, loan.getUser().getId());
            stmt.setDate(3, java.sql.Date.valueOf(loan.getLoanDate()));
            stmt.setDate(4,
                    java.sql.Date.valueOf(loan.getExpectedReturnDate()));

            if (loan.getReturnDate() != null) {
                stmt.setDate(
                        5,
                        java.sql.Date.valueOf(loan.getReturnDate())
                );
            } else {
                stmt.setNull(5, java.sql.Types.DATE);
            }

            stmt.setLong(6, loan.getId());

            int rows = stmt.executeUpdate();

            System.out.println("Linhas atualizadas: " + rows);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}