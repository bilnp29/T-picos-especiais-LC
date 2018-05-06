package com.servicos.perfil;

import com.dominio.Usuario;
import com.dominio.dao.SistemaDao;
import com.tratamento.erro.ErroException;

public class ServicosPerfil {

	private SistemaDao sistemaDao;

	public ServicosPerfil() {
		sistemaDao = new SistemaDao();
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
		String loginSessao = sistemaDao.buscarLogin(idSessao);

		if (!login.equals(loginSessao)) {
			throw new ErroException("login invalido");
		}
		return sistemaDao.visualizarPerfil(idSessao, login);
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
	public String gerAtributoPerfil(String login, String atributo) {
		String dados = "";
		if (atributo == null || atributo.isEmpty()) {
			throw new ErroException("Atributo invalido");
		} else if (!atributo.equals("nome") && !atributo.equals("endereco") && !atributo.equals("email")
				&& !atributo.equals("historico de caronas") && !atributo.equals("historico de vagas em caronas")
				&& !atributo.equals("caronas seguras e tranquilas") && !atributo.equals("caronas que não funcionaram")
				&& !atributo.equals("faltas em vagas de caronas") && !atributo.equals("faltas em vagas de caronas")
				&& !atributo.equals("presença em vagas de caronas")) {
			throw new ErroException("Atributo inexistente");
		} else {
			Usuario usuario = new Usuario();
			// PerfilUsuario perfil = new PerfilUsuario();
			usuario = sistemaDao.buscar_dados_Usuario(login, atributo);
			if (atributo.equals("nome")) {
				dados = usuario.getNome();

			} else if (atributo.equals("endereco")) {
				dados = usuario.getEndereco();
				
			} else if (atributo.equals("email")) {
				dados = usuario.getEmail();
				
			} else if (atributo.equals("historico de caronas")) {
				
				dados = "[" + sistemaDao.buscar_historico_Caronas(login) + "]";
			} else if (atributo.equals("historico de vagas em caronas")) {
				
				dados = sistemaDao.buscar_historico_vagas_caronas(login);
			} else if (atributo.equals("caronas seguras e tranquilas")) {
				
				dados = sistemaDao.buscar_historico_caronasSeguras_tranquilas();
			} else if (atributo.equals("caronas que não funcionaram")) {
				
				dados = sistemaDao.buscar_historico_caronas_nao_funcionaram();
			} else if (atributo.equals("faltas em vagas de caronas")) {
				
				dados = sistemaDao.buscar_faltas_caronas(login);
			} else {
				
				dados = sistemaDao.buscar_presenca_caronas(login);
			}
		}

		return dados;
	}

}
