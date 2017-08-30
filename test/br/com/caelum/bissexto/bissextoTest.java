package br.com.caelum.bissexto;

import static org.junit.Assert.*;

import org.junit.Test;

public class bissextoTest {

	@Test
	public void ehBissextoTest(){
		int ano = 2016;
		boolean ehAnoBissexto = false;
		
		ehAnoBissexto = Bissexto.validarAno(ano);
		
		assertTrue(ehAnoBissexto);
	}
	
	@Test
	public void naoEhBissextoTest(){
		int ano = 2015;
		boolean naoEhAnoBissexto = false;
		
		naoEhAnoBissexto = Bissexto.validarAno(ano);
		
		assertFalse(naoEhAnoBissexto);
	}
}
