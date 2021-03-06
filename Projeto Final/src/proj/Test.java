/*
 * Universidade de Aveiro, 2017
 * M�todos Probab�listicos para Engenharia Inform�tica
 * Mestrado Integrado em Engenharia de Computadores e Telem�tica
 * Marco Silva(84770) e Raquel Rainho(84891)
 */

package proj;

// Classe  criada para execu��o de testes
public class Test {
	
	public static void main(String[] args) {
		/**BloomFilter b = new BloomFilter(1000, 5);
		String s = "Something"; // String random
		
		for(int i=0; i<1000;i++) {
			b.bloomInsertion(s);
		}*/
		try {
		LojaInterface loja = new Loja("Continente", "Carne: 50, Peixe: 40, "
				+ "Leite: 30, Bolachas: 100, Iogurte: 30");
		
		System.out.println("Loja\tProdutos");
		System.out.println(loja);

		System.out.println("NIF\tCompra");
		
		System.out.println("---- TESTE AQUI ------");
		ComprasSet cs = new ComprasSet();
		System.out.println(cs.comprasDoCliente(156299233));

		System.out.println("---- TESTE AQUI ------");
		
		Cliente c;

		System.out.println("Nome\tNIF");
		for(int i =0; i<10; i++) {
			c = new Cliente("Exemplo" + i, i);
			System.out.println(c);
		}
		
		} catch (ArrayIndexOutOfBoundsException e) {
			System.err.println("Erro!");
		}
		System.out.println("-----------------------");
		ClienteSet c = new ClienteSet();
		c.printClientes();
		
	}
}
