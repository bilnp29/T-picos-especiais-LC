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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class FXMLLoginCadastroController implements Initializable {

	private static String SESSAO_ID;
	
	private ControleUsuario controleUsuario;

	@FXML
	private Button btnLogin, btnFazerCadastro, btnEntra, btnCadastra;

	@FXML
	private AnchorPane paneLogin, paneCadastro, apnGeral;

	@FXML
	private TextField txtLogin, txtNome, txt_LoginUsuario, txtEmail, txtEndereco;

	@FXML
	private PasswordField txtSenha, txtPassoword;

	public FXMLLoginCadastroController() {
		btnEntra = new Button();
		btnCadastra = new Button();

		txtLogin = new TextField();
		txtNome = new TextField();
		txt_LoginUsuario = new TextField();
		txtEmail = new TextField();

		txtPassoword = new PasswordField();
		txtSenha = new PasswordField();

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

	/**
	 * Método é iniciado quando o usuario clica no botão <b>Cadastrar</b>. Serão
	 * capturados os valores dos campos TextField() estes valores serão repassados
	 * como parametro para o método <b>cadastraUsuario</b> Este método retorna uma
	 * String, a mesma será recebida pela variavel result. Caso o valor dela seja
	 * nulo o sistema irá informa um massagem de erro, caso contrario uma messagem
	 * de sucesso.
	 * 
	 * @param event
	 *            funciona como um ouvinte, quando clicadado ou precionado o mesmo
	 *            irá realizar uma tarefa(Cadastrar usuário)
	 */
	@FXML
	private void cadastraUsuario(ActionEvent event) {
		if (event.getSource() == btnCadastra) {
			try {
				String nome = this.txtNome.getText();
				String login = this.txt_LoginUsuario.getText();
				String senha = this.txtSenha.getText();
				String email = this.txtEmail.getText();
				String endereco = this.txtEndereco.getText();
				String result = controleUsuario.cadastraUsuario(login, senha, nome, endereco, email);
				if (result != null) {
					Alert dialogoConfimacao = new Alert(Alert.AlertType.CONFIRMATION);
					dialogoConfimacao.setTitle("Diálogo de Confirmação");
					dialogoConfimacao.setHeaderText("Cadastrando");
					dialogoConfimacao.setContentText("Usuário " + nome + " cadastrado com sucesso.");
					dialogoConfimacao.showAndWait();
					limparCampos();
				}

			} catch (Exception e) {
				Alert dialogoErro = new Alert(Alert.AlertType.ERROR);
				dialogoErro.setTitle("Diálogo de Error");
				dialogoErro.setHeaderText("Erro ao cadastra usuário");
				dialogoErro.setContentText(e.getMessage());
				dialogoErro.showAndWait();
			}
		}

	}

	/**
	 * Abrir um sessão ativa (realizar login)
	 * 
	 * @param event
	 *            funciona como um ouvinte, quando clicadado ou precionado o mesmo
	 *            irá realizar uma tarefa(Fazer login)
	 */
	@FXML
	private void perfilUsuario(ActionEvent event) {

		if (event.getSource() == btnEntra) {
			try {
				String login = this.txtLogin.getText();
				String senha = this.txtPassoword.getText();
				SESSAO_ID = controleUsuario.entraSistema(login, senha);
				if (SESSAO_ID != null) {
					Stage telaLogin = new Stage();
					MainPerfilUsuario viewPerfil = new MainPerfilUsuario();
					viewPerfil.start(telaLogin);
					limparCampos();
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

	/**
	 * Limpar os TextField().
	 */
	public void limparCampos() {
		this.txtNome.clear();
		this.txt_LoginUsuario.clear();
		this.txtEndereco.clear();
		this.txtEmail.clear();
		this.txtSenha.clear();
		this.txtPassoword.clear();
		this.txtLogin.clear();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

	public static String getSESSAO_ID() {
		return SESSAO_ID;
	}

}
