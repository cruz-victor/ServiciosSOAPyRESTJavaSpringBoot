package bo.gob.sin.sre.fac.csis.query;

import java.util.List;

import bo.gob.sin.sre.fac.dto.ListaSistemasSolicitudCertificacionDto;
import bo.gob.sin.sre.fac.dto.ModalidadDto;
import bo.gob.sin.sre.fac.dto.RegistroVerificacionInsituDto;
import bo.gob.sin.sre.fac.dto.RespuestaCalcularPorcentajePruebasEtapa2Dto;
import bo.gob.sin.sre.fac.dto.RespuestaListaDetallePruebasEtapas2Dto;
import bo.gob.sin.sre.fac.dto.RespuestaMatchLogCasosPruebaEtapa2Dto;
import bo.gob.sin.sre.fac.dto.RespuestaPruebaSugeridaDto;
import bo.gob.sin.sre.fac.dto.RespuestaRegistroVerificacionInsituDto;
import bo.gob.sin.sre.fac.dto.RespuestaResumenPruebaCertificacionSistemaDto;
import bo.gob.sin.sre.fac.dto.SistemasSolicitudCertificacionDto;

public interface IEtapa2Query {

	/**
	 * Identifica los registros LOG y CASOS DE PRUEBAS que sean iguales. Es decir, segun el codigo hash almacenado en tabla LOG y 
	 * CASOS DE PRUEBA, identifica los registro que sean iguales. En caso de ser iguales actualiza los valores de los campos: 
					estado_match=1; 
					caso_prueba_id=Identficador del caso de prueba; 
	 * @param sistemaId - Codigo del sistema para identificar los registros de la tabla LOG y CASOS DE PRUEBA de la etapa 2.
	 * @param documentoSectorId - Codigo del documento sector.
	 * @return 1 - Ejecucion exitosa  0 - Ocurrio una excepcion.
	 */	
	RespuestaMatchLogCasosPruebaEtapa2Dto matchLogCasosPruebaEtapa2(long pSistemaId, int pDocumentoSectorId);
	/**
	 * Calcula el porcentaje de pruebas realizadas en la etapa 2 por sector. 
	 * @param sistemaId - Codigo del sistema para identificar los registros de la tabla LOG y CASOS DE PRUEBA de la etapa 2.
	 * @param documentoSectorId - Codigo del documento sector.
	 * @return Porcentaje - Porcentaje de pruebas efectuadas.
	 */	
	RespuestaCalcularPorcentajePruebasEtapa2Dto calcularPorcentajePruebasEtapa2(long pSistemaId, int pDocumentoSectorId);
	/**
	 * Obtiene una lista con todos los casos de prueba de la etapa 2 efectuados por el contribuente. 
	 * @param sistemaId - Codigo del sistema para identificar los registros de la tabla LOG y CASOS DE PRUEBA de la etapa 2.
	 * @param documentoSectorId - Codigo del documento sector.
	 * @return Json - Detalle de pruebas realizadas en la etapa 2.
	 */	
	RespuestaListaDetallePruebasEtapas2Dto obtenerDetallePruebasEtapa2(long pSistemaId);
	/**
	 * Metodo que permite guardar si un caso de prueba fue checado o no. 
	  @param long pSistemaId - Identificador del sistema de facturacion. 
	  @param String pNro - Nro de caso de prueba.
	  @param String pTipoPrueba - Tipo de prueba (Sugerido/obligatorio).
	  @param String pTipoEmision -	Tipo de emision (Online/offline).
	  @param String pParametroEntrada - Parametro de entrada del caso de prueba.
	  @param String pDocumentoSector - Documento sector al que pertenece el caso de prueba.
	  @return 1 - Si la accion fue realizado exitosamente. 0 - Si la accion no fue realizado correctamente.
	 */	
	RespuestaPruebaSugeridaDto registrarPruebaSugeridaEtapa2(long pSistemaId, String pNro, String pTipoPrueba, String pTipoEmision,	String pParametroEntrada, String pDocumentoSector, String pAccion);
	
	RespuestaRegistroVerificacionInsituDto obtenerListaVerificacionInsituPorSolicitudCertificacionId(long SolicitudCertificacionId, long tipoPrueba);
	
	RespuestaResumenPruebaCertificacionSistemaDto obtenerResumenPruebaCertificacionSistema(long pSistemaId);
	
	public ListaSistemasSolicitudCertificacionDto obtenerListadoSistemasFase2(long pContribuyenteId);
	
}

