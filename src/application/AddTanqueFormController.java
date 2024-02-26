package application;

import java.io.IOException;

import dominio.Tanque;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import persistencia.TanqueDAO;

public class AddTanqueFormController {
	
	TanqueDAO td = new TanqueDAO();
	Tanque t;

    @FXML
    private AnchorPane apnAddTanque;

    @FXML
    private Button btnAdicionar;

    @FXML
    private Button btnVoltar;

    @FXML
    private TextField inputCapacidade;

    @FXML
    private TextField inputId;
    
    @FXML
    private Label labelAviso;

    @FXML
    void adicionar(ActionEvent event) {
    	if ( inputCapacidade.getText().equals("")) {
    		labelAviso.setText("Informe a Capacidade.");
    		labelAviso.setTextFill(Color.RED);
    		labelAviso.setVisible(true);
    		inputCapacidade.requestFocus();
    	} else if ( inputId.getText().equals("")) {
    		labelAviso.setText("Informe o Id.");
    		labelAviso.setTextFill(Color.RED);
    		labelAviso.setVisible(true);
    		inputId.requestFocus();
    	} else if ( td.buscarId(LoginController.getRegistro(), Integer.parseInt(inputId.getText()))) {
    		labelAviso.setText("Id já cadastrado.");
    		labelAviso.setTextFill(Color.RED);
    		labelAviso.setVisible(true);
    		inputId.requestFocus();
    	} else {
	    	t = new Tanque ( Integer.parseInt(inputId.getText()), Integer.parseInt(inputCapacidade.getText()), LoginController.getRegistro());
	    	td.inclusao(t);
	    	exibirAlertaConfirma("Tanque adicionado", "Tanque adicionado com sucesso!");
    		((Node) (event.getSource())).getScene().getWindow().hide();
    		carregarTela("MenuRelatorios.fxml");
    	}
    }

    @FXML
    void voltar(ActionEvent event) {
    	carregarTela("MenuRelatorios.fxml");
    	((Node) (event.getSource())).getScene().getWindow().hide();
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
