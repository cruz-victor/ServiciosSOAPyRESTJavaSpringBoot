package bo.gob.sin.sre.fac.domain.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bo.gob.sin.str.cexc.LogExcepcion;
import bo.gob.sin.str.cexc.MethodSign;
import bo.gob.sin.sre.fac.dto.PruebasDto;
import bo.gob.sin.sre.fac.dto.RespuestaListaPruebasDto;
import bo.gob.sin.sre.fac.dao.IPruebasDao;
import bo.gob.sin.sre.fac.domain.IBusquedaPruebasSistemasDomain;
import bo.gob.sin.sre.fac.model.SrePruebas;

@Service
@Transactional
public class BusquedaPruebasSistemasDomainImpl implements IBusquedaPruebasSistemasDomain, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(BusquedaPruebasSistemasDomainImpl.class);
	
	@Autowired
	private IPruebasDao iPruebasDao;

	@Override
	public RespuestaListaPruebasDto recuperaListaPruebasPorTipo(Integer pSolicitud) {
		RespuestaListaPruebasDto vRespuesta = new RespuestaListaPruebasDto();
		
		try {
			List<SrePruebas> vListaPruebas= iPruebasDao.obtenerListaPruebas(pSolicitud);
			
			List<PruebasDto> vListaRespuesta= new ArrayList<PruebasDto>();
			for (SrePruebas vObjPruebas : vListaPruebas) {
				
				PruebasDto vPruebaDto = new PruebasDto();				
				vPruebaDto.setpruebaId(vObjPruebas.getPruebaId());
				vPruebaDto.setNombre(vObjPruebas.getNombre());
				vPruebaDto.setTipoPruebaId(vObjPruebas.getTipoPruebaId());
				
				vListaRespuesta.add(vPruebaDto);
			}
			vRespuesta.setLista(vListaRespuesta);
			
		} catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(vRespuesta));
		}
		
		return vRespuesta;
	}
}
