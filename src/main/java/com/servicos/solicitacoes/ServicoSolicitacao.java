package com.servicos.solicitacoes;

import org.apache.log4j.Logger;

import com.dominio.dao.SistemaDao;
import com.tratamento.erro.ErroException;

public class ServicoSolicitacao {

	private SistemaDao sistemaDao;
	private final String ESTADO_SOLICITACAO;
	private final String SITUACAO_SOLICITACAO_VAGA;
	final static Logger logger = Logger.getLogger(ServicoSolicitacao.class);

	public ServicoSolicitacao() {
		sistemaDao = new SistemaDao();
		ESTADO_SOLICITACAO = "YES";
		SITUACAO_SOLICITACAO_VAGA = "PENDENTE";
	}

	/**
	 * Deacordo com os atributos passados o método verificar se o ponto de encontro
	 * é válido; Captura o nome do socilitante com a chamada do método
	 * <b>nomeSolicitante(idSessao)</b>; o método <b>salvaPontoEncontro(idSessao,
	 * idCarona, pontos, solicitante)</b> salva os dados na base de dados
	 * solicitacoes com base no atributos passados retorna um id.
	 * 
	 * @param idSessao
	 *            identificador de uma sessão de usuário
	 * @param idCarona
	 *            identificador de uma carona
	 * @param pontos
	 *            pontos de encontro sugerido pelo caroneiro
	 * @return retorna um identificador
	 * 
	 * 
	 */

	public int sugestaoPontoEncontro(String idSessao, int idCarona, String pontos) {
		logger.info("iniciando método");
		if (pontos == null || pontos.equals("")) {
			logger.error("ponto invalido");
			throw new ErroException("ponto invalido");
		}
		String solicitante = sistemaDao.nomeSolicitante(idSessao);
		int id_perfil = sistemaDao.buscaIdPerfil(idSessao);
		int id = sistemaDao.salvaPontoEncontro(idSessao, idCarona, pontos, solicitante, id_perfil);
		logger.info("fim do método");
		return id;
	}

	/**
	 * Deacordo com os atributos passados o método verificar se o ponto de partida é
	 * válido; Captura o nome do socilitante com a chamada do método
	 * <b>nomeSolicitante(idSessao)</b>; o método <b>salvaSolicitacaoVaga(idSessao,
	 * idCarona, pontos, solicitante)</b> salva os dados na base de dados
	 * solicitacoes com base no atributos passados retorna um id.
	 * 
	 * @param idSessao
	 *            identificador de uma sessão de usuário
	 * @param idCarona
	 *            identificador de uma carona
	 * @param ponto
	 *            ponto de encotro de uma carona.
	 * @return retorna um identificador
	 * 
	 * 
	 */
	public int requisitarVagas(String idSessao, int idCarona, String ponto) {
		logger.info("Iniciando método");
		if (ponto == null || ponto.equals("")) {
			logger.error("ponto invalido");
			throw new ErroException("ponto invalido");
		}
		String solicitante = sistemaDao.nomeSolicitante(idSessao);
		int idSolicitacao = sistemaDao.salvaSolicitacaoVaga(idSessao, idCarona, ponto, solicitante);
		logger.info("fim do método");
		return idSolicitacao;
	}

	/**
	 * buscar informações de uma carona
	 * 
	 * @param idSolicitacao
	 *            identificador de uma solicitação em uma vaga em caronas cadastrada
	 * @param atributo
	 *            O mesmo é utilizado para fazer uma pesquisa
	 * @return retorna o paramentro pesquisado.
	 * 
	 */
	public String consultaVagas(int idSolicitacao, String atributo) {
		logger.info("Iniciando método");
		if (idSolicitacao == 0) {
			logger.error("identificador solicitação inexistente");
			throw new ErroException("identificador solicitação inexistente");
		}
		logger.info("Fim do método");
		return sistemaDao.consultaAtibutoSolicitacao(idSolicitacao, atributo);
	}

