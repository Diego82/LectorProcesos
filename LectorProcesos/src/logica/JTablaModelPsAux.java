package logica;

import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * Usamos esta clase para mostrar la tabla con los procesos del comando 'ps -aux'
 */
@SuppressWarnings("serial")
public class JTablaModelPsAux extends AbstractTableModel {

	// Atributos
	List<ProcesoPsAux> listadoAux;
	String[] cabecera = { "user", "pid", "usoCPU", "usoMem", "vsz", "rss", "tty", "stat", "start", "time", "command" };

	
	public JTablaModelPsAux(List<ProcesoPsAux> lista) {
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
		ProcesoPsAux p = listadoAux.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return p.getUser();
		case 1:
			return p.getPid();
		case 2:
			return p.getUsoCPU();
		case 3:
			return p.getUsoMemoria();
		case 4:
			return p.getVsz();
		case 5:
			return p.getRss();
		case 6:
			return p.getTty();
		case 7:
			return p.getStat();
		case 8:
			return p.getStart();
		case 9:
			return p.getTime();
		case 10:
			return p.getCommand();
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
