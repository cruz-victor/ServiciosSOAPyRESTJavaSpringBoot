package bo.gob.sin.sre.fac.cfec.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author edwin.coro
 * @fecha 13/12/2018
 */
public class RespuestaValidacionRecepcionPaqueteDto {

	private int codigoEstado;
	private long codigoRecepcion;
	private boolean transaccion;
	private List<Integer> listaCodigosRespuestas;
	private List<RecepcionErrorDetalleDto> listaErroresDetalles;
	private List<String> listaDescripcionesRespuestas;

	public RespuestaValidacionRecepcionPaqueteDto() {
		listaErroresDetalles = new ArrayList<>();
		listaCodigosRespuestas = new ArrayList<>();
		listaDescripcionesRespuestas = new ArrayList<>();
	}

	@Override
	public String toString() {
		return "RespuestaValidacionRecepcionPaqueteDto [transaccion=" + transaccion + ", codigoRecepcion="
				+ codigoRecepcion + ", codigoEstado=" + codigoEstado + ", listaErroresDetalles=" + listaErroresDetalles
				+ ", listaCodigosRespuestas=" + listaCodigosRespuestas + ", listaDescripcionesRespuestas="
				+ listaDescripcionesRespuestas + "]";
	}

	/**
	 * @return the transaccion
	 */
	public boolean isTransaccion() {
		return transaccion;
	}

	/**
	 * @param transaccion the transaccion to set
	 */
	public void setTransaccion(boolean transaccion) {
		this.transaccion = transaccion;
	}

	/**
	 * @return the codigoRecepcion
	 */
	public long getCodigoRecepcion() {
		return codigoRecepcion;
	}

	/**
	 * @param codigoRecepcion the codigoRecepcion to set
	 */
	public void setCodigoRecepcion(long codigoRecepcion) {
		this.codigoRecepcion = codigoRecepcion;
	}

	/**
	 * @return the codigoEstado
	 */
	public int getCodigoEstado() {
		return codigoEstado;
	}

	/**
	 * @param codigoEstado the codigoEstado to set
	 */
	public void setCodigoEstado(int codigoEstado) {
		this.codigoEstado = codigoEstado;
	}

	/**
	 * @return the listaErroresDetalles
	 */
	public List<RecepcionErrorDetalleDto> getListaErroresDetalles() {
		return listaErroresDetalles;
	}

	/**
	 * @param listaErroresDetalles the listaErroresDetalles to set
	 */
	public void setListaErroresDetalles(List<RecepcionErrorDetalleDto> listaErroresDetalles) {
		this.listaErroresDetalles = listaErroresDetalles;
	}

	/**
	 * @return the listaErroresParametrosSOAP
	 */
	public List<Integer> getListaCodigosRespuestas() {
		return listaCodigosRespuestas;
	}

	/**
	 * @param listaErroresParametrosSOAP the listaErroresParametrosSOAP to set
	 */
	public void setListaCodigosRespuestas(List<Integer> listaCodigosRespuestas) {
		this.listaCodigosRespuestas = listaCodigosRespuestas;
	}

	/**
	 * @return the listaDescripcionesRespuestas
	 */
	public List<String> getListaDescripcionesRespuestas() {
		return listaDescripcionesRespuestas;
	}

	/**
	 * @param listaDescripcionesRespuestas the listaDescripcionesRespuestas to set
	 */
	public void setListaDescripcionesRespuestas(List<String> listaDescripcionesRespuestas) {
		this.listaDescripcionesRespuestas = listaDescripcionesRespuestas;
	}

}