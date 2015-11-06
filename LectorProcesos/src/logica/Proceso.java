package logica;

public class Proceso {
	//Atributos cuando realizamos el comando 'ps aux'
	private String user, pid, vsz, rss, tty, stat, start, time, command;
	private double usoMemoria,usoCPU;
	
	//constructor para el proceso TOP
	public Proceso(String pid, String usuario, String orden, String usoCPU, String usoMemoria){
		this.pid = pid; this.user = usuario; this.command = orden;
		this.usoCPU = Double.parseDouble(usoCPU); this.usoMemoria = Double.parseDouble(usoMemoria);
		
	}
	
	/**
	 * Constructor para el proceso ps aux
	 * @param user
	 * @param pid
	 * @param usoCPU
	 * @param usoMemoria
	 * @param vsz
	 * @param rss
	 * @param tty
	 * @param stat
	 * @param start
	 * @param time
	 * @param commando
	 */
	public Proceso(String user, String pid, String usoCPU, String usoMem, String vsz, String rss, String tty,
			String stat, String start, String time, String commando) {
		this.user = user;this.pid = pid;this.vsz = vsz;this.rss = rss;
		this.tty = tty;this.stat = stat;this.start = start;this.time = time;this.command = commando;
		this.usoCPU = Double.parseDouble(usoCPU);
		this.usoMemoria = Double.parseDouble(usoMem);
	}
	
	//getter
	
	/**
	 * @return the user
	 */
	public String getUser() {
		return user;
	}
	/**
	 * @return the pid
	 */
	public String getPid() {
		return pid;
	}
	/**
	 * @return the usoCPU
	 */
	public Double getUsoCPU() {
		return usoCPU;
	}
	/**
	 * @return the usoMemoria
	 */
	public Double getUsoMemoria() {
		return usoMemoria;
	}
	/**
	 * @return the vsz
	 */
	public String getVsz() {
		return vsz;
	}
	/**
	 * @return the rss
	 */
	public String getRss() {
		return rss;
	}
	/**
	 * @return the tty
	 */
	public String getTty() {
		return tty;
	}
	/**
	 * @return the stat
	 */
	public String getStat() {
		return stat;
	}
	/**
	 * @return the start
	 */
	public String getStart() {
		return start;
	}
	/**
	 * @return the time
	 */
	public String getTime() {
		return time;
	}
	/**
	 * @return the command
	 */
	public String getCommand() {
		return command;
	}
}
