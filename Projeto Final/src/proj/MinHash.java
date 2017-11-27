package proj;

public class MinHash {
	private final int k = 15;	// large document requires bigger k
	private int[][] minHash = new int[k][];
	
	MinHash(String[][] sets){
		
		for(int i = 0; i < sets.length; i++){
			String[] cur = sets[i];
			
			for(int j = 0; j < k; j++){
				int minAt = hash(cur[0],j);
				
				for(int x = 1; x < cur.length; x++){
					minAt = min(minAt,hash(cur[x],j));
				}
				minHash[j][i] = minAt; 
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
		int hash = 13562;
		hash = j*(int)(Math.random()*s.length());
		return hash;
	}
}
