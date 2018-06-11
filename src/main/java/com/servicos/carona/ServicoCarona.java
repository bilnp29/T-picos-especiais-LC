package com.servicos.carona;

import java.text.ParseException; 
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.log4j.Logger;

import com.dominio.Carona;
import com.dominio.dao.SistemaDao;
import com.tratamento.erro.ErroException;

/**
 * Esta classe executa os serviços necessarios para validar uma carona, apois
 * isso delega as informações para SistemaDao() para fazer a persistencia. Temos
 * ainda, métodos que realiza pesquisa como o localizarCarona() carona que irá
 * retorna uma listas das caronas cadastrada no sistema.
 * 
 * @author Bruno Miranda, Thassio Lucena.
 */
public class ServicoCarona { 

	final static Logger logger = Logger.getLogger(ServicoCarona.class);

	private SistemaDao sistemaDao;

	public ServicoCarona() {
		sistemaDao = new SistemaDao();

	}

	/**
	 * Verifica se o atributo data é válido, caso sim retotna uma data valida.
	 * 
	 * @param carona
	 *            objeto carona contendo informações de uma carona
	 * @throws Exception
	 *             mensagem de erro Data invalida.
	 * 
	 */
	private void dataValida(Carona carona) throws Exception {
		String data = carona.getData();

		if (data == null || data.isEmpty()) {
			logger.info("Data invalida");
			throw new ErroException("Data invalida");
		}
		if (!isData(data)) {
			logger.info("Data invalida");
			throw new ErroException("Data invalida");
		}
	}

	/**
	 * Verifica se a data em questão existe. Exemplo quando solicitado avalia uma
	 * data passada ex:30/02/2017. Este método verificar os limites das data de cada
	 * mês.
	 * 
	 * @param data
	 *            atributo data a ser verificado
	 * @return retorna um data válida
	 */
	private Boolean isData(String data) {

		try {
			SimpleDateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
			dataFormatada.setLenient(false);
			dataFormatada.parse(data);
		} catch (ParseException e) {
			return false;
		}
		return true;
	}

	/**
	 * Verifica se o atributo hora é válido. Caso sim retorna uma data valida.
	 * 
	 * @param carona
	 *            objeto carona contendo informações de uma carona
	 * @throws Exception
	 *             menssagem de erro Hora invalida
	 */
	public void horaValida(Carona carona) throws Exception {
		String hora = carona.getHora();
		SimpleDateFormat horaFormatada = new SimpleDateFormat("HH:mm");
		horaFormatada.setLenient(false);

		if (hora == null || hora.equals("")) {
			logger.info("Hora invalida");
			throw new ErroException("Hora invalida");
		} else {
			try {
				horaFormatada.parse(hora);
			} catch (Exception e) {
				logger.info("Hora invalida");
				throw new ErroException("Hora invalida");
			}
		}
	}

	/**
	 * Verifica se o atributo vagas é válido. Este atributo representa a quantidade
	 * de vagas disponivel na carona.
	 * 
	 * @param carona
	 *            objeto carona contendo informações de uma carona
	 * @throws Exception
	 *             menssagem de erro vaga invalida
	 * 
	 * 
	 */
	public void vagasValidas(Carona carona) throws Exception {
		if (carona.getVagas() == null) {
			logger.info("Vaga invalida");
			throw new ErroException("Vaga invalida");
		} else {
			try {
				Integer.parseInt(carona.getVagas());
			} catch (Exception e) {
				logger.info("Vaga invalida");
				throw new ErroException("Vaga invalida");
			}
		}

	}

	/**
	 * Verifica se o atributo Origem é válido. Trata-se do local de partida da
	 * carona.
	 * 
	 * @param carona
	 *            objeto carona contendo informações de uma carona
	 * @throws Exception
	 *             menssagem de erro Origem invalida
	 * 
	 */
	public void validaCaronaOrigem(Carona carona) throws Exception {
		if (carona.getOrigemCarona() == null || carona.getOrigemCarona().equals("")) {
			logger.info("Origem invalida");
			throw new ErroException("Origem invalida");
		}

	}

