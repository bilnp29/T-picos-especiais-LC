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
	 * @param indexCarona
	 * @return
	 */
	public int getCaronaUsuario(String idSessao, int indexCarona) {
		return servicosCarona.getCaronaUsuario(idSessao, indexCarona);
	}

	/**
	 * @param idSessao
	 * @return
	 */
	public String getTodasCaronasUsuario(String idSessao) {
		return servicosCarona.getTodasCaronasUsuario(idSessao);
	}

	

}
