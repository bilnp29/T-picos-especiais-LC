package com.dominio.dao;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.dominio.Carona;
import com.dominio.InteresseCarona;
import com.dominio.PerfilUsuario;
import com.dominio.Usuario;
import com.tratamento.erro.ErroException;

/**
 * Classe responsaval por realizar tarefas relacionadas a persistencia de dados.
 * Das classe Usuario(), Carona(), PerfilUsuario().
 * 
 * @author Bruno Miranda Thassio Lucena
 *
 */
public class SistemaDao {

	/**
	 * Inicializando logs da classe <b> SistemaDao </b>
	 */
	final static Logger logger = Logger.getLogger(SistemaDao.class);

	/**
	 * O método<b>guardaUsuario</b> salva as informações dos usuarios cadastrados no
	 * banco de dados shared_carpool.
	 * 
	 * @param usuario
	 *            ele é um objeto Usuário, veja detalhes na classe Usuario
	 * @throws Exception
	 *             Caso o objeto usuario não seja salvo o sistema lança uma
	 *             Exception.
	 * @see Usuario
	 */
	public void guardaUsuario(Usuario usuario) throws Exception {
		logger.info("Inicializando o método guardaUsuario(Usuario usuario)");
		try {

			UsuarioDao usuarioDao = DAOFactory.getDaoFactory().getUsuarioDao();
			usuarioDao.salvar(usuario);
		} catch (Exception e) {
			logger.info("Erro ao salvar usuário", e);
		}
		logger.info("Fim do método");
	}

	/**
	 * O método<b>salvaPerfil</b> salva um perfil valido de usuario cadastrado no
	 * banco de dados shared_carpool.
	 * 
	 * @param perfilUsuario
	 *            este parametro é um objeto da classe PerdfilUsuario
	 * @see PerfilUsuario
	 */
	public void salvarPefil(PerfilUsuario perfilUsuario) {
		logger.info("Inicializando o método salvaPerfil");
		try {

			PerfilUsuarioDao perfilDao = DAOFactory.getDaoFactory().getPerfilUsuario();
			perfilDao.salva(perfilUsuario);
		} catch (Exception e) {
			logger.info("Erro ao salvar o perfil do usuário", e);
		}
		logger.info("Fim do método");
	}

	/**
	 * O método <b>salvarDados</b> salva as informações das caronas cadastradas
	 * pelos usuarios recebendo com paramentro de entrada HashMap dos tipo carona
	 * Carona.
	 * 
	 * @param carona
	 *            este parametro é um objeto da classe Carona
	 * @throws Exception
	 * @see Carona
	 */
	public void salvarDadosCarona(Carona carona, int id) throws Exception {
		logger.info("Inicializando o método salvarDadosCarona ");
		try {
			CaronaDao caronaDao = DAOFactory.getDaoFactory().getCaronaDao();
			caronaDao.salva(carona, id);
		} catch (Exception e) {
			logger.info("Erro ao salvar dados de uma carona", e);
		}
		logger.info("Fim do método");
	}

