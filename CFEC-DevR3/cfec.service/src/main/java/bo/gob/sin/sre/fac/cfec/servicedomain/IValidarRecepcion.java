package bo.gob.sin.sre.fac.cfec.servicedomain;

import java.util.List;

import bo.gob.sin.sre.fac.cfec.dto.LogBDErroresDto;
import bo.gob.sin.sre.fac.cfec.dto.LogEtapa6Dto;
import bo.gob.sin.sre.fac.cfec.dto.RespuestaFacturaParcialDto;
import bo.gob.sin.sre.fac.cfec.dto.RespuestaListaRegistroRecepcionesSoapDto;
import bo.gob.sin.sre.fac.cfec.dto.RespuestaValidacionRecepcionPaqueteDto;
import bo.gob.sin.sre.fac.cfec.dto.RespuestaXmlXsdDto;
import bo.gob.sin.sre.fac.cfec.dto.SolicitudFacturaParcialDto;
import bo.gob.sin.sre.fac.cfec.dto.XmlRecepcionGenericoDto;

public interface IValidarRecepcion {

	/**
	 * @autor edwin.coro
	 * @descripción envia a base de datos para la validación y registro del
	 *              documento fiscal
	 * @param xmlRecGenDto dto obtenido a traves de los parametros del servicio
	 * @fecha 03/05/2019
	 */
	public void enviarJsonFacturaHilo(XmlRecepcionGenericoDto xmlRecGenDto);

	/**
	 * @autor edwin.coro
	 * @descripción valida las firmas y estructura de las facturas del paquete,
	 *              posteriormente lo envia a base de datos para la validación y
	 *              registro de cada uno de los documento fiscal
	 * @param xmlRecGenDto dto obtenido a traves de los parametros del servicio
	 * @fecha 03/05/2019
	 */
	public void enviarJsonFacturaPaqueteHilo(XmlRecepcionGenericoDto xmlRecGenDto);

	/**
	 * @autor edwin.coro
	 * @descripción metodo que valida si el hash corresponde al archivo enviado
	 * @param pArchivo     archivo obtenido a traves de los parametros del servicio
	 * @param pHashArchivo hash equivalente al archivo, obtenido a traves de los
	 *                     parametros del servicio
	 * @return codigo de error si el hash no corresponde al archivo
	 * @fecha 03/05/2019
	 */
	public List<Integer> validarHashFactura(String pArchivo, String pHashArchivo);

	/**
	 * @autor edwin.coro
	 * @descripción metodo que determina si la firma es valida
	 * @param pArchivoXml archivo xml correspondiente al documento fiscal
	 * @return codigo de error si la firma no es valida
	 * @fecha 03/05/2019
	 */
	public List<Integer> validarFirmaElectronicaIndividual(String pArchivoXml);

	/**
	 * @autor edwin.coro
	 * @descripción verifica si puede obtenerse la cadena correspondiente al
	 *              documento fiscal
	 * @param pArchivo archivo obtenido a traves de los parametros del servicio
	 * @return codigo de error si el archivo no puede descomprimirse/decodificarse
	 * @fecha 03/05/2019
	 */
	public List<Integer> validarArchivoIndividual(String pArchivo);

	/**
	 * @autor edwin.coro
	 * @descripción verifica si puede obtenerse la cadena correspondiente al
	 *              documento fiscal
	 * @param pArchivo archivo obtenido a traves de los parametros del servicio
	 * @return codigo de error si el archivo no puede descomprimirse/decodificarse
	 * @fecha 03/05/2019
	 */
	public List<Integer> validarArchivoPaquete(String pArchivo);

	/**
	 * @autor edwin.coro
	 * @descripción metodo que valida la estructura del documento fiscal contra el
	 *              xsd correspondiente
	 * @param pArchivoXml    cadena documento fiscal en formato xml
	 * @param pTipoDocFiscal tipo documento fiscal
	 * @param pTipoDocSector tipo documento sector
	 * @return dto con lista de codigo de error y descripcion de errores, si el
	 *         documento fiscal no cumple la estructura del archivo xsd
	 *         (documentoFiscal.xsd)
	 * @fecha 03/05/2019
	 * @autor edwin.coro
	 * @descripción se modifica metodo para contemplar tipo documento fiscal, para
	 *              el caso de contingencias
	 * @fecha 09/10/2019
	 */
	public RespuestaXmlXsdDto validarDocumentoFiscalXsd(String pArchivoXml, int pTipoDocFiscal, int pTipoDocSector);

