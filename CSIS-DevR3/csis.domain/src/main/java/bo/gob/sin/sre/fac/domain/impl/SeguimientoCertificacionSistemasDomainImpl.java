package bo.gob.sin.sre.fac.domain.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bo.gob.sin.sre.fac.dao.ISeguimientoCertificacionSistemasDao;
import bo.gob.sin.sre.fac.domain.ISeguimientoCertificacionSistemasDomain;
import bo.gob.sin.sre.fac.dto.DetalleCasosDePruebaEtapa0Dto;
import bo.gob.sin.sre.fac.dto.ListaSeguimientoCertificacionSistemasFaseDosDto;
import bo.gob.sin.sre.fac.dto.SeguimientoCertificacionSistemasDto;
import bo.gob.sin.sre.fac.dto.SeguimientoCertificacionSistemasFaseDosDto;
import bo.gob.sin.sre.fac.dto.SeguimientoDetalleCertificacionSistemasDto;
import bo.gob.sin.sre.fac.dto.SistemasContribuyentesDto;

@Service
@Transactional
public class SeguimientoCertificacionSistemasDomainImpl implements ISeguimientoCertificacionSistemasDomain, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	ISeguimientoCertificacionSistemasDao iSeguimientoCertificacionSistemas;
	
	/** 
	 * Obtener el porcentaje de la tabla Pruebas de Certificaci贸n de Sistemas
	 * 
	 * @author: junior.flores 
	 * @Fecha: 03/06/2019
	 * @param pSistemaId, C贸digo unico de Sistema
	 * 		  pNit, N煤mero de Identificaci贸n Tributaria
	 * @return   Devuelve el listado del objeto SeguimientoCertificacionSistemasDto.
	 */
	public List<SeguimientoCertificacionSistemasDto> obtenerListaCertificacionSistemas(Long pSistemaId, Long pNit) {
		List<SeguimientoCertificacionSistemasDto> seguimientoCertificacionSistemasDto=iSeguimientoCertificacionSistemas.obtenerListaCertificacionSistemasBd(pSistemaId, pNit);
		return seguimientoCertificacionSistemasDto;
	}
	/**
	 * @author rosario.garcia
	 * @fecha 05/06/2019
	 * 
	 */
	public List<DetalleCasosDePruebaEtapa0Dto> obtenerListaDetalleCasosDePruebaEtapa0(Long pSistemaId, Long pNit){
		List<DetalleCasosDePruebaEtapa0Dto> listaDetalleCasosDePruebaEtapa0 = iSeguimientoCertificacionSistemas.obtenerListaDetalleCasosDePruebaEtapa0Bd(pSistemaId, pNit);
		return listaDetalleCasosDePruebaEtapa0;
	} 
	
	/** 
	 * Obtener el detalle del seguimiento de Certificaci贸n de Sistemas
	 * 
	 * @author: junior.flores 
	 * @Fecha: 14/06/2019
	 * @param pSistemaId, C贸digo unico de Sistema 
	 * 		  pEtapaPruebaCertificacionId, C贸digo unico de la etapa de Certificaci贸n de Sistemas
	 * @return   Devuelve el listado del objeto SeguimientoDetalleCertificacionSistemasDto.
	 */
	@Override	
	public List<SeguimientoDetalleCertificacionSistemasDto> obtenerListaDetalleCertificacionSistemas(Long pSistemaId,Integer pEtapaPruebaCertificacionId, Long pNit) {
		List<SeguimientoDetalleCertificacionSistemasDto> SeguimientoDetalleCertificacionSistemasDto=iSeguimientoCertificacionSistemas.obtenerListaDetalleCertificacionSistemasBd(pSistemaId,pEtapaPruebaCertificacionId,pNit);
		return SeguimientoDetalleCertificacionSistemasDto;
	}
	
	/** 
	 * Obtener la lista de contribuyentes
	 * 
	 * @author: junior.flores 
	 * @Fecha: 18/06/2019
	 * @param  pSistemaContribuyenteId, C贸digo unico del sistema contribuyente
	 * 		   pEstadoSistemaContribuyenteId, C贸digo unico del estado del sistema contribuyente 
	 * @return   Devuelve el listado del objeto SistemasContribuyentesDto.
	 */
	@Override	
	public List<SistemasContribuyentesDto> obtenerListaSistemaContribuyente(Long pSistemaContribuyenteId, Integer pEstadoSistemaContribuyenteId) {
		List<SistemasContribuyentesDto> SistemasContribuyentesDto=iSeguimientoCertificacionSistemas.obtenerSistemaContribuyenteBd(pSistemaContribuyenteId, pEstadoSistemaContribuyenteId);
		return SistemasContribuyentesDto;
	}
	
	/**
	 * @descripcion Obtiene la lista de casos de prueba sugeridas de la etapa 0 - Generacion de CUF
	 * @author rosario.garcia
	 * @fecha 10/07/2019
	 */
	public List<DetalleCasosDePruebaEtapa0Dto> obtenerListaCasosPruebaSugeridosEtapa0(Long pSistemaId){
		List<DetalleCasosDePruebaEtapa0Dto> vListaCasosPruebaSugeridasEtapa0 = iSeguimientoCertificacionSistemas.obtenerListaCasosPruebaSugeridosEtapa0(pSistemaId);
		return vListaCasosPruebaSugeridasEtapa0;
	}
	
	/** 
	 * Realiza la actualizaci髇 del Match para las etapas de Certificaci髇 de Sistemas 
	 * 
	 * @author: junior.flores 
	 * @Fecha: 06/09/2019
	 * @param pSistemaId, C骴igo unico de Sistema 
	 * 		  pEtapaPruebaCertificacionId, C骴igo unico de la etapa de Certificaci髇 de Sistemas
	 * @return   Devuelve el listado del objeto SeguimientoDetalleCertificacionSistemasDto.
	 */
	@Override
	public Short actualizarMatchBd(Long pSistemaId, Integer pEtapaPruebaCertificacionId){
		Short vEstadoMatch = iSeguimientoCertificacionSistemas.actualizarMatchBd(pSistemaId, pEtapaPruebaCertificacionId);
			return vEstadoMatch;
	}	
	
	/** 
	 * Obtener el listado de las etapas de la fase 2 del Seguimiento de Certificaci髇 de Sistemas
	 * 
	 * @author: junior.flores 
	 * @Fecha: 20/09/2019
	 * @param  pSolicitud, Datos necesarios para obtener el listado
	 * 		   
	 * @return   Devuelve el listado de las etapas de la fase 2 del Seguimiento de Certificaci髇 de Sistemas.
	 */
	@Override	
	public List<SeguimientoCertificacionSistemasFaseDosDto> listaEtapaFaseDos(ListaSeguimientoCertificacionSistemasFaseDosDto pSolicitud) {
		List<SeguimientoCertificacionSistemasFaseDosDto> SistemasContribuyentesDto=iSeguimientoCertificacionSistemas.listaEtapaFaseDosBd(pSolicitud);
		return SistemasContribuyentesDto;
	}
}
