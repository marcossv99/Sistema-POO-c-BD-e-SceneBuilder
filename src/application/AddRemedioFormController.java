package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import dominio.EspecieRemedio;
import dominio.Remedio;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import persistencia.EspecieDAO;
import persistencia.EspecieRemedioDAO;
import persistencia.RemedioDAO;

public class AddRemedioFormController implements Initializable {
	
	EspecieDAO e = new EspecieDAO();
	RemedioDAO rd = new RemedioDAO();
	EspecieRemedioDAO erd = new EspecieRemedioDAO();
	
	Remedio r;
	EspecieRemedio er;

    @FXML
    private AnchorPane apnAddRemedio;

    @FXML
    private ComboBox<String> boxEspecie;

    @FXML
    private Button btnAdicionar;

    @FXML
    private Button btnVoltar;
    
    @FXML
    private Label labelAviso;

    @FXML
    private TableColumn<String, String> columnEspecie;

    @FXML
    private TextField inputDosagem;

    @FXML
    private TextField inputMarca;

    @FXML
    private TextField inputNome;

    @FXML
    private TableView<String> tableEspecie;

    @FXML
    void adicionar(ActionEvent event) {
    	if ( inputMarca.getText().equals("")) {
    		labelAviso.setText("Informe a marca.");
    		labelAviso.setTextFill(Color.RED);
    		labelAviso.setVisible(true);
    		inputMarca.requestFocus();
    	} else if ( inputNome.getText().equals("")) {
    		labelAviso.setText("Informe o nome.");
    		labelAviso.setTextFill(Color.RED);
    		labelAviso.setVisible(true);
    		inputNome.requestFocus();
    	} else if ( inputDosagem.getText().equals("") || Float.parseFloat(inputDosagem.getText()) <= 0) {
    		labelAviso.setText("Dosagem invalida.");
    		labelAviso.setTextFill(Color.RED);
    		labelAviso.setVisible(true);
    		inputDosagem.requestFocus();
    	} else if ( rd.verificar( inputNome.getText(), inputMarca.getText())){
    		labelAviso.setText("Combinação de nome e marca já cadastrados.");
    		labelAviso.setTextFill(Color.RED);
    		labelAviso.setVisible(true);
    		inputNome.requestFocus();
    	} else {
    		ObservableList<String> nomes = tableEspecie.getItems();
    		r = new Remedio ( inputNome.getText(), inputMarca.getText(), Double.parseDouble(inputDosagem.getText()) );
	    	rd.inclusao(r);
    		for ( int i = 0; i < nomes.size(); i++ ) {
    			er = new EspecieRemedio( nomes.get(i), inputNome.getText(), inputMarca.getText());
    		    erd.inclusao(er);
    		}
	    	exibirAlertaConfirma("Remedio adicionado", "Remedio adicionado com sucesso!");
    		((Node) (event.getSource())).getScene().getWindow().hide();
    		carregarTela("MenuRelatorios.fxml");
    	}
    }

    @FXML
    void comboxEspecie(ActionEvent event) {
    	String selectedOption = boxEspecie.getSelectionModel().getSelectedItem();
        if (selectedOption != null && !verificarTabela(selectedOption) ) {
            tableEspecie.getItems().add(selectedOption);
        }
    }

    @FXML
    void voltar(ActionEvent event) {
    	((Node) (event.getSource())).getScene().getWindow().hide();
		carregarTela("MenuRelatorios.fxml");
    }
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		carregarEspecies();
		
		columnEspecie.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()));
	}
    
    private void carregarEspecies() {
		boxEspecie.setItems(e.nomes());
	}
	
	private boolean verificarTabela( String s) {
		boolean existe = false;
		for (String especie : tableEspecie.getItems()) {
		    if (especie.equals(s) && !(existe)) {
		        existe = true;
		    }
		}
		return existe;
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
