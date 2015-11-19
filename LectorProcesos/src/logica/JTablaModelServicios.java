package logica;

import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * Usamos esta clase para mostrar la tabla con los procesos del comando 'service --status-all' 
 */
@SuppressWarnings("serial")
public class JTablaModelServicios extends AbstractTableModel {

	List<ProcesoServicio> listadoAux;
	String[] cabecera = {"Estado", "Nombre"};
	
	public JTablaModelServicios (List<ProcesoServicio> lista) {
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
		ProcesoServicio p = listadoAux.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return p.getEstadoServicio();
		case 1:
			return p.getNombreServicio();
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
