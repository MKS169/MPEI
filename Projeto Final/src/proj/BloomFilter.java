package proj;
// n -> tamanho do conjunto
// k -> numero de hashFunctions


public class BloomFilter {
	private int n, k;
	private int[] bloom;
	
	BloomFilter(int len, int hash){
		this.n = len;
		this.k = hash;
		this.bloom = new int[n];
	}
	
	void bloomInsertion(String s){
		int bloomPos;
		for(int i = 0; i < k; i++){
			s = s.concat(String.valueOf(i));
			bloomPos = string2Hash(s)%bloom.length;
			if(isMember(s))
				bloom[bloomPos] += 1;
			else
				bloom[bloomPos] = 1;	
		}
	}
	
	boolean isMember(String s){
		int bloomPos;
		for(int i = 0; i < k; i++){
			s = s.concat(String.valueOf(i));
			bloomPos = string2Hash(s)%bloom.length;
			if(bloom[bloomPos] == 1)
				return true;
		}
		return false;
	}
	
	int[] getBloom(){
		return bloom;
	}
	
	static int string2Hash(String s){
		int hash = 918463752;
		int hash1 = (int)Math.random()*hash;
		int hash2 = (int)Math.random()*(hash-s.length());
		
		hash = s.length()*s.hashCode();
		hash *= hash1 + hash2;
		
		return hash; 
	}
}
