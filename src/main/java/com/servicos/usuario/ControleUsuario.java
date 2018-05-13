package com.servicos.usuario;

import com.dominio.Usuario;
import com.dominio.dao.SistemaDao;

/**
 * Classe responsável por receber as informações do usuário e repassa para
 * camada de serviço.
 * 
 * @author Bruno Miranda, Thassio Lucena.
 * 
 *
 */
public class ControleUsuario {

	private Usuario usuario;
	private ServicosUsuario servicosUsuario;
	private SistemaDao sistemaDao;

	public ControleUsuario() {
		servicosUsuario = new ServicosUsuario();
		sistemaDao = new SistemaDao();

	}

	/**
	 * O método irá receber os parametros abaixo e repassar para o método
	 * verificaUsuario() que esta presente na classe ServicosUsuario(). Poderá
	 * receber como retorno uma msg de erro ou sucesso ao cadastra o usuario.
	 * 
	 * @param login
	 *            Atributo utilizado para fazer login
	 * @param senha
	 *            Atributo utilizado para fazer login
	 * @param nome
	 *            do usuário
	 * @param endereco
	 *            do usuário
	 * @param email
	 *            do usuário
	 * @param telefone
	 *            do usuário
	 * @return retorna o id cadastrado do usuário
	 * @throws Exception
	 */
	public String cadastraUsuario(String login, String senha, String nome, String endereco, String email,

			String telefone) throws Exception {

		try {
			usuario = new Usuario(login, senha, nome, endereco, email, telefone);
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();
		}

		return servicosUsuario.verificaUsuario(login, usuario);
	}

	/**
	 * Fazer login no sistema
	 * 
	 * @param login
	 *            Atributo utilizado para fazer login
	 * @param senha
	 *            Atributo utilizado para fazer login
	 * @return retorna uma sessão valida
	 * @throws Exception
	 */
	public String entraSistema(String login, String senha) throws Exception {
		return servicosUsuario.validarUsuario(login, senha);
	}

	/**
	 * Pesquisar usuário. Para maior informação consultar métodos adiante.
	 * 
	 * @param atributo
	 *            parametro utilizado para pesquisa dados de um usuário
	 * @param login
	 *            Atributo utilizado para fazer login
	 * @return retorna o dado pesquisado
	 * @throws Exception
	 */
	public String localizarUsuario(String login, String atributo) throws Exception {

		return servicosUsuario.pesquisarUsuario(login, atributo);
	}

	/**
	 * Encerra todos os logins ativos do sistema
	 */
	public void fechaSistema() {

		try {
			servicosUsuario.finalizarSistema();
		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	/**
	 * O método apaga todos os registros salvos na tableas usuario, carona, perfil,
	 * solicitacoes.
	 */
	public void apagarDados() {
		try {
			servicosUsuario.deletarDados();
			sistemaDao.deletarArquivo();
		} catch (Exception e) {

			e.printStackTrace();

		}

	}

	/**
	 * Encerra o sistema apari do login
	 * 
	 * @param login
	 *            parametro utilizado para entra no sistema
	 */
	public void encerraSistemalogin(String login) {
		servicosUsuario.finalizarSistemaComLogin(login);
	}

	/**
	 * reinicia o sistema ativando os logins existentes
	 */
	public void reiniciarSistema() {
		try {
			servicosUsuario.reiniciarSistema();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
