package proj;

import java.util.HashMap;
import java.util.Map;

public class Loja implements LojaInterface{
	private String nomeLoja;
	private Map<String, Integer> produtos;
	//Bloom filter de produtos??????????????
	
	public Loja(String nomeLoja, String listaProdutos) {
		this.nomeLoja = nomeLoja;
		produtos = new HashMap<>();
		String [] aux = listaProdutos.split(", ");
		for (String elem: aux) {
			addProduto(elem.split(": ")[0], Integer.parseInt(elem.split(": ")[1]));
			//produtos.put(elem.split(": ")[0], Integer.parseInt(elem.split(": ")[1]));
			// elem.split(": ")[0] --> Nome do produto (chave)
			// elem.split(": ")[1] --> Quantidade do produto (valor)	
		}
	}

	@Override
	public boolean addProduto(String produto, int quantidade) {
		if(produto == null)
			return false;
		if(produtos.containsKey(produto)){
			int aux = produtos.get(produto) + quantidade;
			produtos.put(produto, aux);
			return true;
		}
		else{
			produtos.put(produto, quantidade);
			return true;
		}
	}

	@Override
	public boolean removeProduto(String produto) {
		if(produto == null)
			return false;
		if(produtos.containsKey(produto)){
			int aux = produtos.get(produto) - 1; // retira 1 produto da quantidade
			produtos.put(produto, aux);
			return true;
		}
		else
			return false;
	}
	public String hashToString() {
		String s = "";
		for (String elem: produtos.keySet()) {
			s += elem + ": "+ produtos.get(elem) + ", ";
		}
		return s.substring(0, s.length() - 2); // para nao aparecer ", " no ultimo elemento
	}
	
	@Override
	public String toString() {
		return nomeLoja + "\t" + hashToString();
	}
}
