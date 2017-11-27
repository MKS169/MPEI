package proj;

// Classe  criada para execução de testes
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

		System.out.println("Nome\tDataNasc\tNIF");
		for(int i =0; i<10; i++) {
			c = new Cliente("Exemplo" + i, new Date("4/2/1998"), i);
			System.out.println(c);
		}
		
		} catch (ArrayIndexOutOfBoundsException e) {
			System.err.println("Fuck!");
		}
		System.out.println("-----------------------");
		ClienteSet c = new ClienteSet();
		c.printClientes();
		
	}
}
