package bo.gob.sin.sre.fac.domain;


import java.util.List;

import bo.gob.sin.sau.ausu.dto.RespuestaServicioDto;
import bo.gob.sin.sre.fac.dto.ConsultaSistemasCertificadosDto;
import bo.gob.sin.sre.fac.dto.DatosSistemasDto;
import bo.gob.sin.sre.fac.dto.GeneraCuisDto;
import bo.gob.sin.sre.fac.dto.ListaSistemasDto;
import bo.gob.sin.sre.fac.dto.RegistroAutorizacionRechazoCertificacionDto;
import bo.gob.sin.sre.fac.dto.RespuestaActualizacionDto;
import bo.gob.sin.sre.fac.dto.RespuestaPruebasSistemasDeCertificacionDto;
import bo.gob.sin.sre.fac.dto.SistemasCertificadosContribuyenteDto;
import bo.gob.sin.sre.fac.dto.SolicitudActualizaSolicitudCertificacionDto;
import bo.gob.sin.sre.fac.dto.SolicitudPruebasSistemas;
import bo.gob.sin.sre.fac.dto.SolicitudPruebasSistemasDeCertificacionDto;
import bo.gob.sin.sre.fac.dto.SolicitudRegistrarSistemaACertificarDto;
import bo.gob.sin.sre.fac.dto.SolicitudRegistrarSolicitudACertificarDto;
import bo.gob.sin.sre.fac.model.SrePruebasSistemas;
import bo.gob.sin.sre.fac.model.SreRegistrarSolicitudCertificacion;
import bo.gob.sin.sre.fac.model.SreRegistrosResultadosSolicitudes;
import bo.gob.sin.sre.fac.model.SreSistemas;
import bo.gob.sin.sre.fac.model.SreSistemasContribuyentes;
import bo.gob.sin.sre.fac.model.SreSistemasDeCertificacion;
import bo.gob.sin.sre.fac.model.SreSolicitudCertificacion;


public interface ISolicitudCertificacionDomain {
	/**
	 * Recuperar la solicitud de certificacion
	 * @param paremetro pSolicitudCertificacionId identificador de registro
	 * @return Entidad de la solicitud de certificacion
	 */
	/**
     * Objetivo: recuperar solicitudes de certificacion por solicitudCertificacionId.
     * Creado por: Reynaldo Chambi.
     * Fecha: 03/04/2018
     * Modificado por: Gualberto Condori
     * Fecha de Modificacion: 19/06/2018
     */
	SreSolicitudCertificacion recuperaSolicitudesCertificacion(Long pSolicitudCertificacionId);
	
		
	
	
	/**
	 * Activa  la Solicitud de Certificacion del Sistema
	 * @param paremetro pSolicitudCertificacionId identificador de registro
	 * @return resultado de la actualizacion true o false 
	 */
	RespuestaActualizacionDto activarSolicitudCertificacionSistema(Long pSolicitudCertificacionId);
	
	/**
	 * Cancela la Solicitud de Certificacion del Sistema
	 * @param paremetro pSolicitudCertificacionId identificador de registro
	 * @return resultado de la actualizacion true o false 
	 */
	RespuestaActualizacionDto cancelarSolicitudCertificacionSistema(Long pSolicitudCertificacionId);
	
	/**
	 * Autoriza la Solicitud de Certificacion del Sistema
	 * @param paremetro pSolicitudCertificacionId identificador de registro
	 * @return resultado de la actualizacion true o false 
	 */
	RespuestaActualizacionDto autorizarSolicitudCertificacionSistema(Long pSolicitudCertificacionId);
	//public SreSolicitudCertificacion autorizarSolicitudCertificacionSistema(Long pSolicitudCertificacionId);
	
	/**
	 * Rechaza la Solicitud de Certificacion del Sistema
	 * @param paremetro pSolicitudCertificacionId identificador de registro
	 * @return resultado de la actualizacion true o false 
	 */
	RespuestaActualizacionDto rechazarSolicitudCertificacionSistema(Long pSolicitudCertificacionId);

	/**
	 * Registra los datos de la solicitud de Certificacion del Sistema
	 * @param paremetros de la Solicitud de Certificacion de Sistemas
	 * @return resultado del registro de solicitud del sistema TRUE o FALSE 
	 */
//	RespuestaRegistrarSolicitudCertificacionDto registrarSolicitudSistema(SolicitudRegistrarSistemaACertificarDto pSolicitudSistema, SolicitudRegistrarSolicitudACertificarDto pSolicitudCertificacion, List<Integer> pTiposPrueba, List<Integer> pTiposModulos);

	
	//public long registrarSolicitudSistema1(SolicitudRegistrarSistemaACertificarDto pSolicitudSistema, SolicitudRegistrarSolicitudACertificarDto pSolicitudCertificacion, List<Integer> pTiposPrueba, List<Integer> pTiposModulos);

