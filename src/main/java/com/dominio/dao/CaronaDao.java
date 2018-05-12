package com.dominio.dao;

import java.util.List;

import com.dominio.Carona;

/**
 * Interface irá auxiliar no servico de persistencia de dados.
 * 
 * @author Bruno Miranda, Thassio Lucena
 *
 */

public interface CaronaDao {

	/**
	 * Salva o paramentro abaixo na base de dados
	 * 
	 * @param carona
	 *            Objeto carona, consulta classe carona para maiores informações
	 * @param id
	 *            identidicador do perfil do usuário
	 * 
	 * @see Carona
	 * 
	 */
	public void salva(Carona carona, int id);

	/**
	 * Captura todas as caronas que ainda tem vagas disponivel
	 * 
	 * @return retorna uma lista com as informações de uma carona
	 */
	public List<Carona> buscartodas();

	/**
	 * Captura informações de uma carona cadastrada
	 * 
	 * @param idcarona
	 *            identificador de uma carona valida.
	 * @return retorna um objeto de uma carona.
	 */
	public Carona buscarCarona(int idcarona);

	/**
	 * Verificar se existe carona cadastrada
	 * 
	 * @param idcarona
	 *            identificador de uma carona valida
	 * @return retorna um valor booblean verdadeiro ou falso.
	 */
	public boolean isCaronaId(int idcarona);

	/**
	 * Excluir os dados cadastrados na base de dados caronas.
	 */
	public void deletarCaronas();

	/**
	 * Cadastra as informações abaixo na base de dados solicitações.
	 * 
	 * @param idSessao
	 *            identificador de uma sessão
	 * @param idCarona
	 *            identificador de uma carona.
	 * @param pontos
	 *            Pontos de encontro sugerido.
	 * @param solicitante
	 *            No do solicitante da carona
	 * @param id_perfil
	 *            Identificador do pefil
	 * @return Retorna um identificador de um ponto de encontro
	 */
	public int salvaPontoEncontro(String idSessao, int idCarona, String pontos, String solicitante, int id_perfil);

	/**
	 * Excluir os registros da tabela solicitacoes .
	 */
	public void deletarRegistroSoliciatacao();

	/**
	 * Salva as informações referentes a susgestão do solicitante da carona sobre o
	 * ponto de encontro.
	 * 
	 * @param idSessao
	 *            identificador de uma sessão
	 * @param idCarona
	 *            identificador de uma carona.
	 * @param idSolicitacoes
	 *            identificador de uma solicitação
	 * @param pontos
	 *            Ponto de encontro sugerido pelo motorista.
	 * 
	 */
	public void salvaRespostaPontoEncontro(String idSessao, int idCarona, int idSolicitacoes, String pontos,
			int idPerfil);

	/**
	 * Excluir os registro da tabela resposta_sugestao_carona.
	 */
	public void deletarRespostaSolicitacao();

	/**
	 * Salva as informações referente a solicitação de um vaga em uma carona
	 * cadastrada. Esta solicitação é sem sugerir ponto de encontro
	 * 
	 * @param idSessao
	 *            identificador de uma sessão
	 * @param idCarona
	 *            identificador de uma carona.
	 * @param ponto
	 *            Pontos de encontro sugerido.
	 * @param solicitante
	 *            No do solicitante da carona
	 * @return retorna um identificador de uma soliciatação sem sugestão.
	 * 
	 * 
	 */
	public int salvaSolicitacaoVagaCarona(String idSessao, int idCarona, String ponto, String solicitante);

	/**
	 * Consulta informações de uma carona. Local de destino e origem.
	 * 
	 * @param idSolicitacao
	 *            identificador de uma solicitação
	 * @return Retornar a origem ou destino da solicitacao pesquisada
	 */
	public Carona consultaSolicitacao(int idSolicitacao);

	/**
	 * Excluir os registro da tabela solicitacao_vagas
	 */
	public void deletarRegistroSolicitacaoVadas();

	/**
	 * Pesquisa ponto de encontro aparti do id de uma solicitação.
	 * 
	 * @param idSolicitacao
	 *            identificador de uma solicitação
	 * @return retorna ponto de encontro da solicitação informada.
	 */
	public String buscaPontoEncontro(int idSolicitacao);

	/**
	 * Pesquisa o nome do soliciatante em uma vaga na carona.
	 * 
	 * @param idSolicitacao
	 *            identificador de uma solicitação
	 * @return retorna nome do solicitante da vaga na carona cadastrada.
	 */
	public String buscaSolicitante(int idSolicitacao);

