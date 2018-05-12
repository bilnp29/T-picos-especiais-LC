package com.servicos.resposta.solicitacao.carona;

import com.dominio.dao.SistemaDao;
import com.tratamento.erro.ErroException;

/**
 * Execulta serviços para obter resposta.
 * 
 * @author Bruno Miranda / Thassio Lucena
 *
 */
public class ServicoResposta {
	private SistemaDao sistemaDao;

	public ServicoResposta() {
		sistemaDao = new SistemaDao();
	}

	/**
	 * Direciona a pesquisa para a classe resposta.
	 * 
	 * @param idSessao identificador de uma sessão
	 * @param idCarona identificador de uma carona
	 * @param idSolicitacoes identificado de uma solicitação
	 * @param pontos pontos de encontro cadastrados.
	 */
	public void responderPontoEncotro(String idSessao, int idCarona, int idSolicitacoes, String pontos) {
		if (pontos == null || pontos.equals("")) {
			throw new ErroException("ponto invalido");
		}
		int id = sistemaDao.buscaIdPerfil(idSessao);
		sistemaDao.salvaResposta(idSessao,idCarona, idSolicitacoes,pontos,id);
	}
	/**
	 * Retorna os pontos de encontro cadastrado.
	 * 
	 * @param idSessao identificador de uma sessao.
	 * @param idCarona identificador de uma carona
	 * @return Retonar a resposta do ponto de encontro sugerido.
	 */
	public String getPontosEncontro(String idSessao, int idCarona) {
		
		return sistemaDao.getPontosEncontro(idSessao, idCarona);
	}
	
}
