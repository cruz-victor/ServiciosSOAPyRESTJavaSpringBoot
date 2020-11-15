package bo.gob.sin.sre.fac.dao;

import java.util.List;

import bo.gob.sin.sre.fac.dto.DetalleCasosDePruebaEtapa0Dto;
import bo.gob.sin.sre.fac.dto.ListaSeguimientoCertificacionSistemasFaseDosDto;
import bo.gob.sin.sre.fac.dto.SeguimientoCertificacionSistemasDto;
import bo.gob.sin.sre.fac.dto.SeguimientoCertificacionSistemasFaseDosDto;
import bo.gob.sin.sre.fac.dto.SeguimientoDetalleCertificacionSistemasDto;
import bo.gob.sin.sre.fac.dto.SistemasContribuyentesDto;

public interface ISeguimientoCertificacionSistemasDao {

	/** 
	 * Obtener el porcentaje de la tabla Pruebas de Certificaci贸n de Sistemas
	 * 
	 * @author: junior.flores 
	 * @Fecha: 03/06/2019
	 * @param pSistemaId, C贸digo unico de Sistema
	 * 		  pNit, N煤mero de Identificaci贸n Tributaria
	 * @return   Devuelve el listado del objeto SeguimientoCertificacionSistemasDto.
	 */
	public List<SeguimientoCertificacionSistemasDto> obtenerListaCertificacionSistemasBd(Long pSistemaId, Long pNit);
	
	public List<DetalleCasosDePruebaEtapa0Dto> obtenerListaDetalleCasosDePruebaEtapa0Bd(Long pSistemaId, Long pNit);
	
	/** 
	 * Obtener el detalle del seguimiento de Certificaci贸n de Sistemas
	 * 
	 * @author: junior.flores 
	 * @Fecha: 14/06/2019
	 * @param pSistemaId, C贸digo unico de Sistema  
	 * 		  pEtapaPruebaCertificacionId, C贸digo unico de la etapa de Certificaci贸n de Sistemas
	 * @return   Devuelve el listado del objeto SeguimientoDetalleCertificacionSistemasDto.
	 */
	public List<SeguimientoDetalleCertificacionSistemasDto> obtenerListaDetalleCertificacionSistemasBd(Long pSistemaId,Integer pEtapaPruebaCertificacionId, Long pNit);
	
	/** 
	 * Obtener la lista de contribuyentes
	 * 
	 * @author: junior.flores 
	 * @Fecha: 18/06/2019
	 * @param  pSistemaContribuyenteId, C贸digo unico del sistema contribuyente
	 * 		   pEstadoSistemaContribuyenteId, C贸digo unico del estado del sistema contribuyente 
	 * @return   Devuelve el listado del objeto SistemasContribuyentesDto.
	 */
	public List<SistemasContribuyentesDto> obtenerSistemaContribuyenteBd(Long pSistemaContribuyenteId, Integer pEstadoSistemaContribuyenteId);
	
	/**
	 * @descripcion: Obtiene la lista de las pruebas sugeridas de la Etapa 0 (Generacion de CUF)
	 * @Fecha 10/07/2019
	 * @param pSitemaId
	 * @return Lista de casos de pruebas sugeridas
	 */
	public List<DetalleCasosDePruebaEtapa0Dto> obtenerListaCasosPruebaSugeridosEtapa0(Long pSistemaId );
	
	/**
	 * @descripcion: Obtiene la lista de las pruebas sugeridas de la Etapa 0 (Generacion de CUF)
	 * @Fecha 10/07/2019
	 * @param pSitemaId
	 * @return Lista de casos de pruebas sugeridas
	 */
	public Short actualizarMatchBd(Long pSistemaId, Integer pEtapaPruebaCertificacionId);
	
	/** 
	 * Obtener el listado de las etapas de la fase 2 del Seguimiento de Certificaci髇 de Sistemas
	 * 
	 * @author: junior.flores 
	 * @Fecha: 20/09/2019
	 * @param  pSolicitud, Datos necesarios para obtener el listado
	 * 		   
	 * @return   Devuelve el listado de las etapas de la fase 2 del Seguimiento de Certificaci髇 de Sistemas.
	 */
	public List<SeguimientoCertificacionSistemasFaseDosDto> listaEtapaFaseDosBd(ListaSeguimientoCertificacionSistemasFaseDosDto pSolicitud);
}
