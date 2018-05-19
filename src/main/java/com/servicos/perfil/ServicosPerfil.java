package com.servicos.perfil;

import org.apache.log4j.Logger;

import com.dominio.Carona;
import com.dominio.InteresseCarona;
import com.dominio.Usuario;
import com.dominio.dao.SistemaDao;
import com.tratamento.erro.ErroException;

/**
 * Esta classe irá realizar alguns servicos para um perfil de usuário
 * 
 * @author Bruno Mirnada / Thassio Lucena
 *
 */
public class ServicosPerfil { 

	private SistemaDao sistemaDao;
	
	final static Logger logger = Logger.getLogger(ServicosPerfil.class);
	
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
		logger.info("Iniciando método");
		String loginSessao = sistemaDao.buscarLogin(idSessao);

		if (!login.equals(loginSessao)) {
			throw new ErroException("login invalido");
		}
		logger.info("Fim do método");
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
		logger.info("Iniciando método");
		String dados = "";
		if (atributo == null || atributo.isEmpty()) {
			logger.error("Atributo invalido");
			throw new ErroException("Atributo invalido");
		} else if (!atributo.equals("nome") && !atributo.equals("endereco") && !atributo.equals("email")
				&& !atributo.equals("historico de caronas") && !atributo.equals("historico de vagas em caronas")
				&& !atributo.equals("caronas seguras e tranquilas") && !atributo.equals("caronas que não funcionaram")
				&& !atributo.equals("faltas em vagas de caronas") && !atributo.equals("faltas em vagas de caronas")
				&& !atributo.equals("presença em vagas de caronas")) {
			logger.error("Atributo invalido");
			throw new ErroException("Atributo inexistente");
			
		} else {
			Usuario usuario = new Usuario();
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
		logger.info("Fim do método");
		return dados;
	}

	/**
	 * @param idSessao
	 *            identificador da sessão
	 * @return retorna as informações sobre uma carona que esta para acontecer
	 */
	public String verificarMensagensPerfil(String idSessao) {
		logger.info("Iniciando método");
		String dados = "[";
		InteresseCarona interesseCaronas = sistemaDao.buscarCaronasInteressadas(idSessao);
		Carona carona = sistemaDao.buscar_dadosCarona(interesseCaronas);
		if (carona == null) {
			dados += "]";
			return dados;
		} else {
			String email = sistemaDao.buscarEmailUsuario(carona);
			dados += "Carona cadastrada no dia " + carona.getData() + ", as " + carona.getHora()
					+ " de acordo com os seus interesses registrados. Entrar em contato com " + email + "]";
		}
		logger.info("Fim do método");
		return dados;
	}

}
