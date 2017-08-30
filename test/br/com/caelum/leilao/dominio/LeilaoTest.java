package br.com.caelum.leilao.dominio;

import static org.junit.Assert.*;

import org.junit.Test;

public class LeilaoTest {

	@Test
	public void umLanceTest() {
		Leilao leiloeiro = new Leilao("Teste");
		leiloeiro.propoe(new Lance(new Usuario("david"), 100.0));
		assertEquals(1, leiloeiro.getLances().size());
		assertEquals(100.0, leiloeiro.getLances().get(0).getValor(), 0.0001);
	}

	@Test
	public void maisDeUmLanceTest() {
		Leilao leiloeiro = new Leilao("Teste");
		leiloeiro.propoe(new Lance(new Usuario("david"), 100.0));
		leiloeiro.propoe(new Lance(new Usuario("leticia"), 1000.0));
		assertEquals(2, leiloeiro.getLances().size());
		assertEquals(100.0, leiloeiro.getLances().get(0).getValor(), 0.0001);
		assertEquals(1000.0, leiloeiro.getLances().get(1).getValor(), 0.0001);
	}

	@Test
	public void naoDeveAceitarDoisLancesSeguidosDoMesmoUsuarioTest() {
		Leilao leiloeiro = new Leilao("Teste");
		leiloeiro.propoe(new Lance(new Usuario("david"), 100.0));
		leiloeiro.propoe(new Lance(new Usuario("david"), 1000.0));
		assertEquals(1, leiloeiro.getLances().size());
		assertEquals(100.0, leiloeiro.getLances().get(0).getValor(), 0.0001);
	}

	@Test
	public void naoDeveAceitarMaisDe5LancesDoMesmoUsuario() {
		Leilao leiloeiro = new Leilao("Teste");

		leiloeiro.propoe(new Lance(new Usuario("david"), 100.0));
		leiloeiro.propoe(new Lance(new Usuario("leticia"), 1000.0));

		leiloeiro.propoe(new Lance(new Usuario("david"), 200.0));
		leiloeiro.propoe(new Lance(new Usuario("leticia"), 2000.0));

		leiloeiro.propoe(new Lance(new Usuario("david"), 300.0));
		leiloeiro.propoe(new Lance(new Usuario("leticia"), 3000.0));

		leiloeiro.propoe(new Lance(new Usuario("david"), 400.0));
		leiloeiro.propoe(new Lance(new Usuario("leticia"), 4000.0));

		leiloeiro.propoe(new Lance(new Usuario("david"), 500.0));
		leiloeiro.propoe(new Lance(new Usuario("leticia"), 5000.0));

		leiloeiro.propoe(new Lance(new Usuario("david"), 600.0));

		assertEquals(10, leiloeiro.getLances().size());
		assertEquals(5000.0, leiloeiro.getLances().get(leiloeiro.getLances().size() - 1).getValor(), 0.00001);
	}
}
