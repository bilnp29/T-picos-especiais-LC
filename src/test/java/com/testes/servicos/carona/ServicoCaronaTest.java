package com.testes.servicos.carona;

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

public class ServicoCaronaTest {

	private Carona carona_valida;
	private Carona carona_origem_vazio;
	private Carona carona_origem_null;
	private Carona carona_destino_vazio;
	private Carona carona_destino_null;
	private Carona carona_hora_vazia;
	private Carona carona_hora_null;
	private Carona carona_hora_invalida;
	private Carona carona_vaga_null;
	private Carona carona_vaga_invalida;

	private ServicoCarona servCarona;
	private ControleUsuario controle;
	private ServicosUsuario servUsuario;
	private SistemaDao sistemadao;
	private Carona carona_valida_municipal;
	@Before
	public void setUp() {
		carona_valida = new Carona("sessaoJose1", "Campo Grande", "São Paulo", "12/12/2012", "10:00", "2");

		carona_valida_municipal = new Carona("sessaoJose1", "Parque da criança", "Parque do povo", "Campina Grande","18/05/2018", "15:00","2");
		
		new Carona("sessaoJose1", "Parque da criança", "Parque do povo", null,"18/05/2018", "15:00","2");
		
		new Carona("sessaoJose1", "Parque da criança", "Parque do povo", "","18/05/2018", "15:00","2");

		carona_origem_vazio = new Carona("sessaoJose1", "", "São Paulo", "12/12/2012", "10:00", "2");

		carona_origem_null = new Carona("sessaoJose1", null, "São Paulo", "12/12/2012", "10:00", "2");

		carona_destino_vazio = new Carona("sessaoJose1", "Campo Grande", "", "12/12/2012", "10:00", "2");

		carona_destino_null = new Carona("sessaoJose1", "Campo Grande", null, "12/12/2012", "10:00", "2");

		new Carona("sessaoJose1", "Campo Grande", "São Paulo", "", "10:00", "2");

		new Carona("sessaoJose1", "Campo Grande", "São Paulo", null, "10:00", "2");

		new Carona("sessaoJose1", "Campo Grande", "São Paulo", "30/02/2015", "10:00", "2");

		carona_hora_vazia = new Carona("sessaoJose1", "Campo Grande", "São Paulo", "12/12/12", "", "2");

		carona_hora_null = new Carona("sessaoJose1", "Campo Grande", "São Paulo", "12/12/12", null, "2");

		carona_hora_invalida = new Carona("sessaoJose1", "Campo Grande", "São Paulo", "12/12/12", "dez", "2");

		carona_vaga_null = new Carona("Sessaojose", "Campo Grande", "São Paulo", "12/12/12", "10:00", null);

		carona_vaga_invalida = new Carona("Sessaojose", "Campo Grande", "São Paulo", "12/12/12", "10:00", "dois");

		servCarona = new ServicoCarona();

		controle = new ControleUsuario();

		servUsuario = new ServicosUsuario();

		sistemadao = new SistemaDao(); 

	}


	@Test
	public void testDeveCadastraCarona() throws Exception {
		controle.cadastraUsuario("jose1", "1", "jose", "projetada I", "jose1@gmail.com", "32329098");
		servUsuario.validarUsuario("jose1", "1");
		servCarona.validaCarona("sessaoJose1", carona_valida);
	}
	@Test
	public void testDeveCadastraCaronaMunicipal() throws Exception {
		controle.cadastraUsuario("Joao1", "11", "Joao","Projetada 8" , "joao@gamil.com", "32329098");
		servUsuario.validarUsuario("Joao1", "11");
		servCarona.validaCaronaMunicipal("sessaoJoao1", carona_valida_municipal);
	}


	@Test
	public void testOrigem_Invalida() throws Exception {
		try {
			servCarona.validaCaronaOrigem(carona_origem_null);
			servCarona.validaCaronaOrigem(carona_origem_vazio);
			fail("Deve lanca exception-> Origem invalida");
		} catch (ErroException e) {
			String menssage = e.getMessage();
			assertEquals("Origem invalida", menssage);
		}
	}

	@Test
	public void testDestino_Invalido() throws Exception {
		try {
			servCarona.validaCaronaDestino(carona_destino_null);
			servCarona.validaCaronaDestino(carona_destino_vazio);
			fail("Deve lanca exception-> Destino invalido");
		} catch (ErroException e) {
			String menssage = e.getMessage();
			assertEquals("Destino invalido", menssage);
		}
	}


