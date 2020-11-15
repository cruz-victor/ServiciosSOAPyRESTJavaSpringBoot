package bo.gob.sin.sre.fac.service.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import bo.gob.sin.sre.fac.domain.IRegistroVerificacionPruebaInsituDomain;
import bo.gob.sin.sre.fac.dto.RespuestaRegistroVerificacionInsituDto;
import bo.gob.sin.sre.fac.dto.SolicitudRegistroVerificacionInsituDto;
import bo.gob.sin.sre.fac.service.IRegistroVerificacionPruebasInsituService;
import bo.gob.sin.str.cmsj.mapl.domain.IMensajeAplicacionDomain;
import bo.gob.sin.str.enumeradores.entorno.EnumSubsistema;
import bo.gob.sin.str.enumeradores.recaudaciones.EnumFacturacionTipoMensaje;

@Service
public class RegistroVerificacionPruebasInsituServiceImpl implements IRegistroVerificacionPruebasInsituService, Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private IMensajeAplicacionDomain mensajesDomain;
	
	@Autowired
	private IRegistroVerificacionPruebaInsituDomain iRegistroVerificacionPruebaInsituDomain;

	@Override
	public RespuestaRegistroVerificacionInsituDto guardar(
			SolicitudRegistroVerificacionInsituDto pSolicitud) {
		RespuestaRegistroVerificacionInsituDto vRespuesta = new RespuestaRegistroVerificacionInsituDto();
		try {
			Long vRegistroId = iRegistroVerificacionPruebaInsituDomain.guardar(pSolicitud.getRegistro());
			vRespuesta.setOk(true);
			vRespuesta.setRegistroObservacionInsituId(vRegistroId);
			vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES, EnumFacturacionTipoMensaje.REGISTRO_SOLICITUD_PROCESO_MASIVO_EXITO));
		} catch (Exception e) {
			vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES, EnumFacturacionTipoMensaje.REGISTRO_SOLICITUD_PROCESO_MASIVO_ERROR));
			TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
		}
		
		return vRespuesta;
	}

}