	/**
	 * Cadastrar a solicitação aceita.
	 * 
	 * @param idSessao
	 *            identificador de uma sessão de usuário
	 * @param idSolicitacao
	 *            identificador uma solicitação
	 */
	public void aceitarSolicitacao(String idSessao, int idSolicitacao) {
		logger.info("Iniciando método");
		String donoCarona = sistemaDao.buscarIdSesao(idSolicitacao);
		if (!donoCarona.equals(idSessao)) {
			logger.error("Solicitacao inexistente");
			throw new ErroException("Solicitacao inexistente");
		} else {
			int idCarona = sistemaDao.buscaridCarona(idSolicitacao);
			sistemaDao.atualizarVagaCarona(idCarona);
			sistemaDao.atualizarEstadoSolicitacao(idSolicitacao);
		}
		logger.info("Fim do método");
	}

	/**
	 * desistir de uma solicitação
	 * 
	 * @param idSessao
	 *            identificador de uma sessão de usuário
	 * @param idCarona
	 *            identificador de uma carona
	 * @param idSolicitacao
	 *            identificador de uma solicitação
	 */
	public void desistirSolicitacao(String idSessao, int idCarona, int idSolicitacao) {
		logger.info("inicializando o método");
		String estadoSolicitacao = sistemaDao.buscarEstadoSolicitacao(idSolicitacao);
		if (!estadoSolicitacao.equals(ESTADO_SOLICITACAO)) {
			sistemaDao.atualizarSituacaoSolicitacao(idSolicitacao);
		} else {
			sistemaDao.encrementaVagaCarona(idCarona);
			sistemaDao.atualizarSituacaoSolicitacao(idSolicitacao);
		}
		logger.error("fim do método");
	}

	/**
	 * Método solicita vaga em carona cadastrada no sistema, sem sugerir ponto de
	 * encontro.
	 * 
	 * @param idSessao
	 *            identificador de uma sessão de usuário
	 * @param idCarona
	 *            identificador de uma carona
	 * @return retorna um id da soliciatação
	 */
	public int solicitarVaga(String idSessao, int idCarona) {
		logger.info("iniciando método");
		int idSolicitacaoVaga = 0;
		String solicitante = sistemaDao.nomeSolicitante(idSessao);
		idSolicitacaoVaga = sistemaDao.salva_Solicitacao_Vaga_Sem_Sugestao(idSessao, idCarona, solicitante,
				SITUACAO_SOLICITACAO_VAGA);
		logger.info("fim do método");
		return idSolicitacaoVaga;
	}

	/**
	 * Consulta vaga cadastrada sem solicitar ponto de encontro.
	 * 
	 * @param idSolicitacaoVaga
	 *            identificador de uma vaga
	 * @param atributo
	 *            parametro utilizado para uma pesquisa
	 * @return retorna o parametro pesquisado
	 */
	public String consultaVagas_SemSugestao(int idSolicitacaoVaga, String atributo) {
		logger.info("inicando método");
		return sistemaDao.consulta_Vaga_Cadastrada_Sem_Sugestao(idSolicitacaoVaga, atributo);
	}

	/**
	 * Aceita a solicitacao de vaga na carona <b> SEM </b> ponto de encontro
	 * sugeriodo, a mesma só e aceita pelo motorista.
	 * 
	 * @param idSessao
	 *            identificador de uma sessão de usuário
	 * @param idSolicitacao
	 *            identificador de um solicitação sem ponto de encontro
	 * 
	 */
	public void aceitarSolicitacao_SemPontoEncontro(String idSessao, int idSolicitacao) {
		logger.info("iniciado método");
		String idSessaoDonoCarona = sistemaDao.buscaridSessaoDonoCarona(idSolicitacao);
		if (!idSessaoDonoCarona.equals(idSessao)) {
			logger.error("Solicitacao inexistente");
			throw new ErroException("Solicitacao inexistente");
		} else {
			String situacao_Solicitacao = sistemaDao.verificar_Solicitacao_Vaga(idSolicitacao);
			if (!SITUACAO_SOLICITACAO_VAGA.equals(situacao_Solicitacao)) {
				logger.error("Solicitacao inexistente");
				throw new ErroException("Solicitacao inexistente");
			} else {
				int idCarona = sistemaDao.capturardCarona(idSolicitacao);
				sistemaDao.atualizarVagaCarona(idCarona);
				sistemaDao.updateSituacao_SemPonto(idSolicitacao);
			}
		}
		logger.info("fim do método");
	}

