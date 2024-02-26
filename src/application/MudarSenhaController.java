package application;

import java.io.IOException;

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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import persistencia.CriadouroDAO;

public class MudarSenhaController {
	
	CriadouroDAO cd = new CriadouroDAO();

    @FXML
    private AnchorPane apnMudarSenha;

    @FXML
    private Button btnAlterar;

    @FXML
    private Button btnVoltar;

    @FXML
    private PasswordField inputConfirmaSenha;

    @FXML
    private PasswordField inputNovaSenha;

    @FXML
    private PasswordField inputSenhaAtual;
    
    @FXML
    private Label labelAviso;

    @FXML
    void mudarSenha(ActionEvent event) {
    	if ( !inputSenhaAtual.getText().equals(LoginController.getSenha())) {
    		labelAviso.setText("Senha atual incorreta.");
    		labelAviso.setTextFill(Color.RED);
    		labelAviso.setVisible(true);
    		inputSenhaAtual.requestFocus();
    	} else if ( inputNovaSenha.getText().equals("")) {
    		labelAviso.setText("\"Nova senha\" não pode ser vazio.");
    		labelAviso.setTextFill(Color.RED);
    		labelAviso.setVisible(true);
    		inputNovaSenha.requestFocus();
    	} else if ( !inputNovaSenha.getText().equals(inputConfirmaSenha.getText())) {
    		labelAviso.setText("Senhas não coincidem.");
    		labelAviso.setTextFill(Color.RED);
    		labelAviso.setVisible(true);
    		inputConfirmaSenha.requestFocus();
    	} else {
    		cd.alterarSenha(LoginController.getRegistro(), inputNovaSenha.getText());
    		exibirAlertaConfirma("Senha altara", "Sua senha foi alterada com sucesso!");
    		((Node) (event.getSource())).getScene().getWindow().hide();
    		carregarTela("MaisDetalhesCriadouro.fxml");
    	}
    }

    @FXML
    void voltar(ActionEvent event) {
    	((Node) (event.getSource())).getScene().getWindow().hide();
		carregarTela("MaisDetalhesCriadouro.fxml");
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
