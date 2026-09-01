
module Biblioteca {
	requires java.sql;
	requires java.desktop;
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	exports aplication;
	opens view to javafx.fxml;
}