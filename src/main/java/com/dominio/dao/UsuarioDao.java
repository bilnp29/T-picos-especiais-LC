/**
 * 
 */
package com.dominio.dao;

import java.util.Map;

import com.dominio.Carona;
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

	/**
	 * buscar emial
	 * @param carona Objeto da classe carona
	 * @return retorna email de um usuário.
	 */
	public String buscarEmailUsuario(Carona carona);

	/**
	 * buscar email do solicitante.
	 * @param idSolicitacao identificador de um solicitação para uma carona
	 * @return retorna o emial do solicitante.
	 */
	public String emailUsuario(int idSolicitacao);

	/**
	 * Apaga os registro da tabela usuário preferencial
	 */
	public void deletarUsuarioPreferencial();

	/**
	 * @param sessao identificador de uma sessao de usuairo valida
	 * @return retorna um objeto do tipo usuario
	 */
	public Usuario buscarUsuario(String sessao);

	
	/**
	 * @param id identificador de uma pessoa
	 * @param pessoa objeto do tipo usuario.
	 */
	public void editarUsuario(int id, String nome, String login, String email, String endereco, String senha);

	
	
}