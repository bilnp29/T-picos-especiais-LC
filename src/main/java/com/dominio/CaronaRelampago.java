package com.dominio;

import java.io.Serializable;

/**
 * Classe CaronaRelemago nela estão presentes os atributos e os métodos getts, setts. O
 * usuario só podera cadastra uma carona quando estive com um perfil ativo.
 * 
 * @author Bruno Miranda / Thassio Lucena
 *
 */
public class CaronaRelampago implements Serializable {

	/**
	 * Serealizando a classe.
	 */
	private static final long serialVersionUID = 7953277309918331873L;
	private int idcaronaRelampago;
	private String idSessao;
	private String origem;
	private String destino;
	private String dataIda;
	private String dataVolta;
	private String hora;
	private String minimoCaroneiro;
	private int perfil_IdPerfil;
	
	public CaronaRelampago() {
		// TODO Auto-generated constructor stub
	}

	public CaronaRelampago(String idSessao, String origem, String destino, String dataIda,
			String dataVolta,String hora ,String minimoCaroneiro) {
		
		this.idSessao = idSessao;
		this.origem = origem;
		this.destino = destino;
		this.dataIda = dataIda;
		this.dataVolta = dataVolta;
		this.hora = hora;
		this.minimoCaroneiro = minimoCaroneiro;
		
	}

	public int getIdcaronaRelampago() {
		return idcaronaRelampago;
	}

	public void setIdcaronaRelampago(int idcaronaRelampago) {
		this.idcaronaRelampago = idcaronaRelampago;
	}

	public String getIdSessao() {
		return idSessao;
	}

	public void setIdSessao(String idSessao) {
		this.idSessao = idSessao;
	}

	public String getOrigem() {
		return origem;
	}

	public void setOrigem(String origem) {
		this.origem = origem;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public String getDataIda() {
		return dataIda;
	}

	public void setDataIda(String dataIda) {
		this.dataIda = dataIda;
	}

	public String getDataVolta() {
		return dataVolta;
	}

	public void setDataVolta(String dataVolta) {
		this.dataVolta = dataVolta;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public String getMinimoCaroneiro() {
		return minimoCaroneiro;
	}

	public void setMinimoCaroneiro(String minimoCaroneiro) {
		this.minimoCaroneiro = minimoCaroneiro;
	}

	public int getPerfil_IdPerfil() {
		return perfil_IdPerfil;
	}

	public void setPerfil_IdPerfil(int perfil_IdPerfil) {
		this.perfil_IdPerfil = perfil_IdPerfil;
	}
	
}
