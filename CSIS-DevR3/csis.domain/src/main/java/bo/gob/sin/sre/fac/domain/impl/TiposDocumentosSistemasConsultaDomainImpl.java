package bo.gob.sin.sre.fac.domain.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bo.gob.sin.str.cexc.LogExcepcion;
import bo.gob.sin.str.cexc.MethodSign;
import bo.gob.sin.sre.fac.dao.ITiposDocumentosSistemasDao;
import bo.gob.sin.sre.fac.domain.ITiposDocumentosSistemasConsultaDomain;
import bo.gob.sin.sre.fac.model.SreSolicitudCertificacion;
import bo.gob.sin.sre.fac.model.SreTiposDocumentosSistemas;

@Service
@Transactional
public class TiposDocumentosSistemasConsultaDomainImpl implements ITiposDocumentosSistemasConsultaDomain, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(TiposDocumentosSistemasConsultaDomainImpl.class);

	@Autowired
	ITiposDocumentosSistemasDao iTiposDocumentosSistemasDao;
	
	//IASC - Obtiene la lista de los tipos de documentos fiscales del sistema - 27/11/2018 
	@Override
	public List<SreTiposDocumentosSistemas> obtieneDocumentosSectores(long pSistemaId, long pSolicitudCertificacionId) {
		List<SreTiposDocumentosSistemas> respuestaRegistro = new ArrayList<>();
		LOG.info("ingresando obtieneDocumentosSectores");
		try {
			respuestaRegistro = iTiposDocumentosSistemasDao.obtieneDocumentosSectores(pSistemaId, pSolicitudCertificacionId);
		} catch (Exception e) {
			LOG.info("No se pudo recuperar datos, existe excepciones");
			LogExcepcion.registrar(e, LOG, MethodSign.build(e));
		}
		LOG.info("Saliendo de obtieneDocumentosSectores", respuestaRegistro);
		return respuestaRegistro;
	}
	
	/**
	 * Cambiar estado en Tipos documentos sistemas
	 * 
	 * @author: Wilson Limachi
	 * @Fecha: 18/07/2019
	 * @param pSolicitudCertificacionId, id de la tabla solicitud certificacion
	 * @param pUsuario, número de identificacion del usuario
	 * @param pEstadoId, nuevo estado de solicitud certificacion
	 * @return Devuelve objeto SreTiposDocumentosSistemas.
	 */

	@Override
	public SreTiposDocumentosSistemas modificarTipoDocumentosSistemas(Long pTipoDocumentoSistemaId, Long pUsuario, Integer pEstadoId) 
	{
		SreTiposDocumentosSistemas vResultado = new SreTiposDocumentosSistemas();
		java.util.Date vFecha = new Date();
		try 
		{
			vResultado = iTiposDocumentosSistemasDao.findById(pTipoDocumentoSistemaId);
			vResultado.setFechaUltimaModificacion(vFecha);
			vResultado.setUsuarioUltimaModificacionId(pUsuario);
			vResultado.setEstadoTipoDocumentoSistemaId(pEstadoId);
	
			iTiposDocumentosSistemasDao.saveOrUpdate(vResultado);			
		} 
		catch (Exception e) 
		{
			LOG.info("No se pudo actualizar");
			LogExcepcion.registrar(e, LOG, MethodSign.build(e));
			return null;
		}
		
		return vResultado;
	}
}