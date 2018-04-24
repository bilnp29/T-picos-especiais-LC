package com.dominio.dao;

import java.util.List;

import com.dominio.Carona;

/**
 * @author Bruno Miranda, Thassio Lucena Interface irá auxiliar no servico de
 *         persistencia de dados.
 *
 */
public interface CaronaDao {

	/**
	 * Salva o paramentro abaixo na base de dados
	 * 
	 * @param carona
	 */

	public void salva(Carona carona, int id);

	/**
	 * Captura todas as caronas que ainda tem vagas disponivel
	 */
	/**
	 * @return
	 */
	public List<Carona> buscartodas();

	/**
	 * @param idcarona
	 * @return
	 */
	public Carona buscarCarona(int idcarona);

	/**
	 * @param idcarona
	 * @return
	 */
	public boolean isCaronaId(int idcarona);

	/**
	 * Excluir os dados cadastrados na base de dados caronas.
	 */
	public void deletarCaronas();

	/**
	 * @param idSessao
	 * @param idCarona
	 * @param pontos
	 * @param solicitante
	 * @param id_perfil
	 * @return Cadastra as informações abaixo na base de dados solicitações.
	 */
	public int salvaPontoEncontro(String idSessao, int idCarona, String pontos, String solicitante, int id_perfil);

	/**
	 * Excluir os registros da tabela solicitacoes .
	 */
	public void deletarRegistroSoliciatacao();

	/**
	 * @param idSessao
	 * @param idCarona
	 * @param idSolicitacoes
	 * @param pontos
	 * 
	 *            Salva as informações referentes a susgestão do solicitante da
	 *            carona sobre o ponto de encontro.
	 */
	public void salvaRespostaPontoEncontro(String idSessao, int idCarona, int idSolicitacoes, String pontos,
			int idPerfil);

	/**
	 * Excluir os registro da tabela resposta_sugestao_carona.
	 */
	public void deletarRespostaSolicitacao();

	/**
	 * @param idSessao
	 * @param idCarona
	 * @param ponto
	 * @param solicitante
	 * @return
	 * 
	 * 		Salva as informações referente a solicitação de um vaga em uma carona
	 *         cadastrada.
	 */
	public int salvaSolicitacaoVagaCarona(String idSessao, int idCarona, String ponto, String solicitante);

	/**
	 * @param idSolicitacao
	 * @return Retornar a origem ou destino da solicitacao pesquisada
	 */
	public Carona consultaSolicitacao(int idSolicitacao);

	/**
	 * Excluir os registro da tabela solicitacao_vagas
	 */
	public void deletarRegistroSolicitacaoVadas();

	/**
	 * @param idSolicitacao
	 * @return retorna ponto de encontro da solicitação informada.
	 */
	public String buscaPontoEncontro(int idSolicitacao);

	/**
	 * @param idSolicitacao
	 * @return retorna nome do solicitante da vaga na carona cadastrada.
	 */
	public String buscaSolicitante(int idSolicitacao);

	/**
	 * @param idSolicitacao
	 * @return retorna o nome do usuario que cadastro a carona.
	 */
	public String buscaDonoCarona(int idSolicitacao);

	/**
	 * @param idSolicitacao
	 *            Decrementa a quantidade de vagas em um Carona cadastrada
	 */
	public void atualizarVagas(int idCarona);

	/**
	 * @param idSolicitacao
	 * @return Retorna o id da carona, da solicitacao cadastrada.
	 */
	public int buscaridCarona(int idSolicitacao);

	/**
	 * @param idSolicitacao
	 *            Atualizar o campo estado da tabela solicitacaoVagas onde ira
	 *            informa se o motorista aceitou ou não a solicitacao da carona.
	 */
	public void atualizarEstadoSolicitacao(int idSolicitacao);

	/**
	 * @param idSolicitacao
	 * @return retornar o valor da situação atual da soliciação de vaga em uma
	 *         carona.
	 */
	public String buscarEstado(int idSolicitacao);

	/**
	 * @param idSolicitacao
	 *            Atualizar a sitação da carona para CANCELADA
	 */
	public void cancelarSolicitacao(int idSolicitacao);

	/**
	 * @param idCarona
	 *            Se a solicitação for aceita e cancelada iremos incrementar o valor
	 *            da vaga na carona.
	 */
	public void encrementaVagaCarona(int idCarona);

