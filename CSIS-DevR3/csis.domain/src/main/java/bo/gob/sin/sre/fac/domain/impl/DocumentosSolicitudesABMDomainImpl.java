package bo.gob.sin.sre.fac.domain.impl;

import java.io.Serializable;
import java.util.Date;

import org.springframework.transaction.annotation.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bo.gob.sin.str.cexc.LogExcepcion;
import bo.gob.sin.str.cexc.MethodSign;
import bo.gob.sin.str.constantes.recaudaciones.ConstFacturacion;
import bo.gob.sin.sre.fac.dao.IDocumentosSolicitudesDao;
import bo.gob.sin.sre.fac.domain.IDocumentosSolicitudesABMDomain;
import bo.gob.sin.sre.fac.model.SreDocumentosSolicitudes;

@Service
@Transactional
public class DocumentosSolicitudesABMDomainImpl implements IDocumentosSolicitudesABMDomain, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(DocumentosSolicitudesABMDomainImpl.class);

	@Autowired
	IDocumentosSolicitudesDao iDocumentosSolicitudesDao;

	/**
	 * Registrar Documentos Solicitudes
	 * 
	 * @author: Carmen Rosa Silva
	 * @Fecha: 21/11/2018
	 * @param pUsuarioRegistroId, código de identificacion del usuario
	 * @param pSolicitudCertificacionId, Número de id de solicitud certificacion
	 * @param pTramiteId, Número de Identificacion del tramite
	 * @param pCite, Cadena que identifica el CITE
	 * @param pCodigoDocumentoId, Número de codigo documento id
	 * @return Devuelve SreDocumentosSolicitudes.
	 */
	@Override
	public boolean registrarDocumentoSolicitud(long pUsuarioRegistroId, long pSolicitudCertificacionId, long pTramiteId, long pCite, String pCodigoDocumentoId) {
		LOG.info("Ingresando registrarDocumentoSolicitud ");
		SreDocumentosSolicitudes vRegistro = new SreDocumentosSolicitudes();
		boolean vRespuesta = false;
		java.util.Date vFechaHoy = new Date();
		try {
			vRegistro.setUsuarioRegistroId(pUsuarioRegistroId);
			vRegistro.setUsuarioUltimaModificacionId(pUsuarioRegistroId);
			vRegistro.setSolicitudCertificacionId(pSolicitudCertificacionId);
			vRegistro.setTramiteId(pTramiteId);
			vRegistro.setCiteId(pCite);
			vRegistro.setCodigoDocumentoId(pCodigoDocumentoId);
			vRegistro.setFechaRegistro(vFechaHoy);
			vRegistro.setFechaUltimaModificacion(vFechaHoy);
			vRegistro.setEstadoId(ConstFacturacion.ESTADO_ACTIVO);

			iDocumentosSolicitudesDao.save(vRegistro);
			
			vRespuesta = true;
			LOG.info("Datos de documentos solicitud registrados.");

		} catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(vRegistro));
			LOG.info("Datos de documentos solicitud no registrado.");
		}
		LOG.info("Saliendo de registrar documentos solicitudes: " + vRegistro);
		return vRespuesta;
	}
}
