/*
 * Universidade de Aveiro, 2017
 * Métodos Probabílisticos para Engenharia Informática
 * Mestrado Integrado em Engenharia de Computadores e Telemática
 * Marco Silva(84770) e Raquel Rainho(84891)
 */

package proj;

public class Cliente {
	private String nome;
	private int nif;
	
	public Cliente(String nome, int nif) {
		this.nome = nome;
		this.nif = nif;
	}
	public Cliente(String linha) {
		this.nome = linha.split("\t")[0];
		this.nif = Integer.parseInt(linha.split("\t")[1]);
	}
	
	public String getNome() {
		return nome;
	}

	public int getNif() {
		return nif;
	}
	
	@Override
	public String toString() {
		return nome + "\t" + nif;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;

		Cliente c = (Cliente) obj;
		if (nif != c.getNif())
			return false;
		if (nome == null) {
			if (c.getNome() != null)
				return false;
		} else if (!nome.equals(c.getNome()))
			return false;
		return true;
	}	
}
