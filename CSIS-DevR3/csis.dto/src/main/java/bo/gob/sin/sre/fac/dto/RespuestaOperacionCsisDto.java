package bo.gob.sin.sre.fac.dto;

import java.io.Serializable;

import bo.gob.sin.str.cmsj.mapl.dto.ListaMensajesAplicacion;

public class RespuestaOperacionCsisDto extends ListaMensajesAplicacion   implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return "RespuestaOperacion []";
	}

	public RespuestaOperacionCsisDto() {
	}
			
}