	/**
	 * Verifica se o atributo destino é válido. a chagada o termino da carona.
	 * 
	 * @param carona
	 *            objeto carona contendo informações de uma carona
	 * @throws Exception
	 *             menssagem de erro Destino invalido
	 * 
	 */
	public void validaCaronaDestino(Carona carona) throws Exception {
		if (carona.getDestinoCarona() == null || carona.getDestinoCarona().equals("")) {
			logger.info("Destino invalido");
			throw new ErroException("Destino invalido");
		}

	}

	/**
	 * Verificar se o idSessao é valido.
	 * 
	 * @param idSessao
	 *            identificador de uma sessao
	 * @throws Exception 
	 * 
	 */
	private void validarIdSessao(String idSessao) throws Exception {
		String sessaoid = sistemaDao.buscarIdsessao(idSessao);
		if (idSessao == null || idSessao.isEmpty()) {
			logger.info("Sessao invalida");
			throw new Exception("Sessao invalida");
		}
		if (sessaoid.equals("")) {
			logger.info("Sessao inexistente");
			throw new Exception("Sessao inexistente");
		}

	}

	/**
	 * Método que irá verificar se a carona apresenta dados válidos. Caso sim irá
	 * salva os dados no bd e retorna um idCarona. Esta carona é do tipo
	 * Intermunicipal.
	 * 
	 * @param idSessao
	 *            identificador de uma sessão
	 * @param carona
	 *            objeto carona contendo informações de uma carona
	 * @return retorna o id da carona cadastrada
	 * @throws Exception
	 *             retorna uma messagem de erro caso algo de errado.
	 * 
	 * 
	 */
	public String validaCarona(String idSessao, Carona carona) throws Exception {

		String idCarona;
		validarIdSessao(idSessao);
		validaCaronaOrigem(carona);
		validaCaronaDestino(carona);
		dataValida(carona);
		horaValida(carona);
		vagasValidas(carona);
		carona.setTipoCarona("Intermunicipal");

		int idPerfil = sistemaDao.buscaIdPerfil(idSessao);
		sistemaDao.salvarDadosCarona(carona, idPerfil);
		idCarona = Integer.toString(carona.getIdCaronas());

		return idCarona;
	}

	/**
	 * Método que irá verificar se a carona apresenta dados válidos . Caso sim irá
	 * salva os dados no bd e retorna um idCarona. Carona do tipo municipal
	 * 
	 * @param idSessao
	 *            identificador de uma sessão
	 * @param carona
	 *            objeto carona contendo informações de uma carona
	 * @return retorna o id da carona cadastrada
	 * @throws Exception
	 *             retorna uma messagem de erro caso algo de errado.
	 */
	public String validaCaronaMunicipal(String idSessao, Carona carona) throws Exception {
		String idCarona;
		validarIdSessao(idSessao);
		validaCaronaOrigem(carona);
		validaCaronaDestino(carona);
		dataValida(carona);
		horaValida(carona);
		vagasValidas(carona);

		carona.setTipoCarona("Municipal");
		int idPerfil = sistemaDao.buscaIdPerfil(idSessao);
		sistemaDao.salvarDadosCarona(carona, idPerfil);

		idCarona = Integer.toString(carona.getIdCaronas());

		return idCarona;
	}

	/**
	 * Buscar dados de uma carona como a origem e o destino. busca destinada a
	 * caronas intermunicipais.
	 * 
	 * @param idSessao
	 *            identificador de uma sessão
	 * @param origem
	 *            local de embarque da carona
	 * @param destino
	 *            local de desembarque da carona
	 * @return retorna o atributo pesquisado
	 * @throws Exception
	 *             messagens de erro associado ao método.
	 */
	public String localizarCarona(String idSessao, String origem, String destino) throws Exception {

		verificar_Origem_Destino(origem, destino);

		String texto = "{";
		List<Carona> carona = sistemaDao.buscarCarona();
		if (carona == null || carona.isEmpty()) {
			return texto + "}";
		}
		for (Carona cr : carona) {
			if (cr.getIdSessao().equals(idSessao) || !cr.getIdSessao().equals(idSessao)) {
				if (origem.equals("")) {
					if (destino.equals("")) {
						texto += cr.getIdCaronas() + ",";
					} else {
						if (cr.getDestinoCarona().equals(destino)) {
							texto += cr.getIdCaronas() + ",";
						}
					}
				} else {
					if (destino.equals("")) {
						if (cr.getOrigemCarona().equals(origem)) {
							texto += cr.getIdCaronas() + ",";
						}
					} else {
						if (cr.getOrigemCarona().equals(origem) && cr.getDestinoCarona().equals(destino)) {
							texto += cr.getIdCaronas() + ",";
						}
					}
				}
			}
		}

		if (!texto.equals("{")) {
			texto = texto.substring(0, texto.length() - 1);
		}
		texto += "}";
		return texto;
	}

