package com.dominio.dao;

import com.dominio.CaronaRelampago;

public interface CaronaRelampagoDao {

	/**
	 * Método verificar se existe o idSessao em uma carona relampago cadastrada
	 * @param idSessao identificador de um usuário
	 * @return retorna falso se não encontra o idSessao.
	 */
	boolean is_IdSessao(String idSessao);

	/**
	 * Método salva uma carona relampago valida.
	 * @param caronaRelampago Objeto carona relampago
	 * @param idPerfil identificador do perfil
	 */
	void salvar(CaronaRelampago caronaRelampago, int idPerfil);

	/**
	 * Método captura dados de uma carona.
	 * @param idCarona identificador da carona
	 * @return retorna um objeto do tipo carona relampago
	 */
	CaronaRelampago buscardadosCarona(String idCarona);

}
