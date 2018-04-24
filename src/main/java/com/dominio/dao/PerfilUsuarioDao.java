/**
 * 
 */
package com.dominio.dao;

import com.dominio.PerfilUsuario;

/**
 * @author Bruno Miranda, Thassio Lucena Interface irá auxiliar no servico de
 *         persistencia de dados.
 *
 */
public interface PerfilUsuarioDao {

	/**
	 * Salva o paramentro abaixo na base de dados
	 * @param perfil
	 */
	public void salva(PerfilUsuario perfil);

	/**
	 * Verificar se uma sessão esta ativa.
	 * @param idSessao 
	 */
	public String buscarid(String idSessao); 

	/**
	 * finaliza uma sessao ativa.
	 */
	public void encerraSessaoTodas();

	/**
	 * Excluir todos os dados da tabela perfil.
	 */
	public void deletarPerfil();

	/**
	 * @param identificador
	 *  Encerra uma sessão baseado no identificador do usuario do usuario
	 */
	public void encerrarSessao(int identificador);

	/**
	 * @param login
	 * @return
	 * recupera um identificardo do perfil com base no login.
	 */
	public int recuperarIdPerfil(String login);

	/**
	 * @param idSessao
	 * @return
	 * Busca o nome do usuario com base do idSessao.
	 */
	public String buscaNomeSolicitante(String idSessao);

	/**
	 * @param idSessao
	 * @return
	 * recupera um identificardo do perfil com base no idSessao.
	 */
	public int buscaridPerfil(String idSessao);

	/**
	 * @param login
	 * @return
	 * Verificar se exister idSessao salvo na base de dados perfil
	 * 
	 */
	public PerfilUsuario buscaDadosPerfil(String login);

	/**
	 * @param id_perfil atualiza o estado da sessao caso esteje falso.
	 */
	public void atualizarSessao(int id_perfil);

	/**
	 * @param idSessao
	 * @param login
	 * @return o id do perfil
	 * Visualizar o perfil do usuario.
	 */
	public int visualizarPeril(String idSessao, String login);

	/**
	 * @param idSessao
	 * @return o id do usuario para comparar com o id passado na função do método.
	 */
	public String buscarLogin(String idSessao);

	

	/**
	 * @param login
	 * @return Vai retorna o id das caronas
	 */
	public String historico_Caronas(String login);

	/**
	 * Reiniciar contas cadastradas, ou seja modifica o estadoSessao para 1 verdadeiro.
	 */
	public void reiniciarSistema();

	/**
	 * @param login
	 * @return
	 */
	public int buscar_faltas_caronas(String login);

	/**
	 * @param login
	 * @return
	 */
	public int buscar_presenca_caronas(String login);

	/**
	 * @param login
	 * @return
	 */
	public String buscar_historico_vagas_caronas(String login);

}
