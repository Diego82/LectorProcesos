package logica;

import java.util.List;
import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class JTableModelo2 extends AbstractTableModel {

	
	
	//Preguntar a luis como cambiar la posicion de la grafica 
	
	
	
	List<ProcesoFree> listadoAux;
	String[] cabecera = {"memTotal", "memUsed","memLibre", "memCompartida", "memBuffer",
			"memCache", "memAplicaciones", "memAplicacionesLibre", "memTotalSwap",
			"memUsedSwap", "memLibreSwap"};
	
	public JTableModelo2 (List<ProcesoFree> lista) {
		this.listadoAux = lista;
	}
	
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return this.listadoAux.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return this.cabecera.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		ProcesoFree p = listadoAux.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return p.getMemTotal();
		case 1:
			return p.getMemUsed();
		case 2:
			return p.getMemLibre();
		case 3:
			return p.getMemCompartida();
		case 4:
			return p.getMemBuffer();
		case 5:
			return p.getMemCache();
		case 6:
			return p.getMemAplicaciones();
		case 7:
			return p.getMemAplicacionesLibre();
		case 8:
			return p.getMemTotalSwap(); 
		case 9:
			return p.getMemUsedSwap();
		case 10:
			return p.getMemLibreSwap();
		default:
			return "";
		}
	}
	
	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return cabecera[column];
	}
}
