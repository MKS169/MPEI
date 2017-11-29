/*
 * Universidade de Aveiro, 2017
 * Métodos Probabílisticos para Engenharia Informática
 * Mestrado Integrado em Engenharia de Computadores e Telemática
 * Marco Silva(84770) e Raquel Rainho(84891)
 */

package proj;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class minHashTEST {

	public static void main(String[] args) {
		ClienteSet clientes = new ClienteSet();
		for(int i = 0; i < 10; i++)	
			clientes.addCliente(new Cliente("user"+i,Date.today(),1234567+i));
		
		clientes.printClientes();
		List<List<String>> compras = new ArrayList<>();;
		
		try {
			int x = 0;
			
			Scanner sc = new Scanner(new File("Test.txt"));
			List<String> aux = new ArrayList<>();
			while(sc.hasNextLine()){
				aux.add(sc.nextLine());
				x++;
				if(x > 3){
					compras.add(aux);
					aux = new ArrayList<>();
					x = 0;
				}
			}
			
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		MinHash m = new MinHash(compras);
			
		Jaccard j = new Jaccard(m);
		
		DistanceFilter df = new DistanceFilter(m.getMinHash().length,clientes,j.getDistance(),0.5);
		
		String[][] s = df.similares();
		
		System.out.println(s.length);
		
		for(int i = 0; i < s.length; i++){
			for(int d = 0; d < s.length; d++){
				System.out.print(s[i][d] + " ");
			}
			System.out.println();
		}
		
		System.out.println("Pares: " + df.NumOfPairs());
		
	}

}
