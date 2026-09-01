package view;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainController {

	@FXML
	private void abrirEmprestimos() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/LoanView.fxml"));

			Parent root = loader.load();
			Stage stage = new Stage();
			stage.setTitle("Gerenciar Empréstimos");
			stage.setScene(new Scene(root));
			stage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void abrirUsuarios() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/UserView.fxml"));

			Parent root = loader.load();
			Stage stage = new Stage();
			stage.setTitle("Gerenciar Usuários");
			stage.setScene(new Scene(root));
			stage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void abrirLivros() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/BookView.fxml"));

			Parent root = loader.load();
			Stage stage = new Stage();
			stage.setTitle("Gerenciar Livros");
			stage.setScene(new Scene(root));
			stage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	} 
}
