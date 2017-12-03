/*
 * Universidade de Aveiro, 2017
 * Métodos Probabílisticos para Engenharia Informática
 * Mestrado Integrado em Engenharia de Computadores e Telemática
 * Marco Silva(84770) e Raquel Rainho(84891)
 */

package proj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;
import static java.lang.System.*;

public abstract class FileRdWr {
	
	@SuppressWarnings("unchecked")
	public static <E> void readFile(String fileName, List<E> fileCont){
		try{
			BufferedReader fRead = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "UTF-8"));
			fRead.readLine();
			String line;
			while((line = fRead.readLine()) != null){
				fileCont.add((E) line);
			}
			
			fRead.close();
		}
		catch(FileNotFoundException e){
			out.println("ERROR: " + e);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static <E> void writeFile(String fileName, List<E> fileCont, String head, boolean append){
		try{
			PrintWriter fWrite = new PrintWriter(new FileWriter(fileName,append)); 
			
			fWrite.println(head);
			
			for(int i = 0; i < fileCont.size(); i++){
				fWrite.println(fileCont.get(i));
			}
			
			fWrite.close();
		}
		catch(FileNotFoundException e){
			out.println("ERROR: " + e);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
