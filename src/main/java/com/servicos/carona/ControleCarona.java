/**
 * 
 */
package com.servicos.carona;

import com.dominio.Carona;

/**
 * @author Bruno Miranda, Thassio Lucena
 *
 */
public class ControleCarona {

	private Carona carona;
	private ServicoCarona servicosCarona;

	public ControleCarona() {
		servicosCarona = new ServicoCarona();
	}

	/**
	 * @param idSessao
	 * @param origem
	 * @param destino
	 * @param data
	 * @param hora
	 * @param vagas
	 * @return
	 * @throws Exception
	 */
	public String cadastroCarona(String idSessao, String origem, String destino, String data, String hora, String vagas)
			throws Exception {
		carona = new Carona(idSessao, origem, destino, data, hora, vagas);
		return servicosCarona.validaCarona(idSessao, carona);
	}

	/**
	 * @param idSessao
	 * @param origem
	 * @param destino
	 * @return
	 * @throws Exception
	 */
	public String pesquisaCarona(String idSessao, String origem, String destino) throws Exception {
		return servicosCarona.localizarCarona(idSessao, origem, destino);
	}

	/**
	 * @param idcarona
	 * @param atributo
	 * @return
	 */
	public String localizarCarona(int idCarona, String atributo) {
		return servicosCarona.pesquisaCarona(idCarona, atributo);
	}

	/**
	 * @param idcarona
	 * @return
	 */
	public String getCarona(int idcarona) {
		return servicosCarona.buscaCaronaCadastrada(idcarona);
	}

	/**
	 * @param idcarona
	 * @return
	 */
	public String getTrajeto(int idcarona) {
		return servicosCarona.descreverTrajeto(idcarona);
	}


	/**
	 * @param idSessao
	 * @return
	 */
	public String getTodasCaronasUsuario(String idSessao) {
		return servicosCarona.getTodasCaronasUsuario(idSessao);
	}

	/**
	 * @param idSessao
	 * @param origem
	 * @param destino
	 * @param cidade
	 * @param data
	 * @param hora
	 * @param vagas
	 * @param tipo
	 * @return
	 * @throws Exception
	 */
	public String cadastraCaronaMunicipal(String idSessao, String origem, String destino, String cidade, String data,
			String hora, String vagas) throws Exception {
		carona = new Carona(idSessao, origem, destino, cidade, data, hora, vagas);
		return servicosCarona.validaCaronaMunicipal(idSessao, carona);
	}

	public boolean getAtributoCaronaMunicipal(int idCarona, String atributo) {
		return servicosCarona.getAtributoCaronaMunicipal(idCarona, atributo);
	}

	/**
	 * localizarCaronaMunicipal-> Este vai direcinar os parametro informados para a
	 * o método de mesmo nome na classe <b>servicosCarona</b>
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
	 * @see
	 * ServicoCarona        
	 */
	public String localizarCaronaMunicipal(String idSessao, String cidade, String origem, String destino) {

		return servicosCarona.localizarCaronaMunicipal(idSessao, cidade, origem, destino);
	}

	/**
	 * localizarCaronaMunicipal-> Este vai direcinar os parametro informados para a
	 * o método de mesmo nome na classe <b>servicosCarona</b>
	 * 
	 * @param idSessao
	 *            Identificador de uma sessão ativa de um usuário
	 * @param cidade
	 *            Local onde a carona vai acontecer <b>(parametro obrigatorio)</b> 
	 **/
	public String localizarCaronaMunicipal(String idSessao, String cidade) {
		return servicosCarona.localizarCaronaMunicipal(idSessao, cidade);
	}

	/**
	 * Método chama o método cadastrarInteresse na classe servicosCarona, direcinando os atributos informados.
	 * @param idSessao identificador de uma sessao
	 * @param origem partida de uma carona
	 * @param destino chegada da carona
	 * @param data data de saída
	 * @param horaInicio hora da saída
	 * @param horaFim hora da chegada
	 * @return retorna o identificador da carona interessada.
	 */
	public int cadastrarInteresse(String idSessao, String origem, String destino, String data, String horaInicio,
			String horaFim) {
		
		return servicosCarona.cadastrarInteresse(idSessao, origem, destino, data, horaInicio, horaFim);
	}

}
