/*
 * Universidade de Aveiro, 2017
 * Métodos Probabílisticos para Engenharia Informática
 * Mestrado Integrado em Engenharia de Computadores e Telemática
 * Marco Silva(84770) e Raquel Rainho(84891)
 */

package proj;

import java.util.HashMap;
import java.util.Map;

public class Compras {
	private int nif;
	private Map<String, Integer> detalhesDaCompra;
			// produto - quantidade
	
	public Compras(int nif, String compra) {
		this.nif = nif;
		detalhesDaCompra = new HashMap<>();
		listaDaCompra(compra);		
	}
	
	public int getNif() {
		return nif;
	}

	// exemplo: Carne: 1; Leite: 1
	private void listaDaCompra(String compra) {
		String[] aux = compra.split(", ");
		for (String elem: aux)
			detalhesDaCompra.put(elem.split(": ")[0], Integer.parseInt(elem.split(": ")[1]));
		
		// compra.split(":")[0] --> produto (chave)
		// compra.split(":")[1] --> quantidade (valor)
	}

	@Override
	public String toString() {
		String s = "";
		for (String elem: detalhesDaCompra.keySet()) {
			s += elem + ": "+ detalhesDaCompra.get(elem) + ", ";
		}
		return nif + "\t" + s.substring(0, s.length() - 2); // para nao aparecer ", " no ultimo elemento
	}
	
}
