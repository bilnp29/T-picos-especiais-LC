package com.easyaccept;

import com.servicos.carona.ControleCarona;
import com.servicos.perfil.ControlePerfil;
import com.servicos.resposta.solicitacao.carona.ControleResposta;
import com.servicos.solicitacoes.ControleSolicitacao;
import com.servicos.usuario.ControleUsuario;

/**
 * Esta classe irá realizar os testes de verificação dos métodos abaixo.
 * 
 * @author Bruno Miranda, Thassio Lucena.
 *
 */
public class FachadaEasyaccept {

	private ControleUsuario controleUsuario;
	private ControleCarona controleCarona;
	private ControleSolicitacao controleSolicitacoes;
	private ControleResposta respostaSolicitacoes;
	private ControlePerfil controlePerfil;

	public FachadaEasyaccept() {
		controleUsuario = new ControleUsuario();
		controleCarona = new ControleCarona();
		controleSolicitacoes = new ControleSolicitacao();
		respostaSolicitacoes = new ControleResposta();
		controlePerfil = new ControlePerfil();
	}

	public String criarUsuario(String login, String senha, String nome, String endereco, String email, String telefone)
			throws Exception {
		return controleUsuario.cadastraUsuario(login, senha, nome, endereco, email, telefone);
	}

	public String abrirSessao(String login, String senha) throws Exception {
		return controleUsuario.entraSistema(login, senha);
	}

	public String cadastrarCarona(String idSessao, String origem, String destino, String data, String hora,
			String vagas) throws Exception {
		return controleCarona.cadastroCarona(idSessao, origem, destino,data, hora, vagas);
	}
	public String cadastrarCaronaMunicipal(String idSessao, String origem, String destino, String cidade ,String data, String hora,
			String vagas) throws Exception {
		return controleCarona.cadastraCaronaMunicipal(idSessao, origem, destino,cidade ,data, hora, vagas);
	}

	public String localizarCarona(String idSessao, String origem, String destino) throws Exception {
		return controleCarona.pesquisaCarona(idSessao, origem, destino);

	}

	public String getAtributoUsuario(String login, String atributo) throws Exception {

		return controleUsuario.localizarUsuario(login, atributo);
	}

	public String getAtributoCarona(int idCarona, String atributo) {
		return controleCarona.localizarCarona(idCarona, atributo);
	}
	public boolean getAtributoCaronaMunicipal(int idCarona, String atributo) {
		return controleCarona.getAtributoCaronaMunicipal(idCarona, atributo);
	}
	
	public String localizarCaronaMunicipal(String idSessao, String cidade, String origem, String destino) { 
		return controleCarona.localizarCaronaMunicipal(idSessao, cidade, origem, destino);
	}
	public String localizarCaronaMunicipal(String idSessao, String cidade) {
		return controleCarona.localizarCaronaMunicipal(idSessao,cidade);
		
	}

	public String getCarona(int idcarona) {
		return controleCarona.getCarona(idcarona);
	}

	public String getTrajeto(int idcarona) {
		return controleCarona.getTrajeto(idcarona);
	}

	public int sugerirPontoEncontro(String idSessao, int idCarona, String pontos) {
		return controleSolicitacoes.sugerirPontoEncontro(idSessao, idCarona, pontos);

	}

	public void responderSugestaoPontoEncontro(String idSessao, int idCarona, int idSolicitacoes, String pontos) {
		respostaSolicitacoes.respostaPontoEncontro(idSessao, idCarona, idSolicitacoes, pontos);
	}

	public int solicitarVagaPontoEncontro(String idSessao, int idCarona, String ponto) {
		return controleSolicitacoes.solicitarVagaPontoEncontro(idSessao, idCarona, ponto);
	}

	public String getAtributoSolicitacao(int idSolicitacao, String atributo) {
		return controleSolicitacoes.getAtributoSolicitacao(idSolicitacao, atributo);
	}

	public void aceitarSolicitacaoPontoEncontro(String idSessao, int idSolicitacao) {
		controleSolicitacoes.aceitarSolicitacaoPontoEncontro(idSessao, idSolicitacao);
	}

	public void desistirRequisicao(String idSessao, int idCarona, int idSolicitacao) {
		controleSolicitacoes.desistirRequisicao(idSessao, idCarona, idSolicitacao);
	}

	public int solicitarVaga(String idSessao, int idCarona) {
		return controleSolicitacoes.solicitarVaga(idSessao, idCarona);
	}

	public String getAtributoSolicitacaoSemSugestao(int idSolicitacaoVaga, String atributo) {
		return controleSolicitacoes.getAtributoSolicitacaoSemSugestao(idSolicitacaoVaga, atributo);
	}

	public void aceitarSolicitacao(String idSessao, int idSolicitacao) {
		controleSolicitacoes.aceitarSolicitacao(idSessao, idSolicitacao);
	}

	public void rejeitarSolicitacao(String idSessao, int idSolicitacao) {
		controleSolicitacoes.rejeitarSolicitacao(idSessao, idSolicitacao);
	}

	public int visualizarPerfil(String idSessao, String login) {
		return controlePerfil.visualizarPerfil(idSessao, login);
	}

	public String getAtributoPerfil(String login, String atributo) {
		return controlePerfil.getAtributoPerfil(login, atributo);
	}

	public String getTodasCaronasUsuario(String idSessao) {
		return controleCarona.getTodasCaronasUsuario(idSessao);
	}

	public String getSolicitacoesConfirmadas(String idSessao, int idCarona) {
		return controleSolicitacoes.getSolicitacoesConfirmadas(idSessao, idCarona);
	}

	public String getSolicitacoesPendentes(String idSessao, int idCarona) {
		return controleSolicitacoes.getSolicitacoesPendentes(idSessao, idCarona);
	}

	public String getPontosEncontro(String idSessao, int idCarona) {
		return respostaSolicitacoes.getPontosEncontro(idSessao, idCarona);
	}

	public String getPontosSugeridos(String idSessao, int idCarona) {
		return controleSolicitacoes.getPontosSugeridos(idSessao, idCarona);
	}

	public void reviewVagaEmCarona(String idSessao, int idCarona, String loginCaroneiro, String review) {
		controleSolicitacoes.reviewVagaEmCarona(idSessao, idCarona, loginCaroneiro, review);
	}
	public void reviewCarona (String idSessao, int Carona, String review) {
		controleSolicitacoes.reviewCarona(idSessao, Carona, review);
	}
	public int cadastrarInteresse(String idSessao, String origem, String destino, String data, String horaInicio, String horaFim) {
		return controleCarona.cadastrarInteresse(idSessao, origem, destino, data, horaInicio, horaFim);
	}
	public String verificarMensagensPerfil(String idSessao) {
		return controlePerfil.verificarMensagensPerfil(idSessao);
	}

	public void encerrarSistema() {
		controleUsuario.fechaSistema();

	}

	public void reiniciarSistema() {
		controleUsuario.reiniciarSistema();
	}

	public void encerrarSessao(String login) {
		controleUsuario.encerraSistemalogin(login);
	}

	public void zerarSistema() {
		controleUsuario.apagarDados();
	}

}
