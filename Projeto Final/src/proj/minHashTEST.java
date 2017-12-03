/*
 * Universidade de Aveiro, 2017
 * M�todos Probab�listicos para Engenharia Inform�tica
 * Mestrado Integrado em Engenharia de Computadores e Telem�tica
 * Marco Silva(84770) e Raquel Rainho(84891)
 */

package proj;
// CLASSE DE TESTE DA MINHASH PARA PARES COM DISTANCIA < 0.5
public class minHashTEST {

	public static void main(String[] args) {
		ClienteSet clientes = new ClienteSet();
		ComprasSet compras = new ComprasSet();
		
		MinHash m = new MinHash(compras,clientes);
		
		Jaccard j = new Jaccard(m);
		
		DistanceFilter df = new DistanceFilter(clientes,j.getDistance(),0.5);
		
		System.out.println("Pares: " + df.NumOfPares());				// numero muito elevado devido � quantidade
																		// de produtos e clientes
	}

}
