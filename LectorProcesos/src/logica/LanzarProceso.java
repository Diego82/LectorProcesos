package logica;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class LanzarProceso {
	
	static void lanzar(String comando, String modificador){

		//Declaramos las variables que vamos a necesitar mas adelante
		List<String> comandos = new ArrayList<String>();
		ProcessBuilder pBuilder = new ProcessBuilder(comandos);
		Process proceso1 = null;
		BufferedReader in = null;
		List<Proceso> listaProcesos = new ArrayList<Proceso>();

		//le pasamos los comandos que queremos procesar 
		comandos.add(comando);
		comandos.add(modificador);
		
		try {
			proceso1 = pBuilder.start();
			in = new BufferedReader(
					new InputStreamReader(
							proceso1.getInputStream()));
			
			String linea = null;
			String campoComando = null;
			ArrayList<String> campos = null;
			Scanner scan = null;
			
			while ((linea = in.readLine()) != null){
				campos = new ArrayList<String>(Arrays.asList(linea.split(" ")));
				for (int i=0;i<campos.size();i++){
					if(campos.get(i).length()==0) {
						campos.remove(i);
						i--;
					}	
				}
				campoComando = campos.get(10);
				for(int i=11; i<campos.size();i++){
					campoComando += " "+campos.get(i);
				}
				campos.set(10, campoComando);				
				Proceso proceso = new Proceso(campos.get(0), 
						campos.get(1), campos.get(2), 
						campos.get(3), campos.get(4), 
						campos.get(5), campos.get(6), 
						campos.get(7), campos.get(8), 
						campos.get(9), campos.get(10));
				listaProcesos.add(proceso);
				}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
