/*
 * Universidade de Aveiro, 2017
 * Métodos Probabílisticos para Engenharia Informática
 * Mestrado Integrado em Engenharia de Computadores e Telemática
 * Marco Silva(84770) e Raquel Rainho(84891)
 */

package proj;
// n -> tamanho do conjunto
// k -> numero de hashFunctions
// CountFilter

public class BloomFilter {
	private int n, k;
	private final double factor = 0.8;	// elevado factor de carga
	private int[] bloom;
	
	BloomFilter(int len, int hash){
		this.n = len;
		this.k = hash;
		this.bloom = new int[(int)(n/factor)];
	}
	
	void bloomInsertion(String s){		// s será um produto
		int bloomPos;
		for(int i = 0; i < k; i++){
			s = s.concat(String.valueOf(i));		// acrescenta sequencialmente o valor em "i" no final da string
			bloomPos = string2Hash(s)%bloom.length;	// começando em 0 e até a um valor máximo k-1
			if(isMember(s))
				bloom[bloomPos] += 1;
			else
				bloom[bloomPos] = 1;	
		}
	}
	
	boolean isMember(String s){
		int bloomPos;
		for(int i = 0; i < k; i++){
			s = s.concat(String.valueOf(i));			// acrescenta sequencialmente o valor em "i" no final da string 
			bloomPos = string2Hash(s)%bloom.length;		// começando em 0 e até a um valor máximo k-1
			if(bloom[bloomPos] >= 1)
				return true;
		}
		return false;
	}
	
	int[] getBloom(){
		return bloom;
	}
	
	static int string2Hash(String s){
		int hash = 102871;		// número primo -> diminui a possibilidade de hashcodes iguais
		
		for(int i = 0; i < s.length(); i++)
			hash = hash*31*i + s.charAt(i);
		
		return hash; 
	}
}
