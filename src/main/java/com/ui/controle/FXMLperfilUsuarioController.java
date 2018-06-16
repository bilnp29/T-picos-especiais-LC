package com.ui.controle;

import java.net.URL;
import java.util.ResourceBundle;

import com.dominio.Carona;
import com.dominio.Usuario;
import com.servicos.carona.ControleCarona;
import com.servicos.carona.ServicoCarona;
import com.servicos.perfil.ControlePerfil;
import com.servicos.usuario.ControleUsuario;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * @author Bruno
 *
 */
/**
 * @author Bruno
 *
 */
public class FXMLperfilUsuarioController implements Initializable {
	/**
	 * But�es responsalve por realizar opera��es em um perfil de um usu�rio, Carona,
	 * e finalizar uma sess�o.
	 */
	@FXML
	private MenuItem itemEditar, itemConsultarPerfil, itemConsultarUsuario, mitEncerra, mitCadastraCaronaMunicipal,
			mitCadastraCaronaIntermunicipal, mitConsultCarona, mitConsultTrajeto, mitDescCarona, mitRejeitCarona,
			mit_SolicitCaronaComPontoEncontro, mit_AceitSolicitacaoComPontoEncontro, mit_SolicitCaronaSemPontoEncontro,
			mit_AceitSolictSemPontoEncontro, mit_SugerPontEncontro, mitDesistCarona, mit_RespondeSugestaoPontoEncontro;
	/**
	 * 
	 */
	@FXML
	private Pane pnEditar, pnConsultaPerfil, pnCons_Usuario, pnPerfilCarona, pnCarona, pnCadastraCaronaMunicipal,
			pnCadastraCaronaIntermunicipal, pnConsultCarona, pnConsutTrajeto, pnConsultDescricaoCarona, pnRejeitaCarona,
			pnSolicitarCarona, pnAceitarCarona_ComPonto, pn_SolicitacaoSemPonto, pnAceitarCarona_SemPontoEncontro,
			pn_sugerirPontoEncontro, pn_DesistirSolicitacao, pn_ResponderSolicitacao_ComPonto;
	/**
	 * But�es responsalves por fazer opera��es em um perfi de usu�rio.
	 */
	@FXML
	private Button btnSalva, btnSalvarEdicao, btn_Pesquisar, btnConsulta, btnCadastraCarona,
			btnCadastraCaronaIntermuniciapl, bntConsutCarona;

	@FXML
	private AnchorPane apnGeral, pnPerfil;

	@FXML
	private MenuButton mbnPerfil, mbnSair, mbnCarona, mbnSolicitacao;

	@FXML
	private TextField txtNome, txtEndereco, txtEmail, txtLogin, txtRespostaConsulta, txtRespostaPerfil, txtOrigem,
			txtDestino, txtCidade, txtHora, txtVaga, txtOrigemInter, txtDestinoInter, txtHoraiInter, txtVagaInter,
			txtDataMuni,txtDataInt;

	@FXML
	private ChoiceBox<Carona> chBoxIdCarona;

	@FXML
	private PasswordField txtSenha;
	@FXML
	private ImageView imgFecha;

	@FXML
	private ToggleGroup consultUsuario, consultPerfil;

	private FXMLLoginCadastroController loginControle;
	private ControleUsuario controleUsuario;
	private ControlePerfil controlePerfil;
	private ControleCarona controleCarona;
	private ServicoCarona servsCarona;
	
	private Usuario usuario;

	private String sessao;

