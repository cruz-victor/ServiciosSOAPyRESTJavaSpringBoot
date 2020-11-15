package bo.gob.sin.sre.fac.dao.impl;

import bo.gob.sin.sre.fac.dao.ISeguimientoCertificacionSistemasDao;
import bo.gob.sin.sre.fac.dto.DetalleCasosDePruebaEtapa0Dto;
import bo.gob.sin.sre.fac.dto.ListaSeguimientoCertificacionSistemasFaseDosDto;
import bo.gob.sin.sre.fac.dto.SeguimientoCertificacionSistemasDto;
import bo.gob.sin.sre.fac.dto.SeguimientoCertificacionSistemasFaseDosDto;
import bo.gob.sin.sre.fac.dto.SeguimientoDetalleCertificacionSistemasDto;
import bo.gob.sin.sre.fac.dto.SistemasContribuyentesDto;
import bo.gob.sin.sre.fac.model.SreRutasServicios;
import bo.gob.sin.str.ccs.dao.AbstractGenericDao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Repository;
import com.google.gson.Gson;

@Repository
@Transactional
public class SeguimientoCertificacionSistemasDaoImpl extends AbstractGenericDao<SreRutasServicios> implements ISeguimientoCertificacionSistemasDao, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** 
	 * Obtener el porcentaje de la tabla Pruebas de Certificación de Sistemas
	 * 
	 * @author: junior.flores 
	 * @Fecha: 03/06/2019
	 * @param pSistemaId, Código unico de Sistema
	 * 		  pNit, Número de Identificación Tributaria
	 * @return   Devuelve el listado del objeto SeguimientoCertificacionSistemasDto.
	 */
	@Override
	public List<SeguimientoCertificacionSistemasDto> obtenerListaCertificacionSistemasBd(Long pSistemaId, Long pNit) {
		List<SeguimientoCertificacionSistemasDto> listaSeguimientoCertificacionSistemasDto = new ArrayList<SeguimientoCertificacionSistemasDto>();
		SeguimientoCertificacionSistemasDto seguimientoCertificacionSistemasDto =null;
		try {
			if (pSistemaId != null && pSistemaId>0) {
				Query query = getSession()
						.createNativeQuery(
								"select sre_recaudaciones.sre_fac_porcentaje_log_caso_pruebas_por_etapas(:pSistemaId, :pNit)")
						.setParameter("pSistemaId",pSistemaId).setParameter("pNit", pNit);
				List<String> x = query.getResultList();
		         
		            for(String item : x){
		            	
		            	seguimientoCertificacionSistemasDto =new SeguimientoCertificacionSistemasDto();
			            seguimientoCertificacionSistemasDto = new Gson().fromJson(item, SeguimientoCertificacionSistemasDto.class);
			            listaSeguimientoCertificacionSistemasDto.add(seguimientoCertificacionSistemasDto);
		            }
		           		
				return listaSeguimientoCertificacionSistemasDto;
			} else {
				return new ArrayList<SeguimientoCertificacionSistemasDto>();
			}
		} catch (NoResultException e) {
			return null;
		}
	}
	
	/**
	 * @author rosario.garcia
	 * 
	 * @fecha 05/06/2019
	 */
	public List<DetalleCasosDePruebaEtapa0Dto> obtenerListaDetalleCasosDePruebaEtapa0Bd(Long pSistemaId, Long pNit){
		List<DetalleCasosDePruebaEtapa0Dto> listaDetalleCasosDePruebaEtapa0 = new ArrayList<DetalleCasosDePruebaEtapa0Dto>();
		DetalleCasosDePruebaEtapa0Dto detalleCasosDePruebaEtapa0Dto = null;
		try {
			if (pSistemaId != null && pNit != null && pSistemaId > 0 && pNit > 0) {
				Query query = getSession()
						      .createNativeQuery("select sre_recaudaciones.sre_fac_detalle_casos_pruebas_etapa_0_v2(:pSistemaId, :pNit)")
						      .setParameter("pSistemaId",pSistemaId).setParameter("pNit", pNit);
				List<String> casosPrueba = (List<String>)query.getResultList();
					
					for(String item : casosPrueba){
						detalleCasosDePruebaEtapa0Dto = new DetalleCasosDePruebaEtapa0Dto();
						detalleCasosDePruebaEtapa0Dto = new Gson().fromJson(item, DetalleCasosDePruebaEtapa0Dto.class);
						listaDetalleCasosDePruebaEtapa0.add(detalleCasosDePruebaEtapa0Dto);
					}
				return listaDetalleCasosDePruebaEtapa0;
				
			}else {
				return new ArrayList<DetalleCasosDePruebaEtapa0Dto>();
			}
			
		}catch(NoResultException e) {
			return null;
		}
	}
	
	/** 
	 * Obtener el detalle del seguimiento de Certificación de Sistemas
	 * 
	 * @author: junior.flores 
	 * @Fecha: 14/06/2019
	 * @param pSistemaId, Código unico de Sistema 
	 * 		  pEtapaPruebaCertificacionId, Código unico de la etapa de Certificación de Sistemas
	 * @return   Devuelve el listado del objeto SeguimientoDetalleCertificacionSistemasDto.
	 */
	@Override
	public List<SeguimientoDetalleCertificacionSistemasDto> obtenerListaDetalleCertificacionSistemasBd(Long pSistemaId, Integer pEtapaPruebaCertificacionId, Long pNit) {
		List<SeguimientoDetalleCertificacionSistemasDto> listaSeguimientoDetalleCertificacionSistemasDto = new ArrayList<SeguimientoDetalleCertificacionSistemasDto>();
		SeguimientoDetalleCertificacionSistemasDto SeguimientoDetalleCertificacionSistemasDto =null;
		try {
			if (pSistemaId>0) {
				Query query = getSession()
						.createNativeQuery(
								"select sre_recaudaciones.sre_fac_match_log_caso_pruebas_por_etapa(:pSistemaId,:pEtapaPruebaCertificacionId,:pNit)")
						.setParameter("pSistemaId",pSistemaId)
						.setParameter("pEtapaPruebaCertificacionId",pEtapaPruebaCertificacionId)
						.setParameter("pNit", pNit);
				List<String> x = query.getResultList();
		         
		            for(String item : x){
		            	
		            	SeguimientoDetalleCertificacionSistemasDto =new SeguimientoDetalleCertificacionSistemasDto();
			            SeguimientoDetalleCertificacionSistemasDto = new Gson().fromJson(item, SeguimientoDetalleCertificacionSistemasDto.class);
			            listaSeguimientoDetalleCertificacionSistemasDto.add(SeguimientoDetalleCertificacionSistemasDto);
		            }
		           		
				return listaSeguimientoDetalleCertificacionSistemasDto;
			} else {
				return new ArrayList<SeguimientoDetalleCertificacionSistemasDto>();
			}
		} catch (Exception e) {
			return null;
		}
	}
	
	/** 
	 * Obtener la lista de contribuyentes
	 * 
	 * @author: junior.flores 
	 * @Fecha: 18/06/2019
	 * @param  pSistemaContribuyenteId, Código unico del sistema contribuyente
	 * 		   pEstadoSistemaContribuyenteId, Código unico del estado del sistema contribuyente 
	 * @return   Devuelve el listado del objeto SistemasContribuyentesDto.
	 */
	@Override
	public List<SistemasContribuyentesDto> obtenerSistemaContribuyenteBd(Long pSistemaContribuyenteId, Integer pEstadoSistemaContribuyenteId) {
		List<SistemasContribuyentesDto> listaSistemasContribuyentesDto = new ArrayList<SistemasContribuyentesDto>();
		SistemasContribuyentesDto SistemasContribuyentesDto =null;
		try {
			if (pSistemaContribuyenteId>0 && pEstadoSistemaContribuyenteId>0) {
				Query query = getSession()
						.createNativeQuery(
								"select sre_recaudaciones.sre_fac_obtener_sistemas_por_contribuyente(:pSistemaContribuyenteId, :pEstadoSistemaContribuyenteId)")
						.setParameter("pSistemaContribuyenteId",pSistemaContribuyenteId)
						.setParameter("pEstadoSistemaContribuyenteId",pEstadoSistemaContribuyenteId);
				List<String> x = query.getResultList();
		         
		            for(String item : x){
		            	
		            	SistemasContribuyentesDto =new SistemasContribuyentesDto();
			            SistemasContribuyentesDto = new Gson().fromJson(item, SistemasContribuyentesDto.class);
			            listaSistemasContribuyentesDto.add(SistemasContribuyentesDto);
		            }
		           		
				return listaSistemasContribuyentesDto;
			} else {
				return new ArrayList<SistemasContribuyentesDto>();
			}
		} catch (Exception e) {
			return null;
		}
	}
	
	/***
	 * @descripcion obtiene lista de casos de prueba sugeridos de la Etapa 0
	 * @author rosario.garcia
	 * @fecha 10/07/2019
	 */
	public List<DetalleCasosDePruebaEtapa0Dto> obtenerListaCasosPruebaSugeridosEtapa0(Long pSistemaId ){
		System.out.println("Ingreso al dao");
		List<DetalleCasosDePruebaEtapa0Dto> vListaCasosPruebaSugeridas = new ArrayList<DetalleCasosDePruebaEtapa0Dto>();
		DetalleCasosDePruebaEtapa0Dto vDetalleCasosDePruebaEtapa0 = null;
		try {
			if (pSistemaId != null && pSistemaId > 0 ) {
				Query query = getSession()
					      				  .createNativeQuery("select sre_recaudaciones.sre_fac_obtener_casos_prueba_sugeridos_etapa_0_v1(:pSistemaId)")
					      				  .setParameter("pSistemaId",pSistemaId);
				List<String> casosPrueba = (List<String>)query.getResultList();
				
					for(String item : casosPrueba){						
						vDetalleCasosDePruebaEtapa0 = new DetalleCasosDePruebaEtapa0Dto();
						vDetalleCasosDePruebaEtapa0 = new Gson().fromJson(item, DetalleCasosDePruebaEtapa0Dto.class);
						vListaCasosPruebaSugeridas.add(vDetalleCasosDePruebaEtapa0);
					}
					
				return vListaCasosPruebaSugeridas;
			} else {
				return new ArrayList<DetalleCasosDePruebaEtapa0Dto>();
			}
			
		} catch (NoResultException e) {
			return null;
		}	
	}
	
	/** 
	 * Realiza la actualizaci�n del Match para las etapas de Certificaci�n de Sistemas 
	 * 
	 * @author: junior.flores 
	 * @Fecha: 06/09/2019
	 * @param pSistemaId, C�digo unico de Sistema 
	 * 		  pEtapaPruebaCertificacionId, C�digo unico de la etapa de Certificaci�n de Sistemas
	 * @return   Devuelve el listado del objeto SeguimientoDetalleCertificacionSistemasDto.
	 */
	@Override
	public Short actualizarMatchBd(Long pSistemaId, Integer pEtapaPruebaCertificacionId) {
		Short vResultado=0;
		try {
			if (pSistemaId>=0 && pEtapaPruebaCertificacionId>=0) {
				Query query = getSession()
						.createNativeQuery(
								"select sre_recaudaciones.sre_fac_pru_match_log_casos_prueba_etapa(:pSistemaId,:pEtapaPruebaCertificacionId)")
						.setParameter("pSistemaId",pSistemaId)
						.setParameter("pEtapaPruebaCertificacionId",pEtapaPruebaCertificacionId);			
				vResultado = (Short)query.getSingleResult();
		         		           		
				return vResultado;
			} else {
				return 0;
			}
		} catch (Exception e) {
			return null;
		}
	}
	
	/** 
	 * Obtener el listado de las etapas de la fase 2 del Seguimiento de Certificaci�n de Sistemas
	 * 
	 * @author: junior.flores 
	 * @Fecha: 20/09/2019
	 * @param  pSolicitud, Datos necesarios para obtener el listado
	 * 		   
	 * @return   Devuelve el listado de las etapas de la fase 2 del Seguimiento de Certificaci�n de Sistemas.
	 */
	@Override
	public List<SeguimientoCertificacionSistemasFaseDosDto> listaEtapaFaseDosBd(ListaSeguimientoCertificacionSistemasFaseDosDto pSolicitud) 
	{		
		List<SeguimientoCertificacionSistemasFaseDosDto> listaSeguimientoCertificacionSistemasFaseDosDto = new ArrayList<SeguimientoCertificacionSistemasFaseDosDto>();
		SeguimientoCertificacionSistemasFaseDosDto SistemasContribuyentesDto =null;
		try 
		{
			if (pSolicitud.getDatosEntradaSistemaId()>0) 
			{
				Query query = getSession()
						.createNativeQuery(
								"select sre_recaudaciones.sre_fac_obtener_listado_etapas_fase_dos(:pSistemaContribuyenteId)")
						.setParameter("pSistemaContribuyenteId",pSolicitud.getDatosEntradaSistemaId());
				
					List<String> x = query.getResultList();
		         
		            for(String item : x)
		            {	
		            	SistemasContribuyentesDto =new SeguimientoCertificacionSistemasFaseDosDto();
		            	SistemasContribuyentesDto = new Gson().fromJson(item, SeguimientoCertificacionSistemasFaseDosDto.class);
			            listaSeguimientoCertificacionSistemasFaseDosDto.add(SistemasContribuyentesDto);
		            }
		           		
				return listaSeguimientoCertificacionSistemasFaseDosDto;
			} 
			else 
			{
				return new ArrayList<SeguimientoCertificacionSistemasFaseDosDto>();
			}
		} 
		catch (Exception e) 
		{
			return null;
		}
	}
}
