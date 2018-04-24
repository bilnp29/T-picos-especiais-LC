/**
 * 
 */
package com.dominio.dao.Jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.dominio.PerfilUsuario;
import com.dominio.dao.DAOException;
import com.dominio.dao.PerfilUsuarioDao;

/**
 * @author Bruno Miranda Thassio Lucena Classe responsavel por implementa os
 *         métodos da interface PerfilUsuarioDao() referente a persistencia de
 *         dados.
 */
public class JdbcPerfilUsuarioDao implements PerfilUsuarioDao {

	private Connection connection;

	public JdbcPerfilUsuarioDao(Connection connection) {
		this.connection = connection;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dominio.dao.PerfilUsuarioDao#salva(com.dominio.PerfilUsuario)
	 */
	@Override
	public void salva(PerfilUsuario perfil) {
		try {
			String sql = String.format(
					"insert into perfil (IdSessao,estadoSessao,usuario_idUsuario, login) values ('%s',%b, %d, '%s')",
					perfil.getIdentificadorSessao(), perfil.isEstadoSessao(), perfil.getIdUsuairo(), perfil.getLogin());
			PreparedStatement ps = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			int idperfil = rs.getInt(1);
			perfil.setIdPerfil(idperfil);

		} catch (SQLException e) {
			throw new DAOException("Erro ao salva dados do Perfl", e);
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
	 * @see com.dominio.dao.PerfilUsuarioDao#buscarid()
	 */
	@Override
	public String buscarid(String idSessao) {
		String id_Sessao = "";
		try {
			String sql = String.format("select idSessao from perfil where idSessao = '%s'", idSessao);
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				id_Sessao = rs.getString(1);
			}
		} catch (SQLException e) {
			throw new DAOException("Erro ao verificar se existe sessao ativa", e);
		}
		return id_Sessao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dominio.dao.PerfilUsuarioDao#encerraSessao()
	 */
	@Override
	public void encerraSessaoTodas() {

		try {
			String sql = "update perfil set estadoSessao = false where perfil.idPerfil <> 0";
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ps.executeUpdate();

		} catch (Exception e) {
			throw new DAOException("Erro ao encerra sessão", e);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dominio.dao.PerfilUsuarioDao#deletarDados()
	 */
	@Override
	public void deletarPerfil() {
		try {
			String sql = "delete from perfil where idPerfil <> 0";
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("Erro ao excluir perfil ", e);
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
	 * @see com.dominio.dao.PerfilUsuarioDao#encerrarSessao(int)
	 */
	@Override
	public void encerrarSessao(int id) {
		try {
			String sql = String.format("update perfil set estadoSessao = false where perfil.idPerfil = %d", id);
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ps.executeUpdate();

		} catch (SQLException e) {
			throw new DAOException("Erro ao encerra sessão", e);
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
	 * @see com.dominio.dao.PerfilUsuarioDao#recuperarIdPerfil(java.lang.String)
	 */
	@Override
	public int recuperarIdPerfil(String login) {
		int id = 0;
		try {
			String sql = String.format("select idPerfil from perfil where login = '%s'", login);
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				id = rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new DAOException("Erro ao recuperar usuario ", e);
		}
		return id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dominio.dao.PerfilUsuarioDao#buscaNomeSolicitante(java.lang.String)
	 */
	@Override
	public String buscaNomeSolicitante(String idSessao) {
		String nome = "";

		try {
			String sql = String.format("select usuario.nome from usuario where login = "
					+ "(select perfil.login from perfil where perfil.idSessao = '%s')", idSessao);
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				nome = rs.getString(1);
			}

		} catch (SQLException e) {
			throw new DAOException("Erro ao buscar nome do solicitante ", e);
		} finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return nome;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dominio.dao.PerfilUsuarioDao#buscaridPerfil(java.lang.String)
	 */
	@Override
	public int buscaridPerfil(String idSessao) {
		int id = 0;
		try {
			String sql = String.format("select idPerfil from perfil where idSessao = '%s'", idSessao);
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				id = rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new DAOException("Erro ao recuperar identificador perfil ", e);
		} finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dominio.dao.PerfilUsuarioDao#buscaDadosPerfil(java.lang.String)
	 */
	@Override
	public PerfilUsuario buscaDadosPerfil(String login) {
		PerfilUsuario perfil = null;
		try {
			String sql = String
					.format("select perfil.idSessao, perfil.estadoSessao from perfil where perfil.login = '%s'", login);
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				perfil = new PerfilUsuario();
				perfil.setIdentificadorSessao(rs.getString(1));
				perfil.setEstadoSessao(rs.getBoolean(2));
			}
		} catch (SQLException e) {
			throw new DAOException("Erro ao recuperar idSessao e estado do perfil ", e);
		} finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return perfil;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dominio.dao.PerfilUsuarioDao#atualizarSessao(int)
	 */
	@Override
	public void atualizarSessao(int id_perfil) {
		try {
			String sql = String.format("update perfil set estadoSessao = true where perfil.idPerfil = %d", id_perfil);
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ps.executeUpdate();

		} catch (SQLException e) {
			throw new DAOException("Erro a atualizar estado da sessão", e);
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
	public int visualizarPeril(String idSessao, String login) {
		int id = 0;
		try {
			String sql = String.format("select idPerfil from perfil where idSessao = '%s' and login = '%s'", idSessao,
					login);
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				id = rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new DAOException("Erro ao recuperar identificador perfil ", e);
		} finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return id;
	}

	@Override
	public String buscarLogin(String idSessao) {
		String login = "";
		try {
			String sql = String.format("select login from perfil where idSessao = '%s'", idSessao);
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				login = rs.getString(1);
			}
		} catch (SQLException e) {
			throw new DAOException("Erro ao recuperar login usuario ", e);
		} finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return login;
	}

	@Override
	public String historico_Caronas(String login) {
		String id = "";
		try {
			String sql = String.format("select idCaronas from caronas where perfil_idPerfil =\r\n"
					+ "(select idPerfil from perfil where login = '%s')", login);

			PreparedStatement ps = this.connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				id += rs.getString(1);
			}
		} catch (SQLException e) {
			throw new DAOException("Erro ao recuperar id carona ", e);
		}
		
		return id;
	}

	@Override
	public void reiniciarSistema() {
		try {
			String sql = "update perfil set estadoSessao = true where perfil.idPerfil <> 0";
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ps.executeUpdate();

		} catch (SQLException e) {
			throw new DAOException("Erro ao reiniciar sessão", e);
		}

	}

	@Override
	public int buscar_faltas_caronas(String login) {
		int faltas = 0;
		try {
			String sql = String.format("select count(*) from review where login = '%s' and presenca = 'faltou' ",
					login);
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				faltas = rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new DAOException("Erro na contagem de faltas ", e);
		}
		return faltas;
	}

	@Override
	public int buscar_presenca_caronas(String login) {
		int presenca = 0;
		try {
			String sql = String.format("select count(*) from review where login = '%s' and presenca = 'nao faltou' ",
					login);
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				presenca = rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new DAOException("Erro na contagem de presenca ", e);
		}
		return presenca;
	}

	@Override
	public String buscar_historico_vagas_caronas(String login) {
		String id = "[";
		try {
			String sql = String.format("(select idCarona from solicitacao_vaga_sem_sugestao where idSessao =\r\n" + 
					"(select idSessao from perfil where login = '%s'))", login);

			PreparedStatement ps = this.connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				id += rs.getString(1) + ",";
			}
		} catch (SQLException e) {
			throw new DAOException("Erro ao recuperar id carona ", e);
		}
		if (!id.equals("[")) {
			id = id.substring(0, id.length() - 1);
		}
		id+= "]";
		return id;
	}
}
