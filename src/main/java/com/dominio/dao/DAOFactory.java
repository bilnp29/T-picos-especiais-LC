/**
 * 
 */
package com.dominio.dao;

import com.dominio.dao.Jdbc.JdbcDAOFactory;

/**
 * Classe responsavel por criar a classe JdbcDAOFactory().
 * 
 * @author Bruno Miranda, Thassio Lucena.
 * 
 *
 */
public abstract class DAOFactory {

	public static DAOFactory getDaoFactory() {
		return new JdbcDAOFactory();

	}

	/**
	 * O Método irá recuparar um Usuario.
	 * 
	 * @return retorna uma conexão de um usuario
	 */
	public abstract UsuarioDao getUsuarioDao();

	/**
	 * O Método irá recuparar um Perfil de um Usuario.
	 * 
	 * @return retorna uma conexão de um perfil
	 */
	public abstract PerfilUsuarioDao getPerfilUsuario();

	/**
	 * O Método irá recuparar dados de uma carona.
	 * 
	 * @return retorna uma conexão de uma carona.
	 */
	public abstract CaronaDao getCaronaDao();
}
