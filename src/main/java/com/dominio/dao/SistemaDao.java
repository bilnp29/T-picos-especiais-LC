package com.dominio.dao;

import java.util.List;
import java.util.Map;

import com.dominio.Carona;
import com.dominio.PerfilUsuario;
import com.dominio.Usuario;
import com.tratamento.erro.ErroException;

/**
 * @author Bruno Miranda Thassio Lucena Classe responsaval por realizar tarefas
 *         relacionadas a persistencia de dados. Das classe Usuario(), Carona(),
 *         PerfilUsuario().
 *
 */
public class SistemaDao {

	/**
	 * O método<b>guardaUsuario</b> salva as informações dos usuarios cadastrados no
	 * banco de dados shared_carpool.
	 * 
	 * @param usuario
	 * @throws Exception
	 */
	public void guardaUsuario(Usuario usuario) throws Exception {
		try {
			UsuarioDao usuarioDao = DAOFactory.getDaoFactory().getUsuarioDao();
			usuarioDao.salvar(usuario);
		} catch (Exception e) {
			System.err.println("Erro ao salva usuário " + e.getMessage());
			e.getStackTrace();
		}
	}

	/**
	 * O método<b>salvaPerfil</b> salva um perfil valido de usuario cadastrado no
	 * banco de dados shared_carpool.
	 * 
	 * @param perfilUsuario
	 */
	public void salvarPefil(PerfilUsuario perfilUsuario) {
		try {
			PerfilUsuarioDao perfilDao = DAOFactory.getDaoFactory().getPerfilUsuario();
			perfilDao.salva(perfilUsuario);
		} catch (Exception e) {
			System.err.println("Erro ao salva usuário " + e.getMessage());
			e.getStackTrace();
		}

	}

	/**
	 * O método <b>salvarDados</b> salva as informações das caronas cadastradas
	 * pelos usuarios recebendo com paramentro de entrada HashMap dos tipo carona
	 * Carona.
	 * 
	 * @param carona
	 * @throws Exception
	 */
	public void salvarDadosCarona(Carona carona, int id) throws Exception {
		try {
			CaronaDao caronaDao = DAOFactory.getDaoFactory().getCaronaDao();
			caronaDao.salva(carona, id);
		} catch (Exception e) {
			System.err.println("Erro ao salva usuário " + e.getMessage());
			e.getStackTrace();
		}

	}

	/**
	 * @param idCarona
	 * @param idSessao
	 * @param carona
	 * 
	 *            public void salvaCaronaInfoAdicional(String idCarona, String
	 *            idSessao, Carona carona) { CaronaDao caronaDao =
	 *            DAOFactory.getDaoFactory().getCaronaDao();
	 *            caronaDao.salvaInfoAdicionaCarona(idCarona, idSessao, carona);
	 * 
	 *            }
	 * 
	 *            /** O método realizar busca no banco de acordo com os dados
	 *            passados abaixo. Onde serão recuparados nome e o endereco do
	 *            usuario.
	 * 
	 * @param login
	 * @param atributo
	 * @return
	 * @throws Exception
	 */
	public String buscarPeloAtributo(String login, String atributo) throws Exception {
		String atributoUsuario = "";
		String loginUsuario = "";
		UsuarioDao usuarioDao = DAOFactory.getDaoFactory().getUsuarioDao();
		if ((atributo == null) || atributo.equals("")) {
			throw new Exception("Atributo invalido");
		} else if ((!atributo.equals("nome")) && (!atributo.equals("endereco"))) {
			throw new Exception("Atributo inexistente");
		} else {
			try {
				Usuario usuario = usuarioDao.buscarAtributo(login, atributo);
				loginUsuario = usuario.getLogin();
				if (loginUsuario.equals(login)) {
					if (atributo.equals("nome")) {
						atributoUsuario = usuario.getNome();
					} else {
						atributoUsuario = usuario.getEndereco();
					}
				}
			} catch (Exception e) {
				System.err.println("Erro, atributo não encontrado " + e.getMessage());
				e.getStackTrace();
			}
		}

		return atributoUsuario;

	}

	/**
	 * @return Busca todos os usuarios cadastrado no sistema
	 */
	public Map<String, Usuario> bustaTodos() {
		UsuarioDao usuarioDao = DAOFactory.getDaoFactory().getUsuarioDao();
		Map<String, Usuario> usuario = usuarioDao.buscarTodosUsuarios();
		return usuario;
	}

