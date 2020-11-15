package bo.gob.sin.sre.fac.domain.impl;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bo.gob.sin.sre.fac.dao.IBitacorasObservacionesComponentesInsituDao;
import bo.gob.sin.sre.fac.dao.IContribuyentesModalidadesDao;
import bo.gob.sin.sre.fac.dao.IObservacionesComponentesInsituDao;
import bo.gob.sin.sre.fac.domain.IObservacionesComponentesInsituDomain;
import bo.gob.sin.sre.fac.model.SreBitacorasObservacionesComponentesInsitu;
import bo.gob.sin.sre.fac.model.SreObservacionesComponentesInsitu;
import bo.gob.sin.str.constantes.recaudaciones.ConstFacturacion;

@Service
@Transactional
public class ObservacionesComponentesInsituDomainImpl implements IObservacionesComponentesInsituDomain, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private IObservacionesComponentesInsituDao iObservacionesComponentesInsituDao;

	@Autowired
	private IBitacorasObservacionesComponentesInsituDao iBitacorasObservacionesComponentesInsituDao;
	
	@Autowired
	private IContribuyentesModalidadesDao iContribuyentesModalidadesDao;

	@Override
	public List<SreObservacionesComponentesInsitu> obtenerObservacionesComponentesInsituPorCodigoCertificacion(
			long pCodigoCertificacion) {
		List<SreObservacionesComponentesInsitu> vRespuesta =  iObservacionesComponentesInsituDao
				.obtenerObservacionesComponentesInsituPorCodigoCertificacion(pCodigoCertificacion);

		return vRespuesta;
	}

	@Override
	public SreObservacionesComponentesInsitu modificarObservacionInSitu(
			SreObservacionesComponentesInsitu pObservacionInsitu) {

		SreObservacionesComponentesInsitu vEntidadRecuperada = new SreObservacionesComponentesInsitu();
		vEntidadRecuperada = iObservacionesComponentesInsituDao
				.findById(pObservacionInsitu.getObservacionComponenteInsituId());

		vEntidadRecuperada.setEstadoPruebaId(pObservacionInsitu.getEstadoPruebaId());
		vEntidadRecuperada.setUsuarioUltimaModificacionId(pObservacionInsitu.getUsuarioUltimaModificacionId());
		vEntidadRecuperada.setFechaUltimaModificacion(pObservacionInsitu.getFechaUltimaModificacion());
		iObservacionesComponentesInsituDao.saveOrUpdate(vEntidadRecuperada);

		if (pObservacionInsitu.getBitacorasObservacionesComponentesInsitu() != null) {
			long vEstadoObservacionComponente = vEntidadRecuperada.getEstadoPruebaId();
			pObservacionInsitu.getBitacorasObservacionesComponentesInsitu().forEach(bitacora -> {
				bitacora.setBitacoraObservacionComponenteInsituId(0);
				bitacora.setEstadoPruebaId(vEstadoObservacionComponente);
				bitacora.setEstadoPruebaId(pObservacionInsitu.getEstadoPruebaId());
				bitacora.setObservacionComponenteInsituId(pObservacionInsitu.getObservacionComponenteInsituId());
				bitacora.setEstadoId(ConstFacturacion.ESTADO_ACTIVO);
				bitacora.setFechaRegistro(pObservacionInsitu.getFechaUltimaModificacion());
				bitacora.setUsuarioUltimaModificacionId(pObservacionInsitu.getUsuarioUltimaModificacionId());
				bitacora.setFechaUltimaModificacion(pObservacionInsitu.getFechaUltimaModificacion());
				iBitacorasObservacionesComponentesInsituDao.save(bitacora);
			});
		}
		
		return pObservacionInsitu;
	}

	@Override
	public SreObservacionesComponentesInsitu registrarObservacionInSitu(
			SreObservacionesComponentesInsitu pObservacionInsitu) {
		iObservacionesComponentesInsituDao.save(pObservacionInsitu);
		return pObservacionInsitu;
	}

	@Override
	public SreBitacorasObservacionesComponentesInsitu registrarBitacoraObservacionInSitu(
			SreBitacorasObservacionesComponentesInsitu pBitacoraObservacionesComponentesInsitu) {
		iBitacorasObservacionesComponentesInsituDao.save(pBitacoraObservacionesComponentesInsitu);
		return pBitacoraObservacionesComponentesInsitu;
	}

	@Override
	public List<SreBitacorasObservacionesComponentesInsitu> obtenerDetalleObservacionesComponentesInsituPorCodigoObservacion(
			long pCodigoObservacionComponenteInSitu) {
		return iBitacorasObservacionesComponentesInsituDao
				.obtenerDetalleObservacionesComponentesInsituPorCodigoObservacion(pCodigoObservacionComponenteInSitu);
	}

	@Override
	public List<SreObservacionesComponentesInsitu> obtenerObservacionesInsituPorCodigoCertificacionNoAprobadas(
			long pCodigoCertificacion) {

		List<SreObservacionesComponentesInsitu> vRespuesta = iObservacionesComponentesInsituDao
				.obtenerObservacionesComponentesInsituPorCodigoCertificacion(pCodigoCertificacion);

		vRespuesta = vRespuesta.stream().filter(x -> x.getEstadoPruebaId() != ConstFacturacion.ESTADO_PRUEBA_APROBADA)
				.collect(Collectors.toList());
		return vRespuesta;
	}

	@Override
	public boolean verificarContribuyenteRequiereVisitaInsitu(Long pNit) {
		return iContribuyentesModalidadesDao.verificarContribuyenteRequiereVisitaInsitu(pNit);
	}
}
