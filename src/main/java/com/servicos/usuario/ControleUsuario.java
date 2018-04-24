package com.servicos.usuario;

import com.dominio.Usuario;
import com.dominio.dao.SistemaDao;

/**
 * @author Bruno Miranda, Thassio Lucena.
 * Classe responsável por receber as informações do usuário e repassa para camada de serviço.
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
	 * verificaUsuario() que esta presente na classe ServicosUsuario(). Poderá receber
	 * como retorno uma msg de erro ou sucesso ao cadastra o usuario.
	 * 
	 * @param login
	 * @param senha
	 * @param nome
	 * @param endereco
	 * @param email
	 * @param telefone
	 * @return
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
	 * @param login
	 * @param senha
	 * @return
	 * @throws Exception
	 */
	public String entraSistema(String login, String senha) throws Exception {	 
		return servicosUsuario.validarUsuario(login, senha);
	}


	/**
	 * @param atributo
	 * @param login
	 * @return
	 * @throws Exception
	 */
	public String localizarUsuario(String login, String atributo) throws Exception {

		return servicosUsuario.pesquisarUsuario(login, atributo);
	}

	/**
	 * @return
	 */
	public void fechaSistema() {
		
		try {
			servicosUsuario.finalizarSistema();
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
	}

	/**
	 * O método apaga todos os registros salvos na tableas usuario, carona, perfil, solicitacoes.
	 */
	public void apagarDados() {
		try {
			servicosUsuario.deletarDados();
			sistemaDao.deletarArquivo();
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}

	}

	public void encerraSistemalogin(String login) {
		servicosUsuario.finalizarSistemaComLogin(login);
	}

	public void reiniciarSistema() {
		try {
			servicosUsuario.reiniciarSistema();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	

}
