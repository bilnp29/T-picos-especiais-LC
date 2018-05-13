package com.servicos.perfil;

/**
 * Esta classe irá receber informações e repassa-las para a classe
 * serviçosPerfil.
 * 
 * @author Bruno Miranda / Thassio Lucena
 *
 */
public class ControlePerfil {

	private ServicosPerfil servicosPerfil;

	public ControlePerfil() {
		servicosPerfil = new ServicosPerfil();
	}

	/**
	 * visualizar dados do perfil de um usuário cadastrado
	 * 
	 * @param idSessao
	 *            identificador de uma sessão
	 * @param login
	 *            Atributo utilizado para acessar uma conta cadastrada.
	 * @return Retorna o id do perfil. Visualizar o perfil do usuario.
	 */
	public int visualizarPerfil(String idSessao, String login) {
		return servicosPerfil.visualizarPerfil(idSessao, login);
	}

	/**
	 * Pesquisa informações do perfil de um usuário
	 * 
	 * @param login
	 *            Atributo utilizado para acessar uma conta cadastrada.
	 * @param atributo
	 *            Este parametro pode receber as seguintes informações: nome,
	 *            endereço, email, historico de caronas, historico de vagas em
	 *            caronas,caronas seguras e tranquilas,caronas que não
	 *            funcionaram,faltas em vagas de caronas,presença em vagas de
	 *            caronas. Beseado nesta informação ele irá realizar uma busca no
	 *            banco de dados.
	 * @return Os valore retornados irá depeder da entrada de dados.
	 * 
	 */

	public String getAtributoPerfil(String login, String atributo) {
		return servicosPerfil.gerAtributoPerfil(login, atributo);
	}

	/**
	 * Este método vai chama o método de mesmo nome para informa se existe caronas
	 * cadastrada no sistema que satisfaz os interesse do caroneiro.
	 * 
	 * @param idSessao
	 *            identificador de uma sessão, usuário ativo no sistema
	 * @return retorna as informações sobre uma carona que esta para acontecer
	 */
	public String verificarMensagensPerfil(String idSessao) {

		return servicosPerfil.verificarMensagensPerfil(idSessao);
	}

}
