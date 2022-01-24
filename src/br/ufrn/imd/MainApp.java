package br.ufrn.imd;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MainApp extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("visao/TelaInicial.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Árvores");
		stage.getIcons().add(new Image("/br/ufrn/imd/visao/icon.png"));
		stage.setResizable(false);
		stage.show();

	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