	//CGA 04/07/2018
	public String generarCodigoCuis(SreSistemas pSistemaACertificar, Long pNit, Integer pSucursal, Long pContribuyenteId, Long pSistemaContribuyenteId);
	
	
		//FRA
	RespuestaServicioDto generarCuis(GeneraCuisDto solicitud);

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
	public List<SreSistemasDeCertificacion> recuperarSistemasEnProcesoPorContribuyente(Long pContribuyenteId);
	//FRA
	ConsultaSistemasCertificadosDto recuperarSistemasCertificadosPorProveedor(SistemasCertificadosContribuyenteDto solicitud);
	// RCR
	RespuestaActualizacionDto actualizarPruebasSistemasDeCertificacion(SolicitudPruebasSistemas solicitud);
	//RespuestaActualizacionDto actualizarSolicitudCertificacion(SolicitudRegistrarSistemasDto solicitud);
	ListaSistemasDto listarSistemasPorIdContribuyente(SolicitudActualizaSolicitudCertificacionDto pSolicitud);
	
	
	//GCA
	public ListaSistemasDto listarSistemasEstadoCertificacion(SolicitudActualizaSolicitudCertificacionDto pSolicitud);


	//IASC
	public DatosSistemasDto recuperarSistemaParaRegistroDeHuella(Long pSistemaId);
	//public SreSistemas recuperarSistemaParaRegistroDeHuella(Long pSistemaId);
	//IASC
	public RegistroAutorizacionRechazoCertificacionDto registrarAprobacionRechazoCertificacion(RegistroAutorizacionRechazoCertificacionDto pSolicitud);
// modificado GCA
	public SreRegistrosResultadosSolicitudes registrarAprobacionRechazoCertificacion(SreRegistrosResultadosSolicitudes pSolicitud);
		
	
	/**
	 * Obtener Lista de Datos de Pruebas de Sistemas de Certificacion
	 * 
	 * @author: Carmen Rosa Silva
	 * @Fecha: 19/06/2018
	 * @param pSistemaId, número identificación de sistema
	 * @param pSolicitudCertificacionId, número identificación de solicitud certificación
	 * @return   Devuelve una lista de objetos SrePruebasSistemas
	 */
	public List<SrePruebasSistemas> obtenerPruebasSistemasDeCertificacion(Long pSistemaId, Long pSolicitudCertificacionId);


	public RespuestaPruebasSistemasDeCertificacionDto recuperarListaPruebasSistemasDeCertificacion(
			SolicitudPruebasSistemasDeCertificacionDto pSolicitud);

	/**
	 * Recuperar Lista Solicitud Certificación por pContribuyenteId y oficinaId
	 * 
	 * @author: Carmen Rosa Silva
	 * @Fecha: 20/06/2018
	 * @param pContribuyenteId, número de identificación del contribuyente
	 * @param pOficinaId, número de identificación de oficina
	 * @return   Devuelve objeto respuesta SreSolicitudCertificacion
	 */
	public List<SreSolicitudCertificacion> recuperaSolicitudesCertificacionContribuyenteOficina(Long pContribuyenteId,
			Integer pOficinaId);
	
	/**
	 * Recuperar cuis
	 * 
	 * @author: Carmen Rosa Silva
	 * @Fecha: 27/06/2018
	 * @param pSistemaId, número de identificación del sistema
	 * @param pContribuyenteId, número de identificación del contribuyente
	 * @param pTipoSolicitudId, número de identificación del tipo solicitud
	 * @return   Devuelve el cuis
	 */
	public String obtenerCuis(Long pSolicitudCertificacionId);
	
	public SreSolicitudCertificacion actualizarSolicitudSistemaCertificado(Long pSolicitudCertificacionId);
		
/**
     * Objetivo: registrar solicitud de sistemas.
     * Creado por: Reynaldo Chambi.
     * Fecha: 03/04/2018
     * Modificado por: Gualberto Condori
     * Fecha de Modificacion: 03/07/2018
     */
	public SreRegistrarSolicitudCertificacion registrarSistemaACertificar(SolicitudRegistrarSistemaACertificarDto pSolicitud);
	/**
     * Objetivo: registrar solicitud de sistemas.
     * Creado por: Reynaldo Chambi.
     * Fecha: 03/04/2018
     * Modificado por: Gualberto Condori
     * Fecha de Modificacion: 03/07/2018
     */
	public SreSistemasContribuyentes registrarSistemasContribuyentes(SreSistemas pSistemaACertificar, SolicitudRegistrarSolicitudACertificarDto pSolicitud);	
	/**
     * Objetivo: registrar solicitud de sistemas.
     * Creado por: Reynaldo Chambi.
     * Fecha: 03/04/2018
     * Modificado por: Gualberto Condori
     * Fecha de Modificacion: 03/07/2018
     */
	public SreSolicitudCertificacion registrarSolicitudACertificar(SreSistemas pSistemaACertificar, SolicitudRegistrarSolicitudACertificarDto pSolicitud, Long pSistemaContribuyenteId);
	/**
     * Objetivo: registrar solicitud de sistemas.
     * Creado por: Reynaldo Chambi.
     * Fecha: 03/04/2018
     * Modificado por: Gualberto Condori
     * Fecha de Modificacion: 03/07/2018
     */
	public SreRegistrarSolicitudCertificacion registrarPruebasSistemas(SreSistemas pSistemaACertificar, SreSolicitudCertificacion pRegistroSolicitud, SolicitudPruebasSistemas pSolicitud, List<Integer> pTiposPrueba);
	
