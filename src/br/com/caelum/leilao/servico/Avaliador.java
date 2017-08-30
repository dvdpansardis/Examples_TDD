package br.com.caelum.leilao.servico;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;

public class Avaliador {

	private double maiorDeTodos = Double.NEGATIVE_INFINITY;
	private double menorDeTodos = Double.NEGATIVE_INFINITY;
	private double mediaDeTodos = Double.NEGATIVE_INFINITY;
	private List<Double> valores;

	public void avalia(Leilao leilao) {
		if(leilao.getLances().isEmpty()) {
			throw new RuntimeException("Não existe lances");
		}
		maiorDeTodos = leilao.getLances().stream().mapToDouble(Lance::getValor).max().getAsDouble();
		menorDeTodos = leilao.getLances().stream().mapToDouble(Lance::getValor).min().getAsDouble();
		mediaDeTodos = leilao.getLances().stream().mapToDouble(Lance::getValor).average().getAsDouble();

		valores = leilao.getLances().stream().map(Lance::getValor).sorted(Comparator.reverseOrder()).collect(Collectors.toList());
	}

	public double getMaiorLance() {
		return maiorDeTodos;
	}

	public double getMenorDeLance() {
		return menorDeTodos;
	}

	public double getMediaDeLance() {
		return mediaDeTodos;
	}

	public List<Double> get3UltimosValores() {
		return valores.subList(0, valores.size() < 3 ? valores.size() - 1 : valores.size() - 1 );
	}
}
