/**
 * 
 */
package com.dominio.dao.Jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.dominio.Carona;
import com.dominio.Usuario;
import com.dominio.dao.UsuarioDao;
import com.mysql.jdbc.Statement;

/**
 * Classe reponsalve por implementar os métodos da interface UsuarioDao.
 * 
 * @author Bruno Miranda, Thassio Lucena
 *
 */
public class JdbcUsuarioDao implements UsuarioDao {

	final static Logger logger = Logger.getLogger(JdbcUsuarioDao.class);

	private Connection connection;

	private static String MSG_INCIAL = "Iniciando o método";

	public JdbcUsuarioDao(Connection connection) {
		this.connection = connection;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dominio.dao.UsuarioDao#salvar(com.dominio.Usuario)
	 */
	@Override
	public void salvar(Usuario usuario) {
		logger.info(MSG_INCIAL);
		try {
			String sql = String.format(
					"insert into usuario (nome,login,email,endereco,telefone,senha) "
							+ "values ('%s','%s','%s','%s','%s','%s')",
					usuario.getNome(), usuario.getLogin(), usuario.getEmail(), usuario.getEndereco(),
					usuario.getTelefone(), usuario.getSenha());
			PreparedStatement ps = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.executeUpdate();

			ResultSet rs = ps.getGeneratedKeys();
			while (rs.next()) {
				int idUsuario = rs.getInt(1);
				usuario.setIdUsuario(idUsuario);
			}
		} catch (SQLException e) {
			logger.info("Erro ao salvar cliente", e);
		} finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				logger.error("Erro ao encerra conexão", e);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dominio.dao.UsuarioDao#buscarAtributo(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public Usuario buscarAtributo(String login, String atributo) {
		logger.info(MSG_INCIAL);
		Usuario usuario = null;
		try {
			String sql = String.format("select * from usuario where login = %s", "'" + login + "'");
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				usuario = new Usuario();
				usuario.setLogin(rs.getString("login"));
				usuario.setNome(rs.getString("nome"));
				usuario.setEndereco(rs.getString("endereco"));
				usuario.setEmail(rs.getString("email"));
			}

		} catch (SQLException e) {
			logger.info("Erro ao buscar usuario", e);
		} finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				logger.error("Erro ao encerra conexão", e);
			}
		}

		return usuario;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dominio.dao.UsuarioDao#buscarTodosUsuarios()
	 */
	@Override
	public Map<String, Usuario> buscarTodosUsuarios() {
		logger.info(MSG_INCIAL);
		Map<String, Usuario> dados = new HashMap<>();
		try {
			String sql = "select * from usuario";
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Usuario usuario = new Usuario();
				String login = rs.getString("login");

				usuario.setLogin(rs.getString("login"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setEmail(rs.getString("email"));
				usuario.setIdUsuario(rs.getInt("idUsuario"));

				dados.put(login, usuario);
			}

		} catch (SQLException e) {
			logger.info("Erro ao buscar usuario", e);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				logger.error("Erro ao encerra conexão", e);
			}
		}

		return dados;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dominio.dao.UsuarioDao#deletarRegistro()
	 */
	@Override
	public void deletarRegistro() {
		logger.info(MSG_INCIAL);
		try {
			String sql = "delete from usuario where idUsuario <> 0";
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ps.executeUpdate();
		} catch (SQLException e) {
			logger.info("Erro ao excluir usuario", e);
		} finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				logger.error("Erro ao encerra conexão", e);
			}

		}

	}

	@Override
	public String buscarEmailUsuario(Carona carona) {
		logger.info(MSG_INCIAL);
		String email = "";
		try {
			String sql = String.format("(select email from usuario where idUsuario =\r\n" + 
					"(select usuario_idUsuario from perfil where idPerfil =\r\n" + 
					"(select perfil_idPerfil from caronas where idCaronas = %d)))", carona.getIdCaronas());
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				email = rs.getString(1);
			}
		} catch (SQLException e) {
			logger.info("Erro ao captura o email", e);
		} finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				logger.error("Erro ao encerra conexão", e);
			}

		}
		return email;
	}

}
