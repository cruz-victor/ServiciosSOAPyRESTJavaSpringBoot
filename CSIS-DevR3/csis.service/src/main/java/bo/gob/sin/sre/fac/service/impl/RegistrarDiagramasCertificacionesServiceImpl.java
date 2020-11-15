package bo.gob.sin.sre.fac.service.impl;

import java.lang.reflect.Type;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bo.gob.sin.sre.fac.domain.IArchivoTmpDomain;
import bo.gob.sin.sre.fac.domain.IRegistrarDiagramasCertificacionesDomain;
import bo.gob.sin.sre.fac.domain.IRegistrarHuellaSistemaDomain;
import bo.gob.sin.sre.fac.dto.DiagramasCertificacionesDto;
import bo.gob.sin.sre.fac.dto.ListaDiagramasCertificacionesDto;
import bo.gob.sin.sre.fac.dto.RespuestaOperacionDto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bo.gob.sin.sre.fac.model.SreArchivos;
import bo.gob.sin.sre.fac.model.SreDiagramasCertificaciones;
import bo.gob.sin.sre.fac.service.IRegistrarDiagramasCertificacionesService;
import bo.gob.sin.sre.fac.service.validation.ValidarDiagramaCertificacionServiceImpl;
import bo.gob.sin.str.cmsj.mapl.domain.IMensajeAplicacionDomain;
import bo.gob.sin.str.enumeradores.entorno.EnumSubsistema;
import bo.gob.sin.str.enumeradores.recaudaciones.EnumFacturacionTipoMensaje;

@Service
@Transactional
public class RegistrarDiagramasCertificacionesServiceImpl implements IRegistrarDiagramasCertificacionesService, Serializable{

	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(RegistroReCertificacionSistemasServiceImpl.class);
	
	@Autowired
	private IRegistrarDiagramasCertificacionesDomain iRegistrarDiagramasCertificacionesDomain;
	
	@Autowired
	private IMensajeAplicacionDomain mensajesDomain;
	
	@Autowired
	IRegistrarHuellaSistemaDomain iRegistroHuellaSistemaDomain;
	
	@Autowired
	IArchivoTmpDomain iArchivoTmpDomain;
	
