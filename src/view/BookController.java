package view;

import javafx.scene.control.TextField;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import model.Book;
import service.BookService;

public class BookController {

	@FXML
	private TextField nameField;

	@FXML
	private TextField authorField;

	@FXML
	private TextField cddField;

	@FXML
	private TextField copiesField;

	@FXML
	private TableView<Book> booksTable;

	@FXML
	private TableColumn<Book, Long> idColumn;

	@FXML
	private TableColumn<Book, String> nameColumn;

	@FXML
	private TableColumn<Book, String> authorColumn;

	@FXML
	private TableColumn<Book, Integer> cddColumn;

	@FXML
	private TableColumn<Book, Integer> copiesColumn;

	@FXML
	private TableColumn<Book, Integer> availableColumn;

	private BookService bookService = new BookService();

	@FXML
	private void handleDelete() {

		Book book = booksTable.getSelectionModel().getSelectedItem();
		if (book == null) {
			mostrarAlerta("Atenção", "Selecione um livro para remover.");
			return;
		}
		Alert confirmacao = new Alert(Alert.AlertType.CONFIRMATION);

		confirmacao.setTitle("Confirmar remoção");
		confirmacao.setHeaderText(null);
		confirmacao.setContentText("Deseja realmente remover o livro \"" + book.getName() + "\"?");

		if (confirmacao.showAndWait().get().getText().equals("OK")) {
			try {
				bookService.removeBook(book.getId());
				mostrarAlerta("Sucesso", "Livro removido com sucesso!");

				carregarLivros();
				limparCampos();

			} catch (Exception e) {
				mostrarAlerta("Erro", "Não foi possível remover o livro.\n" + e.getMessage());
			}
		}
	}

	@FXML
	private void handleTableClick() {
		Book book = booksTable.getSelectionModel().getSelectedItem();

		if (book != null) {
			nameField.setText(book.getName());
			authorField.setText(book.getAuthor());
			cddField.setText(String.valueOf(book.getCdd()));
			copiesField.setText(String.valueOf(book.getNumberOfCopies()));
		}
	}

	@FXML
	private void handleUpdate() {
		Book book = booksTable.getSelectionModel().getSelectedItem();

		if (book == null) {
			mostrarAlerta("Atenção", "Selecione um livro para atualizar.");
			return;
		}

		try {
			String name = nameField.getText();
			String author = authorField.getText();

			int cdd = Integer.parseInt(cddField.getText());
			int copies = Integer.parseInt(copiesField.getText());

			if (name.isBlank() || author.isBlank()) {
				mostrarAlerta("Atenção", "Preencha o nome e o autor.");
				return;
			}

			if (copies <= 0) {
				mostrarAlerta("Atenção", "O número de exemplares deve ser maior que zero.");
				return;
			}

			book.setName(name);
			book.setAuthor(author);
			book.setCdd(cdd);
			book.setNumberOfCopies(copies);

			bookService.updateBook(book);

			mostrarAlerta("Sucesso", "Livro atualizado com sucesso!");

			carregarLivros();
			limparCampos();

		} catch (NumberFormatException e) {
			mostrarAlerta("Erro", "CDD e número de exemplares devem ser números.");

		} catch (Exception e) {
			mostrarAlerta("Erro", "Não foi possível atualizar o livro.\n" + e.getMessage());
		}
	}

	private void carregarLivros() {

		try {
			ObservableList<Book> livros = FXCollections.observableArrayList(bookService.listBooks());
			booksTable.setItems(livros);

		} catch (Exception e) {
			mostrarAlerta("Erro", "Não foi possível carregar os livros.\n" + e.getMessage());
		}
	}

	@FXML
	public void initialize() {
		idColumn.setCellValueFactory(
				cellData -> new SimpleObjectProperty<>(cellData.getValue().getId()));

		nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));

		authorColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAuthor()));

		cddColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getCdd()));

		copiesColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getNumberOfCopies()));

		availableColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getAvailableCopies()));

		carregarLivros();
	}

	@FXML
	private void handleSave() {
		try {
			String name = nameField.getText();
			String author = authorField.getText();

			int cdd = Integer.parseInt(cddField.getText());
			int copies = Integer.parseInt(copiesField.getText());

			if (name.isBlank() || author.isBlank()) {
				mostrarAlerta("Atenção", "Preencha o nome e o autor.");
				return;
			}

			if (copies <= 0) {
				mostrarAlerta("Atenção", "O número de exemplares deve ser maior que zero.");
				return;
			}

			Book book = new Book(name, author, cdd, copies);
			bookService.addBook(book);

			mostrarAlerta("Sucesso", "Livro cadastrado com sucesso!");
			limparCampos();

		} catch (NumberFormatException e) {
			mostrarAlerta("Erro", "CDD e número de exemplares devem ser números.");

		} catch (Exception e) {
			mostrarAlerta("Erro", "Não foi possível cadastrar o livro.\n" + e.getMessage());
		}
	}

	private void limparCampos() {
		nameField.clear();
		authorField.clear();
		cddField.clear();
		copiesField.clear();
	}

	private void mostrarAlerta(String titulo, String mensagem) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);

		alert.setTitle(titulo);
		alert.setHeaderText(null);
		alert.setContentText(mensagem);
		alert.showAndWait();
	}
}
