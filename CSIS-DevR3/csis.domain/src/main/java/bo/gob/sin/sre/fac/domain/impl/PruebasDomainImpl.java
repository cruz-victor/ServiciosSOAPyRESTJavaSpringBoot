package bo.gob.sin.sre.fac.domain.impl;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bo.gob.sin.sre.fac.dao.IPruebasDao;
import bo.gob.sin.sre.fac.domain.IPruebasDomain;
import bo.gob.sin.sre.fac.dto.PruebasDto;
import bo.gob.sin.sre.fac.dto.RespuestaListaPruebasDto;
import bo.gob.sin.sre.fac.dto.SolicitudListaPruebasDto;
import bo.gob.sin.sre.fac.model.SrePruebas;
import bo.gob.sin.str.cexc.LogExcepcion;
import bo.gob.sin.str.cexc.MethodSign;

@Service
@Transactional
public class PruebasDomainImpl implements IPruebasDomain, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(SolicitudCertificacionDomainImpl.class);
	
	@Autowired
	IPruebasDao iSreFacDaoListaPruebas;
	
	
	
	/**
	 * Obtener nombre prueba del sistema
	 * 
	 * @author: Ivan Salas
	 * @Fecha: 
	 * @modificadoPor: Carmen Rosa Silva y Gualberto Condori
	 * @FechaModificacion: 19/06/2018
	 * @param pruebaId, objeto de tipo solicitud
	 * @return   Devuelve el nombre prueba String.
	 */
	@Override
	public String obtenerNombrePrueba(long pPruebaId) {
		LOG.info("Ingresando obtenerNombrePrueba ");
		SrePruebas vRespuesta = new SrePruebas();
		String vResultado="";

		try {
			vRespuesta = iSreFacDaoListaPruebas.obtenerPrueba(pPruebaId);	
			vResultado= vRespuesta.getNombre();
			
		} catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(vResultado));
		}
		LOG.info("Saliendo de recuperar obtenerNombrePrueba: " + vResultado);
		return vResultado;
	}
	
	//uno
	@Override
	public RespuestaListaPruebasDto recuperaListaPruebasPorTipo(Integer pSolicitud) {
		RespuestaListaPruebasDto vRespuesta = new RespuestaListaPruebasDto();
		
		try {
			List<SrePruebas> vListaPruebas= iSreFacDaoListaPruebas.obtenerListaPruebas(pSolicitud);
			
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
	
	@Override
	public SrePruebas recuperaListaPruebasPorTipo1(SrePruebas pSolicitud) {
		SrePruebas vRespuesta = new SrePruebas();
		
		try {
			List<SrePruebas> vListaPruebas= iSreFacDaoListaPruebas.obtenerListaPruebas(pSolicitud.getTipoPruebaId());
			
			List<PruebasDto> vListaRespuesta= new ArrayList<PruebasDto>();
			for (SrePruebas vObjPruebas : vListaPruebas) {
				
				PruebasDto vPruebaDto = new PruebasDto();				
				vPruebaDto.setpruebaId(vObjPruebas.getPruebaId());
				vPruebaDto.setNombre(vObjPruebas.getNombre());
				vPruebaDto.setTipoPruebaId(vObjPruebas.getTipoPruebaId());
				
				vListaRespuesta.add(vPruebaDto);
			}
			//vRespuesta.setLista(vListaRespuesta);
			
		} catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(vRespuesta));
		}
		
		return vRespuesta;

	}
	//prueba beto recuperarlistapruebaspor tipo
	@Override
	public SrePruebas recuperarListaPruebasPorTipo(Integer pTipoPruebaId) {
		LOG.info("Ingresando a recuperarListaPruebasPorTipo");
		SrePruebas vRespuesta = new SrePruebas();
		try {
			vRespuesta = iSreFacDaoListaPruebas.obtenerListasPrueba(pTipoPruebaId);
			
		} catch (Exception e){
			LogExcepcion.registrar(e, LOG, MethodSign.build(vRespuesta));
			
		}
		LOG.info("saliendo de recuperarListaPruebasPorTipo" + vRespuesta);
		return vRespuesta;
		}
	
	/**
	 * Recuperar Lista Pruebas
	 * 
	 * @author: Carmen Rosa Silva
	 * @Fecha: 28/06/20118
	 * @param pSolicitud,objeto de tipo solicitud
	 * @return Devuelve objeto respuesta Dto lista prueba.
	 */
	@Override
	public List<SrePruebas> recuperarListaSistemasAutorizados(Integer pTipoPruebaId) {
		LOG.info("Ingresando recuperarListaSistemasAutorizados ");
		List<SrePruebas> vListaRespuesta = new ArrayList<SrePruebas>();

		try {
			vListaRespuesta = iSreFacDaoListaPruebas.obtenerListaPruebas(pTipoPruebaId);

		} catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(vListaRespuesta));
		}
		LOG.info("Saliendo de recuperar recuperarListaSistemasAutorizados: " + vListaRespuesta.toString());
		return vListaRespuesta;
	}

	
	/** 
	 * Recuperar datos pruebas
	 * 
	 * @author: Carmen Rosa Silva
	 * @Fecha: 29/06/2018
	 * @modificadoPor: 
	 * @FechaModificacion: 
	 * @param pruebaId, objeto de tipo solicitud
	 * @return   Devuelve objeto SrePruebas
	 */
	@Override
	public SrePruebas recuperarSistema(Long pPruebaId) {
		LOG.info("Ingresando recuperarSistema ");
		SrePruebas vListaRespuesta = new SrePruebas();

		try {
			vListaRespuesta = iSreFacDaoListaPruebas.obtenerPrueba(pPruebaId);

		} catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(vListaRespuesta));
		}
		LOG.info("Saliendo de recuperar recuperarSistema: " + vListaRespuesta.toString());
		return vListaRespuesta;
	}

	//TODO CRSP
	@Override
	public RespuestaListaPruebasDto recuperaListaPruebasPorTipo(SolicitudListaPruebasDto pSolicitud) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
