package bo.gob.sin.sre.fac.dto;

import java.io.Serializable;

public class SolicitudSistemasEnProcesoDto  implements Serializable
{
	private static final long serialVersionUID = 1L;

	private long personaContribuyenteId;

	public long getPersonaContribuyenteId() {
		return personaContribuyenteId;
	}

	public void setPersonaContribuyenteId(long personaContribuyenteId) {
		this.personaContribuyenteId = personaContribuyenteId;
	}
	
}
