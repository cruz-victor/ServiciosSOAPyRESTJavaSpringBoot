package bo.gob.sin.sre.fac.domain.impl;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bo.gob.sin.str.cexc.LogExcepcion;
import bo.gob.sin.str.cexc.MethodSign;
import bo.gob.sin.str.constantes.recaudaciones.ConstFacturacion;
import bo.gob.sin.sre.fac.dao.IContactosCertificacionesDao;
import bo.gob.sin.sre.fac.domain.IRegistrarContactoCertificacionDomain;
import bo.gob.sin.sre.fac.dto.SolicitudContactosCertificacionesDto;
import bo.gob.sin.sre.fac.model.SreContactosCertificaciones;
import bo.gob.sin.sre.fac.model.SreSolicitudCertificacion;

@Service
@Transactional(rollbackFor = Exception.class)

public class RegistrarContactoCertificacionDomainImpl implements IRegistrarContactoCertificacionDomain, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(RegistrarContactoCertificacionDomainImpl.class);
	
	@Autowired
	private IContactosCertificacionesDao iContactosCertificacionesDao;

	//IASC - Registro de las personas de contacto de la solicitud de certificación - 05/11/2018
	@Override
	public boolean registrarContactoCertificacion(SreSolicitudCertificacion pSolicitud, List<SolicitudContactosCertificacionesDto> plistaContactos) {
		boolean vRespuesta = false;
		try {
			for (SolicitudContactosCertificacionesDto vRegistro : plistaContactos) {
				SreContactosCertificaciones vDatos = new SreContactosCertificaciones();
				vDatos.setUsuarioRegistroId(pSolicitud.getUsuarioRegistroId());
				vDatos.setUsuarioUltimaModificacionId(pSolicitud.getUsuarioUltimaModficacionId());
				vDatos.setSolicitudCertificacionId(pSolicitud.getSolicitudCertificacionId());
				vDatos.setNombreCompleto(vRegistro.getNombre().toUpperCase());
				vDatos.setCelular(vRegistro.getCelular());
				vDatos.setCorreoElectronico(vRegistro.getCorreo());
				vDatos.setFechaRegistro(pSolicitud.getFechaRegistro());
				vDatos.setFechaUltimaModificacion(pSolicitud.getFechaUltimaModificacion());
				vDatos.setEstadoId(ConstFacturacion.ESTADO_ACTIVO);
				vDatos.setNumeroDocumento(vRegistro.getNumeroDocumento());
				vDatos.setComplemento(vRegistro.getComplemento());
				vDatos.setTipoDocumentoIdentidadId(vRegistro.getTipoDocumentoIdentidad());
				iContactosCertificacionesDao.save(vDatos);
				LOG.info("Datos contactos registrados.");
			}
			vRespuesta = true;
		}
		catch (Exception e) 
		{
			vRespuesta = false;
			LogExcepcion.registrar(e, LOG, MethodSign.build(vRespuesta));
			LOG.info("Datos pruebas sistema no registrados.");
		}
		return vRespuesta;
	}
}
