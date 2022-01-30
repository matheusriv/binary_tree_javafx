package br.ufrn.imd.controle;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class TelaInicialController {
	
	private Stage abbStage;
	private Stage balanceadaStage;
	private Stage rubroNegraStage;
	
	@FXML
	private Button btnABB;
	@FXML
	private Button btnBalanceada;
	@FXML
	private Button btnRubroNegra;
	
	@FXML
	void abrirABB(ActionEvent event) throws IOException {
		if(abbStage == null || !abbStage.isShowing()) {
			FXMLLoader loader = new FXMLLoader();
	    	loader.setLocation(TelaABBController.class.getResource("/br/ufrn/imd/visao/TelaABB.fxml"));
	    	BorderPane page = (BorderPane) loader.load();
	    	
	    	abbStage = new Stage();
	    	abbStage.setTitle("Árvore Binária de Busca");
	    	abbStage.getIcons().add(new Image("/br/ufrn/imd/visao/icon.png"));
	    	Scene scene = new Scene(page);
	    	abbStage.setScene(scene);
	    	
	    	TelaABBController controller = loader.getController();
	    	controller.setABBStage(abbStage);
	    	abbStage.showAndWait();
		}
		else {
			abbStage.toFront();
		}
	}

    @FXML
    void abrirBalanceada(ActionEvent event) throws IOException {
    	if(balanceadaStage == null || !balanceadaStage.isShowing()) {
			FXMLLoader loader = new FXMLLoader();
	    	loader.setLocation(TelaBalanceadaController.class.getResource("/br/ufrn/imd/visao/TelaBalanceada.fxml"));
	    	BorderPane page = (BorderPane) loader.load();
	    	
	    	balanceadaStage = new Stage();
	    	balanceadaStage.setTitle("Árvore Binária de Busca Balanceada");
	    	balanceadaStage.getIcons().add(new Image("/br/ufrn/imd/visao/icon.png"));
	    	Scene scene = new Scene(page);
	    	balanceadaStage.setScene(scene);
	    	
	    	TelaBalanceadaController controller = loader.getController();
	    	controller.setBalanceadaStage(balanceadaStage);
	    	balanceadaStage.showAndWait();
		}
		else {
			balanceadaStage.toFront();
		} 
    }

    @FXML
    void abrirRubroNegra(ActionEvent event) throws IOException {
    	if(rubroNegraStage == null || !rubroNegraStage.isShowing()) {
			FXMLLoader loader = new FXMLLoader();
	    	loader.setLocation(TelaRubroNegraController.class.getResource("/br/ufrn/imd/visao/TelaRubroNegra.fxml"));
	    	BorderPane page = (BorderPane) loader.load();
	    	
	    	// Criando um novo Stage
	    	rubroNegraStage = new Stage();
	    	rubroNegraStage.setTitle("Árvore Rubro-Negra");
	    	rubroNegraStage.getIcons().add(new Image("/br/ufrn/imd/visao/icon.png"));
	    	Scene scene = new Scene(page);
	    	rubroNegraStage.setScene(scene);
	    	
	    	// Setando o Controle 
	    	TelaRubroNegraController controller = loader.getController();
	    	controller.setRubroNegraStage(rubroNegraStage);
	    	rubroNegraStage.showAndWait();
		}
		else {
			rubroNegraStage.toFront();
		} 
    }


}
