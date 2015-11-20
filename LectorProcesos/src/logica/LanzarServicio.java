package logica;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @param comando
 *            muestra el nombre del comando que vamos a ejecutar 'service'
 * @param modificador
 *            muestra el modificador que vamos a añadir al comando
 *            '--status-all'
 * @return nos devuelve un listado parametrizado con la información recogida a
 *         la salida de la consola Para obtener la informacion lanzaremos el
 *         proceso 'service --status-all' y utilizaremos un BufferReader que
 *         recogerá la información de la salida por consola
 * 
 */
public class LanzarServicio {

	public static List<ProcesoServicio> lanzarService(String comando, String modificador) {

		List<String> comandos = new ArrayList<>();
		comandos.add(comando);
		comandos.add(modificador);

		List<ProcesoServicio> listado = new ArrayList<ProcesoServicio>();

		ProcessBuilder pBuilder = new ProcessBuilder(comandos);
		Process proceso1 = null;
		BufferedReader in = null;
		BufferedReader in2 = null;

		try {
			proceso1 = pBuilder.start();
			in = new BufferedReader(new InputStreamReader(proceso1.getInputStream()));
			in2 = new BufferedReader(new InputStreamReader(proceso1.getErrorStream()));

			String linea = null;
			ArrayList<String> campos = null;

			while ((linea = in.readLine()) != null) {
				campos = new ArrayList<String>(Arrays.asList(linea.split(" ")));
				campos.remove(4);
				campos.remove(3);
				campos.remove(1);
				campos.remove(0);

				ProcesoServicio proceso = new ProcesoServicio(campos.get(0), campos.get(1));
				listado.add(proceso);
			}

			while ((linea = in2.readLine()) != null) {
				campos = new ArrayList<String>(Arrays.asList(linea.split(" ")));
				campos.remove(4);
				campos.remove(3);
				campos.remove(1);
				campos.remove(0);

				ProcesoServicio proceso = new ProcesoServicio(campos.get(0), campos.get(1));
				listado.add(proceso);
			}
			//System.out.println(listado);

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
		return listado;
	}
}
