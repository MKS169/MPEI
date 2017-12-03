package proj;

import java.util.ArrayList;
import java.util.List;

// Classe utilizada para testa o metodo printToFile() das classes ComprasSet.java, ClienteSet.java e LojaSet.java
// NÃO EXECUTAR ESTA CLASSE. A SUA EXECUÇÃO VAI ADICIONAR STRINGS QUE NÃO CORRESPONDEM AO FORMATO DAS NECESSÁRIAS NESTE FICHEIRO
public class PrintTest {
	public static void main(String[] args) {
		List<String> set = new ArrayList<>();
		FileRdWr.readFile("DadosCliente.txt", set);
		set.add("Esta String é nova!!!!!!!");
		for (int i = 0; i < 10; i++) {
			set.add("String nova " + i);
		}
		
		List<String> aux = new ArrayList<>();
		FileRdWr.readFile("DadosCliente.txt", aux);
		
		List<String> different = new ArrayList<String>();
        different.addAll(aux);
        different.addAll(set);

        different.removeAll( aux );
		
		FileRdWr.writeFile("DadosCliente.txt", different, "Novo Conteudo!", true);
	}

}
