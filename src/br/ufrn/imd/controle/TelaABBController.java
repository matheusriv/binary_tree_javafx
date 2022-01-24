package br.ufrn.imd.controle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public final class TelaABBController {
	
	@SuppressWarnings("unused")
	private Stage abbStage;
	
	@FXML
    private Button btnInserir;
	@FXML
    private Button btnProcurar;
	@FXML
    private Button btnApagar;
	@FXML
    private Button btnLimpar;
	@FXML
    private Button btnInorder;
	@FXML
    private Button btnPreorder;
	@FXML
    private Button btnPostorder;

	@FXML 
	private BorderPane rootContainer;
	@FXML 
	private TextArea textArea;
	@FXML 
	private TextField inputField;

	/* private GraphicsTree graphicsTree; */
	
	@FXML 
	void inserir(ActionEvent event) {
		
	}
	
	@FXML 
	void procurar(ActionEvent event) {
		
	}

	@FXML 
	void apagar(ActionEvent event) {
		
	}

	/*private void limparArvore() {
		
	}*/
	
	@FXML 
	void limpar(ActionEvent event) {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Você quer apagar a árvore?", ButtonType.OK);
		alert.showAndWait().filter(response -> response == ButtonType.OK);
	}

	@FXML 
	void inorder(ActionEvent event) {
		
	}

	@FXML 
	void preorder(ActionEvent event) {
		
	}

	@FXML 
	void postorder(ActionEvent event) {
		
	}
	
	public void setABBStage(Stage abbStage) {
		this.abbStage = abbStage;
		
	}
	
	
}