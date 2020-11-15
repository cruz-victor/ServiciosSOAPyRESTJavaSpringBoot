package bo.gob.sin.sre.fac.domain.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import bo.gob.sin.sre.fac.dao.IPruebasCertificacionSistemasDao;
import bo.gob.sin.sre.fac.dao.IPruebasEtapaCertificacionDao;
import bo.gob.sin.sre.fac.domain.IPruebasCertificacionSistemasDomain;
import bo.gob.sin.sre.fac.dto.SeguimientoDetalleCertificacionSistemasDto;
import bo.gob.sin.sre.fac.dto.SolicitudActualizaPruebaCertificacionSistemaDto;
import bo.gob.sin.sre.fac.model.SrePruebasCertificacionSistemas;
import bo.gob.sin.sre.fac.model.parameter.ParametrosCsis;
import bo.gob.sin.str.cexc.LogExcepcion;
import bo.gob.sin.str.cexc.MethodSign;

@Service
@Transactional
public class PruebasCertificacionSistemasDomainImpl implements IPruebasCertificacionSistemasDomain, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	IPruebasCertificacionSistemasDao iPruebasCertificacionSistemasDao;
	
	@Autowired
	IPruebasEtapaCertificacionDao iPruebasEtapaCertificacionDao;
	private static final Logger LOG = LoggerFactory.getLogger(ArchivosTmpDomainImpl.class);
	
	/***
	 * Actualiza la entidad SrePruebasCertificacionSistemas
	 * 
	 * @author junior.flores
	 * @return La entidad SrePruebasCertificacionSistemas
	 * @fecha 05/06/2019
	 */
	 @Override
	 public SrePruebasCertificacionSistemas actualizarPruebasCertificacionSistemas(SolicitudActualizaPruebaCertificacionSistemaDto pSolicitud, Date pFechaActual) {
		 LOG.info("Ingresando a actualizarPruebasCertificacionSistemas");
		 SrePruebasCertificacionSistemas vSrePruebasCertificacionSistemas = null; 
		 SrePruebasCertificacionSistemas vRespuesta = null;		 
		 try {
			 // Obtener la informaci贸n de base de datos
			 if(pSolicitud.isInicioPrueba()) {				 					 
					 Long vPruebaEtapaCertificacionId=iPruebasEtapaCertificacionDao.obtenerPruebaEtapaCertificacionId(pSolicitud.getEtapaCertificacionSistemaId());
					 SrePruebasCertificacionSistemas vSrePruebasCertificacionSistemasVerificacion=iPruebasCertificacionSistemasDao.obtenerPruebasCertificacionSistemasPorEtapa(vPruebaEtapaCertificacionId,pSolicitud.getSistemaId());
					 if(vSrePruebasCertificacionSistemasVerificacion.getPruebaCertificacionSistemaId()==0){
						 vSrePruebasCertificacionSistemas = new SrePruebasCertificacionSistemas();
						 vSrePruebasCertificacionSistemas.setSistemaId(pSolicitud.getSistemaId());
						 vSrePruebasCertificacionSistemas.setPruebaEtapaSistemasId(vPruebaEtapaCertificacionId);				 
						 vSrePruebasCertificacionSistemas.setFechaInicioPrueba(pFechaActual);
						 vSrePruebasCertificacionSistemas.setFechaUltimaModificacion(pFechaActual);
						 vSrePruebasCertificacionSistemas.setFechaRegistro(pFechaActual);
						 vSrePruebasCertificacionSistemas.setUsuarioRegistroId(1000);
						 vSrePruebasCertificacionSistemas.setUsuarioUltimaModificacionId(1000);
						 vSrePruebasCertificacionSistemas.setEtapaCompletada(false);
						 vSrePruebasCertificacionSistemas.setEstadoId("AC");				
						 iPruebasCertificacionSistemasDao.save(vSrePruebasCertificacionSistemas);
						 vRespuesta = vSrePruebasCertificacionSistemas;
						 if(vRespuesta.getPruebaCertificacionSistemaId()>0 && pSolicitud.getEtapaCertificacionSistemaId().equals(ParametrosCsis.ETAPA_SISTEMA_PROVEEDORES))
							 this.asignarNitProveedores(pSolicitud.getSistemaId(),pSolicitud.getContribuyenteId(),pSolicitud.getModalidadFacturacion());
					}
			 }else {
				 vSrePruebasCertificacionSistemas = new SrePruebasCertificacionSistemas();
				 vSrePruebasCertificacionSistemas = iPruebasCertificacionSistemasDao.findById(pSolicitud.getPruebaCertificacionSistemaId());
				 vSrePruebasCertificacionSistemas.setFechaUltimaModificacion(pFechaActual);		 				 
				 vSrePruebasCertificacionSistemas.setFechaFinPrueba(pFechaActual);
				 iPruebasCertificacionSistemasDao.saveOrUpdate(vSrePruebasCertificacionSistemas);
				 vRespuesta = iPruebasCertificacionSistemasDao.findById(pSolicitud.getPruebaCertificacionSistemaId());
			 }			 
				 			 		 
		 }catch (Exception e){
			 LogExcepcion.registrar(e, LOG, MethodSign.build(vRespuesta));
			 LOG.info("Saliendo de actualizarPruebasCertificacionSistemas: " + vRespuesta);
		}
		return vRespuesta;
	 }
	 
	/***
	 * Reiniciar la entidad SrePruebasCertificacionSistemas
	 * 
	 * @author junior.flores
	 * @return La entidad SrePruebasCertificacionSistemas
	 * @fecha 06/06/2019
	 */
	 @Override
	 public SrePruebasCertificacionSistemas reiniciarPruebasCertificacionSistemas(Long pPruebaCertificacionSistemaId, Date pFechaActual) {
		 LOG.info("Ingresando a actualizarPruebasCertificacionSistemas");
		 SrePruebasCertificacionSistemas vSrePruebasCertificacionSistemas = new SrePruebasCertificacionSistemas(); 			 
		 SrePruebasCertificacionSistemas vRespuesta = null;
		 try {
			 //Modificar
			 vSrePruebasCertificacionSistemas = iPruebasCertificacionSistemasDao.findById(pPruebaCertificacionSistemaId);
			 if(vSrePruebasCertificacionSistemas!=null && vSrePruebasCertificacionSistemas.getEstadoId().equals("AC")) {
				 vRespuesta = new SrePruebasCertificacionSistemas();
				 vSrePruebasCertificacionSistemas.setFechaUltimaModificacion(pFechaActual);
				 vSrePruebasCertificacionSistemas.setEstadoId("AN");		 
				 iPruebasCertificacionSistemasDao.saveOrUpdate(vSrePruebasCertificacionSistemas);
				 //Adicionar
				 vSrePruebasCertificacionSistemas.setPruebaCertificacionSistemaId(0);
				 vSrePruebasCertificacionSistemas.setEstadoId("AC");
				 vSrePruebasCertificacionSistemas.setFechaInicioPrueba(pFechaActual);
				 vSrePruebasCertificacionSistemas.setEtapaCompletada(false);
				 vSrePruebasCertificacionSistemas.setFechaFinPrueba(null);
				 iPruebasCertificacionSistemasDao.save(vSrePruebasCertificacionSistemas);
				 vRespuesta = iPruebasCertificacionSistemasDao.findById(pPruebaCertificacionSistemaId);
			 }
		 }catch (Exception e){
			 LogExcepcion.registrar(e, LOG, MethodSign.build(vRespuesta));
			 LOG.info("Saliendo de actualizarPruebasCertificacionSistemas: " + vRespuesta);
		}
		return vRespuesta;
	 }
	 
	/**
	* @Descipcion: Realiza el registro del log seleccionado en la grilla del Detalle de Certificaci贸n de Pruebas.
	* @author: Peter Flores.
	* @Fecha: 08/07/2019
	* @param pSolicitud: Informaci贸n necesaria para registrar la etapa de prueba (detalle)
	* @return Devuelve un valor booleano que indica la transacci贸n
	*/
	@Override
	public boolean registroPruebaDetalleOpcional(SeguimientoDetalleCertificacionSistemasDto pSolicitud) {
		boolean vPruebaEtapaCertificacionId=iPruebasCertificacionSistemasDao.registroPruebaDetalleOpcional(pSolicitud);
		return vPruebaEtapaCertificacionId;
	}
	
	/**
	* @Descipcion: Realiza el registro en las siguientes tablas: 
	* 				- sre_fac_nit_asignados_clientes_proveedor
	* 				- sre_fac_sistemas_contribuyentes
	* 				- re_fac_inicios_sistemas
	* @author: Peter Flores.
	* @Fecha: 22/08/2019
	* @param pSistemaId: C骴igo unico del Sistema 
	* @return Devuelve un valor booleano que indica la transacci贸n
	*/	
	public boolean asignarNitProveedores(Long pSistemaId, Long pContribuyenteId, String pModalidadesFacturacion) {
		boolean vPruebaEtapaCertificacionId=iPruebasCertificacionSistemasDao.asignarNitProveedores(pSistemaId,pContribuyenteId,pModalidadesFacturacion);
		return vPruebaEtapaCertificacionId;
	}
}
