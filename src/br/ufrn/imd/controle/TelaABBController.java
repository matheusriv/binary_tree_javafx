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
			found = graphicsBSTree.search(Integer.parseInt(inputField.getText().trim()));
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
			found = graphicsBSTree.search(Integer.parseInt(inputField.getText().trim()));
		} catch (NumberFormatException nfe) {
			Alert alert = new Alert(Alert.AlertType.ERROR, "Error. The input field can only accept numbers.", ButtonType.OK);
			alert.showAndWait().filter(response -> response == ButtonType.OK).ifPresent(response -> alert.close());
		}
		
		if(found) { 
			graphicsBSTree.delete(Integer.parseInt(inputField.getText().trim()));
			textArea.setText("");
		} else {
			textArea.setText("Not found");
		}
	}
	
	@FXML 
	private void limpar(ActionEvent event) {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to empty the tree?", ButtonType.OK);
		alert.showAndWait().filter(response -> response == ButtonType.OK).ifPresent(response -> clearTree());

	}
	
	private void clearTree() {
		graphicsBSTree.makeEmpty();
		textArea.setText("");
	}

	@FXML 
	private void inorder(ActionEvent event) {
		graphicsBSTree.setInorder();
		textArea.setText("Inorder: ".concat(graphicsBSTree.getStringIterator()));
	}

	@FXML 
	private void preorder(ActionEvent event) {
		graphicsBSTree.setPreorder();
		textArea.setText("Preorder: ".concat(graphicsBSTree.getStringIterator()));
	}

	@FXML 
	private void postorder(ActionEvent event) {
		graphicsBSTree.setPostorder();
		textArea.setText("Postorder: ".concat(graphicsBSTree.getStringIterator()));
	}
	
	public void setABBStage(Stage abbStage) {
		this.abbStage = abbStage;
	}
}
