package com.dominio.dao.Jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.dominio.Carona;
import com.dominio.InteresseCarona;
import com.dominio.dao.CaronaDao;
import com.dominio.dao.DAOException;
import com.mysql.jdbc.Statement;

/**
 * Classe responsavel por implementa os métodos da interface <b>CaronoDao().</b>
 * 
 * @author Bruno Miranda Thassio Lucena.
 *
 * @see CaronaDao
 */
public class JdbcCaronaDao implements CaronaDao {

	/**
	 * Inicializando logs da classe <b> JdbcCaronaDao </b>
	 */
	final static Logger logger = Logger.getLogger(JdbcCaronaDao.class);

	private Connection connection;

	/**
	 * Método construtor inicializando o connection.
	 * 
	 * @param connection
	 *            Atributo da classe
	 */
	public JdbcCaronaDao(Connection connection) {
		this.connection = connection;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dominio.dao.CaronaDao#salva(com.dominio.Carona)
	 */
	@Override
	public void salva(Carona carona, int id) {
		try {
			logger.info("Inicializando o método salva(Carona carona, int id)");
			String sql = String.format(
					"insert into caronas(localOrigem, localDestino, data, cidade, hora, vagas, idSessao, tipoCarona, perfil_idPerfil)"
							+ "values('%s','%s','%s','%s','%s','%s','%s','%s',%d)",
					carona.getOrigemCarona(), carona.getDestinoCarona(), carona.getData(), carona.getCidade(),
					carona.getHora(), carona.getVagas(), carona.getIdSessao(), carona.getTipoCarona(), id);
			PreparedStatement ps = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.executeUpdate();

			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			int idCarona = rs.getInt(1);
			carona.setIdCaronas(idCarona);

		} catch (SQLException e) {
			logger.info("Erro ao salva dados do Carona", e);
		} finally {
			try {

				this.connection.close();
			} catch (SQLException e) {
				System.err.println(e.getLocalizedMessage());
				e.printStackTrace();
			}
		}
		logger.info("Fim da execução do método salva(Carona carona, int id)");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dominio.dao.CaronaDao#buscartodas()
	 */
	@Override
	public List<Carona> buscartodas() {
		logger.info("Inicializando o método: List<Carona> buscartodas()");
		List<Carona> caronas = new ArrayList<>();
		try {
			String sql = "select * from caronas where vagas != 0";
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Carona carona = new Carona();
				carona.setIdSessao(rs.getString("idSessao"));
				carona.setIdCaronas(rs.getInt("idCaronas"));
				carona.setOrigemCarona(rs.getString("localOrigem"));
				carona.setDestinoCarona(rs.getString("localDestino"));

				caronas.add(carona);
			}

		} catch (SQLException e) {
			logger.info("Erro ao busca caronas cadastradas", e);
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
		logger.info("Fim do método");
		return caronas;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dominio.dao.CaronaDao#buscarCarona(int)
	 */
	@Override
	public Carona buscarCarona(int idcarona) {
		logger.info("Inicializando o método -> Carona buscarCarona(int idcarona) ");
		Carona carona = null;
		try {
			String sql = String.format("select * from caronas where idCaronas = %d ", idcarona);
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				carona = new Carona();
				carona.setIdCaronas(rs.getInt("idCaronas"));
				carona.setOrigemCarona(rs.getString("localOrigem"));
				carona.setDestinoCarona(rs.getString("localDestino"));
				carona.setVagas(rs.getString("vagas"));
				carona.setData(rs.getString("data"));
				carona.setHora(rs.getString("hora"));

			}
		} catch (SQLException e) {
			logger.info("Erro ao busca dado da carona cadastrada, e");
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
		logger.info("Fim do método");
		return carona;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dominio.dao.CaronaDao#isCaronaId(int)
	 */
	@Override
	public boolean isCaronaId(int idcarona) {
		logger.info("Inicializando o método: isCaronaId(int idcarona)");
		int id = 0;
		try {
			String sql = String.format("select * from caronas where idCaronas = %d ", idcarona);
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				id = rs.getInt("idCaronas");
			}
			if (id != idcarona) {
				return false;
			}

		} catch (SQLException e) {
			logger.info("Erro ao perquisa carona cadastradas", e);

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
		logger.info("Fim do método");
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dominio.dao.CaronaDao#salvaPontoEncontro(java.lang.String, int,
	 * java.lang.String, java.lang.String, int)
	 */
	@Override
	public int salvaPontoEncontro(String idSessao, int idCarona, String pontos, String solicitante, int id_perfil) {
		logger.info(
				"Inicializando o método: salvaPontoEncontro(String idSessao, int idCarona, String pontos, String solicitante, int id_perfil)");
		int idSolicitacao = 0;
		try {
			String sql = String
					.format("insert into solicitacoes (idSessao, idCarona, pontos, solicitante, perfil_idPerfil)"
							+ "values('%s',%d,'%s','%s',%d)", idSessao, idCarona, pontos, solicitante, id_perfil);

			PreparedStatement ps = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.executeUpdate();

			ResultSet rs = ps.getGeneratedKeys();
			while (rs.next()) {

				idSolicitacao = rs.getInt(1);
			}
		} catch (SQLException e) {
			logger.info("Erro ao salva solicitação ponto de encontro", e);
		} finally {
			try {

				this.connection.close();
			} catch (SQLException e) {
				System.err.println(e.getLocalizedMessage());
				e.printStackTrace();
			}
		}
		logger.info("Fim do método");
		return idSolicitacao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dominio.dao.CaronaDao#deletarRegistroSoliciatacao()
	 */
	@Override
	public void deletarRegistroSoliciatacao() {
		logger.info("Inicializando o método: deletarRegistroSoliciatacao()");
		try {
			String sql = "delete from solicitacoes where idSugestao <> 0";
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ps.executeUpdate();
		} catch (SQLException e) {
			logger.info("Erro ao excluir as solicitacões de carona", e);
			throw new DAOException("Erro ao excluir as solicitacões de carona ", e);
		}
		logger.info("Fim do método");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dominio.dao.CaronaDao#deletarDados()
	 */
	@Override
	public void deletarCaronas() {
		logger.info("Inicializando o método: deletarCaronas()");
		try {
			String sql = "delete from caronas where idCaronas <> 0";
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ps.executeUpdate();
		} catch (SQLException e) {
			logger.info("Erro ao excluir carona cadastradas", e);

		} finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				System.err.println(e.getLocalizedMessage());
				e.printStackTrace();
			}

		}
		logger.info("Fim do método");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dominio.dao.CaronaDao#salvaRespostaPontoEncontro(java.lang.String,
	 * int, int, java.lang.String, int)
	 */
	@Override
	public void salvaRespostaPontoEncontro(String idSessao, int idCarona, int idSolicitacoes, String pontos,
			int idPerfil) {
		logger.info(
				"Inicializando o método: salvaRespostaPontoEncontro(String idSessao, int idCarona, int idSolicitacoes, String pontos,\r\n"
						+ "			int idPerfil)");
		try {
			String sql = String.format(
					"insert into respostaSugestaoCarona (idSessao, idCarona, idSugestao, pontos,perfil_idPerfil)"
							+ " values('%s', %d,%d,'%s',%d)",
					idSessao, idCarona, idSolicitacoes, pontos, idPerfil);
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ps.executeUpdate();

		} catch (SQLException e) {
			logger.info("Erro ao salva resposta para as sugenstões de caronas", e);
		} finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				System.err.println(e.getLocalizedMessage());
				e.printStackTrace();
			}

		}
		logger.info("Fim do método");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dominio.dao.CaronaDao#deletarRespostaSolicitacao()
	 */
	@Override
	public void deletarRespostaSolicitacao() {
		logger.info("Inicializando o método: deletarRespostaSolicitacao()");
		try {
			String sql = "delete from respostasugestaocarona where idrespostaSugestaoCarona <> 0";
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ps.executeUpdate();
		} catch (SQLException e) {
			logger.info("Erro ao excluir Resposta da solicitação ponto de encontro ", e);

		}
		logger.info("Fim do método");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dominio.dao.CaronaDao#salvaSolicitacaoVagaCarona(java.lang.String,
	 * int, java.lang.String, java.lang.String)
	 */
	@Override
	public int salvaSolicitacaoVagaCarona(String idSessao, int idCarona, String ponto, String solicitante) {
		logger.info(
				"Inicializando o método: salvaSolicitacaoVagaCarona(String idSessao, int idCarona, String ponto, String solicitante)");
		int idSolicitacaoVaga = 0;
		try {
			String sql = String.format("insert into solicitacaoVagas (idSessao, idCarona, ponto, solicitante)"
					+ " values ('%s',%d,'%s','%s')", idSessao, idCarona, ponto, solicitante);
			PreparedStatement ps = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.executeUpdate();

			ResultSet rs = ps.getGeneratedKeys();
			while (rs.next()) {

				idSolicitacaoVaga = rs.getInt(1);

			}
		} catch (SQLException e) {
			logger.info("Erro ao salva solicitação da vaga em uma carona", e);
		} finally {
			try {

				this.connection.close();
			} catch (SQLException e) {
				System.err.println(e.getLocalizedMessage());
				e.printStackTrace();
			}

		}
		logger.info("Fim do método");
		return idSolicitacaoVaga;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dominio.dao.CaronaDao#deletarRegistroSolicitacaoVadas()
	 */
	@Override
	public void deletarRegistroSolicitacaoVadas() {
		logger.info("Inicializando o método:deletarRegistroSolicitacaoVadas() ");
		try {
			String sql = "delete from solicitacaovagas where idsolicitacaoVagas <> 0";
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ps.executeUpdate();
		} catch (SQLException e) {
			logger.info("Erro ao excluir o registro de solicitacao de vagas em uma carona ", e);
		}
		logger.info("Fim do método");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dominio.dao.CaronaDao#consultaSolicitacao(int)
	 */
	@Override
	public Carona consultaSolicitacao(int idSolicitacao) {
		logger.info("Inicializando o método: consultaSolicitacao(int idSolicitacao)");
		Carona carona = null;
		try {
			String sql = String
					.format("select caronas.localOrigem, caronas.localDestino from caronas where caronas.idCaronas "
							+ "= (select idCarona from solicitacaovagas where idsolicitacaovagas = %d)", idSolicitacao);
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				carona = new Carona();
				carona.setOrigemCarona(rs.getString("localOrigem"));
				carona.setDestinoCarona(rs.getString("localDestino"));
			}
		} catch (SQLException e) {
			logger.info("Erro ao busca dado da carona cadastrada - Origem ou Destino ", e);
		}
		logger.info("Fim do método");
		return carona;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dominio.dao.CaronaDao#buscaDonoCarona(int)
	 */
	@Override
	public String buscaDonoCarona(int idSolicitacao) {
		logger.info("Inicializando o método: buscaDonoCarona(int idSolicitacao)");
		String donoCarona = "";
		try {
			String sql = String.format(
					"(select nome from usuario where idUsuario =\r\n"
							+ "(select usuario_idUsuario from perfil where idPerfil =\r\n"
							+ "(select caronas.perfil_idPerfil from caronas where idCaronas = \r\n"
							+ "(select idCarona from solicitacaovagas where idsolicitacaovagas = %d))))",
					idSolicitacao);
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				donoCarona = rs.getString(1);
			}

		} catch (SQLException e) {
			logger.info("Erro ao busca dono da carona ", e);
		}
		logger.info("Fim do método");
		return donoCarona;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dominio.dao.CaronaDao#buscaPontoEncontro(int)
	 */
	@Override
	public String buscaPontoEncontro(int idSolicitacao) {
		logger.info("Inicializando o método: buscaPontoEncontro(int idSolicitacao)");
		String pontoEncontro = "";
		try {
			String sql = String.format("select ponto from solicitacaovagas where idsolicitacaovagas = %d",
					idSolicitacao);
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				pontoEncontro = rs.getString(1);
			}

		} catch (SQLException e) {
			logger.info("Erro ao busca Pontos de encontros cadastrados ", e);
		}
		logger.info("Fim do método");
		return pontoEncontro;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dominio.dao.CaronaDao#buscaSolicitante(int)
	 */
	@Override
	public String buscaSolicitante(int idSolicitacao) {
		logger.info("Inicializando o método: buscaSolicitante(int idSolicitacao)");
		String solicitante = "";
		try {
			String sql = String.format("select solicitante from solicitacaovagas where idsolicitacaovagas = %d",
					idSolicitacao);
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				solicitante = rs.getString(1);
			}

		} catch (SQLException e) {
			logger.info("Erro ao buscar solicitante da carona", e);
		} finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		logger.info("Fim do método");
		return solicitante;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dominio.dao.CaronaDao#atualizarVagas(int)
	 */
	@Override
	public void atualizarVagas(int idCarona) {
		logger.info("Inicializando o método: atualizarVagas(int idCarona)");
		try {
			String sql = String.format("update caronas set vagas = vagas - 1 where vagas != 0 and idCaronas = %d",
					idCarona);
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ps.executeUpdate();
		} catch (SQLException e) {
			logger.info("Erro ao atualizar números de vagas em uma carona", e);
		}
		logger.info("Fim do método");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dominio.dao.CaronaDao#buscaridCarona(int)
	 */
	@Override
	public int buscaridCarona(int idSolicitacao) {
		logger.info("Inicializando o método:buscaridCarona(int idSolicitacao)");
		int idCarona = 0;
		try {
			String sql = String.format("select idCarona from solicitacaovagas where idsolicitacaovagas = %d",
					idSolicitacao);
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				idCarona = rs.getInt(1);
			}
		} catch (SQLException e) {
			logger.info("Erro ao buscar idCarona", e);
		}
		logger.info("Fim do método");
		return idCarona;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dominio.dao.CaronaDao#atualizarEstadoSolicitacao(int)
	 */
	@Override
	public void atualizarEstadoSolicitacao(int idSolicitacao) {
		logger.info("Inicializando o método:atualizarEstadoSolicitacao(int idSolicitacao)");

		try {
			String sql = String.format("update solicitacaoVagas set estado = 'YES' where idsolicitacaoVagas = %d",
					idSolicitacao);
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ps.executeUpdate();
		} catch (SQLException e) {
			logger.info("Erro ao atualizar estado de uma solicitação", e);
		} finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				logger.info("Erro ao fechar connection", e);
			}
		}
		logger.info("Fim do método");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dominio.dao.CaronaDao#buscarEstado(int)
	 */
	@Override
	public String buscarEstado(int idSolicitacao) {
		logger.info("Inicializando o método: buscarEstado(int idSolicitacao)");
		String estado = "";
		try {
			String sql = String.format("select estado from solicitacaoVagas where idsolicitacaoVagas = %d",
					idSolicitacao);
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				estado = rs.getString(1);
			}
		} catch (SQLException e) {
			logger.info("Erro ao buscar informação sobre o estado de uma solicitação", e);
		}
		logger.info("Fim do método");
		return estado;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dominio.dao.CaronaDao#cancelarSolicitacao(int)
	 */
	@Override
	public void cancelarSolicitacao(int idSolicitacao) {
		logger.info("Inicializando o método: cancelarSolicitacao(int idSolicitacao)");
		try {
			String sql = String.format(
					"update solicitacaoVagas set situacao = 'CANCELADA' where idsolicitacaoVagas = %d", idSolicitacao);
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ps.executeUpdate();
		} catch (SQLException e) {
			logger.info("Erro ao atualizar solicitacaoVagas para o estado CANCELADA.", e);
		} finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				logger.info("Erro ao fechar connection", e);
			}
		}
		logger.info("Fim do método");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dominio.dao.CaronaDao#encrementaVagaCarona(int)
	 */
	@Override
	public void encrementaVagaCarona(int idCarona) {
		logger.info("Inicializando o método: encrementaVagaCarona(int idCarona)");
		try {
			String sql = String.format("update caronas set vagas = vagas + 1 where vagas != 0 and idCaronas = %d",
					idCarona);
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ps.executeUpdate();
		} catch (SQLException e) {
			logger.info("Erro ao encrementar vagas em caronas", e);
		}
		logger.info("Fim do método");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dominio.dao.CaronaDao#buscaridSessao(int)
	 */
	@Override
	public String buscaridSessao(int idSolicitacao) {
		logger.info("Inicializando o método:buscaridSessao(int idSolicitacao)");
		String idSessao = "";
		try {
			String sql = String.format("select idSessao from caronas where idCaronas =\r\n"
					+ "(select idCarona from solicitacaoVagas where idsolicitacaoVagas = %d)", idSolicitacao);
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				idSessao = rs.getString(1);
			}

		} catch (SQLException e) {
			logger.info("Erro ao buscar idSessao", e);
		} finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				logger.info("Erro ao fechar connection", e);
			}
		}
		logger.info("Fim do método");
		return idSessao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dominio.dao.CaronaDao#salva_Solicitacao_Vaga_Vem_Sugestao(java.lang.
	 * String, int, java.lang.String, java.lang.String)
	 */
	@Override
	public int salva_Solicitacao_Vaga_Vem_Sugestao(String idSessao, int idCarona, String solicitante,
			String SITUACAO_SOLICITACAO_VAGA) {
		logger.info(
				"Inicializando o método: salva_Solicitacao_Vaga_Vem_Sugestao(String idSessao, int idCarona, String solicitante,\r\n"
						+ "			String SITUACAO_SOLICITACAO_VAGA)");
		int idsolicitacaoVaga = 0;
		try {
			String sql = String.format(
					"insert into solicitacao_vaga_sem_sugestao (idSessao, idCarona, solicitante, situacaoVagaSolicitada) "
							+ "values('%s','%s','%s','%s')",
					idSessao, idCarona, solicitante, SITUACAO_SOLICITACAO_VAGA);
			PreparedStatement ps = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			while (rs.next()) {

				idsolicitacaoVaga = rs.getInt(1);
			}

		} catch (SQLException e) {
			logger.info("Erro ao salva, solicitação sem sugestão de ponto de encontro, de uma carona cadastrada", e);
		} finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				logger.info("Erro ao fechar connection", e);
			}
		}
		logger.info("Fim do método");
		return idsolicitacaoVaga;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dominio.dao.CaronaDao#consultaSolicitacaoVagaSemPonto(int)
	 */
	@Override
	public Carona consultaSolicitacaoVagaSemPonto(int idSolicitacaoVaga) {
		logger.info("Inicializando o método: consultaSolicitacaoVagaSemPonto(int idSolicitacaoVaga)");
		Carona carona = null;
		try {
			String sql = String.format(
					"select caronas.localOrigem, caronas.localDestino from caronas where caronas.idCaronas "
							+ "= (select idCarona from solicitacao_vaga_sem_sugestao where idSolicitacao_Vaga_Vem_Sugestao = %d)",
					idSolicitacaoVaga);
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				carona = new Carona();
				carona.setOrigemCarona(rs.getString("localOrigem"));
				carona.setDestinoCarona(rs.getString("localDestino"));
			}
		} catch (SQLException e) {
			throw new DAOException("Erro ao busca dado da carona cadastrada - Origem ou Destino ", e);
		}
		logger.info("Fim do método");
		return carona;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dominio.dao.CaronaDao#buscaSolicitacaoDonoCarona(int)
	 */
	@Override
	public String buscaSolicitacaoDonoCarona(int idSolicitacaoVaga) {
		logger.info("Inicializando o método: buscaSolicitacaoDonoCarona(int idSolicitacaoVaga)");
		String dono_Carona = "";
		try {
			String sql = String.format("(select nome from usuario where idUsuario =\r\n"
					+ "(select usuario_idUsuario from perfil where idSessao =\r\n"
					+ "(select idSessao from caronas where idCaronas = \r\n"
					+ "(select idCarona from solicitacao_vaga_sem_sugestao where idSolicitacao_Vaga_Vem_Sugestao = %d))))",
					idSolicitacaoVaga);
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				dono_Carona = rs.getString(1);
			}

		} catch (Exception e) {
			logger.info("Erro ao buscar o nome do dono da carona.", e);
		}
		logger.info("Fim do método");
		return dono_Carona;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dominio.dao.CaronaDao#buscaSolicitanteVaga(int)
	 */
	@Override
	public String buscaSolicitanteVaga(int idSolicitacaoVaga) {
		logger.info("Inicializando o método: buscaSolicitanteVaga(int idSolicitacaoVaga) ");
		String solicitante = "";
		try {
			String sql = String.format(
					"select solicitante from solicitacao_vaga_sem_sugestao where idSolicitacao_Vaga_Vem_Sugestao = %d",
					idSolicitacaoVaga);
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				solicitante = rs.getString(1);
			}

		} catch (Exception e) {
			logger.info("Erro ao buscar o nome do solicitante da carona", e);
		} finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				logger.info("Erro ao fechar connection", e);
			}
		}

		logger.info("Fim do método");
		return solicitante;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dominio.dao.CaronaDao#deletarRegistroSolicitacaoSemSugestao()
	 */
	@Override
	public void deletarRegistroSolicitacaoSemSugestao() {
		logger.info("Inicializando o método: deletarRegistroSolicitacaoSemSugestao()");
		try {
			String sql = "delete from solicitacao_vaga_sem_sugestao where idSolicitacao_Vaga_Vem_Sugestao <> 0";
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ps.executeUpdate();
		} catch (SQLException e) {
			logger.info("Erro ao excluir o registro de solicitacao de vagas em uma carona", e);
		}
		logger.info("Fim do método");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dominio.dao.CaronaDao#buscaridSessaoDonoCarona(java.lang.String)
	 */
	@Override
	public String buscaridSessaoDonoCarona(int idSolicitacao) {
		logger.info("Inicializando o método: buscaridSessaoDonoCarona(int idSolicitacao)");
		String idSessaoDonoCarona = "";
		try {
			String sql = String.format("select idSessao from caronas where idCaronas =\r\n"
					+ "(select idCarona from solicitacao_vaga_sem_sugestao where idSolicitacao_Vaga_Vem_Sugestao = %d)",
					idSolicitacao);
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				idSessaoDonoCarona = rs.getString(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				logger.info("Erro ao fechar connection", e);
			}
		}
		logger.info("Fim do método");
		return idSessaoDonoCarona;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dominio.dao.CaronaDao#buscaridCarona_SemSugestaoPonto(int)
	 */
	@Override
	public int buscaridCarona_SemSugestaoPonto(int idSolicitacao) {
		logger.info("Inicializando o método: buscaridCarona_SemSugestaoPonto(int idSolicitacao)");
		int idCarona = 0;
		try {
			String sql = String.format(
					"select idCarona from solicitacao_vaga_sem_sugestao where idSolicitacao_Vaga_Vem_Sugestao = %d",
					idSolicitacao);
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				idCarona = rs.getInt(1);
			}
		} catch (SQLException e) {
			logger.info("Erro ao buscar idCarona", e);
		}
		logger.info("Fim do método");
		return idCarona;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dominio.dao.CaronaDao#AterarSituacao_SemSugestaoPonto(int)
	 */
	@Override
	public void AterarSituacao_SemSugestaoPonto(int idSolicitacao) {
		logger.info("Inicializando o método: AterarSituacao_SemSugestaoPonto(int idSolicitacao)");
		try {
			String sql = String.format(
					"update solicitacao_vaga_sem_sugestao set situacaoVagaSolicitada = 'ACEITO' where idSolicitacao_Vaga_Vem_Sugestao = %d",
					idSolicitacao);
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ps.executeUpdate();
		} catch (SQLException e) {
			logger.info("Erro ao atualizar solicitação", e);
		} finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				logger.info("Erro ao fechar connection", e);
			}
		}
		logger.info("Fim do método");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dominio.dao.CaronaDao#verificar_Solicitacao_Vaga(int)
	 */
	@Override
	public String verificar_Solicitacao_Vaga(int idSolicitacao) {
		logger.info("Inicializando o método: verificar_Solicitacao_Vaga(int idSolicitacao)");
		String situacao = "";
		try {
			String sql = String.format("select situacaoVagaSolicitada from solicitacao_vaga_sem_sugestao "
					+ "where idSolicitacao_Vaga_Vem_Sugestao", idSolicitacao);
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				situacao = rs.getString(1);
			}
		} catch (SQLException e) {
			logger.info("Erro ao capturar valor da situação vaga  solicitacao", e);
		}
		logger.info("Fim do método");
		return situacao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dominio.dao.CaronaDao#alterar_Sicituacao_Solicitacao(int)
	 */
	@Override
	public void alterar_Sicituacao_Solicitacao(int idSolicitacao) {
		logger.info("Inicializando o método: alterar_Sicituacao_Solicitacao(int idSolicitacao)");
		try {
			String sql = String.format("update solicitacao_vaga_sem_sugestao set situacaoVagaSolicitada = 'REJEITADA' "
					+ "where idSolicitacao_Vaga_Vem_Sugestao = %d", idSolicitacao);
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ps.executeUpdate();
		} catch (SQLException e) {
			logger.info("Erro em atualizar solicitação vaga sem sugestão", e);
		} finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				logger.info("Erro ao fechar connection", e);
			}
			logger.info("Fim do método");
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dominio.dao.CaronaDao#getTodasCaronasUsuario(java.lang.String)
	 */
	@Override
	public String getTodasCaronasUsuario(String idSessao) {
		logger.info("Inicializando o método: getTodasCaronasUsuario(String idSessao)");
		String caronas = "{";
		try {
			String sql = String.format("select idCaronas from caronas where idSessao = '%s'", idSessao);
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				caronas += Integer.toString(rs.getInt(1)) + ",";
			}

		} catch (SQLException e) {
			logger.info("Erro ao retorna o idCarona das caronas cadastradas.", e);
		} finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				logger.info("Erro ao fechar connection", e);
			}
		}
		if (!caronas.equals("{")) {
			caronas = caronas.substring(0, caronas.length() - 1);
		}
		caronas += "}";
		logger.info("Fim do método");
		return caronas;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dominio.dao.CaronaDao#getSolicitacoesConfirmadas(java.lang.String,
	 * int)
	 */
	@Override
	public String getSolicitacoesConfirmadas(String idSessao, int idCarona) {
		logger.info("Inicializando o método: getSolicitacoesConfirmadas(String idSessao, int idCarona)");
		String solicitacoes_confirmadas = "{";
		try {
			String sql = String.format("select idSolicitacao_Vaga_Vem_Sugestao from solicitacao_vaga_sem_sugestao "
					+ "where idCarona = %d and situacaoVagaSolicitada = 'ACEITO'", idCarona);
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				solicitacoes_confirmadas += Integer.toString(rs.getInt(1)) + ",";
			}
		} catch (SQLException e) {
			logger.info("Erro ao captura idSolicitacao_Vaga_Vem_Sugestao", e);
		} finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				logger.info("Erro ao fechar connection", e);
			}
		}
		if (!solicitacoes_confirmadas.equals("{")) {
			solicitacoes_confirmadas = solicitacoes_confirmadas.substring(0, solicitacoes_confirmadas.length() - 1);
		}
		solicitacoes_confirmadas += "}";
		logger.info("Fim do método");
		return solicitacoes_confirmadas;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dominio.dao.CaronaDao#getSolicitacoesPendentes(java.lang.String,
	 * int)
	 */
	@Override
	public String getSolicitacoesPendentes(String idSessao, int idCarona) {
		logger.info("Inicializando o método: getSolicitacoesPendentes(String idSessao, int idCarona)");
		String solicitacoes_Pendentes = "{";
		try {
			String sql = String.format("select idSolicitacao_Vaga_Vem_Sugestao from solicitacao_vaga_sem_sugestao "
					+ "where idCarona = %d and situacaoVagaSolicitada = 'PENDENTE'", idCarona);
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				solicitacoes_Pendentes += Integer.toString(rs.getInt(1)) + ",";
			}
		} catch (SQLException e) {
			logger.info("Erro ao captura idSolicitacao_Vaga_Vem_Sugestao", e);
		} finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				logger.info("Erro ao fechar connection", e);
			}
		}
		if (!solicitacoes_Pendentes.equals("{")) {
			solicitacoes_Pendentes = solicitacoes_Pendentes.substring(0, solicitacoes_Pendentes.length() - 1);
		}
		solicitacoes_Pendentes += "}";
		logger.info("Fim do método");
		return solicitacoes_Pendentes;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dominio.dao.CaronaDao#getPontosEncontro(java.lang.String, int)
	 */
	@Override
	public String getPontosEncontro(String idSessao, int idCarona) {
		logger.info("Inicializando o método: getPontosEncontro(String idSessao, int idCarona)");
		String idResposta_pontoEncontro = "[";

		try {
			String sql = String.format("select idrespostaSugestaoCarona from respostaSugestaoCarona "
					+ "where idSessao = '%s' and idCarona = %d", idSessao, idCarona);
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				idResposta_pontoEncontro = Integer.toString(rs.getInt(1));
			}
		} catch (SQLException e) {
			logger.info("Erro ao buscar id resposta de uma sugestão para um ponto de encontro de uma carona", e);
		} finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				logger.info("Erro ao fechar connection", e);
			}
		}
		idResposta_pontoEncontro += "]";
		logger.info("Fim do método");
		return idResposta_pontoEncontro;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dominio.dao.CaronaDao#getPontosSugeridos(java.lang.String, int)
	 */
	@Override
	public String getPontosSugeridos(String idSessao, int idCarona) {
		logger.info("Inicializando o método: getPontosSugeridos(String idSessao, int idCarona)");
		String idSugestao_pontoEncontro = "[";

		try {
			String sql = String.format("select pontos from solicitacoes " + "where idSessao = '%s' and idCarona = %d",
					idSessao, idCarona);
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				idSugestao_pontoEncontro += rs.getString(1);
			}
		} catch (SQLException e) {
			logger.info("Erro ao retorna os pontos de encotros cadastrados", e);
		} finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				logger.info("Erro ao fechar connection", e);
			}
		}
		idSugestao_pontoEncontro += "]";
		logger.info("Fim do método");
		return idSugestao_pontoEncontro;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dominio.dao.CaronaDao#reviewCaronaPresenca(java.lang.String, int,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public void reviewCaronaPresenca(String idSessao, int idCarona, String loginCaroneiro, String review) {
		logger.info(
				"Inicializando o método:reviewCaronaPresenca(String idSessao, int idCarona, String loginCaroneiro, String review)");
		try {
			String sql = String.format(
					"insert into review (idSessao, idCarona, login, presenca)values('%s',%d,'%s','%s') ", idSessao,
					idCarona, loginCaroneiro, review);
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ps.executeUpdate();
		} catch (SQLException e) {
			logger.info("Erro ao inserir dados na tabela review", e);
		} finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				logger.info("Erro ao fechar connection", e);
			}
		}
		logger.info("Fim do método");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dominio.dao.CaronaDao#deletarReview()
	 */
	@Override
	public void deletarReview() {
		logger.info("Inicializando o método:deletarReview()");
		try {
			String sql = "delete from review where idreview <> 0";
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("Erro ao excluir informações da tabela review ", e);
		}
		logger.info("Fim do método");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dominio.dao.CaronaDao#getAtributoCaronaMunicipal(int,
	 * java.lang.String)
	 */
	@Override
	public boolean getAtributoCaronaMunicipal(int idCarona, String atributo) {
		logger.info("Inicializando o método:getAtributoCaronaMunicipal(int idCarona, String atributo) ");
		String tipo = "";
		boolean valor = false;

		try {
			String sql = String.format("select tipoCarona from caronas where idCaronas = %d", idCarona);
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				tipo = rs.getString(1);
			}

		} catch (SQLException e) {
			logger.info("Erro ao selecionar o tipo da Carona", e);
		} finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				logger.info("Erro ao fechar connection", e);
			}
		}

		if (atributo.equals("ehMunicipal")) {
			if (tipo.equals("Municipal")) {
				valor = true;
			}
		}
		logger.info("Fim do método.");
		return valor;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dominio.dao.CaronaDao#buscarCaronaMunicipio(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public String buscarCaronaMunicipio(String idSessao, String cidade) {
		logger.info("Inicializando o método: buscarCaronaMunicipio(String idSessao, String cidade)");
		String idCarona = "{";

		try {
			String sql = String.format("select idCaronas from caronas where idSessao = '%s' and cidade = '%s'",
					idSessao, cidade);
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				idCarona += rs.getString(1) + ",";
			}

		} catch (SQLException e) {
			logger.info("Erro ao selecionar o idCarona", e);
		} finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				logger.info("Erro ao fechar connection", e);
			}
		}

		if (!idCarona.equals("{")) {
			idCarona = idCarona.substring(0, idCarona.length() - 1);
		}
		idCarona += "}";
		logger.info("Fim do mértodo.buscarCaronaMunicipio(String idSessao, String cidade)");
		return idCarona;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dominio.dao.CaronaDao#buscarCarona_Municipio_id(java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public String buscarCarona_Municipio_id(String idSessao, String cidade, String origem, String destino) {
		logger.info(
				"Inicializando o método: buscarCarona_Municipio_id(String idSessao, String cidade, String origem, String destino)");
		String idCarona = "{";

		try {
			String sql = String.format(
					"select idCaronas from caronas where cidade = '%s' and localOrigem = '%s' and localDestino = '%s'",
					cidade, origem, destino);
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				idCarona += rs.getString(1) + ",";
			}

		} catch (SQLException e) {
			logger.info("Erro ao selecionar o idCarona", e);
		} finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				logger.info("Erro ao fechar connection", e);
			}
		}

		if (!idCarona.equals("{")) {
			idCarona = idCarona.substring(0, idCarona.length() - 1);
		}
		logger.info(
				"Fim do método. buscarCarona_Municipio_id(String idSessao, String cidade, String origem, String destino)");
		idCarona += "}";

		return idCarona;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dominio.dao.CaronaDao#cadastrarInteresse(java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public int cadastrarInteresse(String idSessao, String origem, String destino, String data, String horaInicio,
			String horaFim) {
		int id = 0;
		try {
			logger.info("Inicializando o método");
			String sql = String.format(
					"insert into interessecaronas(idSessao, origem,destino,data,horaInicial, horaFinal)"
							+ "values('%s','%s','%s','%s','%s' ,'%s')",
					idSessao, origem, destino, data, horaInicio, horaFim);
			PreparedStatement ps = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.executeUpdate();

			ResultSet rs = ps.getGeneratedKeys();

			while (rs.next()) {
				id = rs.getInt(1);
			}

		} catch (SQLException e) {
			logger.info("Erro ao salva dados de interesse de uma carona", e);

		} finally {
			try {

				this.connection.close();
			} catch (SQLException e) {
				logger.info("Erro ao fechar connection", e);
			}
		}
		logger.info("Fim do método");
		return id;
	}

	@Override
	public Carona buscar_dadosCarona(InteresseCarona interesseCaronas) {
		logger.info("Inicializando método");
		Carona carona = null;
		String data = "";
		if (interesseCaronas.getData().equals("")) {
			Date hoje = new Date();
			SimpleDateFormat df;
			df = new SimpleDateFormat("dd/MM/yyyy");
			data = df.format(hoje);
			
			try {
				String sql = String.format(
						"select idCaronas, data, hora, localOrigem, localDestino from caronas where caronas.localOrigem = '%s' "
								+ "and caronas.localDestino = '%s' and caronas.data >= '%s'",
						interesseCaronas.getOrigem(), interesseCaronas.getDestino(), data);
				PreparedStatement ps = this.connection.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					carona = new Carona();
					carona.setIdCaronas(rs.getInt(1));
					carona.setData(rs.getString(2));
					carona.setHora(rs.getString(3));
					carona.setOrigemCarona(rs.getString(4));
					carona.setDestinoCarona(rs.getString(5));
				}

			} catch (SQLException e) {
				logger.info("Erro ao salva dados de interesse de uma carona", e);

			} finally {
				try {

					this.connection.close();
				} catch (SQLException e) {
					logger.info("Erro ao fechar connection", e);
				}
			}
		} else {
			try {
				String sql = String.format(
						"select idCaronas, data, hora, localOrigem, localDestino from caronas where caronas.localOrigem = '%s' "
								+ "and caronas.localDestino = '%s' and caronas.data = '%s'",
						interesseCaronas.getOrigem(), interesseCaronas.getDestino(), interesseCaronas.getData());
				PreparedStatement ps = this.connection.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					carona = new Carona();
					carona.setIdCaronas(rs.getInt(1));
					carona.setData(rs.getString(2));
					carona.setHora(rs.getString(3));
					carona.setOrigemCarona(rs.getString(4));
					carona.setDestinoCarona(rs.getString(5));
				}

			} catch (SQLException e) {
				logger.info("Erro ao capturar dados de uma carona", e);

			} finally {
				try {

					this.connection.close();
				} catch (SQLException e) {
					logger.info("Erro ao fechar connection", e);
				}
			}
		}

		logger.info("Fim do método");
		return carona;
	}

}
