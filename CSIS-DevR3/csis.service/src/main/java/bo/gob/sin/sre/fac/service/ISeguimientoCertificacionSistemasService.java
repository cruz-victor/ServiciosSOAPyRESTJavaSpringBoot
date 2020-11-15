package bo.gob.sin.sre.fac.service;

import bo.gob.sin.sre.fac.dto.ListaDetalleCasosDePruebaEtapa0Dto;
import bo.gob.sin.sre.fac.dto.ListaDetalleSeguimientoCertificacionSistemasDto;
import bo.gob.sin.sre.fac.dto.ListaSeguimientoCertificacionSistemasDto;
import bo.gob.sin.sre.fac.dto.ListaSeguimientoCertificacionSistemasFaseDosDto;
import bo.gob.sin.sre.fac.dto.ListaSistemasProveedorDto;

public interface ISeguimientoCertificacionSistemasService {

	/** 
	 * Obtener el porcentaje de la tabla Pruebas de Certificación de Sistemas
	 * 
	 * @author: junior.flores 
	 * @Fecha: 03/06/2019
	 * @param pSistemaId, Código unico de Sistema
	 * 		  pNit, Número de Identificación Tributaria
	 * @return   Devuelve el listado del objeto SeguimientoCertificacionSistemasDto.
	 */
	public ListaSeguimientoCertificacionSistemasDto obtenerListaCertificacionSistemas(Long pSistemaId, Long pNit);
	
	public ListaDetalleCasosDePruebaEtapa0Dto obtenerListaDdetalleCasosDePruebaEtap0(Long pSistemaId, Long pNit);
	
	/** 
	 * Obtener el detalle del seguimiento de Certificación de Sistemas
	 * 
	 * @author: junior.flores 
	 * @Fecha: 14/06/2019
	 * @param pSistemaId, Código unico de Sistema 
	 *        pEtapaPruebaCertificacionId, Código unico de la etapa de Certificación de Sistemas
	 * @return   Devuelve el objeto ListaDetalleSeguimientoCertificacionSistemasDto.
	 */	
	public ListaDetalleSeguimientoCertificacionSistemasDto obtenerListaDetalleCertificacionSistemas(Long pSistemaId, Integer pEtapaPruebaCertificacionId, Long pNit);
	
	/** 
	 * Obtener la lista de contribuyentes
	 * 
	 * @author: junior.flores 
	 * @Fecha: 18/06/2019
	 * @param  pSistemaContribuyenteId, Código unico del sistema contribuyente
	 * 		   pEstadoSistemaContribuyenteId, Código unico del estado del sistema contribuyente 
	 * @return   Devuelve el objeto ListaSistemasProveedorDto.
	 */
	public ListaSistemasProveedorDto consultaSistemasAsociadosContribuyente(Long pSistemaContribuyenteId, Integer pEstadoSistemaContribuyenteId);
	
	/**
	 * @descripcion Obtiene lista de casos de prueba sugeridas de la etaoa 0 - Generacion de CUF
	 * @author rosario.garcia
	 * @fecha 10/07/2019
	 */
	public ListaDetalleCasosDePruebaEtapa0Dto obtenerListaCasosDePruebaSugeridasEtap0(Long pSistemaId);
	
	/**
	 * @descripcion Obtiene lista las etapas y avance de Seguimiento Certificacion Fase II
	 * @author wilson.limachi
	 * @fecha 13/09/2019
	 */
	public ListaSeguimientoCertificacionSistemasFaseDosDto ListaEtapaFaseDos(ListaSeguimientoCertificacionSistemasFaseDosDto vSolictud);
}