	/**
     * Objetivo: registrar solicitud de sistemas.
     * Creado por: Reynaldo Chambi.
     * Fecha: 03/04/2018
     * Modificado por: Gualberto Condori
     * Fecha de Modificacion: 03/07/2018
     */public SreRegistrarSolicitudCertificacion registraModulosSistemaACertificar(SreSistemas pSolicitudSistema, SreSolicitudCertificacion pSolicitudCertificacion, List<Integer> pTiposModulos);
			
	
     public SrePruebasSistemas actualizarPruebaSistemasDeCertificacion( SrePruebasSistemas pSolicitud);
     /**
		 * Recuperar numero de solicitudes certificacion de un sistema
		 * 
		 * @author: Carmen Rosa Silva
		 * @Fecha: 06/07/2018
		 * @modificadoPor: 
		 * @FechaModificacion: 
		 * @param pSistemaId, número de identificacion del Sistema
		 * @return   Devuelve contador.
		 */
	boolean recuperarSolicitudCertificacionDeSistema(Long pSistemaId);
	/**
	 * Recuperar Lista SolicitudCertificacion por contribuyenteId
	 * 
	 * @author: Carmen Rosa Silva
	 * @Fecha: 02/08/2018
	 * @param pContribuyenteId, número de identificación del contribuyente
	 * @return   Devuelve objeto respuesta SreSolicitudCertificacion
	 */
	public List<SreSolicitudCertificacion> recuperaSolicitudesCertificacionIdContribuyente(Long pContribuyenteId);

	/**
	 * Recuperar Lista SolicitudCertificacion por contribuyenteId y estado
	 * 
	 * @author: Carmen Rosa Silva
	 * @Fecha: 10/08/2018
	 * @param pContribuyenteId, número de identificación del contribuyente
	 * @param pEstadoSolicitudCertificacionId, número de identificación del estado de solicitud de certificacion
	 * @return   Devuelve objeto respuesta SreSolicitudCertificacion
	 */
	public List<SreSolicitudCertificacion> recuperaSolicitudesCertificacionIdContribuyenteEstado(Long pContribuyenteId,
			Integer pEstadoSolicitudCertificacionId);

	public RespuestaServicioDto generarCuisContribuyente(GeneraCuisDto solicitud);

	/**
	 * Registrar Verificacion In Situ
	 * @author: Carmen Rosa Silva
	 * @Fecha: 31/08/2018
	 * @param pSolicitudCertificacionId,número de identificacion solicitud certificacion id
	 * @param pEstadoSolicitudCertificacionId, estadi
	 * @return   Devuelve objeto SreSolicitudCertificacion.
	 */
	public boolean modificarEstadoSolicitudCertificacion(Long pSolicitudCertificacionId,
			Integer pEstadoSolicitudCertificacionId);
	
	/**
	 * Cambiar estado en Solicitud Certificacion 
	 * 
	 * @author: Ivan Salas
	 * @Fecha: 19/09/2018
	 * @modificadoPor: Carmen Rosa Silva
	 * @FechaModificacion: 15/11/2018
	 * @param pSolicitudCertificacionId, id de la tabla solicitud certificacion
	 * @param pUsuario, número de identificacion del usuario
	 * @param pEstadoId, nuevo estado de solicitud certificacion
	 * @return Devuelve objeto SreSolicitudCertificacion.
	 */
	public SreSolicitudCertificacion habilitarEstadoCertificacion(Long pSolicitudCertificacionId, Long pUsuario, Integer pEstadoId);
	
	/**
	 * Recuperar Lista de Solicitudes de Certificacion, con estado en PROCESO
	 * @author: Ivan Salas
	 * @Fecha: 21/09/2018
	 * @modificadoPor: Carmen Rosa Silva
	 * @FechaModificacion: 08/11/2018
	 * @param pContribuyenteId,número de identificacion del contribuyente
	 * @param pEstadoSolicitudCertificacionId, estado de certificacion
	 * @return   Devuelve objeto SreSolicitudCertificacion.
	 */
	public List<SreSolicitudCertificacion> recuperaSolicitudesCertificacionParaCancelar(Long pContribuyenteId, Integer pEstadoCertificacion);




	
}
