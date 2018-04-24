package com.servicos.solicitacoes;

import com.dominio.dao.SistemaDao;
import com.tratamento.erro.ErroException;

public class ServicoSolicitacao {

	private SistemaDao sistemaDao;
	private final String ESTADO_SOLICITACAO;
	private final String SITUACAO_SOLICITACAO_VAGA;

	public ServicoSolicitacao() {
		sistemaDao = new SistemaDao();
		ESTADO_SOLICITACAO = "YES";
		SITUACAO_SOLICITACAO_VAGA = "PENDENTE";
	}

	/**
	 * @param idSessao
	 * @param idCarona
	 * @param pontos
	 * @return
	 * 
	 * 		Deacordo com os atributos passados o método verificar se o ponto de
	 *         encontro é válido; Captura o nome do socilitante com a chamada do
	 *         método <b>nomeSolicitante(idSessao)</b>; o método
	 *         <b>salvaPontoEncontro(idSessao, idCarona, pontos, solicitante)</b>
	 *         salva os dados na base de dados solicitacoes com base no atributos
	 *         passados retorna um id.
	 */

	public int sugestaoPontoEncontro(String idSessao, int idCarona, String pontos) {
		if (pontos == null || pontos.equals("")) {
			throw new ErroException("ponto invalido");
		}
		String solicitante = sistemaDao.nomeSolicitante(idSessao);
		int id_perfil = sistemaDao.buscaIdPerfil(idSessao);
		int id = sistemaDao.salvaPontoEncontro(idSessao, idCarona, pontos, solicitante, id_perfil);
		return id;
	}

	/**
	 * @param idSessao
	 * @param idCarona
	 * @param ponto
	 * @return
	 * 
	 * 		Deacordo com os atributos passados o método verificar se o ponto de
	 *         partida é válido; Captura o nome do socilitante com a chamada do
	 *         método <b>nomeSolicitante(idSessao)</b>; o método
	 *         <b>salvaSolicitacaoVaga(idSessao, idCarona, pontos, solicitante)</b>
	 *         salva os dados na base de dados solicitacoes com base no atributos
	 *         passados retorna um id.
	 */
	public int requisitarVagas(String idSessao, int idCarona, String ponto) {
		if (ponto == null || ponto.equals("")) {
			throw new ErroException("ponto invalido");
		}
		String solicitante = sistemaDao.nomeSolicitante(idSessao);
		int idSolicitacao = sistemaDao.salvaSolicitacaoVaga(idSessao, idCarona, ponto, solicitante);
		return idSolicitacao;
	}

	/**
	 * @param idSolicitacao
	 * @param atributo
	 * @return
	 */
	public String consultaVagas(int idSolicitacao, String atributo) {
		if (idSolicitacao == 0) {
			throw new ErroException("identificador solicitação inexistente");
		}
		return sistemaDao.consultaAtibutoSolicitacao(idSolicitacao, atributo);
	}

	/**
	 * @param idSessao
	 * @param idSolicitacao
	 */
	public void aceitarSolicitacao(String idSessao, int idSolicitacao) {
		String donoCarona = sistemaDao.buscarIdSesao(idSolicitacao);
		if (!donoCarona.equals(idSessao)) {
			throw new ErroException("Solicitacao inexistente");
		} else {
			int idCarona = sistemaDao.buscaridCarona(idSolicitacao);
			sistemaDao.atualizarVagaCarona(idCarona);
			sistemaDao.atualizarEstadoSolicitacao(idSolicitacao);
		}
	}

	/**
	 * @param idSessao
	 * @param idCarona
	 * @param idSolicitacao
	 */
	public void desistirSolicitacao(String idSessao, int idCarona, int idSolicitacao) {
		String estadoSolicitacao = sistemaDao.buscarEstadoSolicitacao(idSolicitacao);
		if (!estadoSolicitacao.equals(ESTADO_SOLICITACAO)) {
			sistemaDao.atualizarSituacaoSolicitacao(idSolicitacao);
		} else {
			sistemaDao.encrementaVagaCarona(idCarona);
			sistemaDao.atualizarSituacaoSolicitacao(idSolicitacao);
		}
	}

