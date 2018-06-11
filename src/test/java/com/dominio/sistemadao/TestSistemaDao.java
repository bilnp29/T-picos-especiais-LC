package com.dominio.sistemadao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import com.dominio.Carona;
import com.dominio.dao.SistemaDao;
import com.servicos.carona.ServicoCarona;
import com.servicos.usuario.ControleUsuario;
import com.servicos.usuario.ServicosUsuario;
import com.tratamento.erro.ErroException;

public class TestSistemaDao {

	private SistemaDao sistemadao;
	private ServicoCarona servCarona;
	private Carona carona_valida;
	private String idCarona = "";
	private ControleUsuario controle;
	private ServicosUsuario servUsuario;
	

	@Before
	public void setUp(){
		sistemadao = new SistemaDao();
		servCarona = new ServicoCarona();
		controle = new ControleUsuario();
		servUsuario = new ServicosUsuario();
	}
	
	@Test
	public void zeraSistema() throws Exception {
		sistemadao.deletarArquivo();
	}

	@Test
	public void cadastraCarona() throws Exception {
		controle.cadastraUsuario("jose1", "1", "jose", "projetada I", "jose1@gmail.com");
		servUsuario.validarUsuario("jose1", "1");
		
	}
	
	@Test
	public void testMessagem_Erro_Atributo_Carona() throws NumberFormatException, Exception {
		try {
			carona_valida = new Carona("sessaoJose1", "João Pessoa", "Campina Grande", "12/12/2012", "10:00", "2");
			idCarona = servCarona.validaCarona("sessaoJose1", carona_valida);
			sistemadao.buscarAtributoCarona(idCarona, null);
			sistemadao.buscarAtributoCarona(idCarona, "");
			fail("Deve lanca msg erro -> Atributo invalido");
		} catch (ErroException e) {
			String menssage = e.getMessage();
			assertEquals("Atributo invalido", menssage);
		}
	}
	@Test
	public void test_Messagem_Erro_Atributo_Carona_inexistente() throws NumberFormatException, Exception {
		try {
			carona_valida = new Carona("sessaoJose1", "João Pessoa", "Campina Grande", "12/12/2012", "10:00", "2");
			idCarona = servCarona.validaCarona("sessaoJose1", carona_valida);
			sistemadao.buscarAtributoCarona(idCarona,"nome");
			fail("Deve lanca msg erro -> Atributo inexistente");
		} catch (ErroException e) {
			String menssage = e.getMessage();
			assertEquals("Atributo inexistente", menssage);
		}
	}
 
	@Test
	public void testBuscar_Atributo_Carona_Destino() throws NumberFormatException, Exception {
		carona_valida = new Carona("sessaoJose1", "João Pessoa", "Campina Grande", "12/12/2012", "10:00", "2");
		idCarona = servCarona.validaCarona("sessaoJose1", carona_valida);
		String dado = sistemadao.buscarAtributoCarona(idCarona, "destino");
		assertEquals("Campina Grande",dado);
	}
	
	@Test
	public void testBuscar_Atributo_Carona_Data() throws NumberFormatException, Exception {
		carona_valida = new Carona("sessaoJose1", "João Pessoa", "Campina Grande", "12/12/2012", "10:00", "2");
		servCarona.validaCarona("sessaoJose1", carona_valida);
		String dado = sistemadao.buscarAtributoCarona(idCarona, "data");
		assertEquals("12/12/2012",dado);
	}
	
	@Test
	public void testBuscar_Atributo_Carona_Vagas() throws NumberFormatException, Exception {
		carona_valida = new Carona("sessaoJose1", "João Pessoa", "Campina Grande", "12/12/2012", "10:00", "2");
		idCarona = servCarona.validaCarona("sessaoJose1", carona_valida);
		String dado = sistemadao.buscarAtributoCarona(idCarona, "vagas");
		assertEquals("2",dado);
	}
	
	@Test
	public void testDescricaoCarona() throws NumberFormatException, Exception {
		carona_valida = new Carona("sessaoJose1", "João Pessoa", "Campina Grande", "12/12/2012", "10:00", "2");
		idCarona = servCarona.validaCarona("sessaoJose1", carona_valida);
		String descricao = sistemadao.descricaoCarona(idCarona);
		assertEquals("João Pessoa para Campina Grande, no dia 12/12/2012, as 10:00", descricao);
	}
	
	@Test
	public void testDescricaoTrajeto() throws NumberFormatException, Exception {
		carona_valida = new Carona("sessaoJose1", "João Pessoa", "Campina Grande", "12/12/2012", "10:00", "2");
		idCarona = servCarona.validaCarona("sessaoJose1", carona_valida);
		String descricao = sistemadao.descricaoTrajero(idCarona);
		assertEquals("João Pessoa - Campina Grande", descricao);
	}
	
}
