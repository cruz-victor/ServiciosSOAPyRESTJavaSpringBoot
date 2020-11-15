package bo.gob.sin.sre.fac.cfec.dto;

import java.util.ArrayList;
import java.util.List;

public class RespuestaListaRegistroRecepcionesSoapDto {

	private int codigoEstado;
	private long codigoRecepcion;
	private boolean transaccion;
	private List<Integer> listaCodigosRespuestas;
	private List<String> listaDescripcionesRespuestas;

	public RespuestaListaRegistroRecepcionesSoapDto() {
		this.listaCodigosRespuestas = new ArrayList<>();
		this.listaDescripcionesRespuestas = new ArrayList<>();
	}

	public List<Integer> getListaCodigosRespuestas() {
		return listaCodigosRespuestas;
	}

	public void setListaCodigosRespuestas(List<Integer> listaCodigosRespuestas) {
		this.listaCodigosRespuestas = listaCodigosRespuestas;
	}

	public boolean isTransaccion() {
		return transaccion;
	}

	public void setTransaccion(boolean transaccion) {
		this.transaccion = transaccion;
	}

	public long getCodigoRecepcion() {
		return codigoRecepcion;
	}

	public void setCodigoRecepcion(long codigoRecepcion) {
		this.codigoRecepcion = codigoRecepcion;
	}

	public int getCodigoEstado() {
		return codigoEstado;
	}

	public void setCodigoEstado(int codigoEstado) {
		this.codigoEstado = codigoEstado;
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

	@Override
	public String toString() {
		return "RespuestaListaRegistroRecepcionesSoapDto [listaDescripcionesRespuestas=" + listaDescripcionesRespuestas
				+ ", listaCodigosRespuestas=" + listaCodigosRespuestas + ", transaccion=" + transaccion
				+ ", codigoRecepcion=" + codigoRecepcion + ", codigoEstado=" + codigoEstado + "]";
	}

}