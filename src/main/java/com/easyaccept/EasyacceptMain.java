package com.easyaccept;

import easyaccept.EasyAccept;

/**
 * @author <b>Bruno Miranda/Thassio Lucena.</b> Esta classe tem como finalidade
 *         executar os testes de aceitação
 *
 */
public class EasyacceptMain {

	public static void main(String[] args) {
		args = new String[] { "com.easyaccept.FachadaEasyaccept", "teste_aceitacao/US01.txt",
				"teste_aceitacao/US02.txt", "teste_aceitacao/US03.txt", "teste_aceitacao/US04.txt",
				"teste_aceitacao/US05.txt", "teste_aceitacao/US06.txt", "teste_aceitacao/US07.txt",
				"teste_aceitacao/US08.txt", "teste_aceitacao/US09.txt", "teste_aceitacao/US10.txt" };
		
		/*args = new String[] { "com.easyaccept.FachadaEasyaccept", "teste_aceitacao/US04.txt" };*/
		
		EasyAccept.main(args);

	}

}
