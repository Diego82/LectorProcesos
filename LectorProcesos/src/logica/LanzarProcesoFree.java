package logica;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LanzarProcesoFree {

	/**
	 * LLamaremos a esta clase para lanzar el proceso free
	 * 
	 * @param comando
	 *            que debe ejecutar ('free')
	 * @return lista parametrizada con la informacion obtenida en la salida de
	 *         la consola Para obtener la informacion lanzaremos el proceso free
	 *         y utilizaremos un BufferReader que recogerá la información de la
	 *         salida por consola
	 * 
	 */
	public static List<ProcesoFree> lanzarFree(String comando) {

		ProcessBuilder pBuilder = new ProcessBuilder(comando);
		Process proceso1 = null;
		BufferedReader in = null;
		ProcesoFree proceso = new ProcesoFree();
		List<ProcesoFree> listado = new ArrayList<ProcesoFree>();

		try {
			proceso1 = pBuilder.start();
			in = new BufferedReader(new InputStreamReader(proceso1.getInputStream()));
			
			String linea = "";
			int control = 0;
			ArrayList<String> campos = null;
			linea = in.readLine();
			while ((linea = in.readLine()) != null) {

				campos = new ArrayList<String>(Arrays.asList(linea.split(" ")));

				for (int i = 0; i < campos.size(); i++) {
					if (!campos.get(0).matches("\\d*") || campos.get(i).length() == 0) {
						campos.remove(i);
						i--;
					}
				}
				if (control == 0) {
					proceso.setMemTotal(campos.get(0));
					proceso.setMemUsed(campos.get(1));
					proceso.setMemLibre(campos.get(2));
					proceso.setMemCompartida(campos.get(3));
					proceso.setMemBuffer(campos.get(4));
					proceso.setMemCache(campos.get(5));
				} else if (control == 1) {
					proceso.setMemAplicaciones(campos.get(0));
					proceso.setMemAplicacionesLibre(campos.get(1));
				} else {
					proceso.setMemTotalSwap(campos.get(0));
					proceso.setMemUsedSwap(campos.get(1));
					proceso.setMemLibreSwap(campos.get(2));
				}
				control++;
			}

			listado.add(proceso);

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
