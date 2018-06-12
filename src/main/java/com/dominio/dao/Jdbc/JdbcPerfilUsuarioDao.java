/**
 * 
 */
package com.dominio.dao.Jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.dominio.InteresseCarona;
import com.dominio.PerfilUsuario;
import com.dominio.dao.PerfilUsuarioDao;

/**
 * Classe responsavel por implementa os métodos da interface PerfilUsuarioDao()
 * referente a persistencia de dados.
 * 
 * @author Bruno Miranda Thassio Lucena
 */
public class JdbcPerfilUsuarioDao implements PerfilUsuarioDao {

	/**
	 * Inicializando logs da classe <b> SistemaDao </b>
	 */
	final static Logger logger = Logger.getLogger(JdbcPerfilUsuarioDao.class);

	private Connection connection;
	private static String MSG_INCIAL = "Iniciando o método";

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
		logger.info(MSG_INCIAL);
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
			logger.info("Erro ao salvar dados do Perfil", e);
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
	 * @see com.dominio.dao.PerfilUsuarioDao#buscarid()
	 */
	@Override
	public String buscarid(String idSessao) {
		logger.info(MSG_INCIAL);
		String id_Sessao = "";
		try {
			String sql = String.format("select idSessao from perfil where idSessao = '%s'", idSessao);
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				id_Sessao = rs.getString(1);
			}
		} catch (SQLException e) {
			logger.error("Erro ao verificar se existe sessao ativa", e);
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
		logger.info(MSG_INCIAL);
		try {
			String sql = "update perfil set estadoSessao = false where perfil.idPerfil <> 0";
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ps.executeUpdate();

		} catch (Exception e) {
			logger.error("Erro ao encerrar sessão", e);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dominio.dao.PerfilUsuarioDao#deletarDados()
	 */
	@Override
	public void deletarPerfil() {
		logger.info(MSG_INCIAL);
		try {
			String sql = "delete from perfil where idPerfil <> 0";
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ps.executeUpdate();
		} catch (SQLException e) {
			logger.error("Erro ao excluir perfil", e);
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
	 * @see com.dominio.dao.PerfilUsuarioDao#encerrarSessao(int)
	 */
	@Override
	public void encerrarSessao(int id) {
		logger.info(MSG_INCIAL);
		try {
			String sql = String.format("update perfil set estadoSessao = false where perfil.idPerfil = %d", id);
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ps.executeUpdate();

		} catch (SQLException e) {
			logger.error("Erro ao encerrar sessão", e);
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
	 * @see com.dominio.dao.PerfilUsuarioDao#recuperarIdPerfil(java.lang.String)
	 */
	@Override
	public int recuperarIdPerfil(String login) {
		logger.info(MSG_INCIAL);
		int id = 0;
		try {
			String sql = String.format("select idPerfil from perfil where login = '%s'", login);
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				id = rs.getInt(1);
			}
		} catch (SQLException e) {
			logger.info("Erro ao recuperar usuario ", e);
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
		logger.info(MSG_INCIAL);
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
			logger.info("Erro ao buscar nome do solicitante ", e);
		} finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				logger.error("Erro ao encerra conexão", e);
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
		logger.info(MSG_INCIAL);
		int id = 0;
		try {
			String sql = String.format("select idPerfil from perfil where idSessao = '%s'", idSessao);
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				id = rs.getInt(1);
			}
		} catch (SQLException e) {
			logger.warn("Erro ao recuperar identificador perfil ", e);
		} finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				logger.error("Erro ao encerra conexão", e);
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
		logger.info(MSG_INCIAL);
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
			logger.warn("Erro ao recuperar idSessao e estado do perfil ", e);
		} finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				logger.error("Erro ao encerra conexão", e);
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
		logger.info(MSG_INCIAL);
		try {
			String sql = String.format("update perfil set estadoSessao = true where perfil.idPerfil = %d", id_perfil);
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ps.executeUpdate();

		} catch (SQLException e) {
			logger.error("Erro a atualizar estado da sessão", e);
		} finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				System.err.println(e.getLocalizedMessage());
				logger.error("Erro ao encerra conexão", e);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dominio.dao.PerfilUsuarioDao#visualizarPeril(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public int visualizarPeril(String idSessao, String login) {
		logger.info(MSG_INCIAL);
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
			logger.error("Erro ao recuperar identificador perfil ", e);
		} finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				logger.error("Erro ao encerra conexão", e);
			}
		}
		return id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dominio.dao.PerfilUsuarioDao#buscarLogin(java.lang.String)
	 */
	@Override
	public String buscarLogin(String idSessao) {
		logger.info(MSG_INCIAL);
		String login = "";
		try {
			String sql = String.format("select login from perfil where idSessao = '%s'", idSessao);
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				login = rs.getString(1);
			}
		} catch (SQLException e) {
			logger.info("Erro ao recuperar login usuario ", e);
		} finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				logger.error("Erro ao encerra conexão", e);
			}
		}
		return login;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dominio.dao.PerfilUsuarioDao#historico_Caronas(java.lang.String)
	 */
	@Override
	public String historico_Caronas(String login) {
		logger.info(MSG_INCIAL);
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
			logger.info("Erro ao recuperar id carona ", e);
		}

		return id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dominio.dao.PerfilUsuarioDao#reiniciarSistema()
	 */
	@Override
	public void reiniciarSistema() {
		logger.info(MSG_INCIAL);
		try {
			String sql = "update perfil set estadoSessao = true where perfil.idPerfil <> 0";
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ps.executeUpdate();

		} catch (SQLException e) {
			logger.info("Erro ao reiniciar sessão ", e);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dominio.dao.PerfilUsuarioDao#buscar_faltas_caronas(java.lang.String)
	 */
	@Override
	public int buscar_faltas_caronas(String login) {
		logger.info(MSG_INCIAL);
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
			logger.info("Erro ao informa o número de faltas ", e);
		}
		return faltas;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dominio.dao.PerfilUsuarioDao#buscar_presenca_caronas(java.lang.String)
	 */
	@Override
	public int buscar_presenca_caronas(String login) {
		logger.info(MSG_INCIAL);
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
			logger.info("Erro ao informa o número de presença em caronas ", e);
		}
		return presenca;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dominio.dao.PerfilUsuarioDao#buscar_historico_vagas_caronas(java.lang.
	 * String)
	 */
	@Override
	public String buscar_historico_vagas_caronas(String login) {
		logger.info(MSG_INCIAL);
		String id = "[";
		try {
			String sql = String.format("(select idCarona from solicitacao_vaga_sem_sugestao where idSessao =\r\n"
					+ "(select idSessao from perfil where login = '%s'))", login);

			PreparedStatement ps = this.connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				id += rs.getString(1) + ",";
			}
		} catch (SQLException e) {
			logger.info("Erro ao informa o historico de vagas ", e);
		}
		if (!id.equals("[")) {
			id = id.substring(0, id.length() - 1);
		}
		id += "]";
		return id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dominio.dao.PerfilUsuarioDao#existeIdSessao(java.lang.String)
	 */
	@Override
	public boolean existeIdSessao(String idSessao) {
		logger.info(MSG_INCIAL);
		boolean sessaoExistente = false;
		try {
			String sql = String.format(
					"SELECT NOT EXISTS (SELECT idSessao FROM solicitacao_vaga_sem_sugestao where idSessao = '%s')",
					idSessao);
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				sessaoExistente = rs.getBoolean(1);
			}

		} catch (SQLException e) {
			logger.info("Erro ao encontra usuario na tabela solicitacao_vaga_sem_sugestao", e);
		} finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				logger.error("Erro ao encerra conexão", e);
			}
		}

		return sessaoExistente;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dominio.dao.PerfilUsuarioDao#review_Motorista(java.lang.String, int,
	 * java.lang.String)
	 */
	@Override
	public void review_Motorista(String idSessao, int carona, String review) {
		logger.info(MSG_INCIAL);
		try {
			String sql = String.format(
					"insert into review_Motorista (idSessao, idcarona, informacao) values ('%s', %d, '%s')", idSessao,
					carona, review);
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ps.executeUpdate();

		} catch (SQLException e) {
			logger.info("Erro a salva informações na base de dados review_Motorista", e);
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
	 * @see
	 * com.dominio.dao.PerfilUsuarioDao#buscar_historico_caronasSeguras(java.lang.
	 * String)
	 */
	@Override
	public String buscar_historico_caronasSeguras() {
		logger.info(MSG_INCIAL);
		String informacao = "";
		try {
			String sql = "select count(*) from review_Motorista where informacao = 'segura e tranquila'";
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				informacao = rs.getString(1);
			}
		} catch (SQLException e) {
			logger.info("Erro na contagem das caronas seguras e tranquilas ", e);
		}
		return informacao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dominio.dao.PerfilUsuarioDao#buscar_historico_caronas_nao_funcionaram(
	 * java.lang.String)
	 */
	@Override
	public String buscar_historico_caronas_nao_funcionaram() {
		logger.info(MSG_INCIAL);
		String informacao = "";
		try {
			String sql = "select count(*) from review_Motorista where informacao = 'não funcionou'";
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				informacao = rs.getString(1);
			}
		} catch (SQLException e) {
			logger.info("Erro na contagem das caronas que não funcionaram ", e);
		}
		return informacao;
	}

	@Override
	public void deletarReviewMotorista() {
		logger.info(MSG_INCIAL);
		try {
			String sql = "delete from review_motorista where id_review_Motorista <> 0";
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ps.executeUpdate();
		} catch (SQLException e) {
			logger.error("Erro ao excluir review_motorista  ", e);
		}

	}

	@Override
	public InteresseCarona buscarIntersseCarona(String idSessao) {
		logger.info(MSG_INCIAL);
		InteresseCarona informacao = null;
		try {
			String sql = String.format("select * from interessecaronas where idSessao = '%s'", idSessao);
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				informacao = new InteresseCarona();

				informacao.setOrigem(rs.getString("origem"));
				informacao.setDestino(rs.getString("destino"));
				informacao.setData(rs.getString("data"));
			}

		} catch (SQLException e) {
			logger.info("Erro ao recuperar dados de Interesse em caronas", e);
		} finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				logger.error("Erro ao encerra conexão", e);
			}
		}
		return informacao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dominio.dao.PerfilUsuarioDao#nomeMotorista(int)
	 */
	@Override
	public String nomeMotorista(int carona) {
		String nome = "";
		logger.info(MSG_INCIAL);
		try {
			String sql = String.format("(select nome from usuario where idUsuario =\r\n"
					+ "(select usuario_idUsuario from perfil where idPerfil =\r\n"
					+ "(select perfil_idPerfil from caronas where idCaronas = %d)));", carona);
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				nome = rs.getString("nome");
			}
		} catch (SQLException e) {
			logger.info("Erro ao buscacr o nome");
		}
		return nome;
	}

	@Override
	public void cadastraUsuarioPreferencial(String idSessao, int carona, String nome, String nomeMotorista) {
		logger.info(MSG_INCIAL);
		try {
			String sql = String.format("insert into usuarioPreferencial (caroneiro,idcarona,donoCarona, idSessao) "
					+ "values ('%s','%s', '%s', '%s')", nome, carona, nomeMotorista, idSessao);
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ps.executeUpdate();

		} catch (SQLException e) {
			logger.info("Erro ao cadastra Usuario Preferencial", e);
		} finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				logger.error("Erro ao encerra conexão", e);
			}
		}
	}
}
