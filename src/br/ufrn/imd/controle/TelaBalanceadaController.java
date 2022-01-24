package br.ufrn.imd.controle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public final class TelaBalanceadaController {
	
	@SuppressWarnings("unused")
	private Stage balenceadaStage;
	
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

	/* 
	@FXML 
	private BorderPane root_container;
	@FXML 
	private TextArea traversal_textarea;
	@FXML 
	private TextField input_field;

	private GraphicsTree graphicsTree; 
	*/
	
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
	
	public void setABBStage(Stage balenceadaStage) {
		this.balenceadaStage = balenceadaStage;
		
	}
	
	
}