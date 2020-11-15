package bo.gob.sin.sre.fac.domain.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bo.gob.sin.str.cexc.LogExcepcion;
import bo.gob.sin.str.cexc.MethodSign;
import bo.gob.sin.sre.fac.dao.IContactosCertificacionesDao;
import bo.gob.sin.sre.fac.domain.IContactosCertificacionesConsultaDomain;
import bo.gob.sin.sre.fac.model.SreContactosCertificaciones;

@Service
@Transactional
public class ContactosCertificacionesConsultaDomainImpl implements IContactosCertificacionesConsultaDomain, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(ContactosCertificacionesConsultaDomainImpl.class);

	@Autowired
	IContactosCertificacionesDao iContactosCertificacionesDao;
	
	//IASC - Obtiene la lista de los contactos de la solicitud - 23/11/2018 
	@Override
	public List<SreContactosCertificaciones> obtieneListaContactosSolicitud(Long pSolicitudCertificacionId) {
		List<SreContactosCertificaciones> respuestaRegistro = new ArrayList<>();
		LOG.info("ingresando obtieneListaContactosSolicitud");
		try {
			respuestaRegistro = iContactosCertificacionesDao.obtieneListaContactosSolicitud(pSolicitudCertificacionId);
		} catch (Exception e) {
			LOG.info("No se pudo recuperar la lista de contactos, existe excepciones");
			LogExcepcion.registrar(e, LOG, MethodSign.build(e));
		}
		LOG.info("Saliendo de obtieneListaContactosSolicitud", respuestaRegistro);
		return respuestaRegistro;
	}
	
}
