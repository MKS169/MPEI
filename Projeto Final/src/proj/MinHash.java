/*
 * Universidade de Aveiro, 2017
 * Métodos Probabílisticos para Engenharia Informática
 * Mestrado Integrado em Engenharia de Computadores e Telemática
 * Marco Silva(84770) e Raquel Rainho(84891)
 */

package proj;

public class MinHash {
	private int[][] minHash;
	
	MinHash(String[][] sets){		// sets serão produtos
			
		minHash = new int[sets.length][sets.length];
		
		for(int i = 0; i < sets.length; i++){
			String[] cur = sets[i];					// cur é a linha na posição "i" de sets
			
			for(int j = 0; j < cur.length; j++){
				int minAt = hash(cur[0],j);
				
				for(int x = 1; x < cur.length; x++){
					minAt = min(minAt,hash(cur[x],j));	// escolhe o menor HashCode por linha
				}
				minHash[i][j] = minAt;
			}
		}
	}
	
	public int[][] getMinHash(){
		return minHash;
	}
	
	private int min(int a, int b){
		if(a < b)
			return a;
		else 
			return b;
	}
	
	private int hash(String s, int j){
		int hash = 151027;		// número primo -> diminui a possibilidade de hashcodes iguais

		if(j == 0)
			hash = hash*s.hashCode();
		
		else
			hash = hash*s.hashCode()*j;	
		
		return hash;
	}
}