	/**
	 * Excluir os dados das tabelas perfil, caronas, usuario que estão presenta no
	 * banco.
	 * 
	 * @throws Exception
	 */
	public void deletarArquivo() throws Exception {
		CaronaDao caronaDao = DAOFactory.getDaoFactory().getCaronaDao();
		PerfilUsuarioDao perfilDao = DAOFactory.getDaoFactory().getPerfilUsuario();
		UsuarioDao usuarioDao = DAOFactory.getDaoFactory().getUsuarioDao();

		caronaDao.deletarRespostaSolicitacao();

		caronaDao.deletarRegistroSoliciatacao();

		caronaDao.deletarRegistroSolicitacaoVadas();

		caronaDao.deletarRegistroSolicitacaoSemSugestao();
		
		caronaDao.deletarReview();

		caronaDao.deletarInformacoesCaornas();

		caronaDao.deletarCaronas();

		perfilDao.deletarPerfil();

		usuarioDao.deletarRegistro();

	}

	/**
	 * @param idSessao
	 * @return Faz busca de um idSessao valido
	 */
	public String buscarIdsessao(String idSessao) {
		PerfilUsuarioDao perfilDao = DAOFactory.getDaoFactory().getPerfilUsuario();
		return perfilDao.buscarid(idSessao);
	}

	/**
	 * @return retorna uma lista das caronas cadastradas que possui vagas no
	 *         sistema.
	 */
	public List<Carona> buscarCarona() {
		CaronaDao caronaDao = DAOFactory.getDaoFactory().getCaronaDao();
		List<Carona> caronas = caronaDao.buscartodas();
		return caronas;
	}

	/**
	 * @param idcarona
	 * @return Verificar se existe o id especificado.
	 * 
	 */
	public boolean isIdCarona(int idcarona) {
		CaronaDao caronaDao = DAOFactory.getDaoFactory().getCaronaDao();
		return caronaDao.isCaronaId(idcarona);
	}

	/**
	 * @param idcarona
	 * @param atributo
	 * @return Faz busca em uma carona especifica, diacordo com o id da carona,
	 *         retornando a origem ou destino ou vaga ou data da mesma
	 */
	public String buscarAtributoCarona(int idcarona, String atributo) {
		String atributoCarona = "";
		CaronaDao caronaDao = DAOFactory.getDaoFactory().getCaronaDao();
		if ((atributo == null) || atributo.equals("")) {
			throw new ErroException("Atributo invalido");
		} else if ((!atributo.equals("origem")) && (!atributo.equals("destino")) && (!atributo.equals("vagas"))
				&& (!atributo.equals("data"))) {
			throw new ErroException("Atributo inexistente");
		} else {
			try {
				Carona carona = caronaDao.buscarCarona(idcarona);
				if (atributo.equals("origem")) {
					atributoCarona = carona.getOrigemCarona();
				} else if (atributo.equals("destino")) {
					atributoCarona = carona.getDestinoCarona();
				} else if (atributo.equals("data")) {
					atributoCarona = carona.getData();
				} else {
					atributoCarona = carona.getVagas();
				}

			} catch (Exception e) {
				System.err.println("Erro, atributo não encontrado " + e.getMessage());
				e.getStackTrace();
			}
		}

		return atributoCarona;
	}

	/**
	 * @param idcarona
	 * @return Descreve os dados de uma carona cadastrada no sistema.
	 */
	public String descricaoCarona(int idcarona) {
		String atributoCarona = "";
		try {
			CaronaDao caronaDao = DAOFactory.getDaoFactory().getCaronaDao();
			Carona carona = caronaDao.buscarCarona(idcarona);
			atributoCarona = carona.getOrigemCarona() + " para " + carona.getDestinoCarona() + ", no dia "
					+ carona.getData() + ", as " + carona.getHora();

		} catch (Exception e) {
			System.err.println("Erro, carona não localizada " + e.getMessage());
			e.getStackTrace();
		}

		return atributoCarona;
	}

