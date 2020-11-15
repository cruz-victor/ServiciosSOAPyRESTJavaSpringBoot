package bo.gob.sin.sre.fac.service;

import bo.gob.sin.sre.fac.dto.GenerarHuellaSistemaDto;
import bo.gob.sin.sre.fac.dto.ListaRegistroHuellasDigitalesDto;
import bo.gob.sin.sre.fac.dto.RegistrarHuellaSistemaDto;
import bo.gob.sin.sre.fac.dto.RegistroHuellasDigitalesDto;
import bo.gob.sin.sre.fac.dto.RespuestaDetalleHuellaDto;
import bo.gob.sin.sre.fac.dto.RespuestaOperacionDto;

public interface IRegistroHuellasSistemaService {
	
	/**
	 * Registro de huellas digitales de sistema.  
	 * 
	 * @author: Peter Flores
	 * @Fecha: 15/11/2018
	 * @param pSolicitud, objeto de tipo solicitud
	 * @return   Devuelve objeto Dto de tipo RegistroHuellasDigitalesDto.
	 */
	public RegistroHuellasDigitalesDto registrarHuellaDigitalSistema(RegistroHuellasDigitalesDto pSolicitud);

	//IASC
	public String algoritmoHash(byte[] pArchivo, String algorithm);
	
	//IASC
	public String calCrc32(byte[] data);
	
	/**
	 * Genera codigo SHA-256, MD5 y CRC32
	 * 
	 * @author: Peter Flores
	 * @Fecha: 14/11/2018
	 * @param pSolicitud, objeto de tipo generar huella digital de sistema
	 * @return   Devuelve objeto Dto de tipo GenerarHuellaSistemaDto.
	 */
	public RegistrarHuellaSistemaDto obtenerCifradoHuellaDigital(GenerarHuellaSistemaDto pSolicitud) ;
	
	/**
	 * Registro del listado de huellas digitales de sistema.  
	 * 
	 * @author: Peter Flores
	 * @Fecha: 16/11/2018
	 * @param pSolicitud, listado de la entidad huellas digitales de sistema.
	 * @return   Devuelve objeto Dto de tipo RegistroHuellasDigitalesDto.
	 */
	public RegistroHuellasDigitalesDto registrarHuellasDigitalesSistemas(ListaRegistroHuellasDigitalesDto pSolicitud);
	
	/**
	 * Obtiene el registro del listado de huellas digitales de sistema.  
	 * 
	 * @author: Peter Flores
	 * @Fecha: 20/11/2018
	 * @param pSistemaId, listado de la entidad huellas digitales de sistema.
	 * @return   Devuelve una lista de tipo ListaRegistroHuellasDigitalesDto.
	 */
	public ListaRegistroHuellasDigitalesDto obtieneComponentesArchivos(Long pSistemaId);
	
	/** 
	 * Registro de huellas digitales de sistema.  
	 * 
	 * @author: Wilson Limachi
	 * @Fecha: 20/09/2019
	 * @param pSolicitud, objeto de tipo solicitud
	 * @return   Devuelve objeto Dto de tipo RegistroHuellasDigitalesDto.
	 */
	public RespuestaOperacionDto registrarComponentesArchivos(RespuestaDetalleHuellaDto pSolicitud); 

}
