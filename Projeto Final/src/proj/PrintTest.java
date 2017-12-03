package proj;

import java.util.ArrayList;
import java.util.List;

// Classe utilizada para testa o metodo printToFile() das classes ComprasSet.java, ClienteSet.java e LojaSet.java
// N�O EXECUTAR ESTA CLASSE. A SUA EXECU��O VAI ADICIONAR STRINGS QUE N�O CORRESPONDEM AO FORMATO DAS NECESS�RIAS NESTE FICHEIRO
public class PrintTest {
	public static void main(String[] args) {
		List<String> set = new ArrayList<>();
		FileRdWr.readFile("DadosCliente.txt", set);
		set.add("Esta String � nova!!!!!!!");
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
