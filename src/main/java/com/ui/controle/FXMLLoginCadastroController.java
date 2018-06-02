package com.ui.controle;

import java.net.URL;
import java.util.ResourceBundle;

import com.servicos.usuario.ControleUsuario;
import com.ui.visao.MainPerfilUsuario;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class FXMLLoginCadastroController implements Initializable {

	private ControleUsuario controleUsuario;

	// Tela inicial bot�es login e cadastro, refere-se ao Login e Cadastre-se.
	@FXML
	private Button btnLogin, btnFazerCadastro, btnEntra;

	// Panes de ancoragem Panel Login e Panel Cadastre-se.
	@FXML
	private AnchorPane paneLogin, paneCadastro, apnGeral;

	@FXML
	private TextField txtLogin, txtPassoword;

	public FXMLLoginCadastroController() {
		btnEntra = new Button();

		txtLogin = new TextField();
		txtPassoword = new TextField();

		controleUsuario = new ControleUsuario();

	}

	/**
	 * Tela de login e cadastro usu�rio
	 * 
	 * M�todo realizar evento de troca de paines quanto clicarmos no bot�o Login
	 * aparece a tela para fazer login com os campos padr�es (login e senha + bot�o
	 * entrar). Clicando no bot�o Cadastre-se vai apresenta a tela para fazer o
	 * cadstro no sistema.
	 * 
	 * @param event
	 *            Captura enventos, neste caso de dois bot�es btnLogin,
	 *            btnFazerCadastro
	 */

	@FXML
	private void trocarTela(ActionEvent event) {
		if (event.getSource() == btnLogin) {
			paneLogin.toFront();
		} else {

			if (event.getSource() == btnFazerCadastro) {
				paneCadastro.toFront();
			}
		}
	}

	@FXML
	private void cadastraUsuario(ActionEvent event) {

	}

	@FXML
	private void perfilUsuario(ActionEvent event) {

		if (event.getSource() == btnEntra) {
			try {
				String login = this.txtLogin.getText();
				String senha = this.txtPassoword.getText();
				String result = controleUsuario.entraSistema(login, senha);
				if (result != null) {
					System.out.println(result);
					Stage telaLogin = new Stage();
					MainPerfilUsuario viewPerfil = new MainPerfilUsuario();
					viewPerfil.start(telaLogin);
				}
			} catch (Exception e) {
				Alert dialogoErro = new Alert(Alert.AlertType.ERROR);
				dialogoErro.setTitle("Diálogo de Error");
				dialogoErro.setHeaderText("Erro ao entra no sistema");
				dialogoErro.setContentText(e.getMessage());
				dialogoErro.showAndWait();

			}
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

}
