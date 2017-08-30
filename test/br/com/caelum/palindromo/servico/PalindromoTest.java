package br.com.caelum.palindromo.servico;

import org.junit.Assert;
import org.junit.Test;

import br.com.caelum.palidromo.servico.Palindromo;

public class PalindromoTest {

	@Test
	public void ehPalindromoTest() {
		
		final String frase1 = "Socorram-me subi no onibus em Marrocos";
		final String frase2 = "Anotaram a data da maratona";
			
		Palindromo palindromo = new Palindromo();
		
		Assert.assertTrue(palindromo.ehPalindromo(frase1));
		Assert.assertTrue(palindromo.ehPalindromo(frase2));
	}

	@Test
	public void naoEhPalindromoTest() {
		
		final String frase1 = "E preciso amar as pessoas como se nao houvesse amanha";
			
		Palindromo palindromo = new Palindromo();
		
		Assert.assertFalse(palindromo.ehPalindromo(frase1));
	}
}
