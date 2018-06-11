package com.dominio;

import java.io.Serializable;

/**
 * Classe PerfilUsuario nela estão presentes os atributos e os métodos getts,
 * setts. Será requisitada toda vez que o usuario fizer login.
 * 
 * @author Bruno Miranda / Thassio Lucena.
 *
 */
public class PerfilUsuario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1313350845830606773L;
	private int idPerfil;
	private String identificadorSessao;
	private boolean estadoSessao;
	private int idUsuairo;
	private String login;

	public PerfilUsuario() {

	}

	/**
	 * Método construtor da classe PerfilUsuario recebe com parametro os atributos
	 * abaixo.
	 * 
	 * @param identificadorSessao
	 *            Este atributo identifica o usuário que esta logado no momento.
	 *
	 * @param estadoSessao
	 *            Guarda o valor do estado atual da sessão podendo ser verdadeiro ou
	 *            falso.
	 * @param idUsuario
	 *            Identificador do usuario
	 * 
	 * @param login
	 *            Login de um usuario.
	 * 
	 * */
	public PerfilUsuario(String identificadorSessao, boolean estadoSessao, int idUsuario, String login) {

		this.identificadorSessao = identificadorSessao;
		this.estadoSessao = estadoSessao;
		this.idUsuairo = idUsuario;
		this.login = login;

	}

	public String getIdentificadorSessao() {
		return identificadorSessao;
	}

	public void setIdentificadorSessao(String identificadorSessao) {
		this.identificadorSessao = identificadorSessao;
	}

	public int getIdPerfil() {
		return idPerfil;
	}

	public void setIdPerfil(int idPerfil) {
		this.idPerfil = idPerfil;
	}

	public boolean isEstadoSessao() {
		return estadoSessao;
	}

	public void setEstadoSessao(boolean estadoSessao) {
		this.estadoSessao = estadoSessao;
	}

	public int getIdUsuairo() {
		return idUsuairo;
	}

	public void setIdUsuairo(int idUsuairo) {
		this.idUsuairo = idUsuairo;
	}

	public String getLogin() {
		return login;
	}

}
