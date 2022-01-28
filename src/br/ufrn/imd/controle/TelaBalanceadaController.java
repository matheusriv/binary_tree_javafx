package br.ufrn.imd.controle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public final class TelaBalanceadaController implements Initializable {
	
	@SuppressWarnings("unused")
	private Stage balanceadaStage;

	@FXML 
	private BorderPane rootContainer;
	@FXML 
	private TextArea textArea;
	@FXML 
	private TextField inputField;

	private GraficosBalanceada graphicsAVLTree;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		graphicsAVLTree = new GraficosBalanceada();
		// Add the panels onto the border pane
		rootContainer.setCenter(graphicsAVLTree);
		// Bind canvas size to stack pane size.
		graphicsAVLTree.widthProperty().bind(rootContainer.widthProperty());
		graphicsAVLTree.heightProperty().bind(rootContainer.heightProperty().subtract(50));
	}
	
	@FXML 
	private void inserir(ActionEvent event) {
		try {
			graphicsAVLTree.insert(Integer.parseInt(inputField.getText().trim()));
		} catch (NumberFormatException nfe) {
			Alert alert = new Alert(Alert.AlertType.ERROR, "Erro. A entrada só aceita números.", ButtonType.OK);
			alert.showAndWait().filter(response -> response == ButtonType.OK).ifPresent(response -> alert.close());
		}
	}
	
	@FXML 
	private void procurar(ActionEvent event) {
		try {
			graphicsAVLTree.search(Integer.parseInt(inputField.getText().trim()));
		} catch (NumberFormatException nfe) {
			Alert alert = new Alert(Alert.AlertType.ERROR, "Erro. A entrada só aceita números.", ButtonType.OK);
			alert.showAndWait().filter(response -> response == ButtonType.OK).ifPresent(response -> alert.close());

		}
	}

	@FXML 
	private void apagar(ActionEvent event) {
		try {
			graphicsAVLTree.delete(Integer.parseInt(inputField.getText().trim()));
		} catch (NumberFormatException nfe) {
			Alert alert = new Alert(Alert.AlertType.ERROR, "Erro. A entrada só aceita números.", ButtonType.OK);
			alert.showAndWait().filter(response -> response == ButtonType.OK).ifPresent(response -> alert.close());
		}
	}
	
	@FXML 
	private void limpar(ActionEvent event) {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Você quer limpar toda árvore?", ButtonType.OK);
		alert.showAndWait().filter(response -> response == ButtonType.OK).ifPresent(response -> clearTree());

	}
	
	private void clearTree() {
		graphicsAVLTree.makeEmpty();
		textArea.setText("");
	}

	@FXML 
	private void inorder(ActionEvent event) {
		graphicsAVLTree.setInorder();
		String percurso = "Em ordem: "; 
		textArea.setText(percurso.concat(graphicsAVLTree.printTree()));
	}

	@FXML 
	private void preorder(ActionEvent event) {
		graphicsAVLTree.setPreorder();
		String percurso = "Pré-ordem: "; 
		textArea.setText(percurso.concat(graphicsAVLTree.printTree()));
	}

	@FXML 
	private void postorder(ActionEvent event) {
		graphicsAVLTree.setPostorder();
		String percurso = "Pós-ordem: "; 
		textArea.setText(percurso.concat(graphicsAVLTree.printTree()));
	}
	
	public void setBalanceadaStage(Stage balanceadaStage) {
		this.balanceadaStage = balanceadaStage;
	}
	
}
