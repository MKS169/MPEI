package proj;

import java.util.ArrayList;
import java.util.List;

public class DistanceFilter {

	private int pares = 0;
	private double limite;
	private List<String> similares = new ArrayList<String>(); // cliente1 + cliente2 + distancia
	
	public DistanceFilter(ClienteSet clientes, double[][] J, double threshold){
		this.limite = threshold;					// limite de distançia
		
		for(int i = 0; i < J.length; i++){				// com j = i + 1 toda a metade, com i > j da matriz J[][] é ignorada 
			for(int j = 0; j < J[i].length; j++){			// visto que não contem qq valor
				if(J[i][j] < limite){
					similares.add(clientes.getCliente(i).getNif() + " " + clientes.getCliente(j).getNif() + " " + J[i][j]);
					pares++;
				}
			}
		}
		System.out.println("Fim de Distance");
	}

	public String[] similares(){
		String[] s = new String[similares.size()];
		s = similares.toArray(s);
		return s;
	}
	
	public int NumOfPares(){
		return this.pares;
	}
}