	/**
	 * @autor edwin.coro
	 * @descripción metodo que valida la estructura del documento fiscal contra el
	 *              xsd correspondiente
	 * @param pArchivoXml    cadena documento fiscal electronico en formato xml
	 * @param pTipoDocFiscal tipo documento fiscal
	 * @param pTipoDocSector tipo documento sector
	 * @return dto con lista de codigo de error y descripcion de errores, si el
	 *         documento fiscal no cumple la estructura del archivo xsd
	 *         (documentoFiscal.xsd)
	 * @fecha 03/05/2019
	 * @descripción se modifica metodo para contemplar tipo documento fiscal, para
	 *              el caso de contingencias
	 * @fecha 09/10/2019
	 */
	public RespuestaXmlXsdDto validarDocumentoFiscalElectronicaXsd(String pArchivoXml, int pTipoDocFiscal,
			int pTipoDocSector);

	/**
	 * @autor edwin.coro
	 * @descripción metodo que valida la estructura y valores determinados en los
	 *              parametros del servicio de recepcion individual
	 * @param xmlRecGenDto dto obtenido a traves de los parametros del servicio
	 * @return dto con lista de codigo de error y descripcion de errores, si los
	 *         valores enviados no cumplen la estructura del archivo xsd
	 *         (recepcionIndividual.xsd)
	 * @fecha 03/05/2019
	 */
	public RespuestaXmlXsdDto validarRecepcionIndXsd(XmlRecepcionGenericoDto xmlRecGenDto);

	/**
	 * @autor edwin.coro
	 * @descripción metodo que valida la estructura y valores determinados en los
	 *              parametros del servicio de recepcion paquete
	 * @param xmlRecGenDto dto obtenido a traves de los parametros del servicio
	 * @return dto con lista de codigo de error y descripcion de errores, si los
	 *         valores enviados no cumplen la estructura del archivo xsd
	 *         (recepcionpaquete.xsd)
	 * @fecha 03/05/2019
	 */
	public RespuestaXmlXsdDto validarRecepcionPaqXsd(XmlRecepcionGenericoDto xmlRecGenDto);

	/**
	 * @autor edwin.coro
	 * @descripción metodo que valida la estructura y valores determinados en los
	 *              parametros del servicio de validacion de recepcion individual
	 * @param xmlRecGenDto dto obtenido a traves de los parametros del servicio
	 * @return dto con lista de codigo de error y descripcion de errores, si los
	 *         valores enviados no cumplen la estructura del archivo xsd
	 *         (validacionrecepcionIndividual.xsd)
	 * @fecha 03/05/2019
	 */
	public RespuestaXmlXsdDto validarValidacionRecepcionIndXsd(XmlRecepcionGenericoDto xmlRecGenDto);

	/**
	 * @autor edwin.coro
	 * @descripción metodo que valida la estructura y valores determinados en los
	 *              parametros del servicio de validacion de recepcion paquete
	 * @param xmlRecGenDto dto obtenido a traves de los parametros del servicio
	 * @return dto con lista de codigo de error y descripcion de errores, si los
	 *         valores enviados no cumplen la estructura del archivo xsd
	 *         (validacionRecepcionPaquete.xsd)
	 * @fecha 03/05/2019
	 */
	public RespuestaXmlXsdDto validarValidacionRecepcionPaqXsd(XmlRecepcionGenericoDto xmlRecGenDto);

	/**
	 * @autor edwin.coro
	 * @descripción metodo que valida la estructura y valores determinados en los
	 *              parametros del servicio de anulacion
	 * @param xmlRecGenDto dto obtenido a traves de los parametros del servicio
	 * @return dto con lista de codigo de error y descripcion de errores, si los
	 *         valores enviados no cumplen la estructura del archivo xsd
	 *         (anulacion.xsd)
	 * @fecha 03/05/2019
	 */
	public RespuestaXmlXsdDto validarAnulacionXsd(XmlRecepcionGenericoDto xmlRecGenDto);

