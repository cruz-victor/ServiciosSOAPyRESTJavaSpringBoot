package bo.gob.sin.sre.fac.cfec.domain.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bo.gob.sin.sre.fac.cfec.dao.IJsonComprasDao;
import bo.gob.sin.sre.fac.cfec.domain.IJsonComprasDomain;
import bo.gob.sin.sre.fac.cfec.dto.RespuestaObtenerComprasDto;

@Service
@Transactional
public class JsonComprasDomainImpl implements IJsonComprasDomain {

	@Autowired
	private IJsonComprasDao iJsonComprasDao;

	@Override
	public String recepcionEtapa1(String pSolicitud) {
		return iJsonComprasDao.recepcionBdEtapa1(pSolicitud);
	}

	@Override
	public String recepcionEtapa2(String pSolicitud) {
		return iJsonComprasDao.recepcionBdEtapa2(pSolicitud);
	}

	@Override
	public String validacionRecepcionXml(String pSolicitud) {
		return iJsonComprasDao.validacionRecepcionBd(pSolicitud);
	}

	@Override
	public String validacionRecepcionPaqueteXml(String pSolicitud) {
		return iJsonComprasDao.validacionRecepcionPaqueteBd(pSolicitud);
	}

	@Override
	public void registrarErroresBd(String pFactura, String pListaErrores) {
		iJsonComprasDao.registrarErroresBd(pFactura, pListaErrores);
	}

	@Override
	public RespuestaObtenerComprasDto recepcionBdComprasIndividual(int pDocumentoSector, String pCuf, long pNumeroFactura, String pNumeroDocumento, String pCodigoSistema, String pCufd, String pCuis, Integer pCodigoSucursal, Integer pCodigoPuntoVenta, Integer pCodigoAmbiente) {
		RespuestaObtenerComprasDto vRespuestaCompras = new RespuestaObtenerComprasDto();
		
		Object vObjeto = iJsonComprasDao.recepcionBdComprasIndividual(pDocumentoSector, pCuf, pNumeroFactura, pNumeroDocumento, pCodigoSistema, pCufd, pCuis, pCodigoSucursal, pCodigoPuntoVenta, pCodigoAmbiente);
		
		if(vObjeto != null) {
		Object[] y = (Object[]) vObjeto;
		vRespuestaCompras.setCufAutorizacion(y[3].toString());
		vRespuestaCompras.setEstadoFactura(y[6].toString());
		vRespuestaCompras.setFechaEmision(y[0].toString());
		vRespuestaCompras.setModalidadFacturacion(y[1].toString());
		vRespuestaCompras.setMontoTotal(y[5].toString());
		vRespuestaCompras.setNitEmisor(y[4].toString());
		vRespuestaCompras.setNumeroFactura(y[2].toString());
		vRespuestaCompras.setTransaccion(true);
		} else 
			return null;
					
		return vRespuestaCompras;
	}

	@Override
	public List<RespuestaObtenerComprasDto> recepcionBdComprasPorDia(Date pFechaInicio, Date pFechaFin, String pNumeroDocumento, String pCodigoSistema, String pCufd, String pCuis, Integer pCodigoSucursal, Integer pCodigoPuntoVenta, Integer pCodigoAmbiente) {

		List<RespuestaObtenerComprasDto> vListCompras = new ArrayList<RespuestaObtenerComprasDto>();
		List<Object> vObjetoRespuesta = iJsonComprasDao.recepcionBdComprasPorDia(pFechaInicio, pFechaFin, pNumeroDocumento, pCodigoSistema, pCufd, pCuis, pCodigoSucursal, pCodigoPuntoVenta, pCodigoAmbiente);
		
		if(vObjetoRespuesta != null && vObjetoRespuesta.size() > 0)
		{
			for (Object item : vObjetoRespuesta) {
				Object[] y = (Object[]) item;
				RespuestaObtenerComprasDto compras = new RespuestaObtenerComprasDto();
				compras.setCufAutorizacion(y[3].toString());
				compras.setEstadoFactura(y[6].toString());
				compras.setFechaEmision(y[0].toString());
				compras.setModalidadFacturacion(y[1].toString());
				compras.setMontoTotal(y[5].toString());
				compras.setNitEmisor(y[4].toString());
				compras.setNumeroFactura(y[2].toString());
				compras.setTransaccion(true);
				vListCompras.add(compras);
			}	
			return vListCompras;
		} else
		return null;
	}
}