package bo.gob.sin.sre.fac.dao;



import java.util.List;

import bo.gob.sin.sre.fac.model.SreSistemasDeCertificacion;
import bo.gob.sin.str.ccs.dao.GenericDao;

public interface ISistemasDeCertificacionDao extends GenericDao<SreSistemasDeCertificacion>{
	

	public List<SreSistemasDeCertificacion> recuperarSistemasCertificadosPorProveedor(long contribuyenteId);
	
	/**
	 * Recuperar Sistema En Proceso por Contribuyente
	 * 
	 * @author: Fabio Ramos
	 * @Fecha: 27/04/2018
	 * @modificadoPor: Carmen Rosa Silva
	 * @FechaModificacion: 18/06/2018
	 * @param pContribuyenteId, número de id identificador en Padron
	 * @return   Devuelve una lista del tipo SreSistemasCertificacion.
	 */
	public List<SreSistemasDeCertificacion> recuperarSistemasEnProcesoPorContribuyente(Long contribuyenteId);
	

	/**
	 * Recuperar Lista Solicitud Certificación por pSistemaId
	 * 
	 * @author: Carmen Rosa Silva
	 * @Fecha: 06/07/2018
	 * @param pSistemaId, número de identificación de sistema
	 * @return   Devuelve lista de objetos respuesta SreSolicitudCertificacion
	 */
	public List<SreSistemasDeCertificacion> recuperarSistemasCertificadosAsociadosNit(Long contribuyenteId);
}