	/**
	 * @param idSessao
	 * @param idCarona
	 * @return Método solicita vaga em carona cadastrada no sistema, sem sugerir
	 *         ponto de encontro.
	 */
	public int solicitarVaga(String idSessao, int idCarona) {
		int idSolicitacaoVaga = 0;
		String solicitante = sistemaDao.nomeSolicitante(idSessao);
		idSolicitacaoVaga = sistemaDao.salva_Solicitacao_Vaga_Sem_Sugestao(idSessao, idCarona, solicitante,
				SITUACAO_SOLICITACAO_VAGA);

		return idSolicitacaoVaga;
	}

	/**
	 * @param idSolicitacaoVaga
	 * @param atributo
	 * @return Consulta vaga cadastrada sem solicitar ponto de encontro.
	 */
	public String consultaVagas_SemSugestao(int idSolicitacaoVaga, String atributo) {

		return sistemaDao.consulta_Vaga_Cadastrada_Sem_Sugestao(idSolicitacaoVaga, atributo);
	}

	/**
	 * @param idSessao
	 * @param idSolicitacao
	 *            Aceita a solicitacao de vaga na carona <b> SEM </b> ponto de
	 *            encontro sugeriodo, a mesma só e aceita pelo motorista.
	 */
	public void aceitarSolicitacao_SemPontoEncontro(String idSessao, int idSolicitacao) {
		String idSessaoDonoCarona = sistemaDao.buscaridSessaoDonoCarona(idSolicitacao);
		if (!idSessaoDonoCarona.equals(idSessao)) {
			throw new ErroException("Solicitacao inexistente");
		} else {
			String situacao_Solicitacao = sistemaDao.verificar_Solicitacao_Vaga(idSolicitacao);
			if (!SITUACAO_SOLICITACAO_VAGA.equals(situacao_Solicitacao)) {
				throw new ErroException("Solicitacao inexistente");
			} else {
				int idCarona = sistemaDao.capturardCarona(idSolicitacao);
				sistemaDao.atualizarVagaCarona(idCarona);
				sistemaDao.updateSituacao_SemPonto(idSolicitacao);
			}
		}
	}

	/**
	 * @param idSessao
	 * @param idSolicitacao
	 *            Método irá rejeita uma solicitação PENDETE.
	 */
	public void rejeitarSolicitacao(String idSessao, int idSolicitacao) {

		String idSessaoDonoCarona = sistemaDao.buscaridSessaoDonoCarona(idSolicitacao);
		if (!idSessaoDonoCarona.equals(idSessao)) {
			throw new ErroException("Solicitacao inexistente");
		} else {
			String situacao_Solicitacao = sistemaDao.verificar_Solicitacao_Vaga(idSolicitacao);
			if (!SITUACAO_SOLICITACAO_VAGA.equals(situacao_Solicitacao)) {
				throw new ErroException("Solicitacao inexistente");
			} else {
				sistemaDao.rejeita_Solicitacao(idSolicitacao);
			}
		}
	}

	/**
	 * @param idSessao
	 * @param idCarona
	 * @return a quantidade de solicitações confirmadas em caronas que não foi
	 *         sugerido ponto de encontro
	 */
	public String getSolicitacoesConfirmadas(String idSessao, int idCarona) {

		return sistemaDao.getSolicitacoesConfirmadas(idSessao, idCarona);
	}

	/**
	 * @param idSessao
	 * @param idCarona
	 * @return a quantidade de solicitações Pendentes em caronas que não foi
	 *         sugerido ponto de encontro
	 */
	public String getSolicitacoesPendentes(String idSessao, int idCarona) {

		return sistemaDao.getSolicitacoesPendentes(idSessao, idCarona);
	}

	/**
	 * @param idSessao
	 * @param idCarona
	 * @return
	 */
	public String getPontosSugeridos(String idSessao, int idCarona) {

		return sistemaDao.getPontosSugeridos(idSessao, idCarona);
	}

	/**
	 * @param idSessao
	 * @param idCarona
	 * @param loginCaroneiro
	 * @param review
	 */
	public void reviewVagaEmCarona(String idSessao, int idCarona, String loginCaroneiro, String review) {
		if (review.equals("não dou mais carona")) {
			throw new ErroException("Opção invalida");
		} else if (review.equals("não funcionou")) {
			throw new ErroException("Usuario não possui vaga na carona");
		} else {
			if(review.equals("faltou")) {
				sistemaDao.reviewCaronaPresenca(idSessao,idCarona,loginCaroneiro,review);
			}else if(review.equals(("nao faltou"))) {
				sistemaDao.reviewCaronaPresenca(idSessao,idCarona,loginCaroneiro,review);
			
			}else {
				
			}
		}
		
	}

}
