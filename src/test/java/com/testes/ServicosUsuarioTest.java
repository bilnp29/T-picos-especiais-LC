package com.testes;

import static org.junit.Assert.*;

import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import com.dominio.Usuario;
import com.dominio.dao.SistemaDao;
import com.servicos.usuario.ControleUsuario;
import com.servicos.usuario.ServicosUsuario;

/**
 * @author Bruno
 * 
 */
public class ServicosUsuarioTest {

	private Usuario usuarioLoginValido;
	private Usuario usuarioEmailValido;

	private ControleUsuario controle;
	private ServicosUsuario servicosUsuario;

	private Usuario usuarioLoginInvalido;
	private Usuario usuarioLoginNull;

	private Usuario usuarioNomeInvalido;
	private Usuario usuarioNomeNull;

	private Usuario usuarioEmailInvalido;
	private Usuario usuarioEmailNull;
	private Usuario usuarioEmailDuplicado;

	private SistemaDao sistemadao;

	@Before 
	public void setUp() {
		usuarioLoginInvalido = new Usuario("", "senha", "nome", "endereco", "email", "telefone");
		usuarioLoginNull = new Usuario(null, "senha", "nome", "endereco", "email", "telefone");

		usuarioNomeInvalido = new Usuario("login", "senha", "", "endereco", "email", "telefone");
		usuarioNomeNull = new Usuario("login", "senha", null, "endereco", "email", "telefone");

		usuarioEmailInvalido = new Usuario("login", "senha", "nome", "endereco", "", "telefone");
		usuarioEmailNull = new Usuario("login", "senha", "nome", "endereco", null, "telefone");

		usuarioLoginValido = new Usuario("bruno29", "1111", "bruno29", "projetada I", "bruno@gmail.com", "32329098");
		usuarioEmailValido = new Usuario("xpto", "1111", "bruno29", "projetada I", "bruno@gmail.com", "32329098");
		usuarioEmailDuplicado = new Usuario("antonio", "1111", "bruno29", "projetada I", "bruno@gmail.com", "32329098");

		controle = new ControleUsuario();
		servicosUsuario = new ServicosUsuario();

		sistemadao = new SistemaDao();
	}