	/**
	 * Verificar os valores de origem e destino. qualquer valor diferente do que
	 * temos abaixo lançara um erro.
	 * 
	 * @param origem
	 *            local de embarque da carona
	 * @param destino
	 *            local de desembarque da carona
	 */
	public void verificar_Origem_Destino(String origem, String destino) {
		if (!origem.matches("^[ a-zA-Z ã á â é ê i í ó õ ô ú]*$")) {
			logger.info("Origem invalida");
			throw new ErroException("Origem invalida");
		}
		if (!destino.matches("^[ a-zA-Z ã á â é ê i í ó õ ô ú]*$")) {
			logger.info("Destino invalido");
			throw new ErroException("Destino invalido");
		}
	}

	/**
	 * Esta buscar será realizada pela classe <b>JdbcCaronaDao</b>
	 * 
	 * @param idCarona
	 *            identificado de uma carona
	 * @param atributo
	 *            este parametro fornece informações para realizar um busca.
	 * @return retorna o dado informado
	 * 
	 * @see SistemaDao
	 * @see buscarAtributoCarona
	 */
	public String pesquisaCarona(String idCarona, String atributo) {
		String dado = "";
		verificarId(idCarona);
		dado = sistemaDao.buscarAtributoCarona(idCarona, atributo);
		return dado;

	}
	
	

	private void verificarId(String idCarona) {
		
		if (idCarona == null || idCarona.equals("")) {
			logger.info("Identificador do carona é inválido");
			throw new ErroException("Identificador do carona é inválido");
		}
		if (!sistemaDao.isIdCarona(idCarona)) {
			logger.info("Item inexistente");
			throw new ErroException("Item inexistente");
		}
		
	}

