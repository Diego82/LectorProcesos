package interfazGrafica;

import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import logica.ProcesoPsAux;
import logica.ProcesoServicio;
import logica.ProcesoFree;

/**
 * Usamos esta clase como plantilla para obtener las diferentes gráficas de los
 * procesos que vamos a lanzar
 * 
 */
public class JChartPanelPlantilla {

	private List<ProcesoPsAux> listaAux;
	private List<ProcesoFree> listaAux2;
	private List<ProcesoServicio> listaAux3;
	static JFreeChart chart1;
	static JFreeChart chart3;
	static JFreeChart chart2;

	// Refactorizar a Map para hacerlo mas eficiente

	public JChartPanelPlantilla(List<ProcesoPsAux> listaAux) {
		this.listaAux = listaAux;
	}

	public JChartPanelPlantilla(List<ProcesoFree> listaAux, int n) {
		this.listaAux2 = listaAux;
	}

	public JChartPanelPlantilla(List<ProcesoServicio> listaAux, boolean a) {
		this.listaAux3 = listaAux;
	}

	/**
	 * Con esta clase cargamos los datos que queremos reflejar en el gráfico En
	 * el gráfico mostraremos los procesos que tienen mas de 0.5% de uso de CPU
	 * Los que contenga un valor menor, se mostrar juntos en una grafica común
	 * 
	 * @return charpanel con el uso de la CPU
	 */
	public ChartPanel ventanaCPU() {

		DefaultPieDataset data = new DefaultPieDataset();
		double otrosProcesos = 0;

		for (int i = 0; i < listaAux.size(); i++) {
			if (listaAux.get(i).getUsoMemoria() > 0.5)
				data.setValue(listaAux.get(i).getPid(), listaAux.get(i).getUsoMemoria());
			else
				otrosProcesos += listaAux.get(i).getUsoMemoria();
		}
		// El resto de procesos los mostraremos todos juntos es esta porcion
		data.setValue("Otros procesos", otrosProcesos);

		// Creando el Grafico
		chart1 = ChartFactory.createPieChart3D("Uso de la CPU", data, true, true, false);

		// Crear el Panel del Grafico con ChartPanel
		ChartPanel chartPanel = new ChartPanel(chart1);
		return chartPanel;
	}

	/**
	 * Con esta clase cargamos los datos que queremos reflejar Mostramos la
	 * Memoria RAM que se encuentran libre, ocupada y compartida
	 * 
	 * @return charpanel con el uso de memoria
	 */
	public ChartPanel ventanaRAM() {

		DefaultPieDataset data = new DefaultPieDataset();
		data.setValue("Memoria en uso", listaAux2.get(0).getMemUsed());
		data.setValue("Memoria libre", listaAux2.get(0).getMemLibre());
		data.setValue("Memoria compartida", listaAux2.get(0).getMemCompartida());

		// Creando el Grafico
		chart2 = ChartFactory.createRingChart("Uso de la Memoria RAM", data, true, true, false);

		// Crear el Panel del Grafico con ChartPanel
		ChartPanel chartPanel = new ChartPanel(chart2);
		return chartPanel;
	}

	/**
	 * Esta clase muestra los servicios activos e inactivos
	 * 
	 * @return charpanel de servicios
	 */

	public ChartPanel ventanaServicios() {

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		int activos = 0, inactivos = 0, indeterminados=0;
		for (ProcesoServicio procesoServicio : listaAux3) {
			if (procesoServicio.getEstadoServicio().contains("+"))
				procesoServicio.setEstadoServicio("Activos");
				activos++;
			if (procesoServicio.getEstadoServicio().contains("-"))
				procesoServicio.setEstadoServicio("Detenidos");
				inactivos++;
			if (procesoServicio.getEstadoServicio().contains("?"))
				
				indeterminados++;
			// total++;
			// System.out.println(procesoServicio);
		}
		dataset.setValue(activos, "Servicios", "Activos");
		dataset.setValue(inactivos, "Servicios", "Detenidos");
		dataset.setValue(indeterminados, "Servicios", "Indeterminado");
		
		chart3 = ChartFactory.createBarChart("Servicios en ejecución", "Estado", null, dataset,
				PlotOrientation.HORIZONTAL, false, true, false);

		ChartPanel chartPanel = new ChartPanel(chart3);
		return chartPanel;
	}
}