	/**
	 * O teste abaixo deve permitir cadastra um usuario.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testDevePermitirCadastraUsuario() throws Exception {
		controle.cadastraUsuario("bruno29", "1111", "bruno", "projetada I", "bruno@gmail.com", "32329098");

	}

	@Test
	public void testDevePesquisarAtributoUsuarioNome() throws Exception {
		controle.cadastraUsuario("bruno29", "1111", "bruno", "projetada I", "bruno@gmail.com", "32329098");
		controle.localizarUsuario("bruno29", "nome");
		assertEquals("bruno", "bruno");

	}

	@Test
	public void testDevePesquisaAtributoEndereco() throws Exception {
		controle.cadastraUsuario("bruno29", "1111", "bruno", "projetada I", "bruno@gmail.com", "32329098");
		controle.localizarUsuario("bruno29", "endereco");
		assertEquals("projetada I", "projetada I");
	}

	@Test
	public void testVerificarMessagenErroEmailDuplicado() {

		try {
			servicosUsuario.adicionarUsuario("xpto", usuarioEmailValido);
			servicosUsuario.adicionarUsuario("Antonio", usuarioEmailDuplicado);
			fail("Deve lanca exception-> Já existe um usuário com este email");
		} catch (Exception e) {
			String menssage = e.getMessage();
			assertEquals("Já existe um usuário com este email", menssage);
		}
	}

	@Test
	public void testVerificarMenssagenErroLoginDuplicado() {

		try {
			servicosUsuario.verificaUsuario("bruno29", usuarioLoginValido);
			servicosUsuario.verificaUsuario("bruno29", usuarioLoginValido);
			fail("Deve lanca exception-> Já existe um usuário com este login");
		} catch (Exception e) {
			String menssage = e.getMessage();
			assertEquals("Já existe um usuário com este login", menssage);
		}
	}

	@Test
	public void testVerificarMenssagenErroLoginInvalido() {
		try {
			servicosUsuario.verificaUsuario("", usuarioLoginInvalido);
			servicosUsuario.verificaUsuario(null, usuarioLoginNull);
			fail("Deve lanca exception-> Login invalido");
		} catch (Exception e) {
			String menssage = e.getMessage();
			assertEquals("Login invalido", menssage);
		}
	}

	@Test
	public void testVerificarMenssagenErroNomeInvalido() {
		try {
			servicosUsuario.verificaUsuario("login", usuarioNomeNull);
			servicosUsuario.verificaUsuario("login", usuarioNomeInvalido);
			fail("Deve lanca exception-> Nome invalido");
		} catch (Exception e) {
			String menssage = e.getMessage();
			assertEquals("Nome invalido", menssage);
		}
	}

	@Test
	public void testVerificarMenssagenErroEmailInvalido() {

		try {
			servicosUsuario.verificaUsuario("login", usuarioEmailInvalido);
			servicosUsuario.verificaUsuario("login", usuarioEmailNull);
			fail("Deve lanca exception-> Email invalido");
		} catch (Exception e) {
			String menssage = e.getMessage();
			assertEquals("Email invalido", menssage);
		}
	}

	@Test
	public void testDeveAbrirSessaoComDadosValidos() throws Exception {
		controle.cadastraUsuario("bruno29", "1111", "bruno", "projetada I", "bruno@gmail.com", "32329098");
		controle.entraSistema("bruno29", "1111");

	}

	@Test
	public void testDeveVerificarMenssagemDeErroAoAbrirSessaoLoginIvalido() {

		try {
			controle.cadastraUsuario("bruno29", "1111", "bruno", "projetada I", "bruno@gmail.com", "32329098");
			servicosUsuario.validarUsuario(null, "bruno29");
			servicosUsuario.validarUsuario("","bruno29");
			servicosUsuario.validarUsuario("bruno29", "teste");
			servicosUsuario.validarUsuario("bruno29", "testedois");
			fail("Deve lanca exception -> Login invalido");
		} catch (Exception e) {
			String message = e.getMessage();
			assertEquals("Login invalido", message);
		}
	}

	@Test
	public void testDeveVerificarMenssagemDeErroAbrirSessaoSenhaIncorreta() {

		try {
			controle.cadastraUsuario("bruno29", "1111", "bruno", "projetada I", "bruno@gmail.com", "32329098");
			servicosUsuario.validarUsuario("mateus11", "2456");
			fail("Deve lanca exception -> Usuario inexistente");
		} catch (Exception e) {
			String message = e.getMessage();
			assertEquals("Usuario inexistente", message);
		}
	}

	@Test
	public void testDeveVerificarMenssagemErroAtributoLogin() {

		try {
			controle.cadastraUsuario("bruno29", "1111", "bruno", "projetada I", "bruno@gmail.com", "32329098");
			servicosUsuario.validarLogin(null);
			servicosUsuario.validarLogin("");
			fail("Deve lanca exception -> Login invalido");
		} catch (Exception e) {
			String message = e.getMessage();
			assertEquals("Login invalido", message);
		}
	}

	@Test
	public void testDeveVerificarMenssagemErroUsuarioInexistente() {
		try {
			controle.cadastraUsuario("bruno29", "1111", "bruno", "projetada I", "bruno@gmail.com", "32329098");
			servicosUsuario.validarLogin("xpto");
			fail("Deve lanca exception -> Usuario inexistente");
		} catch (Exception e) {
			String message = e.getMessage();
			assertEquals("Usuario inexistente", message);
		}
	}

	@Test
	public void testDeveVerificarMenssagemErroAtributoInvalido() {

		try {
			controle.cadastraUsuario("maria", "1111", "bruno", "projetada I", "bruno@gmail.com", "32329098");
			servicosUsuario.pesquisarUsuario("maria",null);
			servicosUsuario.pesquisarUsuario("maria","");
			fail("Deve lanca exception -> Atributo invalido");
		} catch (Exception e) {
			String message = e.getMessage();
			assertEquals("Atributo invalido", message);
		}
	}

	@Test
	public void testDeveVerificarMenssagemErroAtributoInexistente() {

		try {
			controle.cadastraUsuario("sandro", "1111", "sandro", "projetada II", "sandro@gmail.com", "32329098");
			servicosUsuario.pesquisarUsuario("sandro","xspt");
			fail("Deve lanca exception -> Atributo inexistente");
		} catch (Exception e) {
			String message = e.getMessage();
			assertEquals("Atributo inexistente", message);
		}
	}

	@Test
	public void testDeveZeraSistema() throws Exception {
		controle.cadastraUsuario("bruno29", "1111", "bruno", "projetada I", "bruno@gmail.com", "32329098");
		servicosUsuario.deletarDados();
	}

	@Test
	public void testDeveApagaBaseDados() throws Exception {
		sistemadao.deletarArquivo();
	}
	@Test
	public void testDeveEncerraSistema() throws Exception {
		controle.cadastraUsuario("bruno29", "1111", "bruno", "projetada I", "bruno@gmail.com", "32329098");
		controle.entraSistema("bruno29", "1111");
		//assertFalse(servicosUsuario.finalizarSistema());
	}
}
