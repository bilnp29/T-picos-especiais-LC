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

import com.dominio.Usuario;
import com.dominio.dao.DAOException;
import com.dominio.dao.UsuarioDao;
import com.mysql.jdbc.Statement;

/**
 * @author Bruno Miranda, Thassio Lucena Classe reponsalve por implementar os
 *         métodos da interface UsuarioDao.
 *
 */
public class JdbcUsuarioDao implements UsuarioDao {

	private Connection connection;

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
			throw new DAOException("Erro ao salva cliente", e);
		} finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				System.err.println(e.getLocalizedMessage());
				e.printStackTrace();
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
			throw new DAOException("Erro ao busca usuario ", e);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				System.err.println(e.getLocalizedMessage());
				e.printStackTrace();
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
			throw new DAOException("Erro ao busca usuario ", e);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				System.err.println(e.getLocalizedMessage());
				e.printStackTrace();
			}
		}

		return dados;
	}

	/* (non-Javadoc)
	 * @see com.dominio.dao.UsuarioDao#deletarRegistro()
	 */
	@Override
	public void deletarRegistro() {
		try {
			String sql = "delete from usuario where idUsuario <> 0";
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("Erro ao excluir usuario ", e);
		} finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				System.err.println(e.getLocalizedMessage());
				e.printStackTrace();
			}

		}

	}

	@Override
	public String retornaDados(String login) {
		// TODO Auto-generated method stub
		return null;
	}

}
