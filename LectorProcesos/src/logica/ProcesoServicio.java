package logica;

public class ProcesoServicio {

	private String estadoServicio, nombreServicio;

	public ProcesoServicio(String estadoServicio, String nombreServicio) {
		super();
		this.estadoServicio = estadoServicio;
		this.nombreServicio = nombreServicio;
	}

	public String getEstadoServicio() {
		return estadoServicio;
	}

	public void setEstadoServicio(String estadoServicio) {
		this.estadoServicio = estadoServicio;
	}

	public String getNombreServicio() {
		return nombreServicio;
	}

	public void setNombreServicio(String nombreServicio) {
		this.nombreServicio = nombreServicio;
	}

	@Override
	public String toString() {
		return "ProcesoServicio [estadoServicio=" + estadoServicio + ", nombreServicio=" + nombreServicio + "\n";
	}
	
}
