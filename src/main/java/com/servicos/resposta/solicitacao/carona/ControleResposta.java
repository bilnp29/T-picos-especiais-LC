package com.servicos.resposta.solicitacao.carona;

public class ControleResposta {
	
	private ServicoResposta resposta;
	
	public ControleResposta() {
		resposta = new ServicoResposta();
	}

	/**
	 * @param idSessao
	 * @param idCarona
	 * @param idSolicitacoes
	 * @param pontos
	 */
	public void respostaPontoEncontro(String idSessao, int idCarona, int idSolicitacoes, String pontos) {
		resposta.responderPontoEncotro(idSessao, idCarona, idSolicitacoes, pontos);
		
	}

	/**
	 * @param idSessao
	 * @param idCarona
	 * @return Retonar a resposta do ponto de encontro sugerido.
	 */
	public String getPontosEncontro(String idSessao, int idCarona) {
		
		return resposta.getPontosEncontro(idSessao, idCarona);
	}

}
