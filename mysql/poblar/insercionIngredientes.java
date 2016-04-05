package poblar;

import java.util.*;
import java.io.*;

public class insercionIngredientes{

	public static void main (String [] args) {
		try{
			Scanner br = new Scanner(new File("/home/carlos/GIT/ME4L/mysql/poblar/ingredientes.txt"));
			String tipo = "Otros";
			while(br.hasNextLine()){
				String linea = br.nextLine();
				if (linea.substring(0,1).equals("-")) {
					tipo = linea.substring(1, linea.length());
				}else{
					linea = linea.trim();
					System.out.println("INSERT INTO ingrediente(Nombre, TipoIngrediente) VALUES (" + linea + ", " + tipo + ")");
				}
				
			}
		}catch(FileNotFoundException e){

		}

	}


}