	/**
	 * @param idcarona
	 * @return Retorna um trajeto valido de acordo com o id da carona.
	 */
	public String descricaoTrajero(int idcarona) {

		String atributoCarona = "";
		try {
			CaronaDao caronaDao = DAOFactory.getDaoFactory().getCaronaDao();
			Carona carona = caronaDao.buscarCarona(idcarona);
			atributoCarona = carona.getOrigemCarona() + " - " + carona.getDestinoCarona();

		} catch (Exception e) {
			System.err.println("Erro, carona não localizada " + e.getMessage());
			e.getStackTrace();
		}
		return atributoCarona;
	}

	/**
	 * Encerra sessões ativas no sistema.
	 */
	public void fechaSistema() {
		PerfilUsuarioDao perfilDao = DAOFactory.getDaoFactory().getPerfilUsuario();
		perfilDao.encerraSessaoTodas();
	}

	/**
	 * @param login
	 *            Encerra login aparti.
	 */
	public void encerraSistema(String login) {
		PerfilUsuarioDao perfilDao = DAOFactory.getDaoFactory().getPerfilUsuario();
		int id_perfil = perfilDao.recuperarIdPerfil(login);
		perfilDao.encerrarSessao(id_perfil);
	}

	/**
	 * @param idSessao
	 * @return
	 */
	public String nomeSolicitante(String idSessao) {
		PerfilUsuarioDao perfilDao = DAOFactory.getDaoFactory().getPerfilUsuario();
		return perfilDao.buscaNomeSolicitante(idSessao);
	}

	/**
	 * @param idSessao
	 * @return
	 */
	public int buscaIdPerfil(String idSessao) {
		PerfilUsuarioDao perfilDao = DAOFactory.getDaoFactory().getPerfilUsuario();
		return perfilDao.buscaridPerfil(idSessao);
	}

	/**
	 * @param idSessao
	 * @param idCarona
	 * @param pontos
	 * @param solicitante
	 * @param id_perfil
	 * @return 
	 * As informações serão salvas na tabela <b>solicitacoes</b>.
	 */
	public int salvaPontoEncontro(String idSessao, int idCarona, String pontos, String solicitante, int id_perfil) {
		CaronaDao caronaDao = DAOFactory.getDaoFactory().getCaronaDao();
		return caronaDao.salvaPontoEncontro(idSessao, idCarona, pontos, solicitante, id_perfil);
	}

	/**
	 * @param idSessao
	 * @param idCarona
	 * @param idSolicitacoes
	 * @param pontos
	 * @param idPerfil
	 */
	public void salvaResposta(String idSessao, int idCarona, int idSolicitacoes, String pontos, int idPerfil) {
		CaronaDao caronaDao = DAOFactory.getDaoFactory().getCaronaDao();
		caronaDao.salvaRespostaPontoEncontro(idSessao, idCarona, idSolicitacoes, pontos, idPerfil);
	}

	/**
	 * @param login
	 * @return
	 */
	public PerfilUsuario isIdSessao(String login) {
		PerfilUsuarioDao perfilDao = DAOFactory.getDaoFactory().getPerfilUsuario();
		return perfilDao.buscaDadosPerfil(login);
	}

	/**
	 * @param login
	 */
	public void atualizarEstadoSessao(String login) {
		PerfilUsuarioDao perfilDao = DAOFactory.getDaoFactory().getPerfilUsuario();
		int id_perfil = perfilDao.recuperarIdPerfil(login);
		perfilDao.atualizarSessao(id_perfil);
	}

	/**
	 * @param idSessao
	 * @param idCarona
	 * @param ponto
	 * @param solicitante
	 * @return
	 */
	public int salvaSolicitacaoVaga(String idSessao, int idCarona, String ponto, String solicitante) {
		CaronaDao caronaDao = DAOFactory.getDaoFactory().getCaronaDao();
		return caronaDao.salvaSolicitacaoVagaCarona(idSessao, idCarona, ponto, solicitante);
	}