	@SuppressWarnings("static-access")
	public FXMLperfilUsuarioController() {
		/*
		 * //this.itemEditar = new MenuItem(); this.itemConsultarPerfil = new
		 * MenuItem(); this.itemConsultarUsuario = new MenuItem(); this.mitEncerra = new
		 * MenuItem(); this.mitCadastraCaronaMunicipal = new MenuItem();
		 * this.mitConsultCarona = new MenuItem(); this.mitConsultTrajeto = new
		 * MenuItem(); this.mitDescCarona = new MenuItem(); this.mitRejeitCarona = new
		 * MenuItem(); this.mit_SolicitCaronaComPontoEncontro = new MenuItem();
		 * this.mit_AceitSolicitacaoComPontoEncontro = new MenuItem();
		 * this.mit_SolicitCaronaSemPontoEncontro = new MenuItem();
		 * this.mit_AceitSolictSemPontoEncontro = new MenuItem();
		 * this.mit_SugerPontEncontro = new MenuItem(); this.mitDesistCarona = new
		 * MenuItem(); this.mit_RespondeSugestaoPontoEncontro = new MenuItem();
		 * 
		 * //this.pnEditar = new Pane(); this.pnConsultaPerfil = new Pane();
		 * this.pnCons_Usuario = new Pane(); this.pnPerfilCarona = new Pane();
		 * this.pnCadastraCaronaMunicipal = new Pane();
		 * this.pnCadastraCaronaIntermunicipal = new Pane(); this.pnConsultCarona = new
		 * Pane(); this.pnConsutTrajeto = new Pane(); this.pnConsultDescricaoCarona =
		 * new Pane(); this.pnRejeitaCarona = new Pane(); this.pnCarona = new Pane();
		 * this.pnSolicitarCarona = new Pane(); this.pnAceitarCarona_ComPonto = new
		 * Pane(); this.pn_SolicitacaoSemPonto = new Pane();
		 * this.pnAceitarCarona_SemPontoEncontro = new Pane();
		 * this.pn_sugerirPontoEncontro = new Pane(); this.pn_DesistirSolicitacao = new
		 * Pane(); this.pn_ResponderSolicitacao_ComPonto = new Pane();
		 * 
		 * this.mbnPerfil = new MenuButton(); this.mbnSair = new MenuButton();
		 * this.mbnCarona = new MenuButton(); this.mbnSolicitacao = new MenuButton();
		 * 
		 * this.btnSalva = new Button();
		 * 
		 * this.imgFecha = new ImageView();
		 * 
		 * this.apnGeral = new AnchorPane(); this.pnPerfil = new AnchorPane();
		 */
		sessao = loginControle.getSESSAO_ID();
		controleUsuario = new ControleUsuario();
		controlePerfil = new ControlePerfil();
		controleCarona = new ControleCarona();
		servsCarona = new ServicoCarona();
		usuario = new Usuario();
		loginControle = new FXMLLoginCadastroController();
		btnCadastraCarona = new Button();
		chBoxIdCarona = new ChoiceBox<>();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

	/**
	 * Método quando chamado irá setar a visibilidade dos paineis para falso.
	 */
	public void setarVisibilidade() {

		if (pnEditar.isVisible() || pnPerfilCarona.isVisible() || pnCarona.isVisible() || pnConsultaPerfil.isVisible()
				|| pnCons_Usuario.isVisible() || pnCadastraCaronaMunicipal.isVisible()
				|| pnCadastraCaronaIntermunicipal.isVisible() || pnConsultCarona.isVisible()
				|| pnConsutTrajeto.isVisible() || pnConsultDescricaoCarona.isVisible() || pnRejeitaCarona.isVisible()
				|| pnSolicitarCarona.isVisible() || pnAceitarCarona_ComPonto.isVisible()
				|| pnAceitarCarona_SemPontoEncontro.isVisible() || pn_SolicitacaoSemPonto.isVisible()
				|| pn_sugerirPontoEncontro.isVisible() || pn_DesistirSolicitacao.isVisible()
				|| pn_ResponderSolicitacao_ComPonto.isVisible()) {
			this.pnPerfilCarona.setVisible(false);
			this.pnCarona.setVisible(false);
			this.pnEditar.setVisible(false);
			this.pnConsultaPerfil.setVisible(false);
			this.pnCons_Usuario.setVisible(false);
			this.pnCadastraCaronaMunicipal.setVisible(false);
			this.pnCadastraCaronaIntermunicipal.setVisible(false);
			this.pnConsultCarona.setVisible(false);
			this.pnConsutTrajeto.setVisible(false);
			this.pnConsultDescricaoCarona.setVisible(false);
			this.pnRejeitaCarona.setVisible(false);
			this.pnSolicitarCarona.setVisible(false);
			this.pnAceitarCarona_ComPonto.setVisible(false);
			this.pn_SolicitacaoSemPonto.setVisible(false);
			this.pnAceitarCarona_SemPontoEncontro.setVisible(false);
			this.pn_sugerirPontoEncontro.setVisible(false);
			this.pn_DesistirSolicitacao.setVisible(false);
			this.pn_ResponderSolicitacao_ComPonto.setVisible(false);
		}
	}

	@SuppressWarnings("static-access")
	@FXML
	protected void funcaoEditarPerfil(ActionEvent event) {
		if (event.getSource() == itemEditar) {
			String sessao = loginControle.getSESSAO_ID();
			setarVisibilidade();
			pnPerfilCarona.setVisible(true);
			pnEditar.setVisible(true);
			usuario = controleUsuario.buscarUsuario(sessao);

			txtNome.setText(usuario.getNome());
			txtEmail.setText(usuario.getEmail());
			txtLogin.setText(usuario.getLogin());
			txtEndereco.setText(usuario.getEndereco());
			txtSenha.setText(usuario.getSenha());

		}
	}

	@FXML
	protected void funcaoConsultarPerfil(ActionEvent event) {
		if (event.getSource() == itemConsultarPerfil) {
			setarVisibilidade();
			pnPerfilCarona.setVisible(true);
			pnConsultaPerfil.setVisible(true);
		}
	}

	@FXML
	protected void funcaoConsultarUsuario(ActionEvent event) {

		if (event.getSource() == itemConsultarUsuario) {
			setarVisibilidade();
			pnPerfilCarona.setVisible(true);
			pnCons_Usuario.setVisible(true);
		}
	}

	@FXML
	protected void funcaoCadastraCaronaMunicipal(ActionEvent event) {
		if (event.getSource() == mitCadastraCaronaMunicipal) {
			setarVisibilidade();
			pnCarona.setVisible(true);
			pnCadastraCaronaMunicipal.setVisible(true);
		}
	}

	@FXML
	protected void funcaoCadastraCaronaIntermuniciapl(ActionEvent event) {
		if (event.getSource() == mitCadastraCaronaIntermunicipal) {
			setarVisibilidade();
			pnCarona.setVisible(true);
			pnCadastraCaronaIntermunicipal.setVisible(true);
		}
	}

	@FXML
	protected void funcaoConsultCarona(ActionEvent event) {
		if (event.getSource() == mitConsultCarona) {
			setarVisibilidade();
			pnCarona.setVisible(true);
			pnConsultCarona.setVisible(true);
		}
	}

	@FXML
	protected void funcaoConsultarTrajeto(ActionEvent event) {
		if (event.getSource() == mitConsultTrajeto) {
			setarVisibilidade();
			pnCarona.setVisible(true);
			pnConsutTrajeto.setVisible(true);
		}
	}

	@FXML
	protected void funcaoConsultarDescricaoCarona(ActionEvent event) {
		if (event.getSource() == mitDescCarona) {
			setarVisibilidade();
			pnCarona.setVisible(true);
			pnConsultDescricaoCarona.setVisible(true);
		}
	}

	@FXML
	protected void funcaoRejeitarCarona(ActionEvent event) {
		if (event.getSource() == mitRejeitCarona) {
			setarVisibilidade();
			pnCarona.setVisible(true);
			pnRejeitaCarona.setVisible(true);
		}
	}

	@FXML
	protected void funcaoSolicitarCaronaComPontoEncontro(ActionEvent event) {
		if (event.getSource() == mit_SolicitCaronaComPontoEncontro) {
			setarVisibilidade();
			pnCarona.setVisible(true);
			pnSolicitarCarona.setVisible(true);
		}
	}

	@FXML
	protected void funcaoAceitarCaronaComPontoEncontro(ActionEvent event) {
		if (event.getSource() == mit_AceitSolicitacaoComPontoEncontro) {
			setarVisibilidade();
			pnCarona.setVisible(true);
			pnAceitarCarona_ComPonto.setVisible(true);
		}
	}

	@FXML
	protected void funcaoSolicitarCaronaSemPontoEncontro(ActionEvent event) {
		if (event.getSource() == mit_SolicitCaronaSemPontoEncontro) {
			setarVisibilidade();
			pnCarona.setVisible(true);
			pn_SolicitacaoSemPonto.setVisible(true);
		}
	}

	@FXML
	protected void funcaoAceitarCaronaSemPontoEncontro(ActionEvent event) {
		if (event.getSource() == mit_AceitSolictSemPontoEncontro) {
			setarVisibilidade();
			pnCarona.setVisible(true);
			pnAceitarCarona_SemPontoEncontro.setVisible(true);

		}
	}

	@FXML
	protected void funcaoSugerirPontoEncontro(ActionEvent event) {
		if (event.getSource() == mit_SugerPontEncontro) {
			setarVisibilidade();
			pnCarona.setVisible(true);
			pn_sugerirPontoEncontro.setVisible(true);
		}
	}

	@FXML
	protected void funcaoDesistirPontoEncontro(ActionEvent event) {
		if (event.getSource() == mitDesistCarona) {
			setarVisibilidade();
			pnCarona.setVisible(true);
			pn_DesistirSolicitacao.setVisible(true);
		}
	}

	@FXML
	protected void funcaoResponderSolicitacaoPontoEncontro(ActionEvent event) {
		if (event.getSource() == mit_RespondeSugestaoPontoEncontro) {
			setarVisibilidade();
			pnCarona.setVisible(true);
			pn_ResponderSolicitacao_ComPonto.setVisible(true);
		}
	}

	@FXML
	protected void funcaoFecharSessao(MouseEvent event) {
		if (event.getSource() == imgFecha) {
			System.exit(0);
		}
	}

	@FXML
	protected void funcaoEncerrarSessao(ActionEvent event) {
		if (event.getSource() == mitEncerra) {
			System.exit(0);
		}
	}

	@SuppressWarnings("static-access")
	@FXML
	protected void funcaoSalvaEdicao(ActionEvent event) {
		if (event.getSource() == btnSalvarEdicao) {
			usuario = controleUsuario.buscarUsuario(sessao);
			int id = usuario.getIdUsuario();
			String nome = txtNome.getText();
			String login = txtLogin.getText();
			String email = txtEmail.getText();
			String endereco = txtEndereco.getText();
			String senha = txtSenha.getText();

			if (nome.equals("") || login.equals("") || email.equals("") || endereco.equals("") || senha.equals("")) {
				Alert dialogoErro = new Alert(Alert.AlertType.ERROR);
				dialogoErro.setTitle("Diálogo de Error");
				dialogoErro.setHeaderText("Erro, campo vazio!");
				dialogoErro.showAndWait();
			} else {
				controleUsuario.editarUsuario(id, nome, login, email, endereco, senha);
			}
		}
	}

	@SuppressWarnings("static-access")
	@FXML
	protected void funcaoConsultaUsuario(ActionEvent event) {
		if (event.getSource() == btn_Pesquisar) {
			RadioButton radio = (RadioButton) consultUsuario.getSelectedToggle();
			usuario = controleUsuario.buscarUsuario(sessao);
			String result;
			try {
				result = controleUsuario.localizarUsuario(usuario.getLogin(), radio.getText().toLowerCase());
				txtRespostaConsulta.setText(result);
			} catch (Exception e) {
				Alert dialogoErro = new Alert(Alert.AlertType.ERROR);
				dialogoErro.setTitle("Diálogo de Error");
				dialogoErro.setHeaderText("Erro na buscar: ");
				dialogoErro.setContentText(e.getMessage());
				dialogoErro.showAndWait();
			}

		}
	}

	@SuppressWarnings("static-access")
	@FXML
	protected void funcaoPesquisaPerfil(ActionEvent event) {
		if (event.getSource() == btnConsulta) {
			RadioButton radio = (RadioButton) consultPerfil.getSelectedToggle();
			String sessao = loginControle.getSESSAO_ID();
			usuario = controleUsuario.buscarUsuario(sessao);
			String result;
			try {
				result = controlePerfil.getAtributoPerfil(usuario.getLogin(), radio.getText().toLowerCase());
				txtRespostaPerfil.setText(result);
			} catch (Exception e) {
				Alert dialogoErro = new Alert(Alert.AlertType.ERROR);
				dialogoErro.setTitle("Diálogo de Error");
				dialogoErro.setHeaderText("Erro na pesquisa: ");
				dialogoErro.setContentText(e.getMessage());
				dialogoErro.showAndWait();
			}
		}
	}
	

	@FXML
	protected void CadastraCaronaMunicipal(ActionEvent event) {
		if (event.getSource() == btnCadastraCarona) {
			String origem = txtOrigem.getText();
			String destino = txtDestino.getText();
			String cidade = txtCidade.getText();
			String hora = txtHora.getText();
			String vaga = txtVaga.getText();
			String data = txtDataMuni.getText();

			try {
				String result = controleCarona.cadastraCaronaMunicipal(sessao, origem,
						destino, cidade, data, hora, vaga);
				if (result != null) {
					Alert dialogoConfimacao = new Alert(Alert.AlertType.CONFIRMATION);
					dialogoConfimacao.setTitle("Diálogo de Confirmação");
					dialogoConfimacao.setHeaderText("Cadastrando");
					dialogoConfimacao.setContentText("Carona cadastrada com sucesso.");
					dialogoConfimacao.showAndWait();
				}
			} catch (Exception e) {
				Alert dialogoErro = new Alert(Alert.AlertType.ERROR);
				dialogoErro.setTitle("Diálogo de Error");
				dialogoErro.setHeaderText("Erro ao cadastra carona");
				dialogoErro.setContentText(e.getMessage());
				dialogoErro.showAndWait();
			}
		}
	}

	@FXML
	protected void cadastraCaronaIntermunicipal(ActionEvent event) {
		if (event.getSource() == btnCadastraCaronaIntermuniciapl) {
			try {
				String result = controleCarona.cadastroCarona(sessao, txtOrigemInter.getText(),
						txtDestinoInter.getText(), txtDataInt.getText(), txtHoraiInter.getText(), txtVagaInter.getText());
				if (!result.equals("0")) {
					Alert dialogoConfimacao = new Alert(Alert.AlertType.CONFIRMATION);
					dialogoConfimacao.setTitle("Diálogo de Confirmação");
					dialogoConfimacao.setHeaderText("Cadastrando");
					dialogoConfimacao.setContentText("Carona cadastrada com sucesso.");
					dialogoConfimacao.showAndWait();
				}
			} catch (Exception e) {
				Alert dialogoErro = new Alert(Alert.AlertType.ERROR);
				dialogoErro.setTitle("Diálogo de Error");
				dialogoErro.setHeaderText("Erro ao cadastra carona");
				dialogoErro.setContentText(e.getMessage());
				dialogoErro.showAndWait();
			}
		}
	}
	@FXML
	protected void consultaCarona(ActionEvent event) {
		
		if(event.getSource() == bntConsutCarona) {
			Carona carona = servsCarona.buscatodas();
			chBoxIdCarona.getItems().add(carona);
			try {
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
}
