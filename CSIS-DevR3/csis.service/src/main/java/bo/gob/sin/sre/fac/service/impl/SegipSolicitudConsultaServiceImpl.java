package bo.gob.sin.sre.fac.service.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bo.gob.sin.sre.fac.service.ISegipSolicitudConsultaService;
@Service
@Transactional
public class SegipSolicitudConsultaServiceImpl implements ISegipSolicitudConsultaService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7705962555231767125L;

//	@Autowired
//	private ISegipConsumerRest iSegipConsumerRest;
//
//	/**
//	 * Obtiene la consulta realizada al Segip para ver la existencia de personas
//	 * 
//	 * @author: Peter Flores
//	 * @Fecha: 14/12/2018
//	 * @param pSegipSolicitudServicioPersonaXNroDocumentoDto, datos primarios de la persona
//	 * @return SegipRespuestaServicioPersonaXNroDocumentoDto objeto Dto de tipo Respuesta.
//	 */	
//	@Override
//	public SegipRespuestaServicioPersonaXNroDocumentoDto  obtenerInformacionDatosSegip( SegipSolicitudServicioPersonaXNroDocumentoDto  pSegipSolicitudServicioPersonaXNroDocumentoDto ) {
//		
//		SegipRespuestaServicioPersonaXNroDocumentoDto resultado =null;
//		try {
//			resultado = iSegipConsumerRest.servicioPersonaXNroDocumento(pSegipSolicitudServicioPersonaXNroDocumentoDto);	
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//        
//        return resultado;
//	}
}
