package application;

import java.io.IOException;

import dominio.Criadouro;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import persistencia.CriadouroDAO;

public class CadastrarController {
	
	CriadouroDAO cd = new CriadouroDAO();
	Criadouro c;

    @FXML
    private AnchorPane anchorCadastrar;

    @FXML
    private Button btnCadastrar;

    @FXML
    private Button btnVerificar;

    @FXML
    private Button btnVoltar;

    @FXML
    private TextField inputCPFProprietario;

    @FXML
    private TextField inputCelularDDD;

    @FXML
    private TextField inputCelularNumero;

    @FXML
    private PasswordField inputConfirmarSenha;

    @FXML
    private TextField inputEndereco;

    @FXML
    private TextField inputNumeroRegistro;

    @FXML
    private PasswordField inputSenha;

    @FXML
    private Label labelAviso;

    @FXML
    private Label labelCPFProprietarioCadastrar;

    @FXML
    private Label labelCelularCadastrar;

    @FXML
    private Label labelEnderecoCadastrar;

    @FXML
    private Label labelNumeroRegistroCadastrar;

    @FXML
    private Label labelSenhaCadastrar;

    @FXML
    private Label labelSenhaCadastrar1;

    @FXML
    void acaoVerificar(ActionEvent event) {
    	if ( cd.verificarRegistro(inputNumeroRegistro.getText()) == 0 ) {
    		labelAviso.setText("Registro não cadastrado!");
    		labelAviso.setTextFill(Color.GREEN);
    		labelAviso.setVisible(true);
    		btnCadastrar.setDisable(false);
    	} else {
    		labelAviso.setText("Registro já cadastrado!");
    		labelAviso.setTextFill(Color.RED);
    		labelAviso.setVisible(true);
    		btnCadastrar.setDisable(true);
    	}
    }

    @FXML
    void tratarCadastrarEvent(ActionEvent event) {
    	if ( inputNumeroRegistro.getText().equals("")) {
    		labelAviso.setText("Informe o Registro.");
    		labelAviso.setTextFill(Color.RED);
    		labelAviso.setVisible(true);
    		inputNumeroRegistro.requestFocus();
    	} else if ( inputSenha.getText().equals("")) {
    		labelAviso.setText("Informe a Senha.");
    		labelAviso.setTextFill(Color.RED);
    		labelAviso.setVisible(true);
    		inputSenha.requestFocus();
    	} else if ( !(inputConfirmarSenha.getText().equals(inputSenha.getText())) ) {
    		labelAviso.setText("Senhas diferentes.");
    		labelAviso.setTextFill(Color.RED);
    		labelAviso.setVisible(true);
    		inputConfirmarSenha.requestFocus();
    	} else if ( inputCPFProprietario.getLength() < 9) {
    		labelAviso.setText("CPF Invalido.");
    		labelAviso.setTextFill(Color.RED);
    		labelAviso.setVisible(true);
    		inputCPFProprietario.requestFocus();
    	} else if ( inputCelularDDD.getLength() > 2 || inputCelularDDD.getLength() < 2 ) {
    		labelAviso.setText("DDD invalido.");
    		labelAviso.setTextFill(Color.RED);
    		labelAviso.setVisible(true);
    		inputCelularDDD.requestFocus();
    	} else if ( inputCelularNumero.getLength() > 9 || inputCelularNumero.getLength() < 8) {
    		labelAviso.setText("Numero de celular inválido.");
    		labelAviso.setTextFill(Color.RED);
    		labelAviso.setVisible(true);
    		inputCelularNumero.requestFocus();
    	} else if ( inputEndereco.getText().equals("")) {
    		labelAviso.setText("Informe o Endereço.");
    		labelAviso.setTextFill(Color.RED);
    		labelAviso.setVisible(true);
    		inputEndereco.requestFocus();
    	} else {
    		String celular = inputCelularDDD.getText() + inputCelularNumero.getText();
    		c = new Criadouro ( inputCPFProprietario.getText() , inputEndereco.getText(),
    				inputSenha.getText(), celular,inputNumeroRegistro.getText());
    		cd.inclusao(c);
    		exibirAlertaConfirma("Cadastro efetuado.", "Sua conta foi cadastrada com sucesso!");
    		((Node) (event.getSource())).getScene().getWindow().hide();
    		carregarTela("Login.fxml");
    	}
    }
    
    @FXML
    void voltar(ActionEvent event) {
    	((Node) (event.getSource())).getScene().getWindow().hide();
    	carregarTela("Login.fxml");
    }
    
    private void exibirAlertaConfirma(String titulo, String mensagem) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
    
    private void carregarTela( String c) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(c));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
    
}


