package com.servicos.carona;

import org.apache.log4j.Logger;

import com.dominio.CaronaRelampago;

/**
 * Controla as ações da classe Carona Relampago
 * 
 * @author Bruno Miranda / Thassio Lucena
 * @see CaronaRelampago
 */

public class ControleCaronaRelampago {

	final static Logger logger = Logger.getLogger(ControleCaronaRelampago.class);
	private CaronaRelampago caronaRelampago;
	private ServicoCaronaRelampago servicoCaronaRelampago;

	public ControleCaronaRelampago() {
		servicoCaronaRelampago = new ServicoCaronaRelampago();
	}

	/**
	 * O método recebe as informações do usuario e repassa para a classe
	 * ServicoCaronaRelampago consultar a mesma.
	 * 
	 * @param idSessao
	 *            identificador da sessao usuário
	 * @param origem
	 *            local de partida
	 * @param destino
	 *            Chegada de carona
	 * @param dataIda
	 *            Data partida
	 * @param dataVolta
	 *            Data da volta
	 * @param minimoCaroneiro
	 *            Quantidade minima para a carona acontecer
	 * @return retorna o id da carona cadastrada
	 * @see ServicoCaronaRelampago
	 */
	public String cadastraCaronaRelampago(String idSessao, String origem, String destino, String dataIda,
			String dataVolta, String hora, String minimoCaroneiro) {
		logger.info("Iniciando o método cadastraCaronaRelampago - Classe ControleCaronaRelampago");
		caronaRelampago = new CaronaRelampago(idSessao, origem, destino, dataIda, dataVolta, hora, minimoCaroneiro);
		return servicoCaronaRelampago.cadastraCaronaRelampago(caronaRelampago);
	}

	/**
	 * Método de busca, repassa as entrada para a classe ServicoCaronaRelampago onde será executada as operações de busca.
	 * @param idCarona identificador da carona
	 * @param atributo valor procurado pelo caroneiro
	 * @return retorna o valor procurado.
	 */
	public String getAtributoCaronaRelampago(String idCarona, String atributo) {
		logger.info("Iniciando o método getAtributoCaronaRelampago - Classe ControleCaronaRelampago");
		return servicoCaronaRelampago.getAtributoCaronaRelampago(idCarona,atributo);
	}

	/**
	 * Método irá auxiliar na funcao de descrever um carona relampago
	 * @param idCarona identificados da carona
	 * @return retorna uma descrição de uma carona relampago
	 */
	public String getCaronaRelampago(String idCarona) {
		logger.info("Iniciando o método getCaronaRelampago - Classe ControleCaronaRelampago");
		return servicoCaronaRelampago.getCaronaRelampago(idCarona);
	}

	/**
	 * Buscar quantidade minima de vagas em uma carona relampago
	 * @param idCarona identificador de uma carona
	 * @return retorna quantidade de minima de vaga em uma carona relampago.
	 */
	public String getMinimoCaroneiros(String idCarona) {
		logger.info("Iniciando o método getMinimoCaroneiros - Classe ControleCaronaRelampago");
		return servicoCaronaRelampago.getMinimoCaroneiros(idCarona);
	}

	/**
	 * @param idCarona identificador da carona
	 * @return retorna um identificador
	 */
	public String setCaronaRelampagoExpired(String idCarona) {
		
		return servicoCaronaRelampago.setCaronaRelampagoExpired(idCarona);
	}

	/**
	 * @param expiredID identificador
	 * @param emailTo Envia email para
	 * @return retorna uma lista
	 */
	public String getAtributoExpired(String expiredID, String atributo) {
		
		return servicoCaronaRelampago.getAtributoExpired(expiredID, atributo);
	}

}
