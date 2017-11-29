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
		this.distance = new double[minHash.length][minHash.length];
		
		double x = 0;
		int inter = 0, union = 0;
		
		for(int i = 0; i < minHash.length-1; i++){
			for(int j = i+1; j < minHash.length; j++){
				inter = sum(minHash[i],minHash[j]);
				union = minHash[i].length + minHash[j].length;
				x = 1-(inter/(union-inter));
				distance[i][j] = x;
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