	/**
	 * @autor edwin.coro
	 * @descripción metodo que valida la estructura y valores determinados en los
	 *              parametros del servicio de validacion de anulacion
	 * @param xmlRecGenDto dto obtenido a traves de los parametros del servicio
	 * @return dto con lista de codigo de error y descripcion de errores, si los
	 *         valores enviados no cumplen la estructura del archivo xsd
	 *         (validacionAnulacion.xsd)
	 * @fecha 03/05/2019
	 */
	public RespuestaXmlXsdDto validarValidacionAnulacionXsd(XmlRecepcionGenericoDto xmlRecGenDto);

	/**
	 * @autor edwin.coro
	 * @descripción metodo que invoca funcion en BD y recupera el codigo de
	 *              recepcion individual y paquete(no valida firma ni estructura de
	 *              documentos fiscales)
	 * @param xmlRecGenDto dto obtenido a traves de los parametros del servicio
	 * @return respuesta obtenida por funcion en BD, correspondiente al registro de
	 *         la recepcion
	 * @fecha 03/05/2019
	 */
	public RespuestaListaRegistroRecepcionesSoapDto enviarJsonRecepcionBDEtapa1(XmlRecepcionGenericoDto xmlRecGenDto);

	/**
	 * @autor edwin.coro
	 * @descripción metodo que invoca funcion en BD para el registro del documento
	 *              fiscal individual y paquete(valida firma y estructura de
	 *              documentos fiscales en paquete)
	 * @param xmlRecGenDto dto obtenido a traves de los parametros del servicio
	 * @fecha 03/05/2019
	 */
	public void enviarJsonRecepcionBDEtapa2(XmlRecepcionGenericoDto xmlRecGenDto, int pTipoEmision);

	/**
	 * @autor edwin.coro
	 * @descripción metodo que invoca funcion en BD para realizar la validacion de
	 *              la recepcion individual
	 * @param xmlRecGenDto dto obtenido a traves de los parametros del servicio
	 * @return dto con los datos correspondientes a codigo recepcion, codigo estado,
	 *         lista errores (si hubiera)
	 * @fecha 03/05/2019
	 */
	public RespuestaListaRegistroRecepcionesSoapDto validacionRecepcionBd(XmlRecepcionGenericoDto xmlRecGenDto);

	/**
	 * @autor edwin.coro
	 * @descripción metodo que invoca funcion en BD para realizar la validacion de
	 *              la recepcion paquete
	 * @param xmlRecGenDto dto obtenido a traves de los parametros del servicio
	 * @return dto con los datos correspondientes a codigo recepcion, codigo estado,
	 *         lista errores (si hubiera) y lista de detalles de errores (si
	 *         hubiera)
	 * @fecha 03/05/2019
	 */
	public RespuestaValidacionRecepcionPaqueteDto validacionRecepcionPaqueteBd(XmlRecepcionGenericoDto xmlRecGenDto);

	/**
	 * @autor edwin.coro
	 * @descripción metodo que invoca funcion en BD para el registro de solicitud de
	 *              anulacion de documento fiscal.
	 * @param xmlRecGenDto dto obtenido a traves de los parametros del servicio
	 * @return dto con los datos correspondientes a codigo recepcion, codigo estado,
	 *         lista errores (si hubiera)
	 * @fecha 03/05/2019
	 */
	public RespuestaListaRegistroRecepcionesSoapDto anulacionBd(XmlRecepcionGenericoDto xmlRecGenDto);

	/**
	 * @autor edwin.coro
	 * @descripción metodo que invoca funcion en BD para la validacion de anulacion
	 *              de documento fiscal.
	 * @param xmlRecGenDto dto obtenido a traves de los parametros del servicio
	 * @return dto con los datos correspondientes a codigo recepcion, codigo estado,
	 *         lista errores (si hubiera)
	 * @fecha 03/05/2019
	 */
	public RespuestaListaRegistroRecepcionesSoapDto validacionAnulacionBd(XmlRecepcionGenericoDto xmlRecGenDto);

