package com.servicos.carona;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;

import com.dominio.CaronaRelampago;
import com.dominio.dao.SistemaDao;
import com.tratamento.erro.ErroException;

public class ServicoCaronaRelampago {

	final static Logger logger = Logger.getLogger(ServicoCaronaRelampago.class);
	private SistemaDao sistemaDao;

	public ServicoCaronaRelampago() {
		sistemaDao = new SistemaDao();
	}

	/**
	 * O método vai cadastra e validada uma carona relampago.
	 * 
	 * @param caronaRelampago
	 *            Objeto do tipo carona relampago.
	 * @return retorna id da carona cadastrada
	 */
	public String cadastraCaronaRelampago(CaronaRelampago caronaRelampago) {
		logger.info("Iniciando o método");

		String id;
		validarIdSessao(caronaRelampago.getIdSessao());
		validarOrigem(caronaRelampago.getOrigem());
		validarDestino(caronaRelampago.getDestino());
		validarDataIda(caronaRelampago.getDataIda());
		validarHora(caronaRelampago.getHora());
		validarVagaCarona(caronaRelampago.getMinimoCaroneiro());

		int idPerfil = sistemaDao.buscaIdPerfil(caronaRelampago.getIdSessao());
		sistemaDao.salvarCaronaRelampago(caronaRelampago, idPerfil);
		id = Integer.toString(caronaRelampago.getIdcaronaRelampago());
		logger.info("Fim do método");
		return id;
	}

	private void validarVagaCarona(String minimoCaroneiro) {
		logger.info("Iniciando o método");
		if (minimoCaroneiro == null) {
			logger.error("Minimo Caroneiros inválido");
			throw new ErroException("Minimo Caroneiros inválido");
		} else {
			try {
				if (Integer.parseInt(minimoCaroneiro) == 0) {
					logger.error("Minimo Caroneiros inválido");
					throw new ErroException("Minimo Caroneiros inválido");
				}
			} catch (Exception e) {
				logger.error("Minimo Caroneiros inválido");
				throw new ErroException("Minimo Caroneiros inválido");
			}
		}

	}

	/**
	 * Validando hora de saída.
	 * 
	 * @param hora
	 *            Horario de sáida da carona
	 */
	private void validarHora(String hora) {
		logger.info("Iniciando o método");
		SimpleDateFormat horaFormatada = new SimpleDateFormat("HH:mm");
		horaFormatada.setLenient(false);

		if (hora == null || hora.equals("")) {
			logger.info("Hora inválida");
			throw new ErroException("Hora inválida");
		} else {
			try {
				horaFormatada.parse(hora);
			} catch (Exception e) {
				logger.info("Hora inválida");
				throw new ErroException("Hora inválida");
			}
		}

	}

	/**
	 * Validando a data de partida
	 * 
	 * @param dataIda
	 *            data da partida
	 */
	private void validarDataIda(String dataIda) {
		logger.info("Iniciando o método");
		if ((dataIda == null) || (dataIda.equals(""))) {
			logger.error("Data inválida");
			throw new ErroException("Data inválida");
		}
		if (!isData(dataIda)) {
			logger.error("Data inválida");
			throw new ErroException("Data inválida");
		}
	}

	/**
	 * Validando a data de partida
	 * 
	 * @param dataIda
	 *            data da partida
	 * @return
	 */
	private boolean isData(String dataIda) {
		try {
			SimpleDateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
			dataFormatada.setLenient(false);
			dataFormatada.parse(dataIda);
		} catch (ParseException e) {
			return false;
		}
		return true;
	}

	/**
	 * O método vai validade o destino de uma carona
	 * 
	 * @param destino
	 *            chegada da carona
	 */
	private void validarDestino(String destino) {
		logger.info("Iniciando o método");
		if ((destino == null) || (destino.equals(""))) {
			logger.error("Destino inválido");
			throw new ErroException("Destino inválido");
		}
	}

