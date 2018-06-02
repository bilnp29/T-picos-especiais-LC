package com.ui.controle;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
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
			pnSolicitarCarona, pnAceitarCarona_ComPonto, pn_SolicitacaoSemPonto, pnAceitarCarona_SemPontoEncontro ,pn_sugerirPontoEncontro,
			pn_DesistirSolicitacao, pn_ResponderSolicitacao_ComPonto;
	/**
	 * But�es responsalves por fazer opera��es em um perfi de usu�rio.
	 */
	@FXML
	private Button btnSalva;

	@FXML
	private AnchorPane apnGeral, pnPerfil;

	@FXML
	private MenuButton mbnPerfil, mbnSair, mbnCarona, mbnSolicitacao;
	
	@FXML
	private ImageView imgFecha;

	public FXMLperfilUsuarioController() {
		this.itemEditar = new MenuItem();
		this.itemConsultarPerfil = new MenuItem();
		this.itemConsultarUsuario = new MenuItem();
		this.mitEncerra = new MenuItem();
		this.mitCadastraCaronaMunicipal = new MenuItem();
		this.mitConsultCarona = new MenuItem();
		this.mitConsultTrajeto = new MenuItem();
		this.mitDescCarona = new MenuItem();
		this.mitRejeitCarona = new MenuItem();
		this.mit_SolicitCaronaComPontoEncontro = new MenuItem();
		this.mit_AceitSolicitacaoComPontoEncontro = new MenuItem();
		this.mit_SolicitCaronaSemPontoEncontro = new MenuItem();
		this.mit_AceitSolictSemPontoEncontro = new MenuItem();
		this.mit_SugerPontEncontro = new MenuItem();
		this.mitDesistCarona = new MenuItem();
		this.mit_RespondeSugestaoPontoEncontro = new MenuItem();

		this.pnEditar = new Pane();
		this.pnConsultaPerfil = new Pane();
		this.pnCons_Usuario = new Pane();
		this.pnPerfilCarona = new Pane();
		this.pnCadastraCaronaMunicipal = new Pane();
		this.pnCadastraCaronaIntermunicipal = new Pane();
		this.pnConsultCarona = new Pane();
		this.pnConsutTrajeto = new Pane();
		this.pnConsultDescricaoCarona = new Pane();
		this.pnRejeitaCarona = new Pane();
		this.pnCarona = new Pane();
		this.pnSolicitarCarona = new Pane();
		this.pnAceitarCarona_ComPonto = new Pane();
		this.pn_SolicitacaoSemPonto = new Pane();
		this.pnAceitarCarona_SemPontoEncontro = new Pane();
		this.pn_sugerirPontoEncontro = new Pane();
		this.pn_DesistirSolicitacao = new Pane();
		this.pn_ResponderSolicitacao_ComPonto = new Pane();

		this.mbnPerfil = new MenuButton();
		this.mbnSair = new MenuButton();
		this.mbnCarona = new MenuButton();
		this.mbnSolicitacao = new MenuButton();

		this.btnSalva = new Button();

		this.imgFecha = new ImageView();
		
		this.apnGeral = new AnchorPane();
		this.pnPerfil = new AnchorPane();
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
				|| pnSolicitarCarona.isVisible() || pnAceitarCarona_ComPonto.isVisible() || pnAceitarCarona_SemPontoEncontro.isVisible()
				|| pn_SolicitacaoSemPonto.isVisible() || pn_sugerirPontoEncontro.isVisible()
				|| pn_DesistirSolicitacao.isVisible() || pn_ResponderSolicitacao_ComPonto.isVisible()) {
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

	@FXML
	protected void funcaoEditarPerfil(ActionEvent event) {
		if (event.getSource() == itemEditar) {
			setarVisibilidade();
			pnPerfilCarona.setVisible(true);
			pnEditar.setVisible(true);
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
		if(event.getSource() == mit_SolicitCaronaComPontoEncontro ) {
			setarVisibilidade();
			pnCarona.setVisible(true);
			pnSolicitarCarona.setVisible(true);
		}
	}
	@FXML
	protected void funcaoAceitarCaronaComPontoEncontro(ActionEvent event) {
		if(event.getSource() == mit_AceitSolicitacaoComPontoEncontro) {
			setarVisibilidade();
			pnCarona.setVisible(true);
			pnAceitarCarona_ComPonto.setVisible(true);
		}
	}
	
	
	@FXML
	protected void funcaoSolicitarCaronaSemPontoEncontro(ActionEvent event) {
		if(event.getSource() == mit_SolicitCaronaSemPontoEncontro) {
			setarVisibilidade();
			pnCarona.setVisible(true);
			pn_SolicitacaoSemPonto.setVisible(true);
		}
	}

	@FXML
	protected void funcaoAceitarCaronaSemPontoEncontro(ActionEvent event) {
		if(event.getSource() == mit_AceitSolictSemPontoEncontro) {
			setarVisibilidade();
			pnCarona.setVisible(true);
			pnAceitarCarona_SemPontoEncontro.setVisible(true);
			
		}
	}
	
	@FXML
	protected void funcaoSugerirPontoEncontro(ActionEvent event) {
		if(event.getSource() == mit_SugerPontEncontro) {
			setarVisibilidade();
			pnCarona.setVisible(true);
			pn_sugerirPontoEncontro.setVisible(true);
		}
	}
	
	@FXML
	protected void funcaoDesistirPontoEncontro(ActionEvent event) {
		if(event.getSource() == mitDesistCarona) {
			setarVisibilidade();
			pnCarona.setVisible(true);
			pn_DesistirSolicitacao.setVisible(true);
		}
	}
	
	@FXML
	protected void funcaoResponderSolicitacaoPontoEncontro(ActionEvent event) {
		if(event.getSource() == mit_RespondeSugestaoPontoEncontro) {
			setarVisibilidade();
			pnCarona.setVisible(true);
			pn_ResponderSolicitacao_ComPonto.setVisible(true);
		}
	}
	
	@FXML
	protected void funcaoFecharSessao(MouseEvent event) {
		if(event.getSource() == imgFecha) {
			System.exit(0);
		}
	}
	
	@FXML
	protected void funcaoEncerrarSessao(ActionEvent event) {
		if(event.getSource() == mitEncerra) {
			System.exit(0);
		}
	}
}
