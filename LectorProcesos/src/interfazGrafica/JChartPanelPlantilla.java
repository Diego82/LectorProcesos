package interfazGrafica;

import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import logica.ProcesoPsAux;
import logica.ProcesoFree;

public class JChartPanelPlantilla {

	private List<ProcesoPsAux> listaAux;
	private List<ProcesoFree> listaAux2;

	public JChartPanelPlantilla(List<ProcesoPsAux> listaAux) {
		this.listaAux = listaAux;
	}
	
	public JChartPanelPlantilla(List<ProcesoFree> listaAux2, int numero) {
		this.listaAux2 = listaAux2;
	}
	
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
        JFreeChart chart = ChartFactory.createPieChart(
         "Uso de la CPU", 
         data, 
         true, 
         true, 
         false);
 
        // Crear el Panel del Grafico con ChartPanel
        ChartPanel chartPanel = new ChartPanel(chart);
        
        return chartPanel;
	}
	
	public ChartPanel ventanaRAM(){

		//Con esta clase cargamos los datos que queremos reflejar 
		DefaultPieDataset data = new DefaultPieDataset();
        
		data.setValue("Memoria en uso",listaAux2.get(0).getMemUsed());
		data.setValue("Memoria libre",listaAux2.get(0).getMemLibre());
		data.setValue("Memoria compartida",listaAux2.get(0).getMemCompartida());
		
        // Creando el Grafico
        JFreeChart chart = ChartFactory.createPieChart(
         "Uso de la Memoria RAM", 
         data, 
         true, 
         true, 
         false);
 
        // Crear el Panel del Grafico con ChartPanel
        ChartPanel chartPanel = new ChartPanel(chart);
        
        return chartPanel;
	}
	
}
