/*
 * Universidade de Aveiro, 2017
 * M�todos Probab�listicos para Engenharia Inform�tica
 * Mestrado Integrado em Engenharia de Computadores e Telem�tica
 * Marco Silva(84770) e Raquel Rainho(84891)
 */

package proj;

public class Jaccard {
	
	private MinHash minHashes;
	private double[][] distance;
	private int[][] minHash;
	
	public Jaccard(String[][] sets){		// sets ser�o produtos
		this.minHashes = new MinHash(sets); 
		this.minHash = minHashes.getMinHash();
		this.distance = new double[minHash.length][minHash.length];
		
		int inter = 0, uni = 0; 
		double x = 0;
		
		for(int i = 0; i < minHash.length-1; i++){
			for(int j = i+1; j < minHash.length; j++){
				
				if(minHash[j] == minHash[i])		// interse��o entre minHash[i] e minHash[j]
					inter = sum(minHash[i],minHash[j]);
				else
					inter = 0;
				
				uni = minHash[j].length + minHash[i].length;	// uniao entre minHash[i] and minHash[j]		
				
				x = inter/uni;
				
				distance[i][j] = 1-x;
			}
		}
	}
	
	public double[][] getDistance(){
		return this.distance;
	}
	
	private int sum(int[] a, int[] b){
		int x = 0;
		for(int i = 0; i < a.length; i++){
			if(a[i] == b[i])
				x++;
		}
		return x;
	}
}
