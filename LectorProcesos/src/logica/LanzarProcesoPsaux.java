package logica;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LanzarProcesoPsaux {

	public static List<ProcesoPsAux> lanzar(String comando, String modificador) {
		 
		List<String> comandos = new ArrayList<String>();
		comandos.add(comando);
		comandos.add(modificador);
		
		
		List<ProcesoPsAux> listado = new ArrayList<ProcesoPsAux>();
		
		ProcessBuilder pBuilder = new ProcessBuilder(comandos);
		Process proceso1 = null;
		BufferedReader in = null;

		try {
			proceso1 = pBuilder.start();
			in = new BufferedReader(new InputStreamReader(proceso1.getInputStream()));

			String linea = null;
			String campoComando = null;
			ArrayList<String> campos = null;
			linea = in.readLine();

			while ((linea = in.readLine()) != null) {
				campos = new ArrayList<String>(Arrays.asList(linea.split(" ")));
				for (int i = 0; i < campos.size(); i++) {
					if (campos.get(i).length() == 0) {
						campos.remove(i);
						i--;
					}
				}

				campoComando = campos.get(10);
				for (int i = 11; i < campos.size(); i++) {
					campoComando += " " + campos.get(i);
				}
				campos.set(10, campoComando);

				ProcesoPsAux proceso = new ProcesoPsAux(campos.get(0), campos.get(1), 
												campos.get(2), campos.get(3),
												campos.get(4), campos.get(5), 
												campos.get(6), campos.get(7), 
												campos.get(8), campos.get(9), 
												campos.get(10));
				listado.add(proceso);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<String> comandos2 = new ArrayList<String>();
		String modificador2="";
		comandos.add("ps");
		comandos.add("-p");
		comandos.add(modificador2);
		comandos.add("-o");
		comandos.add("comm =");
		List<String> listado2 = new ArrayList<String>();
		
		try {
			proceso1 = pBuilder.start();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return listado;
		
	}
	public static void main(String[] args) {
		
		long ini = System.currentTimeMillis();	
		LanzarProcesoPsaux proceso= new LanzarProcesoPsaux();
		long fin = System.currentTimeMillis();
		System.out.printf("Tiempo del proceso: "+(fin-ini));
	}
}
