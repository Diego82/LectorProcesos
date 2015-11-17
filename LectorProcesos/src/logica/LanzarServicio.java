package logica;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LanzarServicio {
	
	public static List<ProcesoServicio>lanzarService(String comando, String modificador){
			
			List<String> comandos = new ArrayList<>();
			comandos.add(comando);
			comandos.add(modificador);

			List<ProcesoServicio> listado = new ArrayList<ProcesoServicio>();
			
			ProcessBuilder pBuilder = new ProcessBuilder(comandos);
			Process proceso1 = null;
			BufferedReader in = null;
			
			try {
				
				proceso1 = pBuilder.start();
				
				in = new BufferedReader(
						new InputStreamReader(
								proceso1.getInputStream()));
				
				String linea = null;
				String campoComando = null;
				ArrayList<String> campos = null;
				
				while ((linea = in.readLine()) != null){
					
					campos = new ArrayList<String>(Arrays.asList(linea.split(" ")));
					//System.out.println(campos);
					campos.remove(4);
					campos.remove(3);
					campos.remove(1);
					campos.remove(0);

					ProcesoServicio proceso = new ProcesoServicio(campos.get(0), campos.get(1));
					listado.add(proceso);
					//System.out.println(proceso);
				}
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return listado;
	}
}