	/**
	 * @autor edwin.coro
	 * @descripción invoca metodo que obtiene descripcion de codigos de mensaje
	 * @param pSolicitud lista de codigos de mensaje
	 * @return lista de descripciones correspondiente a los codigos de mensaje
	 * @fecha 05/06/2019
	 */
	public List<String> obtenerDescripcionesCodigosRespuesta(List<Integer> pSolicitud);

	/**
	 * @autor edwin.coro
	 * @descripción metodo que invoca funcion en BD y recupera el codigo de
	 *              recepcion individual y paquete(no valida firma ni estructura de
	 *              documentos fiscales)
	 * @param xmlRecGenDto dto obtenido a traves de los parametros del servicio
	 * @return respuesta obtenida por funcion en BD, correspondiente al registro de
	 *         la recepcion
	 * @fecha 03/05/2019
	 */
	public RespuestaListaRegistroRecepcionesSoapDto recepcionarValidarPortalWeb(XmlRecepcionGenericoDto xmlRecGenDto);

	public Boolean validarCuf(XmlRecepcionGenericoDto pXmlRecGenDto, String xmlB64, int pTipoEmision);

	/**
	 * @autor edwin.coro
	 * @descripción metodo que valida si el documento de identidad es valido
	 * @param pArchivoXml documento fiscal en formato xml
	 * @return lista con posibles errores, caso contrario lista vacias
	 * @fecha 12/06/2019
	 */
	public List<Integer> validarDocumentoIdentidadNitCi(String pArchivoXml);

	/**
	 * @autor edwin.coro
	 * @descripción metodo que valida si el codigo excepcion documento (codigo
	 *              recepcion) es valido
	 * @param pArchivoXml documento fiscal en formato xml
	 * @return true si es valido
	 * @fecha 12/06/2019
	 */
	public boolean validarCodExcDoc(String pArchivoXml);

	/**
	 * @autor reynaldo.chambi
	 * @descripción metodo que valida la estructura y valores determinados en los
	 *              parametros del servicio de recepcion masivos
	 * @param xmlRecGenDto dto obtenido a traves de los parametros del servicio
	 * @return dto con lista de codigo de error y descripcion de errores, si los
	 *         valores enviados no cumplen la estructura del archivo xsd
	 *         (recepcionMasiva.xsd)
	 * @fecha 19/06/2019
	 */
	public RespuestaXmlXsdDto validarRecepcionMasXsd(XmlRecepcionGenericoDto xmlRecGenDto);

	/**
	 * @autor reynaldo.chambi
	 * @descripción metodo que invoca funcion en BD y recupera el codigo de
	 *              recepcion masivo(no valida firma ni estructura de documentos
	 *              fiscales)
	 * @param xmlRecGenDto dto obtenido a traves de los parametros del servicio
	 * @return respuesta obtenida por funcion en BD, correspondiente al registro de
	 *         la recepcion
	 * @fecha 18/06/2019
	 */
	public RespuestaListaRegistroRecepcionesSoapDto enviarJsonRecepcionBDEtapa1Masivo(
			XmlRecepcionGenericoDto xmlRecGenDto);

	/**
	 * @autor reynaldo.chambi
	 * @descripción metodo que invoca funcion en BD para el registro del documento
	 *              fiscal masiva(valida firma y estructura de documentos fiscales
	 *              en paquete)
	 * @param xmlRecGenDto dto obtenido a traves de los parametros del servicio
	 * @fecha 24/06/2019
	 */
	public void enviarJsonFacturaMasivoHilo(XmlRecepcionGenericoDto xmlRecGenDto);

	/**
	 * @autor reynaldo.chambi
	 * @descripción metodo que valida la estructura y valores determinados en los
	 *              parametros del servicio de validacion de recepcion masiva
	 * @param xmlRecGenDto dto obtenido a traves de los parametros del servicio
	 * @return dto con lista de codigo de error y descripcion de errores, si los
	 *         valores enviados no cumplen la estructura del archivo xsd
	 *         (validacionRecepcionMasiva.xsd)
	 * @fecha 25/06/2019
	 */
	public RespuestaXmlXsdDto validarValidacionRecepcionMasXsd(XmlRecepcionGenericoDto xmlRecGenDto);