	@Test
	public void testHora_Invalida() {
		try {
			servCarona.horaValida(carona_hora_null);
			servCarona.horaValida(carona_hora_vazia);
			fail("Deve lanca exception->Hora invalida");
		} catch (Exception e) {
			String menssage = e.getMessage();
			assertEquals("Hora invalida", menssage);
		}
	}

	@Test
	public void testHora_Entrada_Invalida() throws Exception {
		try {
			servCarona.horaValida(carona_hora_invalida);
			fail("Deve lanca exception->Hora invalida");
		} catch (ErroException e) {
			String menssage = e.getMessage();
			assertEquals("Hora invalida", menssage);
		}
	}

	@Test
	public void testVaga_Carona() throws Exception {
		try {
			servCarona.vagasValidas(carona_vaga_null);
			servCarona.vagasValidas(carona_vaga_invalida);
			fail("Deve lanca exception -> Vaga invalida");
		} catch (ErroException e) {
			String msg = e.getMessage();
			assertEquals("Vaga invalida", msg);
		}
	}

	@Test
	public void testOrigemEntradaInvalida() {
		try {
			servCarona.verificar_Origem_Destino("()", "Campina Grande");
			servCarona.verificar_Origem_Destino("-", "Campina Grande");
			servCarona.verificar_Origem_Destino("!?", "Campina Grande");
			fail("Deve lanca exception-> Origem invalida");
		} catch (ErroException e) {
			String menssage = e.getMessage();
			assertEquals("Origem invalida", menssage);
		}
	}

	@Test
	public void testDestinoEntradaInvalida() {
		try {
			servCarona.verificar_Origem_Destino("Campina Grande", "()");
			servCarona.verificar_Origem_Destino("Campina Grande", "-");
			servCarona.verificar_Origem_Destino("Campina Grande", "!?");
			fail("Deve lanca exception-> Destino invalida");
		} catch (ErroException e) {
			String menssage = e.getMessage();
			assertEquals("Destino invalido", menssage);
		}
	}

	@Test
	public void testLocalizaCarona() throws Exception {
		servCarona.localizarCarona("", "", "");
		servCarona.localizarCarona("sessaoJose1", "", "");
		servCarona.localizarCarona("sessaoJose1", "Campina Grande", "");
		servCarona.localizarCarona("sessaoJose1","","São Paulo");
		servCarona.localizarCarona("sessaoJose1", "Campina Grande", "São Paulo");

	}
	
	@Test
	public void testGetAtributoVazio() throws Exception {
		String idCarona = servCarona.localizarCarona("", "", "");
		assertEquals("{"+"}", idCarona);
	}
	
	
	@Test
	public void testPesquisarCaronaMenssagemErro() {
		try {
			servCarona.pesquisaCarona(0, "nome");
			servCarona.pesquisaCarona(-1, "endereço");
			fail("Deve lanca exception-> Carona Invalida");
			
		} catch (ErroException e) {
			String msg = e.getMessage();
			assertEquals("Carona Invalida", msg);
		}
	}
	@Test
	public void testValidarTrajetoMessagemErro() {
		try {
			servCarona.validarTrajeto(0);
			servCarona.validarTrajeto(-1);
			fail("Deve lanca exception-> Trajeto Invalido");
		} catch (ErroException e) {
			String msg = e.getMessage();
			assertEquals("Trajeto Invalido", msg);
		}
	}
	@Test
	public void testValidarTrajetoMessagemErroTrajetoInexistente() {
		try {
			servCarona.validarTrajeto(18);
			
			fail("Deve lanca exception-> Trajeto Inexistente");
		} catch (ErroException e) {
			String msg = e.getMessage();
			assertEquals("Trajeto Inexistente", msg);
		}
	}
	
 @Test
 public void testget_atributo_Carona_municipio_false() {
	 boolean estado = servCarona.getAtributoCaronaMunicipal(34, "ehMunicipal");
	 assertEquals(false, estado);
 }

 
 @Test
 public void test_messagem_erro_cidade_inexistente() {
	 try {
			servCarona.validarCidade(null);
			servCarona.validarCidade("");
			
			fail("Deve lanca exception-> Cidade inexistente");
		} catch (ErroException e) {
			String msg = e.getMessage();
			assertEquals("Cidade inexistente", msg);
		}
 }
	
	@Test
	public void zeraSistema() throws Exception {
		sistemadao.deletarArquivo();
	}
}
