/*
 * Universidade de Aveiro, 2017
 * Métodos Probabílisticos para Engenharia Informática
 * Mestrado Integrado em Engenharia de Computadores e Telemática
 * Marco Silva(84770) e Raquel Rainho(84891)
 */

package proj;

import java.util.LinkedList;

public class MinHash {
	
	private final int k = 5;									// 5 hashfunctions
	private int[][] minHash;
	private ComprasSet compra;
	private ClienteSet cliente;
	private String[][] set;
	MinHash(ComprasSet cp, ClienteSet c){						// sets serão compras por cliente organizadas pela ordem da
																// lista dos clientes para comparação
		this.compra = cp;
		this.cliente = c;
		
		this.set = createSet(cliente,compra);					// contém compras correspondente a cada cliente	
																// set[i] = array de compras do cliente "i"
		minHash = new int[cliente.size()][maxSize(set)];
		
		for(int i = 0; i < cliente.size(); i++){
			for(int j = 0; j < set[i].length; j++){
				int min = min(hash(set[i][j],k));
				minHash[i][j] = min;
			}	
		}
		System.out.println("Fim de MinHash");
	}
	public String[][] set(){
		return set;
	}
	private int maxSize(String[][] set){
		int max = 0;
		
		for(int i = 0; i < set.length; i++){
			if(max < set[i].length)
				max = set[i].length;
		}
		return max;
	}	
	
	private String[][] createSet(ClienteSet cliente, ComprasSet compra){
		String[][] set = new String[cliente.size()][compra.size()];
		
		for(int i = 0; i < cliente.size(); i++){
			LinkedList<Compras> aux = compra.comprasDoCliente(cliente.getCliente(i).getNif());
			String[] s = new String[aux.size()];
			
			for(int j = 0; j < aux.size(); j++){
				String[] a = aux.get(j).compra().split(": ");
				s[j] = a[0];
			}
			set[i] = s;
		}
		return set;
	}
	
	public int[][] getMinHash(){
		return minHash;
	}
	
	private int min(int[] hash){
		int min = Integer.MAX_VALUE;
		for(int x: hash)
			if(x < min)
				min = x;
		return min;
	}
	
	private int[] hash(String s, int k){
		int[] h = new int[k];
		
		for(int j = 0; j < k; j++){
			int hash = 2017+k;
			
			for(int i = 0; i < s.length(); i++){
				hash = (int) ((hash*i*k + s.charAt(i))%(Math.pow(2, 32)-1));
				if(hash < 0)
					hash = -hash;
			}
			h[j] = hash;
		}
	    return h;
	}
}
