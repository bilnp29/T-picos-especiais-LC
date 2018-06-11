package com.dominio.dao.Jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.dominio.CaronaRelampago;
import com.dominio.dao.CaronaRelampagoDao;
import com.mysql.jdbc.Statement;

/**
 * Classe responsavel por implementa os métodos da interface
 * <b>CaronoRelampagoDao().</b>
 * 
 * @author Bruno Miranda / Thassio Lucena
 *
 */
public class JdbcCaronaRelampago implements CaronaRelampagoDao {

	final static Logger logger = Logger.getLogger(JdbcCaronaRelampago.class);
	private Connection connection;

	/**
	 * Método construtor inicializando o connection.
	 * 
	 * @param connection
	 *            Atributo da classe
	 */
	public JdbcCaronaRelampago(Connection connection) {
		this.connection = connection;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dominio.dao.CaronaRelampagoDao#is_IdSessao(java.lang.String)
	 */
	@Override
	public boolean is_IdSessao(String idSessao) {
		logger.info("Iniciando método");
		String sessaoExistente = "";
		try {
			String sql = String.format("SELECT idSessao FROM perfil where idSessao = '%s'", idSessao);
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				sessaoExistente = rs.getString(1);
			}

		} catch (SQLException e) {
			logger.info("Erro ao encontra usuario na tabela caronarelampago", e);
		} finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				logger.error("Erro ao encerra conexão", e);
			}
		}

		if (!idSessao.equals(sessaoExistente)) {
			return false;
		}
		logger.info("Fim do método");
		return true;
	}

	@Override
	public void salvar(CaronaRelampago caronaRelampago, int idPerfil) {
		logger.info("Iniciando método");
		try {
			String sql = String.format("insert into caronas"
					+ "(idSessao,localOrigem,localDestino,dataIda,dataVolta,hora,minimoCaroneiros,perfil_idPerfil)"
					+ "values('%s','%s','%s','%s','%s','%s','%s',%d)", caronaRelampago.getIdSessao(),
					caronaRelampago.getOrigem(), caronaRelampago.getDestino(), caronaRelampago.getDataIda(),
					caronaRelampago.getDataVolta(), caronaRelampago.getHora(), caronaRelampago.getMinimoCaroneiro(),
					idPerfil);
			PreparedStatement ps = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.executeUpdate();

			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			int idCarona = rs.getInt(1);
			caronaRelampago.setIdcaronaRelampago(idCarona);
		} catch (SQLException e) {
			logger.info("Erro ao salva dados do Carona Relampago", e);
		} finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				logger.warn("Erro ao fecha conection");
			}
		}
		logger.info("Fim do método salvar");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dominio.dao.CaronaRelampagoDao#buscardadosCarona(java.lang.String)
	 */
	@Override
	public CaronaRelampago buscardadosCarona(String idCarona) {
		logger.info("Iniciando método");
		CaronaRelampago caronaRelampago = null;
		try {
			String sql = String.format("select * from caronas where idCaronas ='%s'", idCarona);
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				caronaRelampago = new CaronaRelampago();
				caronaRelampago.setOrigem(rs.getString("localOrigem"));
				caronaRelampago.setDestino(rs.getString("localDestino"));
				caronaRelampago.setDataIda(rs.getString("dataIda"));
				caronaRelampago.setMinimoCaroneiro(rs.getString("minimoCaroneiros"));
				caronaRelampago.setHora(rs.getString("hora"));
			}
		} catch (SQLException e) {
			logger.info("Erro ao busca dado da carona cadastrada" + e);
		} finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				logger.info("Erro ao fecha a connection" + e);
			}
		}
		logger.info("Fim do método");
		return caronaRelampago;
	}

}
