package br.com.caelum.leilao.servico;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.hasItems;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;

public class AvaliadorTest {

	private Avaliador leiloeiro;
	private Usuario usu1;
	private Usuario usu2;
	private Usuario usu3;
	private Usuario usu4;

	@Before
	public void setUp() {
		System.out.println("inicializando teste!");

		leiloeiro = new Avaliador();

		usu1 = new Usuario("David");
		usu2 = new Usuario("Leticia");
		usu3 = new Usuario("VH");
		usu4 = new Usuario("Teste");
	}

	@After
	public void finaliza() {
		System.out.println("fim");
	}

	@BeforeClass
	public static void testandoBeforeClass() {
		System.out.println("before class");
	}

	@AfterClass
	public static void testandoAfterClass() {
		System.out.println("after class");
	}

	@Test(expected = RuntimeException.class)
	public void naoDeveAceitarAvaliarSemLances() {
		Leilao leilao = new LeilaoBuilder().para("teste").construir();
		leiloeiro.avalia(leilao);
	}

	@Test(expected = IllegalArgumentException.class)
	public void valorDoLanceInvalido() {
		@SuppressWarnings("unused")
		Lance lance = new Lance(new Usuario("David"), 0.0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void valorDoLanceNegativo() {
		@SuppressWarnings("unused")
		Lance lance = new Lance(new Usuario("David"), 0.0);
	}

	@Test
	public void verificarMaiorEMenorLanceTest() {
		Leilao leilao = new LeilaoBuilder().para("pc gamer").lance(usu1, 4000.0).lance(usu2, 400.0).lance(usu3, 3000.0)
				.lance(usu4, 30.0).construir();

		leiloeiro.avalia(leilao);

		assertThat(leiloeiro.getMenorDeLance(), is(closeTo(30.0, 0.0001)));
		assertThat(leiloeiro.getMaiorLance(), is(closeTo(4000.0, 0.0001)));
		assertThat(leiloeiro.getMediaDeLance(), is(closeTo(1857.5, 0.0001)));
	}

	@Test
	public void verificarLanceOrdemCrescenteTest() {
		Leilao leilao = new LeilaoBuilder().para("PC").lance(usu1, 40).lance(usu2, 400).lance(usu3, 3000).construir();

		leiloeiro.avalia(leilao);

		assertThat(leiloeiro.getMenorDeLance(), is(closeTo(40.0, 0.1)));
		assertThat(leiloeiro.getMaiorLance(), is(closeTo(3000.0, 0.1)));
		assertThat(leiloeiro.getMediaDeLance(), is(closeTo(1146.6, 0.1)));
	}

	@Test
	public void verificarLanceOrdemDecrescenteTest() {
		Leilao leilao = new LeilaoBuilder().para("PC").lance(usu1, 3000.0).lance(usu2, 400.0).lance(usu3, 40.0)
				.construir();

		leiloeiro.avalia(leilao);

		assertThat(leiloeiro.getMenorDeLance(), is(closeTo(40.0, 0.1)));
		assertThat(leiloeiro.getMaiorLance(), is(closeTo(3000.0, 0.1)));
		assertThat(leiloeiro.getMediaDeLance(), is(closeTo(1146.6, 0.1)));
	}

	@Test
	public void verificaUmLance() {
		Leilao leilao = new LeilaoBuilder().para("PC").lance(usu1, 4000).construir();

		leiloeiro.avalia(leilao);

		assertThat(leiloeiro.getMaiorLance(), is(closeTo(4000.0, 0.0001)));
		assertThat(leiloeiro.getMenorDeLance(), is(closeTo(4000.0, 0.0001)));
		assertThat(leiloeiro.getMediaDeLance(), is(closeTo(4000.0, 0.0001)));
	}

	@Test
	public void verificarOS3UltimosLancesTest() {
		Leilao leilao = new LeilaoBuilder().para("PC").lance(usu1, 4000).lance(usu2, 400).lance(usu3, 3000)
				.lance(usu4, 30000).construir();

		leiloeiro.avalia(leilao);

		List<Double> valores = leiloeiro.get3UltimosValores();

		assertEquals(3, valores.size());

		assertThat(valores, hasItems(30000.0, 4000.0, 3000.0));
	}

	@Test
	public void verificaDobraUltimoLance() {
		Leilao leilao = new LeilaoBuilder().para("PC").lance(usu1, 3000).lance(usu2, 400).lance(usu3, 3000)
				.lance(usu1, 4000.0).lance(usu4, 30).construir();

		leilao.dobraLance(usu1);

		leiloeiro.avalia(leilao);

		assertThat(leilao.getUltimoLance(usu1).getValor(), closeTo(8000.0, 0.0001));
	}

	@Test
	public void temUmLance() {
		Leilao leilao = new LeilaoBuilder().para("Macbook Pro 15").construir();
        assertEquals(0, leilao.getLances().size());

        Lance lance = new Lance(new Usuario("Steve Jobs"), 2000);
        leilao.propoe(lance);

        assertThat(leilao.getLances().size(), equalTo(1));
        assertThat(leilao, LeilaoMatcher.temUmLance(lance));
	}
}
