package bo.gob.sin.sre.fac.cfec.dao;

/**
 * @author edwin.coro
 *
 */
public interface IJsonDao {

	/**
	 * @autor edwin.coro
	 * @descripci贸n
	 * @param pSolicitud
	 * @return
	 * @fecha 22/04/2019
	 */
	public String recepcionBdEtapa1(String pSolicitud);

	/**
	 * @autor edwin.coro
	 * @descripci贸n
	 * @param pSolicitud
	 * @return
	 * @fecha 22/04/2019
	 */
	public String recepcionBdEtapa2(String pSolicitud);

	/**
	 * @autor edwin.coro
	 * @descripci贸n metodo que invoca funciona para el servicio de validacion de
	 *              recepcion
	 * @param pSolicitud dto con parametros de servicio
	 * @return
	 * @fecha 26/03/2019
	 */
	public String validacionRecepcionBd(String pSolicitud);

	/**
	 * @autor edwin.coro
	 * @descripci贸n metodo que invoca funciona para el servicio de validacion de
	 *              recepcion paquete
	 * @param pSolicitud dto con parametros de servicio
	 * @return
	 * @fecha 26/03/2019
	 */
	public String validacionRecepcionPaqueteBd(String pSolicitud);

	public String anulacionBd(String pSolicitud);

	public String validacionAnulacionBd(String pSolicitud);

	/**
	 * @param pNit
	 * @return
	 */
	public Integer validarNitBd(long pNit);

	/**
	 * @param registrarErroresBd
	 * @return
	 */
	public void registrarErroresBd(String pFactura, String pListaErrores);

	/**
	 * @autor edwin.coro
	 * @descripci贸n invoca funcion que obtiene descripcion de codigos de mensaje
	 * @param pSolicitud json que contiene lista de codigos de mensaje
	 * @return lista de descripciones correspondiente a los codigos de mensaje
	 * @fecha 05/06/2019
	 */
	public String obtenerDescripcionCodigosRespuesta(String pSolicitud);

	/**
	 * @autor junior.flores
	 * @descripci贸n ingresa el log para el la certificaci贸n de Sistemas
	 * @param pSolicitud dto con parametros de servicio
	 * @return
	 * @fecha 29/05/2019
	 */
	public String registroLogCertificacionSistemasBd(String pSolicitud);

	/**
	 * @autor edwin.coro
	 * @descripci贸n realiza el registro de envio paquete
	 * @param pSolicitud dto XmlRecepcionGenericoDto en formato json
	 * @fecha 10/06/2019
	 */
	public void registrarLogEtapa6EnvioPaquetesBd(String pSolicitud);

	/**
	 * @autor Reynaldo.Chambi
	 * @descripci贸n realiza el registro de envio masivo
	 * @param pSolicitud dto XmlRecepcionGenericoDto en formato json
	 * @fecha 23/06/2019
	 */
	public String recepcionBdEtapa1Masivo(String pSolicitud);

	/**
	 * @autor Reynaldo.Chambi
	 * @descripci贸n realiza el registro de envio masivo
	 * @param pSolicitud dto XmlRecepcionGenericoDto en formato json
	 * @fecha 23/06/2019
	 */
	public String recepcionBdEtapa2Masivo(String pSolicitud);

	/**
	 * @author edwin.coro
	 * @descripcion verifica que existe registro de recepcion
	 * @param pTipoDocSector   tipo documento sector
	 * @param pCodigoRecepcion codigo de recepcion a verificar
	 * @return valor mayor a 0 si existe recepcion caso contrario 0 no existencia o
	 *         null en caso de excepcion
	 * @fecha 24/06/2019
	 */
	public Integer verificarExistenciaCodigoRecepcion(int pTipoDocSector, long pCodigoRecepcion);

	/**
	 * @autor edwin.coro
	 * @descripci贸n metodo que invoca funcion para la obtencion de codigo recepcion
	 *              individual, apartir del cuf y valores obtenidos en el servicio
	 * @param pSolicitud dto con parametros de servicio
	 * @return codigo recepcion individual o valor 0
	 * @fecha 10/07/2019
	 */
	public Long obtenerCodigoRecIndBd(String pSolicitud);

	/**
	 * @autor reynaldo.chambi
	 * @descripci贸n metodo que invoca funciona para el servicio de validacion de
	 *              recepcion Masivo
	 * @param pSolicitud dto con parametros de servicio
	 * @return
	 * @fecha 20/07/2019
	 */
	public String validacionRecepcionMasivoBd(String pSolicitud);

	/**
	 * @autor reynaldo.chambi
	 * @descripci贸n metodo que invoca funciona para el servicio de validacion de
	 *              recepcion Masivo
	 * @param pSolicitud dto con parametros de servicio
	 * @return
	 * @fecha 24/07/2019
	 */
	public int obtenerCantidadMaximoMasivo(long pContribuyenteId, int pSucursal);

	/**
	 * @autor edwin.coro
	 * @descripci贸n
	 * @param pSolicitud
	 * @fecha 07/08/2019
	 */
	public void registrarLogPruebaObligatoriaEtapa6(String pSolicitud);

	/**
	 * @autor edwin.coro
	 * @descripci贸n
	 * @param pSolicitud
	 * @return
	 * @fecha 15/08/2019
	 */
	public String obtenerFacturaDatosParcial(String pSolicitud);

	/**
	 * @autor edwin.coro
	 * @descripci贸n metodo que invoca funcion para registro de excepciones
	 * @param pSolicitud dto con datos correspondientes a tabla
	 *                   sre_fac_logbd_errores
	 * @fecha 26/08/2019
	 */
	public void registrarLogExcepcionBDErrores(String pSolicitud);

	/**
	 * @autor reynaldo.chmabi
	 * @descripci贸n metodo que invoca funcion para registro de excepciones
	 * @param pSolicitud dto con datos correspondientes a tabla
	 * @fecha 26/08/2019
	 */
	public String obtenerFacturaPush(int pCodigoDocumentoSector, long pRecepcionId, String pCuf);

	public String modificarBdEtapaProcesada(int pTipoDocumentoFiscal, int pSector, long pCodigoRecepcion,
			int estadoPaquete);

	public long obtenerRecepcionAnulacion(String pCuf);

	/**
	 * @autor reynaldo.chmabi
	 * @descripci贸n metodo que modifica registro de archivo
	 * @param pSolicitud dto con datos correspondientes a tabla
	 * @fecha 27/09/2019
	 */
	public String modificarRecepcionPaqueteCantidadArchivo(long pArchivoValidoId, long pRecepcionPaqueteId,
			int pCantidadPaquete, int pSector);

	/**
	 * @autor reynaldo.chmabi
	 * @descripci贸n metodo que recupera la cantidad maxima de paquetes
	 * @fecha 07/10/2019
	 */
	public Integer obtenerCantidadMaximaPaquete();
	
	/**
	 * @autor junior.flores
	 * @descripci贸n m閠odo que obtiene el c骴igo unico de recepci髇 de anulaci髇 
	 * @fecha 25/10/2019
	 */
	public String validacionRecepcionAnulacionBd(String pSolicitud);
}