package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MenuPrincipalController {
	@FXML
    private AnchorPane apnMenuPrincipal;

    @FXML
    private Button btnEspeciesMenu;

    @FXML
    private Button btnPerfil;

    @FXML
    private Button btnProducaoMenu;

    @FXML
    private Button btnRacaoMenu;

    @FXML
    private Button btnRemedioMenu;

    @FXML
    private Button btnTanqueMenu;

    @FXML
    private Button btnUsuarioLogout;

    @FXML
    private Label labelEspeciesBtn;

    @FXML
    private Label labelProducaoBtn;

    @FXML
    private Label labelRacaoBtn;

    @FXML
    private Label labelRemedioBtn;

    @FXML
    private Label labelTanqueBtn;

    @FXML
    void EspeciesMenuEvent(ActionEvent event) {

    }

    @FXML
    void Perfil(ActionEvent event) {

    }

    @FXML
    void ProducaoMenuEvent(ActionEvent event) {

    }

    @FXML
    void RacaoMenuEvent(ActionEvent event) {

    }

    @FXML
    void RemedioMenuEvent(ActionEvent event) {

    }

    @FXML
    void TanqueMenuEvent(ActionEvent event) {

    }

    @FXML
    void UsuarioLogoutEvent(ActionEvent event) {
    	carregarTelaLogin();
    	((Node) (event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    void especieMenuEventImg(MouseEvent event) {
    	carregarTelaRelatorios();
    	((Node) (event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    void producaoMenuEventImg(MouseEvent event) {

    }

    @FXML
    void racaoMenuEventImg(MouseEvent event) {

    }

    @FXML
    void remedioMenuEventImg(MouseEvent event) {

    }

    @FXML
    void tanqueMenuEventImg(MouseEvent event) {

    }
    
    private void carregarTelaLogin() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();

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
}
