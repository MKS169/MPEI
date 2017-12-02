/*
 * Universidade de Aveiro, 2017
 * Métodos Probabílisticos para Engenharia Informática
 * Mestrado Integrado em Engenharia de Computadores e Telemática
 * Marco Silva(84770) e Raquel Rainho(84891)
 */

package proj;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Jaccard {
	
	private int[][] minHash;
	private Map<Integer, List<Double>> dist = new HashMap<>();
	
	public Jaccard(MinHash minHashes){
		this.minHash = minHashes.getMinHash();
	
		for(int i = 0; i < minHash.length; i++){
			List<Double> aux = new ArrayList<>();
			for(int j = i+1; j < minHash.length; j++){
				double x = sum(minHash[i],minHash[j]);
				double f = minHash[i].length + minHash[j].length;
				aux.add(1-(x/f));
			}
			dist.put(i, aux);
		}
		System.out.println("Fim de Jaccard");
	}
	
	public double[][] getDistance(){
		double[][] aux = new double[dist.size()][];
		for(int i = 0; i < dist.size(); i++){
			List<Double> a = dist.get(i);
			double[] d = new double[a.size()];
			
			for(int j = 0; j < d.length; j++)
				d[j] = a.get(j);
			
			aux[i] = d;
				
		}
		return aux;
	}
	
	private int sum(int[] a, int[] b){
		int x = 0;
		
		for(int i: a)
			for(int j: b)
				if(i == j)
					x++;
		
		return x;
	}
}