	/**
	 * @autor reynaldo.chambi
	 * @descripción metodo que valida la estructura y valores determinados en los
	 *              parametros del servicio de validacion de recepcion masiva
	 * @param xmlRecGenDto dto obtenido a traves de los parametros del servicio
	 * @return dto con lista de codigo de error y descripcion de errores, si los
	 *         valores enviados no cumplen la estructura del archivo xsd
	 *         (validacionRecepcionMasiva.xsd)
	 * @fecha 25/06/2019
	 */
	public RespuestaValidacionRecepcionPaqueteDto validacionRecepcionMasivaBd(XmlRecepcionGenericoDto xmlRecGenDto);

	/**
	 * @autor junior.flores
	 * @descripción Método que realiza la inserción de los log para el consumo de
	 *              los servicios
	 * @param pXmlRecGenDto: Archivo en formato xml con la información de la
	 *        recepción pListaErrores Listado de la descripción de los errores luego
	 *        de la validación XML/XSD pResultado: Resultado de la transacción
	 *        pCasoPrueba: Descripción del Caso de Prueba pRecepcionId: Código de la
	 *        recepción
	 * @fecha 30/05/2019
	 */
	public void registrarLogConsumoServicio(XmlRecepcionGenericoDto pXmlRecGenDto, Integer pResultado,
			String pCasoPrueba, Long pRecepcionId);

	/**
	 * @autor edwin.coro
	 * @descripción metodo que invoca funcion para la obtencion de codigo recepcion
	 *              individual, apartir del cuf y valores obtenidos en el servicio
	 * @param pSolicitud dto con parametros de servicio
	 * @return codigo recepcion individual o valor 0
	 * @fecha 10/07/2019
	 */
	public Long obtenerCodigoRecIndBd(XmlRecepcionGenericoDto pXmlRecGenDto);

	/**
	 * @autor Reynaldo Chambi
	 * @descripción metodo que invoca funcion para la obtencion de codigo recepcion
	 *              individual, apartir del cuf y valores obtenidos en el servicio
	 * @param pSolicitud dto con parametros de servicio
	 * @return codigo recepcion individual o valor 0
	 * @fecha 23/07/2019
	 */
	public List<Integer> validarArchivoMasivo(String pArchivo, long pNit, int pSucursal);

	/**
	 * @autor edwin.coro
	 * @descripción
	 * @param pLogEtapa6
	 * @fecha 07/08/2019
	 */
	public void registrarLogPruebaObligatoriaEtapa6(LogEtapa6Dto pLogEtapa6);

	/**
	 * @autor reynaldo.chambi
	 * @descripción
	 * @param pArchivoXml
	 * @fecha 09/08/2019
	 */
	public RespuestaXmlXsdDto validarDocumentoFiscalComprasXsd(String pArchivoXml);

	/**
	 * @autor reynaldo.chambi
	 * @descripción
	 * @param pArchivoXml
	 * @fecha 09/08/2019
	 */
	public RespuestaListaRegistroRecepcionesSoapDto enviarJsonComprasRecepcionBDEtapa1(
			XmlRecepcionGenericoDto xmlRecGenDto);

	/**
	 * @autor reynaldo.chambi
	 * @descripción
	 * @param pArchivoXml
	 * @fecha 09/08/2019
	 */
	public String obtenerCuf(XmlRecepcionGenericoDto pXmlRecGenDto);

	/**
	 * @autor reynaldo.chambi
	 * @descripción
	 * @param pArchivoXml
	 * @fecha 09/08/2019
	 */
	public void enviarJsonComprasRecepcionBDEtapa2(XmlRecepcionGenericoDto xmlRecGenDto);

	/**
	 * @autor reynaldo.chambi
	 * @descripción
	 * @param pArchivoXml
	 * @fecha 09/08/2019
	 */
	public void enviarJsonComprasRecepcionPaqueteBDEtapa2(XmlRecepcionGenericoDto xmlRecGenDto);

	/**
	 * @autor reynaldo.chambi
	 * @descripción
	 * @param pArchivoXml
	 * @fecha 09/08/2019
	 */
	public void enviarJsonComprasFacturaHilo(XmlRecepcionGenericoDto xmlRecGenDto);

