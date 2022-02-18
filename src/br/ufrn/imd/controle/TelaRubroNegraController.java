package br.ufrn.imd.controle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public final class TelaRubroNegraController implements Initializable {
	
	@SuppressWarnings("unused")
	private Stage rubroNegraStage;

	@FXML 
	private BorderPane rootContainer;
	@FXML 
	private TextArea textArea;
	@FXML 
	private TextField inputField;

	private GraficosRubroNegra graphicsRedBlackTree;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		graphicsRedBlackTree = new GraficosRubroNegra();
		// Add the panels onto the border pane
		rootContainer.setCenter(graphicsRedBlackTree);
		// Bind canvas size to stack pane size.
		graphicsRedBlackTree.widthProperty().bind(rootContainer.widthProperty());
		graphicsRedBlackTree.heightProperty().bind(rootContainer.heightProperty().subtract(50));
	}
	
	@FXML 
	private void inserir(ActionEvent event) {
		try {
			graphicsRedBlackTree.insert(Integer.parseInt(inputField.getText().trim()));
		} catch (NumberFormatException nfe) {
			Alert alert = new Alert(Alert.AlertType.ERROR, "Error. The input field can only accept numbers.", ButtonType.OK);
			alert.showAndWait().filter(response -> response == ButtonType.OK).ifPresent(response -> alert.close());
			return;
		} 
		textArea.setText("");
	}
	
	@FXML 
	private void procurar(ActionEvent event) {
		boolean found = false;
		try {
			found = graphicsRedBlackTree.search(Integer.parseInt(inputField.getText().trim()));
		} catch (NumberFormatException nfe) {
			Alert alert = new Alert(Alert.AlertType.ERROR, "Error. The input field can only accept numbers.", ButtonType.OK);
			alert.showAndWait().filter(response -> response == ButtonType.OK).ifPresent(response -> alert.close());
		}
		
		if(found) { 
			textArea.setText(inputField.getText() + " found");
		} else {
			textArea.setText("Not Found");
		}
	}

	@FXML 
	private void apagar(ActionEvent event) {
		boolean found = false;
		try {
			found = graphicsRedBlackTree.search(Integer.parseInt(inputField.getText().trim()));
		} catch (NumberFormatException nfe) {
			Alert alert = new Alert(Alert.AlertType.ERROR, "Error. The input field can only accept numbers.", ButtonType.OK);
			alert.showAndWait().filter(response -> response == ButtonType.OK).ifPresent(response -> alert.close());
			return;
		}
		
		if(found) { 
			graphicsRedBlackTree.delete(Integer.parseInt(inputField.getText().trim()));
			textArea.setText("");
		} else {
			textArea.setText("Not Found");
		}
	}
	
	@FXML 
	private void limpar(ActionEvent event) {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Você quer limpar toda árvore?", ButtonType.OK);
		alert.showAndWait().filter(response -> response == ButtonType.OK).ifPresent(response -> clearTree());
	}
	
	private void clearTree() {
		graphicsRedBlackTree.makeEmpty();
		textArea.setText("");
	}

	@FXML 
	private void emOrdem(ActionEvent event) {
		graphicsRedBlackTree.setInorder();
		textArea.setText("Em ordem: ".concat(graphicsRedBlackTree.getStringIterator())); 
	}

	@FXML 
	private void preOrdem(ActionEvent event) {
		graphicsRedBlackTree.setPreorder();
		textArea.setText("Pré-ordem: ".concat(graphicsRedBlackTree.getStringIterator()));
	}

	@FXML 
	private void posOrdem(ActionEvent event) {
		graphicsRedBlackTree.setPostorder();
		textArea.setText("Pós-ordem: ".concat(graphicsRedBlackTree.getStringIterator())); 
	}
	
	public void setRubroNegraStage(Stage rubroNegraStage) {
		this.rubroNegraStage = rubroNegraStage;
	}
	
}
