package bo.gob.sin.sre.fac.domain.impl;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import bo.gob.sin.sre.fac.dao.ISistemasContribuyentesDao;
import bo.gob.sin.sre.fac.dao.ISistemasDao;
import bo.gob.sin.sre.fac.domain.ISistemasDomain;
import bo.gob.sin.sre.fac.domain.ISolicitudCertificacionDomain;
import bo.gob.sin.sre.fac.dto.RespuestaActualizacionDto;
import bo.gob.sin.sre.fac.model.SreIniciosSistemas;
import bo.gob.sin.sre.fac.model.SreSistemas;
import bo.gob.sin.sre.fac.model.SreSistemasContribuyentes;
import bo.gob.sin.str.cexc.LogExcepcion;
import bo.gob.sin.str.cexc.MethodSign;
import bo.gob.sin.str.constantes.recaudaciones.ConstFacturacion;

@Service
@Transactional
public class SistemaDomainImpl implements ISistemasDomain, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LoggerFactory.getLogger(SistemaDomainImpl.class);
	@Autowired
	ISistemasDao iSreFacDaoSistemas;
	@Autowired
	ISistemasContribuyentesDao iSistemasContribuyentesDao;
	@Autowired
	ISolicitudCertificacionDomain iSolicitudCertificacionService;
	
	@Override
	public RespuestaActualizacionDto actualizarEstadoSistemaIniciado(Long pSistemaId) {
		LOG.info("Ingresando ActualizatEstadoSistema pSistemaId ={} ", pSistemaId);
		RespuestaActualizacionDto vResultadoActualizacion = new RespuestaActualizacionDto();
		try {
			if (pSistemaId > 0 && pSistemaId != null) {
				iSreFacDaoSistemas.actualizarEstadosSistemas(pSistemaId, ConstFacturacion.ESTADO_SISTEMA_INICIADO);
				vResultadoActualizacion.setEstaActualizado(true);
				vResultadoActualizacion.setOk(true);
			} else {
				LOG.info("No se pudo actualizar el estado sistema");
			}
		} catch (Exception e) {
			vResultadoActualizacion.setEstaActualizado(false);
			vResultadoActualizacion.setOk(false);
			LogExcepcion.registrar(e, LOG, MethodSign.build(e));
		}
		LOG.info("Saliendo de ActualizatEstadoSistema", vResultadoActualizacion);
		return vResultadoActualizacion;
	}
	

	/**
	    * Objetivo: verificacion de sistemas registrados.
	    * Creado por: Reynaldo Chambi.
	    * Fecha: 13/04/2018
	    * Modificado por: Gualberto Condori
	    * Fecha de Modificacion: 18/06/2018
	 */
	@Override
	public SreSistemasContribuyentes actualizarEstadoSistemaCertificado(Long pSistemaId, Long pContribuyenteId, Long pSolicitudCertificacionId, int pModalidad) {
		LOG.info("Ingresando ActualizatEstadoSistema pSistemaId ={} ", pSistemaId);
		SreSistemasContribuyentes vEntidad = new SreSistemasContribuyentes();
		vEntidad = iSistemasContribuyentesDao.recuperarRegistroSistemaContribuyenteSistemasId(pSistemaId, pContribuyenteId, pModalidad);
		iSistemasContribuyentesDao.actualizarEstadosSistemasContribuyentes(vEntidad.getSistemaContribuyenteId(), ConstFacturacion.ESTADO_SISTEMA_CONTRIBUYENTE_ID_APROBADA, pModalidad);
		
		iSolicitudCertificacionService.autorizarSolicitudCertificacionSistema(pSolicitudCertificacionId);
		
		RespuestaActualizacionDto vResultadoActualizacion = new RespuestaActualizacionDto();
		try {
			if (pSistemaId > 0 && pSistemaId != null) {
				iSreFacDaoSistemas.actualizarEstadosSistemas(pSistemaId, ConstFacturacion.ESTADO_SISTEMA_CERTIFICADO);
				vResultadoActualizacion.setEstaActualizado(true);
				vResultadoActualizacion.setOk(true);
			} else {
				LOG.info("No se pudo actualizar el estado sistema");
			}
		} catch (Exception e) {
			vResultadoActualizacion.setEstaActualizado(false);
			vResultadoActualizacion.setOk(false);
			LogExcepcion.registrar(e, LOG, MethodSign.build(e));
		}
		LOG.info("Saliendo de ActualizatEstadoSistema", vResultadoActualizacion);
		return vEntidad;
	}
	
	//RCR
	@Override
	public RespuestaActualizacionDto actualizarEstadoSistemaObservado(Long pSistemaId) {
		LOG.info("Ingresando ActualizatEstadoSistema pSistemaId ={} ", pSistemaId);
		RespuestaActualizacionDto vResultadoActualizacion = new RespuestaActualizacionDto();
		try {
			if (pSistemaId > 0 && pSistemaId != null) {
				iSreFacDaoSistemas.actualizarEstadosSistemas(pSistemaId, ConstFacturacion.ESTADO_SISTEMA_OBSERVADO);
				vResultadoActualizacion.setEstaActualizado(true);
				vResultadoActualizacion.setOk(true);
			} else {
				LOG.info("No se pudo actualizar el estado sistema");
			}
		} catch (Exception e) {
			vResultadoActualizacion.setEstaActualizado(false);
			vResultadoActualizacion.setOk(false);
			LogExcepcion.registrar(e, LOG, MethodSign.build(e));
		}
		LOG.info("Saliendo de ActualizatEstadoSistema", vResultadoActualizacion);
		return vResultadoActualizacion;
	}

	//RCR
	@Override
	public RespuestaActualizacionDto actualizarEstadoSistemaBaja(Long pSistemaId) {
		LOG.info("Ingresando ActualizatEstadoSistema pSistemaId ={} ", pSistemaId);
		RespuestaActualizacionDto vResultadoActualizacion = new RespuestaActualizacionDto();
		try {
			if (pSistemaId > 0 && pSistemaId != null) {
				iSreFacDaoSistemas.actualizarEstadosSistemas(pSistemaId, ConstFacturacion.ESTADO_SISTEMA_BAJA);
				vResultadoActualizacion.setEstaActualizado(true);
				vResultadoActualizacion.setOk(true);
			} else {
				LOG.info("No se pudo actualizar el estado sistema");
			}
		} catch (Exception e) {
			vResultadoActualizacion.setEstaActualizado(false);
			vResultadoActualizacion.setOk(false);
			LogExcepcion.registrar(e, LOG, MethodSign.build(e));
		}
		LOG.info("Saliendo de ActualizatEstadoSistema", vResultadoActualizacion);
		return vResultadoActualizacion;
	}
	
	/**
	    * Objetivo: verificacion de sistemas registrados.
	    * Creado por: Reynaldo Chambi.
	    * Fecha: 13/04/2018
	    * Modificado por: Gualberto Condori
	    * Fecha de Modificacion: 15/06/2018
	    */
	@Override
	public List<SreSistemas> verificacionSistemaRegistrado(String pNombreSistema, Integer pModalidadFacturacionId, Long pContribuyenteId) {
		List<SreSistemas> respuestaRegistro = new ArrayList<>();
		LOG.info("ingresando verificacionSistemaRegistrado");
		try {
			if (!pContribuyenteId.equals(null) && pNombreSistema != null && pContribuyenteId > 0 && pModalidadFacturacionId > 0) {
				respuestaRegistro = iSreFacDaoSistemas.verificacionSistemaRegistrado(pNombreSistema, pModalidadFacturacionId, pContribuyenteId);
				LOG.info("verificaicon de sistemas exitoso");
			} else {
				LOG.info("No se pudo verificar los sistemas por los datos de entrada");
			}
		} catch (Exception e) {
			LOG.info("No se pudo verificar existe excepciones");
			LogExcepcion.registrar(e, LOG, MethodSign.build(e));
		}
		LOG.info("Saliendo de ActualizatEstadoSistema", respuestaRegistro);
		return respuestaRegistro;
	}
	
	
	/**
	 * Obtener version del sistema
	 * 
	 * @author: Carmen Rosa Silva
	 * @Fecha: 22/06/2018
	 * @param pSistemaId, numero identificador de sistema
	 * @return   Devuelve el version sistema String.
	 */
	@Override
	public String obtenerVersionSistema(long pSistemaId) {
		LOG.info("Ingresando obtenerVersionPrueba ");
		SreSistemas vRespuesta = new SreSistemas();
		String vResultado="";

		try {
			vRespuesta = iSreFacDaoSistemas.obtenerSistemaPorId(pSistemaId);	
			vResultado= vRespuesta.getVersion();
			
		} catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(vResultado));
		}
		LOG.info("Saliendo de recuperar obtenerVersionPrueba: " + vResultado);
		return vResultado;
	}
	
	
	/**
	 * Obtener descripcion del sistema
	 * 
	 * @author: Carmen Rosa Silva
	 * @Fecha: 22/06/2018
	 * @param pSistemaId, numero identificador de sistema
	 * @return   Devuelve el descripcion sistema String.
	 */
	@Override
	public String obtenerDescripcionSistema(long pSistemaId) {
		LOG.info("Ingresando obtenerDescripcionSistema ");
		SreSistemas vRespuesta = new SreSistemas();
		String vResultado="";

		try {
			vRespuesta = iSreFacDaoSistemas.obtenerSistemaPorId(pSistemaId);	
			vResultado= vRespuesta.getNombreSistema();
			
		} catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(vResultado));
		}
		LOG.info("Saliendo de recuperar obtenerDescripcionSistema: " + vResultado);
		return vResultado;
	}
	
	/**
	 * Obtener codigo sistema asociado del sistema
	 * 
	 * @author: Carmen Rosa Silva
	 * @Fecha: 27/06/2018
	 * @param pSistemaId, numero identificador de sistema
	 * @return   Devuelve el codigo sistema asociado sistema String.
	 */
	@Override
	public String obtenerCodigoSistemaAsociado(long pSistemaId) {
		LOG.info("Ingresando obtenerCodigoSistemaAsociado ");
		SreSistemas vRespuesta = new SreSistemas();
		String vResultado="";

		try {
			vRespuesta = iSreFacDaoSistemas.obtenerSistemaPorId(pSistemaId);	
			if(vRespuesta!=null) {
				vResultado= vRespuesta.getNombreSistema();
			}else {
				vResultado="";
			}
			
		} catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(vResultado));
		}
		LOG.info("Saliendo de recuperar obtenerCodigoSistemaAsociado: " + vResultado);
		return vResultado;
	}
	
	/**
	 * Obtener Solicitud de Sistemas por sistemaId
	 * 
	 * @author: Carmen Rosa Silva
	 * @Fecha: 19/06/2018
	 * @param pSistemaId, número identificación de sistema
	 * @return   Devuelve objeto respuesta SreSistema.
	 */
	@Override
	public SreSistemas recuperarDatosSistemas(Long pSistemaId) {
		LOG.info("Ingresando obtenerSolicitudSistemas ");
		SreSistemas vRespuesta = new SreSistemas();

		try {
			vRespuesta = iSreFacDaoSistemas.obtenerSolicitudSistemaCertificado(pSistemaId);

		} catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(vRespuesta));
		}
		LOG.info("Saliendo de recuperar obtenerSolicitudSistemas: " + vRespuesta);
		return vRespuesta;
	}
	
	public SreSistemas actualizarDatosSistemas(Long pSistemaId) {
		SreSistemas vResultado= new SreSistemas();
		try {
			vResultado=iSreFacDaoSistemas.findById(pSistemaId);
			vResultado.setEstadoSistemaId(ConstFacturacion.ESTADO_SOLICITUD_CERTIFICACION_HISTORICO);
			iSreFacDaoSistemas.saveOrUpdate(vResultado);

		} catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(vResultado));
			LOG.info("False");
			return null;
		}

		return vResultado;
	}
	
	//IASC - Verifica si existe sistema - 22/07/2018
	public boolean verificaExisteSistema(Long pSistemaId) {
		boolean vExiste = true;
		Long vResultado = iSreFacDaoSistemas.verificaExisteSistema(pSistemaId);
		if (vResultado == 0) {
			vExiste = false;
		}
		return vExiste;
	}
	
	//IASC - Para cancelar el sistema de la solicitud de certificacion - 21/09/2018
	
	//TODO LLEVADO A SISTEMAABMDOMAIN, CON EL NOMBRE CAMBIAR ESTADO SISTEMA, PARAMETRIZADO
	@Override
	public SreSistemas actualizarEstadoSistemaCancelado(Long pSistemaId, Long pUsuario) {
		LOG.info("Ingresando ActualizatEstadoSistema pSistemaId ={} ", pSistemaId);
		
		SreSistemas vResultado= new SreSistemas();
		try {
			vResultado = iSreFacDaoSistemas.findById(pSistemaId);
			vResultado.setEstadoSistemaId(ConstFacturacion.ESTADO_SISTEMA_CANCELADO);
			vResultado.setFechaUltimaModificacion(new Date());
			vResultado.setUsuarioUltimaModificacionId(pUsuario);
			iSreFacDaoSistemas.saveOrUpdate(vResultado);
		} catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(e));
			vResultado = null;
		}
		LOG.info("Saliendo de ActualizatEstadoSistema", vResultado);
		return vResultado;
	}

	
}