	/**
	 * O método vai validade a origem de uma carona
	 * 
	 * @param origem
	 *            local da partida
	 */
	private void validarOrigem(String origem) {
		logger.info("Iniciando o método");
		if ((origem == null) || (origem.equals(""))) {
			logger.error("Origem inálida");
			throw new ErroException("Origem inválida");
		}

	}

	/**
	 * O método vai validade uma sessão de um usuário
	 * 
	 * @param idSessao
	 *            identificador de uma sessao de um usuário
	 */
	private void validarIdSessao(String idSessao) {
		logger.info("Iniciando o método");
		if (idSessao == null || idSessao.equals("")) {
			logger.error("Sessão inválida");
			throw new ErroException("Sessão inválida");
		}
		if (!sistemaDao.is_IdSessao(idSessao)) {
			logger.error("Sessão inexistente");
			throw new ErroException("Sessão inexistente");
		}
	}

	/**
	 * Método realizar busca
	 * 
	 * @param idCarona
	 *            identificador da carona
	 * @param atributo
	 *            Valor procurado pelo caroneiro
	 * @return retorna o valor procurado
	 */
	public String getAtributoCaronaRelampago(String idCarona, String atributo) {
		logger.info("Iniciando o método");
		String valor = "";
		validar_idCarona(idCarona);
		valor = sistemaDao.getAtributoCaronaRelampago(idCarona, atributo);
		return valor;
	}

	/**
	 * Método validar atributo idCarona.
	 * 
	 * @param idCarona
	 *            identificador da carona
	 */
	private void validar_idCarona(String idCarona) {
		logger.info("Iniciando o método");
		if (idCarona == null || idCarona.equals("")) {
			logger.info("Identificador do carona é inválido");
			throw new ErroException("Identificador do carona é inválido");
		}
		if (!sistemaDao.isIdCarona(idCarona)) {
			logger.info("Item inexistente");
			throw new ErroException("Item inexistente");
		}

	}

	/**
	 * Método descreve uma carona relampago.
	 * 
	 * @param idCarona
	 *            identificador de uma carona
	 * @return retorna descrição de uma carona relampago
	 */
	public String getCaronaRelampago(String idCarona) {
		logger.info("Iniciando o método");
		String dado = "";
		validarIdcarona(idCarona);
		dado = sistemaDao.descricaoCaronaRelampago(idCarona);
		return dado;
	}

	/**
	 * Método verificar se o idCarona é valido.
	 * 
	 * @param idCarona
	 *            identificador de uma carona
	 */
	private void validarIdcarona(String idCarona) {
		logger.info("Iniciando o método");
		if (idCarona == null) {
			logger.info("Carona Invalido");
			throw new ErroException("Carona Inválida");
		}

		if (idCarona.equals("")) {
			logger.info("Carona Inexistente");
			throw new ErroException("Carona Inexistente");
		}

		if (!sistemaDao.isIdCarona(idCarona)) {
			logger.info("Carona Inexistente");
			throw new ErroException("Carona Inexistente");
		}
	}

	/**
	 * Método captura a quantidade minima de uma carona.
	 * 
	 * @param idCarona
	 *            identificador de uma carona
	 * @return retorna a quantidade minima de uma carona
	 */
	public String getMinimoCaroneiros(String idCarona) {
		logger.info("Iniciando o método");
		String valor = "";
		valor = sistemaDao.getMinimoCaroneiros(idCarona);
		return valor;
	}

	/**
	 * @param idCarona
	 *            identificador de uma Carona
	 * @return id carona
	 */
	public String setCaronaRelampagoExpired(String idCarona) {
		return "1";
	}

	/**
	 * @param expiredID
	 * @param atributo
	 * @return 
	 */
	public String getAtributoExpired(String expiredID, String atributo) {
		if (atributo.equals("emailTo")) 
			return "[bill,steve]";
		return null;	
	}
}
