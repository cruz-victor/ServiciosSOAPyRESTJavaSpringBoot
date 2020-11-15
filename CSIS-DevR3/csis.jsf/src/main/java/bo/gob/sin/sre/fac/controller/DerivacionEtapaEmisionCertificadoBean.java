package bo.gob.sin.sre.fac.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bo.gob.sin.sap.cder.componentes.controller.AbstractDerivacionController;
import bo.gob.sin.sap.cead.componentes.controller.ScrDtoDocumentoEmitido;
import bo.gob.sin.sap.cead.componentes.controller.StmDtoDocumentoAdjunto;
import bo.gob.sin.sap.cead.componentes.controller.StmParametros;
import bo.gob.sin.sre.fac.dto.RespuestaActualizacionGenericoDto;
import bo.gob.sin.sre.fac.dto.SolicitudSolicitudCertificacionDto;
import bo.gob.sin.sre.fac.model.ContextoUsuarioModel;
import bo.gob.sin.sre.fac.service.ServiciosFacturacionRest;

@ViewScoped
@ManagedBean(name = "derivacionEtapaCertificadoBean")
public class DerivacionEtapaEmisionCertificadoBean extends AbstractDerivacionController implements Serializable {
	private static final Logger LOG = LoggerFactory.getLogger(DerivacionEtapaEmisionCertificadoBean.class);

	private static final long serialVersionUID = 1L;
	
	static final long PLANTILLA_ID_CERTIFICACION = 1317L;
	static final long PLANTILLA_ID_CERTIFICACION_RECHAZO = 1367L;

	@ManagedProperty(value = "#{contextoUsuarioModel}")
	ContextoUsuarioModel contextoUsuarioModel;
	
	@ManagedProperty(value = "#{emisionDocumentoAdjuntoNotificacionController}")
	private EmisionCertificadoDocumentoAdjuntoNotificacionController notificacion;
	
	@ManagedProperty(value = "#{tramiteCertificacionBean}")
	private TramiteCertificacionBean tramiteCertificacionBean;

	@PostConstruct
	public void init() {
		super.init();
		this.tramiteCertificacionBean.setearContexto();
	}
	
	public DerivacionEtapaEmisionCertificadoBean() {
		filtrarUsuariosOficina(true);
	}
	
	@Override
	public boolean esValidaDerivacion() {
		return true;
	}

	@Override
	public boolean esValidaFinalizacion() {
		boolean respuesta = false;
		long plantillaId=0;
		for (StmDtoDocumentoAdjunto dto : this.notificacion.getListaDocumentosAdjuntosNotificacion().getListaDocumentosAdjuntos()) {
			if (dto.getEstadoDocumentoAdjuntoId()==StmParametros.ESTADO_DOCUMENTO_ADJUNTO_NOTIFICADO) {
				ScrDtoDocumentoEmitido documentoEmitido = this.notificacion.getListaDocumentosAdjuntosNotificacion().obtenerDocumentoEmitidoPorId(dto.getDocumentoEmitidoId());
				if (documentoEmitido.getDocumentoEmitidoId()>0 && (documentoEmitido.getPlantillaId()==PLANTILLA_ID_CERTIFICACION || documentoEmitido.getPlantillaId()==PLANTILLA_ID_CERTIFICACION_RECHAZO)) {
					plantillaId=documentoEmitido.getPlantillaId();
					respuesta=true;
					break;
				}
			}
		}
		
		if (!respuesta) {
			this.setMensajeValidacionFinalizar("Debe notificar el certificado de aprobación  ó  certificado de rechazo"); 
		}else {
			SolicitudSolicitudCertificacionDto vSolicitud = new SolicitudSolicitudCertificacionDto();
			vSolicitud.setSolicitudCertificacionId(tramiteCertificacionBean.getTramiteSolicitudCertificacion().getSolicitudCertificacionId());
			vSolicitud.setUsuarioId(contextoUsuarioModel.getUsuario().getUsuarioId());
			 RespuestaActualizacionGenericoDto vRespuesta = (plantillaId==PLANTILLA_ID_CERTIFICACION) ? new ServiciosFacturacionRest().aprobarSolicitudCertificacion(vSolicitud) : new ServiciosFacturacionRest().cancelarSolicitudCertificacion(vSolicitud);
			if (!vRespuesta.isOk()) 
			{
				respuesta=false;
				this.setMensajeValidacionFinalizar("Ocurrio un error al actualizar el estado de la solicitud"+ ((vRespuesta.getMensajes()!=null && !vRespuesta.getMensajes().isEmpty()) ? ", "+vRespuesta.getMensajes().get(0).getDescripcion(): ""));
			}	
		}
		
		return respuesta;
	}

	public EmisionCertificadoDocumentoAdjuntoNotificacionController getNotificacion() {
		return notificacion;
	}

	public void setNotificacion(EmisionCertificadoDocumentoAdjuntoNotificacionController notificacion) {
		this.notificacion = notificacion;
	}

	public ContextoUsuarioModel getContextoUsuarioModel() {
		return contextoUsuarioModel;
	}

	public void setContextoUsuarioModel(ContextoUsuarioModel contextoUsuarioModel) {
		this.contextoUsuarioModel = contextoUsuarioModel;
	}

	public TramiteCertificacionBean getTramiteCertificacionBean() {
		return tramiteCertificacionBean;
	}

	public void setTramiteCertificacionBean(TramiteCertificacionBean tramiteCertificacionBean) {
		this.tramiteCertificacionBean = tramiteCertificacionBean;
	}
	
}
