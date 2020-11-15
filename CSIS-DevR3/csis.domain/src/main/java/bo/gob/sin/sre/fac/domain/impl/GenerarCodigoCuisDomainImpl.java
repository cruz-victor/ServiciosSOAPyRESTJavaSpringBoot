package bo.gob.sin.sre.fac.domain.impl;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bo.gob.sin.str.cexc.LogExcepcion;
import bo.gob.sin.str.cexc.MethodSign;
import bo.gob.sin.str.constantes.recaudaciones.ConstFacturacion;
import bo.gob.sin.str.util.facturacion.Utiles;
import bo.gob.sin.sre.fac.domain.IGenerarCodigoCuisDomain;
import bo.gob.sin.sre.fac.dto.SolicitudGeneracionCuisDto;

@Service
@Transactional
public class GenerarCodigoCuisDomainImpl implements IGenerarCodigoCuisDomain, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LoggerFactory.getLogger(GenerarCodigoCuisDomainImpl.class);
	
	//FRRA
	@Override
	public SolicitudGeneracionCuisDto generarCuis(SolicitudGeneracionCuisDto pSolicitud) {

		SolicitudGeneracionCuisDto vRespuesta = new SolicitudGeneracionCuisDto();
		Utiles utiles = new Utiles();
		
		String modalidad = String.valueOf(pSolicitud.getModalidad());
		String sucursal = String.valueOf(pSolicitud.getSucursal());
		String nit = String.valueOf(pSolicitud.getNit());
		String idSistema = String.valueOf(pSolicitud.getIdSistema());
		SimpleDateFormat formateador = new SimpleDateFormat("yyyyMMdd");
		String vFecha = formateador.format(new Date());
		String vPuntoVenta = String.valueOf(0); 
		
		String input = utiles.corregirLongitudCadena(ConstFacturacion.CORREGIR_LONGITUD_MODALIDAD, modalidad)
				+ utiles.corregirLongitudCadena(ConstFacturacion.CORREGIR_LONGITUD_CADENA_SUCURSAL, sucursal)
				+ utiles.corregirLongitudCadena(ConstFacturacion.CORREGIR_LONGITUD_CADENA_NIT, nit)
				+ utiles.corregirLongitudCadena(ConstFacturacion.CORREGIR_LONGITUD_CADENA_IDSISTEMA, idSistema)
				+ utiles.corregirLongitudCadena(ConstFacturacion.CORREGIR_LONGITUD_CADENA_PUNTO_VENTA, vPuntoVenta) + vFecha;		
		
		try {
			String hexa = utiles.algoritmoGeneraCuis(input);
			String cuis = utiles.invertirCadena(hexa.toUpperCase());
			if (hexa.length() > 0) {
				LOG.info("GENERADO DE CUIS CON EXITO");
				vRespuesta.setCuis(cuis);
				vRespuesta.setOk(true);
			} else {
				LOG.info("NO se GENERÓ el CUIS ");
				vRespuesta.setOk(false);
			}
		} catch (Exception e) {
			vRespuesta.setOk(false);
			LogExcepcion.registrar(e, LOG, MethodSign.build(vRespuesta));
			LOG.info("Saliendo, Cuis no generado");
		}
		return vRespuesta;
	}
	
	// RCR
	@Override
 	public String generarCodigoCuis(SolicitudGeneracionCuisDto pSolicitud)
	{
		SolicitudGeneracionCuisDto vRespuestaCuis = new SolicitudGeneracionCuisDto();
		String vCuis = "0";

		try 
		{
			if (pSolicitud.getNit() > 0 && pSolicitud.getIdSistema() > 0) 
			{
				vRespuestaCuis = this.generarCuis(pSolicitud);
				
				if (vRespuestaCuis.isOk()) 
				{
					vCuis = vRespuestaCuis.getCuis();
					LOG.info("Cuis generado correctamente");
				}
			}
		} 
		catch (Exception e) 
		{
			vCuis = "0";
			LogExcepcion.registrar(e, LOG, MethodSign.build(vRespuestaCuis));
			LOG.info("Error, Excepcion al momento de generar");
		}
		
		return vCuis;
	}
}
