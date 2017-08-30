package br.com.caelum.matematica.servico;

import static org.junit.Assert.*;

import org.junit.Test;

import br.com.caelum.matematica.servico.MatematicaMaluca;


public class matematicaMalucaTest {
	
	@Test
	public void valorMaiorQue30(){
		MatematicaMaluca matematica = new MatematicaMaluca();
        assertEquals(50*4, matematica.contaMaluca(50));
	}
	
	@Test
	public void valorMaiorQue10MenorQue30(){
		MatematicaMaluca mat = new MatematicaMaluca();
		
		int contaMaluca = mat.contaMaluca(20);
		
		assertEquals(60, contaMaluca);
	}
	
	@Test
	public void valorMenorQue10(){
		MatematicaMaluca mat = new MatematicaMaluca();
		
		int contaMaluca = mat.contaMaluca(5);
		
		assertEquals(10, contaMaluca);
	}

}
