/*
 * Universidade de Aveiro, 2017
 * Métodos Probabílisticos para Engenharia Informática
 * Mestrado Integrado em Engenharia de Computadores e Telemática
 * Marco Silva(84770) e Raquel Rainho(84891)
 */

package proj;

import java.io.FileNotFoundException;
import java.util.Scanner;
import static java.lang.System.*;

public class Supermercado {
	static Scanner sc= new Scanner(System.in);
	static LojaSet conjLojas = new LojaSet();
	static ClienteSet conjClientes = new ClienteSet();
	static ComprasSet conjCompras = new ComprasSet();
	
	public static void main(String[] args) throws FileNotFoundException {
		int opcao=0;
		
		do {
			menu();
			out.print("Opção: ");
			opcao = Integer.parseInt(sc.nextLine());
			operaçao(opcao);
		}while(opcao!=0);
	}
	
	public static void menu() {
		out.println("\n---- OPERAÇÕES ----");
		out.println("1) Efetuar compra");
		out.println("2) Repor stock");
		out.println("3) Registar nova loja");
		out.println("4) Registar novo produto");
		out.println("0) Terminar");
	}
	public static void operaçao(int opcao) {
		switch(opcao) {
		case 1: efetuarCompra(); break;
		case 2: reporStock(); break;
		case 3: registarLoja(); break;
		case 4: registarProduto(); break;
		case 0: out.println("Terminado"); break;
		default: err.println("\nOperação inexistente!");
		}
	}
	
	private static void efetuarCompra() {
		String nomeLoja, nomeCliente, produto, compra="";
		int nif, numProdutos, quantidade;
		
		try {
			out.print("Insira nome da loja que pretende frequentar (" + conjLojas.nomeLojas() + "):");
			nomeLoja = sc.nextLine().toLowerCase();
			nomeLoja = nomeLoja.replaceFirst(nomeLoja.substring(0,1), nomeLoja.substring(0,1).toUpperCase());
			
			if(conjLojas.lojaExiste(nomeLoja)) {
				do {
					out.println("Insira o seu NIF: ");
					nif = Integer.parseInt(sc.nextLine());
					if(Integer.toString(nif).length()!=9) out.println("NIF tem de ter 9 dígitos!");
				}while(Integer.toString(nif).length()!=9);
				
				if(!conjClientes.clienteExiste(nif)) {
					out.println("É necessário registar-se!\nInsira o seu nome: ");
					
					do {
						nomeCliente = sc.nextLine();
						if(nomeCliente.equals("")) out.println("Nome inválido!\nInsira o seu nome: ");
					}while(nomeCliente.equals(""));
					
					conjClientes.addCliente(new Cliente(nomeCliente, nif));
					conjClientes.printToFile(); // coloca-o no registo
					out.println("Registado com sucesso!");
				}
				else {
					conjClientes.listaDeSugestoes(nif,conjCompras,conjClientes);
				}
				
				do {
					out.println("Insira o numero de produtos que pretende comprar: ");
					numProdutos = Integer.parseInt(sc.nextLine());
					if(numProdutos<=0) out.println("Numero de produtos inválido!");
				}while(numProdutos<=0);
				
				for(int i=0; i<numProdutos;i++) {
					do {
						out.print("Produto: ");
						produto = sc.nextLine();
						produto = Character.toUpperCase(produto.charAt(0)) + produto.substring(1, produto.length());
						if(produto.equals("")) out.println("Nome do produto inválido!");
					}while(produto.equals(""));
					
					do {
						out.print("Quantidade: ");
						quantidade = Integer.parseInt(sc.nextLine());
						if(quantidade<=0) out.println("Quantidade inválida!");
					}while(quantidade<=0);
					System.out.println(nomeLoja + " " + produto + " " + quantidade);
					if(conjLojas.produtoExiste(nomeLoja, produto, quantidade)) {
						System.out.println("Chegou ao if");
						compra += produto + ": " + quantidade + ", ";
						conjLojas.loja(nomeLoja).atualizarStock(produto, quantidade);
						conjLojas.printToFile();
						
					}else {
						System.out.println("Chegou ao else");
						String[] lojas = conjLojas.nomeLojas().split(", ");
						for(int j=0; j<lojas.length; j++) {
							if(conjLojas.produtoExiste(lojas[j], produto, quantidade)) 
								out.println("Poderá encontrar o produto " + produto + " na loja " + lojas[j]);
						}
						out.println("O produto " + produto + " não será incluido na sua compra.");
					}
				}
				System.out.println("Chegou ao add");
				conjCompras.addCompra(new Compras(nif, compra.substring(0, compra.length()-2)));
				conjCompras.printToFile();
				out.println("Compra registada com sucesso!");
			}
			else {
				err.println("Loja não existente!");
				throw new Exception();
			}
		}catch(Exception e) {
			err.println("Erro no processo de compra!");
			err.print(e);
		}
	}


