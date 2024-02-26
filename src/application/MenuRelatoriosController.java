package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import persistencia.EspecieDAO;
import persistencia.ProducaoDAO;
import persistencia.RacaoDAO;
import persistencia.RemedioDAO;
import persistencia.TanqueDAO;
import javafx.scene.Node;

public class MenuRelatoriosController implements Initializable {
	
	private EspecieDAO ed = new EspecieDAO();
	private ProducaoDAO p = new ProducaoDAO();
	private RacaoDAO rac = new RacaoDAO();
	private RemedioDAO rem = new RemedioDAO();
	private TanqueDAO t = new TanqueDAO();
	
	private ObservableList<Especie> listaEspecies = FXCollections.observableArrayList();
	private ObservableList<Racao> listaRacoes = FXCollections.observableArrayList();
	private ObservableList<Tanque> listaTanques = FXCollections.observableArrayList();
	private ObservableList<Producao> listaProducoes = FXCollections.observableArrayList();
	private ObservableList<Remedio> listaRemedios = FXCollections.observableArrayList();

    @FXML
    private AnchorPane EspeciesTabOption;

    @FXML
    private AnchorPane apnMenuRelatorioAbas;

    @FXML
    private Button btnAdicionarEspecie;

    @FXML
    private Button btnAdicionarProducao;

    @FXML
    private Button btnAdicionarRacao;

    @FXML
    private Button btnAdicionarRemedio;

    @FXML
    private Button btnAdicionarTanque;

    @FXML
    private Button btnDeletarEspecie;

    @FXML
    private Button btnDeletarProducao;

    @FXML
    private Button btnDeletarRacao;

    @FXML
    private Button btnDeletarRemedio;

    @FXML
    private Button btnDeletarTanque;
    
    @FXML
    private Button btnPerfil;

    @FXML
    private Button btnUsuarioLogout;

    @FXML
    private TableColumn<Especie, String> colunaEspecieComprimento;

    @FXML
    private TableColumn<Especie, String> colunaEspecieDias;

    @FXML
    private TableColumn<Especie, String> colunaEspecieNome;

    @FXML
    private TableColumn<Especie, String> colunaEspeciePeso;

    @FXML
    private TableColumn<Especie, String> colunaEspeciePhMax;

    @FXML
    private TableColumn<Especie, String> colunaEspeciePhMin;

    @FXML
    private TableColumn<Especie, String> colunaEspecieTempMax;

    @FXML
    private TableColumn<Especie, String> colunaEspecieTempMin;

    @FXML
    private TableColumn<Producao, String> colunaProducaoData;

    @FXML
    private TableColumn<Producao, String> colunaProducaoEspecie;

    @FXML
    private TableColumn<Producao, String> colunaProducaoId;

    @FXML
    private TableColumn<Producao, String> colunaProducaoQuantidade;

    @FXML
    private TableColumn<Producao, String> colunaProducaoTanque;

    @FXML
    private TableColumn<Racao, String> colunaRacaoMarca;

    @FXML
    private TableColumn<Racao, String> colunaRacaoNome;

    @FXML
    private TableColumn<Racao, String> colunaRacaoPreco;

    @FXML
    private TableColumn<Remedio, String> colunaRemedioDosagem;

    @FXML
    private TableColumn<Remedio, String> colunaRemedioMarca;

    @FXML
    private TableColumn<Remedio, String> colunaRemedioNome;

    @FXML
    private TableColumn<Tanque, String> colunaTanqueCapacidade;

    @FXML
    private TableColumn<Tanque, String> colunaTanqueId;

    @FXML
    private TextField inputPesquisaRelatorioEspecies;

    @FXML
    private TextField inputPesquisaRelatorioProducao;

    @FXML
    private TextField inputPesquisaRelatorioRacao;

    @FXML
    private TextField inputPesquisaRelatorioRemedio;

    @FXML
    private Tab tabPaneEspecies;

    @FXML
    private Tab tabPaneProducao;

    @FXML
    private Tab tabPaneRacao;

    @FXML
    private Tab tabPaneRemedios;

    @FXML
    private Tab tabPaneTanques;

    @FXML
    private TableView<Especie> tableViewEspeciesRelatorio;

    @FXML
    private TableView<Producao> tableViewProducaoRelatorio;

