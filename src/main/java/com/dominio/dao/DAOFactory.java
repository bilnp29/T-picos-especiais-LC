/**
 * 
 */
package com.dominio.dao;

import com.dominio.dao.Jdbc.JdbcDAOFactory;

/**
 * @author Bruno Miranda, Thassio Lucena.
 *	Classe responsavel por criar a classe JdbcDAOFactory().
 *
 */
public abstract class DAOFactory {

	public static DAOFactory getDaoFactory() {
		return new JdbcDAOFactory();
		
	}
	
	/**
	 * O Método irá recuparar um Usuario.
	 * @return
	 */
	public abstract UsuarioDao getUsuarioDao();
	
	/**
	 * O Método irá recuparar um Perfil de um Usuario.
	 * @return
	 */
	public abstract PerfilUsuarioDao getPerfilUsuario();
	
	/**
	 *  O Método irá recuparar dados de uma carona.
	 * @return
	 */
	public abstract CaronaDao getCaronaDao();
}
