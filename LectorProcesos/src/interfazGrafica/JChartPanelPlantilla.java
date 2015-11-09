package interfazGrafica;

import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import logica.Proceso;

public class JChartPanelPlantilla {

	private List<Proceso> listaAux;

	public JChartPanelPlantilla(List<Proceso> listaAux) {
		this.listaAux = listaAux;
	}
	
	public ChartPanel ventanaCPU(){

		//Con esta clase cargamos los datos que queremos reflejar 
		DefaultPieDataset data = new DefaultPieDataset();
		double otrosProcesos = 0;
		int procesos1=0, procesos2=0;
        for (int i = 0; i < listaAux.size(); i++) {
        	if(listaAux.get(i).getUsoMemoria()>0.5){
        		data.setValue(listaAux.get(i).getPid(),listaAux.get(i).getUsoMemoria());
        		procesos1++;
        	}
        	else{
        		otrosProcesos += listaAux.get(i).getUsoMemoria();
        		procesos2++;
        	}	
		}
        System.out.println("Numero de procesos mayor que 0.5: "+procesos1);
        System.out.println("Numero de procesos mayor que 0.5: "+procesos2);
        
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
        for (int i = 0; i < listaAux.size(); i++) {
        	if(listaAux.get(i).getUsoMemoria()>0.1)
        		data.setValue(listaAux.get(i).getPid(),listaAux.get(i).getUsoMemoria());        
		}
                
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
	
	public ChartPanel ventanaSWAP(){

		//Con esta clase cargamos los datos que queremos reflejar 
		DefaultPieDataset data = new DefaultPieDataset();
        for (int i = 0; i < listaAux.size(); i++) {
        	if(listaAux.get(i).getUsoMemoria()>0.5)
        		data.setValue(listaAux.get(i).getPid(),listaAux.get(i).getUsoMemoria());
		}
                
        // Creando el Grafico
        JFreeChart chart = ChartFactory.createPieChart(
         "Uso de la Memoria SWAP", 
         data, 
         true, 
         true, 
         false);
 
        // Crear el Panel del Grafico con ChartPanel
        ChartPanel chartPanel = new ChartPanel(chart);
        
        return chartPanel;
	}
	
}