	/**
	 * Pesquisa o nome do usuário que cadastro a carona.
	 * 
	 * @param idSolicitacao
	 *            identificador de uma solicitação
	 * @return retorna o nome do usuario que cadastro a carona.
	 */
	public String buscaDonoCarona(int idSolicitacao);

	/**
	 * Decrementa a quantidade de vagas em um Carona cadastrada
	 * 
	 * @param idCarona
	 *            identificador de uma carona.
	 */
	public void atualizarVagas(int idCarona);

	/**
	 * Pesquisa o id da carona em uma solicitação.
	 * 
	 * @param idSolicitacao
	 *            identificador de uma solicitação.
	 * @return Retorna o id da carona, da solicitacao cadastrada.
	 */
	public int buscaridCarona(int idSolicitacao);

	/**
	 * Atualizar o campo estado da tabela solicitacaoVagas onde ira informa se o
	 * motorista aceitou ou não a solicitacao da carona.
	 * 
	 * @param idSolicitacao
	 *            identificador de uma solicitação.
	 * 
	 */
	public void atualizarEstadoSolicitacao(int idSolicitacao);

	/**
	 * @param idSolicitacao
	 *            identificador de uma solicitação.
	 * @return retornar o valor da situação atual da soliciação de vaga em uma
	 *         carona.
	 */
	public String buscarEstado(int idSolicitacao);

	/**
	 * Atualizar a sitação da carona para CANCELADA
	 * 
	 * @param idSolicitacao
	 *            identificador de uma solicitação.
	 * 
	 */
	public void cancelarSolicitacao(int idSolicitacao);

	/**
	 * Se a solicitação for aceita e cancelada iremos incrementar o valor da vaga na
	 * carona.
	 * 
	 * @param idCarona
	 *            identificador de uma solicitação.
	 * 
	 */
	public void encrementaVagaCarona(int idCarona);

	/**
	 * Pesquisa um id de um usuário
	 * 
	 * @param idSolicitacao
	 *            identificador de uma solicitação.
	 * @return Retorna um idSessao
	 */
	public String buscaridSessao(int idSolicitacao);

	/**
	 * Método solicita vaga em carona cadastrada no sistema, sem sugerir ponto de
	 * encontro, e salva as informacoes dos atributos na tabela
	 * Solicitacao_Vaga_Sem_Sugestao
	 * 
	 * @param idSessao
	 *            identificador de uma sessão
	 * @param idCarona
	 *            identificador de uma carona
	 * @param solicitante
	 *            Nome do solicitante da carona
	 * @param sITUACAO_SOLICITACAO_VAGA
	 *            valor atrbuido para a variavel em questão.
	 * @return retorna um id valido.
	 */
	public int salva_Solicitacao_Vaga_Vem_Sugestao(String idSessao, int idCarona, String solicitante,
			String SITUACAO_SOLICITACAO_VAGA);

	/**
	 * Pesquisa carona sem o ponto de encontro.
	 * 
	 * @param idSolicitacaoVaga
	 *            identificador de uma soliciatação
	 * @return Consulta caronas com vagas disponivel sem sugerir o ponto de encontro
	 */
	public Carona consultaSolicitacaoVagaSemPonto(int idSolicitacaoVaga);

	/**
	 * Buscar o nome do dono da carona informado o id da tabela
	 * solicitacao_vaga_sem_sugestao
	 * 
	 * @param idSolicitacaoVaga
	 *            identificador de uma soliciatação
	 * @return retorna o nome.
	 */
	public String buscaSolicitacaoDonoCarona(int idSolicitacaoVaga);

	/**
	 * buscar o nome do solicitante de carona.
	 * 
	 * @param idSolicitacaoVaga
	 *            identificador de uma soliciatação
	 * @return
	 */
	public String buscaSolicitanteVaga(int idSolicitacaoVaga);

	/**
	 * Deleta os registros da tabela solicitacao_vaga_sem_sugestao.
	 */
	public void deletarRegistroSolicitacaoSemSugestao();

	/**
	 * Pesquisa idSessao.
	 * 
	 * @param idSessao
	 *            identificador de uma sessãp
	 * @return a busca abaixo retorna um idSessao, deacordo com tabela
	 *         <b>buscaridSessaoDonoCarona</b> Caronas cadastradas sem ponto de
	 *         encontro.
	 */
	public String buscaridSessaoDonoCarona(int idSolicitacao);

	/**
	 * Busca o identificador da carona para ser utlizado posteriomente.
	 * 
	 * @param idSolicitacao
	 *            identificador de uma solicitação
	 * @return retorna um identificador de uma solicitação
	 */
	public int buscaridCarona_SemSugestaoPonto(int idSolicitacao);

