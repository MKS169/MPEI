package proj;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BloomTest {

	public static void main(String[] args) {
		try {
			//------------------Teste 1---------------------------------------------------
			CountFilter bf = new CountFilter(500,3);				// length = 100/0.8;	hashFunctions = 3;
			
			Scanner sc = new Scanner(new File("Test.txt"));			// não existem produtos repetidos nesta lista
																
			while(sc.hasNextLine())
				bf.bloomInsertion(sc.nextLine());
			
			int[] bloom = bf.getBloom();
			int collisions = 0;
			
			for(int i = 0; i < bloom.length; i++){
				if(bloom[i] > 1){
					collisions++;	
				}
			}
			
			System.out.println("Colisões: " + collisions);
			
			System.out.println("Probabilidade de Falsos Positivos: " + bf.falsePositives());
			
			sc.close();
			
			//-------------------Teste 2------------------------------------------------------
			CountFilter bf2 = new CountFilter(500,3);					// length = 100/0.8;	hashFunctions = 3;
			
			Scanner sc2 = new Scanner(new File("TestRepeat.txt"));		// existem produtos repetidos nesta lista
																		// o numero de colisões deve ser 3
			while(sc2.hasNextLine())
				bf2.bloomInsertion(sc2.nextLine());
			
			int[] bloom2 = bf2.getBloom();
			int collisions2 = 0;
			
			for(int i = 0; i < bloom2.length; i++){
				if(bloom2[i] > 1){
					collisions2++;	
				}
			}
			
			System.out.println("Colisões: " + collisions2);
			
			System.out.println("Probabilidade de Falsos Positivos: " + bf2.falsePositives());
			
			sc2.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