	/**
	 * Método irá rejeita uma solicitação PENDETE.
	 * 
	 * @param idSessao
	 *            identificador de uma sessão de usuário
	 * @param idSolicitacao
	 *            identificador de uma solicitação
	 * 
	 */
	public void rejeitarSolicitacao(String idSessao, int idSolicitacao) {
		logger.info("iniciando método");
		String idSessaoDonoCarona = sistemaDao.buscaridSessaoDonoCarona(idSolicitacao);
		if (!idSessaoDonoCarona.equals(idSessao)) {
			logger.error("Solicitacao inexistente");
			throw new ErroException("Solicitacao inexistente");
		} else {
			String situacao_Solicitacao = sistemaDao.verificar_Solicitacao_Vaga(idSolicitacao);
			if (!SITUACAO_SOLICITACAO_VAGA.equals(situacao_Solicitacao)) {
				logger.error("Solicitacao inexistente");
				throw new ErroException("Solicitacao inexistente");
			} else {
				sistemaDao.rejeita_Solicitacao(idSolicitacao);
			}
		}
	}

	/**
	 * buscar solicitações confirmadas
	 * 
	 * @param idSessao
	 *            identificador de uma sessão de usuário
	 * @param idCarona
	 *            identificador de uma carona
	 * @return a quantidade de solicitações confirmadas em caronas que não foi
	 *         sugerido ponto de encontro
	 */
	public String getSolicitacoesConfirmadas(String idSessao, int idCarona) {

		return sistemaDao.getSolicitacoesConfirmadas(idSessao, idCarona);
	}

	/**
	 * buscar solicitações pendentes
	 * 
	 * @param idSessao
	 *            identificador de uma sessão de usuário
	 * @param idCarona
	 *            identificador de uma carona
	 * @return a quantidade de solicitações Pendentes em caronas que não foi
	 *         sugerido ponto de encontro
	 */
	public String getSolicitacoesPendentes(String idSessao, int idCarona) {

		return sistemaDao.getSolicitacoesPendentes(idSessao, idCarona);
	}

	/**
	 * buscar pontos de encontro sugerido
	 * 
	 * @param idSessao
	 *            identificador de uma sessão de usuário
	 * @param idCarona
	 *            identificador de uma carona
	 * @return retorna pontos de encontro
	 */
	public String getPontosSugeridos(String idSessao, int idCarona) {

		return sistemaDao.getPontosSugeridos(idSessao, idCarona);
	}

	/**
	 * @param idSessao
	 *            identificador de uma sessão de usuário
	 * @param idCarona
	 *            identificador de uma carona
	 * @param loginCaroneiro
	 * @param review
	 */
	public void reviewVagaEmCarona(String idSessao, int idCarona, String loginCaroneiro, String review) {
		logger.info("iniciando método");
		if (review.equals("não dou mais carona")) {
			logger.error("Opeção Invalida");
			throw new ErroException("Opção invalida");
		} else if (review.equals("não funcionou")) {
			logger.error("Usuario não possui vaga na carona");
			throw new ErroException("Usuario não possui vaga na carona");
		} else {
			if (review.equals("faltou")) {
				sistemaDao.reviewCaronaPresenca(idSessao, idCarona, loginCaroneiro, review);
			} else if (review.equals(("nao faltou"))) {
				sistemaDao.reviewCaronaPresenca(idSessao, idCarona, loginCaroneiro, review);

			} else {

			}
		}
		logger.info("fim do método");

	}

	/**
	 * O método abaixo faz algumas verificações antes de direcionar a informação
	 * para ser salva.
	 * 
	 * @param idSessao
	 *            identificador de uma sessão
	 * @param carona
	 *            identificador de uma carona
	 * @param review
	 *            Atributo faz referencia a um comentario de um caroneiro
	 */
	public void reviewCarona(String idSessao, int carona, String review) {
		logger.info("iniciando o método");
		if (review.equals("bacana")) {
			logger.error("Opção invalida");
			throw new ErroException("Opção invalida");
		} else if (sistemaDao.existeIdSessao(idSessao)) {
			logger.error("Usuario não possui vaga na carona");
			throw new ErroException("Usuario não possui vaga na carona");
		} else {
			if (review.equals("segura e tranquila")) {
				sistemaDao.review_Motorista(idSessao, carona, review);
			} else if (review.equals("não funcionou")) {
				sistemaDao.review_Motorista(idSessao, carona, review);
			}
		}
		logger.info("fim método");

	}

}