	/**
	 * @param idSolicitacao
	 * @param atributo
	 * @return
	 */
	public String consultaAtibutoSolicitacao(int idSolicitacao, String atributo) {
		String atributoSolicitacao = "";
		CaronaDao caronaDao = DAOFactory.getDaoFactory().getCaronaDao();
		try {
			if (atributo == null || atributo.isEmpty()) {
				throw new ErroException("Atributo invalido");
			} else if (!atributo.equals("origem") && !atributo.equals("destino") && !atributo.equals("Dono da carona")
					&& !atributo.equals("Ponto de Encontro") && !atributo.equals("Dono da solicitacao")) {
				throw new ErroException("Atributo inexistente");
			} else {
				Carona carona = caronaDao.consultaSolicitacao(idSolicitacao);

				if (atributo.equals("origem")) {
					atributoSolicitacao = carona.getOrigemCarona();
				} else if (atributo.equals("destino")) {
					atributoSolicitacao = carona.getDestinoCarona();
				} else if (atributo.equals("Dono da carona")) {
					atributoSolicitacao = caronaDao.buscaDonoCarona(idSolicitacao);
				} else if (atributo.equals("Ponto de Encontro")) {
					atributoSolicitacao = caronaDao.buscaPontoEncontro(idSolicitacao);
				} else {
					atributoSolicitacao = caronaDao.buscaSolicitante(idSolicitacao);
				}

			}
		} catch (Exception e) {
			System.err.println("Erro, atributo não encontrado " + e.getMessage());
			e.getStackTrace();
		}

		return atributoSolicitacao;
	}

	/**
	 * @param idSolicitacao
	 * @return Retorna o nome do Dono da Carona Motorista
	 */
	public String buscarDonoCarona(int idSolicitacao) {
		CaronaDao caronaDao = DAOFactory.getDaoFactory().getCaronaDao();
		return caronaDao.buscaDonoCarona(idSolicitacao);

	}

	/**
	 * @param idSolicitacao
	 *            decrementa quantidade de vagas da carona
	 * 
	 */
	public void atualizarVagaCarona(int idCarona) {
		CaronaDao caronaDao = DAOFactory.getDaoFactory().getCaronaDao();
		caronaDao.atualizarVagas(idCarona);
	}

	/**
	 * @param idSolicitacao
	 * @return Retorna o id da carona.
	 */
	public int buscaridCarona(int idSolicitacao) {
		CaronaDao caronaDao = DAOFactory.getDaoFactory().getCaronaDao();
		return caronaDao.buscaridCarona(idSolicitacao);
	}

	/**
	 * @param idSolicitacao
	 *            Atualizar o campo aceitou da tabela solicitacaoVagas
	 */
	public void atualizarEstadoSolicitacao(int idSolicitacao) {
		CaronaDao caronaDao = DAOFactory.getDaoFactory().getCaronaDao();
		caronaDao.atualizarEstadoSolicitacao(idSolicitacao);
	}

	/**
	 * @param idSolicitacao
	 * @return retornar o valor do estado atual da soliciação de vaga em uma carona.
	 */
	public String buscarEstadoSolicitacao(int idSolicitacao) {
		CaronaDao caronaDao = DAOFactory.getDaoFactory().getCaronaDao();
		return caronaDao.buscarEstado(idSolicitacao);
	}

	/**
	 * @param idSolicitacao
	 *            Atualizar a sitação da carona para CANCELADA
	 */
	public void atualizarSituacaoSolicitacao(int idSolicitacao) {
		CaronaDao caronaDao = DAOFactory.getDaoFactory().getCaronaDao();
		caronaDao.cancelarSolicitacao(idSolicitacao);
	}

	/**
	 * @param id
	 *            Se a solicitação for aceita e cancelada iremos incrementar o valor
	 *            da vaga na carona.
	 */
	public void encrementaVagaCarona(int idCarona) {
		CaronaDao caronaDao = DAOFactory.getDaoFactory().getCaronaDao();
		caronaDao.encrementaVagaCarona(idCarona);
	}

	/**
	 * @param idSolicitacao
	 * @return Retorna um idSessoa.
	 */
	public String buscarIdSesao(int idSolicitacao) {
		CaronaDao caronaDao = DAOFactory.getDaoFactory().getCaronaDao();

		return caronaDao.buscaridSessao(idSolicitacao);
	}

