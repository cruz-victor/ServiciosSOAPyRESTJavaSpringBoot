package bo.gob.sin.sre.fac.cfec.dto.fcon.interfaces;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class SolicitudValidacionAnulacionAdapter
		extends XmlAdapter<SolicitudValidacionAnulacion, ISolicitudValidacionAnulacion> {

	@Override
	public ISolicitudValidacionAnulacion unmarshal(SolicitudValidacionAnulacion mensaje) throws Exception {
		return mensaje;
	}

	@Override
	public SolicitudValidacionAnulacion marshal(ISolicitudValidacionAnulacion mensaje) throws Exception {
		if (mensaje instanceof SolicitudValidacionAnulacion) {
			return (SolicitudValidacionAnulacion) mensaje;
		}
		SolicitudValidacionAnulacion impl = new SolicitudValidacionAnulacion();

		impl.setCodigoSistema(mensaje.getCodigoSistema());

		impl.setCodigoAmbiente(mensaje.getCodigoAmbiente());

		impl.setCodigoEmision(mensaje.getCodigoEmision());

		impl.setCodigoModalidad(mensaje.getCodigoModalidad());

		impl.setNit(mensaje.getNit());

		impl.setCuis(mensaje.getCuis());

		impl.setCufd(mensaje.getCufd());

		impl.setCuf(mensaje.getCuf());

		impl.setCodigoDocumentoFiscal(mensaje.getCodigoDocumentoFiscal());

		impl.setCodigoDocumentoSector(mensaje.getCodigoDocumentoSector());

		impl.setCodigoSucursal(mensaje.getCodigoSucursal());

		impl.setCodigoPuntoVenta(mensaje.getCodigoPuntoVenta());

		impl.setNumeroDocumentoFiscal(mensaje.getNumeroDocumentoFiscal());

		impl.setCaed(mensaje.getCaed());

		impl.setCodigoMotivo(mensaje.getCodigoMotivo());

		impl.setCodigoRecepcion(mensaje.getCodigoRecepcion());

		return impl;
	}

}
