/**
 * 
 */
package com.dominio.dao;

import java.util.Map;

import com.dominio.Usuario;

/**
 * Lucena Interface irá auxiliar no servico de
 *         persistencia de dados.
 * @author Bruno Miranda, Thassio 
 *
 */
public interface UsuarioDao {

	/**
	 * O método ira salva um usuario valido.
	 * @param usuario o atributo em questão é um objeto do tipo usuário
	 * @see
	 * Usuario
	 */
	public void salvar(Usuario usuario);

	/**
	 *  Busca o nome do usuario ou endereco do mesmo diacordo com o seu login.
	 * @param login atributo utilizado para fazer login
	 * @param atributo este atributo recebe dois valor nome ou endereco
	 * @return retorna o valor informado.
	 * 
	 *
	 */
	public Usuario buscarAtributo(String login,String atributo);

	/**
	 * Buscar dados de um usuário
	 * @return retorna uma hasrMap.
	 * 
	 */
	public Map<String, Usuario> buscarTodosUsuarios();

	/**
	 * Deletar todos os registros na base de dados.
	 */
	public void deletarRegistro();

	
	
}