/*
 * Universidade de Aveiro, 2017
 * M�todos Probab�listicos para Engenharia Inform�tica
 * Mestrado Integrado em Engenharia de Computadores e Telem�tica
 * Marco Silva(84770) e Raquel Rainho(84891)
 */

package proj;

// n -> tamanho do conjunto
// k -> numero de hashFunctions
// CountFilter

public class CountFilter {
	private int n, k;
	private final double factor = 0.8;					// elevado factor de carga
	private int[] bloom;
	
	CountFilter(int len, int hash){
		this.n = len;
		this.k = hash;
		this.bloom = new int[(int)(n/factor)];
		for (int i=0; i<(int)(n/factor);i++) {
			bloom[i]=0;
		}
	}
	
	void bloomInsertion(String s){						// s ser� um produto
		int bloomPos;
		
		for(int i = 1; i <= k; i++){					// i = 0 -> hash = 0
			s = s.concat(String.valueOf(i));			// acrescenta sequencialmente o valor em "i" no final da string
			bloomPos = hash(s)%bloom.length;			// come�ando em 0 e at� a um valor m�ximo k-1
			bloom[bloomPos] += 1;
		}
	}
	
	void bloomInsertion(String s, int qt){				// s ser� um produto, qt ser� quantidade
		int bloomPos;
		
		for(int i = 1; i <= k; i++){					// i = 0 -> hash = 0
			s = s.concat(String.valueOf(i));			// acrescenta sequencialmente o valor em "i" no final da string
			bloomPos = hash(s)%bloom.length;			// come�ando em 0 e at� a um valor m�ximo k-1
			bloom[bloomPos] += qt;
		}
	}
	
	void bloomRemove(String s){							// s ser� um produto, qt ser� quantidade
		int bloomPos;
		
		for(int i = 1; i <= k; i++){					// i = 0 -> hash = 0
			s = s.concat(String.valueOf(i));			// acrescenta sequencialmente o valor em "i" no final da string
			bloomPos = hash(s)%bloom.length;			// come�ando em 0 e at� a um valor m�ximo k-1
			if(bloom[bloomPos]>0)
				bloom[bloomPos] -= 1;
			else
				bloom[bloomPos] = 0;
		}
	}
	
	boolean isMember(String s){
		int bloomPos;
		System.out.println("2");
		for(int i = 1; i <= k; i++){					// i = 0 -> hash = 0;
			s = s.concat(String.valueOf(i));			// acrescenta sequencialmente o valor em "i" no final da string 
			bloomPos = hash(s)%bloom.length;			// come�ando em 0 e at� a um valor m�ximo k-1

			if(bloom[bloomPos] == 0)
				return false;
		}
		return true;
	}
	int count(String s) {
		int bloomPos;
		int minValue = Integer.MAX_VALUE;
		for(int i = 1; i <= k; i++){					// i = 0 -> hash = 0;
			s = s.concat(String.valueOf(i));			// acrescenta sequencialmente o valor em "i" no final da string 
			bloomPos = hash(s)%bloom.length;
			if(bloom[bloomPos]<minValue)
				minValue=bloom[bloomPos];
		}
		return minValue;
	}
	
	double falsePositives(){
		double count = 0;
		
		for(int i = 0; i < bloom.length; i++){
			if(bloom[i] >= 1)
				count++;
		}
		double d = (double) (1.0/n);
		count = count*k;
		double x = Math.pow(1-d,count);
		x = 1 - x;
		x = Math.pow(x, k);
		return x;
	}
	
	int[] getBloom(){
		return bloom;
	}
	
	int hash(String s){
		int hash = 16573;
		
		for(int i = 0; i < s.length(); i++){
			hash = (int) ((hash*i + s.charAt(i))%(Math.pow(2, 32)-1));
			if(hash < 0)
				hash = -hash;
		}
	    return hash;
	}
}
