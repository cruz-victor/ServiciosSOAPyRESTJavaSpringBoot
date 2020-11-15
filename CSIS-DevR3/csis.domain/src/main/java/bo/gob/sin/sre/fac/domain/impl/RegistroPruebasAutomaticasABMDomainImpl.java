package bo.gob.sin.sre.fac.domain.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bo.gob.sin.str.cexc.LogExcepcion;
import bo.gob.sin.str.cexc.MethodSign;
import bo.gob.sin.sre.fac.dao.IRegistroPruebasAutomaticasDao;
import bo.gob.sin.sre.fac.domain.IRegistroPruebasAutomaticasABMDomain;
import bo.gob.sin.sre.fac.model.SreFacRegistrosPruebasAutomaticas;
import bo.gob.sin.sre.fac.model.SrePruebasAutomaticas;
import bo.gob.sin.sre.fac.model.SreSistemas;
import bo.gob.sin.str.constantes.recaudaciones.ConstFacturacion;

@Service
@Transactional
public class RegistroPruebasAutomaticasABMDomainImpl implements IRegistroPruebasAutomaticasABMDomain, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(RegistroPruebasAutomaticasABMDomainImpl.class);
	
	@Autowired
	IRegistroPruebasAutomaticasDao iRegistroPruebasAutomaticasDao;
	
	//IASC - Registro de las pruebas automaticas de la solicitud de certificacion - 28/11/2018
	@Override
	public boolean registrarPruebasAutomaticas(SreSistemas pSistema, long pSolicitudCertificacionId, List<SrePruebasAutomaticas> pPruebas) {
		boolean vRespuesta = false;
		try {
			for (SrePruebasAutomaticas vRegistro : pPruebas) {
				SreFacRegistrosPruebasAutomaticas vDatos = new SreFacRegistrosPruebasAutomaticas();
				vDatos.setUsuarioRegistroId(pSistema.getUsuarioRegistroId());
				vDatos.setUsuarioUltimaModficacionId(pSistema.getUsuarioUltimaModificacionId());
				vDatos.setSolicitudCertificacionId(pSolicitudCertificacionId);
				vDatos.setTramiteId(pSistema.getTramiteId());
				vDatos.setPruebaAutomaticaId(vRegistro.getPruebaAutomaticaId());
				vDatos.setSistemaId(pSistema.getSistemaId());
				vDatos.setEstadoPruebaId(ConstFacturacion.ESTADO_PRUEBA_PENDIENTE);
				vDatos.setFechaInicio(pSistema.getFechaRegistro());
				vDatos.setFechaRegistro(pSistema.getFechaRegistro());
				vDatos.setFechaUltimaModificacion(pSistema.getFechaUltimaModificacion());
				vDatos.setEstadoId(ConstFacturacion.ESTADO_ACTIVO);
				iRegistroPruebasAutomaticasDao.save(vDatos);

				LOG.info("Datos pruebas automaticas registrados.");
			}
			vRespuesta = true;
		}catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(vRespuesta));
			LOG.info("Datos pruebas automaticas no registrados.");
		}
		return vRespuesta;
	}

}
