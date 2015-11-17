package logica;

public class ProcesoNombre {

	public int pid;
	public String name;
	/**
	 * @param pid
	 * @param name
	 */
	public ProcesoNombre(int pid, String name) {
		this.pid = pid;
		this.name = name;
	}
	/**
	 * @return the pid
	 */
	public int getPid() {
		return pid;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ProcesoNombre [pid=" + pid + ", name=" + name + "]";
	}
	
	
	
}
