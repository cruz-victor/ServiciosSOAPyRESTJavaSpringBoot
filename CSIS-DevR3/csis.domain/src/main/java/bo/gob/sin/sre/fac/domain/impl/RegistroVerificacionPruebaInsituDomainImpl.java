package bo.gob.sin.sre.fac.domain.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bo.gob.sin.sre.fac.dao.IRegistroVerificacionPruebaInsituDao;
import bo.gob.sin.sre.fac.domain.IRegistroVerificacionPruebaInsituDomain;
import bo.gob.sin.sre.fac.dto.RegistroVerificacionInsituDto;
import bo.gob.sin.sre.fac.model.SreRegistroVerificacionPruebaInsitu;
import bo.gob.sin.str.constantes.recaudaciones.ConstFacturacion;

@Service
@Transactional
public class RegistroVerificacionPruebaInsituDomainImpl implements IRegistroVerificacionPruebaInsituDomain {
	
	@Autowired
	IRegistroVerificacionPruebaInsituDao iRegistroVerificacionPruebaInsituDao;

	
	@Override
	public Long guardar(RegistroVerificacionInsituDto pSolicitud) 
	{
		Date vFechaHoy = new Date();
		SreRegistroVerificacionPruebaInsitu vEntidad = new SreRegistroVerificacionPruebaInsitu();
		
		if (pSolicitud.getRegistroObservacionInsituId()!=null && pSolicitud.getRegistroObservacionInsituId() != 0) 
		{
			vEntidad = iRegistroVerificacionPruebaInsituDao.findById(pSolicitud.getRegistroObservacionInsituId());
			vEntidad.setUsuarioUltimaModificacionId(pSolicitud.getUsuarioRegistroId());
			vEntidad.setFechaUltimaModificacion(vFechaHoy);
		} 
		else 
		{
			vEntidad.setUsuarioRegistroId(pSolicitud.getUsuarioRegistroId());
			vEntidad.setUsuarioUltimaModificacionId(pSolicitud.getUsuarioRegistroId());
			vEntidad.setFechaRegistro(vFechaHoy);
			vEntidad.setFechaUltimaModificacion(vFechaHoy);
			vEntidad.setEstadoId(ConstFacturacion.ESTADO_ACTIVO);
		}
		
		vEntidad.setEstadoPruebaId(pSolicitud.getEstadoPruebaId());
		vEntidad.setObservacion(pSolicitud.getObservacion());
		vEntidad.setObservacionInsituId(pSolicitud.getObservacionInsituId());
		vEntidad.setSolicitudCertificacionId(pSolicitud.getSolicitudCertificacionId());
		iRegistroVerificacionPruebaInsituDao.saveOrUpdate(vEntidad);
		return vEntidad.getRegistroObservacionInsituId();
	}
	
	@Override
	public Long actualizarVisitaInsitu(RegistroVerificacionInsituDto pSolicitud) 
	{
		Date vFechaHoy = new Date();
		SreRegistroVerificacionPruebaInsitu vEntidad = new SreRegistroVerificacionPruebaInsitu();
		
		if (pSolicitud.getRegistroObservacionInsituId()!=null && pSolicitud.getRegistroObservacionInsituId() != 0) 
		{
			vEntidad = iRegistroVerificacionPruebaInsituDao.findById(pSolicitud.getRegistroObservacionInsituId());
			vEntidad.setUsuarioUltimaModificacionId(pSolicitud.getUsuarioRegistroId());
			vEntidad.setFechaUltimaModificacion(vFechaHoy);
		} 
		else 
		{
			vEntidad.setUsuarioRegistroId(pSolicitud.getUsuarioRegistroId());
			vEntidad.setUsuarioUltimaModificacionId(pSolicitud.getUsuarioRegistroId());
			vEntidad.setFechaRegistro(vFechaHoy);
			vEntidad.setFechaUltimaModificacion(vFechaHoy);
			vEntidad.setEstadoId(ConstFacturacion.ESTADO_ACTIVO);
		}
		
		vEntidad.setEstadoPruebaId(pSolicitud.getEstadoPruebaId());
		vEntidad.setObservacion(pSolicitud.getObservacion());
		vEntidad.setObservacionInsituId(pSolicitud.getObservacionInsituId());
		vEntidad.setSolicitudCertificacionId(pSolicitud.getSolicitudCertificacionId());
		iRegistroVerificacionPruebaInsituDao.saveOrUpdate(vEntidad);
		return vEntidad.getRegistroObservacionInsituId();
	}
	
	
	@Override
	public Long guardarVisitaInsitu(RegistroVerificacionInsituDto pSolicitud) 
	{
		Date vFechaHoy = new Date();
		SreRegistroVerificacionPruebaInsitu vEntidad = new SreRegistroVerificacionPruebaInsitu();
		
		vEntidad.setEstadoPruebaDescripcion(pSolicitud.getEstadoPruebaDescripcion());
		vEntidad.setUsuarioRegistroId(pSolicitud.getUsuarioRegistroId());
		vEntidad.setUsuarioUltimaModificacionId(pSolicitud.getUsuarioRegistroId());
		vEntidad.setFechaRegistro(vFechaHoy);
		vEntidad.setFechaUltimaModificacion(vFechaHoy);
		vEntidad.setEstadoId(ConstFacturacion.ESTADO_ACTIVO);
		vEntidad.setEstadoPruebaId(pSolicitud.getEstadoPruebaId());
		vEntidad.setObservacion(pSolicitud.getObservacion());
		vEntidad.setObservacionInsituId(pSolicitud.getObservacionInsituId());
		vEntidad.setTipoEsquemaId(pSolicitud.getTipoEsquemaId());
		vEntidad.setTipoObligatorioId(pSolicitud.getTipoObligatorioId());
		vEntidad.setSolicitudCertificacionId(pSolicitud.getSolicitudCertificacionId());
		iRegistroVerificacionPruebaInsituDao.saveOrUpdate(vEntidad);
		return vEntidad.getRegistroObservacionInsituId();
	}
	
	@Override
	public List<SreRegistroVerificacionPruebaInsitu> recuperarListaRegistroObservacionesInSitu(long pSolicitudCertificacionId) 
	{
		List<SreRegistroVerificacionPruebaInsitu> vListaSolicitudEntidad = new ArrayList<>();		
		vListaSolicitudEntidad = iRegistroVerificacionPruebaInsituDao.recuperarListaRegistroObservacionesInSitu(pSolicitudCertificacionId);
		
		return vListaSolicitudEntidad;
	}
}
