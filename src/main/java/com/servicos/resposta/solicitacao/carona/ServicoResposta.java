package com.servicos.resposta.solicitacao.carona;

import org.apache.log4j.Logger;

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
	final static Logger logger = Logger.getLogger(ServicoResposta.class);

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
		logger.info("Iniciando o método");
		if (pontos == null || pontos.equals("")) {
			logger.error("ponto invalido");
			throw new ErroException("ponto invalido");
		}
		int id = sistemaDao.buscaIdPerfil(idSessao);
		sistemaDao.salvaResposta(idSessao,idCarona, idSolicitacoes,pontos,id);
		logger.info("Fim do método");
	}
	/**
	 * Retorna os pontos de encontro cadastrado.
	 * 
	 * @param idSessao identificador de uma sessao.
	 * @param idCarona identificador de uma carona
	 * @return Retonar a resposta do ponto de encontro sugerido.
	 */
	public String getPontosEncontro(String idSessao, int idCarona) {
		logger.info("Iniciando o método");
		return sistemaDao.getPontosEncontro(idSessao, idCarona);
	}
	
}
