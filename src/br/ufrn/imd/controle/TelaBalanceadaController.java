package br.ufrn.imd.controle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public final class TelaBalanceadaController  {
	
	@SuppressWarnings("unused")
	private Stage balanceadaStage;

	@FXML 
	private BorderPane rootContainer;
	@FXML 
	private TextArea textArea;
	@FXML 
	private TextField inputField;

	//private GraficosArvore graphicsTree;
	
	@FXML 
	private void insert(ActionEvent event) {
		
	}
	
	@FXML 
	private void search(ActionEvent event) {
		
	}

	@FXML 
	private void delete(ActionEvent event) {
		
	}

	/*private void clearTree() {
		graphicsTree.makeEmpty();
		textArea.setText("");
	} */
	
	@FXML 
	private void clear(ActionEvent event) {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Você quer limpar toda árvore?", ButtonType.OK);
		alert.showAndWait().filter(response -> response == ButtonType.OK);

	}

	@FXML 
	private void inorder(ActionEvent event) {
		
	}

	@FXML 
	private void preorder(ActionEvent event) {
		
	}

	@FXML 
	private void postorder(ActionEvent event) {

	}
	
	public void setBalanceadaStage(Stage balanceadaStage) {
		this.balanceadaStage = balanceadaStage;
	}
}
