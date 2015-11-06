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
	
	public ChartPanel ventana(){
		DefaultPieDataset data = new DefaultPieDataset();
        for (int i = 0; i < listaAux.size(); i++) {
        	if(listaAux.get(i).getUsoMemoria()>0.1)
        		data.setValue(listaAux.get(i).getPid(),listaAux.get(i).getUsoMemoria());        
		}
                
        
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
	
}
