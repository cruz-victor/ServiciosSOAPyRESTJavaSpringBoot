package bo.gob.sin.sre.fac.domain.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bo.gob.sin.str.cexc.LogExcepcion;
import bo.gob.sin.str.cexc.MethodSign;
import bo.gob.sin.sre.fac.dao.IPruebasSistemasDao;
import bo.gob.sin.sre.fac.dto.PruebasDto;
import bo.gob.sin.str.constantes.recaudaciones.ConstFacturacion;
import bo.gob.sin.sre.fac.domain.IRegistrarPruebasSistemasDomain;
import bo.gob.sin.sre.fac.model.SrePruebasSistemas;
import bo.gob.sin.sre.fac.model.SreSistemas;

@Service
@Transactional
public class RegistrarPruebasSistemasDomainImpl implements IRegistrarPruebasSistemasDomain, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(RegistrarPruebasSistemasDomainImpl.class);
	
	@Autowired
	IPruebasSistemasDao iPruebasSistemasDao;
	
	/**4
     * Objetivo: registrar solicitud de sistemas.
     * Creado por: Reynaldo Chambi.
     * Fecha: 03/04/2018
     * Modificado por: Gualberto Condori
     * Fecha de Modificacion: 03/07/2018
     */
	@Override
	public boolean registrarPruebasSistemas(SreSistemas pSistemaACertificar, Long pSolicitudCertificacionId,  List<PruebasDto> pResultadoListaPruebas) {
		boolean vRespuesta = false;
		Date vFecha = new Date();
		try {
			for (PruebasDto vRegistroprueba : pResultadoListaPruebas) {
				SrePruebasSistemas vRegistroPruebasSistemas = new SrePruebasSistemas();
				vRegistroPruebasSistemas.setEstadoId(ConstFacturacion.ESTADO_ACTIVO);
				vRegistroPruebasSistemas.setEstadoPruebaId(ConstFacturacion.ESTADO_PRUEBA_PENDIENTE);
				vRegistroPruebasSistemas.setFechaInicio(vFecha);
				vRegistroPruebasSistemas.setPruebaId(vRegistroprueba.getpruebaId());
				vRegistroPruebasSistemas.setSolicitudCertificacionId(pSolicitudCertificacionId);
				vRegistroPruebasSistemas.setSistemaId(pSistemaACertificar.getSistemaId());
				vRegistroPruebasSistemas.setFechaRegistro(pSistemaACertificar.getFechaRegistro());
				vRegistroPruebasSistemas.setFechaUltimaModificacion(pSistemaACertificar.getFechaUltimaModificacion());
				vRegistroPruebasSistemas.setTramiteId(pSistemaACertificar.getTramiteId());
				vRegistroPruebasSistemas.setUsuarioRegistroId(pSistemaACertificar.getUsuarioRegistroId());
				vRegistroPruebasSistemas.setUsuarioUltimaModificacionId(pSistemaACertificar.getUsuarioRegistroId());
				
				iPruebasSistemasDao.save(vRegistroPruebasSistemas);
				LOG.info("Datos pruebas sistema registrados.");
			}
			vRespuesta = true;
		}catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(vRespuesta));
			LOG.info("Datos pruebas sistema no registrados.");
		}
		return vRespuesta;
	}
}
