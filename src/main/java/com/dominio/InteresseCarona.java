package com.dominio;

import java.io.Serializable;

/**
 * 
 * @author Bruno Mianda / Thassio Lucena
 *
 */
public class InteresseCarona implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5815789995768632315L;
	private String idSessao;
	private String origem;
	private String destino;
	private String data;
	private String horaInicio;
	private String horaFim;
	
	public InteresseCarona() {

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

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}

	public String getHoraFim() {
		return horaFim;
	}

	public void setHoraFim(String horaFim) {
		this.horaFim = horaFim;
	}


}
