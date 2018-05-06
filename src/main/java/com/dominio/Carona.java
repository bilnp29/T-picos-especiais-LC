package com.dominio;

/**
 * Classe Carona nela estão presentes os atributos e os métodos getts, setts. O
 * usuario só podera cadastra uma carona quando estive com um perfil ativo.
 * 
 * @author Bruno Miranda / Thassio Lucena.
 *
 */
public class Carona {

	private int idCaronas;
	private String idSessao;
	private String donoCarona;
	private String origemCarona;
	private String destinoCarona;
	private String vagas;
	private String data;
	private String hora;
	private int perfil_IdPerfil;

	public Carona() {

	}

	/**
	 * Método construtor da classe Carona recebe com parametro os atributos abaixo.
	 * 
	 * @param idSessao
	 *            Identificador da sessão do usuário
	 * @param origemCarona
	 *            Local de partida de uma carona
	 * @param destinoCarona
	 *            Local da chegada de uma carona
	 * @param data
	 *            arona quando vai acontecer
	 * @param hora
	 *            O horario da carona
	 * @param vagas
	 *            Quantidade de pessoas que podem solicitar uma carona
	 */
	public Carona(String idSessao, String origemCarona, String destinoCarona, String data, String hora, String vagas) {
		this.setOrigemCarona(origemCarona);
		this.setDestinoCarona(destinoCarona);
		this.setVagas(vagas);
		this.setData(data);
		this.setHora(hora);
		this.setIdSessao(idSessao);

	}

	public int getIdCaronas() {
		return idCaronas;
	}

	public void setIdCaronas(int idCaronas) {
		this.idCaronas = idCaronas;
	}

	public String getIdSessao() {
		return idSessao;
	}

	public void setIdSessao(String idSessao) {
		this.idSessao = idSessao;
	}

	public String getOrigemCarona() {
		return origemCarona;
	}

	public void setOrigemCarona(String origemCarona) {
		this.origemCarona = origemCarona;
	}

	public String getDestinoCarona() {
		return destinoCarona;
	}

	public void setDestinoCarona(String destinoCarona) {
		this.destinoCarona = destinoCarona;
	}

	public String getVagas() {
		return vagas;
	}

	public void setVagas(String vagas) {
		this.vagas = vagas;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public int getPerfil_IdPerfil() {
		return perfil_IdPerfil;
	}

	public void setPerfil_IdPerfil(int perfil_IdPerfil) {
		this.perfil_IdPerfil = perfil_IdPerfil;
	}

	public String getDonoCarona() {
		return donoCarona;
	}

	public void setDonoCarona(String donoCarona) {
		this.donoCarona = donoCarona;
	}

}
