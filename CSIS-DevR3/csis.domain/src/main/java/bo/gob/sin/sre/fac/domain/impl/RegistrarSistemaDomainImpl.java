package bo.gob.sin.sre.fac.domain.impl;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bo.gob.sin.str.cexc.LogExcepcion;
import bo.gob.sin.str.cexc.MethodSign;
import bo.gob.sin.sre.fac.dao.ISistemasDao;
import bo.gob.sin.sre.fac.domain.IRegistrarSistemaDomain;
import bo.gob.sin.sre.fac.model.SreSistemas;
import bo.gob.sin.str.constantes.recaudaciones.ConstFacturacion;

@Service
@Transactional(rollbackFor = Exception.class)
public class RegistrarSistemaDomainImpl implements IRegistrarSistemaDomain, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(RegistrarSistemaDomainImpl.class);
	
	@Autowired
	ISistemasDao iSistemasDao;
	
	
	//IASC - Genera codigo de sistema - 01/12/2018
	@Override
 	public String generarCodigoSistema(SreSistemas pSolicitud)
	{
		String vCodigoSistema = "0";
		int radix = 16; 

		try {
			if (pSolicitud.getTramiteId() > 0 && pSolicitud.getSistemaId() > 0 && pSolicitud.getTipoSistemaId() > 0) {
				
				String vSistemaId = String.valueOf(pSolicitud.getSistemaId());
				String vTramiteId = String.valueOf(pSolicitud.getTramiteId());
				String vTipoSistemaId = String.valueOf(pSolicitud.getTipoSistemaId());
				
				String vInput = vSistemaId + vTramiteId + vTipoSistemaId;
				BigInteger numBig = new BigInteger(vInput);
				//Hexadecimal
				vCodigoSistema = numBig.toString(radix).toUpperCase();
				
				LOG.info("Codigo de sistema generado correctamente");
			}
		} catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(pSolicitud));
			LOG.info("Error, Excepcion al momento de generar codigo de sistema");
		}
		
		return vCodigoSistema;
	}
	
	
	/**1
     * Objetivo: registrar solicitud de sistemas.
     * Creado por: Reynaldo Chambi.
     * Fecha: 03/04/2018
     * Modificado por: Gualberto Condori
     * Fecha de Modificacion: 03/07/2018
     */
	@Override
	public SreSistemas registrarSistemaACertificar(SreSistemas pSolicitud){
		SreSistemas vRegistroSistema = new SreSistemas();
		java.util.Date vFechaHoy = new Date();
		String vCodigoSistema; 
		try {
			vRegistroSistema.setEstadoId(ConstFacturacion.ESTADO_ACTIVO);
			vRegistroSistema.setEstadoSistemaId(ConstFacturacion.ESTADO_SISTEMA_INICIADO);
			vRegistroSistema.setFechaRegistro(vFechaHoy);
			vRegistroSistema.setFechaUltimaModificacion(vFechaHoy);
			vRegistroSistema.setNombreSistema(pSolicitud.getNombreSistema().toUpperCase());
			vRegistroSistema.setTipoDepartamentoId(pSolicitud.getTipoDepartamentoId());
			vRegistroSistema.setTipoSistemaId(pSolicitud.getTipoSistemaId());
			vRegistroSistema.setTramiteId(pSolicitud.getTramiteId());
			vRegistroSistema.setUsuarioRegistroId(pSolicitud.getUsuarioRegistroId());
			vRegistroSistema.setUsuarioUltimaModificacionId(pSolicitud.getUsuarioRegistroId());
			vRegistroSistema.setVersion(pSolicitud.getVersion().toUpperCase());
			
			iSistemasDao.save(vRegistroSistema);
						
			vCodigoSistema = this.generarCodigoSistema(vRegistroSistema);
			vRegistroSistema.setCodigoSistema(vCodigoSistema);
			iSistemasDao.saveOrUpdate(vRegistroSistema);
			LOG.info("Datos de sistema registrados.");
				
		}catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(vRegistroSistema));
			LOG.info("Datos sistema no registrados.");
			vRegistroSistema.setSistemaId(0L);
		}
		return vRegistroSistema;
	}
}
