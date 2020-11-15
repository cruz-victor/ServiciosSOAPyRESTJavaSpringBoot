package bo.gob.sin.sre.fac.service;

import bo.gob.sin.sre.fac.dto.DiagramasCertificacionesDto;
import bo.gob.sin.sre.fac.dto.ListaDiagramasCertificacionesDto;
import bo.gob.sin.sre.fac.dto.RespuestaOperacionDto;

public interface IRegistrarDiagramasCertificacionesService {

	/** 
	 * Realiza el registro del diagrama de certificaciones 
	 * 
	 * @author: junior.flores 
	 * @Fecha: 23/09/2019
	 * @param  pSolicitud, Todos los datos correspondientes a Diagrama de Certificaciones 		   
	 * @return   Devuelve el objeto DiagramasCertificacionesDto con la respuesta de la transacci�n.
	 */
	public DiagramasCertificacionesDto registraDiagramasCertificaciones(DiagramasCertificacionesDto pSolicitud);
	
	/** 
	 * Realiza el listado de del diagrama de certificaciones 
	 * 
	 * @author: junior.flores 
	 * @Fecha: 24/09/2019
	 * @param  pSolicitud, Todos los datos para obtener el listado de Diagramas de Certificaciones   		   
	 * @return   Devuelve el objeto ListaDiagramasCertificacionesDto.
	 */
	public ListaDiagramasCertificacionesDto recuperaListaDiagramasCertificaciones(ListaDiagramasCertificacionesDto pSolicitud);
	
	/** 
	 * Realiza la actualizacion del estado de diagrama de certificaciones 
	 * 
	 * @author: wilson.limachi 
	 * @Fecha: 27/09/2019
	 * @param  pDiagramaCertificacionId	   
	 * @return   Devuelve el objeto RespuestaOperacionDto
	 */
	public RespuestaOperacionDto actualizaDiagramasCertificaciones(Long pArchivoId, Long pDiagramaCertificacionId, Long pUsuarioRegistro); 
}
