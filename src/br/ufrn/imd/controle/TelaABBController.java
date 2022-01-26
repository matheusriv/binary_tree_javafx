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

	private GraficosArvore graphicsTree;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// The center panel for drawing the tree
		graphicsTree = new GraficosArvore();
		// Add the panels onto the border pane
		rootContainer.setCenter(graphicsTree);
		// Bind canvas size to stack pane size.
		graphicsTree.widthProperty().bind(rootContainer.widthProperty());
		graphicsTree.heightProperty().bind(rootContainer.heightProperty().subtract(50));
	}
	
	@FXML 
	private void insert(ActionEvent event) {
		try {
			graphicsTree.insert(Integer.parseInt(inputField.getText().trim()));
		} catch (NumberFormatException nfe) {
			Alert alert = new Alert(Alert.AlertType.ERROR, "Erro. A entrada só aceita números.", ButtonType.OK);
			alert.showAndWait().filter(response -> response == ButtonType.OK).ifPresent(response -> alert.close());
		}
	}
	
	@FXML 
	private void search(ActionEvent event) {
		try {
			graphicsTree.search(Integer.parseInt(inputField.getText().trim()));
		} catch (NumberFormatException nfe) {
			Alert alert = new Alert(Alert.AlertType.ERROR, "Erro. A entrada só aceita números.", ButtonType.OK);
			alert.showAndWait().filter(response -> response == ButtonType.OK).ifPresent(response -> alert.close());

		}
	}

	@FXML 
	private void delete(ActionEvent event) {
		try {
			graphicsTree.delete(Integer.parseInt(inputField.getText().trim()));
		} catch (NumberFormatException nfe) {
			Alert alert = new Alert(Alert.AlertType.ERROR, "Erro. A entrada só aceita números.", ButtonType.OK);
			alert.showAndWait().filter(response -> response == ButtonType.OK).ifPresent(response -> alert.close());
		}
	}
	
	@FXML 
	private void clear(ActionEvent event) {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Você quer limpar toda árvore?", ButtonType.OK);
		alert.showAndWait().filter(response -> response == ButtonType.OK).ifPresent(response -> clearTree());

	}
	
	private void clearTree() {
		graphicsTree.makeEmpty();
		textArea.setText("");
	}

	@FXML 
	private void inorder(ActionEvent event) {
		graphicsTree.setInorder();
		textArea.setText(graphicsTree.printTree());
	}

	@FXML 
	private void preorder(ActionEvent event) {
		graphicsTree.setPreorder();
		textArea.setText(graphicsTree.printTree());
	}

	@FXML 
	private void postorder(ActionEvent event) {
		graphicsTree.setPostorder();
		textArea.setText(graphicsTree.printTree());
	}
	
	public void setABBStage(Stage abbStage) {
		this.abbStage = abbStage;
	}
}