	/**
	 * @param idSessao
	 * @param idCarona
	 * @param solicitante
	 * @param SITUACAO_SOLICITACAO_VAGA
	 * @return Método solicita vaga em carona cadastrada no sistema, sem sugerir
	 *         ponto de encontro.
	 */
	public int salva_Solicitacao_Vaga_Sem_Sugestao(String idSessao, int idCarona, String solicitante,
			String SITUACAO_SOLICITACAO_VAGA) {
		CaronaDao caronaDao = DAOFactory.getDaoFactory().getCaronaDao();
		return caronaDao.salva_Solicitacao_Vaga_Vem_Sugestao(idSessao, idCarona, solicitante,
				SITUACAO_SOLICITACAO_VAGA);
	}

	/**
	 * @param idSolicitacaoVaga
	 * @param atributo
	 * @return
	 */
	public String consulta_Vaga_Cadastrada_Sem_Sugestao(int idSolicitacaoVaga, String atributo) {
		String dados = "";
		CaronaDao caronaDao = DAOFactory.getDaoFactory().getCaronaDao();
		try {
			if (atributo == null || atributo.isEmpty()) {
				throw new ErroException("Atributo invalido");
			} else if (!atributo.equals("origem") && !atributo.equals("destino") && !atributo.equals("Dono da carona")
					&& !atributo.equals("Dono da solicitacao")) {
				throw new ErroException("Atributo inexistente");
			} else {
				Carona carona = caronaDao.consultaSolicitacaoVagaSemPonto(idSolicitacaoVaga);
				if (atributo.equals("origem")) {
					dados = carona.getOrigemCarona();
				} else if (atributo.equals("destino")) {
					dados = carona.getDestinoCarona();
				} else if (atributo.equals("Dono da carona")) {
					dados = caronaDao.buscaSolicitacaoDonoCarona(idSolicitacaoVaga);
				} else {
					dados = caronaDao.buscaSolicitanteVaga(idSolicitacaoVaga);
				}
			}
		} catch (Exception e) {
			System.err.println("Erro, atributo não encontrado " + e.getMessage());
			e.getStackTrace();
		}

		return dados;
	}

	/**
	 * @param idSolicitacao
	 * @return
	 */
	public String buscaridSessaoDonoCarona(int idSolicitacao) {
		CaronaDao caronaDao = DAOFactory.getDaoFactory().getCaronaDao();
		return caronaDao.buscaridSessaoDonoCarona(idSolicitacao);
	}

	/**
	 * @param idSolicitacao
	 * @return
	 */
	public int capturardCarona(int idSolicitacao) {
		CaronaDao caronaDao = DAOFactory.getDaoFactory().getCaronaDao();
		return caronaDao.buscaridCarona_SemSugestaoPonto(idSolicitacao);
	}

	/**
	 * @param idSolicitacao
	 *            Alterar a situação da carona solicitada para ACEITO
	 */
	public void updateSituacao_SemPonto(int idSolicitacao) {
		CaronaDao caronaDao = DAOFactory.getDaoFactory().getCaronaDao();
		caronaDao.AterarSituacao_SemSugestaoPonto(idSolicitacao);
	}

	/**
	 * @param idSolicitacao
	 * @return capturar o valor da celular situacaoVagaSolicitada na tabela
	 *         idSolicitacao_Vaga_Vem_Sugestao e retorna o valor da celular para o
	 *         método chamdor
	 */
	public String verificar_Solicitacao_Vaga(int idSolicitacao) {
		CaronaDao caronaDao = DAOFactory.getDaoFactory().getCaronaDao();
		return caronaDao.verificar_Solicitacao_Vaga(idSolicitacao);
	}

	/**
	 * @param idSolicitacao
	 *            Alterar a situação da carona solicitada para "REJEITADA".
	 */
	public void rejeita_Solicitacao(int idSolicitacao) {
		CaronaDao caronaDao = DAOFactory.getDaoFactory().getCaronaDao();
		caronaDao.alterar_Sicituacao_Solicitacao(idSolicitacao);

	}

	/**
	 * @param idSessao
	 * @param login
	 * @return Retorna o id do perfil. Visualizar o perfil do usuario.
	 */
	public int visualizarPerfil(String idSessao, String login) {
		PerfilUsuarioDao perfilDao = DAOFactory.getDaoFactory().getPerfilUsuario();
		return perfilDao.visualizarPeril(idSessao, login);
	}

	/**
	 * @param idSessao
	 * @return o login do perfil do usuario selecionado
	 */
	public String buscarLogin(String idSessao) {
		PerfilUsuarioDao perfilDao = DAOFactory.getDaoFactory().getPerfilUsuario();
		return perfilDao.buscarLogin(idSessao);
	}

