package view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.User;
import service.UserService;

public class UserController {

    @FXML
    private TextField nameField;

    @FXML
    private TextField numberField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField addressField;

    @FXML
    private TableView<User> usersTable;

    @FXML
    private TableColumn<User, Long> idColumn;

    @FXML
    private TableColumn<User, String> nameColumn;

    @FXML
    private TableColumn<User, String> numberColumn;

    @FXML
    private TableColumn<User, String> emailColumn;

    @FXML
    private TableColumn<User, String> addressColumn;

    private UserService userService = new UserService();


    // ==============================
    // INICIALIZAÇÃO
    // ==============================

    @FXML
    public void initialize() {

        idColumn.setCellValueFactory(
                cellData -> new javafx.beans.property.SimpleObjectProperty<>(
                        cellData.getValue().getId()
                )
        );

        nameColumn.setCellValueFactory(
                cellData -> new javafx.beans.property.SimpleStringProperty(
                        cellData.getValue().getName()
                )
        );

        numberColumn.setCellValueFactory(
                cellData -> new javafx.beans.property.SimpleStringProperty(
                        cellData.getValue().getNumber()
                )
        );

        emailColumn.setCellValueFactory(
                cellData -> new javafx.beans.property.SimpleStringProperty(
                        cellData.getValue().getEmail()
                )
        );

        addressColumn.setCellValueFactory(
                cellData -> new javafx.beans.property.SimpleStringProperty(
                        cellData.getValue().getAnddress()
                )
        );

        carregarUsuarios();
    }


    // ==============================
    // CARREGAR USUÁRIOS
    // ==============================

    private void carregarUsuarios() {

        usersTable.getItems().clear();

        var usuarios = userService.listUsers();

        System.out.println("USUÁRIOS ENCONTRADOS: " + usuarios.size());

        for (User user : usuarios) {
            System.out.println(
                user.getId() + " - " +
                user.getName() + " - " +
                user.getEmail()
            );
        }

        usersTable.getItems().addAll(usuarios);
    }


    // ==============================
    // CADASTRAR
    // ==============================

    @FXML
    private void handleSave() {

        try {

            String name = nameField.getText();
            String number = numberField.getText();
            String email = emailField.getText();
            String address = addressField.getText();

            if (name.isBlank()
                    || number.isBlank()
                    || email.isBlank()
                    || address.isBlank()) {

                mostrarAlerta(
                        "Atenção",
                        "Preencha todos os campos."
                );

                return;
            }

            User user = new User(
                    name,
                    number,
                    email,
                    address
            );

            userService.addUser(user);

            mostrarAlerta(
                    "Sucesso",
                    "Usuário cadastrado com sucesso!"
            );

            limparCampos();

            carregarUsuarios();

        } catch (Exception e) {

            mostrarAlerta(
                    "Erro",
                    "Não foi possível cadastrar o usuário.\n"
                    + e.getMessage()
            );
        }
    }


    // ==============================
    // ATUALIZAR
    // ==============================

    @FXML
    private void handleUpdate() {

        User user = usersTable
                .getSelectionModel()
                .getSelectedItem();

        if (user == null) {

            mostrarAlerta(
                    "Atenção",
                    "Selecione um usuário na tabela."
            );

            return;
        }

        try {

            String name = nameField.getText();
            String number = numberField.getText();
            String email = emailField.getText();
            String address = addressField.getText();

            if (name.isBlank()
                    || number.isBlank()
                    || email.isBlank()
                    || address.isBlank()) {

                mostrarAlerta(
                        "Atenção",
                        "Preencha todos os campos."
                );

                return;
            }

            user.setName(name);
            user.setNumber(number);
            user.setEmail(email);
            user.setAnddress(address);

            userService.updateUser(user);

            mostrarAlerta(
                    "Sucesso",
                    "Usuário atualizado com sucesso!"
            );

            carregarUsuarios();

            limparCampos();

        } catch (Exception e) {

            mostrarAlerta(
                    "Erro",
                    "Não foi possível atualizar o usuário.\n"
                    + e.getMessage()
            );
        }
    }


    // ==============================
    // REMOVER
    // ==============================

    @FXML
    private void handleDelete() {

        User user = usersTable
                .getSelectionModel()
                .getSelectedItem();

        if (user == null) {

            mostrarAlerta(
                    "Atenção",
                    "Selecione um usuário na tabela."
            );

            return;
        }

        try {

            userService.removeUser(user.getId());

            mostrarAlerta(
                    "Sucesso",
                    "Usuário removido com sucesso!"
            );

            carregarUsuarios();

            limparCampos();

        } catch (Exception e) {

            mostrarAlerta(
                    "Erro",
                    "Não foi possível remover o usuário.\n"
                    + e.getMessage()
            );
        }
    }


    // ==============================
    // CLIQUE NA TABELA
    // ==============================

    @FXML
    private void handleTableClick() {

        User user = usersTable
                .getSelectionModel()
                .getSelectedItem();

        if (user != null) {

            nameField.setText(
                    user.getName()
            );

            numberField.setText(
                    user.getNumber()
            );

            emailField.setText(
                    user.getEmail()
            );

            addressField.setText(
                    user.getAnddress()
            );
        }
    }


    // ==============================
    // LIMPAR CAMPOS
    // ==============================

    private void limparCampos() {

        nameField.clear();
        numberField.clear();
        emailField.clear();
        addressField.clear();
    }


    // ==============================
    // ALERTA
    // ==============================

    private void mostrarAlerta(
            String titulo,
            String mensagem) {

        Alert alert = new Alert(
                Alert.AlertType.INFORMATION
        );

        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);

        alert.showAndWait();
    }
}