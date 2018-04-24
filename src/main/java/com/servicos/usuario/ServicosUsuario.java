package com.servicos.usuario;

import java.util.Map;

import com.dominio.PerfilUsuario;
import com.dominio.Usuario;
import com.dominio.dao.SistemaDao;

/**
 * @author Bruno Miranda Thassio Lucena
 * 
 *         Classe responsalve por execuata criar um usuario, validar o mesmo,
 *         criar um perfil, e abrir uma sessao valida.
 *
 */
public class ServicosUsuario {

	private SistemaDao sistemaDao;
	private static boolean ESTADO_SESSAO;
	private static String ID_SESSAO;
	private PerfilUsuario perfilUsuario;

	public ServicosUsuario() {
		sistemaDao = new SistemaDao();
		ESTADO_SESSAO = false;
		ID_SESSAO = null;
	}

	/**
	 * @param login
	 * @param usuario
	 * @throws Exception
	 *             Faz uma verificação nos dados passado login,nome e email, antes
	 *             de salva o usuario.
	 */
	public String verificaUsuario(String login, Usuario usuario) throws Exception {
		if ((usuario.getLogin() == null) || (usuario.getLogin().equals(""))) {
			throw new Exception("Login invalido");

		} else if ((usuario.getNome() == null) || (usuario.getNome().equals(""))) {
			throw new Exception("Nome invalido");

		} else if ((usuario.getEmail() == null) || (usuario.getEmail().equals(""))) {
			throw new Exception("Email invalido");

		} else {
			adicionarUsuario(login, usuario);
		}
		return login;
	}

	/**
	 * @param login
	 * @param usuario
	 * @throws Exception
	 *             Adiciona um usuario valido no banco de dados chamando o método
	 *             guarda usuario.
	 */
	public void adicionarUsuario(String login, Usuario usuario) throws Exception {
		Map<String, Usuario> dados = sistemaDao.bustaTodos();
		if (dados.containsKey(login)) {
			throw new Exception("Já existe um usuário com este login");
		}
		for (Usuario user : dados.values()) {
			if (user.getEmail().equals(usuario.getEmail())) {
				throw new Exception("Já existe um usuário com este email");
			}
		}
		sistemaDao.guardaUsuario(usuario);

	}

	/**
	 * @param login
	 * @param senha
	 * @throws Exception
	 *             Verificar se a senha informada é a mesma para o login cadastrado.
	 */
	private void validaSenha(String login, String senha) throws Exception {
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
			throw new Exception("Login invalido");
		}
	}

	/**
	 * @param login
	 * @throws Exception
	 *             Verificar se o login é nulo ou vazio, e se o login informado
	 *             existe.
	 */
	public void validarLogin(String login) throws Exception {
		Map<String, Usuario> dados = sistemaDao.bustaTodos();
		if (login == null || login.equals("")) {
			throw new Exception("Login invalido");
		}
		if (!dados.containsKey(login)) {
			throw new Exception("Usuario inexistente");
		}
	}

	/**
	 * @param login
	 * @param senha
	 * @throws Exception
	 *             Verificar login e senha, caso sejam validos o usuario vai loga.E
	 *             cria-se um perfil usuario. Antes disso verifica-se o perfil já existe.
	 */
	public String validarUsuario(String login, String senha) throws Exception {

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
		return ID_SESSAO;

	}

	/**
	 * @param login
	 * @return
	 * 
	 * 		Captura um id de um usuario valido e repassa para método
	 *         validarUsuairo.
	 */
	private int buscarIdUsuario(String login) {
		int id_Usuario = 0;
		Map<String, Usuario> dados = sistemaDao.bustaTodos();
		for (Usuario user : dados.values()) {
			if (user.getLogin().equals(login)) {
				id_Usuario = user.getIdUsuario();
				break;
			}

		}
		return id_Usuario;

	}

	/**
	 * @param login
	 * @param atributo
	 * @throws Exception
	 *             Realizar uma pesquisa deacordo com o parametro atributo passado
	 *             podendo ser o nome do usuario ou seu endereço.
	 * 
	 */
	public String pesquisarUsuario(String login, String atributo) throws Exception {
		String dado = "";
		validarLogin(login);
		dado = sistemaDao.buscarPeloAtributo(login, atributo);
		return dado;
	}

	/**
	 * @throws Exception
	 *             Finalizar sessões ativas.
	 */
	public void finalizarSistema() throws Exception {
		if (ESTADO_SESSAO != false) {
			ESTADO_SESSAO = false;
			sistemaDao.fechaSistema();
		} else {
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
		Map<String, Usuario> dados = sistemaDao.bustaTodos();
		if ((dados.values() != null)) {
			dados.clear();
			ID_SESSAO = null;
		} else {
			throw new Exception("Repositorio de usuario vazio");

		}
	}

	/**
	 * @param login
	 *            Encerra o sistema com base no login do usuario.
	 */
	public void finalizarSistemaComLogin(String login) {
		sistemaDao.encerraSistema(login);

	}

	/**
	 * Reinicia o sistema
	 */
	public void reiniciarSistema() {
		sistemaDao.reiniciarSistema();
		
	}

}