/*
 * Universidade de Aveiro, 2017
 * Métodos Probabílisticos para Engenharia Informática
 * Mestrado Integrado em Engenharia de Computadores e Telemática
 * Marco Silva(84770) e Raquel Rainho(84891)
 */

package proj;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

// É nesta classe que se vai ler o ficheiro DadosCliente
public class ClienteSet {
	private List<Cliente> clienteSet;
	private Map<Integer, LinkedList<Compras>> comprasFeitasPeloCliente;
	private ComprasSet comprasSet;
	
	public ClienteSet() {
		clienteSet = new ArrayList<>();
		comprasFeitasPeloCliente = new HashMap<>();
		comprasSet = new ComprasSet();
		addClienteFromFile();
	}
	
	public void addClienteFromFile() {
		List<String> aux = new ArrayList<>();
		FileRdWr.readFile("DadosCliente.txt", aux);
		Cliente c;
		for (String elem: aux) {
			c = new Cliente(elem);
			clienteSet.add(c);
			comprasFeitasPeloCliente.put(c.getNif(), comprasSet.comprasDoCliente(c.getNif()));
		}
	}
	
	public int size(){
		return clienteSet.size();
	}
	
	// Depois de já ter sido lido o ficheiro, podemos querer adicionar novos clientes
	public boolean addCliente(Cliente c) {
		if (c == null) return false;
		for(Cliente client : clienteSet)
			if (client.equals(c)) return false;
		clienteSet.add(c);
		
		return true;
	}
	
	public Cliente getCliente(int index){
		return clienteSet.get(index);
	}
	
	public void printClientes() {
		clienteSet.forEach(System.out::println);
	}

	public void printToFile() {
		List<Cliente> fich = new ArrayList<>();
		List<Cliente> nEqual = new ArrayList<>();
		String head = "Novo Conteudo";
		FileRdWr.readFile("DadosCliente.txt", fich);
		
		nEqual.addAll(fich);
		nEqual.addAll(clienteSet);
		nEqual.removeAll(fich);
		
		FileRdWr.writeFile("DadosCliente.txt", nEqual, head, true);
	}
		
	public boolean clienteExiste(int nif) {
		for(int i = 0; i<clienteSet.size(); i++) {
			if(clienteSet.get(i).getNif()==nif) {
				return true;
			}
		}
		return false;
	}
		
		public void listaDeSugestoes(int nif, ComprasSet comprasSet, ClienteSet clienteSet) {
		MinHash minHash = new MinHash(comprasSet, clienteSet);
		Jaccard jaccard = new Jaccard(minHash);
		DistanceFilter df = new DistanceFilter(clienteSet, jaccard.getDistance(), 0.5);
		int nifClienteSemelhante=0;
		
		for(int i=0; i<df.similares().length; i++) {
			if (Integer.parseInt(df.similares()[i].split(" ")[0])==nif) {
				nifClienteSemelhante = Integer.parseInt(df.similares()[i].split(" ")[1]);
				break;
			}else if (Integer.parseInt(df.similares()[i].split(" ")[1])==nif) {
				nifClienteSemelhante = Integer.parseInt(df.similares()[i].split(" ")[0]);
				break;
			}
		}	
		out.println("Cliente semelhante: " + nifClienteSemelhante);
		out.println("Poderá querer comprar os seguintes produtos: ");
		LinkedList<String> comprasDoSemelhante = comprasSet.produtosCompradosPeloCliente(nifClienteSemelhante);
		LinkedList<String> comprasDoNif = comprasSet.produtosCompradosPeloCliente(nif);
		int count;
		for(String produtoSem: comprasDoSemelhante) {
			count=0;
			for(String produto: comprasDoNif) {
				if(produto.equals(produtoSem)) {count++;}
			}
			if(count==0) System.out.println(produtoSem);
		}
		if(nifClienteSemelhante==0) out.println("Sem sugestões de produtos, de momento...");
	}
	
	@Override
	public String toString(){
		String s = "";
		
		for(Cliente c: clienteSet)
			s += c.toString() + "\n";
		
		return s;
	}
}
