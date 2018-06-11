package com.dominio.dao.Jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

import com.dominio.dao.CaronaDao;
import com.dominio.dao.CaronaRelampagoDao;
import com.dominio.dao.DAOFactory;
import com.dominio.dao.PerfilUsuarioDao;
import com.dominio.dao.UsuarioDao;

/**
 * @author Bruno Miranda, Thassio Lucena. Classe responsavel por recuperar a
 *         conexão com o banco e recuperar JdbcUsuarioDao.
 *
 */
public class JdbcDAOFactory extends DAOFactory {

	private Connection connection;

	public JdbcDAOFactory() {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			this.connection = DriverManager.getConnection("jdbc:mysql://localhost/shared_carpool", "root", "");
		} catch (Exception e) {
			throw new RuntimeException("Erro recuperando conexão com o banco", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dominio.dao.DAOFactory#getUsuarioDao()
	 */
	@Override
	public UsuarioDao getUsuarioDao() {

		return new JdbcUsuarioDao(connection);
	}

	/* (non-Javadoc)
	 * @see com.dominio.dao.DAOFactory#getPerfilUsuario()
	 */
	@Override
	public PerfilUsuarioDao getPerfilUsuario() {

		return new JdbcPerfilUsuarioDao(connection);
	}

	/* (non-Javadoc)
	 * @see com.dominio.dao.DAOFactory#getCaronaDao()
	 */
	@Override
	public CaronaDao getCaronaDao() {

		return new JdbcCaronaDao(connection);
	}

	@Override
	public CaronaRelampagoDao getCaronaRelampagoDao() {
		
		return new JdbcCaronaRelampago(connection);
	}
}
