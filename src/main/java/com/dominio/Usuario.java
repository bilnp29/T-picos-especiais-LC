/**
 * 
 */
package com.dominio;

import java.io.Serializable;

/**
 * Classe Usuario nela estão presentes os atributos e os métodos getts, setts.
 * 
 * @author Bruno Miranda / Thassio Lucena.
 * 
 *
 */
public class Usuario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5851746119591282333L;


	/**
	 * Método construtor da classe usuario recebe com parametro os atributos abaixo.
	 * 
	 * @param login
	 *            Guarda o valor de uma strig onde o usuário ira utiliza-lo para
	 *            entra no sistema.
	 * @param senha
	 *            Valor para entra no sistema
	 * @param nome
	 *            Nome do Usuário
	 * @param endereco
	 *            Endereço do usuário
	 * @param email
	 *            Email do usuário
	 * 
	 */
	public Usuario(String login, String senha, String nome, String endereco, String email) {
		this.login = login;
		this.senha = senha;
		this.nome = nome;
		this.endereco = endereco;
		this.email = email;
	}

	public Usuario() {

	}
	
	private int idUsuario;
	private String login;
	private String senha;
	private String nome;
	private String endereco;
	private String email;


	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getSenha() {
		return senha;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

}
