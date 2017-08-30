package br.com.caelum.leilao.servico;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;

public class LeilaoBuilder {

	private Leilao leilao;

	public LeilaoBuilder para(String descricao) {
		leilao = new Leilao(descricao);
		return this;
	}

	public LeilaoBuilder lance(Usuario usu, double valor) {
		leilao.propoe(new Lance(usu, valor));
		return this;
	}
	
	public Leilao construir(){
		return this.leilao;
	}

}
