package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dominio.EspecieRacao;
import dominio.Racao;
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
import persistencia.EspecieRacaoDAO;
import persistencia.RacaoDAO;

public class AddRacaoFormController implements Initializable {
	
	EspecieDAO e = new EspecieDAO();
	RacaoDAO rd = new RacaoDAO();
	EspecieRacaoDAO erd = new EspecieRacaoDAO();
	
	Racao r;
	EspecieRacao er;

    @FXML
    private AnchorPane apnAddRacao;

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
    private TableView<String> tableEspecie;

    @FXML
    private TextField textMarca;

    @FXML
    private TextField textNome;

    @FXML
    private TextField textPreco;

    @FXML
    void adicionar(ActionEvent event) {
    	if ( textMarca.getText().equals("")) {
    		labelAviso.setText("Informe a marca.");
    		labelAviso.setTextFill(Color.RED);
    		labelAviso.setVisible(true);
    		textMarca.requestFocus();
    	} else if ( textNome.getText().equals("")) {
    		labelAviso.setText("Informe o nome.");
    		labelAviso.setTextFill(Color.RED);
    		labelAviso.setVisible(true);
    		textNome.requestFocus();
    	} else if ( textPreco.getText().equals("") || Float.parseFloat(textPreco.getText()) <= 0) {
    		labelAviso.setText("Preço invalido.");
    		labelAviso.setTextFill(Color.RED);
    		labelAviso.setVisible(true);
    		textPreco.requestFocus();
    	} else if ( rd.verificar( textNome.getText(), textMarca.getText())){
    		labelAviso.setText("Combinação de nome e marca já cadastrados.");
    		labelAviso.setTextFill(Color.RED);
    		labelAviso.setVisible(true);
    		textNome.requestFocus();
    	} else {
    		ObservableList<String> nomes = tableEspecie.getItems();
    		r = new Racao ( textNome.getText(), textMarca.getText(), Double.parseDouble(textPreco.getText()) );
	    	rd.inclusao(r);
    		for ( int i = 0; i < nomes.size(); i++ ) {
    			er = new EspecieRacao( nomes.get(i), textNome.getText(), textMarca.getText());
    		    erd.inclusao(er);
    		}
	    	exibirAlertaConfirma("Racao adicionada", "Racao adicionada com sucesso!");
    		((Node) (event.getSource())).getScene().getWindow().hide();
    		carregarTela("MenuRelatorios.fxml");
    	}
    }

    @FXML
    void comboxEspecies(ActionEvent event) {
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