	/**
	 * @param idSolicitacao
	 * @return Retorna um idSessao
	 */
	public String buscaridSessao(int idSolicitacao);

	/**
	 * @param idSessao
	 * @param idCarona
	 * @param solicitante
	 * @param sITUACAO_SOLICITACAO_VAGA
	 * @return Método solicita vaga em carona cadastrada no sistema, sem sugerir
	 *         ponto de encontro, e salva as informacoes dos atributos na tabela
	 *         Solicitacao_Vaga_Sem_Sugestao
	 */
	public int salva_Solicitacao_Vaga_Vem_Sugestao(String idSessao, int idCarona, String solicitante,
			String SITUACAO_SOLICITACAO_VAGA);

	/**
	 * @param idSolicitacaoVaga
	 * @return Consulta caronas com vagas disponivel sem sugerir o ponto de encontro
	 */
	public Carona consultaSolicitacaoVagaSemPonto(int idSolicitacaoVaga);

	/**
	 * @param idSolicitacaoVaga
	 * @return Buscar o nome do dono da carona informado o id da tabela
	 *         solicitacao_vaga_sem_sugestao
	 */
	public String buscaSolicitacaoDonoCarona(int idSolicitacaoVaga);

	/**
	 * @param idSolicitacaoVaga
	 * @return buscar o nome do solicitante de carona.
	 */
	public String buscaSolicitanteVaga(int idSolicitacaoVaga);

	/**
	 * Deleta os registros da tabela solicitacao_vaga_sem_sugestao.
	 */
	public void deletarRegistroSolicitacaoSemSugestao();

	/**
	 * @param idSessao
	 * @return a busca abaixo retorna um idSessao, deacordo com tabela
	 *         <b>buscaridSessaoDonoCarona</b> Caronas cadastradas sem ponto de
	 *         encontro.
	 */
	public String buscaridSessaoDonoCarona(int idSolicitacao);

	/**
	 * @param idSolicitacao
	 * @return Busca o identificador da carona para ser utlizado posteriomente.
	 */
	public int buscaridCarona_SemSugestaoPonto(int idSolicitacao);

	/**
	 * @param idSolicitacao
	 *            atualizar o valor da celular situacaoVagaSolicitada na tabela
	 *            solicitacao_vaga_sem_sugestao para aceito quando o motorista
	 *            aceita a solicitacao da carona sem sugestao de ponto de encontro.
	 */
	public void AterarSituacao_SemSugestaoPonto(int idSolicitacao);

	/**
	 * @param idSolicitacao
	 * @return capturar o valor da celular situacaoVagaSolicitada na tabela
	 *         solicitacao_vaga_sem_sugestao e retorna o valor da celular para o
	 *         método chamdor
	 */
	public String verificar_Solicitacao_Vaga(int idSolicitacao);

	/**
	 * @param idSolicitacao Alterar a situação da carona solicitada para "REJEITADA".
	 */
	public void alterar_Sicituacao_Solicitacao(int idSolicitacao);

	/**
	 * @param idSessao
	 * @param indexCarona
	 * @return
	 */
	public int getCaronaUsuario(String idSessao, int indexCarona);

	/**
	 * @param idSessao
	 * @return
	 */
	public String getTodasCaronasUsuario(String idSessao);

	/**
	 * Apaga as informações da tabela informacaocarona
	 */
	public void deletarInformacoesCaornas();

	/**
	 * @param idSessao
	 * @param idCarona
	 * @return Retorna o id das solicitações de vaga sem sugerir ponto de encontro
	 */
	public String getSolicitacoesConfirmadas(String idSessao, int idCarona);

	/**
	 * @param idSessao
	 * @param idCarona
	 * @return o id das solicitações pendentes de vaga sem sugerir ponto de encontro
	 */
	public String getSolicitacoesPendentes(String idSessao, int idCarona);

	/**
	 * @param idSessao
	 * @param idCarona
	 * @return Retonar a resposta do ponto de encontro sugerido.
	 */
	public String getPontosEncontro(String idSessao, int idCarona);

	/**
	 * @param idSessao
	 * @param idCarona
	 * @return
	 */
	public String getPontosSugeridos(String idSessao, int idCarona);

	/**
	 * @param idSessao
	 * @param idCarona
	 * @param review
	 */
	public void reviewCaronaPresenca(String idSessao, int idCarona, String loginCaroneiro, String review);

	/**
	 * Apaga os registro da tabela review.
	 */
	public void deletarReview(); 

}
