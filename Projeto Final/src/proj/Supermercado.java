/*
 * Universidade de Aveiro, 2017
 * Métodos Probabílisticos para Engenharia Informática
 * Mestrado Integrado em Engenharia de Computadores e Telemática
 * Marco Silva(84770) e Raquel Rainho(84891)
 */

package proj;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
public class Supermercado {
	public static void main(String[] args) throws FileNotFoundException {
		//add stock
		//String[] supermercados = {"Continente", "Pingo Doce", "Intermarché"};
		//ArrayList[] supermercados = new ArrayList()[3];
		
		
		
		// ---------------------------
		int numSupermercados;
		int indexSupermercado;
		
		ArrayList<String> prod = new ArrayList<>(); // lista de produtos
		FileRdWr.readFile("ListaDeCompras.txt", prod);
		HashMap<Integer, ArrayList<String>> compras = new HashMap<>();
		
		int primeiro;
		int segundo;
		int terceiro;
		
		compras.put(0, new ArrayList<>());
		compras.put(1, new ArrayList<>());
		compras.put(2, new ArrayList<>());
		
		for (int i = 0; i< prod.size(); i++) {
			primeiro = (int) (Math.random()*2);
			segundo  = (int) (Math.random()*2);
			terceiro = (int) (Math.random()*2);
			
			if(primeiro==1) compras.get(0).add(prod.get(i));
			if(segundo==1) compras.get(1).add(prod.get(i));
			if(terceiro==1) compras.get(2).add(prod.get(i));
			if(primeiro!=1 && segundo!=1 && terceiro!=1) compras.get((int)(Math.random()*2)).add(prod.get(i));
		}
			String continente = "Continente\t";
			for(int i=0; i<compras.get(0).size(); i++) {
				continente+=compras.get(0).get(i) + ": " + (int)(Math.random()*50+50) + ", ";
			}
			String pingo_doce = "Pingo-doce\t";
			for(int i=0; i<compras.get(1).size(); i++) {
				pingo_doce +=compras.get(1).get(i) + ": " + (int)(Math.random()*50+50) + ", ";
			}
			
			String intermarché = "Intermarché\t";
			for(int i=0; i<compras.get(2).size(); i++) {
				intermarché+=compras.get(2).get(i) + ": " + (int)(Math.random()*50+50) + ", ";
			}
			
			PrintWriter print = new PrintWriter("Stock.txt");
			
			print.println("Loja\tProdutos");
			print.println(continente.substring(0, continente.length()-2));
			print.println(pingo_doce.substring(0, pingo_doce.length()-2));
			print.println(intermarché.substring(0, intermarché.length()-2));
			print.close();
			
			/**numSupermercados = (int) (Math.random()*2+1);
			for (int j=0;j<=numSupermercados ;j++) {
				do {
					indexSupermercado = (int) (Math.random()*2);
				} while(compras.get(indexSupermercado).contains(prod.get(i)));
				compras.get(indexSupermercado).add(prod.get(i));
			}
			*/
		
		//System.out.println(compras.get(0));
		//System.out.println(compras.get(1));
		//System.out.println(compras.get(2));
		
		//String s = "Continente\t" + 
				/**		do {
				indexSupermercado = (int) (Math.random()*2+1);
				}while();
				supermercados[indexSupermercado];
			}
		}
		*/
		// -----------------------
		
		/**ArrayList<String> array = new ArrayList<>();
		FileRdWr.readFile("NumerosAux.txt", array);
		ArrayList<String> arrayProdutos = new ArrayList<>();
		FileRdWr.readFile("ListaDeCompras.txt", arrayProdutos);
		ArrayList<String> produtos = new ArrayList<>();
		
		ArrayList<String> new_array = new ArrayList<>();
		
		int lojas;
		int nifs;
		int indexProduto;
		int nProd;
		int numProd;
		String s = "";
		
		String[] arr = {"Continente", "Pingo Doce", "Intermarché"};
		for(int i=0; i<10000; i++) {
			s = "";
			produtos.clear();
			nifs = (int)(Math.random()*array.size()); // gerar nifs (dos clientes existentes)
			lojas = (int)(Math.random()*arr.length); // gerar nomes de Lojas
			
			
			nProd = (int)((Math.random()*10)+1);
			for (int j = 0; j<nProd; j++) {
				numProd = (int)((Math.random()*10)+1);
				indexProduto = (int)(Math.random()*arrayProdutos.size());
				if (!produtos.contains(arrayProdutos.get(indexProduto))) {
					produtos.add(arrayProdutos.get(indexProduto));
					s+= arrayProdutos.get(indexProduto) + ": " + numProd + ", ";
				}
			}
			new_array.add(array.get(nifs) + "\t" + s.substring(0,s.length()-2)  + "\t" + arr[lojas]);
		}
		
		FileRdWr.writeFile("ParaUsar.txt", new_array, "");
		*/
		/**
		for( int i=0; i< array.size(); i++)
			new_array.add(array.get(i).split("\t")[1]);
		
		FileRdWr.writeFile("NumerosAux.txt", new_array, "");
		*/
	}
}
