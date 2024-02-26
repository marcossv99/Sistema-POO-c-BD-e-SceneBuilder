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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import persistencia.CriadouroDAO;


public class LoginController {
	
	private CriadouroDAO cd = new CriadouroDAO();
	private static Criadouro c;

    @FXML
    private AnchorPane apnLoginNRegistro;

    @FXML
    private Button btnProximo;
    
    @FXML
    private Button btnVoltar;

    @FXML
    private TextField inputLogin;

    @FXML
    private PasswordField inputSenha;

    @FXML
    private Label labelNumeroRegistro;

    @FXML
    void proximoSenha(ActionEvent event) {
    	if ( btnProximo.getText().equals("Próximo")) {
	    	if(cd.verificarRegistro((String) inputLogin.getText()) == 1) {
	    		c = ( cd.buscar(inputLogin.getText()));
	    		carregarTelaSenha();
	    		inputSenha.setText("");
	    		inputSenha.requestFocus();
	    	} else {
	    		((Node) (event.getSource())).getScene().getWindow().hide();
	    		carregarTelaRegistro();
	    	}
    	} else {
    		if ( inputSenha.getText().equals(c.getSenha())) {
    			((Node) (event.getSource())).getScene().getWindow().hide();
    			carregarTelaInicio();
    		} else {
    			exibirAlertaSenha("Senha incorreta", "A senha não coincide com o registro informado.");
    			inputSenha.setText("");
    			inputSenha.requestFocus();
    		}
    	}
    }
    
    @FXML
    void voltarLogin(ActionEvent event) {
    	carregarTelaLogin();
    	inputLogin.setText("");
		inputLogin.requestFocus();
		inputSenha.setDisable(true);
    }
    
    private void carregarTelaRegistro() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Cadastrar.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
    
    private void carregarTelaInicio() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuRelatorios.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
    
    private void carregarTelaSenha() {
        inputLogin.setVisible(false);
        labelNumeroRegistro.setText("Senha");
        btnProximo.setText("Entrar");
        inputSenha.setVisible(true);
        inputSenha.setDisable(false);
        btnVoltar.setVisible(true);
        btnVoltar.setDisable(false);
    }
    
    private void carregarTelaLogin() {
        inputLogin.setVisible(true);
        labelNumeroRegistro.setText("Nº Registro");
        btnProximo.setText("Próximo");
        inputSenha.setVisible(false);
        btnVoltar.setVisible(false);
        btnVoltar.setDisable(true);
    }
    
    public static String getRegistro() {
    	return c.getNumeroRegistro();
    }
    
    public static String getSenha() {
    	return c.getSenha();
    }
    
    public static String getCelular() {
    	return c.getCel();
    }
    
    public static String getEndereco() {
    	return c.getEndereco();
    }
    
    public static String getCpf() {
    	return c.getCpfProprietario();
    }
    
    public static void setCriadouro( Criadouro novo ) {
    	c = novo;
    }
    
    private void exibirAlertaSenha(String titulo, String mensagem) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

}
