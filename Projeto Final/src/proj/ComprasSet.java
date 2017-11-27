package proj;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ComprasSet {
	private static List<Compras> comprasSet;
	
	public ComprasSet() {
		comprasSet = new ArrayList<>();
		addComprasFromFile();
	}
	
	private void addComprasFromFile() {
		List<String> aux = new ArrayList<>();
		FileRdWr.readFile("Compras.txt", aux);
		Compras c;
		for (String elem: aux) {
			c = new Compras(Integer.parseInt(elem.split("\t")[0]), elem.split("\t")[1]);
			comprasSet.add(c);
		}
	}
	
	public LinkedList<Compras> comprasDoCliente(int nif){
		LinkedList<Compras> aux = new LinkedList<>();
		for (Compras elem: comprasSet)
			if(elem.getNif() == nif)
				aux.add(elem);
		return aux;
	}
}
// CONJUNTO DE COMPRAS EXISTENTES ATE AGORA
// FALTA FAZER O TOSTRING()