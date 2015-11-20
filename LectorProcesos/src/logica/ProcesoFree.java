package logica;

public class ProcesoFree {
	// Atributos cuando realizamos el comando 'free'
	private Double memTotal, memUsed, memLibre, memCompartida, memBuffer, memCache, memAplicaciones,
			memAplicacionesLibre, memTotalSwap, memUsedSwap, memLibreSwap;

	public ProcesoFree() {
	};

	public ProcesoFree(String memTotal, String memUsed, String memLibre, String memCompartida, String memBuffer,
			String memCache, String memAplicaciones, String memAplicacionesLibre, String memTotalSwap,
			String memUsedSwap, String memLibreSwap) {
		super();
		this.memTotal = Double.parseDouble(memTotal);
		this.memUsed = Double.parseDouble(memUsed);
		this.memLibre = Double.parseDouble(memLibre);
		this.memCompartida = Double.parseDouble(memCompartida);
		this.memBuffer = Double.parseDouble(memBuffer);
		this.memCache = Double.parseDouble(memCache);
		this.memAplicaciones = Double.parseDouble(memAplicaciones);
		this.memAplicacionesLibre = Double.parseDouble(memAplicacionesLibre);
		this.memTotalSwap = Double.parseDouble(memTotalSwap);
		this.memUsedSwap = Double.parseDouble(memUsedSwap);
		this.memLibreSwap = Double.parseDouble(memLibreSwap);
	}

	/**
	 * @return the memTotal
	 */
	public Double getMemTotal() {
		return memTotal;
	}

	/**
	 * @param memTotal
	 *            the memTotal to set
	 */
	public void setMemTotal(String memTotal) {
		this.memTotal = Double.parseDouble(memTotal);
	}

	/**
	 * @return the memUsed
	 */
	public Double getMemUsed() {
		return memUsed;
	}

	/**
	 * @param memUsed
	 *            the memUsed to set
	 */
	public void setMemUsed(String memUsed) {
		this.memUsed = Double.parseDouble(memUsed);
	}

	/**
	 * @return the memLibre
	 */
	public Double getMemLibre() {
		return memLibre;
	}

	/**
	 * @param memLibre
	 *            the memLibre to set
	 */
	public void setMemLibre(String memLibre) {
		this.memLibre = Double.parseDouble(memLibre);
	}

	/**
	 * @return the memCompartida
	 */
	public Double getMemCompartida() {
		return memCompartida;
	}

	/**
	 * @param memCompartida
	 *            the memCompartida to set
	 */
	public void setMemCompartida(String memCompartida) {
		this.memCompartida = Double.parseDouble(memCompartida);
	}

	/**
	 * @return the memBuffer
	 */
	public Double getMemBuffer() {
		return memBuffer;
	}

	/**
	 * @param memBuffer
	 *            the memBuffer to set
	 */
	public void setMemBuffer(String memBuffer) {
		this.memBuffer = Double.parseDouble(memBuffer);
	}

	/**
	 * @return the memCache
	 */
	public Double getMemCache() {
		return memCache;
	}

	/**
	 * @param memCache
	 *            the memCache to set
	 */
	public void setMemCache(String memCache) {
		this.memCache = Double.parseDouble(memCache);
	}

	/**
	 * @return the memAplicaciones
	 */
	public Double getMemAplicaciones() {
		return memAplicaciones;
	}

	/**
	 * @param memAplicaciones
	 *            the memAplicaciones to set
	 */
	public void setMemAplicaciones(String memAplicaciones) {
		this.memAplicaciones = Double.parseDouble(memAplicaciones);
	}

	/**
	 * @return the memAplicacionesLibre
	 */
	public Double getMemAplicacionesLibre() {
		return memAplicacionesLibre;
	}

	/**
	 * @param memAplicacionesLibre
	 *            the memAplicacionesLibre to set
	 */
	public void setMemAplicacionesLibre(String memAplicacionesLibre) {
		this.memAplicacionesLibre = Double.parseDouble(memAplicacionesLibre);
	}

	/**
	 * @return the memTotalSwap
	 */
	public Double getMemTotalSwap() {
		return memTotalSwap;
	}

	/**
	 * @param memTotalSwap
	 *            the memTotalSwap to set
	 */
	public void setMemTotalSwap(String memTotalSwap) {
		this.memTotalSwap = Double.parseDouble(memTotalSwap);
	}

	/**
	 * @return the memUsedSwap
	 */
	public Double getMemUsedSwap() {
		return memUsedSwap;
	}

	/**
	 * @param memUsedSwap
	 *            the memUsedSwap to set
	 */
	public void setMemUsedSwap(String memUsedSwap) {
		this.memUsedSwap = Double.parseDouble(memUsedSwap);
	}

	/**
	 * @return the memLibreSwap
	 */
	public Double getMemLibreSwap() {
		return memLibreSwap;
	}

	/**
	 * @param memLibreSwap
	 *            the memLibreSwap to set
	 */
	public void setMemLibreSwap(String memLibreSwap) {
		this.memLibreSwap = Double.parseDouble(memLibreSwap);
	}

	@Override
	public String toString() {
		return "ProcesoFree [memTotal=" + memTotal + ", memUsed=" + memUsed + ", memLibre=" + memLibre
				+ ", memCompartida=" + memCompartida + ", memBuffer=" + memBuffer + ", memCache=" + memCache
				+ ", memAplicaciones=" + memAplicaciones + ", memAplicacionesLibre=" + memAplicacionesLibre
				+ ", memTotalSwap=" + memTotalSwap + ", memUsedSwap=" + memUsedSwap + ", memLibreSwap=" + memLibreSwap
				+ "]";
	}

}
