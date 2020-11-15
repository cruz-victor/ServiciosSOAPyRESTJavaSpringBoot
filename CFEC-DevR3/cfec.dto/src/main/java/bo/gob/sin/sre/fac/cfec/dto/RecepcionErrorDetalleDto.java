package bo.gob.sin.sre.fac.cfec.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author edwin.coro
 * @fecha 13/12/2018
 */
public class RecepcionErrorDetalleDto {

	private long codigoRecepcionIndividual;
	private Long numeroFactura;
	private String cuf;
	private List<Integer> listaCodigosError;

	public RecepcionErrorDetalleDto() {
		listaCodigosError = new ArrayList<>();
	}

	public RecepcionErrorDetalleDto(long codigoRecepcionIndividual, Long numeroFactura, String cuf, List<Integer> listaCodigosError) {
		super();
		this.codigoRecepcionIndividual = codigoRecepcionIndividual;
		this.numeroFactura = numeroFactura;
		this.cuf = cuf;
		this.listaCodigosError = listaCodigosError;
	}

	@Override
	public String toString() {
		return "RecepcionErrorDetalleDto [codigoRecepcionIndividual=" + codigoRecepcionIndividual + ", numeroFactura="
				+ numeroFactura + ", cuf=" + cuf + ", listaCodigosError=" + listaCodigosError + "]";
	}

	/**
	 * @return the codigoRecepcionIndividual
	 */
	public long getCodigoRecepcionIndividual() {
		return codigoRecepcionIndividual;
	}

	/**
	 * @param codigoRecepcionIndividual the codigoRecepcionIndividual to set
	 */
	public void setCodigoRecepcionIndividual(long codigoRecepcionIndividual) {
		this.codigoRecepcionIndividual = codigoRecepcionIndividual;
	}

	/**
	 * @return the numeroFactura
	 */
	public Long getNumeroFactura() {
		return numeroFactura;
	}

	/**
	 * @param numeroFactura the numeroFactura to set
	 */
	public void setNumeroFactura(Long numeroFactura) {
		this.numeroFactura = numeroFactura;
	}

	/**
	 * @return the cuf
	 */
	public String getCuf() {
		return cuf;
	}

	/**
	 * @param cuf the cuf to set
	 */
	public void setCuf(String cuf) {
		this.cuf = cuf;
	}

	/**
	 * @return the listaCodigosError
	 */
	public List<Integer> getListaCodigosError() {
		return listaCodigosError;
	}

	/**
	 * @param listaCodigosError the listaCodigosError to set
	 */
	public void setListaCodigosError(List<Integer> listaCodigosError) {
		this.listaCodigosError = listaCodigosError;
	}

}
