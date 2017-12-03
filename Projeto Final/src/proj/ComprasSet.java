/*
 * Universidade de Aveiro, 2017
 * Métodos Probabílisticos para Engenharia Informática
 * Mestrado Integrado em Engenharia de Computadores e Telemática
 * Marco Silva(84770) e Raquel Rainho(84891)
 */

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
	
	public boolean addCompra(Compras compra) {
		if(compra==null) return false;
		comprasSet.add(compra);
		return true;
	}
	
	public void printToFile() {
		List<Compras> fich = new ArrayList<>();
		List<Compras> nEqual = new ArrayList<>();
		String head = "Novo Conteudo";
		FileRdWr.readFile("Compras.txt", fich);
		
		nEqual.addAll(fich);
		nEqual.addAll(comprasSet);
		nEqual.removeAll(fich);
		
		FileRdWr.writeFile("Compras.txt", nEqual, head, true);
	}
		
	public Compras getCompras(int index){
		return comprasSet.get(index);
	}
	
	public int size(){
		return comprasSet.size();
	}
	
	public LinkedList<Compras> comprasDoCliente(int nif){
		LinkedList<Compras> aux = new LinkedList<>();
		for (Compras elem: comprasSet)
			if(elem.getNif() == nif)
				if(!aux.contains(elem))
					aux.add(elem);
		return aux;
	}
	
	@Override
	public String toString(){
		String s = "";
		for(Compras c: comprasSet){
			s += c.toString() + "\n";
		}
		return s;
	}
}