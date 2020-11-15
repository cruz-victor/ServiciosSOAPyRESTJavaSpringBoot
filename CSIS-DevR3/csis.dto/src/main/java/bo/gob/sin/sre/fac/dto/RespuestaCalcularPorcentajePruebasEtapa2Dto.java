package bo.gob.sin.sre.fac.dto;



import java.io.Serializable;

import bo.gob.sin.str.cmsj.mapl.dto.ListaMensajesAplicacion;

public class RespuestaCalcularPorcentajePruebasEtapa2Dto extends ListaMensajesAplicacion implements Serializable {
	private static final long serialVersionUID = 1L;
	private float porcentaje;

	public float getPorcentaje() {
		return porcentaje;
	}

	public void setPorcentaje(float porcentaje) {
		this.porcentaje = porcentaje;
	}	
}							


