/*
 * Universidade de Aveiro, 2017
 * Métodos Probabílisticos para Engenharia Informática
 * Mestrado Integrado em Engenharia de Computadores e Telemática
 * Marco Silva(84770) e Raquel Rainho(84891)
 */

package proj;

import java.util.List;

public class MinHash {
	
	private int[][] minHash;
	
	MinHash(List<List<String>> sets){						// sets serão compras por cliente organizadas pela ordem da
															// lista dos clientes para comparação
		int size = maxSize(sets);
		
		minHash = new int[sets.size()][size];
		
		for(int i = 0; i < sets.size(); i++){
			List<String> cur = sets.get(i);			
			
			for(int j = 0; j < cur.size(); j++){
				int minAt = hash(cur.get(0));
				
				for(int x = 1; x < cur.size(); x++){
					minAt = min(minAt,hash(cur.get(x)));	// escolhe o menor HashCode por linha
						
				}
				minHash[i][j] = minAt;
			}
		}
	}
	
	public int[][] getMinHash(){
		return minHash;
	}
	
	private int maxSize(List<List<String>> sets){
		int t = 0;
		
		for(List<String> aux: sets){
			if(aux.size() > t)
				t = aux.size();
		}
		
		return t;
	}
	
	private int min(int a, int b){
		if(a < b)
			return a;
		else 
			return b;
	}
	
	private int hash(String s){
		int hash = 16573;
		
		for(int i = 0; i < s.length(); i++){
			hash = (int) ((hash*i + s.charAt(i))%(Math.pow(2, 32)-1));
			if(hash < 0)
				hash = -hash;
		}
		
	    return hash;
	}
}
