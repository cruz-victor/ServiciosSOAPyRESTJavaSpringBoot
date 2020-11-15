package bo.gob.sin.sre.fac.domain.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bo.gob.sin.str.cexc.LogExcepcion;
import bo.gob.sin.str.cexc.MethodSign;
import bo.gob.sin.sre.fac.dao.IConsultaEstadoCertificacionSistemasDao;
import bo.gob.sin.sre.fac.domain.IConsultaEstadoCertificacionSistemasDomain;
import bo.gob.sin.sre.fac.model.SreSolicitudCertificacion;

@Service
@Transactional
public class ConsultaEstadoCertificacionSistemasDomainImpl implements IConsultaEstadoCertificacionSistemasDomain, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(ConsultaEstadoCertificacionSistemasDomainImpl.class);
	
	@Autowired	
	IConsultaEstadoCertificacionSistemasDao iConsultaEstadoCertificacionSistemasDao;
	
	@Override	
	public List<SreSolicitudCertificacion> recuperaListaEstadoCertificacionSistemas(long pPersonaContribuyenteId)
	{
		LOG.info("Ingresando recuperaListaEstadoCertificacionSistemas pPersonaContribuyenteId={}",pPersonaContribuyenteId);
		
		List<SreSolicitudCertificacion> vResultado = new ArrayList<>();
		try {
			
				if(pPersonaContribuyenteId > 0)
				{
					vResultado =  iConsultaEstadoCertificacionSistemasDao.recuperaListaEstadoCertificacionSistemas(pPersonaContribuyenteId);
					
				}else {
					
					LOG.info("Nro. de NIT es negativo");
				}					
				
		}catch (Exception e) {
			vResultado = null;
			LogExcepcion.registrar(e, LOG, MethodSign.build(vResultado));		
		
	   }
		LOG.info("Saliendo de recuperaListaEstadoCertificacionSistemas={}", vResultado);		
		return vResultado;	
	
	}

}