	/**
	 * atualizar o valor da celular situacaoVagaSolicitada na tabela
	 * solicitacao_vaga_sem_sugestao para aceito quando o motorista aceita a
	 * solicitacao da carona sem sugestao de ponto de encontro.
	 * 
	 * @param idSolicitacao
	 *            identificador de uma solicitação
	 * 
	 */
	public void AterarSituacao_SemSugestaoPonto(int idSolicitacao);

	/**
	 * capturar o valor da celular situacaoVagaSolicitada na tabela
	 *         solicitacao_vaga_sem_sugestao e retorna o valor da celular para o
	 *         método chamdor
	 * @param idSolicitacao
	 *            identificador de uma solicitação
	 * @return 
	 */
	public String verificar_Solicitacao_Vaga(int idSolicitacao);

	/**
	 *  identificador de uma solicitação Alterar a situação da carona
	 *            solicitada para "REJEITADA".
	 * @param idSolicitacao identificador de uma solicitação
	 *           
	 */
	public void alterar_Sicituacao_Solicitacao(int idSolicitacao);


	/**
	 * Pesquisa as caronas cadastrada pela sessão de um usuario.
	 * 
	 * @param idSessao
	 *            identificador de uma sessão
	 * @return retorna um lista de caronas cadastrada pelo idSessao.
	 */
	public String getTodasCaronasUsuario(String idSessao);

	/**
	 * Pesquisa as solicitações de uma carona.
	 * 
	 * @param idSessao
	 *            identificador da sessão
	 * @param idCarona
	 *            identificador da carona
	 * @return Retorna o id das solicitações de vaga sem sugerir ponto de encontro
	 */
	public String getSolicitacoesConfirmadas(String idSessao, int idCarona);

	/**
	 * Pesquisa as solicitações pendentes de uma carona.
	 * 
	 * @param idSessao
	 *            identificador da sessão
	 * @param idCarona
	 *            identificador da carona
	 * @return retorna o id das solicitações pendentes de vaga sem sugerir ponto de
	 *         encontro
	 */
	public String getSolicitacoesPendentes(String idSessao, int idCarona);

	/**
	 * buscar pontos de encotro cadastrado
	 * @param idSessao
	 *            identificador da sessão
	 * @param idCarona
	 *            identificador da carona
	 * @return Retonar a resposta do ponto de encontro sugerido.
	 */
	public String getPontosEncontro(String idSessao, int idCarona);

	/**
	 * buscar pontos sugeridos
	 * @param idSessao
	 *            identificador da sessão
	 * @param idCarona
	 *            identificador da carona
	 * @return Retorna os pontos sugeridos de uma carona.
	 */
	public String getPontosSugeridos(String idSessao, int idCarona);

	/**
	 * Cadastra informações de um perfil
	 * 
	 * @param idSessao
	 *            identificador da sessão
	 * @param idCarona
	 *            identificador da carona
	 * @param review
	 *            informação da presença de um caroneiro.
	 */
	public void reviewCaronaPresenca(String idSessao, int idCarona, String loginCaroneiro, String review);

	/**
	 * Apaga os registro da tabela review.
	 */
	public void deletarReview();

	/**
	 * Método verificar se uma determinada carona é do tipo municipal.
	 * 
	 * @param idCarona
	 *            identificador de uma carona
	 * @param atributo
	 *            parametro que será utilizado com referencia para pesquisa.
	 * @return retorna um valor booleano verdade quando a carona forma municipal e
	 *         falso para intermunicipal.
	 */
	public boolean getAtributoCaronaMunicipal(int idCarona, String atributo);

	/**
	 * O método irá buscar o id da carona com base com base nos parametros abaixo.
	 * 
	 * @param idSessao
	 *            Identificador de uma sessão ativa de um usuário
	 * @param cidade
	 *            Local onde a carona vai acontecer <b>(parametro obrigatorio)</b>
	 * @return O retorno será uma lista de caronas do tipo municipal cadastrada para
	 *         a pesquisa.
	 */
	public String buscarCaronaMunicipio(String idSessao, String cidade);

	/**
	 @param idSessao
	 *            Identificador de uma sessão ativa de um usuário
	 * @param cidade
	 *            Local onde a carona vai acontecer <b>(parametro obrigatorio)</b>
	 * @param origem
	 *            partida da carona
	 * @param destino
	 *            chegada da carona
	 * @return O retorno será uma lista de caronas do tipo municipal cadastrada para
	 *         a pesquisa.
	 */
	public String buscarCarona_Municipio_id(String idSessao, String cidade, String origem, String destino);

}
