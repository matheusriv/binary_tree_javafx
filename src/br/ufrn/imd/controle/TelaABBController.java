package br.ufrn.imd.controle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public final class TelaABBController implements Initializable {
	
	@SuppressWarnings("unused")
	private Stage abbStage;

	@FXML 
	private BorderPane rootContainer;
	@FXML 
	private TextArea textArea;
	@FXML 
	private TextField inputField;

	private GraficosABB graphicsBSTree;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		graphicsBSTree = new GraficosABB();
		// Add the panels onto the border pane
		rootContainer.setCenter(graphicsBSTree);
		// Bind canvas size to stack pane size.
		graphicsBSTree.widthProperty().bind(rootContainer.widthProperty());
		graphicsBSTree.heightProperty().bind(rootContainer.heightProperty().subtract(50));
	}
	
	@FXML 
	private void inserir(ActionEvent event) {
		try {
			graphicsBSTree.insert(Integer.parseInt(inputField.getText().trim()));
		} catch (NumberFormatException nfe) {
			Alert alert = new Alert(Alert.AlertType.ERROR, "Erro. A entrada só aceita números.", ButtonType.OK);
			alert.showAndWait().filter(response -> response == ButtonType.OK).ifPresent(response -> alert.close());
			return;
		}
		textArea.setText("");
	}
	
	@FXML 
	private void procurar(ActionEvent event) {
		boolean found = false;
		try {
			found = graphicsBSTree.search(Integer.parseInt(inputField.getText().trim()));
		} catch (NumberFormatException nfe) {
			Alert alert = new Alert(Alert.AlertType.ERROR, "Erro. A entrada só aceita números.", ButtonType.OK);
			alert.showAndWait().filter(response -> response == ButtonType.OK).ifPresent(response -> alert.close());
		}
		
		if(found) { 
			textArea.setText(inputField.getText() + " encontrado");
		} else {
			textArea.setText("Não Encontrado");
		}
	}

	@FXML 
	private void apagar(ActionEvent event) {
		boolean found = false;
		try {
			found = graphicsBSTree.search(Integer.parseInt(inputField.getText().trim()));
		} catch (NumberFormatException nfe) {
			Alert alert = new Alert(Alert.AlertType.ERROR, "Erro. A entrada só aceita números.", ButtonType.OK);
			alert.showAndWait().filter(response -> response == ButtonType.OK).ifPresent(response -> alert.close());
		}
		
		if(found) { 
			graphicsBSTree.delete(Integer.parseInt(inputField.getText().trim()));
			textArea.setText("");
		} else {
			textArea.setText("Não Encontrado");
		}
	}
	
	@FXML 
	private void limpar(ActionEvent event) {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Você quer limpar toda árvore?", ButtonType.OK);
		alert.showAndWait().filter(response -> response == ButtonType.OK).ifPresent(response -> clearTree());

	}
	
	private void clearTree() {
		graphicsBSTree.makeEmpty();
		textArea.setText("");
	}

	@FXML 
	private void inorder(ActionEvent event) {
		graphicsBSTree.setInorder();
		textArea.setText("Em ordem: ".concat(graphicsBSTree.getStringIterator()));
	}

	@FXML 
	private void preorder(ActionEvent event) {
		graphicsBSTree.setPreorder();
		textArea.setText("Pré-ordem: ".concat(graphicsBSTree.getStringIterator()));
	}

	@FXML 
	private void postorder(ActionEvent event) {
		graphicsBSTree.setPostorder();
		textArea.setText("Pós-ordem: ".concat(graphicsBSTree.getStringIterator()));
	}
	
	public void setABBStage(Stage abbStage) {
		this.abbStage = abbStage;
	}
}