	private static void reporStock() {
		String lojas[] = conjLojas.nomeLojas().split(", ");
		String produtos[];
		int quantARepor;
		
		for(int i=0; i< lojas.length; i++) {
			produtos = conjLojas.loja(lojas[i]).produtos();
			for(int j=0; j<produtos.length; j++) {
				if(conjLojas.loja(lojas[i]).getQuantidade(produtos[j])<10){
					quantARepor = (int)(Math.random()*20+10);
					conjLojas.loja(lojas[i]).reporStock(produtos[j], quantARepor);
					out.println("Stock reposto do produto " + produtos[j]+ " na loja "+ lojas[i]);
					conjLojas.printToFile();
				}
			}
		}
		System.out.println("Stock Reposto!");
	}
	
	private static void registarLoja() {
		String nomeLoja, produto, produtos="";
		int numProdutos, quantidade;
		try {
			out.print("Insira nome da loja que pretende registar: ");
			nomeLoja = sc.nextLine().toLowerCase();
			nomeLoja = nomeLoja.replaceFirst(nomeLoja.substring(0,1), nomeLoja.substring(0,1).toUpperCase());
			if(conjLojas.lojaExiste(nomeLoja)) {
				err.println("Loja já existe!");
				throw new Exception();
			}
			
			out.print("Insira o numero de produtos que pretende ter na loja: ");
			numProdutos = Integer.parseInt(sc.nextLine());
			
			if(numProdutos<=0) {
				err.println("A loja necessita de, pelo menos, um produto!");
				throw new Exception();
			}
	
			for (int i=0; i<numProdutos; i++) {
				do {
					out.print("Produto: ");
					produto = sc.nextLine();
					produto = produto.replace(produto.charAt(0), Character.toUpperCase(produto.charAt(0)));
					if(produto.equals("")) out.println("Não é possível adicionar produtos sem nome!");
				}while(produto.equals(""));
				
				do {
					out.print("Quantidade: ");
					quantidade = Integer.parseInt(sc.nextLine());
					if (quantidade<=0) out.println("É necessário colocar, pelo menos, uma unidade em stock!");
				}while(quantidade<=0);
				
				produtos+= produto + ": " + quantidade + ", ";
			}
			conjLojas.addLoja(new Loja(nomeLoja, produtos.substring(0, produtos.length()-2)));
			conjLojas.printToFile();
			out.println("Loja registada com sucesso!");
		}catch(Exception e) {
			err.println("Impossível terminar processo de registo da Loja!");
		}
	}
	
	private static void registarProduto() {
		String nomeLoja, produto;
		int quantidade;
			try {
				out.print("Insira nome da loja onde pretende registar o produto: ");		
				nomeLoja = sc.nextLine().toLowerCase();
				nomeLoja = nomeLoja.replaceFirst(nomeLoja.substring(0,1), nomeLoja.substring(0,1).toUpperCase());
				
				if(conjLojas.loja(nomeLoja)==null) {
					err.println("Loja não existente!");
					throw new Exception(); 
				}
				
				out.print("Produto: ");
				produto = sc.nextLine();
				produto = produto.replace(produto.charAt(0), Character.toUpperCase(produto.charAt(0)));
				if(conjLojas.loja(nomeLoja).contains(produto) || produto.equals("")) {
					err.println("Nome do produto já existe ou campo por preencher!");
					throw new Exception();
				}
				
				out.print("Quantidade: ");
				quantidade = Integer.parseInt(sc.nextLine());
				
				if(quantidade<=0) {
					out.println("Quantidade do produto inválida!");
					throw new Exception();
				}
				
				conjLojas.loja(nomeLoja).addProduto(produto, quantidade);
				conjLojas.printToFile();
				out.println("Produto adicionado com sucesso!");
			}catch(Exception e) {
				err.println("Impossível terminar processo de registo do produto!");
			}
	}
}






