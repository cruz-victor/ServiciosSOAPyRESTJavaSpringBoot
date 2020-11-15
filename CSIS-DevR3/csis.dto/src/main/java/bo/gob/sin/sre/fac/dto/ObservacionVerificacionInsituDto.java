package bo.gob.sin.sre.fac.dto;

import java.io.Serializable;

import bo.gob.sin.str.cmsj.mapl.dto.ListaMensajesAplicacion;

public class ObservacionVerificacionInsituDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long observacionInsituId;

	private String descripcionPrueba;

	public Long getObservacionInsituId() {
		return observacionInsituId;
	}

	public void setObservacionInsituId(Long observacionInsituId) {
		this.observacionInsituId = observacionInsituId;
	}

	public String getDescripcionPrueba() {
		return descripcionPrueba;
	}

	public void setDescripcionPrueba(String descripcionPrueba) {
		this.descripcionPrueba = descripcionPrueba;
	}

}
