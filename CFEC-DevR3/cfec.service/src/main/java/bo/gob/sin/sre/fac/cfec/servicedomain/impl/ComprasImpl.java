package bo.gob.sin.sre.fac.cfec.servicedomain.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bo.gob.sin.sre.fac.cfec.domain.IJsonComprasDomain;
import bo.gob.sin.sre.fac.cfec.domain.IJsonDomain;
import bo.gob.sin.sre.fac.cfec.dto.RespuestaDatosCufDto;
import bo.gob.sin.sre.fac.cfec.dto.RespuestaFacturaVentasDto;
import bo.gob.sin.sre.fac.cfec.dto.RespuestaObtenerComprasDto;
import bo.gob.sin.sre.fac.cfec.dto.SolicitudObtencionFacturaVentasDto;
import bo.gob.sin.sre.fac.cfec.dto.SolicitudRevertirCadenaDto;
import bo.gob.sin.sre.fac.cfec.service.IServiciosReversionClientRest;
import bo.gob.sin.sre.fac.cfec.servicedomain.ICompras;
import bo.gob.sin.sre.fac.cfec.servicedomain.IOperacionesCufService;

@Service
@Transactional
public class ComprasImpl implements ICompras {
	
	private static final Logger LOG = LoggerFactory.getLogger(ComprasImpl.class);

	@Autowired
	IJsonComprasDomain iJsonComprasDomain;
	
	@Autowired
	IServiciosReversionClientRest iServiciosReversionClientRest;
	
	@Autowired
	IOperacionesCufService iOperacionesCufService;

	@Override
	public RespuestaObtenerComprasDto recepcionBdComprasIndividual(String pCuf, long pNumeroFactura, String pNumeroDocumento, String pCodigoSistema, String pCufd, String pCuis, Integer pCodigoSucursal, Integer pCodigoPuntoVenta, Integer pCodigoAmbiente) {
		RespuestaDatosCufDto vRespuesta = new RespuestaDatosCufDto();
		SolicitudRevertirCadenaDto vSolicitud = new SolicitudRevertirCadenaDto();
		vSolicitud.setCodigo(pCuf);
		vRespuesta = iOperacionesCufService.revertirCuf(vSolicitud);
		if(vRespuesta != null) 
			return iJsonComprasDomain.recepcionBdComprasIndividual(vRespuesta.getDocumentoSector(), pCuf, pNumeroFactura, pNumeroDocumento, pCodigoSistema, pCufd, pCuis, pCodigoSucursal, pCodigoPuntoVenta, pCodigoAmbiente);
		else
			return null;
	}

	@Override
	public List<RespuestaObtenerComprasDto> recepcionBdComprasPorDia(Date pFechaInicio, Date pFechaFin, String pNumeroDocumento, String pCodigoSistema, String pCufd, String pCuis, Integer pCodigoSucursal, Integer pCodigoPuntoVenta, Integer pCodigoAmbiente) {
		return iJsonComprasDomain.recepcionBdComprasPorDia(pFechaInicio, pFechaFin, pNumeroDocumento, pCodigoSistema, pCufd, pCuis, pCodigoSucursal, pCodigoPuntoVenta, pCodigoAmbiente);
	}
	
}