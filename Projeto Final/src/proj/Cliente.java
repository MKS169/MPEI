package proj;

public class Cliente {
	private String nome;
	private Date dataNasc;
	private int nif;
	
	public Cliente(String nome, Date dataNasc, int nif) {
		this.nome = nome;
		this.dataNasc = dataNasc;
		this.nif = nif;
	}
	public Cliente(String linha) {
		this.nome = linha.split("\t")[0];
		this.dataNasc = new Date(linha.split("\t")[1]);
		this.nif = Integer.parseInt(linha.split("\t")[2]);
	}
	
	public String getNome() {
		return nome;
	}

	public Date getDataNasc() {
		return dataNasc;
	}

	public int getNif() {
		return nif;
	}
	
	@Override
	public String toString() {
		return nome + "\t" + dataNasc + "\t" + nif;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;

		Cliente c = (Cliente) obj;
		if (dataNasc == null) {
			if (c.getDataNasc() != null)
				return false;
		} else if (!dataNasc.equals(c.getDataNasc()))
			return false;
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
