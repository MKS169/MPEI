package proj;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LojaSet {
	private List<Loja> lojaSet;
	
	public LojaSet() {
		lojaSet = new LinkedList<>();
		addLojasFromFile();
	}
	
	private void addLojasFromFile() {
		List<String> aux = new ArrayList<>();
		FileRdWr.readFile("Stock.txt", aux);
		for (String elem: aux) {
			lojaSet.add(new Loja(elem.split("\t")[0], elem.split("\t")[1]));
		}
	}
	public boolean addLoja(Loja l) {
		if (l == null) return false;
		for(Loja loja : lojaSet)
			if (loja.equals(l)) return false;
		lojaSet.add(l);
		
		return true;
	}
	
	public Loja loja(String nomeLoja) {
		for(Loja loja: lojaSet)
			if(loja.getNomeLoja().equals(nomeLoja)) return loja;
		return null;
	}
	
	public int size() {
		return lojaSet.size();
	}
	
	public boolean removeLoja(Loja l){
		if(l == null)
			return false;
		if(!lojaSet.contains(l))
			return false;
		else{
			lojaSet.remove(l);
			return true;
		}
	}

	//VERIFICAR METODO
	public void printToFile() {
		List<Loja> fich = new ArrayList<>();
		List<Loja> nEqual = new ArrayList<>();
		String head = "Novo Conteudo";
		FileRdWr.readFile("Stock.txt", fich);
		
		nEqual.addAll(fich);
		nEqual.addAll(lojaSet);
		nEqual.removeAll(fich);
		
		FileRdWr.writeFile("Stock.txt", nEqual, head, true);
	}
	
	// função redundante??
	public void printLojas() {
		lojaSet.forEach(System.out::println);
	}
	
	public String nomeLojas() {
		String s="";
		for(int i=0; i<lojaSet.size(); i++) {
			s+= lojaSet.get(i).getNomeLoja() + ", ";
		}
		return s.substring(0,s.length()-2);
	}


	public boolean lojaExiste(String nomeLoja) {
		for(int i = 0; i<lojaSet.size(); i++) {
			if(lojaSet.get(i).getNomeLoja().equalsIgnoreCase(nomeLoja)) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public String toString() {
		String s = "";
		for (Loja loja: lojaSet) {
			s+= loja + "\n";
		}
		return s;
	}

	public boolean produtoExiste(String nomeLoja, String produto, int quantidade) {
		for(Loja loja: lojaSet) {
			if(loja.getNomeLoja().equals(nomeLoja) && loja.contains(produto) && loja.getQuantidade(produto)-quantidade>=0) 
				return true;
		}
		return false;
		
	}
}
