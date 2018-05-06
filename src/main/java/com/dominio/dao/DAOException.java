package com.dominio.dao;

/**
 * Menssagem de erro referente ao sistema de persitencia de dados.
 * @author Bruno Miranda / Thassio Lucena
 * 
 *
 */
@SuppressWarnings("serial")
public class DAOException extends RuntimeException {
	
	public DAOException(String msg, Throwable t) {
		super(msg,t);
	}
}