	/**
	 * @autor reynaldo.chambi
	 * @descripción
	 * @param pArchivoXml
	 * @fecha 09/08/2019
	 */
	public void enviarJsonComprasFacturaPaqueteHilo(XmlRecepcionGenericoDto xmlRecGenDto);

	/**
	 * @autor reynaldo.chambi
	 * @descripción
	 * @param pArchivoXml
	 * @fecha 09/08/2019
	 */
	public RespuestaListaRegistroRecepcionesSoapDto validacionRecepcionComprasBd(XmlRecepcionGenericoDto xmlRecGenDto);

	/**
	 * @autor reynaldo.chambi
	 * @descripción
	 * @param pArchivoXml
	 * @fecha 09/08/2019
	 */
	public RespuestaValidacionRecepcionPaqueteDto validacionRecepcionComprasPaqueteBd(
			XmlRecepcionGenericoDto xmlRecGenDto);

	/**
	 * @autor edwin.coro
	 * @descripción
	 * @param pSolicitud
	 * @return
	 * @fecha 14/08/2019
	 */
	public RespuestaFacturaParcialDto obtenerFacturaDatosParcial(SolicitudFacturaParcialDto pSolicitud);

	/**
	 * @autor reynaldo.chambi
	 * @descripción
	 * @param pSolicitud
	 * @return
	 * @fecha 16/08/2019
	 */
	public void registrarLogAnulacionFactura(XmlRecepcionGenericoDto pXmlRecGenDto, Integer pResultado,
			String pCasoPrueba, Long pRecepcionId);

	/**
	 * @autor reynaldo.chambi
	 * @descripción
	 * @param pSolicitud
	 * @return
	 * @fecha 19/08/2019
	 */
	public RespuestaXmlXsdDto validarRecepcionComprasIndXsd(XmlRecepcionGenericoDto xmlRecGenDto);

	/**
	 * @autor edwin.coro
	 * @descripción metodo que invoca funcion para registro de excepciones
	 * @param pSolicitud dto con datos correspondientes a tabla
	 *                   sre_fac_logbd_errores
	 * @fecha 26/08/2019
	 */
	public void registrarLogExcepcionBDErrores(LogBDErroresDto pLogBDErroresDto);

	/**
	 * @autor edwin.coro
	 * @descripción metodo que invoca funcion para registro de excepciones
	 * @param pSolicitud dto con datos correspondientes a tabla
	 *                   sre_fac_logbd_errores
	 * @fecha 26/08/2019
	 */
	public void procesarMasivoIndividual(XmlRecepcionGenericoDto xmlRecGenDto, int pTipoEmision, String xmlB64);

	/**
	 * @autor reynaldo.chambi
	 * @descripción metodo que invoca funcion para paquete individual
	 * @param xmlRecGenDto, pTipoEmision, xmlB64
	 * @fecha 12/09/2019
	 */
	public void procesarPaqueteIndividual(XmlRecepcionGenericoDto xmlRecGenDto, int pTipoEmision, String xmlB64);

	/**
	 * @autor reynaldo.chambi
	 * @descripción metodo que invoca funcion para obtener recepcion anulacion.
	 * @param xmlRecGenDto
	 * @fecha 18/09/2019
	 */
	public RespuestaListaRegistroRecepcionesSoapDto obtenerRecepcionAnulacionPorCuf(
			XmlRecepcionGenericoDto xmlRecGenDto);
	
	/**
	 * @autor junior.flores
	 * @descripción metodo que invoca funcion para obtener la recepcion anulacion a trav�s del cuf y el numero de documento fiscal.
	 * @param xmlRecGenDto
	 * @fecha 25/10/2019
	 */
	public RespuestaListaRegistroRecepcionesSoapDto recepcionAnulacionBd(XmlRecepcionGenericoDto xmlRecGenDto);
	
	/**
	 * @autor junior.flores
	 * @descripci�n metodo que realiza la validaci�n del xsd con el xml
	 * @param XmlRecepcionGenericoDto  dto en formato json, obtenido a traves de los
	 *                                 parametros del servicio
	 * @fecha 29/10/2019
	 */	
	public RespuestaXmlXsdDto validarRecepcionAnulacionXsd(XmlRecepcionGenericoDto pDto);
}