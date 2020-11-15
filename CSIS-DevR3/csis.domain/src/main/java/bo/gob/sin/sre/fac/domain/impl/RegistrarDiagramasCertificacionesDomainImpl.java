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

import bo.gob.sin.sre.fac.dao.IDiagramasCertificacionesDao;
import bo.gob.sin.sre.fac.domain.IRegistrarDiagramasCertificacionesDomain;
import bo.gob.sin.sre.fac.model.SreDiagramasCertificaciones;
import bo.gob.sin.str.cexc.LogExcepcion;
import bo.gob.sin.str.cexc.MethodSign;

@Service
@Transactional(rollbackFor = Exception.class)

public class RegistrarDiagramasCertificacionesDomainImpl implements IRegistrarDiagramasCertificacionesDomain, Serializable {


	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(RegistrarDiagramasCertificacionesDomainImpl.class);
	
	@Autowired
	private IDiagramasCertificacionesDao iDiagramasCertificacionesDao;

	@Override
	public SreDiagramasCertificaciones registraDiagramasCertificaciones(SreDiagramasCertificaciones pSolicitud) {
		try {
			iDiagramasCertificacionesDao.save(pSolicitud);
		}catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(pSolicitud));
			LOG.info("Datos pruebas sistema no registrados.");
		}
		return pSolicitud;
	}
	
	/** 
	 * Realiza el listado de del diagrama de certificaciones 
	 * 
	 * @author: junior.flores 
	 * @Fecha:  24/09/2019
	 * @param   pEtapaId, C�digo unico de la Etapa.
	 * 		    pSistemaId, C�digo unico del sistema.
	 * 		    pSolicitudCertificacionId, C�digo unico de la solicitud de certificaci�n.	   		   
	 * @return  Devuelve la lista SreDiagramasCertificaciones.
	 */
	@Override
	public List<SreDiagramasCertificaciones> recuperaListaDiagramasCertificaciones(long pEtapaId, long pSistemaId, long pSolicitudCertificacionId){
		List<SreDiagramasCertificaciones> listaComponentesArchivos=new ArrayList<>(); 
		LOG.info("ingresando a obtieneComponentesArchivos");
		try {
			
			listaComponentesArchivos = iDiagramasCertificacionesDao.recuperaListaDiagramasCertificaciones(pEtapaId, pSistemaId, pSolicitudCertificacionId);
			
		} catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(pSistemaId));
		}
		LOG.info("Finalizando Obtiene Componentes Archivos");
		return listaComponentesArchivos;
	}
	
	/** 
	 * Recupera el diagrama de certificaciones 
	 * 
	 * @author: wilson.limachi 
	 * @Fecha:  27/09/2019
	 * @param   pDiagramaCertificacionId.	   		   
	 * @return  Devuelve SreDiagramasCertificaciones.
	 */
	@Override
	public SreDiagramasCertificaciones actualizaDiagramaCertificacion(long pDiagramaCertificacionId, Long pUsuarioUltimaModificacionId)
	{ 
		SreDiagramasCertificaciones vSreDiagramasCertificaciones = new SreDiagramasCertificaciones();
		LOG.info("ingresando a obtieneComponentesArchivos");
		try 
		{	
			vSreDiagramasCertificaciones = iDiagramasCertificacionesDao.findById(pDiagramaCertificacionId);
			vSreDiagramasCertificaciones.setUsuarioUltimaModificacionId(pUsuarioUltimaModificacionId);
			vSreDiagramasCertificaciones.setEstadoId("IA");
			vSreDiagramasCertificaciones.setFechaUltimaModificacion(new Date());
			iDiagramasCertificacionesDao.saveOrUpdate(vSreDiagramasCertificaciones);
		} 
		catch (Exception e) 
		{
			vSreDiagramasCertificaciones = null;
			LogExcepcion.registrar(e, LOG, MethodSign.build(pDiagramaCertificacionId));
		}
		
		LOG.info("Finalizando Obtiene Componentes Archivos");
		return vSreDiagramasCertificaciones;
	}

}