	private void validarIdcarona(String idCarona) {
		
		if (idCarona == null) {
			logger.info("Carona Inválido");
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
	 * buscar dados de uma carona, pra ver o método de busca verificar classe
	 * <b>descricaoCarona</b>
	 * 
	 * @param idcarona
	 *            identificador de uma carona
	 * @return Retorna uma descrição de uma carona cadastrada. informando a
	 *         origem,destino,hora e vaga.
	 * @see descricaoCarona
	 */
	public String buscaCaronaCadastrada(String idcarona) {
		String dado = "";
		validarIdcarona(idcarona);
		dado = sistemaDao.descricaoCarona(idcarona);
		return dado;
	}

	/**
	 * Este método irá direcionar o serviço de buscar trajetoria de uma carona. Onde
	 * será passado com parametro de entrada o idCarona que por sua vez chama o
	 * método descricaoTrajeto(idCarona). Retornando um trajeto valido.
	 * 
	 * @param idcarona
	 *            identifcador de uma carona
	 * @return retorna dados de uma trajetoria de uma carona
	 */
	public String descreverTrajeto(String idcarona) {
		String dado = "";
		validarTrajeto(idcarona);
		dado = sistemaDao.descricaoTrajero(idcarona);
		return dado;
	}

	/**
	 * Verificar se a carona é valida
	 * 
	 * @param idcarona
	 *            identificador de uma carona
	 */
	public void validarTrajeto(String idcarona) {

		if (idcarona == null ) {
			logger.info("Trajeto Invalido");
			throw new ErroException("Trajeto Inválido");
		}
		
		if (idcarona.equals("")) {
			logger.info("Trajeto Inexistente");
			throw new ErroException("Trajeto Inexistente");
		}
		
		if (!sistemaDao.isIdCarona(idcarona)) {
			logger.info("Trajeto Inexistente");
			throw new ErroException("Trajeto Inexistente");
		}
	}

	/**
	 * Faz uma busca nas caronas cadastradas de acordo com o idSessao.
	 * 
	 * @param idSessao
	 *            identificador de uma sessão
	 * @return retorna os identificadores de todas as caronas cadastradas
	 */
	public String getTodasCaronasUsuario(String idSessao) {
		return sistemaDao.getTodasCaronasUsuario(idSessao);
	}

	/**
	 * Verificar se uma carona é do tipo municipal ou intermunicipal.
	 * 
	 * @param idCarona
	 *            identificador de uma carona
	 * @param atributo
	 *            Valor a ser pesquisado
	 * @return dado pesquisado
	 */
	public boolean getAtributoCaronaMunicipal(int idCarona, String atributo) {

		return sistemaDao.getAtributoCaronaMunicipal(idCarona, atributo);
	}

	/**
	 * localizarCaronaMunicipal-> Este método faz um pesquisa no sistema com base no
	 * parametro abaixo. Obs.: O paramentro <b>cidade</b> é obrigatorio. O mesmo irá
	 * retorna uma ou varias carona do tipo municipal.
	 * 
	 * @param idSessao
	 *            Identificador de uma sessão ativa de um usuário
	 * @param cidade
	 *            Local onde a carona vai acontecer <b>(parametro obrigatorio)</b>
	 * @param origem
	 *            partida da carona
	 * @param destino
	 *            chegada da carona
	 * @return O retorno será uma lista de caronas do tipo municipal cadastrada para
	 *         a pesquisa.
	 */
	public String localizarCaronaMunicipal(String idSessao, String cidade, String origem, String destino) {

		validarCidade(cidade);

		return sistemaDao.buscarCarona_Municipio_id(idSessao, cidade, origem, destino);
	}

	/**
	 * Verifica se o parametro é null ou vazio.
	 * 
	 * @param cidade
	 *            nome da cidade
	 */
	public void validarCidade(String cidade) {
		if (cidade == null || cidade.equals("")) {
			logger.info("Cidade inexistente");
			throw new ErroException("Cidade inexistente");
		}
	}

	/**
	 * localizarCaronaMunicipal-> Este método faz um pesquisa no sistema com base no
	 * parametro abaixo. Obs.: O paramentro <b>cidade</b> é obrigatorio. O mesmo irá
	 * retorna uma ou varias carona do tipo municipal.
	 * 
	 * @param idSessao
	 *            Identificador de uma sessão ativa de um usuário
	 * @param cidade
	 *            Local onde a carona vai acontecer <b>(parametro obrigatorio)</b>
	 * 
	 * @return O retorno será uma lista de caronas do tipo municipal cadastrada para
	 *         a pesquisa.
	 */

	public String localizarCaronaMunicipal(String idSessao, String cidade) {
		validarCidade(cidade);

		return sistemaDao.buscarCaronaMunicipio(idSessao, cidade);
	}

	/**
	 * Método verificar os atributos informados e chama o método de mesmo nome na
	 * classe sistemaDao.
	 * 
	 * @param idSessao
	 *            identificador de uma sessao
	 * @param origem
	 *            partida de uma carona
	 * @param destino
	 *            chegada da carona
	 * @param data
	 *            data de saída
	 * @param horaInicio
	 *            hora da saída
	 * @param horaFim
	 *            hora da chegada
	 * @return retorna o identificador da carona interessada.
	 * @see SistemaDao
	 */
	public int cadastrarInteresse(String idSessao, String origem, String destino, String data, String horaInicio,
			String horaFim) {
		logger.info("Iniciando o método");
		verificar_Origem_Destino(origem, destino);
		validaData(data);

		return sistemaDao.cadastrarInteresse(idSessao, origem, destino, data, horaInicio, horaFim);
	}

	/**
	 * Caso a data informada seja nula lança um exceção.
	 * 
	 * @param data
	 *            atributo informado pelo usuario
	 */
	private void validaData(String data) {
		logger.info("Iniciando o método");
		if (data == null) {
			logger.info("Data invalida");
			throw new ErroException("Data invalida");

		}
		logger.info("Fim do método");
	}
}
