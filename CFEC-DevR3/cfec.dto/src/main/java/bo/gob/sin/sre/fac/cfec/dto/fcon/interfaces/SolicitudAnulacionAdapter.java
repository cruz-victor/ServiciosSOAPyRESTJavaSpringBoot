package bo.gob.sin.sre.fac.cfec.dto.fcon.interfaces;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class SolicitudAnulacionAdapter extends XmlAdapter<SolicitudAnulacion, ISolicitudAnulacion> {

	@Override
	public ISolicitudAnulacion unmarshal(SolicitudAnulacion mensaje) throws Exception {
		return mensaje;
	}

	@Override
	public SolicitudAnulacion marshal(ISolicitudAnulacion mensaje) throws Exception {
		if (mensaje instanceof SolicitudAnulacion) {
			return (SolicitudAnulacion) mensaje;
		}
		SolicitudAnulacion impl = new SolicitudAnulacion();

		impl.setCodigoSistema(mensaje.getCodigoSistema());

		impl.setCodigoAmbiente(mensaje.getCodigoAmbiente());

		impl.setCodigoEmision(mensaje.getCodigoEmision());

		impl.setCodigoModalidad(mensaje.getCodigoModalidad());

		impl.setNit(mensaje.getNit());

		impl.setCuis(mensaje.getCuis());

		impl.setCufd(mensaje.getCufd());

		impl.setCodigoDocumentoFiscal(mensaje.getCodigoDocumentoFiscal());

		impl.setCodigoDocumentoSector(mensaje.getCodigoDocumentoSector());

		impl.setCodigoSucursal(mensaje.getCodigoSucursal());

		impl.setCodigoPuntoVenta(mensaje.getCodigoPuntoVenta());

		impl.setNumeroDocumentoFiscal(mensaje.getNumeroDocumentoFiscal());

		impl.setCuf(mensaje.getCuf());

		impl.setCodigoMotivo(mensaje.getCodigoMotivo());

		impl.setCaed(mensaje.getCaed());

		return impl;
	}

}
