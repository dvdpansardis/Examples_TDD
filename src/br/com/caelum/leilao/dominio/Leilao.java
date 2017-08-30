package br.com.caelum.leilao.dominio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Leilao {

	private String descricao;
	private List<Lance> lances;

	public Leilao(String descricao) {
		this.descricao = descricao;
		this.lances = new ArrayList<Lance>();
	}

	// If is validated at sequence.
	public void propoe(Lance lance) {
		if (lances.isEmpty() || podeDarLance(lance.getUsuario()))
			lances.add(lance);
	}

	private boolean podeDarLance(Usuario usuario) {
		return !getUltimoLance().getUsuario().equals(usuario) && getQuantidadeLances(usuario) < 5;
	}

	public long getQuantidadeLances(Usuario usuario) {
		return lances.stream().filter(l -> l.getUsuario().equals(usuario)).count();
	}

	private Lance getUltimoLance() {
		return lances.get(lances.size() - 1);
	}

	public String getDescricao() {
		return descricao;
	}

	public List<Lance> getLances() {
		return Collections.unmodifiableList(lances);
	}

	public void dobraLance(Usuario usuario) {
		Lance lance = getUltimoLance(usuario);
		if (lance != null)
			propoe(new Lance(usuario, lance.getValor() * 2));
	}

	public Lance getUltimoLance(Usuario usuario) {
		for (int i = lances.size() - 1; i >= 0; i--) {
			if (usuario.equals(lances.get(i).getUsuario()))
				return lances.get(i);
		}
		return null;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((lances == null) ? 0 : lances.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Leilao other = (Leilao) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (lances == null) {
			if (other.lances != null)
				return false;
		} else if (!lances.equals(other.lances))
			return false;
		return true;
	}
	
	

}
