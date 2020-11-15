package bo.gob.sin.sre.fac.cfec.dto;

import bo.gob.sin.str.cmsj.mapl.dto.ListaMensajesAplicacion;

public class RespuestaValidacionFirmadoXmlFacturaDto extends ListaMensajesAplicacion {
	private String detalleError;

	public RespuestaValidacionFirmadoXmlFacturaDto() {

	}

	public String getDetalleError() {
		return detalleError;
	}

	public void setDetalleError(String detalleError) {
		this.detalleError = detalleError;
	}

	@Override
	public String toString() {
		return "RespuestaValidacionFirmadoXmlFacturaDto [detalleError=" + detalleError + "]";
	}

}