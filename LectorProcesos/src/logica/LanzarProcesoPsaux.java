package logica;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @param comando
 *            muestra el nombre del comando que vamos a ejecutar 'ps'
 * @param modificador
 *            muestra el modificador que vamos a añadir al comando '-aux'
 * @return nos devuelve un listado parametrizado con la información recogida a
 *         la salida de la consola Para obtener la informacion lanzaremos el
 *         proceso ps aux y utilizaremos un BufferReader que recogerá la
 *         información de la salida por consola
 * 
 */
public class LanzarProcesoPsaux {

	public static List<ProcesoPsAux> lanzar(String comando, String modificador) {

		List<String> comandos = new ArrayList<String>();
		comandos.add(comando);
		comandos.add(modificador);

		List<ProcesoPsAux> listado = new ArrayList<ProcesoPsAux>();
		List<ProcesoNombre> listado2 = new ArrayList<ProcesoNombre>();

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

				ProcesoPsAux proceso = new ProcesoPsAux(campos.get(0), campos.get(1), campos.get(2), campos.get(3),
						campos.get(4), campos.get(5), campos.get(6), campos.get(7), campos.get(8), campos.get(9),
						campos.get(10));
				listado.add(proceso);
				int numeroPID = Integer.parseInt(campos.get(1));
				ProcesoNombre proceso2 = new ProcesoNombre(numeroPID, "");
				listado2.add(proceso2);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// Ver como se podria lanzar el proceso para atrapar los nombres de los
		// comandos

		/*
		 * List<String> comandos2 = new ArrayList<String>(); Scanner in2 = new
		 * Scanner(System.in); try {
		 * 
		 * String pid = listado2.get(0).getPid() + ""; System.out.println(pid);
		 * comandos.add("ps"); comandos.add("-p"); comandos.add("1");
		 * comandos.add("-o"); comandos.add("comm ="); proceso1 =
		 * pBuilder.start();
		 * 
		 * String linea = ""; //in.readLine(); linea = in.readLine();
		 * System.out.println(linea);
		 * //System.out.println(listado2.get(0).getPid() + " " +
		 * listado2.get(0).getName());
		 * 
		 * } catch (IOException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */
		return listado;
	}
}
