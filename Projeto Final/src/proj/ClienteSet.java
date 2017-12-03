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
		clienteSet = new ArrayList<>();// VER MELHOR FORMA DE FAZER ISTO!!
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
		
	public void listaDeSugestoes(int nif) {
		//FALTA FAZER ISTO!!!
		System.out.println("FALTA CRIAR AINDA!");
	}
	
	@Override
	public String toString(){
		String s = "";
		
		for(Cliente c: clienteSet)
			s += c.toString() + "\n";
		
		return s;
	}
}