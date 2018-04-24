/**
 * 
 */
package com.dominio.dao;

import java.util.Map;

import com.dominio.Usuario;

/**
 * @author Bruno Miranda, Thassio Lucena Interface irá auxiliar no servico de
 *         persistencia de dados.
 *
 */
public interface UsuarioDao {

	/**
	 * O método ira salva um usuario valido.
	 * @param usuario
	 */
	public void salvar(Usuario usuario);

	/**
	 * @param login
	 * @param atributo
	 * @return
	 * 
	 * Busca o nome do usuario ou endereco do mesmo diacordo com o seu login.
	 */
	public Usuario buscarAtributo(String login,String atributo);

	/**
	 * @return
	 * Busca todos os registro da tabela usuario.
	 */
	public Map<String, Usuario> buscarTodosUsuarios();

	/**
	 * Deletar todos os registros na base de dados.
	 */
	public void deletarRegistro();

	/**
	 * @param login
	 * @return
	 */
	public String retornaDados(String login);
	
}