package com.servicos.carona;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import com.dominio.Carona;
import com.dominio.dao.SistemaDao;
import com.tratamento.erro.ErroException;

/**
 * @author Bruno Miranda, Thassio Lucena.Esta classe executa os serviços
 *         necessarios para validar uma carona, apois isso delega as informações
 *         para SistemaDao() para fazer a persistencia. Temos ainda, métodos que
 *         realiza pesquisa como o localizarCarona() carona que irá retorna uma
 *         listas das caronas cadastrada no sistema.
 */
public class ServicoCarona {
	private SistemaDao sistemaDao;

	public ServicoCarona() {
		sistemaDao = new SistemaDao();

	}

	/**
	 * 
	 * @param carona
	 * @return
	 * @throws Exception
	 *             Verifica se o atributo data é válido, caso sim retotna uma data
	 *             valida.
	 */
	private void dataValida(Carona carona) throws Exception {
		String data = carona.getData();

		if (data == null || data.isEmpty()) {
			throw new ErroException("Data invalida");
		}
		if (!isData(data)) {
			throw new ErroException("Data invalida");
		}
	}

	/**
	 * @param data
	 * @return Verifica se a data em questão existe. Exemplo quando solicitado
	 *         avalia uma data passada ex:30/02/2017. Este método verificar os
	 *         limites das data de cada mês.
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
	 * @param carona
	 * @return
	 * @throws Exception
	 * 
	 *             Verifica se o atributo hora é válido. Caso sim retorna uma data
	 *             valida.
	 */
	private void horaValida(Carona carona) throws Exception {
		String hora = carona.getHora();
		SimpleDateFormat horaFormatada = new SimpleDateFormat("HH:mm");
		horaFormatada.setLenient(false);

		if (hora == null || hora.equals("")) {
			throw new Exception("Hora invalida");
		} else {
			try {
				horaFormatada.parse(hora);
			} catch (ParseException e) {
				throw new Exception("Hora invalida");
			}
		}
	}

	/**
	 * @param carona
	 * @throws Exception
	 *             Verifica se o atributo vagas é válido. Este atributo representa a
	 *             quantidade de vagas disponivel na carona.
	 * 
	 */
	private void vagasValidas(Carona carona) throws Exception {
		if (carona.getVagas() == null) {
			throw new Exception("Vaga invalida");
		} else {
			try {
				Integer.parseInt(carona.getVagas());
			} catch (Exception e) {
				throw new Exception("Vaga invalida");
			}
		}

	}

	/**
	 * @param carona
	 * @throws Exception
	 *             Verifica se o atributo Origem é válido. Trata-se do local de
	 *             partida da carona.
	 */
	private void validaCaronaOrigem(Carona carona) throws Exception {
		if (carona.getOrigemCarona() == null || carona.getOrigemCarona().equals("")) {
			throw new Exception("Origem invalida");
		}

	}

	/**
	 * @param carona
	 * @throws Exception
	 *             Verifica se o atributo destino é válido. a chagada o termino da
	 *             carona.
	 */
	private void validaCaronaDestino(Carona carona) throws Exception {
		if (carona.getDestinoCarona() == null || carona.getDestinoCarona().equals("")) {
			throw new Exception("Destino invalido");
		}

	}

	/**
	 * @param idSessao
	 *            Verificar se o idSessao é valido.
	 */
	private void validarIdSessao(String idSessao) {
		String sessaoid = sistemaDao.buscarIdsessao(idSessao);
		if (idSessao == null || idSessao.isEmpty()) {
			throw new ErroException("Sessao invalida");
		}
		if (sessaoid.equals("")) {
			throw new ErroException("Sessao inexistente");
		}

	}

	/**
	 * @param idSessao
	 * @param carona
	 * @return
	 * @throws Exception
	 * 
	 *             Método que irá veridica se a carona apresenta dados válidos. Caso
	 *             sim irá salva os dados no banco de dados e retorna um idCarona.
	 */
	public String validaCarona(String idSessao, Carona carona) throws Exception {

		validarIdSessao(idSessao);
		validaCaronaOrigem(carona);
		validaCaronaDestino(carona);
		dataValida(carona);
		horaValida(carona);
		vagasValidas(carona);
		
		int idPerfil = sistemaDao.buscaIdPerfil(idSessao);
		sistemaDao.salvarDadosCarona(carona,idPerfil);

		String idCarona = Integer.toString(carona.getIdCaronas());
		

		return idCarona;
	}

	public String localizarCarona(String idSessao, String origem, String destino) throws Exception {

		if (!origem.matches("^[ a-zA-Z ã á â é ê i í ó õ ô ú]*$")) {
			throw new ErroException("Origem invalida");
		}
		if (!destino.matches("^[ a-zA-Z ã á â é ê i í ó õ ô ú]*$")) {
			throw new ErroException("Destino invalido");
		}

		String texto = "{";
		List<Carona> carona = sistemaDao.buscarCarona();
		if (carona == null || carona.isEmpty()) {
			return texto + "}";
		}
		for (Carona cr : carona) {
			if (cr.getIdSessao().equals(idSessao)) {
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

	public String pesquisaCarona(int idCarona, String atributo) {
		String dado = "";
		validarIdcarona(idCarona);
		dado = sistemaDao.buscarAtributoCarona(idCarona, atributo);
		return dado;

	}

	private void validarIdcarona(int idCarona) {

		if (idCarona == 0 || idCarona == -1) {
			throw new ErroException("Carona Invalido");
		}
		if (!sistemaDao.isIdCarona(idCarona)) {
			throw new ErroException("Carona Inexistente");
		}

	}

	/**
	 * @param idcarona
	 * @return Retorna uma descrição de uma carona cadastrada. informando a
	 *         origem,destino,hora e vaga.
	 */
	public String buscaCaronaCadastrada(int idcarona) {
		String dado = "";
		validarIdcarona(idcarona);
		dado = sistemaDao.descricaoCarona(idcarona);
		return dado;
	}

	/**
	 * @param idcarona
	 * @return
	 */
	public String descreverTrajeto(int idcarona) {
		String dado = "";
		validarTrajeto(idcarona);
		dado = sistemaDao.descricaoTrajero(idcarona);
		return dado;
	}

	/**
	 * @param idcarona
	 */
	private void validarTrajeto(int idcarona) {

		if (idcarona == 0 || idcarona == -1) {
			throw new ErroException("Trajeto Invalido");
		}
		if (!sistemaDao.isIdCarona(idcarona)) {
			throw new ErroException("Trajeto Inexistente");
		}
	}

	/**
	 * @param idSessao
	 * @param indexCarona
	 * @return
	 */
	public int getCaronaUsuario(String idSessao, int indexCarona) {
		return sistemaDao.getCaronaUsuario(idSessao, indexCarona);
	}

	/**
	 * @param idSessao
	 * @return
	 */
	public String getTodasCaronasUsuario(String idSessao) {
		return sistemaDao.getTodasCaronasUsuario(idSessao);
	}

	
	
}
