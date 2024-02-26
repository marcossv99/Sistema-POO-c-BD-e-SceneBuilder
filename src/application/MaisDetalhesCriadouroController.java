package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

//import application.MenuRelatoriosController.DoubleClickHandler;
import dominio.Criadouro;
import dominio.Producao;
import dominio.Tanque;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import persistencia.CriadouroDAO;
import persistencia.ProducaoDAO;
import persistencia.TanqueDAO;

public class MaisDetalhesCriadouroController implements Initializable {
	
	private CriadouroDAO cd = new CriadouroDAO();
	private ProducaoDAO p = new ProducaoDAO();
	private TanqueDAO t = new TanqueDAO();
	
	private Criadouro c;
	
	private ObservableList<Tanque> listaTanques = FXCollections.observableArrayList();
	private ObservableList<Producao> listaProducoes = FXCollections.observableArrayList();

    @FXML
    private AnchorPane apnDetalhesCriadouro;

    @FXML
    private Button btnAlterarSenha;

    @FXML
    private Button btnVoltar;

    @FXML
    private TableColumn<Tanque, String> columnCapacidadeTanque;

    @FXML
    private TableColumn<Producao, String> columnDataProducao;

    @FXML
    private TableColumn<Producao, String> columnEspecieProducao;
    
    @FXML
    private TableColumn<Producao, String> columnIdProducao;

    @FXML
    private TableColumn<Tanque, String> columnIdTanque;

    @FXML
    private TableColumn<Producao, String> columnIdTanqueProducao;

    @FXML
    private TableColumn<Producao, String> columnQntProducao;

    @FXML
    private TextField inputCelular;

    @FXML
    private TextField inputEditarCpf;

    @FXML
    private TextField inputEndereco;

    @FXML
    private TextField inputRegistro;

    @FXML
    private Label labelCPF;

    @FXML
    private Label labelCel;

    @FXML
    private Label labelEnd;

    @FXML
    private Label labelRegistro;
    
    @FXML
    private Label labelAviso;

    @FXML
    private TableView<Producao> tableProducao;

    @FXML
    private TableView<Tanque> tableTanque;

    @FXML
    void mudarSenha(ActionEvent event) {
    	carregarTela("MudarSenha.fxml");
    	((Node) (event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    void voltar(ActionEvent event) {
    	if ( inputCelular.getLength() > 11 || inputCelular.getLength() < 10) {
    		labelAviso.setText("Campo \"Celular\" vazio.");
    		labelAviso.setTextFill(Color.RED);
    		labelAviso.setVisible(true);
    		inputCelular.requestFocus();
    	} else if ( inputEditarCpf.getText().equals("")) {
    		labelAviso.setText("Campo \"CPF\" vazio.");
    		labelAviso.setTextFill(Color.RED);
    		labelAviso.setVisible(true);
    		inputEditarCpf.requestFocus();
    	} else if ( inputEndereco.getText().equals("")) {
    		labelAviso.setText("Campo \"Endereço\" vazio.");
    		labelAviso.setTextFill(Color.RED);
    		labelAviso.setVisible(true);
    		inputEndereco.requestFocus();
    	} else if ( inputRegistro.getText().equals("")) {
    		labelAviso.setText("Campo \"Registro\" vazio.");
    		labelAviso.setTextFill(Color.RED);
    		labelAviso.setVisible(true);
    		inputRegistro.requestFocus();
    	} else {
    		if ( cd.verificarRegistro(inputRegistro.getText()) >= 1 && !(inputCelular.getText().equals(cd.buscarCelular(inputRegistro.getText())))) {
    			labelAviso.setText("Registro já cadastrado. Tente outro.");
        		labelAviso.setTextFill(Color.RED);
        		labelAviso.setVisible(true);
        		inputRegistro.requestFocus();
    		} else {
	    		c = new Criadouro(inputEditarCpf.getText(), inputEndereco.getText(), LoginController.getSenha(), inputCelular.getText(), inputRegistro.getText());
	        	cd.alteracao(c, LoginController.getRegistro());
	        	LoginController.setCriadouro(c);
	    		((Node) (event.getSource())).getScene().getWindow().hide();
	    		carregarTela("MenuRelatorios.fxml");
    		}
    	}
    	
    }
    
    public void initialize(URL arg0, ResourceBundle arg1) {
		inputCelular.setDisable(true);
		
		columnEspecieProducao.setCellValueFactory(new PropertyValueFactory<Producao, String>("nomeEspecie"));
		columnDataProducao.setCellValueFactory(new PropertyValueFactory<Producao, String>("dataProducao"));
		columnIdProducao.setCellValueFactory(new PropertyValueFactory<Producao, String>("idProducao"));
		columnQntProducao.setCellValueFactory(new PropertyValueFactory<Producao, String>("quantidade"));
		columnIdTanqueProducao.setCellValueFactory(new PropertyValueFactory<Producao, String>("idTanque"));
		
		listaProducoes.addAll(p.relatorio(LoginController.getRegistro()));
		
		tableProducao.setItems(listaProducoes);
		
		
		columnCapacidadeTanque.setCellValueFactory(new PropertyValueFactory<Tanque, String>("capacidade"));
		columnIdTanque.setCellValueFactory(new PropertyValueFactory<Tanque, String>("idTanque"));
		
		listaTanques.addAll(t.relatorio(LoginController.getRegistro()));
		
		tableTanque.setItems(listaTanques);
		
		inputCelular.setText(LoginController.getCelular());
		inputEditarCpf.setText(LoginController.getCpf());
		inputEndereco.setText(LoginController.getEndereco());
		inputRegistro.setText(LoginController.getRegistro());
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
