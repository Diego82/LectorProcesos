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

public class JChartPanelPlantilla {

	private List<ProcesoPsAux> listaAux;
	private List<ProcesoFree> listaAux2;
	private List<ProcesoServicio> listaAux3;
	static JFreeChart chart1;
	static JFreeChart chart3;
	static JFreeChart chart2;

	//Refactorizar a Map para hacerlo mas eficiente

	public JChartPanelPlantilla(List<ProcesoPsAux> listaAux) {this.listaAux = listaAux;}
	public JChartPanelPlantilla(List<ProcesoFree> listaAux, int n) {this.listaAux2 = listaAux;}
	public JChartPanelPlantilla(List<ProcesoServicio> listaAux, boolean a) {this.listaAux3 = listaAux;}
	
	public ChartPanel ventanaCPU(){

		//Con esta clase cargamos los datos que queremos reflejar en el gráfico
		//En el gráfico mostraremos los procesos que tienen mas de 0.5% de uso de CPU
		
		DefaultPieDataset data = new DefaultPieDataset();
		double otrosProcesos = 0;
        
		for (int i = 0; i < listaAux.size(); i++) {
        	if(listaAux.get(i).getUsoMemoria()>0.5)
        		data.setValue(listaAux.get(i).getPid(),listaAux.get(i).getUsoMemoria());
        	else
        		otrosProcesos += listaAux.get(i).getUsoMemoria();
		}
        //El resto de procesos los mostraremos todos juntos es esta porcion
        data.setValue("Otros procesos", otrosProcesos);
                
        // Creando el Grafico
        chart1 = ChartFactory.createPieChart3D("Uso de la CPU", data, true, true, false);
 
        // Crear el Panel del Grafico con ChartPanel
        ChartPanel chartPanel = new ChartPanel(chart1);
        return chartPanel;
	}
	
	public ChartPanel ventanaRAM(){

		//Con esta clase cargamos los datos que queremos reflejar 
		DefaultPieDataset data = new DefaultPieDataset();
		data.setValue("Memoria en uso",listaAux2.get(0).getMemUsed());
		data.setValue("Memoria libre",listaAux2.get(0).getMemLibre());
		data.setValue("Memoria compartida",listaAux2.get(0).getMemCompartida());

		// Creando el Grafico
        chart2 = ChartFactory.createRingChart("Uso de la Memoria RAM", data, true, true, false);
        
        // Crear el Panel del Grafico con ChartPanel
        ChartPanel chartPanel = new ChartPanel(chart2);
        return chartPanel;
	}
	
	public ChartPanel ventanaServicios(){

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		int activos=0, inactivos=0;
		//,indeterminados=0, total=0;
		for (ProcesoServicio procesoServicio : listaAux3) {
			if (procesoServicio.getEstadoServicio().contains("+")) activos++;
			if (procesoServicio.getEstadoServicio().contains("-")) inactivos++;
			//if (procesoServicio.getEstadoServicio().contains("?")) indeterminados++;
			//total++;
			//System.out.println(procesoServicio);
		}
		dataset.setValue(activos, "", "Activos");
		dataset.setValue(inactivos, "", "Pasivos");
		chart3 = ChartFactory.createBarChart("Servicios en ejecución",
				"Estado", null, dataset, PlotOrientation.HORIZONTAL, false, true, false);
		
        ChartPanel chartPanel = new ChartPanel(chart3);
        return chartPanel;
	}
}
