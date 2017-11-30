/*
 * Universidade de Aveiro, 2017
 * M�todos Probab�listicos para Engenharia Inform�tica
 * Mestrado Integrado em Engenharia de Computadores e Telem�tica
 * Marco Silva(84770) e Raquel Rainho(84891)
 */

package proj;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;
import static java.lang.System.*;

public abstract class FileRdWr {
	
	@SuppressWarnings("unchecked")
	public static <E> void readFile(String fileName, List<E> fileCont){
		try{
			Scanner fRead = new Scanner(new File(fileName));
			//fRead.nextLine();
			while(fRead.hasNextLine()){
				fileCont.add((E) fRead.nextLine());
			}
			
			fRead.close();
		}
		catch(FileNotFoundException e){
			out.println("ERROR: " + e);
		}
	}
	
	public static <E> void writeFile(String fileName, List<E> fileCont, String head){
		try{
			PrintWriter fWrite = new PrintWriter(new File(fileName)); 
			
			fWrite.println(head);
			
			for(int i = 0; i < fileCont.size(); i++){
				fWrite.println(fileCont.get(i));
			}
			
			fWrite.close();
		}
		catch(FileNotFoundException e){
			out.println("ERROR: " + e);
		}
	}
}