	/**
	 * @param login
	 * @param atributo
	 * @return
	 */
	public Usuario buscar_dados_Usuario(String login, String atributo) {
		UsuarioDao usuarioDao = DAOFactory.getDaoFactory().getUsuarioDao();
		return usuarioDao.buscarAtributo(login, atributo);
	}

	/**
	 * @param login
	 * @return
	 */
	public String buscar_historico_Caronas(String login) {
		PerfilUsuarioDao perfilDao = DAOFactory.getDaoFactory().getPerfilUsuario();

		return perfilDao.historico_Caronas(login);
	}

	/**
	 * 
	 */
	public void reiniciarSistema() {
		PerfilUsuarioDao perfilDao = DAOFactory.getDaoFactory().getPerfilUsuario();
		perfilDao.reiniciarSistema();

	}

	/**
	 * @param idSessao
	 * @param indexCarona
	 * @return
	 */
	public int getCaronaUsuario(String idSessao, int indexCarona) {
		CaronaDao caronaDao = DAOFactory.getDaoFactory().getCaronaDao();
		return caronaDao.getCaronaUsuario(idSessao, indexCarona);
	}

	/**
	 * @param idSessao
	 * @return
	 * Retorna todas as caronas para um perfil
	 */
	public String getTodasCaronasUsuario(String idSessao) {
		CaronaDao caronaDao = DAOFactory.getDaoFactory().getCaronaDao();
		return caronaDao.getTodasCaronasUsuario(idSessao);
	}

	/**
	 * @param idSessao
	 * @param idCarona
	 * @return
	 * Retorna o id das solicitações confirmadas.
	 */
	public String getSolicitacoesConfirmadas(String idSessao, int idCarona) {
		CaronaDao caronaDao = DAOFactory.getDaoFactory().getCaronaDao();
		return caronaDao.getSolicitacoesConfirmadas(idSessao, idCarona);
	}

	/**
	 * @param idSessao
	 * @param idCarona
	 * @return o id das solicitações Pendentes.
	 */
	public String getSolicitacoesPendentes(String idSessao, int idCarona) {
		CaronaDao caronaDao = DAOFactory.getDaoFactory().getCaronaDao();
		return caronaDao.getSolicitacoesPendentes(idSessao, idCarona);
	}

	/**
	 * @param idSessao
	 * @param idCarona
	 * @return Retonar a resposta do ponto de encontro sugerido.
	 */
	public String getPontosEncontro(String idSessao, int idCarona) {
		CaronaDao caronaDao = DAOFactory.getDaoFactory().getCaronaDao();
		return caronaDao.getPontosEncontro(idSessao, idCarona);
	}

	/**
	 * @param idSessao
	 * @param idCarona
	 * @return
	 */
	public String getPontosSugeridos(String idSessao, int idCarona) {
		CaronaDao caronaDao = DAOFactory.getDaoFactory().getCaronaDao();
		return caronaDao.getPontosSugeridos(idSessao, idCarona);
	}

	/**
	 * @param idSessao
	 * @param idCarona
	 * @param review
	 */
	public void reviewCaronaPresenca(String idSessao, int idCarona, String loginCaroneiro, String review) {
		CaronaDao caronaDao = DAOFactory.getDaoFactory().getCaronaDao();
		caronaDao.reviewCaronaPresenca(idSessao, idCarona, loginCaroneiro, review);
	}


	public String buscar_faltas_caronas(String login) {
		PerfilUsuarioDao perfilDao = DAOFactory.getDaoFactory().getPerfilUsuario();
		int faltas = perfilDao.buscar_faltas_caronas(login);
		return Integer.toString(faltas);
	}

	public String buscar_presenca_caronas(String login) {
		PerfilUsuarioDao perfilDao = DAOFactory.getDaoFactory().getPerfilUsuario();
		int presenca = perfilDao.buscar_presenca_caronas(login);
		return Integer.toString(presenca);
	}

	public String buscar_historico_vagas_caronas(String login) {
		PerfilUsuarioDao perfilDao = DAOFactory.getDaoFactory().getPerfilUsuario();
		return perfilDao.buscar_historico_vagas_caronas(login);
	}

	

}