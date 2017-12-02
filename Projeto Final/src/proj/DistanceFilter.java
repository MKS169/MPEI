package proj;

import java.util.ArrayList;
import java.util.List;

public class DistanceFilter {

	private int len, pares = 0;
	private double limite;
	private List<String> similares;
	
	public DistanceFilter(ClienteSet clientes, double[][] J, double threshold){
		this.len = clientes.size();					// número de clientes
		this.limite = threshold;					// limite de distançia
		this.similares = new ArrayList<String>();	// cliente1 + cliente2 + distancia
		
		for(int i = 0; i < len-1; i++){
			for(int j = i+1; j < len; j++){
				if(J[i][j] < limite){
					similares.add(clientes.getCliente(i).getNome() + " " + clientes.getCliente(j).getNome() + " " + J[i][j]);
					pares++;
				}
			}
		}
		System.out.println("Fim de Distance");
	}

	public String[] similares(){
		return (String[]) similares.toArray();
	}
	
	public int NumOfPares(){
		return this.pares;
	}
}