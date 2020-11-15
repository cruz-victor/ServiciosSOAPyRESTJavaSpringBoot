package bo.gob.sin.sre.fac.cfec.dto;

public class SolicitudValidacionFirmadoXmlFacturaDto {
	private String archivo;

	public SolicitudValidacionFirmadoXmlFacturaDto() {
	}

	public String getArchivo() {
		return archivo;
	}

	public void setArchivo(String archivo) {
		this.archivo = archivo;
	}

	@Override
	public String toString() {
		return "SolicitudValidacionFirmadoXmlFactura [archivo=" + archivo + "]";
	}

}
