package bo.gob.sin.sre.fac.cfec.dto.interfaces;

import java.util.ArrayList;
import java.util.List;

import bo.gob.sin.sre.fac.cfec.dto.RecepcionErrorDetalleDto;

public class Respuesta implements IRespuesta {

	private boolean transaccion;
	private long codigoRecepcion;
	private int codigoEstado;
	private List<Integer> listaCodigosRespuestas;
	private List<RecepcionErrorDetalleDto> listaErroresDetalles;
	private List<String> listaDescripcionesRespuestas;

	public Respuesta(boolean transaccion, long codigoRecepcion, int codigoEstado, List<Integer> listaCodigosRespuestas,
			List<RecepcionErrorDetalleDto> listaErroresDetalles) {
		super();
		this.transaccion = transaccion;
		this.codigoRecepcion = codigoRecepcion;
		this.codigoEstado = codigoEstado;
		this.listaCodigosRespuestas = listaCodigosRespuestas;
		this.listaErroresDetalles = listaErroresDetalles;
	}

	public Respuesta() {
		this.listaCodigosRespuestas = new ArrayList<>();
		this.listaErroresDetalles = new ArrayList<>();
		this.listaDescripcionesRespuestas = new ArrayList<>();
	}

	@Override
	public List<Integer> getListaCodigosRespuestas() {
		return listaCodigosRespuestas;
	}

	@Override
	public boolean isTransaccion() {
		return transaccion;
	}

	@Override
	public long getCodigoRecepcion() {
		return codigoRecepcion;
	}

	@Override
	public int getCodigoEstado() {
		return codigoEstado;
	}

	@Override
	public List<RecepcionErrorDetalleDto> getListaErroresDetalles() {
		return listaErroresDetalles;
	}

	@Override
	public List<String> getListaDescripcionesRespuestas() {
		return listaDescripcionesRespuestas;
	}

	public void setListaCodigosRespuestas(List<Integer> listaCodigosRespuestas) {
		this.listaCodigosRespuestas = listaCodigosRespuestas;
	}

	public void setTransaccion(boolean transaccion) {
		this.transaccion = transaccion;
	}

	public void setCodigoRecepcion(long codigoRecepcion) {
		this.codigoRecepcion = codigoRecepcion;
	}

	public void setCodigoEstado(int codigoEstado) {
		this.codigoEstado = codigoEstado;
	}

	public void setListaErroresDetalles(List<RecepcionErrorDetalleDto> listaErroresDetalles) {
		this.listaErroresDetalles = listaErroresDetalles;
	}

	public void setListaDescripcionesRespuestas(List<String> listaDescripcionesRespuestas) {
		this.listaDescripcionesRespuestas = listaDescripcionesRespuestas;
	}

	@Override
	public String toString() {
		return "Respuesta [transaccion=" + transaccion + ", codigoRecepcion=" + codigoRecepcion + ", codigoEstado="
				+ codigoEstado + ", listaCodigosRespuestas=" + listaCodigosRespuestas + ", listaErroresDetalles="
				+ listaErroresDetalles + ", listaDescripcionesRespuestas=" + listaDescripcionesRespuestas + "]";
	}

}