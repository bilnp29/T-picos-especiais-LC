package com.servicos.solicitacoes;

public class ControleSolicitacao {

	private ServicoSolicitacao servicosSolicitacoes;

	public ControleSolicitacao() {
		servicosSolicitacoes = new ServicoSolicitacao();
	}

	/**
	 * Método realizar chamada de <b> sugestaoPontoEncontro(idSessao, idCarona,
	 * pontos) </b> tem como retorno um identificador da susgetão. Maiores detalhes
	 * no método em questão.
	 * 
	 * @param idSessao
	 *            identificador de uma sessao
	 * @param idCarona
	 *            identificador de uma carona
	 * @param pontos
	 *            Pontos de encontro sugerido pelo caroneio
	 * @return retorna o identificador de sugestaoPontoEncontro
	 * 
	 * @see sugestaoPontoEncontro
	 */
	public int sugerirPontoEncontro(String idSessao, int idCarona, String pontos) {

		return servicosSolicitacoes.sugestaoPontoEncontro(idSessao, idCarona, pontos);
	}

	/**
	 * Solicitar vaga em uma carona cadastrada no sistema
	 * 
	 * @param idSessao
	 *            identificador de uma sessão de um usuário
	 * @param idCarona
	 *            identificador de uma carona
	 * @param ponto
	 *            Ponto de encontro para de uma carona
	 * @return retorna um identificador
	 * 
	 */
	public int solicitarVagaPontoEncontro(String idSessao, int idCarona, String ponto) {
		return servicosSolicitacoes.requisitarVagas(idSessao, idCarona, ponto);

	}

	/**
	 * Realiza consulta em uma solicitação cadastrada, sugerindo o ponto de
	 * encontro.
	 * 
	 * @param idSolicitacao
	 *            identificador de uma solicitação
	 * @param atributo
	 *            parametro utilizado para realizar uma pesquisa
	 * @return retorna o atributo pesquisado
	 */
	public String getAtributoSolicitacao(int idSolicitacao, String atributo) {

		return servicosSolicitacoes.consultaVagas(idSolicitacao, atributo);
	}

	/**
	 * Aceita a solicitacao de vaga na carona com ponto de encontro sugeriodo
	 * 
	 * @param idSessao
	 *            identificador de uma sessao de usuario
	 * @param idSolicitacao
	 *            identificador de uma solicitação
	 * 
	 */
	public void aceitarSolicitacaoPontoEncontro(String idSessao, int idSolicitacao) {
		servicosSolicitacoes.aceitarSolicitacao(idSessao, idSolicitacao);

	}

	public void desistirRequisicao(String idSessao, int idCarona, int idSolicitacao) {
		servicosSolicitacoes.desistirSolicitacao(idSessao, idCarona, idSolicitacao);

	}

	/**
	 * Método solicita vaga em carona cadastrada no sistema, sem sugerir ponto de
	 * encontro.
	 * 
	 * @param idSessao
	 *            identificador de uma conta de usuário
	 * @param idCarona
	 *            identificador de uma carona
	 * @return retorna o identificador da solicitação cadastrada
	 */
	public int solicitarVaga(String idSessao, int idCarona) {

		return servicosSolicitacoes.solicitarVaga(idSessao, idCarona);
	}

	/**
	 * Realiza consulta em uma solicitação cadastrada, sem sugerindo o ponto de
	 * encontro.
	 * 
	 * @param idSolicitacaoVaga
	 *            identificador de uma solicitação sem ponto de encotro
	 * @param atributo
	 *            Parametro utilizado para realizar uma pesquisa
	 * @return retorna o atributo pesquisado
	 */
	public String getAtributoSolicitacaoSemSugestao(int idSolicitacaoVaga, String atributo) {
		return servicosSolicitacoes.consultaVagas_SemSugestao(idSolicitacaoVaga, atributo);
	}

	/**
	 * Aceita a solicitacao de vaga na carona <b> SEM </b> ponto de encontro
	 * sugeriodo
	 * 
	 * @param idSessao
	 *            identificador de uma sessao
	 * @param idSolicitacao
	 *            identificador de uma solicitação
	 * 
	 */
	public void aceitarSolicitacao(String idSessao, int idSolicitacao) {
		servicosSolicitacoes.aceitarSolicitacao_SemPontoEncontro(idSessao, idSolicitacao);
	}

	/**
	 * Rejeita uma solicitacao PENDENTE.
	 * 
	 * @param idSessao
	 *            identificador de uma sessão de usuário
	 * @param idSolicitacao
	 *            identificador de um solicitação
	 * 
	 */
	public void rejeitarSolicitacao(String idSessao, int idSolicitacao) {
		servicosSolicitacoes.rejeitarSolicitacao(idSessao, idSolicitacao);
	}

	/**
	 * Buscar solicitações confirmadas
	 * 
	 * @param idSessao
	 *            identificador de uma sessão
	 * @param idCarona
	 *            identificador de uma carona
	 * @return a quantidade de solicitações confirmadas em caronas que não foi
	 *         sugerido ponto de encontro
	 */
	public String getSolicitacoesConfirmadas(String idSessao, int idCarona) {

		return servicosSolicitacoes.getSolicitacoesConfirmadas(idSessao, idCarona);
	}

	/**
	 * buscar valor de solicitação pendentes
	 * 
	 * @param idSessao
	 *            identificador de uma sessão
	 * @param idCarona
	 *            identificador de uma carona
	 * @return a quantidade de solicitações Pendentes em caronas que não foi
	 *         sugerido ponto de encontro
	 */
	public String getSolicitacoesPendentes(String idSessao, int idCarona) {

		return servicosSolicitacoes.getSolicitacoesPendentes(idSessao, idCarona);
	}

	/**
	 * buscar pontos sugeridos
	 * 
	 * @param idSessao
	 *            identificador de uma sessão
	 * @param idCarona
	 *            identificador de uma carona
	 * @return O ponto de encontro sugerido
	 */
	public String getPontosSugeridos(String idSessao, int idCarona) {

		return servicosSolicitacoes.getPontosSugeridos(idSessao, idCarona);
	}

	/**
	 * Cadastra review de uma carona
	 * 
	 * @param idSessao
	 *            identificador de uma sessão de usuário
	 * @param idCarona
	 *            identificador de uma carona
	 * @param loginCaroneiro
	 *            paramento utilizado para fazer login no sistema
	 * @param review
	 *            atributo utilizado para cadastra informações sobre a carona
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
