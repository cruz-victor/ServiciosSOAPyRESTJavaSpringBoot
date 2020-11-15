package bo.gob.sin.sre.fac.cfec.servicedomain.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bo.gob.sin.sre.fac.cfec.domain.IOperacionesCufDomain;
import bo.gob.sin.sre.fac.cfec.dto.GenerarCufDto;
import bo.gob.sin.sre.fac.cfec.dto.RespuestaDatosCufDto;
import bo.gob.sin.sre.fac.cfec.dto.RespuestaFacturaVentasDto;
import bo.gob.sin.sre.fac.cfec.dto.SolicitudObtencionFacturaVentasDto;
import bo.gob.sin.sre.fac.cfec.dto.SolicitudRevertirCadenaDto;
import bo.gob.sin.sre.fac.cfec.dto.ValidarCufDto;
import bo.gob.sin.sre.fac.cfec.dto.parameter.Parametros;
import bo.gob.sin.sre.fac.cfec.service.IServiciosReversionClientRest;
import bo.gob.sin.sre.fac.cfec.servicedomain.IOperacionesCufService;
import bo.gob.sin.sre.fac.cfec.servicedomain.validation.CodigosMensajesServiciosSOAPServiceImpl;

@Service
@Transactional
public class OperacionesCufService implements IOperacionesCufService{
	
	@Autowired
	IOperacionesCufDomain iOperacionesCufDomain;
	
	@Autowired
	IServiciosReversionClientRest iServiciosReversionClientRest;
	
	@Override
	public  String generadorCUF(GenerarCufDto dtoCuf) {
		return iOperacionesCufDomain.generadorCUF(dtoCuf.getpNit(),dtoCuf.getpSucursal(),dtoCuf.getpFecha(),dtoCuf.getpModalidad(),dtoCuf.getpTipoEmision(),dtoCuf.getpTipoDocumentoFiscal(),dtoCuf.getpTipoDocumentoSector(),dtoCuf.getpNumFactura(),dtoCuf.getpPuntoVenta());
	}
	@Override
	public boolean verificacionCuf(ValidarCufDto dtoCuf) {
		return iOperacionesCufDomain.verificacionCuf(dtoCuf.getpNit(),dtoCuf.getpSucursal(),dtoCuf.getpFecha(),dtoCuf.getpModalidad(),dtoCuf.getpTipoEmision(),dtoCuf.getpTipoDocumentoFiscal(),dtoCuf.getpTipoDocumentoSector(),dtoCuf.getpCuf(),dtoCuf.getpNumFactura(),dtoCuf.getpPuntoVenta());
	}
	@Override
	public RespuestaFacturaVentasDto obtenerFacturaVentas(SolicitudObtencionFacturaVentasDto pSolicitud) {
		
		RespuestaFacturaVentasDto vRespuesta = new RespuestaFacturaVentasDto();
		vRespuesta.setOk(false);
		SolicitudRevertirCadenaDto vSolicitud = new SolicitudRevertirCadenaDto();
		RespuestaDatosCufDto vRespuestaCuf = new RespuestaDatosCufDto();
		vSolicitud.setCodigo(pSolicitud.getAutorizacionCuf());
		
		if (pSolicitud.getNit() > 0 && pSolicitud.getAutorizacionCuf() != null) {
			vRespuestaCuf = this.revertirCuf(vSolicitud);
			if (vRespuestaCuf.getNitEmisor() != null && vRespuestaCuf.getNitEmisor() > 0) {
				if (!pSolicitud.getNit().equals(vRespuestaCuf.getNitEmisor())) {
					return vRespuesta;
				}
			} else {
				return vRespuesta;
			}
		} else {
			return vRespuesta;
		}
		
		return iOperacionesCufDomain.obtenerFacturaVentas(pSolicitud, vRespuestaCuf);
	}
	
	@Override
	public RespuestaDatosCufDto revertirCuf(SolicitudRevertirCadenaDto pSolicitud) {
		RespuestaDatosCufDto vRespuestaCuf = new RespuestaDatosCufDto();
		if (pSolicitud.getCodigo() != null && !pSolicitud.getCodigo().isEmpty()) {
			vRespuestaCuf = iServiciosReversionClientRest.reversionCuf(pSolicitud);
			if (vRespuestaCuf != null && vRespuestaCuf.getNitEmisor() != null && vRespuestaCuf.getNitEmisor() > 0) 
				return vRespuestaCuf;
			else
				return null;
		} else {
			return null;
		}
	}
}
