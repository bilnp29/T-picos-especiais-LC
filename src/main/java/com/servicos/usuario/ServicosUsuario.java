package com.servicos.usuario;

import java.util.Map;

import org.apache.log4j.Logger;

import com.dominio.PerfilUsuario;
import com.dominio.Usuario;
import com.dominio.dao.SistemaDao;

/**
 * Classe responsalve por execuata criar um usuario, validar o mesmo, criar um
 * perfil, e abrir uma sessao valida.
 * 
 * @author Bruno Miranda Thassio Lucena
 * 
 */
public class ServicosUsuario {

	private SistemaDao sistemaDao;
	private static boolean ESTADO_SESSAO;
	private static String ID_SESSAO;
	private PerfilUsuario perfilUsuario;
	final static Logger logger = Logger.getLogger(ServicosUsuario.class);

	public ServicosUsuario() {
		sistemaDao = new SistemaDao();
		ESTADO_SESSAO = false;
		ID_SESSAO = null;
	}

	/**
	 * Faz uma verificação nos dados passado login,nome e email, antes de salva o
	 * usuario.
	 * 
	 * @param login
	 *            de usário
	 * @param usuario
	 *            Objeto usuário que apresenta dados de um usuario.
	 * @throws Exception
	 * @see Usuario
	 * 
	 */
	public String verificaUsuario(String login, Usuario usuario) throws Exception {
		logger.info("Iniciadndo método");
		if ((usuario.getLogin() == null) || (usuario.getLogin().equals(""))) {
			logger.error("Login invalido");
			throw new Exception("Login invalido");

		} else if ((usuario.getNome() == null) || (usuario.getNome().equals(""))) {
			logger.error("Nome invalido");
			throw new Exception("Nome invalido");

		} else if ((usuario.getEmail() == null) || (usuario.getEmail().equals(""))) {
			logger.error("Email invalido");
			throw new Exception("Email invalido");

		} else {
			adicionarUsuario(login, usuario);
		}
		logger.info("fim método");
		return login;
	}

	/**
	 * Adiciona um usuario valido no banco de dados chamando o método guarda
	 * usuario.
	 * 
	 * @param login
	 *            atributo utilizado para fazer login
	 * @param usuario
	 *            Objeto usuário que apresenta dados de um usuario.
	 * @throws Exception
	 * @see Usuario
	 */
	public void adicionarUsuario(String login, Usuario usuario) throws Exception {
		logger.info("Iniciadndo método");
		Map<String, Usuario> dados = sistemaDao.bustaTodos();
		if (dados.containsKey(login)) {
			logger.error("Já existe um usuário com este login");
			throw new Exception("Já existe um usuário com este login");
		}
		for (Usuario user : dados.values()) {
			if (user.getEmail().equals(usuario.getEmail())) {
				logger.error("Já existe um usuário com este email");
				throw new Exception("Já existe um usuário com este email");
			}
		}
		sistemaDao.guardaUsuario(usuario);
		logger.info("fim do método");
	}

	/**
	 * Verificar se a senha informada é a mesma para o login cadastrado.
	 * 
	 * @param login
	 *            atributo utilizado para fazer login
	 * @param senha
	 *            atributo utilizado para fazer login
	 * @throws Exception
	 * 
	 */
	private void validaSenha(String login, String senha) throws Exception {
		logger.info("Iniciadndo método");
		boolean validacao = false;
		Map<String, Usuario> dados = sistemaDao.bustaTodos();
		if (dados.containsKey(login)) {
			for (Usuario user : dados.values()) {
				if (user.getSenha().equals(senha)) {
					validacao = true;
					break;
				}
			}
		}
		if (!validacao) {
			logger.error("Login invalido");
			throw new Exception("Login invalido");

		}
	}

	/**
	 * Verificar se o login é nulo ou vazio, e se o login informado existe.
	 * 
	 * @param login
	 *            atributo utilizado para fazer login
	 * @throws Exception
	 * 
	 */
	public void validarLogin(String login) throws Exception {
		logger.info("Iniciadndo método");
		Map<String, Usuario> dados = sistemaDao.bustaTodos();
		if (login == null || login.equals("")) {
			logger.error("Login invalido");
			throw new Exception("Login invalido");
		}
		if (!dados.containsKey(login)) {
			logger.error("Usuario inexistente");
			throw new Exception("Usuario inexistente");
		}
	}

