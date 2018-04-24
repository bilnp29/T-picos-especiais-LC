package com.servicos.resposta.solicitacao.carona;

import com.dominio.dao.SistemaDao;
import com.tratamento.erro.ErroException;

public class ServicoResposta {
	private SistemaDao sistemaDao;

	public ServicoResposta() {
		sistemaDao = new SistemaDao();
	}

	public void responderPontoEncotro(String idSessao, int idCarona, int idSolicitacoes, String pontos) {
		if (pontos == null || pontos.equals("")) {
			throw new ErroException("ponto invalido");
		}
		int id = sistemaDao.buscaIdPerfil(idSessao);
		sistemaDao.salvaResposta(idSessao,idCarona, idSolicitacoes,pontos,id);
	}

	/**
	 * @param idSessao
	 * @param idCarona
	 * @return Retonar a resposta do ponto de encontro sugerido.
	 */
	public String getPontosEncontro(String idSessao, int idCarona) {
		
		return sistemaDao.getPontosEncontro(idSessao, idCarona);
	}
	
}