	/**
	 * O método realizar busca no banco de acordo com os dados passados abaixo. Onde
	 * serão recuparados nome e o endereco do usuario.
	 * 
	 * @param login
	 *            atributo login,utilizado para logar no sistema.
	 * @param atributo
	 *            Este parametro informa o será pesquisado.
	 * @return Pode retorna o nome ou endereco do usuário.
	 * @throws Exception
	 */
	public String buscarPeloAtributo(String login, String atributo) throws Exception {
		logger.info("Inicialiazando a classe.");
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
				logger.info("Atributo não localizado", e);
			}
		}
		logger.info("Fim do método");
		return atributoUsuario;

	}

	/**
	 * Busca todos os usuarios cadastrado no sistema
	 * 
	 * @return retorna uma lista.
	 */
	public Map<String, Usuario> bustaTodos() {
		logger.info("Inicializando o métooo");
		UsuarioDao usuarioDao = DAOFactory.getDaoFactory().getUsuarioDao();
		Map<String, Usuario> usuario = usuarioDao.buscarTodosUsuarios();
		logger.info("Fim do método");
		return usuario;
	}

	/**
	 * Excluir os dados das tabelas perfil, caronas, usuario que estão presenta no
	 * banco.
	 * 
	 **/
	public void deletarArquivo() throws Exception {
		logger.info("Inicializando a classe deletarArquivo");
		CaronaDao caronaDao = DAOFactory.getDaoFactory().getCaronaDao();
		PerfilUsuarioDao perfilDao = DAOFactory.getDaoFactory().getPerfilUsuario();
		UsuarioDao usuarioDao = DAOFactory.getDaoFactory().getUsuarioDao();

		caronaDao.deletarRespostaSolicitacao();

		caronaDao.deletarRegistroSoliciatacao();

		caronaDao.deletarRegistroSolicitacaoVadas();

		caronaDao.deletarRegistroSolicitacaoSemSugestao();

		caronaDao.deletarReview();

		caronaDao.deletarCaronas();

		perfilDao.deletarReviewMotorista();

		perfilDao.deletarPerfil();

		usuarioDao.deletarRegistro();

		logger.info("Fim do método");
	}

	/**
	 * Faz busca de um idSessao valido
	 * 
	 * @param idSessao
	 *            identificar uma sessão do perfil do usuario.
	 * @return retorna o idSessao.
	 */
	public String buscarIdsessao(String idSessao) {
		logger.info("Inicializando a classe (buscarIdsessao");
		PerfilUsuarioDao perfilDao = DAOFactory.getDaoFactory().getPerfilUsuario();
		logger.info("Fim do método");
		return perfilDao.buscarid(idSessao);
	}

	/**
	 * Pesquisa caronas cadastradas
	 * 
	 * @return retorna uma lista das caronas cadastradas que possui vagas no
	 *         sistema.
	 */
	public List<Carona> buscarCarona() {
		logger.info("Inicializando o método");
		CaronaDao caronaDao = DAOFactory.getDaoFactory().getCaronaDao();
		List<Carona> caronas = caronaDao.buscartodas();
		logger.info("Fim do método");
		return caronas;
	}

	/**
	 * Verificar se existe o id especificado.
	 * 
	 * @param idcarona
	 *            identificador de uma carona
	 * @return retorna um valor booleano.
	 * 
	 */
	public boolean isIdCarona(int idcarona) {
		logger.info("Inicializando o método");
		CaronaDao caronaDao = DAOFactory.getDaoFactory().getCaronaDao();
		logger.info("Fim do método");
		return caronaDao.isCaronaId(idcarona);
	}

	/**
	 * Faz busca em uma carona especifica, diacordo com o id da carona, retornando a
	 * origem ou destino ou vaga ou data da mesma
	 * 
	 * @param idcarona
	 *            identificador de uma carona
	 * @param atributo
	 *            Este parametro fornece informações para realizar uma busca.
	 * @return Retorna o atributo desejado.
	 */
	public String buscarAtributoCarona(int idcarona, String atributo) {
		logger.info("Inicializando o método");
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
				logger.info("Atributo não encontrado", e);
			}
		}
		logger.info("Fim do método");
		return atributoCarona;
	}

	/**
	 * Descreve os dados de uma carona cadastrada no sistema.
	 * 
	 * @param idcarona
	 *            identificador de uma carona
	 * @return retorna uma descrição de uma carona cadastrada.
	 */
	public String descricaoCarona(int idcarona) {
		logger.info("Inicializando o método");
		String atributoCarona = "";
		try {
			CaronaDao caronaDao = DAOFactory.getDaoFactory().getCaronaDao();
			Carona carona = caronaDao.buscarCarona(idcarona);
			atributoCarona = carona.getOrigemCarona() + " para " + carona.getDestinoCarona() + ", no dia "
					+ carona.getData() + ", as " + carona.getHora();

		} catch (Exception e) {
			logger.info("Carona não localizada", e);
		}

		logger.info("Fim do método");
		return atributoCarona;
	}

	/**
	 * Buscar trajeto de uma carona
	 * 
	 * @param idcarona
	 *            identificador de uma carona
	 * @return Retorna um trajeto valido de acordo com o id da carona.
	 */
	public String descricaoTrajero(int idcarona) {
		logger.info("Inicializando o método");
		String atributoCarona = "";
		try {
			CaronaDao caronaDao = DAOFactory.getDaoFactory().getCaronaDao();
			Carona carona = caronaDao.buscarCarona(idcarona);
			atributoCarona = carona.getOrigemCarona() + " - " + carona.getDestinoCarona();

		} catch (Exception e) {
			logger.info("Trajeto não localizado", e);
		}
		logger.info("Fim do método");
		return atributoCarona;
	}

	/**
	 * Encerra sessões ativas no sistema.
	 */
	public void fechaSistema() {
		logger.info("Inicializando o método");
		PerfilUsuarioDao perfilDao = DAOFactory.getDaoFactory().getPerfilUsuario();
		perfilDao.encerraSessaoTodas();
		logger.info("Fim do método");
	}

	/**
	 * @param login
	 *            Encerra login aparti.
	 */
	public void encerraSistema(String login) {
		logger.info("Inicializando o método");
		PerfilUsuarioDao perfilDao = DAOFactory.getDaoFactory().getPerfilUsuario();
		int id_perfil = perfilDao.recuperarIdPerfil(login);
		perfilDao.encerrarSessao(id_perfil);
		logger.info("Fim do método");
	}

	/**
	 * Busca o nome do usuario com base do idSessao.
	 * 
	 * @param idSessao
	 *            identificação de uma sessão
	 * @return retorna um nome do solicitante da carona.
	 */
	public String nomeSolicitante(String idSessao) {
		logger.info("Inicializando o método");
		PerfilUsuarioDao perfilDao = DAOFactory.getDaoFactory().getPerfilUsuario();
		logger.info("Fim do método");
		return perfilDao.buscaNomeSolicitante(idSessao);
	}

	/**
	 * recupera um identificardo do perfil com base no idSessao.
	 * 
	 * @param idSessao
	 *            identificação de uma sessão
	 * @return retorna identificardo do perfil.
	 */
	public int buscaIdPerfil(String idSessao) {
		logger.info("Inicializando o método");
		PerfilUsuarioDao perfilDao = DAOFactory.getDaoFactory().getPerfilUsuario();
		logger.info("Fim do método");
		return perfilDao.buscaridPerfil(idSessao);
	}

	/**
	 * As informações serão salvas na tabela <b>solicitacoes</b>.
	 * 
	 * @param idSessao
	 *            identificação de uma sessão
	 * @param idCarona
	 *            identificador de uma carona
	 * @param pontos
	 *            sugestão de pontos de encontro para uma carona
	 * @param solicitante
	 *            nome do solicitante
	 * @param id_perfil
	 *            identificador de um perfil
	 * @return retorna um identificador
	 */
	public int salvaPontoEncontro(String idSessao, int idCarona, String pontos, String solicitante, int id_perfil) {
		logger.info("Inicializando o método");
		CaronaDao caronaDao = DAOFactory.getDaoFactory().getCaronaDao();
		logger.info("Fim do método");
		return caronaDao.salvaPontoEncontro(idSessao, idCarona, pontos, solicitante, id_perfil);
	}

	/**
	 * As informações serão salva no banco. Verificar o método abaixo.
	 * 
	 * @param idSessao
	 *            identificação de uma sessão
	 * @param idCarona
	 *            identificador de uma carona
	 * @param idSolicitacoes
	 *            identificação de uma solicitação de carona com sugestão de pontos
	 *            de encontro
	 * @param pontos
	 *            sugestão de pontos de encontro para uma carona
	 * @param idPerfil
	 *            identificador de um perfil
	 * @see salvaRespostaPontoEncontro
	 */
	public void salvaResposta(String idSessao, int idCarona, int idSolicitacoes, String pontos, int idPerfil) {
		logger.info("Inicializando o método");
		CaronaDao caronaDao = DAOFactory.getDaoFactory().getCaronaDao();
		caronaDao.salvaRespostaPontoEncontro(idSessao, idCarona, idSolicitacoes, pontos, idPerfil);
		logger.info("Fim do método");
	}

	/**
	 * Verificar se existe uma sessão caso sim retorna dados da mesma.
	 * 
	 * @param login
	 *            utilizado para entra no sistema
	 * @return retorna dados de um perfil
	 */
	public PerfilUsuario isIdSessao(String login) {
		logger.info("Inicializando o método");
		PerfilUsuarioDao perfilDao = DAOFactory.getDaoFactory().getPerfilUsuario();
		logger.info("Fim do método");
		return perfilDao.buscaDadosPerfil(login);
	}

	/**
	 * Para atualizar o estado de uma sessão, primeiro recuperamos o id_perfil com o
	 * parametro login e depois chamamos o método <b>atualizarSessao</b> com o
	 * parametro encontrado.
	 * 
	 * @param login
	 *            login utilizado para entra no sistema
	 * @see recuperarIdPerfil, atualizarSessao
	 */
	public void atualizarEstadoSessao(String login) {
		logger.info("Inicializando o método");
		PerfilUsuarioDao perfilDao = DAOFactory.getDaoFactory().getPerfilUsuario();
		int id_perfil = perfilDao.recuperarIdPerfil(login);
		perfilDao.atualizarSessao(id_perfil);
		logger.info("Fim do método");
	}

	/**
	 * Direciona os parametros abaixo para serem salvos. Consultar classe abaixo
	 * 
	 * @param idSessao
	 *            identificação de uma sessão
	 * @param idCarona
	 *            identificador de uma carona
	 * @param ponto
	 *            de encontro
	 * @param solicitante
	 *            nome do caroneiro
	 * @return retorna um id
	 * 
	 * @see salvaSolicitacaoVagaCarona
	 */
	public int salvaSolicitacaoVaga(String idSessao, int idCarona, String ponto, String solicitante) {
		CaronaDao caronaDao = DAOFactory.getDaoFactory().getCaronaDao();
		logger.info("Fim do método");
		return caronaDao.salvaSolicitacaoVagaCarona(idSessao, idCarona, ponto, solicitante);
	}

	/**
	 * pesquisa dados de uma carona.
	 * 
	 * @param idSolicitacao
	 *            identificador de uma solicitação
	 * @param atributo
	 *            este paramentro pode receber alguns valores: Dono da carona,
	 *            destino, origem... entre outros
	 * @return retorna o dado informada na pesquisa
	 */
	public String consultaAtibutoSolicitacao(int idSolicitacao, String atributo) {
		logger.info("Inicializando o método");
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
			logger.info("Atributo não encontrado", e);
		}
		logger.info("Fim do método");
		return atributoSolicitacao;
	}

	/**
	 * Buscar nome do motorista
	 * 
	 * @param idSolicitacao
	 *            identificador de uma solicitação
	 * @return Retorna o nome do Dono da Carona Motorista
	 */
	public String buscarDonoCarona(int idSolicitacao) {
		CaronaDao caronaDao = DAOFactory.getDaoFactory().getCaronaDao();
		logger.info("Fim do método");
		return caronaDao.buscaDonoCarona(idSolicitacao);

	}

	/**
	 * Atualizar vagas de uma carona decrementando o valor da mesma, caso uma carona
	 * seja aceita.
	 * 
	 * @param idSolicitacao
	 *            identificador de uma solicitação decrementa quantidade de vagas da
	 *            carona
	 * 
	 */
	public void atualizarVagaCarona(int idCarona) {
		CaronaDao caronaDao = DAOFactory.getDaoFactory().getCaronaDao();
		caronaDao.atualizarVagas(idCarona);
		logger.info("Fim do método");
	}

	/**
	 * Pesquisa o id da carona em uma solicitação.
	 * 
	 * @param idSolicitacao
	 *            identificador de uma solicitação
	 * @return Retorna o id da carona.
	 */
	public int buscaridCarona(int idSolicitacao) {
		CaronaDao caronaDao = DAOFactory.getDaoFactory().getCaronaDao();
		logger.info("Fim do método");
		return caronaDao.buscaridCarona(idSolicitacao);
	}

	/**
	 * Atualizar o campo estado da tabela solicitacaoVagas onde ira informa se o
	 * motorista aceitou ou não a solicitacao da carona.
	 * 
	 * @param idSolicitacao
	 *            identificador de uma solicitação
	 * 
	 */
	public void atualizarEstadoSolicitacao(int idSolicitacao) {
		CaronaDao caronaDao = DAOFactory.getDaoFactory().getCaronaDao();
		caronaDao.atualizarEstadoSolicitacao(idSolicitacao);
		logger.info("Fim do método");
	}

	/**
	 * buscar estado de um solicitação
	 * 
	 * @param idSolicitacao
	 *            identificador de uma solicitação
	 * @return retornar o valor do estado atual da soliciação de vaga em uma carona.
	 */
	public String buscarEstadoSolicitacao(int idSolicitacao) {
		CaronaDao caronaDao = DAOFactory.getDaoFactory().getCaronaDao();
		logger.info("Fim do método");
		return caronaDao.buscarEstado(idSolicitacao);
	}

	/**
	 * Atualizar a sitação da carona para CANCELADA
	 * 
	 * @param idSolicitacao
	 *            identificador uma solicitação
	 */
	public void atualizarSituacaoSolicitacao(int idSolicitacao) {
		CaronaDao caronaDao = DAOFactory.getDaoFactory().getCaronaDao();
		caronaDao.cancelarSolicitacao(idSolicitacao);
		logger.info("Fim do método");
	}

	/**
	 * Se a solicitação for aceita e cancelada iremos incrementar o valor da vaga na
	 * carona.
	 * 
	 * @param idCarona
	 *            identificador de uma carona
	 * 
	 *            .
	 */
	public void encrementaVagaCarona(int idCarona) {
		logger.info("Inicializando o método");
		CaronaDao caronaDao = DAOFactory.getDaoFactory().getCaronaDao();
		caronaDao.encrementaVagaCarona(idCarona);
		logger.info("Fim do método");
	}

	/**
	 * buscar o id de uma solicitação
	 * 
	 * @param idSolicitacao
	 *            identificador uma solicitação
	 * @return Retorna um idSessoa.
	 */
	public String buscarIdSesao(int idSolicitacao) {
		logger.info("Inicializando o método");
		CaronaDao caronaDao = DAOFactory.getDaoFactory().getCaronaDao();
		logger.info("Fim do método");
		return caronaDao.buscaridSessao(idSolicitacao);
	}

	/**
	 * Método solicita vaga em carona cadastrada no sistema, sem sugerir ponto de
	 * encontro.
	 * 
	 * @param idSessao
	 *            identificação de uma sessão
	 * @param idCarona
	 *            identificador de uma carona
	 * @param solicitante
	 *            nome caroneiro
	 * @param SITUACAO_SOLICITACAO_VAGA
	 *            estado da solicitação
	 * @return retorna um id da tabela <b>solicitacao_vaga_sem_sugestao</b>
	 */
	public int salva_Solicitacao_Vaga_Sem_Sugestao(String idSessao, int idCarona, String solicitante,
			String SITUACAO_SOLICITACAO_VAGA) {
		logger.info("Inicializando o método");
		CaronaDao caronaDao = DAOFactory.getDaoFactory().getCaronaDao();
		logger.info("Fim do método");
		return caronaDao.salva_Solicitacao_Vaga_Vem_Sugestao(idSessao, idCarona, solicitante,
				SITUACAO_SOLICITACAO_VAGA);
	}

	/**
	 * Busca dados de uma solicitação sem sugestão de ponto de encontro
	 * 
	 * @param idSolicitacaoVaga
	 *            identificador de um solicitação
	 * @param atributo
	 *            campo de pesquisa
	 * @return retorna o valor pesquisado
	 */
	public String consulta_Vaga_Cadastrada_Sem_Sugestao(int idSolicitacaoVaga, String atributo) {

		logger.info("Inicializando o método");
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
			logger.info("Atributo não encontrado", e);
		}
		logger.info("Fim do método");
		return dados;
	}

	/**
	 * Pesquisa idSessao.
	 * 
	 * @param idSolicitacao
	 *            identificador de uma solicitação
	 * @return retorna um identificador
	 */
	public String buscaridSessaoDonoCarona(int idSolicitacao) {
		logger.info("Inicializando o método");
		CaronaDao caronaDao = DAOFactory.getDaoFactory().getCaronaDao();
		logger.info("Fim do método");
		return caronaDao.buscaridSessaoDonoCarona(idSolicitacao);
	}

	/**
	 * Busca o identificador da carona para ser utlizado posteriomente.
	 * 
	 * @param idSolicitacao
	 *            identificador de uma solicitação
	 * @return retorna um identificador
	 */
	public int capturardCarona(int idSolicitacao) {
		CaronaDao caronaDao = DAOFactory.getDaoFactory().getCaronaDao();
		logger.info("Fim do método");
		return caronaDao.buscaridCarona_SemSugestaoPonto(idSolicitacao);
	}

	/**
	 * Alterar a situação da carona solicitada para ACEITO
	 * 
	 * @param idSolicitacao
	 *            identificador uma solicitação
	 * 
	 */
	public void updateSituacao_SemPonto(int idSolicitacao) {
		CaronaDao caronaDao = DAOFactory.getDaoFactory().getCaronaDao();
		caronaDao.AterarSituacao_SemSugestaoPonto(idSolicitacao);
		logger.info("Fim do método");
	}

	/**
	 * capturar o valor da celular situacaoVagaSolicitada na tabela
	 * idSolicitacao_Vaga_Vem_Sugestao e retorna o valor da celular para o método
	 * chamdor
	 * 
	 * @param idSolicitacao
	 *            identificador de uma solicitação
	 * @return retorna o valor da situação de uma solicitação
	 */
	public String verificar_Solicitacao_Vaga(int idSolicitacao) {
		CaronaDao caronaDao = DAOFactory.getDaoFactory().getCaronaDao();
		logger.info("Fim do método");
		return caronaDao.verificar_Solicitacao_Vaga(idSolicitacao);

	}

	/**
	 * Alterar a situação da carona solicitada para "REJEITADA".
	 * 
	 * @param idSolicitacao
	 * 
	 */
	public void rejeita_Solicitacao(int idSolicitacao) {
		CaronaDao caronaDao = DAOFactory.getDaoFactory().getCaronaDao();
		caronaDao.alterar_Sicituacao_Solicitacao(idSolicitacao);
		logger.info("Fim do método");

	}

	/**
	 * Visualizar o perfil do usuario
	 * 
	 * @param idSessao
	 *            identificação de uma sessão
	 * @param login
	 *            atributo utilizado para fazer login.
	 * @return .Retorna o id do perfil.
	 */
	public int visualizarPerfil(String idSessao, String login) {
		PerfilUsuarioDao perfilDao = DAOFactory.getDaoFactory().getPerfilUsuario();
		logger.info("Fim do método");
		return perfilDao.visualizarPeril(idSessao, login);
	}

	/**
	 * buscar login
	 * 
	 * @param idSessao
	 *            identificação de uma sessão
	 * @return o login do perfil do usuario selecionado
	 */
	public String buscarLogin(String idSessao) {
		PerfilUsuarioDao perfilDao = DAOFactory.getDaoFactory().getPerfilUsuario();
		logger.info("Fim do método");
		return perfilDao.buscarLogin(idSessao);
	}

	/**
	 * @param login
	 *            atributo utilizado para fazer login.
	 * @param atributo
	 *            campo de pesquisa
	 * @return retorna o atributo pesquisado
	 */
	public Usuario buscar_dados_Usuario(String login, String atributo) {
		UsuarioDao usuarioDao = DAOFactory.getDaoFactory().getUsuarioDao();
		logger.info("Fim do método");
		return usuarioDao.buscarAtributo(login, atributo);

	}

	/**
	 * buscar historico de uma carona
	 * 
	 * @param login
	 *            atributo utilizado para fazer login.
	 * @return retorna os ids de caronas
	 */
	public String buscar_historico_Caronas(String login) {
		PerfilUsuarioDao perfilDao = DAOFactory.getDaoFactory().getPerfilUsuario();
		logger.info("Fim do método");
		return perfilDao.historico_Caronas(login);

	}

	/**
	 * Reiniciar contas cadastradas, ou seja modifica o estadoSessao para 1
	 * verdadeiro.
	 */
	public void reiniciarSistema() {
		PerfilUsuarioDao perfilDao = DAOFactory.getDaoFactory().getPerfilUsuario();
		perfilDao.reiniciarSistema();
		logger.info("Fim do método");

	}

	/**
	 * buscar todas as caronas
	 * 
	 * @param idSessao
	 *            identificação de uma sessão
	 * @return Retorna todas as caronas para um perfil
	 */
	public String getTodasCaronasUsuario(String idSessao) {
		CaronaDao caronaDao = DAOFactory.getDaoFactory().getCaronaDao();
		return caronaDao.getTodasCaronasUsuario(idSessao);
	}

	/**
	 * Pesquisa as solicitações de uma carona.
	 * 
	 * @param idSessao
	 *            identificação de uma sessão
	 * @param idCarona
	 *            identificador de uma carona
	 * @return Retorna o id das solicitações confirmadas.
	 */
	public String getSolicitacoesConfirmadas(String idSessao, int idCarona) {
		CaronaDao caronaDao = DAOFactory.getDaoFactory().getCaronaDao();
		logger.info("Fim do método");
		return caronaDao.getSolicitacoesConfirmadas(idSessao, idCarona);
	}

	/**
	 * Pesquisa as solicitações pendentes de uma carona.
	 * 
	 * @param idSessao
	 *            identificação de uma sessão
	 * @param idCarona
	 *            identificador de uma carona
	 * @return o id das solicitações Pendentes.
	 */
	public String getSolicitacoesPendentes(String idSessao, int idCarona) {
		CaronaDao caronaDao = DAOFactory.getDaoFactory().getCaronaDao();
		logger.info("Fim do método");
		return caronaDao.getSolicitacoesPendentes(idSessao, idCarona);
	}

	/**
	 * pesquisa os pontos de encotro de uma carona.
	 * 
	 * @param idSessao
	 *            identificação de uma sessão
	 * @param idCarona
	 *            identificador de uma carona
	 * @return Retonar a resposta do ponto de encontro sugerido.
	 */
	public String getPontosEncontro(String idSessao, int idCarona) {
		CaronaDao caronaDao = DAOFactory.getDaoFactory().getCaronaDao();
		logger.info("Fim do método");
		return caronaDao.getPontosEncontro(idSessao, idCarona);
	}

	/**
	 * Buscar pontos sugeridos de um carona
	 * 
	 * @param idSessao
	 *            identificação de uma sessão
	 * @param idCarona
	 *            identificador de uma carona
	 * @return retorna os pontos sugerido de uma carona
	 * 
	 * @see getPontosSugeridos
	 */
	public String getPontosSugeridos(String idSessao, int idCarona) {
		CaronaDao caronaDao = DAOFactory.getDaoFactory().getCaronaDao();
		logger.info("Fim do método");
		return caronaDao.getPontosSugeridos(idSessao, idCarona);
	}

	/**
	 * Este método vai direcionar as informações abaixo para o método
	 * <b>reviewCaronaPresenca</b> salva as informações no banco.
	 * 
	 * @param idSessao
	 *            identificador de uma sessão
	 * @param idCarona
	 *            identificador de uma carona
	 * @param review
	 *            atributo utilizado para fazer uma pesquisa.
	 * @param loginCaroneiro
	 *            login utilizado pra entrar no sistema
	 * 
	 * @see reviewCaronaPresenca
	 */
	public void reviewCaronaPresenca(String idSessao, int idCarona, String loginCaroneiro, String review) {
		CaronaDao caronaDao = DAOFactory.getDaoFactory().getCaronaDao();
		caronaDao.reviewCaronaPresenca(idSessao, idCarona, loginCaroneiro, review);
		logger.info("Fim do método");
	}

	/**
	 * buscar o número de falta em um a carona
	 * 
	 * @param login
	 *            utilizado pra entrar no sistema
	 * @return retorna o número de faltas
	 */
	public String buscar_faltas_caronas(String login) {
		PerfilUsuarioDao perfilDao = DAOFactory.getDaoFactory().getPerfilUsuario();
		int faltas = perfilDao.buscar_faltas_caronas(login);
		logger.info("Fim do método");
		return Integer.toString(faltas);
	}

	/**
	 * Buscar presença em caronas cadastradas
	 * 
	 * @param login
	 *            utilizado pra entrar no sistema
	 * @return retorna um id
	 */
	public String buscar_presenca_caronas(String login) {
		PerfilUsuarioDao perfilDao = DAOFactory.getDaoFactory().getPerfilUsuario();
		int presenca = perfilDao.buscar_presenca_caronas(login);
		logger.info("Fim do método");
		return Integer.toString(presenca);
	}

	/**
	 * Pesquisar historico de vagas em uma carona
	 * 
	 * @param login
	 *            utilizado pra entrar no sistema
	 * @return Retorna o historico de vagas
	 */
	public String buscar_historico_vagas_caronas(String login) {
		logger.info("Inicializando método, String buscar_historico_vagas");
		PerfilUsuarioDao perfilDao = DAOFactory.getDaoFactory().getPerfilUsuario();
		logger.info("Fim do método");
		return perfilDao.buscar_historico_vagas_caronas(login);
	}

	/**
	 * Verificar se existe a sessão informada
	 * 
	 * @param idSessao
	 *            identificado de um perfil
	 * @return retorna falso caso a sessão não seja encontrada na tabela
	 *         <b>solicitacao_vaga_sem_sugestao</b>
	 */
	public boolean existeIdSessao(String idSessao) {
		logger.info("Inicializando o método existeIdSessao(String idSessao) ");
		PerfilUsuarioDao perfilDao = DAOFactory.getDaoFactory().getPerfilUsuario();
		logger.info("Fim do método");
		return perfilDao.existeIdSessao(idSessao);
	}

	/**
	 * Este método irá direcionar as informações para as classe que irá salva as
	 * informações abaixo.
	 * 
	 * @param idSessao
	 *            identificador de uma sessão
	 * @param carona
	 *            identificador de uma carona
	 * @param review
	 *            Atributo faz referencia a um comentario de um caroneiro
	 */
	public void review_Motorista(String idSessao, int carona, String review) {
		logger.info("Inicializando o método buscar_historico_caronas_nao_funcionaram() ");
		PerfilUsuarioDao perfilDao = DAOFactory.getDaoFactory().getPerfilUsuario();
		perfilDao.review_Motorista(idSessao, carona, review);
		logger.info("Fim do método");
	}

	/**
	 * Buscar quantidade de caronas seguras e tranquilas.
	 * 
	 * @return retorna a quantidade de caronas que foram seguras e tranquilas.
	 */
	public String buscar_historico_caronasSeguras_tranquilas() {
		logger.info("Inicializando o método buscar_historico_caronas_nao_funcionaram() ");
		PerfilUsuarioDao perfilDao = DAOFactory.getDaoFactory().getPerfilUsuario();
		logger.info("Fim do método");
		return perfilDao.buscar_historico_caronasSeguras();
	}

	/**
	 * Buscar quantidade de caronas que não funcioram
	 * 
	 * @return retorna a quantidade de caronas que não funcionaram
	 */
	public String buscar_historico_caronas_nao_funcionaram() {
		logger.info("Inicializando o método buscar_historico_caronas_nao_funcionaram() ");
		PerfilUsuarioDao perfilDao = DAOFactory.getDaoFactory().getPerfilUsuario();
		logger.info("Fim do método");
		return perfilDao.buscar_historico_caronas_nao_funcionaram();
	}

	/**
	 * Método de pesquisa que verificar se a carona é do municipio ou
	 * intermunicipal.
	 * 
	 * @param idCarona
	 *            identificador de uma carona
	 * @param atributo
	 *            é uma informação valida inserida pelo usuario, neste caso o valor
	 *            do atriburo é: ehmunicipio.
	 * @return retorna um valor booleano. Verdadeiro para valor encontrado falso
	 *         caso contrario.
	 */
	public boolean getAtributoCaronaMunicipal(int idCarona, String atributo) {
		logger.info("Inicializando o método getAtributoCaronaMunicipal ");
		CaronaDao caronaDao = DAOFactory.getDaoFactory().getCaronaDao();
		logger.info("Fim do método");
		return caronaDao.getAtributoCaronaMunicipal(idCarona, atributo);
	}

	/**
	 * O método irá buscar o id da carona com base nos parametros abaixo.
	 * 
	 * @param idSessao
	 *            Identificador de uma sessão ativa de um usuário
	 * @param cidade
	 *            Local onde a carona vai acontecer <b>(parametro obrigatorio)</b>
	 * @return O retorno será uma lista de caronas do tipo municipal cadastrada para
	 *         a pesquisa.
	 */
	public String buscarCaronaMunicipio(String idSessao, String cidade) {
		logger.info("Inicializando o método buscarCaronaMunicipio ");
		CaronaDao caronaDao = DAOFactory.getDaoFactory().getCaronaDao();
		logger.info("Fim do método");
		return caronaDao.buscarCaronaMunicipio(idSessao, cidade);
	}

	/**
	 * 
	 * O método irá buscar o id da carona com base nos parametros abaixo.
	 * 
	 * @param idSessao
	 *            Identificador de uma sessão ativa de um usuário
	 * @param cidade
	 *            Local onde a carona vai acontecer <b>(parametro obrigatorio)</b>
	 * @param origem
	 *            partida da carona
	 * @param destino
	 *            chegada da carona
	 * @return O retorno será uma lista de caronas do tipo municipal cadastrada para
	 *         a pesquisa.
	 */
	public String buscarCarona_Municipio_id(String idSessao, String cidade, String origem, String destino) {
		logger.info("Inicializando o método buscarCarona_Municipio_id.");
		CaronaDao caronaDao = DAOFactory.getDaoFactory().getCaronaDao();
		logger.info("Fim do método");
		return caronaDao.buscarCarona_Municipio_id(idSessao, cidade, origem, destino);
	}

	/**
	 * Repassa os atributos para o método cadastrarInteresse da interface CaronaDao
	 * onde serão direcioando para a classe que implementa o método.
	 * 
	 * @param idSessao
	 *            identificador de uma sessao
	 * @param origem
	 *            partida de uma carona
	 * @param destino
	 *            chegada da carona
	 * @param data
	 *            data de saída
	 * @param horaInicio
	 *            hora da saída
	 * @param horaFim
	 *            hora da chegada
	 * @return retorna o identificador da carona interessada.
	 * @see CaronaDao
	 */
	public int cadastrarInteresse(String idSessao, String origem, String destino, String data, String horaInicio,
			String horaFim) {
		logger.info("Iniciando método");
		CaronaDao caronaDao = DAOFactory.getDaoFactory().getCaronaDao();
		return caronaDao.cadastrarInteresse(idSessao, origem, destino, data, horaInicio, horaFim);
	}

	/**
	 * @param idSessao identificador de uma sessão
	 * @return retorna um objeto da classe InteresseCarona
	 */
	public InteresseCarona buscarCaronasInteressadas(String idSessao) {
		logger.info("Iniciando método");
		PerfilUsuarioDao perfilDao = DAOFactory.getDaoFactory().getPerfilUsuario();
		return perfilDao.buscarIntersseCarona(idSessao);
	}

	/**
	 * 
	 * Buscar informações de um carona
	 * @param interesseCaronas objeto caronas interessadas
	 * @return retorna o id de uma carona a data e hora da mesma.
	 */
	public Carona buscar_dadosCarona(InteresseCarona interesseCaronas) {
		logger.info("Iniciando método");
		CaronaDao caronaDao = DAOFactory.getDaoFactory().getCaronaDao();
		return caronaDao.buscar_dadosCarona(interesseCaronas);
	}

	/**Este método busca o email do usuaro.
	 * @param carona objeto carona
	 * @return retorna um email do usuario
	 */
	public String buscarEmailUsuario(Carona carona) {
		logger.info("Iniciaindo método");
		UsuarioDao usuarioDao = DAOFactory.getDaoFactory().getUsuarioDao();
		return usuarioDao.buscarEmailUsuario(carona);
	}

}