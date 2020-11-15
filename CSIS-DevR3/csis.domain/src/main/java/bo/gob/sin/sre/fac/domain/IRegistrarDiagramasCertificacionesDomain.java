package bo.gob.sin.sre.fac.domain;

import java.util.List;

import bo.gob.sin.sre.fac.model.SreDiagramasCertificaciones;

public interface IRegistrarDiagramasCertificacionesDomain {

	//RCR
	public SreDiagramasCertificaciones registraDiagramasCertificaciones(SreDiagramasCertificaciones pSolicitudSistema);
	
	/** 
	 * Realiza el listado de del diagrama de certificaciones 
	 * 
	 * @author: junior.flores 
	 * @Fecha:  24/09/2019
	 * @param   pEtapaId, C�digo unico de la Etapa.
	 * 		    pSistemaId, C�digo unico del sistema.
	 * 		    pSolicitudCertificacionId, C�digo unico de la solicitud de certificaci�n.	   		   
	 * @return  Devuelve la lista SreDiagramasCertificaciones.
	 */
	public List<SreDiagramasCertificaciones> recuperaListaDiagramasCertificaciones(long pEtapaId, long pSistemaId, long pSolicitudCertificacionId);
	
	/** 
	 * Recupera el diagrama de certificaciones 
	 * 
	 * @author: wilson.limachi 
	 * @Fecha:  27/09/2019
	 * @param   pDiagramaCertificacionId.	   		   
	 * @return  Devuelve SreDiagramasCertificaciones.
	 */
	public SreDiagramasCertificaciones actualizaDiagramaCertificacion(long pDiagramaCertificacionId, Long pUsuarioUltimaModificacionId);
}
