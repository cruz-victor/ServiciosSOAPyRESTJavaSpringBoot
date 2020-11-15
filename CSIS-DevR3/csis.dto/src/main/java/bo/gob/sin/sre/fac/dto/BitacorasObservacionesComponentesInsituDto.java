package bo.gob.sin.sre.fac.dto;

import java.io.Serializable;
import java.util.List;

import bo.gob.sin.str.cmsj.mapl.dto.ListaMensajesAplicacion;

public class BitacorasObservacionesComponentesInsituDto extends ListaMensajesAplicacion  implements Serializable
{
	private static final long serialVersionUID = 1L;

	private List<BitacoraObservacionComponentesInsituDto> bitacorasObservacionesComponentesInsitu;

	public List<BitacoraObservacionComponentesInsituDto> getBitacorasObservacionesComponentesInsitu() {
		return bitacorasObservacionesComponentesInsitu;
	}

	public void setBitacorasObservacionesComponentesInsitu(
			List<BitacoraObservacionComponentesInsituDto> bitacorasObservacionesComponentesInsitu) {
		this.bitacorasObservacionesComponentesInsitu = bitacorasObservacionesComponentesInsitu;
	}

}
