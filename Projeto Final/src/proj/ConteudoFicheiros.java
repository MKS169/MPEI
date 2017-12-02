package proj;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

// Classe utilizada para gerar o conteúdo dos ficheiros utilizados
// NÃO EXECUTAR ESTA CLASSE. EXISTE, NESTE MOMENTO, APENAS PARA MOSTRAR COMO FORAM CRIADOS OS FICHEIROS
public class ConteudoFicheiros {
	public static void main(String[] args) throws FileNotFoundException {
		
		// --------------------------------------
		// Criação de dados para o ficheiro Stock
		int primeiro, segundo, terceiro;
		ArrayList<String> prod = new ArrayList<>(); // lista de produtos
		HashMap<Integer, ArrayList<String>> compras = new HashMap<>();
		FileRdWr.readFile("ListaDeCompras.txt", prod);
		
		// Criação de arrayLists para as lojas existentes
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
	
		// -----------------------------
		// Criação de dados para o ficheiro Compras
		
		ArrayList<String> array = new ArrayList<>();
		FileRdWr.readFile("NIFs.txt", array);
		ArrayList<String> arrayProdutos = new ArrayList<>();
		FileRdWr.readFile("ListaDeCompras.txt", arrayProdutos);
		ArrayList<String> produtos = new ArrayList<>();
		ArrayList<String> new_array = new ArrayList<>();
		
		int lojas, nifs, indexProduto, nProd, numProd;
		String s = "";
		String[] arr = {"Continente", "Pingo Doce", "Intermarché"};
		
		for(int i=0; i<10000; i++) {
			s = "";
			produtos.clear();
			nifs = (int)(Math.random()*array.size()); // gerar indice de nifs (dos clientes existentes)
			lojas = (int)(Math.random()*arr.length); // gerar indice de nomes de Lojas
			
			
			nProd = (int)((Math.random()*10)+1); // gerar numero de produtos da lista do cliente
			for (int j = 0; j<nProd; j++) {
				numProd = (int)((Math.random()*10)+1); // gerar quantidade do produto
				indexProduto = (int)(Math.random()*arrayProdutos.size()); // gerar indice do produto
				if (!produtos.contains(arrayProdutos.get(indexProduto))) { // "garante" que a lista nao contém elementos repetidos
					produtos.add(arrayProdutos.get(indexProduto));
					s+= arrayProdutos.get(indexProduto) + ": " + numProd + ", "; 
				}
			}
			new_array.add(array.get(nifs) + "\t" + s.substring(0,s.length()-2)  + "\t" + arr[lojas]); // criação do ficheiro Compras 
																									  // com informação "adequada"
		}
		
		FileRdWr.writeFile("Ex.txt", new_array, "NIF\tCompra\tLoja");
		
		// FALTA ESCREVER O CÓDIGO UTILIZADO PARA FAZER O FICHEIRO DADOSCLIENTE
	}
}
