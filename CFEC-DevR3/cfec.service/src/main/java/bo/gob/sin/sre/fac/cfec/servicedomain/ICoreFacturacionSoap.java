package bo.gob.sin.sre.fac.cfec.servicedomain;

import bo.gob.sin.sre.fac.cfec.dto.RespuestaListaRegistroRecepcionesSoapDto;
import bo.gob.sin.sre.fac.cfec.dto.RespuestaValidacionRecepcionPaqueteDto;
import bo.gob.sin.sre.fac.cfec.dto.XmlRecepcionGenericoDto;

public interface ICoreFacturacionSoap {

	/**
	 * @autor edwin.coro
	 * @descripción metodo que es invocado desde los proyectos SOAP
	 * @param xmlRecGenDto dto obtenido a traves de los parametros del servicio
	 * @param vEtapa       valor que determina que procedimiento seguir Recepcion,
	 *                     ValidacionRecepcion, RecepcionPaquete, Anulacion,
	 *                     ValidacionAnulacion
	 * @return dto con valores de codigo recepcion, codigo estado, ...
	 * @fecha 03/05/2019
	 */
	public RespuestaListaRegistroRecepcionesSoapDto CoreFacturacionPrincipal(XmlRecepcionGenericoDto xmlRecGenDto,
			Integer vEtapa);

	/**
	 * @autor edwin.coro
	 * @descripción metodo que es invocado desde los proyectos SOAP
	 * @param xmlRecGenDto dto obtenido a traves de los parametros del servicio
	 * @param vEtapa       valor que determina que procedimiento seguir
	 *                     ValidacionRecepcionPaquete
	 * @return dto con valores de codigo recepcion, codigo estado, ...
	 * @fecha 03/05/2019
	 */
	public RespuestaValidacionRecepcionPaqueteDto CoreFacturacionPrincipalPaquete(XmlRecepcionGenericoDto xmlRecGenDto,
			Integer vEtapa);

	/**
	 * @autor reynaldo.chambi
	 * @descripción metodo que es invocado desde los proyectos SOAP
	 * @param xmlRecGenDto dto obtenido a traves de los parametros del servicio
	 * @param vEtapa       valor que determina que procedimiento seguir ValidacionRecepcionMasiva
	 * @return dto con valores de codigo recepcion, codigo estado, ...
	 * @fecha 25/06/2019
	 */
	public RespuestaValidacionRecepcionPaqueteDto CoreFacturacionPrincipalMasiva(XmlRecepcionGenericoDto vArchivoXml, Integer vEtapa);
}