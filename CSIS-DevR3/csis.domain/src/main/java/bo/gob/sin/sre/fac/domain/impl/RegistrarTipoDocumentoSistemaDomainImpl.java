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
import bo.gob.sin.sre.fac.dao.ITiposDocumentosSistemasDao;
import bo.gob.sin.sre.fac.domain.IRegistrarTipoDocumentoSistemaDomain;
import bo.gob.sin.sre.fac.model.SreSistemas;
import bo.gob.sin.sre.fac.model.SreTiposDocumentosSistemas;

@Service
@Transactional(rollbackFor = Exception.class)

public class RegistrarTipoDocumentoSistemaDomainImpl implements IRegistrarTipoDocumentoSistemaDomain, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(RegistrarTipoDocumentoSistemaDomainImpl.class);
	
	@Autowired
	private ITiposDocumentosSistemasDao iTiposDocumentosSistemasDao;

	//IASC - Registro de el tipo de documento fiscal la solicitud de certificación - 06/11/2018
	@Override
	public boolean registrarTipoDocumentoSistema(SreSistemas pSistema, Long pSolicitudCertificacionId, List<Integer> pSolicitud) {
		
		boolean vRespuesta = false;
		try {
			for (Integer vRegistro : pSolicitud) {
				SreTiposDocumentosSistemas vDatos = new SreTiposDocumentosSistemas();
				vDatos.setUsuarioRegistroId(pSistema.getUsuarioRegistroId());
				vDatos.setUsuarioUltimaModificacionId(pSistema.getUsuarioUltimaModificacionId());
				vDatos.setSistemaId(pSistema.getSistemaId());
				vDatos.setSolicitudCertificacionId(pSolicitudCertificacionId);
				vDatos.setTipoDocumentoFacturaId(vRegistro);
				vDatos.setEstadoTipoDocumentoSistemaId(ConstFacturacion.ESTADO_TIPO_DOCUMENTO_SISTEMA_VALIDO);
				vDatos.setFechaRegistro(pSistema.getFechaRegistro());
				vDatos.setFechaUltimaModificacion(pSistema.getFechaUltimaModificacion());
				vDatos.setEstadoId(ConstFacturacion.ESTADO_ACTIVO);
				iTiposDocumentosSistemasDao.save(vDatos);

				LOG.info("Datos documentos registrados.");
			}
			vRespuesta = true;
		}catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(vRespuesta));
			LOG.info("Datos documentos sistema no registrados.");
		}
		return vRespuesta;
	}
}
