/**
 * 
 */
package com.dominio.dao;

import com.dominio.PerfilUsuario;

/**
 * Interface irá auxiliar no servico de persistencia de dados.
 * 
 * @author Bruno Miranda, Thassio Lucena
 *
 */
public interface PerfilUsuarioDao {

	/**
	 * Salva o paramentro abaixo na base de dados
	 * 
	 * @param perfil
	 *            Objeto perfil, consultar a classe perfil
	 * 
	 * @see PerfilUsuario
	 */
	public void salva(PerfilUsuario perfil);

	/**
	 * Verificar se uma sessão esta ativa.
	 * 
	 * @param idSessao
	 *            identificador de um usuário.
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
	 * Encerra uma sessão baseado no identificador do usuario do usuario
	 * 
	 * @param identificador
	 *            de um perfil.
	 * 
	 */
	public void encerrarSessao(int identificador);

	/**
	 * Recuperar um id do perfil
	 * 
	 * @param login
	 *            Atributo utilizado para acessar uma conta cadastrada.
	 * @return retorna um identificardo do perfil com base no login.
	 */
	public int recuperarIdPerfil(String login);

	/**
	 * Busca o nome do usuario com base do idSessao.
	 * 
	 * @param idSessao
	 *            identificador de um usário
	 * @return retorna o nome do usuário
	 */
	public String buscaNomeSolicitante(String idSessao);

	/**
	 * recupera um identificardo do perfil com base no idSessao.
	 * 
	 * @param idSessao
	 *            identificador de um usário
	 * @return retorna identificardo do perfil.
	 */
	public int buscaridPerfil(String idSessao);

	/**
	 * Verificar se exister idSessao salvo na base de dados perfil
	 * 
	 * @param login
	 *            Atributo utilizado para acessar uma conta cadastrada.
	 * @return retorna um objeto perfil
	 * 
	 */
	public PerfilUsuario buscaDadosPerfil(String login);

	/**
	 * Atualiza o estado da sessao caso esteje falso.
	 * 
	 * @param id_perfil
	 *            identificador de um perdil
	 * 
	 */
	public void atualizarSessao(int id_perfil);

	/**
	 * Buscar o id do perfil para ser visualizado
	 * 
	 * @param idSessao
	 *            identificador de um usário
	 * @param login
	 *            Atributo utilizado para acessar uma conta cadastrada.
	 * @return o id do perfil.
	 */
	public int visualizarPeril(String idSessao, String login);

	/**
	 * Buscar id do usuario
	 * 
	 * @param idSessao
	 *            identificador de um usário
	 * @return o id do usuario.
	 */
	public String buscarLogin(String idSessao);

	/**
	 * Buscar id carona
	 * 
	 * @param login
	 * @return retorna o id das caronas
	 */
	public String historico_Caronas(String login);

	/**
	 * Reiniciar contas cadastradas, ou seja modifica o estadoSessao para 1
	 * verdadeiro.
	 */
	public void reiniciarSistema();

	/**
	 * Buscar número de faltas em uma carona
	 * 
	 * @param login
	 *            Atributo utilizado para acessar uma conta cadastrada.
	 * @return retorna o número de faltas.
	 */
	public int buscar_faltas_caronas(String login);

	/**
	 * Buscar número de presenca em caronas
	 * 
	 * @param login
	 *            Atributo utilizado para acessar uma conta cadastrada.
	 * @return retorna o número de presença
	 */
	public int buscar_presenca_caronas(String login);

	/**
	 * Buscar os ids das solicitações em caronas sem sugestão de ponto de encontro
	 * 
	 * @param login
	 *            Atributo utilizado para acessar uma conta cadastrada.
	 * @return retorna os ids.
	 */
	public String buscar_historico_vagas_caronas(String login);

	/**
	 * Verificar se existe a sessão informada
	 * 
	 * @param idSessao
	 *            identificado de um perfil
	 * @return retorna falso caso a sessão não seja encontrada na tabela
	 *         <b>solicitacao_vaga_sem_sugestao</b>
	 */
	public boolean existeIdSessao(String idSessao);

	/**
	 * Método salva as informações referente ao review do motorista.
	 * 
	 * @param idSessao
	 *            identificador de uma sessão
	 * @param carona
	 *            identificador de uma carona
	 * @param review
	 *            Atributo faz referencia a um comentario de um caroneiro
	 */
	public void review_Motorista(String idSessao, int carona, String review);

	/**
	 * Buscar caronas seguras e tranquilas.
	 *
	 * @return retorna a quantidade de caronas que foram seguras e tranquilas.
	 */
	public String buscar_historico_caronasSeguras();

	/**
	 * Buscar quantidade de caronas que não funcioram
	 * 
	 * @param login
	 *            Atributo utilizado para acessar uma conta cadastrada.
	 * @return retorna a quantidade de caronas que não funcionaram
	 */
	public String buscar_historico_caronas_nao_funcionaram();

	/**
	 * Apaga os registro da tabela review_motorista
	 */
	public void deletarReviewMotorista();

}
