package proj;

public class DistanceFilter {

	private int len, countPairs = 0;
	private double limite;
	private String[][] similares;
	
	public DistanceFilter(int lenClientes, ClienteSet clientes, double[][] J, double threshold){
		this.len = lenClientes;		// número de clientes
		this.limite = threshold;	// limite de distançia
		this.similares = new String[3][100000];	// [0] -> cliente1; [1] -> cliente2; [2] -> distancia
		
		for(int i = 0; i < len; i++){
			for(int j = 0; j < J[i].length; j++){
				similares = extendArray(similares);
				if(J[i][j] <= limite){
					similares[0][i] = clientes.getCliente(i).getNome();
					similares[1][i] = clientes.getCliente(j).getNome();
					similares[2][i] = "" + J[i][j];
					countPairs++;
				}
			}
		}
	}
	
	public String[][] similares(){
		return similares;
	}
	public int NumOfPairs(){
		return this.countPairs;
	}
	
	private String[][] extendArray(String[][] similares){
		String[][] aux = new String[3][similares[0].length+1];
		System.arraycopy(similares, 0, aux, 0, similares.length);
		return aux;
	}
	
}