package bo.gob.sin.sre.fac.dto;

import java.io.Serializable;
import java.util.Arrays;

import bo.gob.sin.str.cmsj.mapl.dto.ListaMensajesAplicacion;

public class GenerarHuellaSistemaDto extends ListaMensajesAplicacion  implements Serializable
{
	private static final long serialVersionUID = 1L;

	private byte[] archivo;
	
	public GenerarHuellaSistemaDto(byte[] archivo) {
		this.archivo = archivo;
	}

	public GenerarHuellaSistemaDto() {
		
	}

	public byte[] getArchivo() {
		return archivo;
	}

	public void setArchivo(byte[] archivo) {
		this.archivo = archivo;
	}

	@Override
	public String toString() {
		return "GenerarHuellaSistemaDto [archivo=" + Arrays.toString(archivo) + "]";
	}
}
