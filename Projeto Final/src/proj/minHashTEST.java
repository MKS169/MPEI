/*
 * Universidade de Aveiro, 2017
 * Métodos Probabílisticos para Engenharia Informática
 * Mestrado Integrado em Engenharia de Computadores e Telemática
 * Marco Silva(84770) e Raquel Rainho(84891)
 */

package proj;

import java.util.LinkedList;

public class minHashTEST {

	public static void main(String[] args) {
		ClienteSet clientes = new ClienteSet();
		ComprasSet compras = new ComprasSet();
		
//		for(int  i = 0; i < clientes.size(); i++){
//			LinkedList<Compras> c = compras.comprasDoCliente(clientes.getCliente(i).getNif());
//			System.out.println(c.toString());
//		}
		
//		Cliente c = new Cliente("joao",123455);
//		Cliente c1 = new Cliente("manel",235625);
//		Cliente c2 = new Cliente("fds",45743);
//		Cliente c3 = new Cliente("pedro",187925);
//		ClienteSet clientes = new ClienteSet();
//		clientes.addCliente(c);
//		clientes.addCliente(c1);
//		clientes.addCliente(c2);
//		clientes.addCliente(c3);
//		
//		Compras d = new Compras(123455,"carne: 2");
//		Compras d1 = new Compras(123455,"peixe: 5");
//		Compras d2 = new Compras(235625,"carne: 2");
//		Compras d3 = new Compras(235625,"queijo: 5");
//		Compras d4 = new Compras(45743,"queijo: 121");
//		Compras d5 = new Compras(45743,"escova: 454");
//		Compras d6 = new Compras(187925,"prato: 87");
//		Compras d7 = new Compras(187925,"cereal: 90");
//		ComprasSet compras = new ComprasSet();
//		compras.addCompras(d);
//		compras.addCompras(d1);
//		compras.addCompras(d2);
//		compras.addCompras(d3);
//		compras.addCompras(d4);
//		compras.addCompras(d5);
//		compras.addCompras(d6);
//		compras.addCompras(d7);
//		
//		for(int  i = 0; i < clientes.size(); i++){
//			LinkedList<Compras> cp = compras.comprasDoCliente(clientes.getCliente(i).getNif());
//			System.out.println(cp.toString());
//		}
		
		MinHash m = new MinHash(compras,clientes);
		
//		String[][] st = m.set();				// produtos
//		int[][] min = m.getMinHash();			// minHash de cada produto
//		
//		for(int i = 0; i < clientes.size(); i++){
//			for(int x = 0; x < st[i].length; x++){
//				System.out.print(st[i][x] + " " + min[i][x] + " ");
//			}
//			System.out.println();
//		}
		
		Jaccard j = new Jaccard(m);
	
//		double[][] ad = j.getDistance();
//		
//		for(int i = 0; i < ad.length; i++){
//			for(int x = i+1; x < ad[i].length; x++){		
//				System.out.print(ad[i][x] + " ");
//			}
//			System.out.println();
//		}
		
		DistanceFilter df = new DistanceFilter(clientes,j.getDistance(),0.6);
		
//		String[] s = df.similares();
//		
//		for(int i = 0; i < s.length; i++){
//			System.out.println(s[i]);
//		}
		
		System.out.println("Pares: " + df.NumOfPares());
		
	}

}
