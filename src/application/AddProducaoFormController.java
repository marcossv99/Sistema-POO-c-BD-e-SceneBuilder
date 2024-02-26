package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dominio.Producao;
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
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import persistencia.EspecieDAO;
import persistencia.ProducaoDAO;
import persistencia.TanqueDAO;

public class AddProducaoFormController implements Initializable {

	EspecieDAO e = new EspecieDAO();
	TanqueDAO t = new TanqueDAO();
	ProducaoDAO pd = new ProducaoDAO();

	Producao p;

	@FXML
	private AnchorPane apnAddProducao;

	@FXML
	private Button btnAdicionarEspecieForm;

	@FXML
	private Button btnVoltarEspecieForm;

	@FXML
	private ComboBox<String> comboxEspecie;

	@FXML
	private ComboBox<String> comboxTanque;

	@FXML
	private TextField inputQuantidade;

	@FXML
	private TextField inputId;

	@FXML
	private TextField inputDataA;

	@FXML
	private TextField inputDataD;

	@FXML
	private TextField inputDataM;

	@FXML
	private Label labelAviso;

	@FXML
	void addProducao(ActionEvent event) {
		if (inputQuantidade.getText().equals("")) {
			labelAviso.setText("Informe a Quantidade.");
			labelAviso.setTextFill(Color.RED);
			labelAviso.setVisible(true);
			inputQuantidade.requestFocus();
		} else if (inputId.getText().equals("")) {
			labelAviso.setText("Informe o número da produção.");
			labelAviso.setTextFill(Color.RED);
			labelAviso.setVisible(true);
			inputId.requestFocus();
		} else if (comboxEspecie.getValue().equals("")) {
			labelAviso.setText("Informe uma espécie.");
			labelAviso.setTextFill(Color.RED);
			labelAviso.setVisible(true);
			comboxEspecie.requestFocus();
		} else if (comboxTanque.getValue().equals("")) {
			labelAviso.setText("Informe um tanque.");
			labelAviso.setTextFill(Color.RED);
			labelAviso.setVisible(true);
			comboxTanque.requestFocus();
		} else if (inputDataD.getText().equals("") || inputDataD.getLength() > 2
				|| Integer.parseInt(inputDataD.getText()) < 0 || Integer.parseInt(inputDataD.getText()) > 31) {
			labelAviso.setText("Dia invalido.");
			labelAviso.setTextFill(Color.RED);
			labelAviso.setVisible(true);
			inputDataD.requestFocus();
		} else if (inputDataM.getText().equals("") || inputDataM.getLength() > 2
				|| Integer.parseInt(inputDataM.getText()) < 0 || Integer.parseInt(inputDataM.getText()) > 12) {
			labelAviso.setText("Mês invalido.");
			labelAviso.setTextFill(Color.RED);
			labelAviso.setVisible(true);
			inputDataM.requestFocus();
		} else if (inputDataA.getText().equals("") || inputDataA.getLength() > 4
				|| Integer.parseInt(inputDataA.getText()) < 2000 || Integer.parseInt(inputDataA.getText()) > 2023) {
			labelAviso.setText("Ano invalido.");
			labelAviso.setTextFill(Color.RED);
			labelAviso.setVisible(true);
			inputDataA.requestFocus();
		} else if (pd.verificar(LoginController.getRegistro(), Integer.parseInt(inputId.getText()))) {
			labelAviso.setText("Número de produção já cadastrado.");
			labelAviso.setTextFill(Color.RED);
			labelAviso.setVisible(true);
			inputId.requestFocus();
		} else {
			String dataprod = inputDataD.getText() + " / " + inputDataM.getText() + " / " + inputDataA.getText();
			p = new Producao(Integer.parseInt(inputId.getText()), Integer.parseInt(inputQuantidade.getText()),
					comboxEspecie.getValue(), Integer.parseInt(comboxTanque.getValue()), dataprod,
					LoginController.getRegistro());
			pd.inclusao(p);
			exibirAlertaConfirma("Produção adicionada", "Produção adicionada com sucesso!");
			((Node) (event.getSource())).getScene().getWindow().hide();
			carregarTela("MenuRelatorios.fxml");
		}
	}

	@FXML
	void voltar(ActionEvent event) {
		((Node) (event.getSource())).getScene().getWindow().hide();
		carregarTela("MenuRelatorios.fxml");
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		inputId.requestFocus();
		comboxEspecie.setItems(e.nomes());
		comboxTanque.setItems(t.ids(LoginController.getRegistro()));
	}

	private void carregarTela(String c) {
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

	private void exibirAlertaConfirma(String titulo, String mensagem) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(titulo);
		alert.setHeaderText(null);
		alert.setContentText(mensagem);
		alert.showAndWait();
	}
}
