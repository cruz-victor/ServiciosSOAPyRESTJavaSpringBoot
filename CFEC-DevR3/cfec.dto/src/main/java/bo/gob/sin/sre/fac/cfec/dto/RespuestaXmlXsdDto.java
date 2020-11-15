package bo.gob.sin.sre.fac.cfec.dto;

import java.util.ArrayList;
import java.util.List;

public class RespuestaXmlXsdDto {
	private List<Integer> listaCodigosRespuestas;
	private List<String> listaDescripcionesRespuestas;

	public RespuestaXmlXsdDto() {
		this.listaCodigosRespuestas = new ArrayList<>();
		this.listaDescripcionesRespuestas = new ArrayList<>();
	}

	public List<Integer> getListaCodigosRespuestas() {
		return listaCodigosRespuestas;
	}

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

	@Override
	public String toString() {
		return "RespuestaListaRegistroRecepcionesSoapDto [listaDescripcionesRespuestas=" + listaDescripcionesRespuestas
				+ ", listaCodigosRespuestas=" + listaCodigosRespuestas + "]";
	}

}