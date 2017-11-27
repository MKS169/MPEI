package proj;

public class DistanceFilter {

	private int len, countPairs = 0;
	private double limite;
	private String[][] similares;
	
	// este input deve ser clientes[], produtos[] e comprasporcliente[] ????????????	
	
	public DistanceFilter(int lenClientes, ClienteSet clientes, double[][] J, double threshold){
		this.len = lenClientes;		// número de clientes
		this.limite = threshold;	// limite de distançia
		this.similares = new String[3][0];	// [0] -> cliente1; [1] -> cliente2; [2] -> distancia
		
		for(int i = 0; i < len; i++){
			for(int j = 0; j < len; j++){
				if(J[i][j] <= limite){
					similares[0][i] = clientes.getCliente(i);
					similares[1][i] = clientes.getCliente(j);
					similares = addClientes(similares, countPairs, J[i][j]);
					countPairs++;	// countPairs vai funcionar tanto para a posição no array como para contar as
									// distancias a baixo do limite
				}
			}
		}
	}
	
	public int NumbOfPairs(){
		return this.countPairs;
	}
	
	private String[][] addClientes(String[][] similares,int k, double distJaccard){
		String[][] hold = new String[3][k];
		System.arraycopy(similares, 0, hold, 0, similares.length);
		hold[2][similares.length] = String.valueOf(distJaccard);
		return hold;
	}
}