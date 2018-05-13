package com.tratamento.erro;

/**
 * Esta classe captura as menssagem de erro quando chamda.
 * 
 * @author Bruno Miranda / Thassio Lucena
 *
 */
public class ErroException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ErroException(String msg) {
		super(msg);
	}

}
