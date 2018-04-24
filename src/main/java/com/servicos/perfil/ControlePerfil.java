package com.servicos.perfil;

public class ControlePerfil {

	private ServicosPerfil servicosPerfil;

	public ControlePerfil() {
		servicosPerfil = new ServicosPerfil();
	}

	/**
	 * @param idSessao
	 * @param login
	 * @return Retorna o id do perfil. Visualizar o perfil do usuario.
	 */
	public int visualizarPerfil(String idSessao, String login) {
		return servicosPerfil.visualizarPerfil(idSessao, login);
	}

	/**
	 * @param login
	 * @param atributo
	 * @return Os atributos pesquisados nome, endereço, email, historico de caronas,
	 *         historico de vagas em caronas,caronas seguras e tranquilas,caronas
	 *         que não funcionaram,faltas em vagas de caronas,presença em vagas de
	 *         caronas
	 * 
	 *         Método de pesquisa que retorna os valores acima.
	 * 
	 */
	/**
	 * @param login
	 * @param atributo
	 * @return
	 */
	public String getAtributoPerfil(String login, String atributo) {
		return servicosPerfil.gerAtributoPerfil(login, atributo);
	}

}
