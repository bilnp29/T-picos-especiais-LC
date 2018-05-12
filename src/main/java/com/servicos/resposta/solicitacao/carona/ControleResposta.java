package com.servicos.resposta.solicitacao.carona;

/**
 * Classe irá direcionar as informações para a classe ServicoResposta;
 * @author Bruno Miranda / Thassio
 *
 */
public class ControleResposta {
	
	private ServicoResposta resposta;
	
	public ControleResposta() {
		resposta = new ServicoResposta();
	}

	/**
	 * Direciona a pesquisa para a classe resposta.
	 * 
	 * @param idSessao identificador de uma sessão
	 * @param idCarona identificador de uma carona
	 * @param idSolicitacoes identificado de uma solicitação
	 * @param pontos pontos de encontro cadastrados.
	 */
	public void respostaPontoEncontro(String idSessao, int idCarona, int idSolicitacoes, String pontos) {
		resposta.responderPontoEncotro(idSessao, idCarona, idSolicitacoes, pontos);
		
	}

	/**
	 * Retorna os pontos de encontro cadastrado.
	 * 
	 * @param idSessao identificador de uma sessao.
	 * @param idCarona identificador de uma carona
	 * @return Retonar a resposta do ponto de encontro sugerido.
	 */
	public String getPontosEncontro(String idSessao, int idCarona) {
		
		return resposta.getPontosEncontro(idSessao, idCarona);
	}

}
