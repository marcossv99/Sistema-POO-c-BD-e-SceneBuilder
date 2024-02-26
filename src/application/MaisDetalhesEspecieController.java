package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dominio.Criadouro;
import dominio.Especie;
import dominio.Producao;
import dominio.Racao;
import dominio.Remedio;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import persistencia.CriadouroDAO;
import persistencia.EspecieDAO;
import persistencia.ProducaoDAO;
import persistencia.RacaoDAO;
import persistencia.RemedioDAO;
import persistencia.TanqueDAO;

public class MaisDetalhesEspecieController implements Initializable{
    private EspecieDAO ed = new EspecieDAO();
    private ProducaoDAO pd = new ProducaoDAO();
    private RacaoDAO rd = new RacaoDAO();
    private RemedioDAO red = new RemedioDAO();
    private TanqueDAO td = new TanqueDAO();
    private CriadouroDAO c = new CriadouroDAO();
    private Especie especieSelecionada;

	private ObservableList<Racao> listaRacoes = FXCollections.observableArrayList();
	private ObservableList<Tanque> listaTanques = FXCollections.observableArrayList();
	private ObservableList<Producao> listaProducoes = FXCollections.observableArrayList();
	private ObservableList<Remedio> listaRemedios = FXCollections.observableArrayList();

    @FXML
    private TableColumn<Tanque, String> colunaCapacidade;

    @FXML
    private TableColumn<Tanque, String> colunaCriadouro;

    @FXML
    private TableColumn<Producao, String> colunaIDProducao;

    @FXML
    private TableColumn<Tanque, String> colunaIDTanque;

    @FXML
    private TableColumn<Racao, String> colunaMarcaRacao;

    @FXML
    private TableColumn<Racao, String> colunaNomeRacao;

    @FXML
    private TableColumn<Producao, String> colunaProducaoData;

    @FXML
    private TableColumn<Producao, String> colunaProducaoQuantidade;

    @FXML
    private TableColumn<Racao, String> colunaRacaoPrecokg;

    @FXML
    private TableColumn<Producao, String> colunaIDTanqueProducao;

    @FXML
    private TableColumn<Remedio, String> colunaRemedioDosagem;

    @FXML
    private TableColumn<Remedio, String> colunaRemedioMarca;

    @FXML
    private TableColumn<Remedio, String> colunaRemedioNome;

    @FXML
    private Tab tabProducao;

    @FXML
    private Tab tabRacao;

    @FXML
    private Tab tabRemedio;

    @FXML
    private Tab tabTanque;

    @FXML
    private Button btnEditarEspecie;

    @FXML
    private AnchorPane apnDetalhesEspecie;

    @FXML
    private Button btnVoltarMenuRelatorioEspecie;

    @FXML
    private TextField inputEditarEspecieComprimentoIdeal;

    @FXML
    private TextField inputEditarEspecieDiasAdulto;

    @FXML
    private TextField inputEditarEspecieNome;

    @FXML
    private TextField inputEditarEspeciePesoIdeal;

    @FXML
    private TextField inputEditarEspeciePhMax;

    @FXML
    private TextField inputEditarEspeciePhMin;

    @FXML
    private TextField inputEditarEspecieTempMax;

    @FXML
    private TextField inputEditarEspecieTempMin;

    @FXML
    private Label labelEditarEspecieComprimentoideal;

    @FXML
    private Label labelEditarEspecieDiasAdulto;

    @FXML
    private Label labelEditarEspecieDiasAdulto1;

    @FXML
    private Label labelEditarEspecieDiasAdulto11;

    @FXML
    private Label labelEditarEspeciePesoIdeal;

    @FXML
    private Label labelAviso;

    @FXML
    private Label labelEditarEspeciePhMax;

    @FXML
    private Label labelEditarEspeciePhMin;

    @FXML
    private Label labelEditarEspecieTempoMax;

    @FXML
    private Label labelEditarEspecieTempoMin;

    @FXML
    private Label labelEditarEspecieTempoMin1;

    @FXML
    private TableView<Producao> tableViewProducaoDetalhesEspecie;

    @FXML
    private TableView<Racao> tableViewRacaoDetalhesEspecie;

    @FXML
    private TableView<Remedio> tableViewRemedioDetalhesEspecie;

    @FXML
    private TableView<Tanque> tableViewTanquesDetalhesEspecie;

