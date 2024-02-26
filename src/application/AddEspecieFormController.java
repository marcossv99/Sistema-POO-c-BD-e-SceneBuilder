package application;

import java.io.IOException;
import dominio.Especie;
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
import persistencia.EspecieDAO;

public class AddEspecieFormController {
	
	EspecieDAO especieDAO = new EspecieDAO();
	Especie especie;

	@FXML
    private AnchorPane apnAddEspecie;

    @FXML
    private Button btnAdicionarEspecieForm;

    @FXML
    private Button btnVoltarEspecieForm;

    @FXML
    private TextField inputComprimento;

    @FXML
    private TextField inputDias;

    @FXML
    private TextField inputNome;

    @FXML
    private TextField inputPeso;

    @FXML
    private TextField inputPhMax;

    @FXML
    private TextField inputPhMin;

    @FXML
    private TextField inputTempMax;

    @FXML
    private TextField inputTempMin;
    
    @FXML
    private Label labelAviso;

    @FXML
    void addEspecie(ActionEvent event) {
    	if ( inputNome.getText().equals("")) {
    		labelAviso.setText("Informe o Registro.");
    		labelAviso.setTextFill(Color.RED);
    		labelAviso.setVisible(true);
    		inputNome.requestFocus();
    	} else if ( inputComprimento.getText().equals("")) {
    		labelAviso.setText("Informe a Senha.");
    		labelAviso.setTextFill(Color.RED);
    		labelAviso.setVisible(true);
    		inputComprimento.requestFocus();
    	} else if ( inputPeso.getText().equals("")) {
    		labelAviso.setText("Informe o CPF.");
    		labelAviso.setTextFill(Color.RED);
    		labelAviso.setVisible(true);
    		inputPeso.requestFocus();
    	} else if ( inputDias.getText().equals("")) {
    		labelAviso.setText("Informe o DDD.");
    		labelAviso.setTextFill(Color.RED);
    		labelAviso.setVisible(true);
    		inputDias.requestFocus();
    	} else if ( inputPhMin.getText().equals("")) {
    		labelAviso.setText("Informe o Número.");
    		labelAviso.setTextFill(Color.RED);
    		labelAviso.setVisible(true);
    		inputPhMin.requestFocus();
    	} else if ( inputPhMax.getText().equals("")) {
    		labelAviso.setText("Informe o Endereço.");
    		labelAviso.setTextFill(Color.RED);
    		labelAviso.setVisible(true);
    		inputPhMax.requestFocus();
    	} else if ( inputTempMin.getText().equals("")) {
    		labelAviso.setText("Informe o Endereço.");
    		labelAviso.setTextFill(Color.RED);
    		labelAviso.setVisible(true);
    		inputTempMin.requestFocus();
    	} else if ( inputTempMax.getText().equals("")) {
    		labelAviso.setText("Informe o Endereço.");
    		labelAviso.setTextFill(Color.RED);
    		labelAviso.setVisible(true);
    		inputTempMax.requestFocus();
    	} else {
    		especie = new Especie ( inputNome.getText() ,  Integer.parseInt(inputComprimento.getText()), Float.parseFloat(inputPeso.getText()),
    				Float.parseFloat(inputPhMin.getText()),Float.parseFloat(inputPhMax.getText()), Integer.parseInt(inputTempMin.getText())
    				, Integer.parseInt(inputTempMax.getText()), Integer.parseInt(inputDias.getText()));
    		especieDAO.inclusao(especie);
    		exibirAlertaConfirma("Especie adicionada", "Especie cadastrada com sucesso!");
    		((Node) (event.getSource())).getScene().getWindow().hide();
    		carregarTelaRelatorios();
    	}
    }

    @FXML
    void voltar(ActionEvent event) {
    	carregarTelaRelatorios();
    	((Node) (event.getSource())).getScene().getWindow().hide();
    }
    
    private void carregarTelaRelatorios() {
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
    
    private void exibirAlertaConfirma(String titulo, String mensagem) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

}

