package view;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import model.Book;
import model.Loan;
import model.User;

import service.BookService;
import service.LoanService;
import service.UserService;

public class LoanController {

	@FXML
	private ComboBox<User> userComboBox;

	@FXML
	private ComboBox<Book> bookComboBox;

	@FXML
	private TableView<Loan> loansTable;

	@FXML
	private TableColumn<Loan, Long> idColumn;

	@FXML
	private TableColumn<Loan, String> bookColumn;

	@FXML
	private TableColumn<Loan, String> userColumn;

	@FXML
	private TableColumn<Loan, String> loanDateColumn;

	@FXML
	private TableColumn<Loan, String> expectedReturnColumn;

	@FXML
	private TableColumn<Loan, String> returnDateColumn;

	@FXML
	private TableColumn<Loan, String> statusColumn;

	private LoanService loanService = new LoanService();
	private UserService userService = new UserService();
	private BookService bookService = new BookService();

	@FXML
	public void initialize() {
		configurarTabela();
		carregarUsuarios();
		carregarLivros();
		carregarEmprestimos();
	}

	private void configurarTabela() {

		idColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getId()));

		bookColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getBook().getName()));

		userColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUser().getName()));

		loanDateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(
				String.valueOf(cellData.getValue().getLoanDate())));

		expectedReturnColumn.setCellValueFactory(cellData -> new SimpleStringProperty(
				String.valueOf(cellData.getValue().getExpectedReturnDate())));

		returnDateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(
				String.valueOf(cellData.getValue().getReturnDate())));

		statusColumn.setCellValueFactory(cellData -> new SimpleStringProperty(
				cellData.getValue().isActive()
				? "ATIVO"
						: "DEVOLVIDO"));
	}

	private void carregarUsuarios() {
		userComboBox.getItems().clear();
		userComboBox.getItems().addAll(userService.listUsers());

		userComboBox.setConverter(new javafx.util.StringConverter<User>() {
			@Override
			public String toString(User user) {
				if (user == null) {
					return "";
				}
				return user.getId()
						+ " - "
						+ user.getName();
			}

			@Override
			public User fromString(String string) {
				return null;
			}
		}
				);
	}

	private void carregarLivros() {
		bookComboBox.getItems().clear();
		bookComboBox.getItems().addAll(bookService.listBooks());

		bookComboBox.setConverter(new javafx.util.StringConverter<Book>() {

			@Override
			public String toString(Book book) {
				if (book == null) {
					return "";
				}

				return book.getId()
						+ " - "
						+ book.getName()
						+ " (Disponíveis: "
						+ book.getAvailableCopies()
						+ ")";
			}

			@Override
			public Book fromString(String string) {
				return null;
			}
		}
				);
	}

	private void carregarEmprestimos() {
		loansTable.getItems().clear();
		loansTable.getItems().addAll(loanService.listLoans());
	}

	@FXML
	private void handleSave() {
		User user = userComboBox.getValue();
		Book book = bookComboBox.getValue();

		if (user == null) {
			mostrarAlerta("Atenção", "Selecione um usuário.");
			return;
		}

		if (book == null) {
			mostrarAlerta("Atenção", "Selecione um livro.");
			return;
		}

		if (book.getAvailableCopies() <= 0) {
			mostrarAlerta("Atenção", "Este livro não possui exemplares disponíveis.");
			return;
		}

		try {
			Loan loan = loanService.makeLoan(user, book);

			mostrarAlerta("Sucesso", "Empréstimo realizado com sucesso!\n\n" + "Livro: " + book.getName() + "\n" + "Usuário: " + user.getName()
					);

			carregarEmprestimos();
			carregarLivros();
			userComboBox.setValue(null);
			bookComboBox.setValue(null);

		} catch (Exception e) {
			mostrarAlerta("Erro", "Não foi possível realizar o empréstimo.\n" + e.getMessage());
		}
	}

	@FXML
	private void handleReturn() {
		Loan loan = loansTable.getSelectionModel().getSelectedItem();

		if (loan == null) {
			mostrarAlerta("Atenção", "Selecione um empréstimo na tabela.");
			return;
		}

		if (!loan.isActive()) {
			mostrarAlerta("Atenção", "Este empréstimo já foi devolvido.");
			return;
		}

		try {
			loanService.returnLoan(loan.getId());

			mostrarAlerta("Sucesso", "Livro devolvido com sucesso!");
			carregarEmprestimos();
			carregarLivros();

		} catch (Exception e) {
			mostrarAlerta("Erro", "Não foi possível devolver o livro.\n" + e.getMessage());
		}
	}

	@FXML
	private void handleDelete() {
		Loan loan = loansTable.getSelectionModel().getSelectedItem();

		if (loan == null) {
			mostrarAlerta( "Atenção", "Selecione um empréstimo na tabela.");
			return;
		}

		try {
			loanService.removeLoan(loan.getId());

			mostrarAlerta("Sucesso", "Empréstimo removido com sucesso!");
			carregarEmprestimos();
			carregarLivros();

		} catch (Exception e) {
			mostrarAlerta("Erro", "Não foi possível remover o empréstimo.\n" + e.getMessage());
		}
	}

	@FXML
	private void handleTableClick() {
		Loan loan = loansTable.getSelectionModel().getSelectedItem();

		if (loan == null) {
			return;
		}

		userComboBox.setValue(loan.getUser());
		bookComboBox.setValue(loan.getBook());
	}

	private void mostrarAlerta(String titulo, String mensagem) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);

		alert.setTitle(titulo);
		alert.setHeaderText(null);
		alert.setContentText(mensagem);
		alert.showAndWait();
	}
}