    @FXML
    private TableView<Racao> tableViewRacaoRelatorio;

    @FXML
    private TableView<Remedio> tableViewRemedioRelatorio;

    @FXML
    private TableView<Tanque> tableViewTanquesRelatorio;
    
    @FXML
    void Perfil(ActionEvent event) {
    	carregarTela("MaisDetalhesCriadouro.fxml");
    	((Node) (event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    void UsuarioLogoutEvent(ActionEvent event) {
    	carregarTela("Login.fxml");
    	((Node) (event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    void adicionarEspecie(ActionEvent event) {
    	carregarTela("AddEspecieForm.fxml");
    	((Node) (event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    void adicionarProducao(ActionEvent event) {
    	carregarTela("AddProducaoForm.fxml");
    	((Node) (event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    void adicionarRacao(ActionEvent event) {
    	carregarTela("AddRacaoForm.fxml");
    	((Node) (event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    void adicionarRemedio(ActionEvent event) {
    	carregarTela("AddRemedioForm.fxml");
    	((Node) (event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    void adicionarTanque(ActionEvent event) {
    	carregarTela("AddTanqueForm.fxml");
    	((Node) (event.getSource())).getScene().getWindow().hide();
    }

    @FXML
     void abrirDetalhesEspecie(Especie especieSelecionada){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MaisDetalhesEspecie.fxml"));
            Parent root = loader.load();
            MaisDetalhesEspecieController controller = loader.getController();
            controller.setEspecie(especieSelecionada);
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
    @FXML
    void deletarEspecie(ActionEvent event) {
    	Especie itemSelecionado = tableViewEspeciesRelatorio.getSelectionModel().getSelectedItem();

        if ( itemSelecionado != null) {
            ObservableList<Especie> dado = tableViewEspeciesRelatorio.getItems();
            dado.remove(itemSelecionado);
            ed.exclusao(itemSelecionado.getNome());
        }
    }

    @FXML
    void deletarProducao(ActionEvent event) {
    	Producao itemSelecionado = tableViewProducaoRelatorio.getSelectionModel().getSelectedItem();

        if ( itemSelecionado != null) {
            ObservableList<Producao> dado = tableViewProducaoRelatorio.getItems();
            dado.remove(itemSelecionado);
            p.exclusao(itemSelecionado.getIdProducao(), LoginController.getRegistro());
        }
    }

    @FXML
    void deletarRacao(ActionEvent event) {
    	Racao itemSelecionado = tableViewRacaoRelatorio.getSelectionModel().getSelectedItem();

        if ( itemSelecionado != null) {
            ObservableList<Racao> dado = tableViewRacaoRelatorio.getItems();
            dado.remove(itemSelecionado);
            rac.exclusao(itemSelecionado.getNome(), itemSelecionado.getMarca());
        }
    }

    @FXML
    void deletarRemedio(ActionEvent event) {
    	Remedio itemSelecionado = tableViewRemedioRelatorio.getSelectionModel().getSelectedItem();

        if ( itemSelecionado != null) {
            ObservableList<Remedio> dado = tableViewRemedioRelatorio.getItems();
            dado.remove(itemSelecionado);
            rem.exclusao(itemSelecionado.getNome(), itemSelecionado.getMarca());
        }
    }

    @FXML
    void deletarTanque(ActionEvent event) {
    	Tanque itemSelecionado = tableViewTanquesRelatorio.getSelectionModel().getSelectedItem();

        if ( itemSelecionado != null) {
            ObservableList<Tanque> dado = tableViewTanquesRelatorio.getItems();
            dado.remove(itemSelecionado);
            t.exclusao(itemSelecionado.getIdTanque(), LoginController.getRegistro());
        }
    }

    @FXML
    void especieLike(KeyEvent event) {
    	listaEspecies.removeAll(listaEspecies);
    	listaEspecies.addAll(ed.buscar(inputPesquisaRelatorioEspecies.getText()));
		
		tableViewEspeciesRelatorio.setItems(listaEspecies);
    }

    @FXML
    void likeProducao(KeyEvent event) {
    	listaProducoes.removeAll(listaProducoes);
    	listaProducoes.addAll(p.buscar(LoginController.getRegistro(), inputPesquisaRelatorioProducao.getText()));
		
		tableViewProducaoRelatorio.setItems(listaProducoes);
    }

    @FXML
    void likeRacao(KeyEvent event) {
    	listaRacoes.removeAll(listaRacoes);
    	listaRacoes.addAll(rac.buscar(inputPesquisaRelatorioProducao.getText()));
		
		tableViewRacaoRelatorio.setItems(listaRacoes);
    }

    @FXML
    void likeRemedio(KeyEvent event) {
    	listaRemedios.removeAll(listaRemedios);
    	listaRemedios.addAll(rem.buscar(inputPesquisaRelatorioProducao.getText()));
		
		tableViewRemedioRelatorio.setItems(listaRemedios);
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
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		colunaEspecieNome.setCellValueFactory(new PropertyValueFactory<Especie, String>("nome"));
		colunaEspeciePhMin.setCellValueFactory(new PropertyValueFactory<Especie, String>("phMin"));
		colunaEspeciePhMax.setCellValueFactory(new PropertyValueFactory<Especie, String>("phMax"));
		colunaEspecieComprimento.setCellValueFactory(new PropertyValueFactory<Especie, String>("comprimentoIdeal"));
		colunaEspecieDias.setCellValueFactory(new PropertyValueFactory<Especie, String>("diasAdulto"));
		colunaEspecieTempMax.setCellValueFactory(new PropertyValueFactory<Especie, String>("tempMax"));
		colunaEspecieTempMin.setCellValueFactory(new PropertyValueFactory<Especie, String>("tempMin"));
		colunaEspeciePeso.setCellValueFactory(new PropertyValueFactory<Especie, String>("pesoIdeal"));
		
		listaEspecies.addAll(ed.relatorio());
		
		tableViewEspeciesRelatorio.setItems(listaEspecies);

        tableViewEspeciesRelatorio.setRowFactory((tableView -> {
            TableRow<Especie> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if(event.getClickCount() == 2 && !row.isEmpty()) {
                    Especie especieSelecionada = row.getItem();
                    ((Node) (event.getSource())).getScene().getWindow().hide();
                    abrirDetalhesEspecie(especieSelecionada);
                }
            });
            return row;
        }));
        
		
		colunaProducaoEspecie.setCellValueFactory(new PropertyValueFactory<Producao, String>("nomeEspecie"));
		colunaProducaoData.setCellValueFactory(new PropertyValueFactory<Producao, String>("dataProducao"));
		colunaProducaoId.setCellValueFactory(new PropertyValueFactory<Producao, String>("idProducao"));
		colunaProducaoQuantidade.setCellValueFactory(new PropertyValueFactory<Producao, String>("quantidade"));
		colunaProducaoTanque.setCellValueFactory(new PropertyValueFactory<Producao, String>("idTanque"));
		
		listaProducoes.addAll(p.relatorio(LoginController.getRegistro()));
		
		tableViewProducaoRelatorio.setItems(listaProducoes);
		
		colunaRacaoMarca.setCellValueFactory(new PropertyValueFactory<Racao, String>("marca"));
		colunaRacaoPreco.setCellValueFactory(new PropertyValueFactory<Racao, String>("precoKg"));
		colunaRacaoNome.setCellValueFactory(new PropertyValueFactory<Racao, String>("nome"));
		
		listaRacoes.addAll(rac.relatorio());
		
		tableViewRacaoRelatorio.setItems(listaRacoes);
		
		colunaRemedioDosagem.setCellValueFactory(new PropertyValueFactory<Remedio, String>("dosagem"));
		colunaRemedioMarca.setCellValueFactory(new PropertyValueFactory<Remedio, String>("marca"));
		colunaRemedioNome.setCellValueFactory(new PropertyValueFactory<Remedio, String>("nome"));
		
		listaRemedios.addAll(rem.relatorio());
		
		tableViewRemedioRelatorio.setItems(listaRemedios);
		
		colunaTanqueCapacidade.setCellValueFactory(new PropertyValueFactory<Tanque, String>("capacidade"));
		colunaTanqueId.setCellValueFactory(new PropertyValueFactory<Tanque, String>("idTanque"));
		
		listaTanques.addAll(t.relatorio(LoginController.getRegistro()));
		
		tableViewTanquesRelatorio.setItems(listaTanques);

        
	}
}
