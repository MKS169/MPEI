/*
 * Universidade de Aveiro, 2017
 * Métodos Probabílisticos para Engenharia Informática
 * Mestrado Integrado em Engenharia de Computadores e Telemática
 * Marco Silva(84770) e Raquel Rainho(84891)
 */

package proj;

public class Jaccard {
	
	private double[][] distance;
	private int[][] minHash;
	
	public Jaccard(MinHash minHashes){
		this.minHash = minHashes.getMinHash();
		this.distance = new double[minHash.length][minHash.length];		//[nº de clientes][nº de clientes]
	
		for(int i = 0; i < minHash.length; i++){
			for(int j = i+1; j < minHash.length; j++){
				double x = sum(minHash[i],minHash[j]);
				distance[i][j] = 1 - (x/minHash[i].length);
			}
		}
		System.out.println("Fim de Jaccard");
	}
	
	public double[][] getDistance(){
		return this.distance;
	}
	
	private int sum(int[] a, int[] b){
		int x = 0;
		
		for(int i = 0; i < a.length; i++)
			for(int j = 0; j < b.length; j++)
				if(a[i] == b[j])
					x++;
		
		return x;
	}
}