    @FXML
    void voltarMenuRelatorioEspecieEvent(ActionEvent event) {
        voltar(event);
    }
     void voltar(ActionEvent event) {
    	carregarTelaRelatorios();
    	((Node) (event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    void editarEspecieEvent(ActionEvent event) {
        atualizarEspecie();
    }
    public void setEspecie(Especie especie) {
        this.especieSelecionada = especie;
        preencherInputs(especie);
    }
    void preencherInputs(Especie e){
        if(e != null){
            inputEditarEspecieNome.setText(e.getNome());
            inputEditarEspecieComprimentoIdeal.setText(String.valueOf(e.getComprimentoIdeal()));
            inputEditarEspeciePesoIdeal.setText(String.valueOf(e.getPesoIdeal()));
            inputEditarEspeciePhMin.setText(String.valueOf(e.getPhMin()));
            inputEditarEspeciePhMax.setText(String.valueOf(e.getPhMax()));
            inputEditarEspecieTempMax.setText(String.valueOf(e.getTempMax()));
            inputEditarEspecieTempMin.setText(String.valueOf(e.getTempMin()));
            inputEditarEspecieDiasAdulto.setText(String.valueOf(e.getDiasAdulto()));
        }
    }
    
    @FXML
    void atualizarEspecie() {
        if ( inputEditarEspecieNome.getText().equals("")) {
    		labelAviso.setText("Informe o nome da especie.");
    		labelAviso.setTextFill(Color.RED);
    		labelAviso.setVisible(true);
    		inputEditarEspecieNome.requestFocus();
    	} else if ( inputEditarEspecieComprimentoIdeal.getText().equals("")) {
    		labelAviso.setText("Informe o comprimento.");
    		labelAviso.setTextFill(Color.RED);
    		labelAviso.setVisible(true);
    		inputEditarEspecieComprimentoIdeal.requestFocus();
    	} else if ( inputEditarEspecieDiasAdulto.getText().equals("")) {
    		labelAviso.setText("Informe o dias ate adulto.");
    		labelAviso.setTextFill(Color.RED);
    		labelAviso.setVisible(true);
    		inputEditarEspecieDiasAdulto.requestFocus();
    	} else if ( inputEditarEspeciePesoIdeal.getText().equals("")) {
    		labelAviso.setText("Informe o peso ideal.");
    		labelAviso.setTextFill(Color.RED);
    		labelAviso.setVisible(true);
    		inputEditarEspeciePesoIdeal.requestFocus();
    	} else if ( inputEditarEspeciePhMin.getText().equals("")) {
    		labelAviso.setText("Informe o ph minimo.");
    		labelAviso.setTextFill(Color.RED);
    		labelAviso.setVisible(true);
    		inputEditarEspeciePhMin.requestFocus();
    	} else if ( inputEditarEspeciePhMax.getText().equals("")) {
    		labelAviso.setText("Informe o ph maximo.");
    		labelAviso.setTextFill(Color.RED);
    		labelAviso.setVisible(true);
    		inputEditarEspeciePhMax.requestFocus();
    	} else if ( inputEditarEspecieTempMin.getText().equals("")) {
    		labelAviso.setText("Informe a temperatura minima.");
    		labelAviso.setTextFill(Color.RED);
    		labelAviso.setVisible(true);
    		inputEditarEspecieTempMin.requestFocus();
    	} else if ( inputEditarEspecieTempMax.getText().equals("")) {
    		labelAviso.setText("Informe a temperatura maxima.");
    		labelAviso.setTextFill(Color.RED);
    		labelAviso.setVisible(true);
    		inputEditarEspecieTempMax.requestFocus();
        } else {

        
        String nome = inputEditarEspecieNome.getText();
        int comprimentoIdeal = Integer.parseInt(inputEditarEspecieComprimentoIdeal.getText());
        float pesoIdeal = Float.parseFloat(inputEditarEspeciePesoIdeal.getText());
        float phMin = Float.parseFloat(inputEditarEspeciePhMin.getText());
        float phMax = Float.parseFloat(inputEditarEspeciePhMax.getText());
        int tempMin = Integer.parseInt(inputEditarEspecieTempMin.getText());
        int tempMax = Integer.parseInt(inputEditarEspecieTempMax.getText());
        int diasAdulto = Integer.parseInt(inputEditarEspecieDiasAdulto.getText());

        Especie atualizar = new Especie(nome, comprimentoIdeal, pesoIdeal, phMin, phMax, tempMin, tempMax, diasAdulto);
        
        ed.alteracao(atualizar, nome);
        exibirAlertaSucesso("Especie editada", "Edicao concluida!");
        }
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
    private void exibirAlertaSucesso(String titulo, String mensagem) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
    @Override
public void initialize(URL location, ResourceBundle resources) {
   inicializarTabelas();
}
    public void inicializarTabelas (){
        colunaProducaoData.setCellValueFactory(new PropertyValueFactory<Producao, String>("dataProducao"));
		colunaIDProducao.setCellValueFactory(new PropertyValueFactory<Producao, String>("idProducao"));
		colunaProducaoQuantidade.setCellValueFactory(new PropertyValueFactory<Producao, String>("quantidade"));
		colunaIDTanqueProducao.setCellValueFactory(new PropertyValueFactory<Producao, String>("idTanque"));
        listaProducoes.addAll(pd.relatorio(LoginController.getRegistro()));
		tableViewProducaoDetalhesEspecie.setItems(listaProducoes);

        colunaNomeRacao.setCellValueFactory(new PropertyValueFactory<Racao, String>("nome"));
		colunaMarcaRacao.setCellValueFactory(new PropertyValueFactory<Racao, String>("marca"));
		colunaRacaoPrecokg.setCellValueFactory(new PropertyValueFactory<Racao, String>("precoKg"));
		listaRacoes.addAll(rd.relatorio());
		tableViewRacaoDetalhesEspecie.setItems(listaRacoes);

		colunaRemedioNome.setCellValueFactory(new PropertyValueFactory<Remedio, String>("nome"));
		colunaRemedioMarca.setCellValueFactory(new PropertyValueFactory<Remedio, String>("marca"));
        colunaRemedioDosagem.setCellValueFactory(new PropertyValueFactory<Remedio, String>("dosagem"));
		
		listaRemedios.addAll(red.relatorio());
		
		tableViewRemedioDetalhesEspecie.setItems(listaRemedios);
		
		colunaIDTanque.setCellValueFactory(new PropertyValueFactory<Tanque, String>("idTanque"));
		colunaCapacidade.setCellValueFactory(new PropertyValueFactory<Tanque, String>("capacidade"));
        colunaCriadouro.setCellValueFactory(new PropertyValueFactory<Tanque, String>("numeroRegistro"));

		listaTanques.addAll(td.relatorio(LoginController.getRegistro()));
		
		tableViewTanquesDetalhesEspecie.setItems(listaTanques);



    }
    
}
