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
		String head = "NIF\tCompras\tLoja";
		FileRdWr.writeFile("Compras.txt", comprasSet, head, false);
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
		public LinkedList<String> produtosCompradosPeloCliente(int nif){
		LinkedList<String> aux = new LinkedList<>();
		
		for (Compras compras: comprasSet)
			if(compras.getNif() == nif) {
				String[] produtos = compras.compra().split(", ");
			
				for(int i=0; i<produtos.length; i++) {
					if(!aux.contains(produtos[i].split(": ")[0])){
						aux.add(produtos[i].split(": ")[0]);
					}
				}
			}
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