	/** 
	 * Realiza el registro del diagrama de certificaciones 
	 * 
	 * @author: junior.flores 
	 * @Fecha: 23/09/2019
	 * @param  pSolicitud, Todos los datos correspondientes a Diagrama de Certificaciones 		   
	 * @return   Devuelve el objeto DiagramasCertificacionesDto con la respuesta de la transacci�n.
	 */
	@Override
	public DiagramasCertificacionesDto registraDiagramasCertificaciones(DiagramasCertificacionesDto pSolicitud) 
	{	

		DiagramasCertificacionesDto vRespuesta=new DiagramasCertificacionesDto(); 
		SreDiagramasCertificaciones diagramasCertificaciones=null;
		vRespuesta.setOk(false);

		try 
		{
			SreArchivos vResultadoArchivo = iRegistroHuellaSistemaDomain.registrarArchivos(pSolicitud.getArchivo());
			
			if(vResultadoArchivo != null)
			{
	
					ModelMapper vMapper = new ModelMapper();
					vMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
					diagramasCertificaciones = vMapper.map(pSolicitud, SreDiagramasCertificaciones.class);
					Date vFechaHoy = new Date();
					diagramasCertificaciones.setFechaRegistro(vFechaHoy);
					diagramasCertificaciones.setArchivoDiagramaId(vResultadoArchivo.getArchivoId());
					diagramasCertificaciones.setFechaUltimaModificacion(vFechaHoy);
					diagramasCertificaciones.setUsuarioUltimaModificacionId(pSolicitud.getUsuarioRegistroId());
					diagramasCertificaciones.setEstadoId("AC");
					diagramasCertificaciones = iRegistrarDiagramasCertificacionesDomain.registraDiagramasCertificaciones(diagramasCertificaciones);
								
					if (diagramasCertificaciones!=null && diagramasCertificaciones.getDiagramaCertificacionId()>0) {
						vRespuesta=vMapper.map(diagramasCertificaciones, DiagramasCertificacionesDto.class);
						vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,
								EnumFacturacionTipoMensaje.REGISTRO_ACEPTACION_RECHAZO_EXITO));
						vRespuesta.setOk(true);				
					} else {
		
						vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,
								EnumFacturacionTipoMensaje.REGISTRO_ACEPTACION_RECHAZO_ERROR));				
					}
		
					return vRespuesta;	
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			vRespuesta.setOk(false);
			vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES, EnumFacturacionTipoMensaje.REGISTRO_ACEPTACION_RECHAZO_ERROR));
		}
		
		return vRespuesta;
	}
	
	/** 
	 * Realiza la actualizacion del estado de diagrama de certificaciones 
	 * 
	 * @author: wilson.limachi 
	 * @Fecha: 27/09/2019
	 * @param  pDiagramaCertificacionId	   
	 * @return   Devuelve el objeto RespuestaOperacionDto.
	 */
	@Override
	public RespuestaOperacionDto actualizaDiagramasCertificaciones(Long pArchivoId, Long pDiagramaCertificacionId, Long pUsuarioRegistro) 
	{
		RespuestaOperacionDto vRespuesta = new RespuestaOperacionDto(); 
		SreDiagramasCertificaciones vResultadoArchivo=new SreDiagramasCertificaciones();
		vRespuesta.setOk(false);
		
		try 
		{			
			vResultadoArchivo = iRegistrarDiagramasCertificacionesDomain.actualizaDiagramaCertificacion(pDiagramaCertificacionId, pUsuarioRegistro);
			
			if(vResultadoArchivo != null)
			{		
				SreArchivos vSreArchivos = new SreArchivos() ;
				
				vSreArchivos = iArchivoTmpDomain.actualizarEstadoArchivo(pArchivoId);
				
				if(vSreArchivos!=null)
				{
					vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES, EnumFacturacionTipoMensaje.CONFIRMACION_EXITOSA));
					vRespuesta.setOk(true);
				}
				else
				{
					vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES, EnumFacturacionTipoMensaje.DATOS_SISTEMAS_ERROR));
					vRespuesta.setOk(false);
				}
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			vRespuesta.setOk(false);
			vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES, EnumFacturacionTipoMensaje.DATOS_SISTEMAS_ERROR));	
		}
		
		return vRespuesta;
	}
	
	/** 
	 * Realiza el listado de del diagrama de certificaciones 
	 * 
	 * @author: junior.flores 
	 * @Fecha: 24/09/2019
	 * @param  pSolicitud, Todos los datos para obtener el listado de Diagramas de Certificaciones   		   
	 * @return   Devuelve el objeto ListaDiagramasCertificacionesDto.
	 */
	@Override
	public ListaDiagramasCertificacionesDto recuperaListaDiagramasCertificaciones(ListaDiagramasCertificacionesDto pSolicitud) {	

		ListaDiagramasCertificacionesDto vRespuesta=new ListaDiagramasCertificacionesDto();
		vRespuesta.setOk(false);

		try {
			ValidarDiagramaCertificacionServiceImpl vValCertificacion = new ValidarDiagramaCertificacionServiceImpl(mensajesDomain);
			vValCertificacion.validarDiagramaCertificacion(pSolicitud);
			if (vValCertificacion.isValido()) 
			{
				List<SreDiagramasCertificaciones> listDiagramasCertificaciones = iRegistrarDiagramasCertificacionesDomain.recuperaListaDiagramasCertificaciones(pSolicitud.getDatosEntradaEtapaId(), pSolicitud.getDatosEntradaSistemaId(), pSolicitud.getDatosEntradaSolicitudCertificacionId());			
				if (listDiagramasCertificaciones!=null) 
				{				
					Type listType = new TypeToken<List<DiagramasCertificacionesDto>>() {}.getType();
					ModelMapper vMapper = new ModelMapper();
					vMapper.getConfiguration().setAmbiguityIgnored(true);
					vRespuesta.setListaDiagramasCertificacionesDto(vMapper.map(listDiagramasCertificaciones, listType));
					vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,
							EnumFacturacionTipoMensaje.CONFIRMACION_EXITOSA));
					vRespuesta.setOk(true);				
				} 
				else 
				{	
					vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES, EnumFacturacionTipoMensaje.DATOS_SISTEMAS_ERROR));				
				}
	
				return vRespuesta;
			} 
			else 
			{
				LOG.info("No se pudo registrar por datos incorrectos de envio.");
				vRespuesta.setMensajes(vValCertificacion.getMensajes());
			}
		} catch (Exception e) {
			e.printStackTrace();
			vRespuesta.setOk(false);
			vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,
					EnumFacturacionTipoMensaje.DATOS_SISTEMAS_ERROR));
		}
		return vRespuesta;
	}
}