	/**
	 * Verificar login e senha, caso sejam validos o usuario vai loga.E cria-se um
	 * perfil usuario. Antes disso verifica-se o perfil já existe.
	 * 
	 * @param login
	 *            atributo utilizado para fazer login
	 * @param senha
	 *            atributo utilizado para fazer login
	 * @throws Exception
	 * 
	 */
	public String validarUsuario(String login, String senha) throws Exception {
		logger.info("Iniciadndo método");

		validarLogin(login);
		validaSenha(login, senha);
		int id_Usuario = buscarIdUsuario(login);
		ESTADO_SESSAO = true;
		ID_SESSAO = "sessao" + login.substring(0, 1).toUpperCase() + login.substring(1, login.length());

		PerfilUsuario perfil = sistemaDao.isIdSessao(login);
		if (perfil != null) {
			if (ID_SESSAO.equals(perfil.getIdentificadorSessao())) {
				if (perfil.isEstadoSessao()) {
					return ID_SESSAO;
				} else {
					sistemaDao.atualizarEstadoSessao(login);
				}
			}
		} else {

			perfilUsuario = new PerfilUsuario(ID_SESSAO, ESTADO_SESSAO, id_Usuario, login);
			sistemaDao.salvarPefil(perfilUsuario);
		}
		logger.info("fim método");
		return ID_SESSAO;

	}

	/**
	 * Captura um id de um usuario valido e repassa para método validarUsuairo.
	 * 
	 * @param login
	 *            atributo utilizado para fazer login
	 * @return retorna o id usuario
	 * 
	 * 
	 */
	private int buscarIdUsuario(String login) {
		logger.info("Iniciadndo método");
		int id_Usuario = 0;
		Map<String, Usuario> dados = sistemaDao.bustaTodos();
		for (Usuario user : dados.values()) {
			if (user.getLogin().equals(login)) {
				id_Usuario = user.getIdUsuario();
				break;
			}

		}
		logger.info("fim do método");
		return id_Usuario;

	}

	/**
	 * Realizar uma pesquisa de acordo com o parametro atributo passado podendo ser o
	 * nome do usuario ou seu endereço.
	 * 
	 * @param login
	 *            atributo utilizado para fazer login
	 * @param atributo
	 *            parametro de pesquisa valor será informado pelo usuário
	 * @throws Exception
	 * 
	 * 
	 */
	public String pesquisarUsuario(String login, String atributo) throws Exception {
		logger.info("Iniciadndo método");
		String dado = "";
		validarLogin(login);
		dado = sistemaDao.buscarPeloAtributo(login, atributo);
		logger.info("fim do método");
		return dado;
	}

	/**
	 * Finalizar sessões ativas.
	 * 
	 * @throws Exception
	 * 
	 */
	public void finalizarSistema() throws Exception {
		logger.info("Iniciadndo método");
		if (ESTADO_SESSAO != false) {
			ESTADO_SESSAO = false;
			sistemaDao.fechaSistema();
		} else {
			logger.error("Falha ao encerra o sistema");
			throw new Exception("Falha ao encerra o sistema");
		}
	}

	/**
	 * O método <b>deletarDados()</b> verificar se o repositorio pessoa contem
	 * dados. Caso o apresenta, os mesmo serão apagados, caso contrario apresentara
	 * uma menssagem de erro.
	 * 
	 * @throws Exception
	 *
	 */
	public void deletarDados() throws Exception {
		logger.info("Iniciadndo método");
		Map<String, Usuario> dados = sistemaDao.bustaTodos();
		if ((dados.values() != null)) {
			dados.clear();
			ID_SESSAO = null;
		} else {
			logger.error("Repositorio de usuario vazio");
			throw new Exception("Repositorio de usuario vazio");

		}
	}

	/**
	 * Encerra o sistema com base no login do usuario.
	 * 
	 * @param login
	 *            atributo utilizado para entra no sistema
	 * 
	 */
	public void finalizarSistemaComLogin(String login) {
		logger.info("Iniciadndo método");
		sistemaDao.encerraSistema(login);

	}

	/**
	 * Reinicia o sistema
	 */
	public void reiniciarSistema() {
		logger.info("Iniciadndo método");
		sistemaDao.reiniciarSistema();

	}

	public Usuario buscarUsuario(String sessao) {
		logger.info("Iniciadndo método");
		return sistemaDao.buscarUsuario(sessao);
	}

	/**
	 * Atualizar dados do usuario
	 * @param nome Nome do usuário
	 * @param login login para acessaoa contra
	 * @param email Email do usuario
	 * @param endereco do usasuário
	 * @param senha senha para entra no sistema
	 */
	public void editarUsuario(int id,String nome, String login, String email, String endereco, String senha) {
		logger.info("Iniciadndo método");	
		sistemaDao.EditarUsuario(id,nome,login,email,endereco,senha);
	}


}