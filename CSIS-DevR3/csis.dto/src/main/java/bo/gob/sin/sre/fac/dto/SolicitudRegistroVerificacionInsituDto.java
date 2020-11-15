package bo.gob.sin.sre.fac.dto;

import java.io.Serializable;

public class SolicitudRegistroVerificacionInsituDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private long registroObservacionInsituId;
	private RegistroVerificacionInsituDto registro;

	public long getRegistroObservacionInsituId() {
		return registroObservacionInsituId;
	}

	public void setRegistroObservacionInsituId(long registroObservacionInsituId) {
		this.registroObservacionInsituId = registroObservacionInsituId;
	}

	public RegistroVerificacionInsituDto getRegistro() {
		return registro;
	}

	public void setRegistro(RegistroVerificacionInsituDto registro) {
		this.registro = registro;
	}

}
