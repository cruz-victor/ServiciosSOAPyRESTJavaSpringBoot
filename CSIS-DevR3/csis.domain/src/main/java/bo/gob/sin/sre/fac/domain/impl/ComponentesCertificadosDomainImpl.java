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
import bo.gob.sin.str.constantes.recaudaciones.ConstFacturacion;
import bo.gob.sin.sre.fac.dao.IComponentesCertificadosDao;
import bo.gob.sin.sre.fac.dao.IComponentesCertificadosTmpDao;
import bo.gob.sin.sre.fac.domain.IComponentesCertificadosDomain;
import bo.gob.sin.sre.fac.domain.IComponentesCertificadosTmpDomain;
import bo.gob.sin.sre.fac.model.SreComponentesCertificados;
import bo.gob.sin.sre.fac.model.SreComponentesCertificadosTmp;

@Service
@Transactional
public class ComponentesCertificadosDomainImpl implements IComponentesCertificadosDomain, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(ComponentesCertificadosDomainImpl.class);
	
	@Autowired
	IComponentesCertificadosDao iComponentesCertificadosDao;

	/**
	 * Listado de los componentes de archivos de sistema.  
	 * 
	 * @author wilson.limachi
	 * @Fecha: 18/09/2019
	 * @param pSistemaId, Id de la entidad sistema.
	 * @return lista de componentes certificados.
	 */
	@Override
	public List<SreComponentesCertificados> obtenerComponentesCertificados(Long pComponenteArchivoId)
	{
		List<SreComponentesCertificados> vListaComponenteCertificado = new ArrayList<>(); 
		LOG.info("ingresando a obtenerComponentesCertificados");
		try {	
			vListaComponenteCertificado = iComponentesCertificadosDao.obtenerComponentesCertificados(pComponenteArchivoId);
		
		} 
		catch (Exception e) 
		{
			vListaComponenteCertificado = null;
			LogExcepcion.registrar(e, LOG, MethodSign.build(pComponenteArchivoId));
		}
		LOG.info("Finalizando obtenerComponentesCertificados");
		return vListaComponenteCertificado;
	}
	

}
