package com.dominio.dao.Jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dominio.Carona;
import com.dominio.dao.CaronaDao;
import com.dominio.dao.DAOException;
import com.mysql.jdbc.Statement;

/**
 * @author Bruno Miranda Thassio Lucena. Classe responsavel por implementa os
 *         métodos da interface CaronoDao().
 *
 */
public class JdbcCaronaDao implements CaronaDao {

	private Connection connection;

	/**
	 * @param connection
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
			String sql = String.format(
					"insert into caronas(localOrigem, localDestino, data, hora, vagas, idSessao,perfil_idPerfil)"
							+ "values('%s','%s','%s','%s','%s','%s',%d)",
					carona.getOrigemCarona(), carona.getDestinoCarona(), carona.getData(), carona.getHora(),
					carona.getVagas(), carona.getIdSessao(), id);
			PreparedStatement ps = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.executeUpdate();

			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			int idCarona = rs.getInt(1);
			carona.setIdCaronas(idCarona);

		} catch (SQLException e) {
			throw new DAOException("Erro ao salva dados do Carona", e);
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
	 * @see com.dominio.dao.CaronaDao#buscartodas()
	 */
	@Override
	public List<Carona> buscartodas() {
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
			throw new DAOException("Erro ao busca caronas cadastradas ", e);
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
		return caronas;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dominio.dao.CaronaDao#buscarCarona(int)
	 */
	@Override
	public Carona buscarCarona(int idcarona) {
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
			throw new DAOException("Erro ao busca dado da carona cadastrada ", e);
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

		return carona;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dominio.dao.CaronaDao#isCaronaId(int)
	 */
	@Override
	public boolean isCaronaId(int idcarona) {
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
			throw new DAOException("Erro ao pesquisa caronas cadastradas ", e);
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
			throw new DAOException("Erro ao salva solicitação ponto de encontro", e);
		} finally {
			try {

				this.connection.close();
			} catch (SQLException e) {
				System.err.println(e.getLocalizedMessage());
				e.printStackTrace();
			}
		}
		return idSolicitacao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dominio.dao.CaronaDao#deletarRegistroSoliciatacao()
	 */
	@Override
	public void deletarRegistroSoliciatacao() {
		try {
			String sql = "delete from solicitacoes where idSugestao <> 0";
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("Erro ao excluir as solicitacões de carona ", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dominio.dao.CaronaDao#deletarDados()
	 */
	@Override
	public void deletarCaronas() {
		try {
			String sql = "delete from caronas where idCaronas <> 0";
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("Erro ao excluir caronas cadastradas ", e);
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
	 * @see com.dominio.dao.CaronaDao#salvaRespostaPontoEncontro(java.lang.String,
	 * int, int, java.lang.String, int)
	 */
	@Override
	public void salvaRespostaPontoEncontro(String idSessao, int idCarona, int idSolicitacoes, String pontos,
			int idPerfil) {
		try {
			String sql = String.format(
					"insert into respostaSugestaoCarona (idSessao, idCarona, idSugestao, pontos,perfil_idPerfil)"
							+ " values('%s', %d,%d,'%s',%d)",
					idSessao, idCarona, idSolicitacoes, pontos, idPerfil);
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ps.executeUpdate();

		} catch (SQLException e) {
			throw new DAOException("Erro ao adicionar respota na base de dados ", e);
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
	 * @see com.dominio.dao.CaronaDao#deletarRespostaSolicitacao()
	 */
	@Override
	public void deletarRespostaSolicitacao() {
		try {
			String sql = "delete from respostasugestaocarona where idrespostaSugestaoCarona <> 0";
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("Erro ao excluir Resposta da solicitação ponto de encontro ", e);
		}

	}

	@Override
	public int salvaSolicitacaoVagaCarona(String idSessao, int idCarona, String ponto, String solicitante) {
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
			throw new DAOException("Erro ao salva solicitação da vaga em uma carona", e);
		} finally {
			try {

				this.connection.close();
			} catch (SQLException e) {
				System.err.println(e.getLocalizedMessage());
				e.printStackTrace();
			}

		}
		return idSolicitacaoVaga;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dominio.dao.CaronaDao#deletarRegistroSolicitacaoVadas()
	 */
	@Override
	public void deletarRegistroSolicitacaoVadas() {
		try {
			String sql = "delete from solicitacaovagas where idsolicitacaoVagas <> 0";
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("Erro ao excluir o registro de solicitacao de vagas em uma carona ", e);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dominio.dao.CaronaDao#consultaSolicitacao(int)
	 */
	@Override
	public Carona consultaSolicitacao(int idSolicitacao) {
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
			throw new DAOException("Erro ao busca dado da carona cadastrada - Origem ou Destino ", e);
		}
		return carona;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dominio.dao.CaronaDao#buscaDonoCarona(int)
	 */
	@Override
	public String buscaDonoCarona(int idSolicitacao) {
		String donoCarona = "";
		try {
			String sql = String.format("(select usuario.nome from usuario where idUsuario = \r\n"
					+ "(select caronas.perfil_idPerfil from caronas where idCaronas = \r\n"
					+ "(select idCarona from solicitacaovagas where idsolicitacaovagas = %d)))", idSolicitacao);
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				donoCarona = rs.getString(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return donoCarona;
	}

	@Override
	public String buscaPontoEncontro(int idSolicitacao) {
		String pontoEncontro = "";
		try {
			String sql = String.format("select ponto from solicitacaovagas where idsolicitacaovagas = %d",
					idSolicitacao);
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				pontoEncontro = rs.getString(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return pontoEncontro;
	}

	@Override
	public String buscaSolicitante(int idSolicitacao) {
		String solicitante = "";
		try {
			String sql = String.format("select solicitante from solicitacaovagas where idsolicitacaovagas = %d",
					idSolicitacao);
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				solicitante = rs.getString(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return solicitante;
	}

	@Override
	public void atualizarVagas(int idCarona) {
		try {
			String sql = String.format("update caronas set vagas = vagas - 1 where vagas != 0 and idCaronas = %d",
					idCarona);
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public int buscaridCarona(int idSolicitacao) {
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
			e.printStackTrace();
		}
		return idCarona;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dominio.dao.CaronaDao#atualizarEstadoSolicitacao(int)
	 */
	@Override
	public void atualizarEstadoSolicitacao(int idSolicitacao) {

		try {
			String sql = String.format("update solicitacaoVagas set estado = 'YES' where idsolicitacaoVagas = %d",
					idSolicitacao);
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public String buscarEstado(int idSolicitacao) {
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
			e.printStackTrace();
		}
		return estado;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dominio.dao.CaronaDao#cancelarSolicitacao(int)
	 */
	@Override
	public void cancelarSolicitacao(int idSolicitacao) {
		try {
			String sql = String.format(
					"update solicitacaoVagas set situacao = 'CANCELADA' where idsolicitacaoVagas = %d", idSolicitacao);
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dominio.dao.CaronaDao#encrementaVagaCarona(int)
	 */
	@Override
	public void encrementaVagaCarona(int idCarona) {
		try {
			String sql = String.format("update caronas set vagas = vagas + 1 where vagas != 0 and idCaronas = %d",
					idCarona);
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dominio.dao.CaronaDao#buscaridSessao(int)
	 */
	@Override
	public String buscaridSessao(int idSolicitacao) {
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
			e.getLocalizedMessage();
			e.printStackTrace();
		} finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
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
			e.getLocalizedMessage();
			e.printStackTrace();
		} finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return idsolicitacaoVaga;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dominio.dao.CaronaDao#consultaSolicitacaoVagaSemPonto(int)
	 */
	@Override
	public Carona consultaSolicitacaoVagaSemPonto(int idSolicitacaoVaga) {
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
		return carona;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dominio.dao.CaronaDao#buscaSolicitacaoDonoCarona(int)
	 */
	@Override
	public String buscaSolicitacaoDonoCarona(int idSolicitacaoVaga) {
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
			e.printStackTrace();
		}

		return dono_Carona;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dominio.dao.CaronaDao#buscaSolicitanteVaga(int)
	 */
	@Override
	public String buscaSolicitanteVaga(int idSolicitacaoVaga) {
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
			e.printStackTrace();
		} finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return solicitante;
	}

	@Override
	public void deletarRegistroSolicitacaoSemSugestao() {
		try {
			String sql = "delete from solicitacao_vaga_sem_sugestao where idSolicitacao_Vaga_Vem_Sugestao <> 0";
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("Erro ao excluir o registro de solicitacao de vagas em uma carona ", e);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dominio.dao.CaronaDao#buscaridSessaoDonoCarona(java.lang.String)
	 */
	@Override
	public String buscaridSessaoDonoCarona(int idSolicitacao) {
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
			e.getLocalizedMessage();
			e.printStackTrace();
		} finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return idSessaoDonoCarona;
	}

	@Override
	public int buscaridCarona_SemSugestaoPonto(int idSolicitacao) {
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
			e.printStackTrace();
		}
		return idCarona;
	}

	@Override
	public void AterarSituacao_SemSugestaoPonto(int idSolicitacao) {
		try {
			String sql = String.format(
					"update solicitacao_vaga_sem_sugestao set situacaoVagaSolicitada = 'ACEITO' where idSolicitacao_Vaga_Vem_Sugestao = %d",
					idSolicitacao);
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dominio.dao.CaronaDao#verificar_Solicitacao_Vaga(int)
	 */
	@Override
	public String verificar_Solicitacao_Vaga(int idSolicitacao) {
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
			e.printStackTrace();
		}
		return situacao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dominio.dao.CaronaDao#alterar_Sicituacao_Solicitacao(int)
	 */
	@Override
	public void alterar_Sicituacao_Solicitacao(int idSolicitacao) {
		try {
			String sql = String.format("update solicitacao_vaga_sem_sugestao set situacaoVagaSolicitada = 'REJEITADA' "
					+ "where idSolicitacao_Vaga_Vem_Sugestao = %d", idSolicitacao);
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public int getCaronaUsuario(String idSessao, int indexCarona) {
		int id = 0;
		try {
			String sql = String.format("insert into informacaoCarona (idSessao, indexCarona) values ('%s',%d)",
					idSessao, indexCarona);
			PreparedStatement ps = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			while (rs.next()) {

				id = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
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
	public String getTodasCaronasUsuario(String idSessao) {
		String caronas = "{";
		try {
			String sql = String.format("select idCaronas from caronas where idSessao = '%s'", idSessao);
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				caronas += Integer.toString(rs.getInt(1)) + ",";
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (!caronas.equals("{")) {
			caronas = caronas.substring(0, caronas.length() - 1);
		}
		caronas += "}";

		return caronas;
	}

	@Override
	public void deletarInformacoesCaornas() {
		try {
			String sql = "delete from informacaocarona where idinformacaoCarona <> 0";
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("Erro ao excluir informações carona ", e);
		}

	}

	@Override
	public String getSolicitacoesConfirmadas(String idSessao, int idCarona) {
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
			e.printStackTrace();
		} finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (!solicitacoes_confirmadas.equals("{")) {
			solicitacoes_confirmadas = solicitacoes_confirmadas.substring(0, solicitacoes_confirmadas.length() - 1);
		}
		solicitacoes_confirmadas += "}";
		return solicitacoes_confirmadas;
	}

	@Override
	public String getSolicitacoesPendentes(String idSessao, int idCarona) {
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
			e.printStackTrace();
		} finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (!solicitacoes_Pendentes.equals("{")) {
			solicitacoes_Pendentes = solicitacoes_Pendentes.substring(0, solicitacoes_Pendentes.length() - 1);
		}
		solicitacoes_Pendentes += "}";
		return solicitacoes_Pendentes;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dominio.dao.CaronaDao#getPontosEncontro(java.lang.String, int)
	 */
	@Override
	public String getPontosEncontro(String idSessao, int idCarona) {
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
			e.printStackTrace();
		} finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		idResposta_pontoEncontro += "]";
		return idResposta_pontoEncontro;
	}

	@Override
	public String getPontosSugeridos(String idSessao, int idCarona) {
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
			e.printStackTrace();
		} finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		idSugestao_pontoEncontro += "]";
		return idSugestao_pontoEncontro;
	}

	@Override
	public void reviewCaronaPresenca(String idSessao, int idCarona, String loginCaroneiro, String review) {
		try {
			String sql = String.format("insert into review (idSessao, idCarona, login, presenca)values('%s',%d,'%s','%s') ", idSessao, idCarona, loginCaroneiro, review);
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void deletarReview() {
		try {
			String sql = "delete from review where idreview <> 0";
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("Erro ao excluir informações da tabela review ", e);
		}
		
	}


}
