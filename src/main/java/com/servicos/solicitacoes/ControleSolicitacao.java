package com.servicos.solicitacoes;

public class ControleSolicitacao {

	private ServicoSolicitacao servicosSolicitacoes;

	public ControleSolicitacao() {
		servicosSolicitacoes = new ServicoSolicitacao();
	}

	/**
	 * @param idSessao
	 * @param idCarona
	 * @param pontos
	 * @return
	 * 
	 * 		Método realizar chamada de <b> sugestaoPontoEncontro(idSessao,
	 *         idCarona, pontos) </b> tem como retorno um identificador da susgetão.
	 *         Maiores detalhes no método em questão.
	 */
	public int sugerirPontoEncontro(String idSessao, int idCarona, String pontos) {

		return servicosSolicitacoes.sugestaoPontoEncontro(idSessao, idCarona, pontos);
	}

	/**
	 * @param idSessao
	 * @param idCarona
	 * @param ponto
	 * 
	 *            Solicitar vaga em uma carona cadastrada no sistema
	 */
	public int solicitarVagaPontoEncontro(String idSessao, int idCarona, String ponto) {
		return servicosSolicitacoes.requisitarVagas(idSessao, idCarona, ponto);

	}

	/**
	 * @param idSolicitacao
	 * @param atributo
	 * @return Realiza consulta em uma solicitação cadastrada, sugerindo o ponto de
	 *         encontro.
	 */
	public String getAtributoSolicitacao(int idSolicitacao, String atributo) {

		return servicosSolicitacoes.consultaVagas(idSolicitacao, atributo);
	}

	/**
	 * @param idSessao
	 * @param idSolicitacao
	 *            Aceita a solicitacao de vaga na carona com ponto de encontro
	 *            sugeriodo
	 */
	public void aceitarSolicitacaoPontoEncontro(String idSessao, int idSolicitacao) {
		servicosSolicitacoes.aceitarSolicitacao(idSessao, idSolicitacao);

	}

	public void desistirRequisicao(String idSessao, int idCarona, int idSolicitacao) {
		servicosSolicitacoes.desistirSolicitacao(idSessao, idCarona, idSolicitacao);

	}

	/**
	 * @param idSessao
	 * @param idCarona
	 * @return Método solicita vaga em carona cadastrada no sistema, sem sugerir
	 *         ponto de encontro.
	 */
	public int solicitarVaga(String idSessao, int idCarona) {

		return servicosSolicitacoes.solicitarVaga(idSessao, idCarona);
	}

	/**
	 * @param idSolicitacaoVaga
	 * @param atributo
	 * @return Realiza consulta em uma solicitação cadastrada, sem sugerindo o ponto
	 *         de encontro.
	 */
	public String getAtributoSolicitacaoSemSugestao(int idSolicitacaoVaga, String atributo) {
		return servicosSolicitacoes.consultaVagas_SemSugestao(idSolicitacaoVaga, atributo);
	}

	/**
	 * @param idSessao
	 * @param idSolicitacao
	 *            Aceita a solicitacao de vaga na carona <b> SEM </b> ponto de
	 *            encontro sugeriodo
	 */
	public void aceitarSolicitacao(String idSessao, int idSolicitacao) {
		servicosSolicitacoes.aceitarSolicitacao_SemPontoEncontro(idSessao, idSolicitacao);
	}

	/**
	 * @param idSessao
	 * @param idSolicitacao
	 *            Rejeita uma solicitacao PENDENTE.
	 */
	public void rejeitarSolicitacao(String idSessao, int idSolicitacao) {
		servicosSolicitacoes.rejeitarSolicitacao(idSessao, idSolicitacao);
	}

	/**
	 * @param idSessao
	 * @param idCarona
	 * @return a quantidade de solicitações confirmadas em caronas que não foi
	 *         sugerido ponto de encontro
	 */
	public String getSolicitacoesConfirmadas(String idSessao, int idCarona) {

		return servicosSolicitacoes.getSolicitacoesConfirmadas(idSessao, idCarona);
	}

	/**
	 * @param idSessao
	 * @param idCarona
	 * @return a quantidade de solicitações Pendentes em caronas que não foi
	 *         sugerido ponto de encontro
	 */
	public String getSolicitacoesPendentes(String idSessao, int idCarona) {

		return servicosSolicitacoes.getSolicitacoesPendentes(idSessao, idCarona);
	}

	/**
	 * @param idSessao
	 * @param idCarona
	 * @return O ponto de encontro sugerido
	 */
	public String getPontosSugeridos(String idSessao, int idCarona) {

		return servicosSolicitacoes.getPontosSugeridos(idSessao, idCarona);
	}

	/**
	 * @param idSessao
	 * @param idCarona
	 * @param loginCaroneiro
	 * @param review
	 */
	public void reviewVagaEmCarona(String idSessao, int idCarona, String loginCaroneiro, String review) {

		servicosSolicitacoes.reviewVagaEmCarona(idSessao, idCarona, loginCaroneiro, review);
	}

	/**
	 * Método ira direcionar a informação para classe {@link ServicoSolicitacao}
	 * 
	 * @param idSessao
	 *            identificador de uma sessão
	 * @param carona
	 *            identificador de uma carona
	 * @param review
	 *            Atributo faz referencia a um comentario de um caroneiro
	 */
	public void reviewCarona(String idSessao, int carona, String review) {
		servicosSolicitacoes.reviewCarona(idSessao, carona, review);

	}

}
