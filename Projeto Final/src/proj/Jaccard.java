package proj;

public class Jaccard {
	
	private MinHash minHashes;
	private int[][] distance;
	private int[][] minHash;
	
	public Jaccard(String[][] sets){
		this.minHashes = new MinHash(sets); 
		this.minHash = minHashes.getMinHash();
		this.distance = new int[minHash.length][minHash.length];
		
		int x = 0;
		
		for(int i = 0; i < minHash.length-1; i++){
			for(int j = i+1; j < minHash.length; j++){
				if(minHash[j] == minHash[i])
					x = sum(minHash[i],minHash[j]);
				x = x/minHash.length;
				distance[i][j] = x;
			}
		}
	}
	
	private int sum(int[] a, int[] b){
		int x = 0;
		for(int i = 0; i < a.length; i++){
			x += a[i]+b[i]; 
		}
		return x;
	}
}
